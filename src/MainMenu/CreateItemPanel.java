package MainMenu;

import Others.MyButton;
import Items.Item;
import Items.ItemTableModel;

import javax.swing.*;
import java.awt.*;

public class CreateItemPanel extends JPanel {

    public CreateItemPanel(ItemTableModel itm){
        JTextField name = new JTextField();
        JTextField size = new JTextField();
        JTextArea description = new JTextArea();
        MyButton confirm = new MyButton("Create");
        JLabel status = new JLabel();
        status.setFont(new Font(getFont().getFontName(), Font.PLAIN, 20));

        setBackground(Color.WHITE);

        name.setPreferredSize(new Dimension(150,25));
        size.setPreferredSize(new Dimension(150,25));
        confirm.setPreferredSize(new Dimension(70,30));
        description.setPreferredSize(new Dimension(400,150));
        description.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.gridwidth = 2;

        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel label = new JLabel("CREATE NEW ITEM");
        label.setFont(new Font(getFont().getFontName(), Font.PLAIN, 30));
        add(label, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        label = new JLabel("Name:");
        label.setFont(new Font(getFont().getFontName(), Font.PLAIN, 20));
        add(label,gbc);

        gbc.gridx++;
        add(name, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        label = new JLabel("Size [m2]");
        label.setFont(new Font(getFont().getFontName(), Font.PLAIN, 20));
        add(label, gbc);

        gbc.gridx++;
        add(size, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        label = new JLabel("Description:");
        label.setFont(new Font(getFont().getFontName(), Font.PLAIN, 20));
        add(label, gbc);

        gbc.gridx++;
        add(description, gbc);

        gbc.gridwidth = 2;
        gbc.gridy++;
        gbc.gridx = 1;
        add(confirm, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(status, gbc);

        confirm.addActionListener(
                (ex)->{
                    try{
                        itm.addItem(new Item(name.getText(), Double.parseDouble(size.getText()), description.getText()));
                        status.setForeground(new Color(0,150,0));
                        status.setText("Item created successfully.");
                    } catch (NumberFormatException exc){
                        status.setForeground(Color.RED);
                        status.setText("Invalid data format.");
                    }
                    revalidate();
                }
        );
    }
}
