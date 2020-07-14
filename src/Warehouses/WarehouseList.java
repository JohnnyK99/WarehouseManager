package Warehouses;

import javax.swing.*;
import java.awt.*;

public class WarehouseList extends JList<Warehouse> {

    public WarehouseList(WarehouseListModel wlm){
        setModel(wlm);

        setFixedCellHeight(150);

        setCellRenderer(new ListCellRenderer<>() {
            WarehouseView warehouseView = new WarehouseView();
            @Override
            public Component getListCellRendererComponent(JList<? extends Warehouse> list, Warehouse value, int index, boolean isSelected, boolean cellHasFocus) {
                warehouseView.setWarehouse(value);
                return warehouseView;
            }
        });
    }
}
