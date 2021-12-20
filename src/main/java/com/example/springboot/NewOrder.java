package com.example.springboot;

class NewOrder extends JavaTrainingApp {

    String account;
    String action;

    int price;
    int quantity;


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
                ", quantity=" + quantity +
                '}';
    }
}
