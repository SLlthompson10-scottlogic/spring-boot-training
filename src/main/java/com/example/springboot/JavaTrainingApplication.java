package com.example.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.ArrayList;

@SpringBootApplication
public class JavaTrainingApplication {

	static ArrayList<NewOrder> buyList = new ArrayList<>();
	static ArrayList<NewOrder> sellList = new ArrayList<>();
	static ArrayList<CompleteTrade> completedTrades = new ArrayList<>();
	static ArrayList<int[]> agBuyList = new ArrayList<>();
	static ArrayList<int[]> agSellList = new ArrayList<>();

	public static void newOrder(NewOrder order) {
		System.out.println("NEW ORDER");
		if (order.action.equals("Buy")) {
			if (sellList.size()==0) {
				orderUnmatchedBuyers(order);
			}
			else if (order.price < sellList.get(0).price) {
				orderUnmatchedBuyers(order);
			}
			else {
				updateBuyTrade(order);
			}
		}
		else {
			if (buyList.size()==0) {
				orderUnmatchedSellers(order);
			}
			else if (order.price > buyList.get(0).price) {
				orderUnmatchedSellers(order);
			}
			else {
				updateSellTrade(order);
			}
		}

	}

	public static void updateBuyTrade(NewOrder order) {
		System.out.println("UPDATE BUY TRADE");

		if (order.quantity < sellList.get(0).quantity) {
			CompleteTrade completeTrade = new CompleteTrade(sellList.get(0).account,order.account, sellList.get(0).price, order.quantity);
			aggregateListDecreaseEditor(agSellList, completeTrade.quantity);
			int newQuantity = sellList.get(0).quantity - order.quantity;
			NewOrder updatedSellListOrder = new NewOrder(sellList.get(0).account,sellList.get(0).price,newQuantity, sellList.get(0).action);
			sellList.set(0,updatedSellListOrder);
			completedTrades.add(completeTrade);
		}

		else if (order.quantity > sellList.get(0).quantity) {
			CompleteTrade completeTrade = new CompleteTrade(sellList.get(0).account, order.account, sellList.get(0).price, sellList.get(0).quantity);
			aggregateListDecreaseEditor(agSellList,completeTrade.quantity);
			int newQuantity = order.quantity - sellList.get(0).quantity;
			sellList.remove(0);
			completedTrades.add(completeTrade);
			NewOrder nextIteration = new NewOrder(order.account,order.price,newQuantity,order.action);
			newOrder(nextIteration);
		}

		else {
			CompleteTrade completeTrade = new CompleteTrade(sellList.get(0).account,order.account, sellList.get(0).price, order.quantity);
			aggregateListDecreaseEditor(agSellList, completeTrade.quantity);
			sellList.remove(0);
			completedTrades.add(completeTrade);
		}

	}

	public static void updateSellTrade(NewOrder order) {
		System.out.println("UPDATE SELL TRADE");

		if (order.quantity < buyList.get(0).quantity) {
			CompleteTrade completeTrade =  new CompleteTrade(order.account,buyList.get(0).account,order.price,order.quantity);
			aggregateListDecreaseEditor(agBuyList, completeTrade.quantity);
			int newQuantity = buyList.get(0).quantity-order.quantity;
			NewOrder updatedBuyListOrder = new NewOrder(buyList.get(0).account, buyList.get(0).price,newQuantity,buyList.get(0).action);
			buyList.set(0,updatedBuyListOrder);
			completedTrades.add(completeTrade);
		}

		else if (order.quantity > buyList.get(0).quantity) {
			CompleteTrade completeTrade =  new CompleteTrade(order.account,buyList.get(0).account,order.price,buyList.get(0).quantity);
			aggregateListDecreaseEditor(agBuyList, completeTrade.quantity);
			int newQuantity = order.quantity - buyList.get(0).quantity;
			buyList.remove(0);
			completedTrades.add(completeTrade);
			NewOrder nextIteration = new NewOrder(order.account, order.price, newQuantity, order.action);
			newOrder(nextIteration);

		}

		else {
			CompleteTrade completeTrade =  new CompleteTrade(order.account,buyList.get(0).account,order.price,order.quantity);
			aggregateListDecreaseEditor(agBuyList, completeTrade.quantity);
			buyList.remove(0);
			completedTrades.add(completeTrade);
		}

	}

	public static void aggregateListIncreaseEditor(ArrayList<int[]> listToBeEdited, int position, int quantityIncrease) {
		System.out.println("AGGREGATE LIST INCREASE EDITOR");

		int i = position + 1;
		while (i < listToBeEdited.size()) {
			int newQuantity = listToBeEdited.get(i)[1] + quantityIncrease;
			int[] updatedAgPriceQuantity = {listToBeEdited.get(i)[0],newQuantity};
			listToBeEdited.set(i,updatedAgPriceQuantity);
			i++;
		}
	}

	public static void aggregateListDecreaseEditor(ArrayList<int[]> listToBeEdited, int quantity) {
		System.out.println("AGGREGATE LIST DECREASE EDITOR");

		int newQuantity =listToBeEdited.get(listToBeEdited.size()-1)[1] - quantity;

		if (listToBeEdited.size() == 1) {
			if (newQuantity == 0) {
				listToBeEdited.remove(0);
			}
		}
		else {
			if (newQuantity == listToBeEdited.get(listToBeEdited.size()-2)[1]) {
				listToBeEdited.remove(listToBeEdited.size()-1);
			}
		}

	}

