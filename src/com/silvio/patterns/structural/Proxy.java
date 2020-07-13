package com.silvio.patterns.structural;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//design the subject interface
abstract interface IOrder {
    public abstract void fulfillOrder(Order order);
}

//implement the proxy class
class Warehouse implements IOrder {
    private String address;
    private Stock stock;

    public Warehouse(String address, Stock stock){
        this.address = address;
        this.stock = stock;
    }

    @Override
    public void fulfillOrder(Order order) {

        if (order == null || !(order.getItemList().size() > 0)) {
            System.out.println("Order is empty!");
            return;
        }

        System.out.println("-> Checking if all items are available");
        for(Integer id : order.getItemList().keySet()) {
            if (!this.stock.isAvailable(this.stock.getStock().get(id),order.getItemList().get(id))) {
                System.out.println("Item "+ id + " is not Available. Cancelling...");
                return;
            }
        }
        System.out.println("OK");

        System.out.println("-> Updating stock");
        for(Integer id : order.getItemList().keySet()){
            this.stock.getStock().replace(id,this.stock.getStock().get(id)-order.getItemList().get(id));
        }
        System.out.println("OK");
    }
}

class Stock {
    private HashMap<Integer,Integer> stock = new HashMap<Integer,Integer>() ;

    public Stock(HashMap<Integer, Integer> stock) { this.stock = stock; }
    public HashMap<Integer, Integer> getStock() { return stock; }
    public Boolean isAvailable(Integer qtyItem, Integer qtyOrder) {
        return (qtyItem >= qtyOrder) ? Boolean.TRUE : Boolean.FALSE;
    }

}

//implement the real subject class
class Order {
    private HashMap<Integer,Integer> itemList;

    public Order(HashMap<Integer,Integer> itemList) { this.itemList = itemList; }
    public HashMap<Integer, Integer> getItemList() { return itemList; }
}

class ProxyExample {

    public static void main(String[] args) {
        IOrder warehouse = initialWarehouse();
        Order order = testOrder();
        warehouse.fulfillOrder(order);
    }

    public static Warehouse initialWarehouse(){
        HashMap<Integer, Integer> list = new HashMap<Integer, Integer>();
        list.put(1,2);
        list.put(2,5);
        list.put(3,11);

        Stock stock = new Stock(list);

        return new Warehouse("123 Street",stock);
    }

    public static Order testOrder(){
        HashMap<Integer,Integer> list = new HashMap<Integer, Integer>();
        list.put(2,1);
        list.put(3,5);

        return new Order(list);
    }
}