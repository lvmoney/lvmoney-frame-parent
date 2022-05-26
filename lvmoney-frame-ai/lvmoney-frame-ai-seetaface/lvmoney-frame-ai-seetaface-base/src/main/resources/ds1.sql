-- --------------------------------------------------------
-- 主机:                           192.168.0.21
-- 服务器版本:                        10.4.19-MariaDB-log - MariaDB Server
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 ds1 的数据库结构
CREATE DATABASE IF NOT EXISTS `ds1` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ds1`;

-- 导出  表 ds1.toca_1 结构
CREATE TABLE IF NOT EXISTS `toca_1` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL,
  `num` int(11) NOT NULL,
  `tenant_id` bigint(20) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `org_id` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds1.toca_1 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `toca_1` DISABLE KEYS */;
INSERT INTO `toca_1` (`id`, `name`, `phone`, `code`, `num`, `tenant_id`, `create_date`, `org_id`) VALUES
	(1, '1', '1', '1', 1, 1, '2021-01-04 06:43:15', 0),
	(2, '2', '2', '2', 2, 2, '2021-01-04 06:43:15', 0),
	(1476749491390214145, 'test', 'test', 'test', 100, 4000, '2021-01-04 06:43:15', 0),
	(1490942669273985025, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:47:11', 0),
	(1490942720473853954, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:47:40', 0),
	(1490943215619899394, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:49:43', 0);
/*!40000 ALTER TABLE `toca_1` ENABLE KEYS */;

-- 导出  表 ds1.toca_2 结构
CREATE TABLE IF NOT EXISTS `toca_2` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL,
  `num` int(11) NOT NULL,
  `tenant_id` bigint(20) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `org_id` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds1.toca_2 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `toca_2` DISABLE KEYS */;
INSERT INTO `toca_2` (`id`, `name`, `phone`, `code`, `num`, `tenant_id`, `create_date`, `org_id`) VALUES
	(3, '3', '3', '3', 3, 3, '2021-01-04 06:43:11', 0),
	(1476749491390214145, 'test', 'test', 'test', 100, 4000, '2022-01-04 06:43:11', 0),
	(1490942669273985025, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:47:11', 0),
	(1490942720473853954, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:47:40', 0),
	(1490943215619899394, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:49:43', 0);
/*!40000 ALTER TABLE `toca_2` ENABLE KEYS */;

-- 导出  表 ds1.toca_3 结构
CREATE TABLE IF NOT EXISTS `toca_3` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL,
  `num` int(11) NOT NULL,
  `tenant_id` bigint(20) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `org_id` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds1.toca_3 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `toca_3` DISABLE KEYS */;
INSERT INTO `toca_3` (`id`, `name`, `phone`, `code`, `num`, `tenant_id`, `create_date`, `org_id`) VALUES
	(4, '4', '4', '4', 4, 4, '2022-01-04 06:43:08', 0),
	(1476749491390214145, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:58:39', 1),
	(1490942669273985025, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:47:11', 0),
	(1490942720473853954, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:47:40', 0),
	(1490943215619899394, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:58:37', 1);
/*!40000 ALTER TABLE `toca_3` ENABLE KEYS */;

-- 导出  表 ds1.t_config 结构
CREATE TABLE IF NOT EXISTS `t_config` (
  `id` int(11) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modify_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds1.t_config 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_config` ENABLE KEYS */;

-- 导出  表 ds1.t_order0 结构
CREATE TABLE IF NOT EXISTS `t_order0` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `config_id` int(11) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modify_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds1.t_order0 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_order0` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order0` ENABLE KEYS */;

-- 导出  表 ds1.t_order1 结构
CREATE TABLE IF NOT EXISTS `t_order1` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `config_id` int(11) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modify_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds1.t_order1 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_order1` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order1` ENABLE KEYS */;

-- 导出  表 ds1.t_order_item0 结构
CREATE TABLE IF NOT EXISTS `t_order_item0` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modify_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds1.t_order_item0 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_order_item0` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order_item0` ENABLE KEYS */;

-- 导出  表 ds1.t_order_item1 结构
CREATE TABLE IF NOT EXISTS `t_order_item1` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modify_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds1.t_order_item1 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_order_item1` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order_item1` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
