package com.silvio.patterns.behavioral;

class Button {
    private Fan fan;
    private Mediator mediator;

    public Button(Fan fan, Mediator mediator) {
        this.fan = fan;
        this.mediator = mediator;
    }

    public void press() { this.mediator.press(); }
}

class Fan {
    private boolean isOn;
    private Mediator mediator;

    public Fan(Mediator mediator) {
        this.isOn = false;
        this.mediator = mediator;
    }

    public boolean isOn() { return isOn; }

    public void turnOn() {
        this.isOn = true;
        mediator.start();
        System.out.println("Fan is On");
    }

    public void turnOff() {
        this.isOn = false;
        this.mediator.stop();
        System.out.println("Fan is Off");
    }

}

class PowerSupplier {
    public void turnOn() { System.out.println("Power Supplier On"); }
    public void turnOff() { System.out.println("Power Supplier Off"); }
}

class Mediator {
    private Button button;
    private Fan fan;
    private PowerSupplier powerSupplier;

    public Mediator() {
        this.powerSupplier = new PowerSupplier();
        this.fan = new Fan(this);
        this.button = new Button(this.fan,this);
    }

    public void press() {
        if (this.fan.isOn()) this.fan.turnOff();
        else this.fan.turnOn();
    }

    public void start() { this.powerSupplier.turnOn(); }
    public void stop() { this.powerSupplier.turnOff(); }


}

class MediatorExample {

    public static void main(String[] args) {

        Mediator m = new Mediator();
        System.out.println("-> Turn on");
        m.press();
        System.out.println("-> Turn off");
        m.press();
    }
}