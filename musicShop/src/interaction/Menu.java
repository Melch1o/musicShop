package interaction;

import products.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private User user;
    private Database db;
    Scanner sc = new Scanner(System.in);

    //------------------------------------------//
    // Singleton menu object creation
    private static Menu instance;

    private Menu(Database db) {
        this.db = db;
    }

    public static Menu initMenu(Database db) {
        if (instance == null) {
            instance = new Menu(db);
        }
        return instance;
    }

    //------------------------------------------//
    // Entry menu page
    public void entry(){
        System.out.println("""
                =====================================
                 Welcome, User! What would you like:
                      1. Log in      2.  Sign up  \s
                =====================================""");
        int choice = sc.nextInt();
        if (choice == 1) login();
        else if (choice == 2) signup();
        else {
            System.out.println(" Please, choose from given options");
            entry();
        }
    }

    // log in menu page
    private void login(){
        System.out.print( "================Log in===============\n" +
                          " Login: ");
        String login = sc.next();
        System.out.print( " Password: ");
        String password = sc.next();

        if (login.equals("root") && password.equals("admin")) {
            System.out.println(" You're loged in as Admin\n" +
                               "=====================================");
            adminPanel();
        }

        if (user != null) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                System.out.println(" Hello, " + user.getLogin() + "!\n" +
                                   "=====================================");
                shop();
            } else {
                System.out.println(" Incorrect password or login\n" +
                                   "=====================================");
                entry();
            }
        }
        else {
            System.out.println(" Can't find such a user. Try creating account first\n" +
                               "=====================================");
            entry();
        }
    }

    // Sign up menu page
    private void signup(){
        System.out.print( "===============Sign up===============\n" +
                          " Login: ");
        String login = sc.next();
        System.out.print( " Password: ");
        String password = sc.next();

        if (user == null) {
            System.out.println("Account created successfully!\n" +
                               "=====================================");
            user = new User(login, password);
            login();
        }
        else {
            System.out.println(" User already exist \n" +
                               "=====================================");
            entry();
        }
    }

    // Admin panel page
    private void adminPanel() {
        System.out.println("""
                =================Admin===============
                 1. Add new product
                 2. Delete product
                 3. Log out
                =====================================""");
        int option = sc.nextInt();
        switch (option) {
            case 1 -> {
                System.out.print("=================Create==============\n" +
                        " Product name: ");
                sc.nextLine();
                String name = sc.nextLine();
                System.out.print(" Price: ");
                double price = sc.nextDouble();
                db.insertProduct(new Product(name, price));
                System.out.println(" Product created successfully!\n" +
                        "=====================================");
            }

            case 2 -> {
                System.out.print("================Delete==============\n" +
                        "Product ID: ");
                int id = sc.nextInt();
                db.deleteProduct(id);
                System.out.println(" Product deleted successfully!" +
                        "=====================================");
            }

            case 3 -> entry();
            default -> System.out.println(" Please, choose from given options");
        }
        adminPanel();
    }

    // Shop main menu
    private void shop() {
        System.out.println("""
                ==================Shop===============
                 1. Browse products
                 2. Your cart
                 3. Log out
                =====================================""");
        int option = sc.nextInt();

        switch (option) {
            case 1 -> {
                ArrayList<Product> pr = db.selectAllProducts();
                System.out.println("==================Shop===============");
                for (Product product : pr) {
                    System.out.print(product.toString());
                }
                System.out.print("=====================================\n" +
                                   " What would you buy? \n" +
                                   " Enter ID's here: ");
                sc.nextLine();
                String[] strid = sc.nextLine().split("\\D+");
                for (String s : strid) {
                    int id = Integer.parseInt(s);
                    try {
                        user.addToCart(pr.get(id));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(" ID: " + id + " out of bound");
                    }
                }
                System.out.println(" Products added to cart successfully!\n" +
                                   "=====================================");
            }

            case 2 -> {
                System.out.println("==================Cart===============");
                if (user.getCart() != null) {
                    for (int i = 0; i < user.getCart().size(); i++) {
                        System.out.print(user.getCart().get(i).toString());
                    }
                    System.out.println("=====================================\n" +
                            "    Would you like to buy them?\n" +
                            " Your balance: " + user.getBalance() + " | Cost: " + user.cartCost() + "\n" +
                            "          1. Yes      2. No   \n" +
                            "=====================================");
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        if (user.purchaseCart()) {
                            System.out.println(" Thank you for shopping! Products was sent to your address");
                        } else {
                            System.out.println(" Not enough money on balance");
                        }
                    } else if (choice == 2) {
                        shop();
                    } else {
                        System.out.println(" Please, choose from given options");
                    }
                }
                else {
                    System.out.println(" Your cart is empty. Maybe it's time for shopping?\n" +
                                       "=====================================");
                }
            }

            case 3 -> entry();
            default -> System.out.println(" Please, choose from given options");
        }
        shop();
    }
}
