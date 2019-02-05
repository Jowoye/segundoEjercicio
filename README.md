# segundoEjercicio

Database setup:

1- In mysql create a schema with the name of "supermarket".

2- Create the tables with the following scripts: 
CREATE TABLE `product` ( 
`idproduct` int(11) NOT NULL AUTO_INCREMENT,   
`productName` varchar(45) DEFAULT NULL, 
`productPrice` int(11) NOT NULL, 
`productQuantity` int(11) DEFAULT NULL, 
PRIMARY KEY (`idproduct`) ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `purchase` (
   `idPurchase` int(11) NOT NULL AUTO_INCREMENT,
   `purchaseProductId` int(11) NOT NULL,
   `purchaseProductQuantity` int(11) NOT NULL,
   PRIMARY KEY (`idPurchase`),
   KEY `purchaseProductId_idx` (`purchaseProductId`),
   CONSTRAINT `purchaseProductId` FOREIGN KEY (`purchaseProductId`) REFERENCES `product` (`idproduct`)
 ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 3- In the application find the file service.java and change the user and password with your credentials
 
 4- Run the application.
 
 
