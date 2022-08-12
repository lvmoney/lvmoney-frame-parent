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


-- 导出 ds0 的数据库结构
CREATE DATABASE IF NOT EXISTS `ds0` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ds0`;

-- 导出  表 ds0.dictionaries_info 结构
CREATE TABLE IF NOT EXISTS `dictionaries_info` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `tenant_id` varchar(50) NOT NULL DEFAULT '',
  `group_code` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL,
  `remark` varchar(50) NOT NULL,
  `create_uid` varchar(50) NOT NULL,
  `update_uid` varchar(50) NOT NULL,
  `sys_status` tinyint(2) NOT NULL DEFAULT 0,
  `level` tinyint(2) NOT NULL DEFAULT 0,
  `org_id` bigint(20) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds0.dictionaries_info 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `dictionaries_info` DISABLE KEYS */;
INSERT INTO `dictionaries_info` (`id`, `name`, `tenant_id`, `group_code`, `code`, `remark`, `create_uid`, `update_uid`, `sys_status`, `level`, `org_id`) VALUES
	(1, '周杰伦', '1', 'music', 'start', '顶级巨星', '1', '1', 0, 0, 1),
	(1486901571706744834, 'test', '', 'test', 'test', 'test', '1', '1', 1, 1, 0),
	(1486901801944674306, 'test', '', 'test', 'test', 'test', '1', '1', 1, 1, 0),
	(1486901827852890114, 'test', '', 'test', 'test', 'test', '1', '1', 1, 1, 0),
	(1486901830092648450, 'test', '', 'test', 'test', 'test', '1', '1', 1, 1, 0),
	(1486901846123278337, 'test', '', 'test', 'test', 'test', '1', '1', 1, 1, 0),
	(1486901847901663234, 'test', '', 'test', 'test', 'test', '1', '1', 1, 1, 0),
	(1486901848467894273, 'test', '', 'test', 'test', 'test', '1', '1', 1, 1, 0),
	(1486901849269006338, 'test', '', 'test', 'test', 'test', '1', '1', 1, 1, 0),
	(1486901849965260801, 'test', '', 'test', 'test', 'test', '1', '1', 1, 1, 0),
	(1486901850749595649, 'test', '', 'test', 'test', 'test', '1', '1', 1, 1, 0);
/*!40000 ALTER TABLE `dictionaries_info` ENABLE KEYS */;

-- 导出  表 ds0.toca_1 结构
CREATE TABLE IF NOT EXISTS `toca_1` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL,
  `num` int(11) NOT NULL,
  `tenant_id` bigint(20) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `org_id` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds0.toca_1 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `toca_1` DISABLE KEYS */;
INSERT INTO `toca_1` (`id`, `name`, `phone`, `code`, `num`, `tenant_id`, `create_date`, `org_id`) VALUES
	(5, '5', '5', '5', 5, 5, '2022-01-29 22:58:32', 1),
	(1476749491390214145, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:58:33', 1),
	(1490942669273985025, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:58:34', 1),
	(1490942720473853954, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:47:40', 0),
	(1490943215619899394, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:49:43', 0);
/*!40000 ALTER TABLE `toca_1` ENABLE KEYS */;

-- 导出  表 ds0.toca_2 结构
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

-- 正在导出表  ds0.toca_2 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `toca_2` DISABLE KEYS */;
INSERT INTO `toca_2` (`id`, `name`, `phone`, `code`, `num`, `tenant_id`, `create_date`, `org_id`) VALUES
	(6, '6', '6', '6', 6, 6, '2021-01-04 06:42:57', 0),
	(1476749491390214145, 'test', 'test', 'test', 100, 4000, '2022-01-04 06:42:57', 0),
	(1490942669273985025, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:47:11', 0),
	(1490942720473853954, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:47:40', 0),
	(1490943215619899394, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:49:43', 0);
/*!40000 ALTER TABLE `toca_2` ENABLE KEYS */;

-- 导出  表 ds0.toca_3 结构
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

-- 正在导出表  ds0.toca_3 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `toca_3` DISABLE KEYS */;
INSERT INTO `toca_3` (`id`, `name`, `phone`, `code`, `num`, `tenant_id`, `create_date`, `org_id`) VALUES
	(7, '7', '7', '7', 7, 7, '2021-01-04 06:43:01', 0),
	(1476749491390214145, 'test', 'test', 'test', 100, 4000, '2022-01-04 06:43:01', 0),
	(1490942669273985025, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:47:11', 0),
	(1490942720473853954, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:47:40', 0),
	(1490943215619899394, 'test', 'test', 'test', 100, 4000, '2022-01-29 22:49:43', 0);
/*!40000 ALTER TABLE `toca_3` ENABLE KEYS */;

-- 导出  表 ds0.t_config 结构
CREATE TABLE IF NOT EXISTS `t_config` (
  `id` int(11) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modify_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds0.t_config 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_config` ENABLE KEYS */;

-- 导出  表 ds0.t_order0 结构
CREATE TABLE IF NOT EXISTS `t_order0` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `config_id` int(11) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modify_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds0.t_order0 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_order0` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order0` ENABLE KEYS */;

-- 导出  表 ds0.t_order1 结构
CREATE TABLE IF NOT EXISTS `t_order1` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `config_id` int(11) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modify_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds0.t_order1 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_order1` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order1` ENABLE KEYS */;

-- 导出  表 ds0.t_order_item0 结构
CREATE TABLE IF NOT EXISTS `t_order_item0` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modify_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds0.t_order_item0 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_order_item0` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order_item0` ENABLE KEYS */;

-- 导出  表 ds0.t_order_item1 结构
CREATE TABLE IF NOT EXISTS `t_order_item1` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `last_modify_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ds0.t_order_item1 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_order_item1` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order_item1` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
