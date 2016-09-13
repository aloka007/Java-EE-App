-- --------------------------------------------------------
-- Host:                         br-cdbr-azure-south-b.cloudapp.net
-- Server version:               5.5.45-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table smart_rms.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `contact_no` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.customer: ~4 rows (approximately)
DELETE FROM `customer`;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customer_id`, `name`, `contact_no`, `email`, `password`) VALUES
	(1, 'SADAD', '0710408207', 'cacac', 'cacac'),
	(11, 'saa', '0710408207', 'sasas', 'aasasa'),
	(21, 'xacacac', '0710408207', 'cacacac', 'cacac'),
	(31, 'savinda', '0710408207', 'savindamaddd@gmail.com', 'savinda');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


-- Dumping structure for table smart_rms.customer_order
CREATE TABLE IF NOT EXISTS `customer_order` (
  `order_no` int(11) NOT NULL AUTO_INCREMENT,
  `order_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `table_no` int(11) DEFAULT NULL,
  `ordered_by` varchar(50) DEFAULT NULL,
  `accepted_by` varchar(50) DEFAULT NULL,
  `cust_name` varchar(50) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1471 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.customer_order: ~6 rows (approximately)
DELETE FROM `customer_order`;
/*!40000 ALTER TABLE `customer_order` DISABLE KEYS */;
INSERT INTO `customer_order` (`order_no`, `order_time`, `table_no`, `ordered_by`, `accepted_by`, `cust_name`, `status`) VALUES
	(1411, '2016-09-11 09:25:56', 5, '3', NULL, NULL, 0),
	(1421, '2016-09-11 09:27:09', 5, '3', NULL, NULL, 0),
	(1431, '2016-09-11 10:01:09', 5, '3', NULL, NULL, 0),
	(1441, '2016-09-11 10:19:18', 6, '3', NULL, NULL, 0),
	(1451, '2016-09-11 15:38:11', 6, '3', NULL, NULL, 0),
	(1461, '2016-09-12 01:50:59', 6, '3', NULL, NULL, 0);
/*!40000 ALTER TABLE `customer_order` ENABLE KEYS */;


-- Dumping structure for table smart_rms.item_list
CREATE TABLE IF NOT EXISTS `item_list` (
  `item_no` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` int(11) DEFAULT NULL,
  `menu_index` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_no`),
  KEY `FK_item_list_order` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.item_list: ~0 rows (approximately)
DELETE FROM `item_list`;
/*!40000 ALTER TABLE `item_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_list` ENABLE KEYS */;


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
	(1, NULL, 'meat', 'Tandoori Chicken Wings', 'Spicy chicken wings infused with chef\'s specially blended spices cooked in a tandoori oven.', 800, 2),
	(2, NULL, 'meat', 'Tandoori Chicken', 'Half chicken marinated with tandoori spices. Skewered and chargrilled.', 850, 1),
	(43, NULL, 'soup', 'Cream of Vegetable', 'A mixed vegetable soup thickened with fresh cream.', 350, 0),
	(53, NULL, 'curry-main-nonveg', 'Garlic Chicken', 'Boneless chicken cooked with garlic.', 850, 1),
	(58, NULL, 'curry-main-nonveg', 'Butter Chicken', 'Tandoori chicken cooked with butter.', 950, 0),
	(127, NULL, 'rice', 'Steamed Rice', 'Aromatic long grain basmati rice.', 200, 0),
	(501, NULL, 'salad', 'Cobb Salad', 'Fresh blend of greens topped with grilled chicken breast, tomatoes, bacon, hard-boiled egg and cheese crumbles.', 1000, 1),
	(502, NULL, 'salad', 'Caesar Salad', 'Local, organic heads of romaine chopped in our house caesar dressing topped with grilled chicken, croutons, and parmesan.', 1000, 0),
	(503, NULL, 'soup', 'Minestrone Soup', 'Beans, shelll pasta, and an assortment of fresh vegetables served in a rich tomato broth.', 800, 0),
	(504, NULL, 'sandwich', 'Meatball Sandwich', 'Our world famous meatballs with marinara and provolone in french bread.', 800, 1),
	(505, NULL, 'sandwich', 'Philly Steak Sandwich', 'NY Steak sliced extra thin, topped with grilled onion, peppers, and jack cheese.', 950, 2),
	(506, NULL, 'burger', 'Bacon Cheeseburger', 'A quarter pound of Kobe beef with a thick slice of cheddar cheese and applewood-smoked bacon.', 1200, 0),
	(507, NULL, 'pasta', 'Fettuccine Alfredo', 'Fresh, house-made fettucine tossed in a thick and creamy Alfredo sauce.', 1200, 0),
	(508, NULL, 'pasta', 'Cheese Ravioli', 'Delicious cheese-stuffed ravioli topped with marinara and a trio of melted cheeses.', 1200, 1),
	(509, NULL, 'pasta', 'Mediterranean Chicken', 'Grilled chicken, marinated and served with linguini topped with a light vinaigrette, spinach and imported olives.', 1600, 0),
	(510, NULL, 'entree', 'Chicken Parmesan', 'Tender chicken breasts, lightly breaded and baked with our signature cheese and marinara sauce.', 1450, 1),
	(511, NULL, 'entree', 'NY Steak', 'Herb-marinated 12 ounce center-cut NY strip steak broiled with garlic butter.', 1400, 0),
	(512, NULL, 'entree', 'Grilled Prawns', 'Juicy grilled prawns, lightly brushed with herb butter.', 1395, 0),
	(513, NULL, 'entree', 'Chicken with Wild Mushrooms', 'Tender roasted chicken, bathed in a rich cream sauce and served under a layer of wild mushrooms and garlic.', 1100, 1),
	(514, NULL, 'entree', 'Filet Mignon ', '10 ounce center cut filet, marinated in a medley of herbs, broiled to perfection and served with our famous veal sauce. ', 2295, 1),
	(515, NULL, 'appetizer', 'Four Cheese Garlic Bread', 'Toasted French bread topped with romano, cheddar, jack and parmesan, with a light layer of roasted garlic butter.', 400, 0),
	(516, NULL, 'appetizer', 'French Fries', 'Hand cut wedges of Yukon Gold potatoes fried in canola oil and tossed with truffle sea salt.', 600, 0);
