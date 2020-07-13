package com.silvio.patterns.principles;

class Friends {
    public void N() {
        System.out.println("Method N invoked");
    }
}
class OO {
    public void M() {
        Friends I = new Friends();
        I.N();
        System.out.println("Method M invoked");
    }
}