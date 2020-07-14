import Others.Users;
import Others.NotEnoughSpaceException;
import Warehouses.Warehouse;
import Warehouses.WarehouseListModel;
import Items.Item;
import Items.ItemTableModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Users users = new Users();
        users.addUser("jsmith", "123456");
        users.addUser("ajones", "password123");
        users.addUser("mbutler", "qwerty");

        Warehouse w1 = new Warehouse(100, "Paris");
        Warehouse w2 = new Warehouse(90,"Warsaw");
        Warehouse w3 = new Warehouse(75,"Moscow");
        Warehouse w4 = new Warehouse(140,"Warsaw");
        Warehouse w5 = new Warehouse(115, "Berlin");

        Item item = new Item("Washing machine", 10, "It's a washing machine");
        Item item2 = new Item("Car",20,"Audi A4");
        Item item3 = new Item("Plane",100,"Airbus A380");
        Item item4 = new Item("Wardrobe",8,"It's a wardrobe");
        Item item5 = new Item("Flour",25,"25 bags of flour");
        Item item6 = new Item("Bike",12,"Mountain bike");
        Item item7 = new Item("Digger",35,"For the highway construction");
        Item item8 = new Item("Helicopter", 30, "Medical helicopter");
        Item item9 = new Item("Fridge", 10, "With a freezing function");
        Item item10 = new Item("Vacuum", 3, "It's a vacuum");

        try {
            w1.addItem(item7);
            w1.addItem(item5);
            w2.addItem(item);
            w4.addItem(item2);
            w4.addItem(item3);
            w5.addItem(item4);
            w5.addItem(item8);
            w5.addItem(item9);
        } catch (NotEnoughSpaceException ex){
            ex.printStackTrace();
        }

        WarehouseListModel wlm = new WarehouseListModel();
        wlm.addWarehouse(w1);
        wlm.addWarehouse(w2);
        wlm.addWarehouse(w3);
        wlm.addWarehouse(w4);
        wlm.addWarehouse(w5);

        ItemTableModel itm = new ItemTableModel();
        itm.addItem(item);
        itm.addItem(item2);
        itm.addItem(item3);
        itm.addItem(item4);
        itm.addItem(item5);
        itm.addItem(item6);
        itm.addItem(item7);
        itm.addItem(item8);
        itm.addItem(item9);
        itm.addItem(item10);

        SwingUtilities.invokeLater(
                ()->new Window(users, wlm, itm)
        );

    }
}
