/* Use the Created Database */
USE Grain_Management_System;

/* Insert Data into Warehouse */
INSERT INTO warehouse (Warehouse_ID, Warehouse_name, Max_Capacity, Location, Warehouse_Telephone, Sector)
VALUES 
('W001', 'Kandy_Warehouse', 1000, 'City Center', '0769823541', 'Private Sector'),
('W002', 'Colombo_Warehouse', 800, 'Eastside', '0774953684', 'Government Sector'),
('W003', 'Jaffna_Warehouse', 600, 'Westside', '0768324951', 'Private Sector');

/* Insert Data into Employee */
INSERT INTO employee (Employee_ID, First_name, Second_name, User_Password, Telephone, Warehouse_ID)
VALUES 
('E001', 'Nimal', 'Perera', 'pass123', '0123456789', 'W001'),
('E002', 'Kamal', 'Amarasinghe', 'pass456', '0123456789', 'W002'),
('E003', 'Sumal', 'Rathnayaka', 'pass789', '0123456789', 'W003');

/* Insert Data into Stock */
INSERT INTO stock (Stock_ID, Stock_name, Quantity, PPU, Sector)
VALUES 
('S001', 'Suwadel', 500, 100.50, 'Private Sector'),
('S002', 'Kuruluthuda', 300, 120.75,'Private Sector'),
('S003', 'Samba', 200, 90.00, 'Government Sector');

/* Insert Data into Buyer */
INSERT INTO buyer (Buyer_ID, Buyer_name, Buyer_Password, Buyer_Telephone)
VALUES 
('B001', 'ABC Traders', 'abc123', '0712548652'),
('B002', 'XYZ Wholesalers', 'xyz456', '0776583295'),
('B003', 'PQR Retailers', 'pqr789', '0758965315');

/* Insert Data into Stock Store (Relationship Between Stock and Warehouse) */
INSERT INTO stock_store (Stock_ID, Warehouse_ID)
VALUES 
('S001', 'W001'),
('S002', 'W002'),
('S003', 'W003');

/* Insert Data into Stock Obtain (Relationship Between Stock and Buyer) */
INSERT INTO stock_obtain (Stock_ID, Buyer_ID, Quantity_obtained, Obtain_Date)
VALUES 
('S001', 'B001', 100, '2024-11-01'),
('S002', 'B002', 150, '2024-11-05'),
('S003', 'B003', 50, '2024-11-10');