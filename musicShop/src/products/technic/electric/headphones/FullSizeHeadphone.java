package products.technic.electric.headphones;

public class FullSizeHeadphone extends Headphone {

    public FullSizeHeadphone(int id) {
        super(id);
    }

    public FullSizeHeadphone(int id, double cost) {
        super(id, cost);
    }

    public FullSizeHeadphone(int id, String name, double cost) {
        super(id, name, cost);
    }

    public FullSizeHeadphone(String name, double price) {
        super(name, price);
    }
}
