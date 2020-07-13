package com.silvio.patterns.structural;

import java.util.ArrayList;

//design the interface that defines the overall type
abstract interface IStructure {
    public abstract void location();
    public abstract String getName();
}

//implement the composite class
class Housing implements IStructure {

    private ArrayList<IStructure> structures;
    private String address;

    public Housing (String address) {
        this.structures = new ArrayList<IStructure>();
        this.address = address;
        System.out.println("New Housing created is called " + this.getName());
    }

    public String getName() { return this.address; }

    public void addStructure(IStructure component) {
        this.structures.add(component);
        System.out.println("New Structure called " + component.getName()+ " added on "+ this.getName());
    }

    public IStructure getStructure(int componentNumber) { return this.structures.get(componentNumber); }

    public void location() {
        System.out.println("Location " + this.getName());
        for (IStructure struct : this.structures) {
            System.out.println("-> ".concat(struct.getName()));
        }
    }
}

//implement the leaf class
class Room implements IStructure {

    public String name;

    public Room(String room) {
        this.name = room;
        System.out.println("New Room created is called " + this.getName());
    }

    public void location() { System.out.println("You are currently in the " + this.name); }
    public String getName() { return this.name; }
}

class CompositeExample {
    public static void main(String args[]) {
        System.out.println("-> Creating a build");
        Housing building = new Housing("123 Street");
        System.out.println("-> Creating a floor");
        Housing floor1 = new Housing("First Floor");
        System.out.println("-> Adding a floor on a building");
        building.addStructure(floor1);
        System.out.println("-> Creating and adding a first room on the first floor");
        Room room1 = new Room("Room 1");
        floor1.addStructure(room1);
        System.out.println("-> Creating and adding a second room on the first floor");
        Room room2 = new Room("Room 2");
        floor1.addStructure(room2);
    }
}