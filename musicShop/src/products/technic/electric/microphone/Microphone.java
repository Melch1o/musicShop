package products.technic.electric.microphone;

import products.technic.electric.ElectricTechnic;

public abstract class Microphone extends ElectricTechnic {


    public Microphone(int id) {
        super(id);
    }

    public Microphone(int id, double cost) {
        super(id, cost);
    }

    public Microphone(int id, String name, double cost) {
        super(id, name, cost);
    }

    public Microphone(String name, double price) {
        super(name, price);
    }
}
