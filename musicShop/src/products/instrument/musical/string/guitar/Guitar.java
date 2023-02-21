package products.instrument.musical.string.guitar;

import products.instrument.musical.string.StringInstrument;

public abstract class Guitar extends StringInstrument {
    public Guitar(int id) {
        super(id);
    }

    public Guitar(int id, double cost) {
        super(id, cost);
    }

    public Guitar(int id, String name, double cost) {
        super(id, name, cost);
    }

    public Guitar(String name, double price) {
        super(name, price);
    }
}
