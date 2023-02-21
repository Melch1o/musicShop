package products.instrument.musical.keyboard;

import products.instrument.Instrument;

public abstract class Keyboard extends Instrument {

    public Keyboard(int id) {
        super(id);
    }

    public Keyboard(int id, double cost) {
        super(id, cost);
    }

    public Keyboard(int id, String name, double cost) {
        super(id, name, cost);
    }

    public Keyboard(String name, double price) {
        super(name, price);
    }

    public abstract int getKeyAmount();
}
