package com.example.springboot;

import com.fasterxml.jackson.annotation.JsonCreator;

class NewOrder {

    String account;
    String action;

    int price;
    int quantity;

    @JsonCreator
    public NewOrder(String account, int price, int quantity, String action) {
        this.account = account;
        this.price = price;
        this.quantity = quantity;
        this.action = action;
    }

    @Override
    public String toString() {
        return
                "account='" + account + '\'' +
                ", action='" + action + '\'' +
                ", price=" + price +
                ", quantity=" + quantity;
    }
}