/*!40000 ALTER TABLE `menu_item` ENABLE KEYS */;


-- Dumping structure for table smart_rms.order_item
CREATE TABLE IF NOT EXISTS `order_item` (
  `order_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `order_no` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `FK_order_item_order` (`order_no`),
  KEY `FK_order_item_menu_item` (`item_id`),
  CONSTRAINT `FK_order_item_menu_item` FOREIGN KEY (`item_id`) REFERENCES `menu_item` (`item_id`),
  CONSTRAINT `FK_order_item_order` FOREIGN KEY (`order_no`) REFERENCES `customer_order` (`order_no`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1321 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.order_item: ~13 rows (approximately)
DELETE FROM `order_item`;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` (`order_item_id`, `item_id`, `order_no`, `quantity`) VALUES
	(1191, 1, 1411, 1),
	(1201, 43, 1411, 1),
	(1211, 58, 1411, 1),
	(1221, 58, 1421, 2),
	(1231, 502, 1421, 2),
	(1241, 504, 1421, 2),
	(1251, 53, 1431, 4),
	(1261, 2, 1441, 1),
	(1271, 502, 1441, 1),
	(1281, 53, 1441, 1),
	(1291, 1, 1451, 1),
	(1301, 1, 1461, 4),
	(1311, 43, 1461, 4);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;


-- Dumping structure for table smart_rms.table_type
CREATE TABLE IF NOT EXISTS `table_type` (
  `table_no` varchar(50) NOT NULL,
  `table_type` varchar(30) NOT NULL,
  `waiter_id` varchar(30) DEFAULT NULL,
  `num_of_seats` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.table_type: ~18 rows (approximately)
DELETE FROM `table_type`;
/*!40000 ALTER TABLE `table_type` DISABLE KEYS */;
INSERT INTO `table_type` (`table_no`, `table_type`, `waiter_id`, `num_of_seats`) VALUES
	('1', 'regular', '7', 2),
	('2', 'regular', '3', 2),
	('3', 'regular', '3', 4),
	('4', 'regular', '3', 4),
	('5', 'regular', '3', 6),
	('6', 'regular', '3', 6),
	('7', 'regular', '2', 8),
	('8', 'regular', '2', 8),
	('9', 'reservation', '0', 2),
	('10', 'reservation', '0', 2),
	('11', 'reservation', '0', 4),
	('12', 'reservation', '0', 4),
	('13', 'reservation', '0', 4),
	('14', 'reservation', '0', 8),
	('15', 'reservation', '0', 8),
	('16', 'reservation', '0', 8),
	('17', 'private', '0', 12),
	('18', 'private', '0', 14);
/*!40000 ALTER TABLE `table_type` ENABLE KEYS */;


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

-- Dumping data for table smart_rms.user_account: ~7 rows (approximately)
DELETE FROM `user_account`;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` (`user_id`, `username`, `password`, `user_type`, `f_name`, `l_name`) VALUES
	(1, 'chef01', 'c123', 'CHEF', 'Michael', 'Scott'),
	(2, 'rec01', 'r123', 'RECEPTIONIST', 'Pam', 'Beesly'),
	(3, 'waiter1', 'f1f4f24aee36f983b826c9e721f8234c', 'WAITER', 'Saman', 'Kumara'),
	(4, 'cash01', 's123', 'CASHIER', 'David', 'Wallace'),
	(5, 'chef02', 'c222', 'CHEF', 'Todd', 'Packer'),
	(6, 'common', 'com123', NULL, NULL, NULL),
	(7, 'waiter2', '9107fa72f033912bdc14a88129e75f70', 'WAITER', 'Ajith', 'Bandara');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
