package com.silvio.patterns.creational;

//design the target interface
abstract interface ITarget {
    public abstract void convertText(String text);
}

//implement the target interface with the adapter class
class Adapter implements ITarget {

    private Adaptee adaptee = new Receiver();

    @Override
    public void convertText(String text) {
        System.out.println("Text input: " + text );
        String t1 = text.toUpperCase();
        System.out.println("Text converted: " + t1 );
        adaptee.transmitText(t1);
    }
}

abstract interface Adaptee {
    public abstract void transmitText(String received);
}

class Receiver implements Adaptee {

    @Override
    public void transmitText(String received) {
        System.out.println("Text received: " + received);
    }
}

//send the request from the client to the adapter using the target interface
class AdapterExample {
    public static void main(String[] args) {
        ITarget adapter = new Adapter();
        adapter.convertText("example text");
    }
}
