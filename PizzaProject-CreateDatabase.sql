UNLOCK TABLES;
DROP DATABASE IF EXISTS JavaPizzaDB;
CREATE DATABASE IF NOT EXISTS JavaPizzaDB;
USE JavaPizzaDB;

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
  employee_id int NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (employee_id)
);
INSERT INTO employee VALUES (1,'admin','12345');


DROP TABLE IF EXISTS pizzaCrust;
CREATE TABLE pizzaCrust (
  pizzaCrust_id int NOT NULL AUTO_INCREMENT,
  name varchar(16) NOT NULL,
  price float NOT NULL DEFAULT '0',
  PRIMARY KEY (pizzaCrust_id)
);

INSERT INTO pizzaCrust VALUES 
	(1,'Thin Crust', 0.00),
	(2,'Handmade Pan', 1.00),
	(3,'Original', 0.00),
	(4,'Gluten', 2.00),
	(5,'Chicago Style', 2.00);


DROP TABLE IF EXISTS pizzaSize;
CREATE TABLE pizzaSize (
  pizzaSize_id int NOT NULL AUTO_INCREMENT,
  name varchar(16) NOT NULL,
  price float NOT NULL DEFAULT '0',
  PRIMARY KEY (pizzaSize_id)
);

INSERT INTO pizzaSize VALUES 
	(1,'Small', 6.00),
	(2,'Medium', 9.00),
	(3,'Large', 12.00),
	(4,'X-Large', 16.00);


DROP TABLE IF EXISTS pizzaTopping;
CREATE TABLE pizzaTopping (
  pizzaTopping_id int NOT NULL AUTO_INCREMENT,
  name varchar(32) NOT NULL,
  price float NOT NULL DEFAULT '0',
  createdate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  empAddedBy int NOT NULL,
  isActive tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (pizzaTopping_id),
  KEY FK_employee (empAddedBy),
  FOREIGN KEY (empAddedBy) REFERENCES employee (employee_id)
);

INSERT INTO pizzaTopping VALUES 
	(5,'cheese',0.00,'2017-11-04 23:35:40',1,1),
	(6,'extra cheese',1.99,'2017-11-04 23:35:40',1,1),
	(7,'pepperoni',1.99,'2017-11-04 23:35:40',1,1),
	(8,'green peppers',1.99,'2017-11-04 23:35:40',1,1);


DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
  customer_id int NOT NULL AUTO_INCREMENT,
  firstName varchar(45) NOT NULL,
  lastName varchar(45) NOT NULL,
  phoneNumber varchar(45) NOT NULL,
  email varchar(45) NOT NULL,
  houseNumber int DEFAULT NULL,
  street varchar(45) DEFAULT NULL,
  province varchar(2) DEFAULT NULL,
  postalCode varchar(7) DEFAULT NULL,
  PRIMARY KEY (customer_id)
);

INSERT INTO Customer VALUES 
	(1,'Dave','Thomas','123','dave@thomas.com',123,'main','NB','A1A1A1');

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  order_id int NOT NULL AUTO_INCREMENT,
  customer_id int NOT NULL,
  subTotal float NOT NULL DEFAULT '0',
  hst float NOT NULL DEFAULT '0',
  orderTotal float NOT NULL DEFAULT '0',
  orderStatus varchar(12) NOT NULL DEFAULT 'PENDING',
  deliveryDate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  orderPlacedDate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (order_id),
  KEY FK_customer (customer_id),
  FOREIGN KEY (customer_id) REFERENCES customer (customer_id) 
);

INSERT INTO Orders (order_id,customer_id) VALUES (1,1);

DROP TABLE IF EXISTS pizza;
CREATE TABLE pizza (
  pizza_id int NOT NULL AUTO_INCREMENT,
  order_id int NOT NULL,
  pizzaSize_id int NOT NULL,
  pizzaCrust_id int NOT NULL,
  quantity int NOT NULL default '1',
  priceEach float NOT NULL,
  totalPrice float NOT NULL,
  PRIMARY KEY (pizza_id),
  KEY FK_order (order_id),
  FOREIGN KEY (pizzaCrust_id) REFERENCES pizzaCrust (pizzaCrust_id),
  FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
  FOREIGN KEY (pizzaSize_id) REFERENCES pizzaSize (pizzaSize_id) 
);


DROP TABLE IF EXISTS pizzaTopping_map;
CREATE TABLE pizzaTopping_map (
  pizzaTopping_map_id int NOT NULL AUTO_INCREMENT,
  pizza_id int NOT NULL,
  pizzaTopping_id int NOT NULL,
  PRIMARY KEY (pizzaTopping_map_id),
  KEY FK_pizza (pizza_id),
  FOREIGN KEY (pizza_id) REFERENCES pizza (pizza_id) ON DELETE CASCADE,
  FOREIGN KEY (pizzaTopping_id) REFERENCES pizzaTopping (pizzaTopping_id)
);

-- we want to easily test how database errors are handled, 
-- so, we add a trigger to make XL pizzas ILLEGAL :)
--
DROP TRIGGER IF EXISTS t_PizzaInsert;
DELIMITER $$
CREATE TRIGGER t_PizzaInsert 
	BEFORE INSERT 
	ON Pizza 
	FOR EACH ROW
BEGIN
  IF NEW.pizzaSize_id = 4 THEN 
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Sorry, XL Pizza NOT available';
  END IF;
END 
$$
DELIMITER ;

DELETE FROM Pizza WHERE pizza_id in (1,2);
INSERT INTO Pizza (pizza_id, order_id, pizzaSize_id, pizzaCrust_id,priceEach,totalPrice) VALUES (1,1,1,1,9.99,9.99);
--INSERT INTO Pizza (pizza_id, order_id, pizzaSize_id, pizzaCrust_id,priceEach,totalPrice) VALUES (2,1,4,1,9.99,9.99);

-- DROP USER IF EXISTS JavaApp;
CREATE USER IF NOT EXISTS JavaApp IDENTIFIED BY 'JavaApp';
GRANT ALL ON JavaPizzaDB.* TO JavaApp;

FLUSH PRIVILEGES;