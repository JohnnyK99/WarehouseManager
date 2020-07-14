package Others;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    public MyButton(String text){
        setText(text);
        setBackground(new Color(81,139,255));
        setForeground(Color.WHITE);
        setFocusable(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
}
