package Items;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class ItemTableModel implements TableModel {

    ArrayList<Item> items;

    public ItemTableModel(){
        items = new ArrayList<>();
    }

    public ItemTableModel(ArrayList<Item> items){
        this.items = items;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0: return "ID";
            case 1: return "Name";
            case 2: return "Size";
            case 3: return "Warehouse ID";
            case 4: return "Description";
            default: return "ERROR";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0: return Integer.class;
            case 1: return String.class;
            case 2: return Double.class;
            case 3: return String.class;
            case 4: return String.class;
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item p = items.get(rowIndex);
        switch (columnIndex){
            case 0: return p.getId();
            case 1: return p.getName();
            case 2: return p.getSize();
            case 3: {
                if(p.getWarehouseId() == 0)
                    return "NONE";
                else
                    return Integer.toString(p.getWarehouseId());
            }
            case 4: return p.getDescription();
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
