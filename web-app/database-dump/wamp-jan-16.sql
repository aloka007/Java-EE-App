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
  `pay_mode` enum('CASH','E-CARD') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.bill: ~44 rows (approximately)
DELETE FROM `bill`;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` (`id`, `waiter_id`, `cashier_id`, `customer_name`, `date`, `amount`, `discount`, `tax`, `sub_total`, `tip`, `total`, `pay_mode`) VALUES
	(9, '7', 'null', 'Tharinda', '2016-11-27 14:33:00', 7400.00, 0.00, 1110.00, 8510.00, 200.00, 8710.00, NULL),
	(13, NULL, NULL, 'kek', '2016-11-02 12:00:00', NULL, NULL, NULL, 100000.00, NULL, NULL, NULL),
	(14, NULL, NULL, 'kek', '2016-11-04 10:52:35', NULL, NULL, NULL, 90000.00, NULL, NULL, NULL),
	(15, NULL, NULL, 'kek', '2016-11-01 12:00:00', NULL, NULL, NULL, 84323.00, NULL, NULL, NULL),
	(16, NULL, NULL, NULL, '2016-11-02 12:00:00', NULL, NULL, NULL, 98656.00, NULL, NULL, NULL),
	(17, NULL, NULL, NULL, '2016-11-03 12:00:00', NULL, NULL, NULL, 131594.00, NULL, NULL, NULL),
	(18, NULL, NULL, NULL, '2016-11-04 12:00:00', NULL, NULL, NULL, 88407.00, NULL, NULL, NULL),
	(19, NULL, NULL, NULL, '2016-11-05 12:00:00', NULL, NULL, NULL, 183769.00, NULL, NULL, NULL),
	(20, NULL, NULL, NULL, '2016-11-06 12:00:00', NULL, NULL, NULL, 139895.00, NULL, NULL, NULL),
	(21, NULL, NULL, NULL, '2016-11-07 12:00:00', NULL, NULL, NULL, 190704.00, NULL, NULL, NULL),
	(22, NULL, NULL, NULL, '2016-11-08 12:00:00', NULL, NULL, NULL, 115363.00, NULL, NULL, NULL),
	(23, NULL, NULL, NULL, '2016-11-09 12:00:00', NULL, NULL, NULL, 187720.00, NULL, NULL, NULL),
	(24, NULL, NULL, NULL, '2016-11-10 12:00:00', NULL, NULL, NULL, 207098.00, NULL, NULL, NULL),
	(25, NULL, NULL, NULL, '2016-11-11 12:00:00', NULL, NULL, NULL, 150828.00, NULL, NULL, NULL),
	(26, NULL, NULL, NULL, '2016-11-12 12:00:00', NULL, NULL, NULL, 125694.00, NULL, NULL, NULL),
	(27, NULL, NULL, NULL, '2016-11-13 12:00:00', NULL, NULL, NULL, 187355.00, NULL, NULL, NULL),
	(28, NULL, NULL, NULL, '2016-11-14 12:00:00', NULL, NULL, NULL, 81507.00, NULL, NULL, NULL),
	(29, NULL, NULL, NULL, '2016-11-15 12:00:00', NULL, NULL, NULL, 191615.00, NULL, NULL, NULL),
	(30, NULL, NULL, NULL, '2016-11-16 12:00:00', NULL, NULL, NULL, 119957.00, NULL, NULL, NULL),
	(31, NULL, NULL, NULL, '2016-11-17 12:00:00', NULL, NULL, NULL, 103673.00, NULL, NULL, NULL),
	(32, NULL, NULL, NULL, '2016-11-18 12:00:00', NULL, NULL, NULL, 195849.00, NULL, NULL, NULL),
	(33, NULL, NULL, NULL, '2016-11-19 12:00:00', NULL, NULL, NULL, 168249.00, NULL, NULL, NULL),
	(34, NULL, NULL, NULL, '2016-11-20 12:00:00', NULL, NULL, NULL, 89139.00, NULL, NULL, NULL),
	(35, NULL, NULL, NULL, '2016-11-21 12:00:00', NULL, NULL, NULL, 153984.00, NULL, NULL, NULL),
	(36, NULL, NULL, NULL, '2016-11-22 12:00:00', NULL, NULL, NULL, 178569.00, NULL, NULL, NULL),
	(37, NULL, NULL, NULL, '2016-11-23 12:00:00', NULL, NULL, NULL, 196821.00, NULL, NULL, NULL),
	(38, NULL, NULL, NULL, '2016-11-24 12:00:00', NULL, NULL, NULL, 117044.00, NULL, NULL, NULL),
	(39, NULL, NULL, NULL, '2016-11-25 12:00:00', NULL, NULL, NULL, 102034.00, NULL, NULL, NULL),
	(40, NULL, NULL, NULL, '2016-11-26 12:00:00', NULL, NULL, NULL, 100521.00, NULL, NULL, NULL),
	(41, NULL, NULL, 'jsmith@gmail.com', '2016-11-27 12:00:00', NULL, NULL, NULL, 112242.00, NULL, NULL, NULL),
	(42, NULL, NULL, 'jsmith@gmail.com', '2016-11-28 12:00:00', NULL, NULL, NULL, 170054.00, NULL, NULL, NULL),
	(43, NULL, NULL, 'jsmith@gmail.com', '2016-11-29 12:00:00', NULL, NULL, NULL, 133571.00, NULL, NULL, NULL),
	(44, '', 'null', 'katharinda@gmail.com', '2016-12-09 12:35:08', 3000.00, 0.00, 450.00, 3450.00, 0.00, 3450.00, 'CASH'),
	(45, '', 'null', 'katharinda@gmail.com', '2017-01-07 14:57:01', 2450.00, 0.00, 367.50, 2817.50, 0.00, 2817.50, 'CASH'),
	(46, '5', 'null', 'katharinda@gmail.com', '2017-01-07 15:10:52', 1650.00, 0.00, 247.50, 1897.50, 20.00, 1917.50, 'CASH'),
	(47, '1', 'null', 'test 0333', '2017-01-09 11:02:19', 1650.00, 0.00, 247.50, 1897.50, 0.00, 1897.50, 'CASH'),
	(48, '12', 'cash01', 'hello', '2017-01-10 07:58:17', 3000.00, 0.00, 450.00, 3450.00, 20.00, 3470.00, 'CASH'),
	(49, '1', 'admin', 'haha@gmail.com', '2017-01-11 22:35:37', 4590.00, 0.00, 688.50, 5278.50, 0.00, 5278.50, 'CASH'),
	(50, '1', 'cash01', 'test', '2017-01-13 15:35:44', 13350.00, 0.00, 2002.50, 15352.50, 10.00, 15362.50, 'CASH'),
	(51, 'null', 'cash01', 'test 4', '2017-01-14 19:53:22', 1050.00, 0.00, 157.50, 1207.50, 0.00, 1207.50, 'CASH'),
	(52, 'null', 'cash01', 'test 5', '2017-01-14 20:13:26', 1050.00, 0.00, 157.50, 1207.50, 0.00, 1207.50, 'CASH'),
	(53, 'null', 'cash01', 'Dave', '2017-01-15 13:02:51', 8400.00, 0.00, 1260.00, 9660.00, 840.00, 10500.00, 'CASH'),
	(54, 'null', 'cash01', 'dave@eg.com', '2017-01-15 14:48:54', 3600.00, 0.00, 540.00, 4140.00, 360.00, 4500.00, 'CASH'),
	(55, 'null', 'cash01', 'dave@eg.com', '2017-01-15 14:49:52', 850.00, 0.00, 127.50, 977.50, 85.00, 1062.50, 'CASH');
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.bill_order: ~17 rows (approximately)
DELETE FROM `bill_order`;
/*!40000 ALTER TABLE `bill_order` DISABLE KEYS */;
INSERT INTO `bill_order` (`id`, `bill_id`, `order_no`, `total`) VALUES
	(3, 9, 1105, NULL),
	(4, 9, 1077, NULL),
	(5, 9, 1081, NULL),
	(6, 44, 1113, NULL),
	(7, 45, 1109, NULL),
	(8, 46, 1105, NULL),
	(9, 47, 1105, NULL),
	(10, 48, 1113, NULL),
	(11, 49, 1110, NULL),
	(12, 50, 1121, NULL),
	(13, 50, 1122, NULL),
	(14, 51, 1124, NULL),
	(15, 51, 1125, NULL),
	(16, 52, 1125, NULL),
	(17, 52, 1124, NULL),
	(18, 53, 1127, NULL),
	(19, 53, 1126, NULL),
	(20, 54, 1128, NULL),
	(21, 55, 1124, NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.bill_order_item: ~42 rows (approximately)
DELETE FROM `bill_order_item`;
/*!40000 ALTER TABLE `bill_order_item` DISABLE KEYS */;
INSERT INTO `bill_order_item` (`id`, `bill_order_id`, `item_name`, `quantity`, `price`, `amount`) VALUES
	(7, 3, 'Butter Chicken', 1, 950.00, 950.00),
	(8, 3, 'Cream of Vegetable', 2, 350.00, 700.00),
	(9, 4, 'Minestrone Soup', 1, 800.00, 800.00),
	(10, 4, 'Fettuccine Alfredo', 2, 1200.00, 2400.00),
	(11, 5, 'Butter Chicken', 1, 950.00, 950.00),
	(12, 5, 'Minestrone Soup', 2, 800.00, 1600.00),
	(13, 6, 'Cream of Vegetable', 1, 350.00, 350.00),
	(14, 6, 'Tandoori Chicken', 2, 850.00, 1700.00),
	(15, 6, 'Butter Chicken', 1, 950.00, 950.00),
	(16, 7, 'Butter Chicken', 1, 950.00, 950.00),
	(17, 7, 'Cream of Vegetable', 2, 350.00, 700.00),
	(18, 7, 'Minestrone Soup', 1, 800.00, 800.00),
	(19, 8, 'Butter Chicken', 1, 950.00, 950.00),
	(20, 8, 'Cream of Vegetable', 2, 350.00, 700.00),
	(21, 9, 'Butter Chicken', 1, 950.00, 950.00),
	(22, 9, 'Cream of Vegetable', 2, 350.00, 700.00),
	(23, 10, 'Cream of Vegetable', 1, 350.00, 350.00),
	(24, 10, 'Tandoori Chicken', 2, 850.00, 1700.00),
	(25, 10, 'Butter Chicken', 1, 950.00, 950.00),
	(26, 11, 'Grilled Prawns', 2, 1395.00, 2790.00),
	(27, 11, 'Caesar Salad', 1, 1000.00, 1000.00),
	(28, 11, 'Meatball Sandwich', 1, 800.00, 800.00),
	(29, 12, 'Chicken with Wild Mushrooms', 1, 1100.00, 1100.00),
	(30, 12, 'Fettuccine Alfredo', 1, 1200.00, 1200.00),
	(31, 12, 'Bacon Cheeseburger', 1, 1200.00, 1200.00),
	(32, 12, 'NY Steak', 1, 1400.00, 1400.00),
	(33, 12, 'Garlic Chicken', 4, 850.00, 3400.00),
	(34, 12, 'Cheese Ravioli', 1, 1200.00, 1200.00),
	(35, 13, 'Tandoori Chicken', 2, 850.00, 1700.00),
	(36, 13, 'Cheese Ravioli', 1, 1200.00, 1200.00),
	(37, 13, 'Butter Chicken', 1, 950.00, 950.00),
	(38, 14, 'Tandoori Chicken', 1, 850.00, 850.00),
	(39, 15, 'Steamed Rice', 1, 200.00, 200.00),
	(40, 16, 'Steamed Rice', 1, 200.00, 200.00),
	(41, 17, 'Tandoori Chicken', 1, 850.00, 850.00),
	(42, 18, 'Tandoori Chicken', 2, 850.00, 1700.00),
	(43, 18, 'Fettuccine Alfredo', 1, 1200.00, 1200.00),
	(44, 18, 'Cream of Vegetable', 2, 350.00, 700.00),
	(45, 19, 'Cheese Ravioli', 4, 1200.00, 4800.00),
	(46, 20, 'Tandoori Chicken Wings', 1, 800.00, 800.00),
	(47, 20, 'Tandoori Chicken', 1, 850.00, 850.00),
	(48, 20, 'Philly Steak Sandwich', 1, 950.00, 950.00),
	(49, 20, 'Caesar Salad', 1, 1000.00, 1000.00),
	(50, 21, 'Tandoori Chicken', 1, 850.00, 850.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.customer: ~18 rows (approximately)
DELETE FROM `customer`;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customer_id`, `name`, `password`, `birthday`, `email`, `contact_no`) VALUES
	(20, 'Tharinda', NULL, '2017-01-15', 'katharinda@gmail.com', '0719690609'),
	(23, 'Dave Hardy', NULL, NULL, 'dave@eg.com', '0800555555'),
	(37, 'Valentine Day', 'commodo', '1990-01-01', 'sem.eget@scelerisque.edu', '767 498 8922'),
	(38, 'Murphy Gallagher', 'orci.', '1990-01-01', 'eleifend.Cras@gravidanon.net', '766 987 1053'),
	(39, 'Alan Estrada', 'magna.', '1990-01-01', 'adipiscing.elit@metusurna.net', '727 221 7530'),
	(40, 'Philip Church', 'nibh', '1990-01-01', 'varius.et@gravidamolestiearcu.com', '678 231 3576'),
	(41, 'Ahmed Woodward', 'a', '1990-01-01', 'libero.mauris.aliquam@euismodmauriseu.ca', '625 746 7269'),
	(42, 'Alvin Wise', 'neque.', '1990-01-01', 'cursus@nonarcu.org', '757 809 5988'),
	(43, 'Lionel Green', 'ultrices,', '1990-01-01', 'amet@Curabitur.org', '913 199 9881'),
	(44, 'Henry Black', 'amet', '1990-01-01', 'sem.Pellentesque@elit.co.uk', '478 380 4821'),
	(45, 'Warren Sykes', 'diam', '1990-01-01', 'euismod@eteuismodet.edu', '008 683 2019'),
	(46, 'Galvin Barron', 'enim.', '1990-01-01', 'quis.pede@duiaugue.net', '291 214 9018'),
	(47, 'Eaton Cruz', 'malesuada', '1990-01-01', 'quam@Donecfelisorci.net', '166 640 8246'),
	(48, 'Yuli Trevino', 'lectus', '1990-01-01', 'non.arcu.Vivamus@Quisquenonummy.ca', '642 475 4295'),
	(49, 'Mark Barker', 'massa.', '1990-01-01', 'auctor.velit@Loremipsum.ca', '827 449 5038'),
	(50, 'Wing Harding', 'Nunc', '1990-01-01', 'malesuada.fringilla@nec.net', '048 977 2667'),
	(51, 'Fulton Thompson', 'nisi', '1990-01-01', 'hello@eg.com', '517 306 1383'),
	(52, 'Demo', NULL, NULL, 'demo@eg.com', '0719690609'),
	(53, 'Steve', NULL, NULL, 'steve@eg.com', '0712526526');
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
) ENGINE=InnoDB AUTO_INCREMENT=1140 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.customer_order: ~19 rows (approximately)
DELETE FROM `customer_order`;
/*!40000 ALTER TABLE `customer_order` DISABLE KEYS */;
INSERT INTO `customer_order` (`order_no`, `order_time`, `table_no`, `ordered_by`, `accepted_by`, `cust_id`, `cust_name`, `status`) VALUES
	(1121, '2017-01-13 11:32:14', 0, 'rec01', 'chef01', NULL, 'test', 3),
	(1122, '2017-01-13 15:10:13', 0, 'rec01', 'chef01', NULL, 'Test02', 2),
	(1123, '2017-01-14 00:05:47', 0, 'rec01', 'rec01', NULL, 'test3', 3),
	(1124, '2017-01-14 00:06:17', 2, 'rec01', 'chef01', NULL, 'test 4', 3),
	(1125, '2017-01-14 00:06:38', 3, 'rec01', 'chef01', NULL, 'test 4', 0),
	(1126, '2017-01-15 09:48:18', 0, 'rec01', 'rec01', NULL, 'test 6', 3),
	(1127, '2017-01-15 12:53:35', 0, 'rec01', 'rec01', NULL, 'Dave', 3),
	(1128, '2017-01-15 14:27:54', 0, 'rec01', 'rec01', NULL, 'dave@eg.com', 3),
	(1129, '2017-01-15 21:18:53', 0, 'rec01', 'chef01', NULL, 'hello@eg.com', 2),
	(1130, '2017-01-15 22:21:21', 9, 'rec01', 'rec01', NULL, 'dave@eg.com', 3),
	(1131, '2017-01-15 22:22:45', 0, 'rec01', 'None', NULL, 'dave@eg.com', 1),
	(1132, '2017-01-16 20:56:29', 0, 'rec01', 'None', NULL, 'demo01', 0),
	(1133, '2017-01-16 20:58:00', 0, 'rec01', 'None', NULL, 'demo02', 0),
	(1134, '2017-01-16 20:58:59', 0, 'rec01', 'None', NULL, 'demo03', 0),
	(1135, '2017-01-16 21:09:40', 3, 'rec01', 'None', NULL, 'demoC1', 3),
	(1136, '2017-01-16 21:10:13', 3, 'rec01', 'None', NULL, 'demoC2', 3),
	(1137, '2017-01-16 21:10:38', 3, 'rec01', 'None', NULL, 'demoC3', 3),
	(1138, '2017-01-16 21:11:14', 5, 'rec01', 'None', NULL, 'demoC4', 3),
	(1139, '2017-01-16 21:11:42', 7, 'rec01', 'None', NULL, 'demoC5', 3);
