package com.demo.controller;

import com.demo.model.OrderDetail;
import com.demo.repository.OrderDetailRepository;
import com.demo.repository.OrderRepository;
import com.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class OrderDetailsController {
    @Autowired
    private OrderDetailRepository orderDetailRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private OrderRepository orderRepo;

    @RequestMapping("/order-details")
    public String index(Model model) {
        List<OrderDetail> orderDetails = orderDetailRepo.findAll();
        model.addAttribute("orderDetails", orderDetails);
        return "order-details/list";
    }

    @RequestMapping("/order-details/create")
    public String create(Model model) {
        OrderDetail orderDetail = new OrderDetail();
        model.addAttribute("orderDetail", orderDetail);
        model.addAttribute("products", productRepo.findAll());
        model.addAttribute("orders", orderRepo.findAll());
        return "order-details/create";
    }

    @RequestMapping(value = "/order-details/create", method = RequestMethod.POST)
    public String save(@ModelAttribute("orderDetail") OrderDetail orderDetail) {
        orderDetailRepo.save(orderDetail);
        return "redirect:/order-details";
    }

    @RequestMapping("/order-details/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepo.findById(id);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail orderDetail = optionalOrderDetail.get();
            model.addAttribute("orderDetail", orderDetail);
            return "order-details/view";
        } else {
            return "redirect:/order-details";
        }
    }

    @RequestMapping("/order-details/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepo.findById(id);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail orderDetail = optionalOrderDetail.get();
            model.addAttribute("orderDetail", orderDetail);
            model.addAttribute("products", productRepo.findAll());
            model.addAttribute("orders", orderRepo.findAll());
            return "order-details/edit";
        } else {
            return "redirect:/order-details";
        }
    }

    @RequestMapping(value = "/order-details/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("orderDetail") OrderDetail orderDetail) {
        orderDetailRepo.save(orderDetail);
        return "redirect:/order-details";
    }

    @RequestMapping("/order-details/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        orderDetailRepo.deleteById(id);
        return "redirect:/order-details";
    }
}
