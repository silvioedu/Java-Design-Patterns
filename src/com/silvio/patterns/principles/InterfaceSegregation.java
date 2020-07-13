package com.silvio.patterns.principles;

abstract interface ICashier {
    public void scanItem();
    public void takePayment();
    public void dispenseChange();
}

class SelfServiceMachine implements ICashier {

    @Override
    public void scanItem() {
        //Do something
    }

    @Override
    public void takePayment() {
        //Do something
    }

    @Override
    public void dispenseChange() {
        //Do something
    }
}

abstract interface IHumanWorker {
    public void startShift();
    public void takeBreak();
    public void completeShift();
}

class HumanCashier implements ICashier,IHumanWorker {

    @Override
    public void startShift() {
        //Do something
    }

    @Override
    public void takeBreak() {
        //Do something
    }

    @Override
    public void completeShift() {
        //Do something
    }

    @Override
    public void scanItem() {
        //Do something
    }

    @Override
    public void takePayment() {
        //Do something
    }

    @Override
    public void dispenseChange() {
        //Do something
    }
}
