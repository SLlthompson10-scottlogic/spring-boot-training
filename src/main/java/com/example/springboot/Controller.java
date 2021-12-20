package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
class Controller {

    JavaTrainingApp matcher = new JavaTrainingApp();

    @GetMapping("/buyList")
    public ArrayList<NewOrder> buyList() {
        return matcher.buyList;
    }

    @GetMapping("/sellList")
    public ArrayList<NewOrder> sellList() {
        return matcher.sellList;
    }

    @GetMapping("/agBuyList")
    public ArrayList<int[]> agBuyList() {
        return matcher.agBuyList;
    }

    @GetMapping("/agSellList")
    public ArrayList<int[]> agSellList() {
        return matcher.agSellList;
    }

    @GetMapping("/completedTrades")
    public ArrayList<CompleteTrade> completedTrades() {
        return matcher.completedTrades;
    }

    @PostMapping("/newOrder")
    public HashMap<String,Object> newOrder(@RequestBody NewOrder order) {

        matcher.newOrder(order);

        HashMap<String,Object> updateLists = new HashMap<String,Object>();
        updateLists.put("completedTrades",matcher.completedTrades);
        updateLists.put("buyList",matcher.buyList);
        updateLists.put("sellList",matcher.sellList);
        updateLists.put("agBuyList",matcher.agBuyList);
        updateLists.put("agSellList",matcher.agBuyList);

        return (updateLists);
    }
}
