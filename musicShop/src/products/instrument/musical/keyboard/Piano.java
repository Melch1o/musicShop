package products.instrument.musical.keyboard;

public class Piano extends Keyboard {

    public Piano(int id) {
        super(id);
    }

    public Piano(int id, double cost) {
        super(id, cost);
    }

    public Piano(int id, String name, double cost) {
        super(id, name, cost);
    }

    public Piano(String name, double price) {
        super(name, price);
    }

    @Override
    public int getKeyAmount() {
        return 88;
    }
}
