package products.instrument;

import products.Product;

public abstract class Instrument extends Product {

    public Instrument(int id) {
        super(id);
    }

    public Instrument(int id, double cost) {
        super(id, cost);
    }

    public Instrument(int id, String name, double cost) {
        super(id, name, cost);
    }

    public Instrument(String name, double price) {
        super(name, price);
    }
}
