DROP DATABASE IF EXISTS FWRP;
CREATE DATABASE FWRP;
USE FWRP;

CREATE TABLE User (
    userId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    userType ENUM('RETAILER', 'CONSUMER', 'CHARITABLE_ORGANIZATION') NOT NULL,
    contactInfo VARCHAR(50) NOT NULL,
    location VARCHAR(50) NOT NULL
);

CREATE TABLE InventoryItem (
    itemId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    itemName VARCHAR(50) NOT NULL,
    itemDescription VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    expirationDate DATE NOT NULL,
    surplus BOOLEAN NOT NULL,
    forDonation BOOLEAN NOT NULL,
    storeId INT NOT NULL,
    FOREIGN KEY (storeId) REFERENCES User(userId)
);

CREATE TABLE notifications (
    notificationId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userId INT,
    surplusFoodId INT,
    notificationMessage VARCHAR(75) NOT NULL,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE subscriptions (
    userEmail VARCHAR(50) NOT NULL,
    storeId INT NOT NULL,
    FOREIGN KEY (userEmail) references User(email),
    FOREIGN KEY (storeId) references User(userId),
)
