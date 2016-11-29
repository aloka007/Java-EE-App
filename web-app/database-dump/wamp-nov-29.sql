-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.17 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for smart_rms
CREATE DATABASE IF NOT EXISTS `smart_rms` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `smart_rms`;


-- Dumping structure for table smart_rms.bill
CREATE TABLE IF NOT EXISTS `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `waiter_id` varchar(50) DEFAULT NULL,
  `cashier_id` varchar(50) DEFAULT NULL,
  `customer_name` varchar(50) DEFAULT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` decimal(10,2) DEFAULT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `tax` decimal(10,2) DEFAULT NULL,
  `sub_total` decimal(10,2) DEFAULT NULL,
  `tip` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.bill: ~1 rows (approximately)
DELETE FROM `bill`;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` (`id`, `waiter_id`, `cashier_id`, `customer_name`, `date`, `amount`, `discount`, `tax`, `sub_total`, `tip`, `total`) VALUES
	(9, '7', 'null', 'Tharinda', '2016-11-27 14:33:00', 7400.00, 0.00, 1110.00, 8510.00, 200.00, 8710.00),
	(13, NULL, NULL, NULL, '2016-11-02 12:00:00', NULL, NULL, NULL, 100000.00, NULL, NULL),
	(14, NULL, NULL, NULL, '2016-11-04 10:52:35', NULL, NULL, NULL, 90000.00, NULL, NULL),
	(15, NULL, NULL, NULL, '2016-11-01 12:00:00', NULL, NULL, NULL, 84323.00, NULL, NULL),
	(16, NULL, NULL, NULL, '2016-11-02 12:00:00', NULL, NULL, NULL, 98656.00, NULL, NULL),
	(17, NULL, NULL, NULL, '2016-11-03 12:00:00', NULL, NULL, NULL, 131594.00, NULL, NULL),
	(18, NULL, NULL, NULL, '2016-11-04 12:00:00', NULL, NULL, NULL, 88407.00, NULL, NULL),
	(19, NULL, NULL, NULL, '2016-11-05 12:00:00', NULL, NULL, NULL, 183769.00, NULL, NULL),
	(20, NULL, NULL, NULL, '2016-11-06 12:00:00', NULL, NULL, NULL, 139895.00, NULL, NULL),
	(21, NULL, NULL, NULL, '2016-11-07 12:00:00', NULL, NULL, NULL, 190704.00, NULL, NULL),
	(22, NULL, NULL, NULL, '2016-11-08 12:00:00', NULL, NULL, NULL, 115363.00, NULL, NULL),
	(23, NULL, NULL, NULL, '2016-11-09 12:00:00', NULL, NULL, NULL, 187720.00, NULL, NULL),
	(24, NULL, NULL, NULL, '2016-11-10 12:00:00', NULL, NULL, NULL, 207098.00, NULL, NULL),
	(25, NULL, NULL, NULL, '2016-11-11 12:00:00', NULL, NULL, NULL, 150828.00, NULL, NULL),
	(26, NULL, NULL, NULL, '2016-11-12 12:00:00', NULL, NULL, NULL, 125694.00, NULL, NULL),
	(27, NULL, NULL, NULL, '2016-11-13 12:00:00', NULL, NULL, NULL, 187355.00, NULL, NULL),
	(28, NULL, NULL, NULL, '2016-11-14 12:00:00', NULL, NULL, NULL, 81507.00, NULL, NULL),
	(29, NULL, NULL, NULL, '2016-11-15 12:00:00', NULL, NULL, NULL, 191615.00, NULL, NULL),
	(30, NULL, NULL, NULL, '2016-11-16 12:00:00', NULL, NULL, NULL, 119957.00, NULL, NULL),
	(31, NULL, NULL, NULL, '2016-11-17 12:00:00', NULL, NULL, NULL, 103673.00, NULL, NULL),
	(32, NULL, NULL, NULL, '2016-11-18 12:00:00', NULL, NULL, NULL, 195849.00, NULL, NULL),
	(33, NULL, NULL, NULL, '2016-11-19 12:00:00', NULL, NULL, NULL, 168249.00, NULL, NULL),
	(34, NULL, NULL, NULL, '2016-11-20 12:00:00', NULL, NULL, NULL, 89139.00, NULL, NULL),
	(35, NULL, NULL, NULL, '2016-11-21 12:00:00', NULL, NULL, NULL, 153984.00, NULL, NULL),
	(36, NULL, NULL, NULL, '2016-11-22 12:00:00', NULL, NULL, NULL, 178569.00, NULL, NULL),
	(37, NULL, NULL, NULL, '2016-11-23 12:00:00', NULL, NULL, NULL, 196821.00, NULL, NULL),
	(38, NULL, NULL, NULL, '2016-11-24 12:00:00', NULL, NULL, NULL, 117044.00, NULL, NULL),
	(39, NULL, NULL, NULL, '2016-11-25 12:00:00', NULL, NULL, NULL, 102034.00, NULL, NULL),
	(40, NULL, NULL, NULL, '2016-11-26 12:00:00', NULL, NULL, NULL, 100521.00, NULL, NULL),
	(41, NULL, NULL, NULL, '2016-11-27 12:00:00', NULL, NULL, NULL, 112242.00, NULL, NULL),
	(42, NULL, NULL, NULL, '2016-11-28 12:00:00', NULL, NULL, NULL, 170054.00, NULL, NULL),
	(43, NULL, NULL, NULL, '2016-11-29 12:00:00', NULL, NULL, NULL, 133571.00, NULL, NULL);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;


-- Dumping structure for table smart_rms.bill_order
CREATE TABLE IF NOT EXISTS `bill_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_id` int(11) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bill_order_bill` (`bill_id`),
  CONSTRAINT `FK_bill_order_bill` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.bill_order: ~3 rows (approximately)
