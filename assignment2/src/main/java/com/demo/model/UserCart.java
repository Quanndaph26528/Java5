package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_cart")
public class UserCart {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "cart_items", length = 10000)
    private String cartItems;
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setCartItems(String cartItems) {
        this.cartItems = cartItems;
    }



}