/*!40000 ALTER TABLE `customer_order` ENABLE KEYS */;


-- Dumping structure for table smart_rms.dining_table
CREATE TABLE IF NOT EXISTS `dining_table` (
  `id` int(11) NOT NULL,
  `table_no` int(11) NOT NULL,
  `table_type` set('RES','NORM','PVT') NOT NULL,
  `num_of_seats` int(3) NOT NULL,
  `status` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.dining_table: ~18 rows (approximately)
DELETE FROM `dining_table`;
/*!40000 ALTER TABLE `dining_table` DISABLE KEYS */;
INSERT INTO `dining_table` (`id`, `table_no`, `table_type`, `num_of_seats`, `status`) VALUES
	(1, 1, 'NORM', 2, '0'),
	(2, 2, 'NORM', 2, '0'),
	(3, 3, 'NORM', 4, '0'),
	(4, 4, 'NORM', 4, '0'),
	(5, 5, 'NORM', 6, '0'),
	(6, 6, 'NORM', 6, '0'),
	(7, 7, 'NORM', 8, '0'),
	(8, 8, 'NORM', 8, '0'),
	(9, 9, 'RES', 2, '0'),
	(10, 10, 'RES', 2, '0'),
	(11, 11, 'RES', 2, '0'),
	(12, 12, 'RES', 2, '0'),
	(13, 13, 'RES', 4, '0'),
	(14, 14, 'RES', 4, '0'),
	(15, 15, 'RES', 8, '0'),
	(16, 16, 'RES', 8, '0'),
	(17, 17, 'PVT', 12, '0'),
	(18, 18, 'PVT', 16, '0');
/*!40000 ALTER TABLE `dining_table` ENABLE KEYS */;


-- Dumping structure for table smart_rms.ingredient
CREATE TABLE IF NOT EXISTS `ingredient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `unit` enum('KG','L','UNIT') DEFAULT NULL,
  `amount` decimal(10,3) DEFAULT NULL,
  `warn` decimal(10,3) DEFAULT NULL,
  `crit` decimal(10,3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.ingredient: ~5 rows (approximately)
DELETE FROM `ingredient`;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` (`id`, `name`, `description`, `unit`, `amount`, `warn`, `crit`) VALUES
	(0, 'Cheese', NULL, 'KG', 9.030, 9.000, 1.000),
	(1, 'Beef', NULL, 'KG', 14.300, 5.000, 2.000),
	(2, 'Milk', NULL, 'L', 5.200, 4.000, 2.000),
	(4, 'Bacon', NULL, 'KG', 4.150, 3.000, 2.000),
	(5, 'Chicken', NULL, 'KG', 8.900, 4.000, 3.000);
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;


-- Dumping structure for table smart_rms.ingredient_consumption
CREATE TABLE IF NOT EXISTS `ingredient_consumption` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ingredient_id` int(11) DEFAULT NULL,
  `action` enum('CONSUME','RESTOCK') DEFAULT NULL,
  `amount` decimal(10,3) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_ingredient_consumption_ingredient` (`ingredient_id`),
  CONSTRAINT `FK_ingredient_consumption_ingredient` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.ingredient_consumption: ~45 rows (approximately)
DELETE FROM `ingredient_consumption`;
/*!40000 ALTER TABLE `ingredient_consumption` DISABLE KEYS */;
INSERT INTO `ingredient_consumption` (`id`, `ingredient_id`, `action`, `amount`, `time`) VALUES
	(1, 5, 'CONSUME', 0.200, '2017-01-14 15:19:49'),
	(2, 5, 'CONSUME', 0.400, '2017-01-14 16:37:33'),
	(3, 0, 'CONSUME', 0.100, '2017-01-14 16:37:33'),
	(4, 5, 'CONSUME', 0.200, '2017-01-14 16:37:33'),
	(5, 5, 'CONSUME', 0.400, '2017-01-14 16:38:21'),
	(6, 0, 'CONSUME', 0.100, '2017-01-14 16:38:21'),
	(7, 5, 'CONSUME', 0.200, '2017-01-14 16:38:21'),
	(8, 5, 'CONSUME', 0.200, '2017-01-14 21:43:42'),
	(9, 0, 'CONSUME', 0.200, '2017-01-14 21:46:17'),
	(10, 0, 'CONSUME', 0.200, '2017-01-14 21:51:24'),
	(11, 5, 'CONSUME', 0.400, '2017-01-14 21:51:39'),
	(12, 0, 'CONSUME', 0.100, '2017-01-14 21:51:39'),
	(13, 5, 'CONSUME', 0.200, '2017-01-14 21:51:39'),
	(14, 5, 'CONSUME', 0.200, '2017-01-14 22:28:11'),
	(15, 5, 'CONSUME', 0.400, '2017-01-14 22:28:39'),
	(16, 0, 'CONSUME', 0.100, '2017-01-14 22:28:39'),
	(17, 5, 'CONSUME', 0.200, '2017-01-14 22:28:39'),
	(18, 5, 'CONSUME', 0.200, '2017-01-14 22:31:38'),
	(19, 0, 'CONSUME', 0.090, '2017-01-14 22:31:38'),
	(20, 0, 'CONSUME', 0.050, '2017-01-14 22:31:38'),
	(21, 4, 'CONSUME', 0.050, '2017-01-14 22:31:38'),
	(22, 1, 'CONSUME', 0.200, '2017-01-14 22:31:38'),
	(23, 5, 'CONSUME', 0.800, '2017-01-14 22:31:38'),
	(24, 0, 'CONSUME', 0.100, '2017-01-14 22:31:38'),
	(25, 5, 'CONSUME', 0.200, '2017-01-14 22:39:29'),
	(26, 0, 'CONSUME', 0.200, '2017-01-14 22:39:48'),
	(27, 5, 'CONSUME', 0.200, '2017-01-14 22:43:06'),
	(28, 5, 'CONSUME', 0.200, '2017-01-14 22:43:46'),
	(29, 0, 'CONSUME', 0.090, '2017-01-14 22:43:46'),
	(30, 0, 'CONSUME', 0.050, '2017-01-14 22:43:46'),
	(31, 4, 'CONSUME', 0.050, '2017-01-14 22:43:46'),
	(32, 1, 'CONSUME', 0.200, '2017-01-14 22:43:46'),
	(33, 5, 'CONSUME', 0.800, '2017-01-14 22:43:46'),
	(34, 0, 'CONSUME', 0.100, '2017-01-14 22:43:46'),
	(35, 5, 'CONSUME', 0.400, '2017-01-15 08:49:20'),
	(36, 0, 'CONSUME', 0.100, '2017-01-15 08:49:20'),
	(37, 5, 'CONSUME', 0.200, '2017-01-15 08:49:20'),
	(38, 5, 'CONSUME', 0.400, '2017-01-15 12:56:59'),
	(39, 0, 'CONSUME', 0.090, '2017-01-15 12:56:59'),
	(40, 2, 'CONSUME', 0.800, '2017-01-15 12:56:59'),
	(41, 0, 'CONSUME', 0.400, '2017-01-15 12:57:14'),
	(42, 5, 'CONSUME', 0.200, '2017-01-15 14:30:35'),
	(43, 5, 'CONSUME', 0.200, '2017-01-15 14:30:36'),
	(44, 1, 'CONSUME', 0.200, '2017-01-15 14:30:36'),
	(45, 5, 'CONSUME', 0.400, '2017-01-15 22:06:38'),
	(46, 5, 'CONSUME', 0.400, '2017-01-15 22:06:38'),
	(47, 0, 'CONSUME', 0.100, '2017-01-15 22:06:38'),
	(48, 5, 'CONSUME', 0.200, '2017-01-15 22:06:38'),
	(49, 0, 'CONSUME', 0.180, '2017-01-15 22:06:38'),
	(50, 5, 'CONSUME', 0.200, '2017-01-15 22:31:58'),
	(51, 0, 'CONSUME', 0.090, '2017-01-15 22:31:58');
/*!40000 ALTER TABLE `ingredient_consumption` ENABLE KEYS */;


-- Dumping structure for table smart_rms.menu_item
CREATE TABLE IF NOT EXISTS `menu_item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_index` int(11) DEFAULT NULL,
  `item_type` varchar(50) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `description` text,
  `price` float DEFAULT NULL,
  `spiciness` tinyint(4) DEFAULT NULL,
  `image_path` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=517 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.menu_item: ~23 rows (approximately)
DELETE FROM `menu_item`;
/*!40000 ALTER TABLE `menu_item` DISABLE KEYS */;
INSERT INTO `menu_item` (`item_id`, `menu_index`, `item_type`, `item_name`, `description`, `price`, `spiciness`, `image_path`) VALUES
	(1, 1, 'meat', 'Tandoori Chicken Wings', 'Spicy chicken wings infused with chef\'s specially blended spices cooked in a tandoori oven.', 800, 2, NULL),
	(2, 2, 'meat', 'Tandoori Chicken', 'Half chicken marinated with tandoori spices. Skewered and chargrilled.', 850, 1, NULL),
	(43, 5, 'soup', 'Cream of Vegetable', 'A mixed vegetable soup thickened with fresh cream.', 350, 0, NULL),
	(53, 3, 'curry-main-nonveg', 'Garlic Chicken', 'Boneless chicken cooked with garlic.', 850, 1, NULL),
	(58, 4, 'curry-main-nonveg', 'Butter Chicken', 'Tandoori chicken cooked with butter.', 950, 0, NULL),
	(127, 14, 'rice', 'Steamed Rice', 'Aromatic long grain basmati rice.', 200, 0, NULL),
	(501, 12, 'salad', 'Cobb Salad', 'Fresh blend of greens topped with grilled chicken breast, tomatoes, bacon, hard-boiled egg and cheese crumbles.', 1000, 1, NULL),
	(502, 13, 'salad', 'Caesar Salad', 'Local, organic heads of romaine chopped in our house caesar dressing topped with grilled chicken, croutons, and parmesan.', 1000, 0, NULL),
	(503, 6, 'soup', 'Minestrone Soup', 'Beans, shelll pasta, and an assortment of fresh vegetables served in a rich tomato broth.', 800, 0, NULL),
	(504, 10, 'sandwich', 'Meatball Sandwich', 'Our world famous meatballs with marinara and provolone in french bread.', 800, 1, NULL),
	(505, 11, 'sandwich', 'Philly Steak Sandwich', 'NY Steak sliced extra thin, topped with grilled onion, peppers, and jack cheese.', 950, 2, NULL),
	(506, 20, 'burger', 'Bacon Cheeseburger', 'A quarter pound of Kobe beef with a thick slice of cheddar cheese and applewood-smoked bacon.', 1200, 0, NULL),
	(507, 7, 'pasta', 'Fettuccine Alfredo', 'Fresh, house-made fettucine tossed in a thick and creamy Alfredo sauce.', 1200, 0, NULL),
	(508, 8, 'pasta', 'Cheese Ravioli', 'Delicious cheese-stuffed ravioli topped with marinara and a trio of melted cheeses.', 1200, 1, NULL),
	(509, 9, 'pasta', 'Mediterranean Chicken', 'Grilled chicken, marinated and served with linguini topped with a light vinaigrette, spinach and imported olives.', 1600, 0, NULL),
	(510, 18, 'entree', 'Chicken Parmesan', 'Tender chicken breasts, lightly breaded and baked with our signature cheese and marinara sauce.', 1450, 1, NULL),
	(511, 19, 'entree', 'NY Steak', 'Herb-marinated 12 ounce center-cut NY strip steak broiled with garlic butter.', 1400, 0, NULL),
	(512, 15, 'entree', 'Grilled Prawns', 'Juicy grilled prawns, lightly brushed with herb butter.', 1395, 0, NULL),
	(513, 16, 'entree', 'Chicken with Wild Mushrooms', 'Tender roasted chicken, bathed in a rich cream sauce and served under a layer of wild mushrooms and garlic.', 1100, 1, NULL),
	(514, 17, 'entree', 'Filet Mignon ', '10 ounce center cut filet, marinated in a medley of herbs, broiled to perfection and served with our famous veal sauce. ', 2295, 1, NULL),
	(515, 21, 'appetizer', 'Four Cheese Garlic Bread', 'Toasted French bread topped with romano, cheddar, jack and parmesan, with a light layer of roasted garlic butter.', 400, 0, NULL),
	(516, 22, 'appetizer', 'French Fries', 'Hand cut wedges of Yukon Gold potatoes fried in canola oil and tossed with truffle sea salt.', 600, 0, NULL);
/*!40000 ALTER TABLE `menu_item` ENABLE KEYS */;


-- Dumping structure for table smart_rms.menu_item_ingredient
CREATE TABLE IF NOT EXISTS `menu_item_ingredient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `ingredient_id` int(11) DEFAULT NULL,
  `amount` decimal(10,3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_menu_item_ingredient_menu_item` (`item_id`),
  KEY `FK_menu_item_ingredient_ingredient` (`ingredient_id`),
  CONSTRAINT `FK_menu_item_ingredient_ingredient` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`),
  CONSTRAINT `FK_menu_item_ingredient_menu_item` FOREIGN KEY (`item_id`) REFERENCES `menu_item` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_rms.menu_item_ingredient: ~13 rows (approximately)
DELETE FROM `menu_item_ingredient`;
/*!40000 ALTER TABLE `menu_item_ingredient` DISABLE KEYS */;
INSERT INTO `menu_item_ingredient` (`id`, `item_id`, `ingredient_id`, `amount`) VALUES
	(1, 508, 0, 0.100),
	(2, 506, 0, 0.050),
	(3, 506, 4, 0.050),
	(4, 505, 1, 0.200),
	(5, 511, 1, 0.200),
	(6, 43, 2, 0.400),
	(7, 1, 5, 0.200),
	(8, 2, 5, 0.200),
	(9, 53, 5, 0.200),
	(10, 58, 5, 0.200),
	(11, 509, 5, 0.200),
	(12, 513, 5, 0.200),
	(13, 507, 0, 0.090);
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
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.order_item: ~57 rows (approximately)
DELETE FROM `order_item`;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` (`order_item_id`, `item_id`, `order_no`, `quantity`) VALUES
	(157, 513, 1121, 1),
	(158, 507, 1121, 1),
	(159, 506, 1121, 1),
	(160, 511, 1121, 1),
	(161, 53, 1121, 4),
	(162, 508, 1121, 1),
	(163, 2, 1122, 2),
	(164, 508, 1122, 1),
	(165, 58, 1122, 1),
	(166, 508, 1123, 2),
	(167, 2, 1124, 1),
	(168, 127, 1125, 1),
	(169, 508, 1126, 4),
	(170, 2, 1127, 2),
	(171, 507, 1127, 1),
	(172, 43, 1127, 2),
	(173, 1, 1128, 1),
	(174, 2, 1128, 1),
	(175, 505, 1128, 1),
	(176, 502, 1128, 1),
	(177, 2, 1129, 2),
	(178, 53, 1129, 2),
	(179, 508, 1129, 1),
	(180, 1, 1129, 1),
	(181, 507, 1129, 2),
	(182, 53, 1130, 1),
	(183, 507, 1130, 1),
	(184, 511, 1131, 1),
	(185, 505, 1131, 2),
	(186, 508, 1132, 2),
	(187, 1, 1132, 1),
	(188, 58, 1132, 1),
	(189, 501, 1133, 1),
	(190, 504, 1133, 1),
	(191, 127, 1133, 2),
	(192, 501, 1134, 1),
	(193, 503, 1134, 2),
	(194, 512, 1134, 1),
	(195, 502, 1134, 1),
	(196, 505, 1135, 1),
	(197, 509, 1135, 1),
	(198, 501, 1135, 1),
	(199, 503, 1135, 2),
	(200, 506, 1136, 1),
	(201, 505, 1136, 2),
	(202, 510, 1136, 1),
	(203, 513, 1136, 2),
	(204, 505, 1137, 1),
	(205, 502, 1137, 2),
	(206, 515, 1138, 1),
	(207, 505, 1138, 2),
	(208, 502, 1138, 1),
	(209, 513, 1138, 2),
	(210, 509, 1139, 1),
	(211, 127, 1139, 2),
	(212, 503, 1139, 2),
	(213, 512, 1139, 1);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;


-- Dumping structure for table smart_rms.promotion
CREATE TABLE IF NOT EXISTS `promotion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `image_path` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
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
  `status` enum('PENDING','LATE,''ACTIVE','COMPLETE','NO-SHOW','CANCELLED') DEFAULT 'PENDING',
  PRIMARY KEY (`res_id`),
  KEY `FK_reservation_customer` (`customer_id`),
  CONSTRAINT `FK_reservation_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1024 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.reservation: ~2 rows (approximately)
DELETE FROM `reservation`;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` (`res_id`, `placed_time`, `date`, `meal_time`, `placed_by`, `customer_id`, `status`) VALUES
	(1022, '2017-01-16 10:37:40', '2017-01-16', 'LUNCH', 'rec01', 53, 'PENDING'),
	(1023, '2017-01-16 20:54:46', '2017-01-18', 'DINNER', 'rec01', 53, 'PENDING');
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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.reservation_table: ~5 rows (approximately)
DELETE FROM `reservation_table`;
/*!40000 ALTER TABLE `reservation_table` DISABLE KEYS */;
INSERT INTO `reservation_table` (`res_tb_id`, `res_id`, `table_id`) VALUES
	(40, 1022, 10),
	(41, 1022, 9),
	(42, 1022, 13),
	(43, 1023, 9),
	(44, 1023, 15);
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
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `user_type` varchar(50) DEFAULT NULL,
  `f_name` varchar(50) DEFAULT NULL,
  `l_name` varchar(50) DEFAULT NULL,
  `token` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table smart_rms.user_account: ~4 rows (approximately)
DELETE FROM `user_account`;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` (`user_id`, `username`, `password`, `user_type`, `f_name`, `l_name`, `token`) VALUES
	(1, 'chef01', '94f3b3a16d8ce064c808b16bee5003c5', 'CHEF', 'Michael', 'Scott', NULL),
	(2, 'rec01', '1dc90e80c77fe245a82ea7ed30d1f849', 'RECEPTIONIST', 'Pam', 'Beesly', NULL),
	(4, 'cash01', '94f3b3a16d8ce064c808b16bee5003c5', 'CASHIER', 'David', 'Wallace', NULL),
	(5, 'admin', '80c9ef0fb86369cd25f90af27ef53a9e', 'ADMIN', 'John', 'Smith', NULL);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
