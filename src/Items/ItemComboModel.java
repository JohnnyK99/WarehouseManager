package Items;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class ItemComboModel implements ComboBoxModel<Item> {

    ArrayList<Item> items;
    Object selected;

    public ItemComboModel(ArrayList<Item> items){
        this.items = items;
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
        return items.size();
    }

    @Override
    public Item getElementAt(int index) {
        return items.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