DELETE FROM `bill_order`;
/*!40000 ALTER TABLE `bill_order` DISABLE KEYS */;
INSERT INTO `bill_order` (`id`, `bill_id`, `order_no`, `total`) VALUES
	(3, 9, 1105, NULL),
	(4, 9, 1077, NULL),
	(5, 9, 1081, NULL);
/*!40000 ALTER TABLE `bill_order` ENABLE KEYS */;


-- Dumping structure for table smart_rms.bill_order_item
CREATE TABLE IF NOT EXISTS `bill_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_order_id` int(11) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bill_order_item_bill_order` (`bill_order_id`),
  CONSTRAINT `FK_bill_order_item_bill_order` FOREIGN KEY (`bill_order_id`) REFERENCES `bill_order` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.bill_order_item: ~6 rows (approximately)
DELETE FROM `bill_order_item`;
/*!40000 ALTER TABLE `bill_order_item` DISABLE KEYS */;
INSERT INTO `bill_order_item` (`id`, `bill_order_id`, `item_name`, `quantity`, `price`, `amount`) VALUES
	(7, 3, 'Butter Chicken', 1, 950.00, 950.00),
	(8, 3, 'Cream of Vegetable', 2, 350.00, 700.00),
	(9, 4, 'Minestrone Soup', 1, 800.00, 800.00),
	(10, 4, 'Fettuccine Alfredo', 2, 1200.00, 2400.00),
	(11, 5, 'Butter Chicken', 1, 950.00, 950.00),
	(12, 5, 'Minestrone Soup', 2, 800.00, 1600.00);
/*!40000 ALTER TABLE `bill_order_item` ENABLE KEYS */;


-- Dumping structure for table smart_rms.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `contact_no` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.customer: ~17 rows (approximately)
DELETE FROM `customer`;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customer_id`, `name`, `password`, `birthday`, `email`, `contact_no`) VALUES
	(0, 'kek', 'kek', '2016-11-06', 'kek', 'kek'),
	(1, 'kek', 'kek', '3894-01-19', 'kek', 'kek'),
	(2, 'kek', 'kek', '3894-01-19', 'kek', 'kek'),
	(4, 'Tharinda', NULL, NULL, NULL, NULL),
	(5, 'John Smith', NULL, NULL, NULL, NULL),
	(6, 'Hello', NULL, NULL, NULL, NULL),
	(7, 'hello2', NULL, NULL, NULL, NULL),
	(8, 'hello3', NULL, NULL, NULL, NULL),
	(9, 'hello4', NULL, NULL, NULL, NULL),
	(10, 'hello5', NULL, NULL, NULL, NULL),
	(11, 'Hello6', NULL, NULL, NULL, NULL),
	(12, 'Tharinda', NULL, NULL, NULL, NULL),
	(13, 'Tharinda', NULL, NULL, NULL, NULL),
	(14, 'haha', NULL, NULL, NULL, NULL),
	(15, 'test', NULL, NULL, NULL, NULL),
	(16, 'hehe', NULL, NULL, NULL, NULL),
	(17, 'lols', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


-- Dumping structure for table smart_rms.customer_order
CREATE TABLE IF NOT EXISTS `customer_order` (
  `order_no` int(11) NOT NULL AUTO_INCREMENT,
  `order_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `table_no` int(11) DEFAULT NULL,
  `ordered_by` varchar(50) DEFAULT NULL,
  `accepted_by` varchar(50) DEFAULT 'None',
  `cust_id` int(11) DEFAULT '0',
  `cust_name` varchar(50) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1111 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.customer_order: ~17 rows (approximately)
DELETE FROM `customer_order`;
/*!40000 ALTER TABLE `customer_order` DISABLE KEYS */;
INSERT INTO `customer_order` (`order_no`, `order_time`, `table_no`, `ordered_by`, `accepted_by`, `cust_id`, `cust_name`, `status`) VALUES
	(1052, '2016-09-04 09:49:01', 0, 'rec01', 'rec01', 0, 'David Wallace', 3),
	(1070, '2016-09-06 21:40:48', 0, 'rec01', 'rec01', 0, 'Tharinda Aloka', 3),
	(1077, '2016-09-08 21:00:02', 0, 'rec01', 'rec01', 0, 'Test Order', 4),
	(1081, '2016-09-10 15:22:18', 0, 'chef01', 'rec01', 0, 'Test Order 2', 4),
	(1082, '2016-09-10 15:29:36', 0, 'rec01', 'chef01', 0, 'Andrew Bernard', 2),
	(1083, '2016-09-10 15:45:39', 0, 'rec01', 'chef01', 0, 'Mr Caesar :)', 2),
	(1093, '2016-09-11 19:41:37', 0, 'rec01', 'chef01', 0, 'Customer one', 2),
	(1094, '2016-09-11 19:45:22', 0, 'rec01', 'rec01', 0, 'Mmhm lols', 9),
	(1100, '2016-09-14 08:02:50', 0, 'rec01', 'chef01', 0, 'Test AA', 1),
	(1101, '2016-09-14 08:07:14', 0, 'rec01', 'chef01', 0, 'Test BB', 1),
	(1102, '2016-09-14 08:16:38', 0, 'rec01', 'None', 0, 'Test CC', 0),
	(1103, '2016-09-14 08:18:25', 0, 'rec01', 'None', 0, 'Test DD', 0),
	(1104, '2016-09-14 08:21:39', 0, 'rec01', 'chef01', 0, 'Test EE', 2),
	(1105, '2016-09-14 09:26:42', 0, 'rec01', 'rec01', 0, 'test 0333', 4),
	(1106, '2016-11-21 10:09:12', 0, 'rec01', 'None', NULL, 'hehe', 0),
	(1107, '2016-11-21 10:11:31', 0, 'rec01', 'None', NULL, 'hehe', 0),
	(1108, '2016-11-21 10:12:51', 0, 'rec01', 'None', NULL, 'hehe', 0),
	(1109, '2016-11-26 10:47:17', 3, 'rec01', 'rec01', NULL, 'haha@gmail.com', 3),
	(1110, '2016-11-26 10:47:51', 7, 'rec01', 'rec01', NULL, 'haha@gmail.com', 3);
/*!40000 ALTER TABLE `customer_order` ENABLE KEYS */;


-- Dumping structure for table smart_rms.dining_table
CREATE TABLE IF NOT EXISTS `dining_table` (
  `id` int(11) NOT NULL,
  `table_no` int(11) NOT NULL,
  `table_type` set('RES','NORM','PVT') NOT NULL,
  `num_of_seats` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.dining_table: ~13 rows (approximately)
DELETE FROM `dining_table`;
/*!40000 ALTER TABLE `dining_table` DISABLE KEYS */;
INSERT INTO `dining_table` (`id`, `table_no`, `table_type`, `num_of_seats`) VALUES
	(0, 0, '', 0),
	(1, 1, 'NORM', 2),
	(2, 2, 'NORM', 2),
	(3, 3, 'NORM', 4),
	(4, 4, 'NORM', 4),
	(5, 5, 'NORM', 6),
	(6, 6, 'RES', 2),
	(7, 7, 'RES', 2),
	(8, 8, 'RES', 4),
	(9, 9, 'RES', 4),
	(10, 10, 'RES', 8),
	(11, 11, 'RES', 8),
	(12, 12, 'PVT', 12),
	(13, 13, 'PVT', 16),
	(14, 14, 'RES', 2);
/*!40000 ALTER TABLE `dining_table` ENABLE KEYS */;


-- Dumping structure for table smart_rms.ingredient
CREATE TABLE IF NOT EXISTS `ingredient` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `unit` enum('KG','L','UNIT') DEFAULT NULL,
  `amount` decimal(10,3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.ingredient: ~0 rows (approximately)
DELETE FROM `ingredient`;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;


-- Dumping structure for table smart_rms.ingredient_consumption
CREATE TABLE IF NOT EXISTS `ingredient_consumption` (
  `id` int(11) NOT NULL,
  `ingredient_id` int(11) DEFAULT NULL,
  `action` enum('CONSUME','RESTOCK') DEFAULT NULL,
  `amount` decimal(10,3) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.ingredient_consumption: ~0 rows (approximately)
DELETE FROM `ingredient_consumption`;
/*!40000 ALTER TABLE `ingredient_consumption` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredient_consumption` ENABLE KEYS */;


-- Dumping structure for table smart_rms.menu_item
CREATE TABLE IF NOT EXISTS `menu_item` (
  `item_id` int(11) NOT NULL,
  `menu_index` int(11) DEFAULT NULL,
  `item_type` varchar(50) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `description` text,
  `price` float DEFAULT NULL,
  `spiciness` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.menu_item: ~22 rows (approximately)
DELETE FROM `menu_item`;
/*!40000 ALTER TABLE `menu_item` DISABLE KEYS */;
INSERT INTO `menu_item` (`item_id`, `menu_index`, `item_type`, `item_name`, `description`, `price`, `spiciness`) VALUES
	(1, 1, 'meat', 'Tandoori Chicken Wings', 'Spicy chicken wings infused with chef\'s specially blended spices cooked in a tandoori oven.', 800, 2),
	(2, 2, 'meat', 'Tandoori Chicken', 'Half chicken marinated with tandoori spices. Skewered and chargrilled.', 850, 1),
	(43, 5, 'soup', 'Cream of Vegetable', 'A mixed vegetable soup thickened with fresh cream.', 350, 0),
	(53, 3, 'curry-main-nonveg', 'Garlic Chicken', 'Boneless chicken cooked with garlic.', 850, 1),
	(58, 4, 'curry-main-nonveg', 'Butter Chicken', 'Tandoori chicken cooked with butter.', 950, 0),
	(127, 14, 'rice', 'Steamed Rice', 'Aromatic long grain basmati rice.', 200, 0),
	(501, 12, 'salad', 'Cobb Salad', 'Fresh blend of greens topped with grilled chicken breast, tomatoes, bacon, hard-boiled egg and cheese crumbles.', 1000, 1),
	(502, 13, 'salad', 'Caesar Salad', 'Local, organic heads of romaine chopped in our house caesar dressing topped with grilled chicken, croutons, and parmesan.', 1000, 0),
	(503, 6, 'soup', 'Minestrone Soup', 'Beans, shelll pasta, and an assortment of fresh vegetables served in a rich tomato broth.', 800, 0),
	(504, 10, 'sandwich', 'Meatball Sandwich', 'Our world famous meatballs with marinara and provolone in french bread.', 800, 1),
	(505, 11, 'sandwich', 'Philly Steak Sandwich', 'NY Steak sliced extra thin, topped with grilled onion, peppers, and jack cheese.', 950, 2),
	(506, 20, 'burger', 'Bacon Cheeseburger', 'A quarter pound of Kobe beef with a thick slice of cheddar cheese and applewood-smoked bacon.', 1200, 0),
	(507, 7, 'pasta', 'Fettuccine Alfredo', 'Fresh, house-made fettucine tossed in a thick and creamy Alfredo sauce.', 1200, 0),
	(508, 8, 'pasta', 'Cheese Ravioli', 'Delicious cheese-stuffed ravioli topped with marinara and a trio of melted cheeses.', 1200, 1),
	(509, 9, 'pasta', 'Mediterranean Chicken', 'Grilled chicken, marinated and served with linguini topped with a light vinaigrette, spinach and imported olives.', 1600, 0),
	(510, 18, 'entree', 'Chicken Parmesan', 'Tender chicken breasts, lightly breaded and baked with our signature cheese and marinara sauce.', 1450, 1),
	(511, 19, 'entree', 'NY Steak', 'Herb-marinated 12 ounce center-cut NY strip steak broiled with garlic butter.', 1400, 0),
	(512, 15, 'entree', 'Grilled Prawns', 'Juicy grilled prawns, lightly brushed with herb butter.', 1395, 0),
	(513, 16, 'entree', 'Chicken with Wild Mushrooms', 'Tender roasted chicken, bathed in a rich cream sauce and served under a layer of wild mushrooms and garlic.', 1100, 1),
	(514, 17, 'entree', 'Filet Mignon ', '10 ounce center cut filet, marinated in a medley of herbs, broiled to perfection and served with our famous veal sauce. ', 2295, 1),
	(515, 21, 'appetizer', 'Four Cheese Garlic Bread', 'Toasted French bread topped with romano, cheddar, jack and parmesan, with a light layer of roasted garlic butter.', 400, 0),
	(516, 22, 'appetizer', 'French Fries', 'Hand cut wedges of Yukon Gold potatoes fried in canola oil and tossed with truffle sea salt.', 600, 0);
/*!40000 ALTER TABLE `menu_item` ENABLE KEYS */;


-- Dumping structure for table smart_rms.menu_item_ingredient
CREATE TABLE IF NOT EXISTS `menu_item_ingredient` (
  `id` int(11) NOT NULL,
  `item_id` int(11) DEFAULT NULL,
  `ingredient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.menu_item_ingredient: ~0 rows (approximately)
DELETE FROM `menu_item_ingredient`;
/*!40000 ALTER TABLE `menu_item_ingredient` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu_item_ingredient` ENABLE KEYS */;


-- Dumping structure for table smart_rms.order_item
CREATE TABLE IF NOT EXISTS `order_item` (
  `order_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `order_no` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `FK_order_item_order` (`order_no`),
  KEY `FK_order_item_menu_item` (`item_id`),
  CONSTRAINT `FK_order_item_menu_item` FOREIGN KEY (`item_id`) REFERENCES `menu_item` (`item_id`),
  CONSTRAINT `FK_order_item_order` FOREIGN KEY (`order_no`) REFERENCES `customer_order` (`order_no`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.order_item: ~45 rows (approximately)
DELETE FROM `order_item`;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` (`order_item_id`, `item_id`, `order_no`, `quantity`) VALUES
	(4, 503, 1052, 2),
	(5, 507, 1052, 1),
	(6, 516, 1052, 3),
	(41, 504, 1070, 2),
	(42, 508, 1070, 1),
	(43, 501, 1070, 1),
	(44, 127, 1070, 2),
	(45, 505, 1070, 3),
	(60, 503, 1077, 1),
	(61, 507, 1077, 2),
	(70, 58, 1081, 1),
	(71, 503, 1081, 2),
	(72, 502, 1082, 1),
	(73, 502, 1083, 1),
	(90, 503, 1093, 1),
	(91, 507, 1093, 1),
	(92, 501, 1094, 1),
	(93, 502, 1094, 2),
	(108, 504, 1100, 2),
	(109, 502, 1100, 1),
	(110, 506, 1101, 1),
	(111, 516, 1101, 1),
	(112, 515, 1101, 3),
	(113, 510, 1101, 1),
	(114, 53, 1102, 1),
	(115, 127, 1102, 2),
	(116, 501, 1102, 1),
	(117, 508, 1103, 1),
	(118, 501, 1103, 1),
	(119, 127, 1103, 1),
	(120, 505, 1103, 2),
	(121, 127, 1104, 1),
	(122, 512, 1104, 1),
	(123, 501, 1104, 1),
	(124, 58, 1105, 1),
	(125, 43, 1105, 2),
	(126, 503, 1106, 1),
	(127, 503, 1107, 1),
	(128, 58, 1108, 1),
	(129, 58, 1109, 1),
	(130, 43, 1109, 2),
	(131, 503, 1109, 1),
	(132, 512, 1110, 2),
	(133, 502, 1110, 1),
	(134, 504, 1110, 1);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;


-- Dumping structure for table smart_rms.promotion
CREATE TABLE IF NOT EXISTS `promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `image_path` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.promotion: ~0 rows (approximately)
DELETE FROM `promotion`;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;


-- Dumping structure for table smart_rms.reservation
CREATE TABLE IF NOT EXISTS `reservation` (
  `res_id` int(11) NOT NULL AUTO_INCREMENT,
  `placed_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `date` date DEFAULT NULL,
  `meal_time` set('LUNCH','DINNER') DEFAULT NULL,
  `placed_by` varchar(50) DEFAULT '0',
  `customer_id` int(11) DEFAULT '0',
  PRIMARY KEY (`res_id`),
  KEY `FK_reservation_customer` (`customer_id`),
  CONSTRAINT `FK_reservation_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.reservation: ~12 rows (approximately)
DELETE FROM `reservation`;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` (`res_id`, `placed_time`, `date`, `meal_time`, `placed_by`, `customer_id`) VALUES
	(1, '0000-00-00 00:00:00', '2016-09-19', 'LUNCH', '0', 0),
	(1000, '0000-00-00 00:00:00', '0000-00-00', 'DINNER', '0', 0),
	(1001, '0000-00-00 00:00:00', '2016-10-29', 'LUNCH', '0', 0),
	(1002, '2016-10-30 09:49:36', '2016-11-02', 'DINNER', '0', 0),
	(1003, NULL, '2016-11-07', 'LUNCH', NULL, 10),
	(1004, '2016-11-06 22:15:45', '2016-11-09', 'LUNCH', NULL, 11),
	(1005, '2016-11-06 22:34:27', '2016-11-07', 'DINNER', 'rec01', 12),
	(1006, '2016-11-09 12:52:29', '2016-12-01', 'LUNCH', 'rec01', 13),
	(1007, '2016-11-20 10:34:28', '2016-11-21', 'LUNCH', 'rec01', 14),
	(1008, '2016-11-23 21:45:07', '2016-11-24', 'LUNCH', 'rec01', 15),
	(1009, '2016-11-23 21:47:02', '2016-11-24', 'LUNCH', NULL, 16),
	(1010, '2016-11-24 06:04:28', '2016-11-25', 'LUNCH', 'rec01', 17);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;


-- Dumping structure for table smart_rms.reservation_table
CREATE TABLE IF NOT EXISTS `reservation_table` (
  `res_tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `res_id` int(11) DEFAULT '0',
  `table_id` int(11) DEFAULT '0',
  PRIMARY KEY (`res_tb_id`),
  KEY `FK_reservation_table_reservation` (`res_id`),
  KEY `FK_reservation_table_dining_table` (`table_id`),
  CONSTRAINT `FK_reservation_table_dining_table` FOREIGN KEY (`table_id`) REFERENCES `dining_table` (`id`),
  CONSTRAINT `FK_reservation_table_reservation` FOREIGN KEY (`res_id`) REFERENCES `reservation` (`res_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.reservation_table: ~21 rows (approximately)
DELETE FROM `reservation_table`;
/*!40000 ALTER TABLE `reservation_table` DISABLE KEYS */;
INSERT INTO `reservation_table` (`res_tb_id`, `res_id`, `table_id`) VALUES
	(1, 1002, 6),
	(2, 1002, 8),
	(3, 1001, 7),
	(4, 1001, 9),
	(5, 1003, 8),
	(6, 1004, 9),
	(7, 1004, 6),
	(8, 1004, 10),
	(9, 1005, 8),
	(10, 1005, 6),
	(11, 1005, 10),
	(12, 1006, 6),
	(13, 1006, 8),
	(14, 1006, 7),
	(15, 1007, 6),
	(16, 1007, 7),
	(17, 1007, 8),
	(18, 1008, 8),
	(19, 1008, 6),
	(20, 1009, 9),
	(21, 1009, 7),
	(22, 1010, 8),
	(23, 1010, 6);
/*!40000 ALTER TABLE `reservation_table` ENABLE KEYS */;


-- Dumping structure for table smart_rms.review
CREATE TABLE IF NOT EXISTS `review` (
  `id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `customer_name` varchar(50) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `comment` text,
  `status` set('PENDING','PUBLISHED','REMOVED') DEFAULT '',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.review: ~0 rows (approximately)
DELETE FROM `review`;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;


-- Dumping structure for table smart_rms.user_account
CREATE TABLE IF NOT EXISTS `user_account` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `user_type` varchar(50) DEFAULT NULL,
  `f_name` varchar(50) DEFAULT NULL,
  `l_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.user_account: ~4 rows (approximately)
DELETE FROM `user_account`;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` (`user_id`, `username`, `password`, `user_type`, `f_name`, `l_name`) VALUES
	(1, 'chef01', '94f3b3a16d8ce064c808b16bee5003c5', 'CHEF', 'Michael', 'Scott'),
	(2, 'rec01', '1dc90e80c77fe245a82ea7ed30d1f849', 'RECEPTIONIST', 'Pam', 'Beesly'),
	(4, 'cash01', '94f3b3a16d8ce064c808b16bee5003c5', 'CASHIER', 'David', 'Wallace'),
	(5, 'admin', '80c9ef0fb86369cd25f90af27ef53a9e', 'ADMIN', 'John', 'Smith');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
