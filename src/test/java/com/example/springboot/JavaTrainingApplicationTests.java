//package com.example.springboot;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//
//class JavaTrainingApplicationTests {
//
////	@Test
////	void contextLoads() {
////	}
//
//    JavaTrainingApp matcher = new JavaTrainingApp();
//
//    NewOrder Order1 = new NewOrder("account1", 12, 10, "Buy");
//    NewOrder Order2 = new NewOrder("account2", 9, 15, "Buy");
//    NewOrder Order3 = new NewOrder("account3", 11, 12, "Buy");
//    NewOrder Order4 = new NewOrder("account4", 10, 15, "Buy");
//    NewOrder Order5 = new NewOrder("account5", 10, 10, "Buy");
//    NewOrder Order6 = new NewOrder("account6", 5, 11, "Buy");
//
//    NewOrder Order7 = new NewOrder("account7", 9, 20, "Sell");
//    NewOrder Order8 = new NewOrder("account8", 13, 5, "Sell");
//    NewOrder Order9 = new NewOrder("account9", 7, 5, "Sell");
//    NewOrder Order10 = new NewOrder("account10", 9, 12, "Sell");
//    NewOrder Order11 = new NewOrder("account11", 10, 20, "Sell");
//
////    @BeforeEach
////    void initialise(){
////
////    }
////
//
//    @Test//Checks correctly added to buyList/sellList.
//    void AddToLists() {
//        matcher.newOrder(Order1);
//        matcher.newOrder(Order8);
////        System.out.println(Order1);
////        System.out.println(matcher.buyList);
////        System.out.println(matcher.sellList);
////        Assertions.assertTrue(matcher.buyList.contains(Order1));
////        Assertions.assertTrue(matcher.sellList.contains(Order8));
//    }
//
//    @Test//Checks buyList is ordered correctly.
//    void OrderBuyLists() {
//        matcher.newOrder(Order1);
//        matcher.newOrder(Order2);
//        matcher.newOrder(Order3);
//        matcher.newOrder(Order4);
//        matcher.newOrder(Order5);
//        matcher.newOrder(Order6);
//        Assertions.assertTrue(matcher.buyList.get(0).getAccount().equals("account1"));
//        Assertions.assertTrue(matcher.buyList.get(1).getAccount().equals("account3"));
//        Assertions.assertTrue(matcher.buyList.get(2).getAccount().equals("account4"));
//        Assertions.assertTrue(matcher.buyList.get(3).getAccount().equals("account5"));
//        Assertions.assertTrue(matcher.buyList.get(4).getAccount().equals("account2"));
//        Assertions.assertTrue(matcher.buyList.get(5).getAccount().equals("account6"));
//    }
//
//    @Test//Checks sellList ordered correctly.
//    void OrderSellLists() {
//        matcher.newOrder(Order7);
//        matcher.newOrder(Order8);
//        matcher.newOrder(Order9);
//        matcher.newOrder(Order10);
//        matcher.newOrder(Order11);
//        Assertions.assertTrue(matcher.sellList.get(0).getAccount().equals("account9"));
//        Assertions.assertTrue(matcher.sellList.get(1).getAccount().equals("account7"));
//        Assertions.assertTrue(matcher.sellList.get(2).getAccount().equals("account10"));
//        Assertions.assertTrue(matcher.sellList.get(3).getAccount().equals("account11"));
//        Assertions.assertTrue(matcher.sellList.get(4).getAccount().equals("account8"));
//    }
//
//    @Test//Checks agBuyList is correct.
//    void AgBuyLists() {
//
//        matcher.newOrder(Order2);
//        matcher.newOrder(Order1);
//        matcher.newOrder(Order3);
//        matcher.newOrder(Order4);
//        matcher.newOrder(Order6);
//        matcher.newOrder(Order5);
//
//
//        Assertions.assertTrue(matcher.agBuyList.get(0)[0]==5);
//        Assertions.assertTrue(matcher.agBuyList.get(1)[0]==9);
//        Assertions.assertTrue(matcher.agBuyList.get(2)[0]==10);
//        Assertions.assertTrue(matcher.agBuyList.get(3)[0]==11);
//        Assertions.assertTrue(matcher.agBuyList.get(4)[0]==12);
//        Assertions.assertTrue(matcher.agBuyList.size()==5);
//
//        Assertions.assertTrue(matcher.agBuyList.get(0)[1]==11);
//        Assertions.assertTrue(matcher.agBuyList.get(1)[1]==26);
//        Assertions.assertTrue(matcher.agBuyList.get(2)[1]==51);
//        Assertions.assertTrue(matcher.agBuyList.get(3)[1]==63);
//        Assertions.assertTrue(matcher.agBuyList.get(4)[1]==73);
//
//    }
//
//    @Test//Checks agSellList is correct.
//    void AgSellLists() {
//        matcher.newOrder(Order7);
//        matcher.newOrder(Order8);
//        matcher.newOrder(Order9);
//        matcher.newOrder(Order10);
//        matcher.newOrder(Order11);
//
//        Assertions.assertTrue(matcher.agSellList.get(0)[0]==13);
//        Assertions.assertTrue(matcher.agSellList.get(1)[0]==10);
//        Assertions.assertTrue(matcher.agSellList.get(2)[0]==9);
//        Assertions.assertTrue(matcher.agSellList.get(3)[0]==7);
//        Assertions.assertTrue(matcher.agSellList.size()==4);
//
//        Assertions.assertTrue(matcher.agSellList.get(0)[1]==5);
//        Assertions.assertTrue(matcher.agSellList.get(1)[1]==25);
//        Assertions.assertTrue(matcher.agSellList.get(2)[1]==57);
//        Assertions.assertTrue(matcher.agSellList.get(3)[1]==62);
//
//    }
//
//    @Test//Checks buyList correctly updated when matched up
//    void updateBuyList() {
//        matcher.newOrder(Order2);
//        matcher.newOrder(Order1);
//        matcher.newOrder(Order11);
//        matcher.newOrder(Order3);
//        matcher.newOrder(Order10);
//        matcher.newOrder(Order4);
//        matcher.newOrder(Order6);
//        matcher.newOrder(Order5);
//        matcher.newOrder(Order8);
//
//        Assertions.assertTrue(matcher.buyList.size()==4);
//        Assertions.assertTrue(matcher.sellList.size()==1);
//
//        Assertions.assertTrue(matcher.buyList.get(0).getAccount().equals("account4"));
//        Assertions.assertTrue(matcher.buyList.get(1).getAccount().equals("account5"));
//        Assertions.assertTrue(matcher.buyList.get(2).getAccount().equals("account2"));
//        Assertions.assertTrue(matcher.buyList.get(3).getAccount().equals("account6"));
//        Assertions.assertTrue(matcher.sellList.get(0).getAccount().equals("account8"));
//
//    }
//
//    @Test//Checks sellList correctly updated when matched up
//    void updateSellList() {
//        matcher.newOrder(Order7);
//        matcher.newOrder(Order8);
//        matcher.newOrder(Order2);
//        matcher.newOrder(Order9);
//        matcher.newOrder(Order3);
//        matcher.newOrder(Order10);
//        matcher.newOrder(Order11);
//        matcher.newOrder(Order6);
//
//    }
//
//    @Test//Checks agBuyList correctly updated when matched up
//    void updateAgBuyList() {
//        matcher.newOrder(Order2);
//        matcher.newOrder(Order1);
//        matcher.newOrder(Order11);
//        matcher.newOrder(Order3);
//        matcher.newOrder(Order10);
//        matcher.newOrder(Order4);
//        matcher.newOrder(Order6);
//        matcher.newOrder(Order5);
//        matcher.newOrder(Order8);
//
//
//        Assertions.assertTrue(matcher.agBuyList.size()==3);
//        Assertions.assertTrue(matcher.agSellList.size()==1);
//
//        Assertions.assertTrue(matcher.agBuyList.get(0)[0]==5);
//        Assertions.assertTrue(matcher.agBuyList.get(0)[1]==11);
//        Assertions.assertTrue(matcher.agBuyList.get(1)[0]==9);
//        Assertions.assertTrue(matcher.agBuyList.get(1)[1]==16);
//        Assertions.assertTrue(matcher.agBuyList.get(2)[0]==10);
//        Assertions.assertTrue(matcher.agBuyList.get(2)[1]==41);
//        Assertions.assertTrue(matcher.agSellList.get(0)[0]==13);
//        Assertions.assertTrue(matcher.agSellList.get(0)[1]==5);
//
//
//
//    }
//
//    @Test//Checks agSellList and everything.
//    void updateAgSellList() {
//        matcher.newOrder(Order7);
//        matcher.newOrder(Order8);
//        matcher.newOrder(Order2);
//        matcher.newOrder(Order9);
//        matcher.newOrder(Order3);
//        matcher.newOrder(Order10);
//        matcher.newOrder(Order11);
//        matcher.newOrder(Order6);
//
//
//
//        Assertions.assertTrue(matcher.buyList.size()==1);
//        Assertions.assertTrue(matcher.sellList.size()==3);
//        Assertions.assertTrue(matcher.agBuyList.size()==1);
//        Assertions.assertTrue(matcher.agSellList.size()==3);
//        Assertions.assertTrue(matcher.completedTrades.size()==4);
//
//        Assertions.assertTrue(matcher.buyList.get(0).getAccount().equals("account6"));
//        Assertions.assertTrue(matcher.buyList.get(0).getQuantity()==11);
//
//        Assertions.assertTrue(matcher.sellList.get(0).getAccount().equals("account10"));
//        Assertions.assertTrue(matcher.sellList.get(0).getQuantity()==10);
//        Assertions.assertTrue(matcher.sellList.get(1).getAccount().equals("account11"));
//        Assertions.assertTrue(matcher.sellList.get(1).getQuantity()==20);
//        Assertions.assertTrue(matcher.sellList.get(2).getAccount().equals("account8"));
//        Assertions.assertTrue(matcher.sellList.get(2).getQuantity()==5);
//
//        Assertions.assertTrue(matcher.agSellList.get(0)[0]==13);
//        Assertions.assertTrue(matcher.agSellList.get(1)[0]==10);
//        Assertions.assertTrue(matcher.agSellList.get(2)[0]==9);
//
//        Assertions.assertTrue(matcher.agSellList.get(0)[1]==5);
//        Assertions.assertTrue(matcher.agSellList.get(1)[1]==25);
//        Assertions.assertTrue(matcher.agSellList.get(2)[1]==35);
//
//
//
//
//    }
//
//
//}
