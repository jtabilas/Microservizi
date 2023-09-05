CREATE TABLE `Account` (
    `IdAccount` INTEGER AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(255) NOT NULL,
    `Surname` VARCHAR(255) NOT NULL,
    `City` VARCHAR(255) NOT NULL,
    `Age` INT NOT NULL,
    `Username` VARCHAR(20) NOT NULL,
    `Email` VARCHAR(255) NOT NULL,
    `Password` VARCHAR(255) NOT NULL
);

CREATE TABLE `Product` (
    `IdProduct` INTEGER AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(255) NOT NULL,
    `Description` VARCHAR(255) NOT NULL,
    `Price` FLOAT NOT NULL,
    `Category` VARCHAR(255) NOT NULL,
    `CodeProduct` VARCHAR(20) NOT NULL,
    `Aviability` INT NOT NULL,
    `UserId` INTEGER,
    FOREIGN KEY (`UserId`) REFERENCES `Account` (`IdAccount`)
);

CREATE TABLE `Cart` (
    `IdCart` INTEGER AUTO_INCREMENT PRIMARY KEY,
    `QuantityProduct` INT NOT NULL,
    `ShippingPrice` FLOAT NOT NULL,
    `ProductId` INTEGER,
    FOREIGN KEY (`ProductId`) REFERENCES `Product` (`IdProduct`)
);

CREATE TABLE `Payment` (
    `IdPayment` INTEGER AUTO_INCREMENT PRIMARY KEY,
    `StatusPayment` BOOLEAN NOT NULL,
    `TypePayment` VARCHAR(255) NOT NULL,
    `TotalPrice` FLOAT NOT NULL,
    `DatePayment` DATE NOT NULL,
    `NumberCard` INT NOT NULL,
    `CartId` INTEGER,
    FOREIGN KEY (`CartId`) REFERENCES `Cart` (`IdCart`)
);

CREATE TABLE `Order` (
    `IdOrder` INTEGER AUTO_INCREMENT PRIMARY KEY,
    `StatusOrder` VARCHAR(255) NOT NULL,
    `CodeTracking` VARCHAR(255) NOT NULL,
    `ShippedDate` DATE NOT NULL,
    `PaymentId` INTEGER,
    `UserId` INTEGER,
    FOREIGN KEY (`PaymentId`) REFERENCES `Payment` (`IdPayment`),
    FOREIGN KEY (`UserId`) REFERENCES `Account` (`IdAccount`)
);
