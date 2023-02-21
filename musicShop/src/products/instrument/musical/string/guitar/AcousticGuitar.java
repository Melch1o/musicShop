package products.instrument.musical.string.guitar;

public class AcousticGuitar extends Guitar {
    public AcousticGuitar(int id) {
        super(id);
    }

    public AcousticGuitar(int id, double cost) {
        super(id, cost);
    }

    public AcousticGuitar(int id, String name, double cost) {
        super(id, name, cost);
    }

    public AcousticGuitar(String name, double price) {
        super(name, price);
    }

    @Override
    public int getStringAmount() {
        return 6;
    }
}
