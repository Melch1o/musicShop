package interaction;

import products.Product;
import products.instrument.musical.keyboard.Accordion;
import products.instrument.musical.keyboard.Piano;
import products.instrument.musical.string.Dombyra;
import products.instrument.musical.string.guitar.AcousticGuitar;
import products.instrument.musical.string.guitar.BassGuitar;
import products.technic.electric.headphones.FullSizeHeadphone;
import products.technic.electric.headphones.InnerHeadphone;
import products.technic.electric.microphone.CondenserMic;
import products.technic.electric.microphone.DynamicMic;

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
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    // Close the connection to the database
    private static void closeConnection() {
        try {
            con.close();
            stmt.close();
        } catch (SQLException se) {}
    }

//----------------------------------------------------------------//

    // Select all products from the database
    public ArrayList<Product> selectAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        openConnection();
        try {
            rs = stmt.executeQuery("SELECT * FROM product.instrument ORDER BY id");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                switch (rs.getString("type")){
                    case "Accordion" ->  products.add(new Accordion(id,name,price));
                    case "Piano" -> products.add(new Piano(id,name,price));
                    case "AcousticGuitar" -> products.add(new AcousticGuitar(id,name,price));
                    case "BassGuitar" -> products.add(new BassGuitar(id,name,price));
                    case "Dombyra" -> products.add(new Dombyra(id,name,price));
                    case "FullSizeHeadphone" -> products.add(new FullSizeHeadphone(id,name,price));
                    case "InnerHeadphone" -> products.add(new InnerHeadphone(id,name,price));
                    case "CondenserMic" -> products.add(new CondenserMic(id,name,price));
                    case "DynamicMic" -> products.add(new DynamicMic(id,name,price));
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            closeConnection();
        }
        return products;
    }

    public ArrayList<Product> selectSpecificProducts(String type) {
        ArrayList<Product> products = new ArrayList<>();
        openConnection();
        try {
            rs = stmt.executeQuery("SELECT * FROM product.instrument WHERE type = '"+ type +"' ORDER BY id");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                switch (type){
                    case "Accordion" ->  products.add(new Accordion(id,name,price));
                    case "Piano" -> products.add(new Piano(id,name,price));
                    case "AcousticGuitar" -> products.add(new AcousticGuitar(id,name,price));
                    case "BassGuitar" -> products.add(new BassGuitar(id,name,price));
                    case "Dombyra" -> products.add(new Dombyra(id,name,price));
                    case "FullSizeHeadphone" -> products.add(new FullSizeHeadphone(id,name,price));
                    case "InnerHeadphone" -> products.add(new InnerHeadphone(id,name,price));
                    case "CondenserMic" -> products.add(new CondenserMic(id,name,price));
                    case "DynamicMic" -> products.add(new DynamicMic(id,name,price));
                }
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
            String query = "INSERT INTO product.instrument (name, price, type) " +
                    "VALUES ('" + product.getName() + "', " + product.getPrice() + ", " + product.getClass().getSimpleName() + ")";
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