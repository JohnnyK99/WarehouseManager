package Items;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ItemTable extends JTable {

    public ItemTable(ItemTableModel itm){
        setModel(itm);
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Integer.class, center);
        setDefaultRenderer(Double.class, center);
        setDefaultRenderer(String.class, center);
        setFocusable(false);
        getTableHeader().setBackground(new Color(81,139,255));
        getTableHeader().setForeground(Color.WHITE);
        getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
}
