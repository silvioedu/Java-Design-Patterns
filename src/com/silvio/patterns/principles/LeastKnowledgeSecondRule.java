package com.silvio.patterns.principles;

class Friend {
    public void N() {
        System.out.println("Methor N invoked");
    }
}

class OO {
    public void M(Friend P) {
        P.N();
        System.out.println("Method M invoked");
    }
}