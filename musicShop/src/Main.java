import interaction.Database;
import interaction.Menu;

public class Main {
    public static void main(String[] args) {
        Database db = Database.initDatabase();
        Menu menu = Menu.initMenu(db);
        menu.entry();
    }
}