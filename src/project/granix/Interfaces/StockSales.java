package Interfaces;

//Imports
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//Java Class Imports
import Interfaces.StockSales;
import controller.salesController;
import controller.stockController;
import db.DBConnection;
import dto.salesdto;
import dto.stockDto;
import dto.warehouseDto;

public class StockSales extends JFrame{

    private JComboBox<String> dropdown;
    private JComboBox<String> dropdown2;
    private JTextField BuyerIDTextBox;
    private JTextField StockQuantityTextBox;
    
    private JTable viewTable;
    private DefaultTableModel dtm;

    private salesController salesController;

    public StockSales(){

        salesController = new salesController();

        //JFrame Definitions
        setTitle("Grain Store Managment System"); //Title Changed
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.white);

        //JFrame Logo
        ImageIcon iconImage = new ImageIcon(getClass().getResource("assets/icon.png"));
        Image image = iconImage.getImage();
        setIconImage(image);

        //JFrame Background Image
        ImageIcon backgroundImageSet = new ImageIcon(getClass().getResource("Assets/sideView.png"));
        Image imageSet = backgroundImageSet.getImage();
        Image resizedImage = imageSet.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
        ImageIcon backgroundImage = new ImageIcon(resizedImage);
        JLabel backgroundImageSetter = new JLabel(backgroundImage);
        backgroundImageSetter.setBounds(100, 0, 1000, 700);

        //JFrame Logo Image
        ImageIcon logoImageSet = new ImageIcon(getClass().getResource("Assets/Logo.png"));
        Image logoSet = logoImageSet.getImage();
        Image resizedLogoImage = logoSet.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        ImageIcon logoImage = new ImageIcon(resizedLogoImage);
        JLabel logoImageSetter = new JLabel(logoImage);
        logoImageSetter.setBounds(40, 0, 200, 100);

        //JPanel for menu Box
        JPanel menuBox = new JPanel();
        menuBox.setBounds(10, 10, 250, 640);
        menuBox.setBackground(Color.WHITE);

        //JPanel for Body Box
        JPanel bodyBox = new JPanel();
        bodyBox.setBounds(260, 10, 710, 640);
        //bodyBox.setBackground(new Color(237, 235, 235));
        bodyBox.setBackground(Color.WHITE);

