package products.instrument.musical.string;

import products.instrument.musical.MusicalInstrument;

public abstract class StringInstrument extends MusicalInstrument {


    public StringInstrument(int id) {
        super(id);
    }

    public StringInstrument(int id, double cost) {
        super(id, cost);
    }

    public StringInstrument(int id, String name, double cost) {
        super(id, name, cost);
    }

    public StringInstrument(String name, double price) {
        super(name, price);
    }

    public abstract int getStringAmount();
}
