package com.example.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class JavaTrainingApp {

    static ArrayList<NewOrder> buyList = new ArrayList<>();
    static ArrayList<NewOrder> sellList = new ArrayList<>();
    static ArrayList<CompleteTrade> completedTrades = new ArrayList<>();
    static ArrayList<int[]> agBuyList = new ArrayList<>();
    static ArrayList<int[]> agSellList = new ArrayList<>();


    public static void newOrder(NewOrder order) {
//        System.out.println("NEW ORDER");
        if (order.getAction().equals("Buy")) {
            if (sellList.size() == 0) {
                orderUnmatchedBuyers(order);
            } else if (order.getPrice() < sellList.get(0).getPrice()) {
                orderUnmatchedBuyers(order);
            } else {
                updateBuyTrade(order);
            }
        } else {
            if (buyList.size() == 0) {
                orderUnmatchedSellers(order);
            } else if (order.getPrice() > buyList.get(0).getPrice()) {
                orderUnmatchedSellers(order);
            } else {
                updateSellTrade(order);
            }
        }

    }

    public static void updateBuyTrade(NewOrder order) {
//        System.out.println("UPDATE BUY TRADE");

        if (order.getQuantity() < sellList.get(0).getQuantity()) {
            CompleteTrade completeTrade = new CompleteTrade(sellList.get(0).getAccount(), order.getAccount(), sellList.get(0).getPrice(), order.getQuantity());
            aggregateListDecreaseEditor(agSellList, completeTrade.getQuantity());
            int newQuantity = sellList.get(0).getQuantity() - order.getQuantity();
            NewOrder updatedSellListOrder = new NewOrder(sellList.get(0).getAccount(), sellList.get(0).getPrice(), newQuantity, sellList.get(0).getAction());
            sellList.set(0, updatedSellListOrder);
            completedTrades.add(completeTrade);
        } else if (order.getQuantity() > sellList.get(0).getQuantity()) {
            CompleteTrade completeTrade = new CompleteTrade(sellList.get(0).getAccount(), order.getAccount(), sellList.get(0).getPrice(), sellList.get(0).getQuantity());
            aggregateListDecreaseEditor(agSellList, completeTrade.getQuantity());
            int newQuantity = order.getQuantity() - sellList.get(0).getQuantity();
            sellList.remove(0);
            completedTrades.add(completeTrade);
            NewOrder nextIteration = new NewOrder(order.getAccount(), order.getPrice(), newQuantity, order.getAction());
            newOrder(nextIteration);
        } else {
            CompleteTrade completeTrade = new CompleteTrade(sellList.get(0).getAccount(), order.getAccount(), sellList.get(0).getPrice(), order.getQuantity());
            aggregateListDecreaseEditor(agSellList, completeTrade.getQuantity());
            sellList.remove(0);
            completedTrades.add(completeTrade);
        }

    }

    public static void updateSellTrade(NewOrder order) {
//        System.out.println("UPDATE SELL TRADE");

        if (order.getQuantity() < buyList.get(0).getQuantity()) {
            CompleteTrade completeTrade = new CompleteTrade(order.getAccount(), buyList.get(0).getAccount(), order.getPrice(), order.getQuantity());
            aggregateListDecreaseEditor(agBuyList, completeTrade.getQuantity());
            int newQuantity = buyList.get(0).getQuantity() - order.getQuantity();
            NewOrder updatedBuyListOrder = new NewOrder(buyList.get(0).getAccount(), buyList.get(0).getPrice(), newQuantity, buyList.get(0).getAction());
            buyList.set(0, updatedBuyListOrder);
            completedTrades.add(completeTrade);
        } else if (order.getQuantity() > buyList.get(0).getQuantity()) {
            CompleteTrade completeTrade = new CompleteTrade(order.getAccount(), buyList.get(0).getAccount(), order.getPrice(), buyList.get(0).getQuantity());
            aggregateListDecreaseEditor(agBuyList, completeTrade.getQuantity());
            int newQuantity = order.getQuantity() - buyList.get(0).getQuantity();
            buyList.remove(0);
            completedTrades.add(completeTrade);
            NewOrder nextIteration = new NewOrder(order.getAccount(), order.getPrice(), newQuantity, order.getAction());
            newOrder(nextIteration);

        } else {
            CompleteTrade completeTrade = new CompleteTrade(order.getAccount(), buyList.get(0).getAccount(), order.getPrice(), order.getQuantity());
            aggregateListDecreaseEditor(agBuyList, completeTrade.getQuantity());
            buyList.remove(0);
            completedTrades.add(completeTrade);
        }

    }

    public static void aggregateBuyListIncreaseEditor(int position, int quantityIncrease) {
//        System.out.println("AGGREGATE BUY LIST INCREASE EDITOR");

        int i = position + 1;
        while (i < agBuyList.size()) {
            int newQuantity = agBuyList.get(i)[1] + quantityIncrease;
            int[] updatedAgPriceQuantity = {agBuyList.get(i)[0], newQuantity};
            agBuyList.set(i, updatedAgPriceQuantity);
            i++;
        }

    }

    public static void aggregateSellListIncreaseEditor(int position, int quantityIncrease) {
//        System.out.println("AGGREGATE SELL LIST INCREASE EDITOR");
//        System.out.println(agSellList);

        int i = position + 1;
        while (i < agSellList.size()) {
            int newQuantity = agSellList.get(i)[1] + quantityIncrease;
            int[] updatedAgPriceQuantity = {agSellList.get(i)[0], newQuantity};
            agSellList.set(i, updatedAgPriceQuantity);
            i++;
        }
        System.out.println(agSellList);

    }

    public static void aggregateListDecreaseEditor(ArrayList<int[]> listToBeEdited, int quantity) {
//        if (listToBeEdited == agSellList) {
//
//        System.out.println("AGGREGATE LIST DECREASE EDITOR");
//        System.out.println("BEFORE");
//
//        for (int[] arr : agSellList) {
//            System.out.println(Arrays.toString(arr));}
//        System.out.println(quantity);}


        int newQuantity = listToBeEdited.get(listToBeEdited.size() - 1)[1] - quantity;
        int[] updatedAgPriceQuantity = {listToBeEdited.get(listToBeEdited.size() - 1)[0], newQuantity};
        listToBeEdited.set(listToBeEdited.size() - 1, updatedAgPriceQuantity);

        if (listToBeEdited.size() == 1) {
            if (newQuantity == 0) {
                listToBeEdited.remove(0);
            }
        } else {
            if (newQuantity == listToBeEdited.get(listToBeEdited.size() - 2)[1]) {
                listToBeEdited.remove(listToBeEdited.size() - 1);
            }
        }
//        if (listToBeEdited == agSellList) {
//            System.out.println("UPDATE");
//            System.out.println(agSellList.size());
//            for (int[] arr : agSellList) {
//                System.out.println(Arrays.toString(arr));
//            }
//        }
    }

    public static void orderUnmatchedBuyers(NewOrder order) {
//        System.out.println("ORDER UNMATCHED BUYERS");

        int[] priceOrder = {order.getPrice(), order.getQuantity()};

        if (buyList.size() == 0) {
            buyList.add(order);
            agBuyList.add(priceOrder);
        } else if (buyList.get(buyList.size() - 1).getPrice() >= order.getPrice()) {
            buyList.add(order);
            if (agBuyList.get(0)[0] > order.getPrice()) {
                agBuyList.add(0, priceOrder);
            } else {
                int newQuantity = agBuyList.get(0)[1] + order.getQuantity();
                int[] updatedAgPriceQuantity = {agBuyList.get(0)[0], newQuantity};
                agBuyList.set(0, updatedAgPriceQuantity);
            }
            if (agBuyList.size() > 1) {
                aggregateBuyListIncreaseEditor(0, order.getQuantity());
            }
        } else if (buyList.get(0).getPrice() < order.getPrice()) {
            buyList.add(0, order);
            int newQuantity = order.getQuantity() + agBuyList.get(agBuyList.size() - 1)[1];
            int[] updatedAgPriceQuantity = {order.getPrice(), newQuantity};
            agBuyList.add(updatedAgPriceQuantity);
        } else {
            int i = 0;
            while (buyList.get(i).getPrice() >= order.getPrice()) {
                i++;
            }
            buyList.add(i, order);


            int j = 0;
            int currentAggregate = 0;
            while (agBuyList.get(j)[0] < order.getPrice()) {
                currentAggregate += agBuyList.get(j)[1];
                j++;
            }

            if (agBuyList.get(j)[0] == order.getPrice()) {
                int newQuantity = order.getQuantity() + agBuyList.get(j)[1];
                int[] updateAgPriceQuantity = {order.getPrice(), newQuantity};
                agBuyList.set(j, updateAgPriceQuantity);
            } else {
                int newQuantity = order.getQuantity() + currentAggregate;
                int[] newAgPriceQuantity = {order.getPrice(), newQuantity};
                agBuyList.add(j, newAgPriceQuantity);
            }

            aggregateBuyListIncreaseEditor(j, order.getQuantity());

        }

    }

    public static void orderUnmatchedSellers(NewOrder order) {
//        System.out.println("ORDER UNMATCHED SELLERS");
//        for (int[] arr : agSellList) {
//            System.out.println(Arrays.toString(arr));
//        }

        int[] priceOrder = {order.getPrice(), order.getQuantity()};

        if (sellList.size() == 0) {
            sellList.add(order);
            agSellList.add(priceOrder);
        } else if (sellList.get(sellList.size() - 1).getPrice() <= order.getPrice()) {
            sellList.add(order);
            if (agSellList.get(0)[0] < order.getPrice()) {
                agSellList.add(0, priceOrder);
            } else {
                int newQuantity = order.getQuantity() + agSellList.get(0)[1];
                int[] updatedAgPriceQuantity = {order.getPrice(), newQuantity};
                agSellList.set(0, updatedAgPriceQuantity);
            }

            if (agSellList.size() > 1) {
                aggregateSellListIncreaseEditor(0, order.getQuantity());
            }
        } else if (sellList.get(0).getPrice() > order.getPrice()) {

            sellList.add(0, order);
            int newQuantity = order.getQuantity() + agSellList.get(agSellList.size() - 1)[1];
            int[] updatedAgPriceQuantity = {order.getPrice(), newQuantity};
            agSellList.add(updatedAgPriceQuantity);
        } else {


            int i = 0;
            while (sellList.get(i).getPrice() <= order.getPrice()) {
                i++;
            }
            sellList.add(i, order);

            int j = 0;
            int currentAggregate = 0;
            while (agSellList.get(j)[0] > order.getPrice()) {
                currentAggregate += agSellList.get(j)[1];
                j++;
            }


            if (agSellList.get(j)[0] == order.getPrice()) {
                int newQuantity = agSellList.get(j)[1] + order.getQuantity();
                int[] updatedAgPriceQuantity = {order.getPrice(), newQuantity};
                agSellList.set(j, updatedAgPriceQuantity);

            } else {
                int newQuantity = order.getQuantity() + currentAggregate;
                int[] updatedAgPriceQuantity = {order.getPrice(), newQuantity};
                agSellList.add(j, updatedAgPriceQuantity);
            }


            aggregateSellListIncreaseEditor(j, order.getQuantity());
        }

    }

//	public static void addToAggregate(int price, int quantity) {
//		System.out.println("ADD TO AGGREGATE");
//
//	}
//
//	public static void orderBooks() {
//		System.out.println("ORDER BOOKS");
//
//	}

//	public String agBooksToStrings(ArrayList<int[]> listToString) {
//		int i=0;
//		while (i<listToString.size()) {
//			String newString = listToString.get(i).toString();
//
//		}
//	}


    public static void main(String[] args) {
        new SpringApplicationBuilder(JavaTrainingApp.class).run(args);
//
//        NewOrder Order1 = new NewOrder("account1", 10, 5, "Buy");
//        NewOrder Order2 = new NewOrder("account2", 8, 10, "Buy");
//        NewOrder Order3 = new NewOrder("account3", 9, 15, "Buy");
//        NewOrder Order4 = new NewOrder("account4", 10, 5, "Buy");
//        NewOrder Order5 = new NewOrder("account5", 15, 8, "Sell");
//        NewOrder Order6 = new NewOrder("account6", 7, 5, "Buy");
//        newOrder(Order4);
//        newOrder(Order5);

//        newOrder(Order1);
//        newOrder(Order2);
//        newOrder(Order3);
//        newOrder(Order6);
//        System.out.println(buyList);
//        System.out.println(buyList);
//        System.out.println(sellList);
//        System.out.println(completedTrades);
//        System.out.println("AGBUYLIST");
//        for (int[] element : agBuyList) {
//            for (int aggs : element) {
//                System.out.print(aggs + "-");
//
//            }
//            System.out.println("");
//        }
//        System.out.println("AGSELLLIST");
//        for (int[] element : agSellList) {
//            for (int aggs : element) {
//                System.out.print(aggs + "-");
//            }
//            System.out.println("");
//        }
//		System.out.println(firstOrder);
//		CompleteTrade firstTrade = new CompleteTrade("account1","account2", 15, 12);
//		System.out.println(firstTrade);


    }


}
