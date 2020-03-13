-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.4.3-MariaDB-log - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 oauth2_server 的数据库结构
CREATE DATABASE IF NOT EXISTS `oauth2_server` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `oauth2_server`;

-- 导出  表 oauth2_server.login_history 结构
CREATE TABLE IF NOT EXISTS `login_history` (
  `history_id` varchar(64) NOT NULL,
  `create_date` timestamp NULL DEFAULT current_timestamp(),
  `update_date` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `valid` int(11) DEFAULT 0,
  `remarks` varchar(255) DEFAULT NULL,
  `sort_priority` int(11) DEFAULT 0,
  `version` int(11) DEFAULT 0,
  `client_id` varchar(255) DEFAULT NULL,
  `device` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `username` varchar(40) NOT NULL,
  PRIMARY KEY (`history_id`),
  KEY `index_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  oauth2_server.login_history 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `login_history` DISABLE KEYS */;
INSERT INTO `login_history` (`history_id`, `create_date`, `update_date`, `valid`, `remarks`, `sort_priority`, `version`, `client_id`, `device`, `ip`, `username`) VALUES
	('1159358421412835329', '2019-08-08 14:59:22', '2019-08-08 14:59:22', 0, '登录成功', 0, 0, NULL, 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36', '0:0:0:0:0:0:0:1', 'zhangsan');
/*!40000 ALTER TABLE `login_history` ENABLE KEYS */;

-- 导出  表 oauth2_server.oauth_client 结构
CREATE TABLE IF NOT EXISTS `oauth_client` (
  `oauth_clientid` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `valid` int(11) DEFAULT 0,
  `remarks` varchar(255) DEFAULT NULL,
  `sort_priority` int(11) DEFAULT 0,
  `version` int(11) DEFAULT 0,
  `access_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(255) DEFAULT NULL,
  `application_name` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) NOT NULL,
  `authorized_grant_types` varchar(255) NOT NULL,
  `auto_approve` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) NOT NULL,
  `client_secret` varchar(255) NOT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oauth_clientid`),
  UNIQUE KEY `UKr2l8pjrmtn8e57w4qqoqnu7is` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  oauth2_server.oauth_client 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `oauth_client` DISABLE KEYS */;
INSERT INTO `oauth_client` (`oauth_clientid`, `create_date`, `update_date`, `valid`, `remarks`, `sort_priority`, `version`, `access_token_validity`, `additional_information`, `application_name`, `authorities`, `authorized_grant_types`, `auto_approve`, `client_id`, `client_secret`, `expiration_date`, `refresh_token_validity`, `resource_ids`, `scope`, `web_server_redirect_uri`) VALUES
	('1', NULL, NULL, 0, '测试明文:tgb.258', 0, 0, 3600, NULL, 'SampleClientId 测试应用', '["ROLE_TRUSTED_CLIENT"]', '["authorization_code","refresh_token","password"]', NULL, 'SampleClientId', '$2a$10$gcrWom7ubcRaVD1.6ZIrIeJP0mtPLH5J9V/.8Qth59lZ4B/5HMq96', NULL, 2592000, NULL, '["user_info"]', '["http://localhost:8030/login"]'),
	('2', NULL, NULL, 0, '测试明文:tgb.258', 0, 0, 3600, NULL, 'resource', '["ROLE_TRUSTED_CLIENT"]', '["authorization_code","refresh_token","password"]', NULL, 'resrouceId', '$2a$10$gcrWom7ubcRaVD1.6ZIrIeJP0mtPLH5J9V/.8Qth59lZ4B/5HMq96', NULL, 2592000, NULL, '["user_info"]', '["http://localhost:8030/login"]');
/*!40000 ALTER TABLE `oauth_client` ENABLE KEYS */;

-- 导出  表 oauth2_server.role 结构
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` varchar(64) NOT NULL,
  `code` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `valid` int(11) DEFAULT 1,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  oauth2_server.role 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`role_id`, `code`, `name`, `valid`, `create_date`, `update_date`) VALUES
	('1', 'ROLE_SUPER', '超级管理员', 1, '2019-08-07 10:17:03', '2019-08-07 10:17:03'),
	('2', 'ROLE_TRUSTED_CLIENT', '可信客户端', 1, '2019-08-07 10:17:29', '2019-08-07 10:17:29');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- 导出  表 oauth2_server.scope_definition 结构
