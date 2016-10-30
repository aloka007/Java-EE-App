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

-- Dumping structure for table company.deptsal
CREATE TABLE IF NOT EXISTS `deptsal` (
  `dnumber` int(11) NOT NULL,
  `totalsalary` float NOT NULL,
  PRIMARY KEY (`dnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table company.deptsal: ~3 rows (approximately)
DELETE FROM `deptsal`;
/*!40000 ALTER TABLE `deptsal` DISABLE KEYS */;
INSERT INTO `deptsal` (`dnumber`, `totalsalary`) VALUES
	(1, 0),
	(2, 0),
	(3, 0);
/*!40000 ALTER TABLE `deptsal` ENABLE KEYS */;


-- Dumping structure for table company.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '0',
  `superid` int(11) DEFAULT NULL,
  `salary` float DEFAULT NULL,
  `bdate` date DEFAULT '0000-00-00',
  `dno` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table company.employee: ~0 rows (approximately)
DELETE FROM `employee`;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`id`, `name`, `superid`, `salary`, `bdate`, `dno`) VALUES
	(1, 'john', 3, 100000, '1960-01-01', 1),
	(2, 'mary', 3, 50000, '1964-12-01', 3),
	(3, 'bob', NULL, 80000, '1974-02-07', 3),
	(4, 'tom', 1, 50000, '1970-01-17', 2),
	(5, 'bill', NULL, NULL, '1985-01-20', 1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
