package com.silvio.patterns.creational;

//create an abstract Creator class, that contains methods that only operate on generalizations
abstract class Creator {
    public abstract Product createProduct(String name);
}

//create a ConcreteCreator class that is responsable for concrete instantiation
class ConcreteCreator extends Creator {
    public Product createProduct(String productType) {
        return productType.equalsIgnoreCase("concrete")
                ? new ConcreteProduct()
                : new OtherConcreteProduct();
    }
}

//create an abstract Product class, that contains methods that only operate on generalizations
abstract class Product {
    protected abstract void displayMessage();
}

//create a ConcreteProduct class that is responsable for concrete methods
class ConcreteProduct extends Product{
    @Override
    protected void displayMessage() { System.out.println("ConcreteProduct"); }
}

//create a ConcreteProduct class that is responsable for concrete methods
class OtherConcreteProduct extends Product{
    @Override
    protected void displayMessage() { System.out.println("OtherConcreteProduct"); }
}

class FactoryExample {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product0 = creator.createProduct("concrete");
        Product product1 = creator.createProduct("other");

        product0.displayMessage();
        product1.displayMessage();
    }
}