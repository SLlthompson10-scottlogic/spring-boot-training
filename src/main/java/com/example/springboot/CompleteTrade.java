package com.example.springboot;

import lombok.AllArgsConstructor;
import lombok.Data;


public class CompleteTrade {
    private String accountFrom = "";
    private String accountTo = "";
    private int price = 0;
    private int quantity = 0;


    public CompleteTrade(String accountFrom, String accountTo, int price, int quantity) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.price = price;
        this.quantity = quantity;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CompleteTrade{" +
                "accountFrom='" + accountFrom + '\'' +
                ", accountTo='" + accountTo + '\'' +
                ", price=" + price +
                ", quantity=" + quantity;
    }
}