	public static void orderUnmatchedBuyers(NewOrder order) {
		System.out.println("ORDER UNMATCHED BUYERS");

		int[] priceOrder = {order.price,order.quantity};

		if (buyList.size() == 0) {
			buyList.add(order);
			agBuyList.add(priceOrder);
		}

		else if (buyList.get(buyList.size()-1).price >= order.price) {
			buyList.add(order);
			if (agBuyList.get(0)[0] > order.price) {
				agBuyList.add(0,priceOrder);
			}
			else {
				int newQuantity = agBuyList.get(0)[1] + order.quantity;
				int[] updatedAgPriceQuantity = {agBuyList.get(0)[0],newQuantity};
				agBuyList.set(0, updatedAgPriceQuantity);
			}
			if (agBuyList.size() > 1) {
				aggregateListIncreaseEditor(agBuyList,0,order.quantity);
			}
		}

		else if (buyList.get(0).price < order.price){
			buyList.add(0,order);
			int newQuantity = order.quantity + agBuyList.get(agBuyList.size()-1)[1];
			int[] updatedAgPriceQuantity = {order.price, newQuantity};
			agBuyList.add(updatedAgPriceQuantity);
		}

		else {
			int i=0;
			while (buyList.get(i).price >= order.price) {
				i++;
			}
			buyList.add(i,order);


			int j=0;
			int currentAggregate=0;
			while(agBuyList.get(j)[0]<order.price) {
				currentAggregate += agBuyList.get(j)[1];
				j++;
			}

			if (agBuyList.get(j)[0] == order.price) {
				int newQuantity = order.quantity + agBuyList.get(j)[1];
				int[] updateAgPriceQuantity = {order.price, newQuantity};
				agBuyList.set(j,updateAgPriceQuantity);
			}

			else {
				int newQuantity = order.quantity + currentAggregate;
				int[] newAgPriceQuantity = {order.price, newQuantity};
				agBuyList.add(j, newAgPriceQuantity);
			}

			aggregateListIncreaseEditor(agBuyList,j,order.quantity);

		}

	}

	public static void orderUnmatchedSellers(NewOrder order) {
		System.out.println("ORDER UNMATCHED SELLERS");

		int[] priceOrder = {order.price,order.quantity};

		if (sellList.size() == 0) {
			sellList.add(order);
			agSellList.add(priceOrder);
		}

		else if (sellList.get(sellList.size()-1).price <= order.price) {
			sellList.add(order);
			if (agSellList.get(0)[0] < order.price) {
				agSellList.add(0,priceOrder);
			}
			else {
				int newQuantity = order.quantity + agSellList.get(0)[1];
				int [] updatedAgPriceQuantity = {order.price, newQuantity};
				agSellList.set(0,updatedAgPriceQuantity);
			}
			if (agSellList.size() > 1) {
				aggregateListIncreaseEditor(agSellList,0,order.quantity);
			}
		}

		else if (sellList.get(0).price > order.price) {
			sellList.add(0,order);
			int newQuantity = order.quantity + agSellList.get(agSellList.size()-1)[1];
			int[] updatedAgPriceQuantity = {order.price,newQuantity};
			agSellList.add(updatedAgPriceQuantity);
		}

		else {
			int i=0;
			while(sellList.get(i).price <= order.price) {
				i++;
			}
			sellList.add(i,order);

			int j=0;
			int currentAggregate = 0;
			while(agSellList.get(j)[0] > order.price) {
				currentAggregate += agSellList.get(j)[1];
				j++;
			}

			if (agSellList.get(j)[0] == order.price) {
				int newQuantity = agSellList.get(j)[1] + order.quantity;
				int[] updatedAgPriceQuantity = {order.price, newQuantity};
				agSellList.set(j,updatedAgPriceQuantity);
			}

			else{
				int newQuantity = order.quantity + currentAggregate;
				int[] updatedAgPriceQuantity = {order.price, newQuantity};
				agSellList.set(j,updatedAgPriceQuantity);
			}

			aggregateListIncreaseEditor(agSellList,j,order.quantity);
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
		new SpringApplicationBuilder(JavaTrainingApplication.class).run(args);
		System.out.println("HELLO");

		NewOrder Order1 = new NewOrder("account1",10,5,"Buy");
		NewOrder Order2 = new NewOrder("account2",8,10,"Buy");
		NewOrder Order3 = new NewOrder("account3",9,15,"Sell");
		NewOrder Order4 = new NewOrder("account4",10,5,"Buy");
		NewOrder Order5 = new NewOrder("account5",10,8,"Sell");
		NewOrder Order6 = new NewOrder("account6",7,5,"Sell");
		newOrder(Order1);
		newOrder(Order2);
		newOrder(Order3);
		newOrder(Order4);
		newOrder(Order5);
		newOrder(Order6);
		System.out.println(buyList);
		System.out.println(sellList);
		System.out.println(completedTrades);
		for (int[] element: agBuyList) {
			for(int aggs : element)   {
				System.out.print(aggs + "-");

			}
			System.out.println("");
		}
		System.out.println("AGSELLLIST");
		for (int[] element: agSellList) {
			for(int aggs : element)  { System.out.print(aggs + "-"); }
			System.out.println("");
		}
//		System.out.println(firstOrder);
//		CompleteTrade firstTrade = new CompleteTrade("account1","account2", 15, 12);
//		System.out.println(firstTrade);


	}



}




