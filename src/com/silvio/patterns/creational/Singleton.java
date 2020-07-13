package com.silvio.patterns.creational;

class StringSingleton {

    //declare a class variable called "uniqueInstance"
    private static StringSingleton uniqueInstance = null;

    private StringSingleton() {
        System.out.println("Created");
    }

    //create a public method called “getInstance”
    public static StringSingleton getInstance() {
        //the method will check if the “uniqueInstance” variable is null
        return uniqueInstance == null
                //If it is null, then it will instantiate the class and set this variable to reference the object.
                ? uniqueInstance = new StringSingleton()
                //On the other hand then the method will simply return that object.
                : uniqueInstance;
    }
}

class SingletonExample {

    public static void main(String[] args) {
        System.out.println("first instance");
        StringSingleton s0 = StringSingleton.getInstance();
        System.out.println("second instance");
        StringSingleton s1 = StringSingleton.getInstance();
        System.out.println("third instance");
        StringSingleton s2 = StringSingleton.getInstance();
    }
}