        //JPanel for Title Box
        JPanel titleBox = new JPanel();
        titleBox.setBounds(260, 0, 740, 75);
        titleBox.setBackground(new Color(172, 145, 127));

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        //JLabel For Interface Title
        JLabel titleLabel = new JLabel("Stock Sales");
        titleLabel.setBounds(310, 15, 400, 50);
        titleLabel.setBackground(new Color(237, 235, 235));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));

        //Buttons defined for DashBoard
        JButton dashBoardButton = new JButton("Dashboard");
        dashBoardButton.setBounds(10, 140, 250, 50);
        dashBoardButton.setBackground(Color.WHITE);
        dashBoardButton.setForeground(new Color(27, 17, 14));
        dashBoardButton.setFont(new Font("Arial", Font.BOLD, 20));
        dashBoardButton.setBorder(border);

        //Buttons defined for Stock Availability
        JButton availabilityButton = new JButton("Stock Availability");
        availabilityButton.setBounds(10, 220, 200, 50);
        availabilityButton.setBackground(new Color(237, 235, 235));
        availabilityButton.setForeground(Color.BLACK);
        availabilityButton.setFont(new Font("Arial", Font.BOLD, 20));
        availabilityButton.setBorder(border);

        //Buttons defined for Report Generation
        JButton reportButton = new JButton("Report");
        reportButton.setBounds(10, 300, 200, 50);
        reportButton.setBackground(new Color(237, 235, 235));
        reportButton.setForeground(Color.BLACK);
        reportButton.setFont(new Font("Arial", Font.BOLD, 20));
        reportButton.setBorder(border);

        //Buttons defined for manage Stocks
        JButton manageStocksButton = new JButton("Manage Stocks");
        manageStocksButton.setBounds(10, 380, 200, 50);
        manageStocksButton.setBackground(new Color(237, 235, 235));
        manageStocksButton.setForeground(Color.BLACK);
        manageStocksButton.setFont(new Font("Arial", Font.BOLD, 20));
        manageStocksButton.setBorder(border);

        //Buttons defined for manage Warehouses
        JButton manageWarehouseButton = new JButton("Manage Warehouses");
        manageWarehouseButton.setBounds(10, 460, 200, 50);
        manageWarehouseButton.setBackground(new Color(237, 235, 235));
        manageWarehouseButton.setForeground(Color.BLACK);
        manageWarehouseButton.setFont(new Font("Arial", Font.BOLD, 20));
        manageWarehouseButton.setBorder(border);

         //Buttons defined for Logout
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(10, 570, 200, 50);
        logoutButton.setBackground(new Color(237, 235, 235));
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 20));
        logoutButton.setBorder(border);

        //Select Crop Name
        dropdown = new JComboBox<>();
        dropdown.setBounds(360, 160, 240, 40);
        dropdown.setFont(new Font("Arial", Font.ITALIC, 20));
        dropdown.setBackground(Color.WHITE);

        //Select Warehouse Name
        dropdown2 = new JComboBox<>();
        dropdown2.setBounds(620, 220, 240, 40);
        dropdown2.setFont(new Font("Arial", Font.ITALIC, 20));
        dropdown2.setBackground(Color.WHITE);

        //TextBox defined for StockId
        BuyerIDTextBox = new JTextField("Enter Buyer ID");
        BuyerIDTextBox.setBounds(620, 160, 240, 40);
        BuyerIDTextBox.setFont(new Font("Arial", Font.ITALIC, 20));

        //Add Item Button
        JButton addStockButton = new JButton("Add to Order");
        addStockButton.setBounds(360, 300, 240, 50);
        addStockButton.setBackground(new Color(237, 235, 235));
        addStockButton.setForeground(Color.BLACK);
        addStockButton.setFont(new Font("Arial", Font.BOLD, 20));
        addStockButton.setBorder(border);

        //Add Item Button
        JButton updateStockButton = new JButton("Update Order");
        updateStockButton.setBounds(620, 300, 240, 50);
        updateStockButton.setBackground(new Color(237, 235, 235));
        updateStockButton.setForeground(Color.BLACK);
        updateStockButton.setFont(new Font("Arial", Font.BOLD, 20));
        updateStockButton.setBorder(border);

        //TextBox defined for StockQuantity
        StockQuantityTextBox = new JTextField("Enter Stock Quantity");
        StockQuantityTextBox.setBounds(360, 220, 240, 40);
        StockQuantityTextBox.setFont(new Font("Arial", Font.ITALIC, 20));

        

        // Create a table model
        dtm = new DefaultTableModel();

        // Create a JTable using the model
        viewTable = new JTable(dtm);

        //Table Appearance Customizations
        viewTable.setFont(new Font("Arial",Font.PLAIN, 14));
        viewTable.setRowHeight(30);
        viewTable.setBackground(new Color(237, 235, 235));
        viewTable.setForeground(Color.BLACK);
        viewTable.setGridColor(Color.DARK_GRAY);
        viewTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        viewTable.getTableHeader().setBackground(new Color(172, 145, 127));
        viewTable.getTableHeader().setForeground(Color.WHITE);

    
        
        // Add the table to a JScrollPane for scroll functionality
        JScrollPane scrollPane = new JScrollPane(viewTable);
        scrollPane.setBounds(300, 390, 600, 150);
        scrollPane.setBackground(new Color(237, 235, 235));
        scrollPane.getViewport().setBackground(new Color(237, 235, 235));
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        

        //Event actions defined for Dashboard Button
        dashBoardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
                new DashboardView().setVisible(true);
            }
        });

        //Event actions defined for StockAvailabitity Button
        availabilityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
                new StockAvailability().setVisible(true);
            }
        });

        //Event actions defined for Reports Button
        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
                new ReportGenerate().setVisible(true);
            }
        });

        //Event actions defined for manage Stocks Button
        manageStocksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
                new ManageStocks().setVisible(true);
            }
        });

        //Event actions defined for manage Warehouse Button
        manageWarehouseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
                new ManageWarehouse().setVisible(true);
            }
        });

        //Event actions defined for Logout Button
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
                new HomeScreen().setVisible(true);
            }
        });
        StockQuantityTextBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Add Focus
                StockQuantityTextBox.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Lose Focus
                if (StockQuantityTextBox.getText().isEmpty()) {
                    StockQuantityTextBox.setText("Enter Stock Quantity");
                }
            }
        });
        BuyerIDTextBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Add Focus
                BuyerIDTextBox.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Lose Focus
                if (BuyerIDTextBox.getText().isEmpty()) {
                    BuyerIDTextBox.setText("Enter Buyer ID");
                }
            }
        });

        addStockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //addToOrder();
            }
        });
        updateStockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //updateOrder();
            }
        });

        
        //Add Elements to the Frame
        add(logoImageSetter);
        add(titleLabel);
        add(dashBoardButton);
        add(availabilityButton);
        add(reportButton);
        add(manageStocksButton);
        add(manageWarehouseButton);
        add(logoutButton);
        add(dropdown);
        add(dropdown2);
        add(addStockButton);
        add(updateStockButton);

        add(StockQuantityTextBox);
        add(BuyerIDTextBox);
        add(scrollPane);
        add(titleBox);
        add(menuBox);
        //add(bodyBox);
        add(backgroundImageSetter);

        loadStocks();
        loadWarehouse();
        loadOrders();
    } 

            private void loadStocks() {
                dropdown.removeAllItems();
                try {
                    String query = "SELECT Stock_name FROM stock";
                    Connection connection = DBConnection.getInstance().getConnection();
                    PreparedStatement pst = connection.prepareStatement(query);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        dropdown.addItem(rs.getString("Stock_name")); // Add each type
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            private void loadWarehouse() {
                dropdown2.removeAllItems();
                try {
                    String query = "SELECT Warehouse_name FROM warehouse";
                    Connection connection = DBConnection.getInstance().getConnection();
                    PreparedStatement pst = connection.prepareStatement(query);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        dropdown2.addItem(rs.getString("Warehouse_name")); // Add each type
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            private void loadOrders() {
                try {
                    String[] columns = {"Stock_ID", "Quantity"};
                    DefaultTableModel dtm = new DefaultTableModel(columns, 0) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    viewTable.setModel(dtm);
            
                    ArrayList<salesdto> sales = salesController.getAllsales();
                    for (salesdto sale : sales) {
                        Object[] rowData = {
                            sale.getStock_ID(),
                            sale.getQuantity_obtained()
                            
                        };
                        dtm.addRow(rowData);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(AddNewStocks.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
            private void addToOrder() throws Exception {
                try {
                    //salesdto salesdto = new salesdto(getIdByName(dropdown.getSelectedItem().toString()),BuyerIDTextBox.getText(),Double.parseDouble(StockQuantityTextBox.getText()),dropdown2.getSelectedItem().toString());
                    
                    //String result = salesController.addsales(salesdto);
                    //JOptionPane.showConfirmDialog(this, result);
                    Clear();
                    loadOrders();
                    } catch (Exception ex) {
                        Logger.getLogger(AddNewWarehouses.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
            }
            public String getIdByName(String name) {
                String id = null;
                try {
                    // Establish database connection
                    Connection connection = DBConnection.getInstance().getConnection();
            
                    // SQL query to get the ID based on the name
                    String query = "SELECT Stock_ID FROM stock WHERE Stock_name = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
            
                    // Set the name parameter
                    preparedStatement.setString(1, name);
            
                    // Execute the query
                    ResultSet resultSet = preparedStatement.executeQuery();
            
                    // Retrieve the ID if a record is found
                    if (resultSet.next()) {
                        id = resultSet.getString("Stock_ID");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error retrieving ID by name: " + e.getMessage());
                }
                return id; // Returns null if no record is found
            }
            private void Clear() {
                BuyerIDTextBox.setText("");
                StockQuantityTextBox.setText("");
                dropdown.setSelectedItem("Please Select");
                dropdown2.setSelectedItem("Please Select");;

            }
}


