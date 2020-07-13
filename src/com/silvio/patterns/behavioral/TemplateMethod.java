package com.silvio.patterns.behavioral;

//the superclass must contain abstract methods that are implemented by the subclasses
abstract class SelfDrivingVehicle {

    public void driveToDestination() { System.out.println("Driving to destination"); }
    private void reachDestination() { System.out.println("Reach destination"); }

    abstract void accelerate();
    abstract void brake();
}

//inherit from the template  method and implement the abstract methods
class SelfDrivingCar extends SelfDrivingVehicle {
    @Override
    void accelerate() { System.out.println("Accelerate Car"); }

    @Override
    void brake() { System.out.println("Brake Car"); }
}

//inherit from the template  method and implement the abstract methods
class SelfDrivingMotorcycle extends SelfDrivingVehicle {
    @Override
    void accelerate() { System.out.println("Accelerate Motorcycle"); }

    @Override
    void brake() { System.out.println("Brake Motorcycle"); }
}

class TemplateMethodExample {
    public static void main(String[] args) {
        SelfDrivingVehicle car = new SelfDrivingCar();
        car.accelerate();
        car.brake();

        SelfDrivingVehicle motorcycle = new SelfDrivingMotorcycle();
        motorcycle.accelerate();
        motorcycle.brake();
    }
}
