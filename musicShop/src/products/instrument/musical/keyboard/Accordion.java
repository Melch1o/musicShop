package products.instrument.musical.keyboard;

public class Accordion extends Keyboard {
    public Accordion(int id) {
        super(id);
    }

    public Accordion(int id, double cost) {
        super(id, cost);
    }

    public Accordion(int id, String name, double cost) {
        super(id, name, cost);
    }

    public Accordion(String name, double price) {
        super(name, price);
    }

    @Override
    public int getKeyAmount() {
        return 96;
    }
}
