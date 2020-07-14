package MainMenu;

import Others.MyButton;
import Warehouses.Warehouse;
import Warehouses.WarehouseListModel;

import javax.swing.*;
import java.awt.*;

public class AddWarehousePanel extends JPanel {

    public AddWarehousePanel(WarehouseListModel wlm){
        JTextField capacity = new JTextField();
        JTextField location = new JTextField();
        MyButton confirm = new MyButton("Add");
        JLabel status = new JLabel();
        status.setFont(new Font(getFont().getFontName(), Font.PLAIN, 20));

        confirm.setPreferredSize(new Dimension(70,30));

        setBackground(Color.WHITE);

        capacity.setPreferredSize(new Dimension(150,25));
        location.setPreferredSize(new Dimension(150,25));
        capacity.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        location.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.gridwidth = 2;

        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel label = new JLabel("ADD NEW WAREHOUSE");
        label.setFont(new Font(getFont().getFontName(), Font.PLAIN, 30));
        add(label, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        label = new JLabel("Capacity [m2]:");
        label.setFont(new Font(getFont().getFontName(), Font.PLAIN, 20));
        add(label,gbc);

        gbc.gridx++;
        add(capacity, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        label = new JLabel("Location:");
        label.setFont(new Font(getFont().getFontName(), Font.PLAIN, 20));
        add(label, gbc);

        gbc.gridx++;
        add(location, gbc);

        gbc.gridwidth = 2;
        gbc.gridy++;
        gbc.gridx = 1;
        add(confirm, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(status, gbc);

        confirm.addActionListener(
                (ex)->{
                    try {
                        wlm.addWarehouse(new Warehouse(Double.parseDouble(capacity.getText()), location.getText()));
                        status.setForeground(new Color(0,150,0));
                        status.setText("Warehouse added successfully");
                    } catch (NumberFormatException exc){
                        status.setForeground(Color.RED);
                        status.setText("Invalid data format");
                    }
                    revalidate();
                }
        );

    }
}
