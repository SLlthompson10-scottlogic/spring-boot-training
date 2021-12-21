package com.example.springboot;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;


class NewOrder {



    private final String account;
    private final int price;
    private int quantity;
    private final String action;

    public NewOrder(String account, int price, int quantity, String action) {
        this.account = account;
        this.price = price;
        this.quantity = quantity;
        this.action = action;
    }

    public String getAccount() {
        return account;
    }


    public int getPrice() {
        return price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAction() {
        return action;
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
