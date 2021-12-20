package com.example.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JavaTrainingApplicationTests {

//	@Test
//	void contextLoads() {
//	}

    JavaTrainingApp matcher = new JavaTrainingApp();

    NewOrder Order1 = new NewOrder("account1", 10, 5, "Buy");
//    NewOrder Order2 = new NewOrder("account2", 8, 10, "Buy");
//    NewOrder Order3 = new NewOrder("account3", 9, 15, "Sell");
//    NewOrder Order4 = new NewOrder("account4", 10, 5, "Buy");
//    NewOrder Order5 = new NewOrder("account5", 10, 8, "Sell");
//    NewOrder Order6 = new NewOrder("account6", 7, 5, "Sell");


    @Test//Checks correctly added to buyList.
    void AddToBuyList() {

        System.out.println("here");
        matcher.newOrder(Order1);

        Assertions.assertTrue(matcher.buyList.contains(Order1));


    }



}
