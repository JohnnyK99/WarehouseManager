package Warehouses;

import Items.ItemTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class WarehouseFullView extends WarehouseView {
    JPanel items;

    public WarehouseFullView(){
        super();
        items = new JPanel();
        items.setLayout(new BorderLayout());
        add(items,BorderLayout.SOUTH);
    }

    public void setWarehouse(Warehouse w){
        items.removeAll();
        super.setWarehouse(w);
        JTable table = new JTable();
        table.setModel(new ItemTableModel(w.getItems()));
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Integer.class, center);
        table.setDefaultRenderer(Double.class, center);
        table.setDefaultRenderer(String.class, center);
        table.getTableHeader().setBackground(new Color(81,139,255));
        table.getTableHeader().setForeground(Color.WHITE);
        items.add(table,BorderLayout.CENTER);
        items.add(table.getTableHeader(), BorderLayout.NORTH);
    }
}
