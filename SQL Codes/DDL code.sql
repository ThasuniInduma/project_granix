/* Create Database */
CREATE DATABASE Grain_Management_System;

/* Use the Created Database */
USE Grain_Management_System;

/* Create Warehouse Table */
CREATE TABLE warehouse (
    Warehouse_ID CHAR(5) NOT NULL PRIMARY KEY,
    Warehouse_name VARCHAR(45) NOT NULL,
    Max_Capacity DECIMAL(10, 2) NOT NULL,
    Location VARCHAR(45) NOT NULL,
    Warehouse_Telephone VARCHAR(15) NOT NULL

);

/* Create Employee Table */
CREATE TABLE employee (
    Employee_ID CHAR(5) NOT NULL PRIMARY KEY,
    First_name VARCHAR(30) NOT NULL,
    Second_name VARCHAR(30) NOT NULL,
    User_Password VARCHAR(10) NOT NULL,
    Warehouse_ID CHAR(5) NOT NULL,
    Telephone CHAR(10) NOT NULL,
    FOREIGN KEY (Warehouse_ID) REFERENCES warehouse(Warehouse_ID)
);


/* Create Stock Table */
CREATE TABLE stock (
    Stock_ID CHAR(5) NOT NULL PRIMARY KEY,
    Stock_name VARCHAR(45) NOT NULL,
    Quantity DECIMAL(10, 2) DEFAULT 0,
    PPU DECIMAL(10, 2) DEFAULT 0,
    Sector varchar(45) Not null
);

/* Create Buyer Table */
CREATE TABLE buyer (
    Buyer_ID CHAR(5) NOT NULL PRIMARY KEY,
    Buyer_name VARCHAR(45) NOT NULL,
    Buyer_Password VARCHAR(10) NOT NULL,
    Buyer_Telephone CHAR(10) NOT NULL,
);



/* Table for Relationship Between Stock and Warehouse (Many-to-Many) */
CREATE TABLE stock_store (
    Stock_ID CHAR(5) NOT NULL,
    Warehouse_ID CHAR(5) NOT NULL,
    PRIMARY KEY (Stock_ID, Warehouse_ID),
    FOREIGN KEY (Stock_ID) REFERENCES stock(Stock_ID),
    FOREIGN KEY (Warehouse_ID) REFERENCES warehouse(Warehouse_ID)
);

/* Table for Relationship Between Stock and Buyer (Many-to-Many) */
CREATE TABLE stock_obtain (
    Stock_ID CHAR(5) NOT NULL,
    Buyer_ID CHAR(5) NOT NULL,
    Quantity_obtained INT NOT NULL,
    Obtain_Date DATE NOT NULL,
    PRIMARY KEY (Stock_ID, Buyer_ID),
    FOREIGN KEY (Stock_ID) REFERENCES stock(Stock_ID),
    FOREIGN KEY (Buyer_ID) REFERENCES buyer(Buyer_ID)
);
