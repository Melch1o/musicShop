package products.instrument.musical.string;

public class Dombyra extends StringInstrument {

    public Dombyra(int id) {
        super(id);
    }

    public Dombyra(int id, double cost) {
        super(id, cost);
    }

    public Dombyra(int id, String name, double cost) {
        super(id, name, cost);
    }

    public Dombyra(String name, double price) {
        super(name, price);
    }

    @Override
    public int getStringAmount() {
        return 2;
    }
}

