package products.technic.electric.headphones;

public class InnerHeadphone extends Headphone{

    public InnerHeadphone(int id) {
        super(id);
    }

    public InnerHeadphone(int id, double cost) {
        super(id, cost);
    }

    public InnerHeadphone(int id, String name, double cost) {
        super(id, name, cost);
    }

    public InnerHeadphone(String name, double price) {
        super(name, price);
    }
}
