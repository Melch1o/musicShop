package interaction;

import products.Product;
import java.util.ArrayList;

public class User {
    private final String login;
    private final String password;
    private double balance;
    private ArrayList<Product> cart;

    public void addToCart(Product p) {
        cart.add(p);
    }

    public double cartCost() {
        double cost = 0;
        for (int i = 0; i < cart.size(); i++) {
            cost += cart.get(i).getPrice();
        }
        return cost;
    }

    public boolean purchaseCart() {
        if (cartCost() > balance) {
            return false;
        }
        else {
            balance -= cartCost();
            cart.clear();
            return true;
        }
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.balance = 5000.0;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }
}
