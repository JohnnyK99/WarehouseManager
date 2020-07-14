import Others.MyButton;
import Others.Users;
import Others.NotEnoughSpaceException;
import Warehouses.Warehouse;
import Warehouses.WarehouseFullView;
import Warehouses.WarehouseList;
import Warehouses.WarehouseListModel;
import MainMenu.AddWarehousePanel;
import MainMenu.AddItemPanel;
import MainMenu.CreateItemPanel;
import Items.Item;
import Items.ItemTable;
import Items.ItemTableModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Window extends JFrame {

    Users users;
    ItemTableModel itemTableModel;
    WarehouseListModel warehouseListModel;

    public Window(Users users, WarehouseListModel mlm, ItemTableModel ptm){

        getContentPane().setBackground(Color.WHITE);

        this.users = users;
        this.itemTableModel = ptm;
        this.warehouseListModel = mlm;

        setTitle("Warehouse Manager");

        showLoginScreen();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showLoginScreen(){

        setSize(new Dimension(300,300));
        setLocationRelativeTo(null);

        JTextField login = new JTextField();
        JTextField haslo = new JTextField();
        MyButton button = new MyButton("Sign in");
        JLabel blad = new JLabel("Invalid login or password");

        button.setPreferredSize(new Dimension(70,30));
        login.setPreferredSize(new Dimension(150,25));
        haslo.setPreferredSize(new Dimension(150,25));

        blad.setForeground(Color.RED);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 0.6;
        gbc.weighty = 0.6;
        gbc.gridx = 1;
        gbc.gridy = 1;

        add(new JLabel("login:"), gbc);

        gbc.gridx++;
        add(login, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        add(new JLabel("password:"), gbc);

        gbc.gridx++;
        add(haslo, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(button, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        gbc.gridwidth = 2;

        button.addActionListener(
                (e)->{
                    if (users.getCredentials().containsKey(login.getText()) && users.getCredentials().get(login.getText()).equals(haslo.getText())){
                        getContentPane().removeAll();
                        showMenuGlowne();
                    }
                    else {
                        if(!blad.isDisplayable())
                            add(blad, gbc);
                        revalidate();
                    }
                }
        );
    }

    private void showMenuGlowne(){

        setSize(new Dimension(1000,600));
        setLocationRelativeTo(null);

        MyButton showWarehouses = new MyButton("Show all available warehouses");
        MyButton showItems = new MyButton("Show a list of all items");
        MyButton addWarehouse = new MyButton("Add new warehouse");
        MyButton createItem = new MyButton("Create new item");
        MyButton addItem = new MyButton("Add item to a warehouse");
        MyButton view = new MyButton("Show a view\n of all warehouses and items");
        MyButton save = new MyButton("Save");
        MyButton load = new MyButton("Load");

        BorderLayout myBorderLayout = new BorderLayout();
        setLayout(myBorderLayout);

        showWarehouses.addActionListener(
                (e)->{
                    if(myBorderLayout.getLayoutComponent(BorderLayout.CENTER) != null)
                        remove(myBorderLayout.getLayoutComponent(BorderLayout.CENTER));

                    JScrollPane scroll = new JScrollPane(new WarehouseList(warehouseListModel));
                    add(scroll, BorderLayout.CENTER);
                    revalidate();
                }
        );

        showItems.addActionListener(
                (e)->{
                    if(myBorderLayout.getLayoutComponent(BorderLayout.CENTER) != null)
                        remove(myBorderLayout.getLayoutComponent(BorderLayout.CENTER));

                    JScrollPane scroll = new JScrollPane(new ItemTable(itemTableModel));
                    add(scroll, BorderLayout.CENTER);

                    revalidate();
                }
        );

        addWarehouse.addActionListener(
                (e)->{
                    if(myBorderLayout.getLayoutComponent(BorderLayout.CENTER) != null)
                        remove(myBorderLayout.getLayoutComponent(BorderLayout.CENTER));

                    add(new AddWarehousePanel(warehouseListModel), BorderLayout.CENTER);

                    revalidate();
                }
        );

        createItem.addActionListener(
                (e)->{
                    if(myBorderLayout.getLayoutComponent(BorderLayout.CENTER) != null)
                        remove(myBorderLayout.getLayoutComponent(BorderLayout.CENTER));

                    add(new CreateItemPanel(itemTableModel), BorderLayout.CENTER);

                    revalidate();
                }
        );

        addItem.addActionListener(
                (e)-> {
                    if(myBorderLayout.getLayoutComponent(BorderLayout.CENTER) != null)
                        remove(myBorderLayout.getLayoutComponent(BorderLayout.CENTER));

                    add(new AddItemPanel(warehouseListModel, itemTableModel), BorderLayout.CENTER);

                    revalidate();
                }
        );

        view.addActionListener(
                (e)->{
                    if(myBorderLayout.getLayoutComponent(BorderLayout.CENTER) != null)
                        remove(myBorderLayout.getLayoutComponent(BorderLayout.CENTER));

                    JList<Warehouse> warehouses = new JList<>();
                    warehouses.setModel(warehouseListModel);

                    warehouses.setCellRenderer(new ListCellRenderer<>() {
                        WarehouseFullView mfv = new WarehouseFullView();
                        @Override
                        public Component getListCellRendererComponent(JList<? extends Warehouse> list, Warehouse value, int index, boolean isSelected, boolean cellHasFocus) {
                            mfv.setWarehouse(value);
                            return mfv;
                        }
                    });

                    JScrollPane scroll = new JScrollPane(warehouses);
                    add(scroll, BorderLayout.CENTER);
                    revalidate();
                }
        );

        save.addActionListener(
                (e)->{
                    if(myBorderLayout.getLayoutComponent(BorderLayout.CENTER) != null)
                        remove(myBorderLayout.getLayoutComponent(BorderLayout.CENTER));

                    JPanel panel = new JPanel();
                    JLabel name = new JLabel("Filename:");
                    JTextField nameField = new JTextField();
                    JLabel status = new JLabel();
                    MyButton confirm = new MyButton("Save");

                    nameField.setPreferredSize(new Dimension(150,25));
                    confirm.setPreferredSize(new Dimension(70,30));
                    name.setFont(new Font(getFont().getFontName(), Font.PLAIN, 20));

                    panel.setBackground(Color.WHITE);
                    panel.setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 1;
                    gbc.gridy = 1;
                    gbc.weightx = 0.5;
                    gbc.weighty = 0.5;
                    gbc.gridwidth = 2;

                    JLabel label = new JLabel("SAVE STATE OF APPLICATION");
                    label.setFont(new Font(getFont().getFontName(), Font.PLAIN, 30));
                    panel.add(label, gbc);

                    gbc.gridwidth = 1;
                    gbc.gridy++;
                    panel.add(name, gbc);

                    gbc.gridx++;
                    panel.add(nameField, gbc);

                    gbc.gridx = 1;
                    gbc.gridy++;
                    gbc.gridwidth = 2;
                    panel.add(confirm, gbc);

                    gbc.gridy++;
                    panel.add(status, gbc);

                    add(panel, BorderLayout.CENTER);
                    revalidate();

                    confirm.addActionListener(
                            (ex)->{
                                try{
                                    File appState = new File(nameField.getText());
                                    FileWriter fileWriter = new FileWriter(appState);

                                    for(Warehouse m : warehouseListModel.getWarehouses()){
                                        fileWriter.write(m.toFile());
                                    }

                                    for(Item p : itemTableModel.getItems()){
                                        fileWriter.write(p.toFile());
                                    }

                                    fileWriter.close();

                                    status.setForeground(new Color(0,150,0));
                                    status.setFont(new Font(getFont().getFontName(), Font.PLAIN, 20));
                                    status.setText("Saved successfully");

                                } catch (IOException exc) {
                                    status.setForeground(Color.RED);
                                    status.setFont(new Font(getFont().getFontName(), Font.PLAIN, 20));
                                    status.setText("State of the application could not be saved");
                                }
                            }
                    );
                }
        );

        load.addActionListener(
                (e)->{
                    if(myBorderLayout.getLayoutComponent(BorderLayout.CENTER) != null)
                        remove(myBorderLayout.getLayoutComponent(BorderLayout.CENTER));

                    JPanel panel = new JPanel();
                    JTextField path = new JTextField();
                    MyButton confirm = new MyButton("Load");
                    JLabel status = new JLabel();
                    status.setFont(new Font(getFont().getFontName(), Font.PLAIN, 20));

                    panel.setBackground(Color.white);

                    path.setPreferredSize(new Dimension(150,25));
                    confirm.setPreferredSize(new Dimension(70,30));

                    panel.setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 1;
                    gbc.gridy = 1;
                    gbc.gridwidth = 2;
                    gbc.weightx = 0.5;
                    gbc.weighty = 0.5;

                    JLabel label = new JLabel("LOAD STATE OF APPLICATION");
                    label.setFont(new Font(getFont().getFontName(), Font.PLAIN, 30));
                    panel.add(label, gbc);

                    gbc.gridwidth = 1;
                    gbc.gridy++;
                    label = new JLabel("Path:");
                    label.setFont(new Font(getFont().getFontName(), Font.PLAIN, 20));
                    panel.add(label, gbc);

                    gbc.gridx++;
                    panel.add(path, gbc);

                    gbc.gridy++;
                    gbc.gridx = 1;
                    gbc.gridwidth = 2;
                    panel.add(confirm, gbc);

                    gbc.gridy++;
                    panel.add(status, gbc);

                    confirm.addActionListener(
                            (ev)->{
                                try{
                                    Scanner in = new Scanner(new File(path.getText()));
                                    WarehouseListModel newmlm = new WarehouseListModel();
                                    ItemTableModel newptm = new ItemTableModel();
                                    while(in.hasNextLine()){
                                        String line = in.nextLine();

                                        String[] values = line.split("&");

                                        if(values.length == 3){
                                            newmlm.addWarehouse(new Warehouse(Integer.parseInt(values[0]), Double.parseDouble(values[1]), values[2]));
                                        }
                                        else{
                                            Item tmp = new Item(Integer.parseInt(values[0]), values[1], Double.parseDouble(values[2]), values[3], Integer.parseInt(values[4]));
                                            newptm.addItem(tmp);
                                            for(Warehouse m : newmlm.getWarehouses()){
                                                if(m.getId() == tmp.getWarehouseId())
                                                    m.addItem(tmp);
                                            }
                                        }
                                    }

                                    this.itemTableModel = newptm;
                                    this.warehouseListModel = newmlm;

                                    status.setForeground(new Color(0,150,0));
                                    status.setText("Loaded successfully");
                                    revalidate();

                                } catch (FileNotFoundException | NotEnoughSpaceException ex ){
                                    status.setForeground(Color.RED);
                                    status.setText("State of the application could not be loaded");
                                    revalidate();
                                }
                            }
                    );
                    add(panel, BorderLayout.CENTER);
                    revalidate();
                }
        );



        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridy = 0;
        panel.add(showWarehouses,gbc);

        gbc.gridy++;
        panel.add(showItems, gbc);

        gbc.gridy++;
        panel.add(addWarehouse, gbc);

        gbc.gridy++;
        panel.add(createItem, gbc);

        gbc.gridy++;
        panel.add(addItem, gbc);

        gbc.gridy++;
        panel.add(view, gbc);

        gbc.gridy++;
        panel.add(save, gbc);

        gbc.gridy++;
        panel.add(load, gbc);

        add(panel, BorderLayout.WEST);

        revalidate();
    }
}
