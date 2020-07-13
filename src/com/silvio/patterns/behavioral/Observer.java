package com.silvio.patterns.behavioral;

import java.util.ArrayList;

abstract class Subject {
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public void registerObserver(Observer observer){ observers.add(observer); }
    public void unregisterObserver(Observer observer){ observers.remove(observer); }
    public void notifyObservers(){ for(Observer observer: observers) observer.update(); }
}

class Blog extends Subject {
    private String name;

    public Blog(String name) {
        super();
        this.name = name;
    }

    public String getName() { return this.name; }
}

abstract interface Observer {
    public void update();
}

class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) { this.name = name; }
    public String getName() { return name; }

    @Override
    public void update() { System.out.println("Update for "+this.name); }
}

class ObserverExample {

    public static void main(String[] args) {

        Observer subscriber1 = new Subscriber("Subscriber 1");
        Observer subscriber2 = new Subscriber("Subscriber 2");

        Subject blog = new Blog("Blog of Design Patterns");
        blog.registerObserver(subscriber1);
        blog.registerObserver(subscriber2);

        System.out.println("-> First notification of observers");
        blog.notifyObservers();

        Observer subscriber3 = new Subscriber("Subscriber 3");
        blog.registerObserver(subscriber3);
        blog.unregisterObserver(subscriber2);

        System.out.println("-> Second notification of observers");
        blog.notifyObservers();
    }
}
