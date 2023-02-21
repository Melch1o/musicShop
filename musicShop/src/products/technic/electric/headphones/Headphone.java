package products.technic.electric.headphones;

import products.technic.electric.ElectricTechnic;

public abstract class Headphone extends ElectricTechnic {

    public Headphone(int id) {
        super(id);
    }

    public Headphone(int id, double cost) {
        super(id, cost);
    }

    public Headphone(int id, String name, double cost) {
        super(id, name, cost);
    }

    public Headphone(String name, double price) {
        super(name, price);
    }
}
