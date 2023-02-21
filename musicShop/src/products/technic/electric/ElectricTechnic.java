package products.technic.electric;

import products.Product;

public abstract class ElectricTechnic extends Product {

    public ElectricTechnic(int id) {
        super(id);
    }

    public ElectricTechnic(int id, double cost) {
        super(id, cost);
    }

    public ElectricTechnic(String name, double price) {
        super(name, price);
    }

    public ElectricTechnic(int id, String name, double cost) {
        super(id, name, cost);
    }
}

