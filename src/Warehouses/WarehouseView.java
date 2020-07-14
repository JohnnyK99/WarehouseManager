package Warehouses;

import javax.swing.*;
import java.awt.*;

public class WarehouseView extends JPanel {
    private JLabel id;
    private JLabel capacity;
    private JLabel location;
    private JPanel fillIndicator;
    private JLabel fillLevel;

    public WarehouseView(){
        id = new JLabel();
        capacity = new JLabel();
        location = new JLabel();
        fillIndicator = new JPanel();
        fillLevel = new JLabel();

        id.setHorizontalAlignment(SwingConstants.CENTER);
        capacity.setHorizontalAlignment(SwingConstants.CENTER);
        location.setHorizontalAlignment(SwingConstants.CENTER);
        fillLevel.setHorizontalAlignment(SwingConstants.CENTER);

        id.setPreferredSize(new Dimension(50, 50));

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setBackground(Color.WHITE);

        setLayout(new BorderLayout());
        add(id, BorderLayout.NORTH);

        JPanel info = new JPanel();
        info.setLayout(new GridLayout(2,2, 5,5));
        info.add(capacity);
        info.add(fillIndicator);
        info.add(location);
        info.add(fillLevel);
        info.setBackground(Color.WHITE);
        fillIndicator.setBackground(Color.WHITE);

        add(info, BorderLayout.CENTER);

    }

    public void setWarehouse(Warehouse w){
        id.setText("WAREHOUSE " + w.getId());
        capacity.setText("Capacity: " + w.getCapacity() + "m2");
        location.setText("Location: " + w.getLocation());
        fillIndicator.removeAll();
        FillLevelComponent fill = new FillLevelComponent(w.fillLevel());
        fill.setOpaque(true);
        fill.setPreferredSize(new Dimension(101,50));
        fillIndicator.add(fill);
        fillLevel.setText("Fill level: " + w.fillLevel() + "%");
    }
}
