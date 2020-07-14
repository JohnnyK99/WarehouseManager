package Others;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;

public class MyComboBoxRenderer extends BasicComboBoxRenderer {
    private String message;

    public MyComboBoxRenderer(String message){
        this.message = message;
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value == null)
            setText(message);

        return this;
    }
}
