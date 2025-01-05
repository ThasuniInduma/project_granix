package fileManager;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import db.DBConnection;

import java.awt.Desktop;


public class FileHandle {
    //Name Constructor
    String FileName;
    public FileHandle (String FileName){
            this.FileName = FileName;
        }
    public void Action(){
        // File Path
        File file = new File(FileName);

        // Desktop Support Check
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                // Check if the file exists
                if (file.exists()) {
                    // Open the file 
                    desktop.open(file);
                } else {
                    System.out.println("File not found: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                System.out.println("An error occurred while opening the file: " + e.getMessage());
            }
        } else {
            System.out.println("Error.");
        }
    }

    public void DeleteFile(){
        try {
            // Sleep to make sure file is open before delete
            Thread.sleep(5000);
        } catch (InterruptedException e) {
             e.getMessage();
        }

        // Path
        File file = new File(FileName);
        // Attempt to delete the file
        if (file.delete()) {
            System.out.println("File deleted successfully: " + file.getAbsolutePath());
        } else {
            System.out.println("Failed to delete the file");
        }
    }

    public void WriteFile(List<String> selectedStocks) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Format the date as a string
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(dateFormatter);
        
        System.out.println("Formatted Date: " + formattedDate);
        
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        // Format the date and time as a string
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        
        System.out.println("Formatted Date and Time: " + formattedDateTime);
        
        // Write the file
        try (FileWriter writeFile = new FileWriter("Report.txt", true)) {
            // Write header for the report
            writeFile.write("Report Generated on: " + formattedDateTime + "\n\n");
        
            // Write table headers
            writeFile.write(String.format("%-15s%-20s%-15s%-15s%-15s\n", "Stock ID", "Stock Name", "Warehouse", "Price", "Quantity"));
            writeFile.write("--------------------------------------------------------------\n");
        
            // Check if there are any selected stocks and write them to the file
            if (selectedStocks != null && !selectedStocks.isEmpty()) {
                for (String stock : selectedStocks) {
                    // Get the stock ID by name
                    String stockId = getDetailsByName(stock);
        
                    // Check if the ID was found
                    if (stockId != null) {
                        // Fetch and write all details of the stock by its ID
                        String stockDetails = getStockDetailsById(stockId);
                        // Split the details by newline and write each part to the correct column
                        String[] details = stockDetails.split("\n");
                        writeFile.write(String.format("%-15s%-20s%-15s%-15s%-15s\n", stockId, stock, details[1], details[2], details[3]));
                    } else {
                        writeFile.write(String.format("%-15s%-20s%-15s%-15s%-15s\n", "N/A", stock, "N/A", "N/A", "N/A"));
                    }
                }
            } else {
                writeFile.write("No stocks selected.\n");
            }
        
            System.out.println("Report written successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getDetailsByName(String name) {
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
    
    public String getStockDetailsById(String stockId) {
        StringBuilder details = new StringBuilder();
        try {
            // Establish database connection
            Connection connection = DBConnection.getInstance().getConnection();
        
            // SQL query to get all details based on the Stock_ID
            String query = "SELECT Stock_name, Warehouse, PPU, Quantity FROM stock WHERE Stock_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        
            // Set the Stock_ID parameter
            preparedStatement.setString(1, stockId);
        
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();
        
            // Retrieve all details if a record is found
            if (resultSet.next()) {
                details.append(resultSet.getString("Stock_name")).append("\n");   // Stock name
                details.append(resultSet.getString("Warehouse")).append("\n");    // Warehouse
                details.append(resultSet.getDouble("PPU")).append("\n");          // Price per unit (PPU)
                details.append(resultSet.getInt("Quantity")).append("\n");        // Quantity
            } else {
                details.append("No details found for this stock.\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving stock details: " + e.getMessage());
        }
        return details.toString(); // Return all details as a string
    }
    
    
    
}


