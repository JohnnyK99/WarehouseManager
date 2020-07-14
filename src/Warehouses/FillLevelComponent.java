package Warehouses;

import javax.swing.*;
import java.awt.*;

public class FillLevelComponent extends JComponent {

    private int percent;

    public FillLevelComponent(int percent) {
        this.percent = percent;
    }

    @Override
    protected void paintComponent (Graphics g){

        g.setColor(Color.BLACK);
        g.drawRect(0,0,100,18);

        g.setColor(new Color((255* percent)/100, 255-(255* percent)/100, 0));
        g.fillRect(1,1, percent, 17);

        repaint();

    }
}
