package MainMenu;

import Others.MyButton;
import Others.MyComboBoxRenderer;
import Others.NotEnoughSpaceException;
import Warehouses.Warehouse;
import Warehouses.WarehouseComboModel;
import Warehouses.WarehouseListModel;
import Items.Item;
import Items.ItemComboModel;
import Items.ItemTableModel;

import javax.swing.*;
import java.awt.*;

public class AddItemPanel extends JPanel {

    public AddItemPanel(WarehouseListModel wlm, ItemTableModel itm){
        JComboBox<Warehouse> containers = new JComboBox<>();
        JComboBox<Item> items = new JComboBox<>();
        MyButton confirm = new MyButton("Add");
        JLabel status = new JLabel();

        status.setFont(new Font(getFont().getFontName(), Font.PLAIN, 20));

        containers.setModel(new WarehouseComboModel(wlm.getWarehouses()));
        items.setModel(new ItemComboModel(itm.getItems()));
        items.setRenderer(new MyComboBoxRenderer("Choose item"));
        containers.setRenderer(new MyComboBoxRenderer("Choose container"));

        containers.setPreferredSize(new Dimension(400,25));
        items.setPreferredSize(new Dimension(400,25));
        confirm.setPreferredSize(new Dimension(70,30));
        containers.setBackground(Color.WHITE);
        items.setBackground(Color.WHITE);
        containers.setFocusable(false);
        items.setFocusable(false);

        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;

        JLabel label = new JLabel("ADD ITEM TO A CONTAINER");
        label.setFont(new Font(getFont().getFontName(), Font.PLAIN, 30));
        add(label, gbc);

        gbc.gridy++;
        add(items, gbc);

        gbc.gridy++;
        add(containers, gbc);

        gbc.gridy++;
        add(confirm, gbc);

        gbc.gridy++;
        add(status, gbc);

        confirm.addActionListener(
                (ex)->{
                    Warehouse selectedWarehouse = (Warehouse)containers.getSelectedItem();
                    Item selectedItem = (Item)items.getSelectedItem();

                    if(selectedWarehouse != null && selectedItem != null) {
                        if(selectedWarehouse.getId() != selectedItem.getWarehouseId()) {
                            if(selectedItem.getWarehouseId() != 0){
                                for(Warehouse m : wlm.getWarehouses()){
                                    if(m.getId() == selectedItem.getWarehouseId())
                                        m.removeItem(selectedItem);
                                }
                            }
                            try {
                                selectedWarehouse.addItem(selectedItem);
                                status.setForeground(new Color(0,150,0));
                                status.setText("Item added successfully.");
                            } catch (NotEnoughSpaceException exc) {
                                status.setForeground(Color.RED);
                                status.setText(exc.getMessage());
                            }
                        }
                        else {
                            status.setForeground(Color.RED);
                            status.setText("ERROR: this item is already in this container.");
                        }

                        containers.setSelectedItem(null);
                        items.setSelectedItem(null);

                        revalidate();
                        repaint();
                    }
                }
        );
    }
}
