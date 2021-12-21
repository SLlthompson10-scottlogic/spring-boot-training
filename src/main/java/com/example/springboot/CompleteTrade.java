package com.example.springboot;

public class CompleteTrade {
    String accountFrom = "";
    String accountTo = "";
    int price = 0;
    int quantity = 0;

    public CompleteTrade(String accountFrom, String accountTo, int price, int quantity) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.price = price;
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
