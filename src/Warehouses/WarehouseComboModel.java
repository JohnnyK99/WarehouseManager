package Warehouses;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class WarehouseComboModel implements ComboBoxModel<Warehouse> {

    ArrayList<Warehouse> warehouses;
    Object selected;

    public WarehouseComboModel(ArrayList<Warehouse> warehouses){
        this.warehouses = warehouses;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

    @Override
    public int getSize() {
        return warehouses.size();
    }

    @Override
    public Warehouse getElementAt(int index) {
        return warehouses.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
