package products.instrument.musical.string.guitar;

public class BassGuitar extends Guitar {
    public BassGuitar(int id) {
        super(id);
    }

    public BassGuitar(int id, double cost) {
        super(id, cost);
    }

    public BassGuitar(int id, String name, double cost) {
        super(id, name, cost);
    }

    public BassGuitar(String name, double price) {
        super(name, price);
    }

    @Override
    public int getStringAmount() {
        return 4;
    }
}
