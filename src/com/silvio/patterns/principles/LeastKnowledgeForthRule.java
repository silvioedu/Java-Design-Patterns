package com.silvio.patterns.principles;

class Friendss {
    public void N() {
        System.out.println("Method N invoked");
    }
}
class OOO {
    public Friend I = new Friend();
    public void M() {
        this.I.N();
        System.out.println("Method M invoked");
    }
}