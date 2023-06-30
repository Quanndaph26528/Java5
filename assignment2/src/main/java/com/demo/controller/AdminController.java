package com.demo.controller;

import com.demo.model.Account;
import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.repository.AccountRepository;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public AdminController(ProductRepository productRepository, CategoryRepository categoryRepository, AccountRepository accountRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
    }

    // Category
    @GetMapping("/category/index")
    public String listCategory(Model model) {
        Category item = new Category();
        model.addAttribute("item", item);
        List<Category> items = categoryRepository.findAll();
        model.addAttribute("items", items);
        return "admin/category/index";
    }

    @PostMapping("/category/create")
    public String createCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/admin/category/index";
    }

    @RequestMapping("/category/edit/{id}")
    public String editCategory(@PathVariable("id") String id, Model model) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category item = optionalCategory.get();
            model.addAttribute("item", item);
        }
        List<Category> items = categoryRepository.findAll();
        model.addAttribute("items", items);
        return "admin/category/index";
    }

    @RequestMapping("/category/update")
    public String updateCategory(Category item) {
        categoryRepository.save(item);
        return "redirect:/admin/category/edit/" + item.getId();
    }
    @RequestMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id) {
        categoryRepository.deleteById(id);
        return "redirect:/admin/category/index";
    }

    // Product
    @GetMapping("/product/index")
    public String listProduct(Model model) {
        Product item = new Product();
        model.addAttribute("item", item);
        model.addAttribute("listCategory", categoryRepository.findAll());
        List<Product> items = productRepository.findAll();
        model.addAttribute("items", items);
        return "admin/product/index";
    }

    @PostMapping("/product/create")
    public String createProduct(Product item) {
        productRepository.save(item);
        return "redirect:/admin/product/index";
    }

    @RequestMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product item = optionalProduct.get();
            model.addAttribute("item", item);
        }
        List<Product> items = productRepository.findAll();
        model.addAttribute("items", items);
        model.addAttribute("listCategory1", categoryRepository.findAll());
        return "admin/product/index";
    }

    @RequestMapping("/product/update")
    public String updateProduct(Product item) {
        productRepository.save(item);
        return "redirect:/admin/product/edit/" + item.getId();
    }

    @RequestMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productRepository.deleteById(id);
        return "redirect:/admin/product/index";
    }

    // Account
    @RequestMapping("/account/index")
    public String index(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        List<Account> accounts = accountRepository.findAll();
        model.addAttribute("accounts", accounts);
        return "admin/account/index";
    }

    @RequestMapping("/account/create")
    public String createAccount(@ModelAttribute("account") Account account) {
        accountRepository.save(account);
        return "redirect:/admin/account/index";
    }

    @RequestMapping("/account/edit/{id}")
    public String editAccount(Model model, @PathVariable("id") String id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            model.addAttribute("account", account);
        }
        List<Account> accounts = accountRepository.findAll();
        model.addAttribute("accounts", accounts);
        return "admin/account/index";
    }

    @RequestMapping("/account/update")
    public String updateAccount(@ModelAttribute("account") Account account) {
        accountRepository.save(account);
        return "redirect:/admin/account/index";
    }

    @RequestMapping("/account/delete/{id}")
    public String deleteAccount(@PathVariable("id") String id) {
        accountRepository.deleteById(id);
        return "redirect:/admin/account/index";
    }

}
