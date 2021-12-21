package com.example.springboot;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


class NewOrder {

    @NotNull(message = "Please select an account.")
    private final String account;
    @Min(value = 1, message = "Please increase the price value to 1 or greater.")
    private final int price;
    @Min(value = 1, message = "Please increase the quantity value to 1 or greater.")
    private int quantity;
    @NotNull(message = "Please select either 'Buy' or 'Sell'.")
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
