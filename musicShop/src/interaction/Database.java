package interaction;

import products.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    // JDBC URL, username and password of MySQL server
    private final static String url = "jdbc:postgresql://127.0.0.1:5432/musicShop";
    private final static String user = "postgres";
    private final static String pass = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    //---------------------------------------------------------------//
    // Singleton database object creation
    private static Database instance;

    private Database() {}

    public static Database initDatabase(){
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    //---------------------------------------------------------------//
    // Open a connection to the database
    private static void openConnection() {
        try {
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();
            System.out.println("Success, DB open");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    // Close the connection to the database
    private static void closeConnection() {
        try {
            con.close();
            stmt.close();
            System.out.println("Success, DB closed");
        } catch (SQLException se) {}
    }

//----------------------------------------------------------------//

    // Select all products from the database
    public ArrayList<Product> selectAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        openConnection();
        try {
            rs = stmt.executeQuery("SELECT * FROM product.instrument");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                products.add(new Product(id,name,price));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            closeConnection();
        }
        return products;
    }

    // Insert a product into the database
    public void insertProduct(Product product) {
        openConnection();
        try {
            String query = "INSERT INTO product.instrument (name, price) " +
                    "VALUES ('" + product.getName() + "', " + product.getPrice() + ")";
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // Update a product in the database
    public void updateProduct(Product product) {
        openConnection();
        try {
            String query = "UPDATE product.instrument SET name = '" + product.getName() +
                    "', price = " + product.getPrice() +
                    " WHERE id = " + product.getId();
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // Delete a product from the database
    public void deleteProduct(int id) {
        openConnection();
        try {
            String query = "DELETE FROM product.instrument WHERE id = " + id;
            stmt.executeUpdate(query);
        }
        catch (SQLException sqlEx) {}
        }
}