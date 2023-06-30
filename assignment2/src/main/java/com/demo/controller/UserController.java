package com.demo.controller;

import com.demo.model.*;
import com.demo.repository.*;
import com.demo.service.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
	@Autowired
	HttpSession session;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@Autowired
	ProductRepository productRepo;

	@Autowired
	CategoryRepository categoryRepo;

	@Autowired
	CartService cart;
	@Autowired
	AccountService accountService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
	UserCartRepository userCartRepository;


	@Autowired
	AccountRepository accountRepo;
	@ModelAttribute("cart")
	CartService getCart(){
		return cart;
	}

	@Data @AllArgsConstructor
	public static class PriceRange{
		int id;
		int minValue;
		int maxValue;
		String display;
	}

	List<PriceRange> priceRangeList = Arrays.asList(
		new PriceRange(0,0, Integer.MAX_VALUE, "Tất cả"),
		new PriceRange(1,0, 10000000, "Dưới 10 triệu"),
		new PriceRange(2,10000000, 20000000, "Từ 10-20 triệu"),
		new PriceRange(3,20000000, Integer.MAX_VALUE, "Trên 20 triệu")
	);
	@RequestMapping("/")
	public String index(
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "") String categoryId,
			@RequestParam(defaultValue = "0") int priceRangeId,
			@RequestParam(defaultValue = "0") int page,
			Model model) {

		if (session.getAttribute("username") == null) {
			return "redirect:/login";
		}

		model.addAttribute("priceRangeList", priceRangeList);
		model.addAttribute("categoryList", categoryService.getAll());

		int minPrice = priceRangeList.get(priceRangeId).getMinValue();
		int maxPrice = priceRangeList.get(priceRangeId).getMaxValue();

		Pageable pageable = PageRequest.of(page, 5);
		Page<Product> data;
		if (!categoryId.isEmpty() && !keyword.isEmpty()) {
			// Tìm kiếm theo cả danh mục và tên
			Category category = categoryRepo.findById(categoryId).orElse(null);

			if (category != null) {
				data = productRepo.searchByNameCategoryAndPriceRange(keyword, categoryId, minPrice, maxPrice, pageable);
			} else {
				// Handle category not found
				data = Page.empty();
			}
		} else if (!categoryId.isEmpty()) {
			// Tìm kiếm theo danh mục
			Category category = categoryRepo.findById(categoryId).orElse(null);

			if (category != null) {
				data = productRepo.searchByCategoryIdAndPriceRange(categoryId, minPrice, maxPrice, pageable);
			} else {
				// Handle category not found
				data = Page.empty();
			}
		} else if (!keyword.isEmpty()) {
			// Tìm kiếm theo tên
			data = productRepo.searchByNamePrice("%" + keyword + "%", minPrice, maxPrice, pageable);
		} else {
			// Tìm kiếm tất cả sản phẩm
			data = productRepo.findAllByPriceRange(minPrice, maxPrice, pageable);
		}

		model.addAttribute("productList", data.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", data.getTotalPages());
		model.addAttribute("totalProducts", data.getTotalElements());
		model.addAttribute("startProduct", pageable.getPageSize() * page + 1);
		model.addAttribute("endProduct", Math.min(pageable.getPageSize() * (page + 1), data.getTotalElements()));
		model.addAttribute("categoryId", categoryId); // Add categoryId to model for maintaining selected category in the view

		return "home/index";
	}

// TODO: Pass the data to the view or perform additional operations

	@GetMapping("/detail/{id}")
	public String viewProduct(@PathVariable int id, Model model) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "home/detail";
	}

	@RequestMapping("/add-to-cart/{id}")
	public String addToCart(@PathVariable int id){
		cart.add(id);
		return "redirect:/cart";
	}

	@RequestMapping("/remove-cart/{id}")
	public String removeCart(@PathVariable int id) {
		cart.remove(id);
		if(cart.getTotal() == 0){
			return "redirect:/";
		}
		return "redirect:/cart";
	}

	@RequestMapping("/update-cart/{id}")
	public String updateCart(@PathVariable int id, int quantity) {
		cart.update(id, quantity);
		return "redirect:/cart";
	}

	@GetMapping("/cart")
	public String cart(){
		return "home/cart";
	}

	@GetMapping("/confirm")
	public String confirm(){

		return "home/confirm";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		return "home/about";
	}
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model) {
		// TODO: Check if user/password exists in database
		boolean isValidCredentials = accountService.checkCredentials(username, password);

		if (isValidCredentials) {
			session.setAttribute("username", username);
			// TODO: Check if user has admin role and set admin attribute in session
			boolean isAdmin = accountService.isAdmin(username);
			if (isAdmin) {
				session.setAttribute("admin", true);
				return "redirect:/admin/category/index";
			}
			return "redirect:/";
		}

		model.addAttribute("message", "Tên đăng nhập/mật khẩu không đúng");
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@PostMapping("/register")
	public String register(@ModelAttribute("account") Account account, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		// Kiểm tra lỗi nhập liệu
		if (bindingResult.hasErrors()) {
			// Xử lý lỗi và thông báo cho người dùng
			redirectAttributes.addFlashAttribute("error", "Đã có lỗi xảy ra. Vui lòng thử lại.");
			return "redirect:/register";
		}

		// Kiểm tra xem tên người dùng đã tồn tại chưa
		if (accountService.findByUsername(account.getUsername()) != null) {
			redirectAttributes.addFlashAttribute("error", "Tên tài khoản đã tồn tại. Vui lòng chọn tên khác.");
			return "redirect:/register";
		}

		// Lưu đối tượng Account vào cơ sở dữ liệu
		accountService.save(account);

		redirectAttributes.addFlashAttribute("success", "Đăng ký tài khoản thành công. Vui lòng đăng nhập.");
		return "redirect:/login";
	}




	@PostMapping("/purchase")
	public String purchase(@RequestParam String address) {
		System.out.println("address=" + address);
		System.out.println("items=" + cart.getItems());
		String un = (String) session.getAttribute("username");
		Account acc = accountRepo.findById(un).orElse(null);
		if (acc != null) {
			Order order = new Order();
			order.setAccount(acc);
			order.setAddress(address);
			orderRepository.save(order);

			for (OrderDetail item : cart.getItems()) {
				item.setOrder(order);
				orderDetailRepository.save(item);
			}

		}
		cart.clearCart();

		return "redirect:/";
	}


	@GetMapping("/logout")
	public String logout(){

		cart.clearCart();
		return "redirect:/login";
	}
}
