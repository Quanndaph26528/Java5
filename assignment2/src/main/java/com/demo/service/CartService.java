package com.demo.service;

import com.demo.model.OrderDetail;
import com.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SessionScope
@Service
public class CartService {
    @Autowired
    private ProductService productService;

    private List<OrderDetail> items = new ArrayList<>();

    public List<OrderDetail> getItems() {
        return items;
    }

    public void add(int id) {
        OrderDetail item = items.stream()
                .filter(it -> it.getProduct().getId() == id)
                .findFirst()
                .orElse(null);

        if (item != null) {
            item.setQuantity(item.getQuantity() + 1);
        } else {
            Product product = productService.findById(id);
            if (product != null) {
                items.add(new OrderDetail(null, product.getPrice(), 1, product, null));
            }
        }
    }

    public void remove(int id) {
        items = items.stream()
                .filter(it -> it.getProduct().getId() != id)
                .collect(Collectors.toList());
    }

    public void update(int id, int quantity) {
        OrderDetail item = items.stream()
                .filter(it -> it.getProduct().getId() == id)
                .findFirst()
                .orElse(null);

        if (item != null) {
            item.setQuantity(quantity);
        }
    }

    public int getTotal() {
        return items.stream()
                .mapToInt(OrderDetail::getQuantity)
                .sum();
    }
    public void clearCart() {
        items.clear(); // Xóa toàn bộ mục hàng trong giỏ hàng
    }
    public int getAmount() {
        return items.stream()
                .mapToInt(item -> item.getQuantity() * item.getProduct().getPrice())
                .sum();
    }
    public class Cart {
        private List<OrderDetail> items;

        public Cart() {
            items = new ArrayList<>();
        }

        public List<OrderDetail> getItems() {
            return items;
        }

        // Phương thức thêm mục vào giỏ hàng
        public void addItem(OrderDetail item) {
            items.add(item);
        }

        // Phương thức xóa tất cả các mục khỏi giỏ hàng
        public void clearItems() {
            items.clear();
        }
    }

}
