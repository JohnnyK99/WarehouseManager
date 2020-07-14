package Warehouses;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class WarehouseListModel implements ListModel<Warehouse> {

    ArrayList<Warehouse> warehouses;

    public WarehouseListModel(){
        warehouses = new ArrayList<>();
    }

    public void addWarehouse(Warehouse w){
        warehouses.add(w);
    }

    public ArrayList<Warehouse> getWarehouses(){
        return warehouses;
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
