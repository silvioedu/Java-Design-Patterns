package com.silvio.patterns.behavioral;

abstract interface State {
    public void insertDollar(VendingMachine vendingMachine);
    public void ejectMoney(VendingMachine vendingMachine);
    public void dispense(VendingMachine vendingMachine);
}

class IdleState implements State {

    @Override
    public void insertDollar(VendingMachine vendingMachine) {
        System.out.println("dollar inserted");
        vendingMachine.getHasOneDollarState();
    }

    @Override
    public void ejectMoney(VendingMachine vendingMachine) { System.out.println("no money to return"); }

    @Override
    public void dispense(VendingMachine vendingMachine) { System.out.println("payment required"); }

    @Override
    public String toString() { return "-> Idle State"; }
}

class HasOneDollarState implements State {

    @Override
    public void insertDollar(VendingMachine vendingMachine) {
        System.out.println("already have one dollar");
        vendingMachine.doReturnMoney();
    }

    @Override
    public void ejectMoney(VendingMachine vendingMachine) {
        System.out.println("returning money");
        vendingMachine.doReturnMoney();
        vendingMachine.setState(vendingMachine.getIdleState());
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        System.out.println("releasing product");

        if (vendingMachine.getCount() > 1) {
            vendingMachine.doReleaseProduct();
            vendingMachine.setState(vendingMachine.getIdleState());
        } else {
            vendingMachine.doReleaseProduct();
            vendingMachine.getOutOfStockSate();
        }
    }

    @Override
    public String toString() { return "-> HasOneDollar State"; }

}

class OutOfStockState implements State {

    @Override
    public void insertDollar(VendingMachine vendingMachine) {
        System.out.println("product is out of stock");
        vendingMachine.doReturnMoney();
    }

    @Override
    public void ejectMoney(VendingMachine vendingMachine) {
        System.out.println("returning money");
        vendingMachine.doReturnMoney();
        vendingMachine.setState(vendingMachine.getIdleState());
    }

    @Override
    public void dispense(VendingMachine vendingMachine) {
        System.out.println("product is out of stock");

        if (vendingMachine.getCount() > 1) {
            vendingMachine.doReleaseProduct();
            vendingMachine.setState(vendingMachine.getIdleState());
        } else {
            vendingMachine.doReleaseProduct();
            vendingMachine.getOutOfStockSate();
        }
    }

    @Override
    public String toString() { return "-> OutOfStock State"; }

}

class VendingMachine {

    private State idleState;
    private State hasOneDollarState;
    private State outOfStockState;

    private State currentState;
    private int count;

    public VendingMachine(int count){
        idleState = new IdleState();
        hasOneDollarState = new HasOneDollarState();
        outOfStockState = new OutOfStockState();

        if (count > 0){
            this.currentState = idleState;
            this.count = count;
        } else {
            currentState = outOfStockState;
            this.count = 0;
        }
    }

    public void doReturnMoney() { System.out.println("Returning money"); }
    public void doReleaseProduct() { System.out.println("Releasing product"); }

    public void insertDollar() { currentState.insertDollar(this); }
    public void ejectMoney() { currentState.ejectMoney(this); }
    public void dispense() { currentState.dispense(this); }

    public int getCount() { return count; }
    public State getHasOneDollarState() { return hasOneDollarState; }
    public State getIdleState() { return idleState; }
    public State getOutOfStockSate() { return outOfStockState; }

    public void setState(State currentState) { this.currentState = currentState; }
    public void logState(){ System.out.println(this.currentState); }
}

class StateExample {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine(2);
        vendingMachine.logState();
        vendingMachine.insertDollar();
        vendingMachine.logState();
        vendingMachine.ejectMoney();
        vendingMachine.logState();
        vendingMachine.dispense();

        vendingMachine = new VendingMachine(0);
        vendingMachine.logState();
        vendingMachine.insertDollar();
        vendingMachine.logState();
        vendingMachine.ejectMoney();
        vendingMachine.logState();
        vendingMachine.dispense();
    }
}