CREATE TABLE IF NOT EXISTS `scope_definition` (
  `definition_id` varchar(64) NOT NULL,
  `create_date` timestamp NULL DEFAULT current_timestamp(),
  `update_date` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `valid` int(11) DEFAULT 0,
  `remarks` varchar(255) DEFAULT NULL,
  `sort_priority` int(11) DEFAULT 0,
  `version` int(11) DEFAULT 0,
  `definition` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`definition_id`),
  UNIQUE KEY `UKn908i6w2068oq5vs137q55ud6` (`scope`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  oauth2_server.scope_definition 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `scope_definition` DISABLE KEYS */;
INSERT INTO `scope_definition` (`definition_id`, `create_date`, `update_date`, `valid`, `remarks`, `sort_priority`, `version`, `definition`, `scope`) VALUES
	('1', NULL, NULL, 0, NULL, 0, 0, '昵称、头像、性别信息', 'user_info');
/*!40000 ALTER TABLE `scope_definition` ENABLE KEYS */;

-- 导出  表 oauth2_server.third_party_account 结构
CREATE TABLE IF NOT EXISTS `third_party_account` (
  `third_accountid` varchar(64) NOT NULL,
  `create_date` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `update_date` timestamp NULL DEFAULT NULL,
  `valid` int(11) DEFAULT 0,
  `remarks` varchar(255) DEFAULT NULL,
  `sort_priority` int(11) DEFAULT 0,
  `version` int(11) DEFAULT 0,
  `account_open_code` varchar(255) DEFAULT NULL,
  `client_id` varchar(32) NOT NULL,
  `third_party` varchar(20) NOT NULL,
  `third_party_account_id` varchar(100) NOT NULL,
  PRIMARY KEY (`third_accountid`),
  UNIQUE KEY `UKteg9nl8ovi8h8b69i4li6m2nx` (`third_party`,`third_party_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  oauth2_server.third_party_account 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `third_party_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `third_party_account` ENABLE KEYS */;

-- 导出  表 oauth2_server.user_account 结构
CREATE TABLE IF NOT EXISTS `user_account` (
  `user_accid` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `valid` int(11) DEFAULT 1 COMMENT '1：正常，0：删除，-1锁住',
  `remarks` varchar(255) DEFAULT NULL,
  `sort_priority` int(11) DEFAULT 0,
  `version` int(11) DEFAULT 0,
  `address` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `failure_count` int(11) DEFAULT 0,
  `failure_time` datetime DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `province` varchar(255) DEFAULT NULL,
  `username` varchar(40) NOT NULL,
  PRIMARY KEY (`user_accid`),
  UNIQUE KEY `UK2jc14awfu2adi6x4w35q6jife` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  oauth2_server.user_account 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` (`user_accid`, `create_date`, `update_date`, `valid`, `remarks`, `sort_priority`, `version`, `address`, `avatar_url`, `birthday`, `city`, `client_id`, `email`, `failure_count`, `failure_time`, `gender`, `mobile`, `nick_name`, `password`, `province`, `username`) VALUES
	('1', '2019-07-26 11:17:58', '2019-08-05 15:23:42', 1, '测试明文:tgb.258', 0, 18, '', NULL, NULL, '', NULL, '', 0, '2019-08-08 11:41:27', '男', '', '张三', '$2a$10$gcrWom7ubcRaVD1.6ZIrIeJP0mtPLH5J9V/.8Qth59lZ4B/5HMq96', '', 'zhangsan');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;

-- 导出  表 oauth2_server.user_role 结构
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_roleid` varchar(64) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  `role_id` varchar(64) DEFAULT NULL,
  `valid` int(11) DEFAULT 1,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`user_roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  oauth2_server.user_role 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_roleid`, `user_id`, `role_id`, `valid`, `create_date`, `update_date`) VALUES
	('1', '1', '1', 1, '2019-08-07 10:16:35', '2019-08-07 10:16:35'),
	('2', '1', '2', 1, '2019-08-07 10:19:07', '2019-08-07 10:19:07');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
