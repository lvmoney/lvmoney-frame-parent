#介绍
1、引入该模块很简单作为系统的modules或者jar直接引入到项目即可
2、shiro的token验证和权限校验均做到了redis中，引入系统后，需要加入redis的配置
#redis single
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.pool.max-active=300
spring.redis.database=0
spring.redis.pool.max-wait=-1
spring.redis.pool.max-idle=100
spring.redis.pool.min-idle=20
spring.redis.timeout=60000

或者

#redis cluster
spring.redis.cluster.nodes=192.168.159.129:7001,192.168.159.129:7002,192.168.159.129:7003,192.168.159.129:7004,192.168.159.129:7005,192.168.159.129:7006
spring.redis.pool.max-active=300
spring.redis.database=0
spring.redis.pool.max-wait=-1
spring.redis.pool.max-idle=100
spring.redis.pool.min-idle=20
spring.redis.timeout=60000
3、该模块采用token的方式进行身份验证，没有用session的方式。每个需要鉴权的访问都需要在head配置token值
4、已做了过滤器，当调用权限校验标签的时候，会自动对token进行登录校验，不需要在代码中显示的调用shiro的登录校验方式。
5、对于需要权限校验的访问，加入配置：
@RequiresPermissions({"/auth/index"}):这里是权限的资源路径和系统的真实路径无关，只是一个权限映射关系
@RequiresRoles("USER")：这里是一个角色配置，和资源路径做对应即可
6、不需要权限校验的需要在application.properties同目录新建shiroConfig.properites,并进行配置，详见样例。
shiro.filterChainDefinitionList[4]=/auth/logout=anon
默认的过滤器配置：shiro.filterChainDefinitionList[5]=/**=frameShiroFilter
7、redis中shiro的数据结构如下：
{
  "expire": 18000,//redis失效时间
  "permissions": [//资源权限路径
    "/auth/index"
  ],
  "roles": [//角色列表
    "USER"
  ],
  "username": "test"//系统唯一的用户名称
}

key值为username
8、本模块最好和jwt模块联合使用，否则4中的验证会报错，当然也可以单独使用只是redis中需要有如下数据，来校验token

{
  "expire": 18000,//失效时间
  "id": "1234567",//用户id
  "password": "1234121",//密码
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjM0NTY3In0.aSyzM5EM4pkUzN1bQUionTbMNF_CVnxmHXx2Lqqc_mE",//token
  "username": "test"//用户名
}

key值为：token值

9、系统已经提供保存权限的接口ShiroRedisService.saveShiroData(),建议在用户登录成功后讲数据同步到redis（数据格式如7）中
10、一般原始的权限结构在mysql中保存，一般有：用户表，角色表，用户角色关系表，资源表，角色资源关系表。简单的样例如下
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `res_url` varchar(255) DEFAULT NULL COMMENT '资源url',
  `user_type` int(11) DEFAULT NULL COMMENT '资源类型   1:菜单    2：按钮',
  `parent_id` int(11) DEFAULT NULL COMMENT '父资源',
  `user_sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resources` VALUES ('1', '系统设置', '/system', '0', '0', '1');
INSERT INTO `sys_resources` VALUES ('2', '用户管理', '/usersPage', '1', '1', '2');
INSERT INTO `sys_resources` VALUES ('3', '角色管理', '/rolesPage', '1', '1', '3');
INSERT INTO `sys_resources` VALUES ('4', '资源管理', '/resourcesPage', '1', '1', '4');
INSERT INTO `sys_resources` VALUES ('5', '添加用户', '/users/add', '2', '2', '5');
INSERT INTO `sys_resources` VALUES ('6', '删除用户', '/users/delete', '2', '2', '6');
INSERT INTO `sys_resources` VALUES ('7', '添加角色', '/roles/add', '2', '3', '7');
INSERT INTO `sys_resources` VALUES ('8', '删除角色', '/roles/delete', '2', '3', '8');
INSERT INTO `sys_resources` VALUES ('9', '添加资源', '/resources/add', '2', '4', '9');
INSERT INTO `sys_resources` VALUES ('10', '删除资源', '/resources/delete', '2', '4', '10');
INSERT INTO `sys_resources` VALUES ('11', '分配角色', '/users/saveUserRoles', '2', '2', '11');
INSERT INTO `sys_resources` VALUES ('13', '分配权限', '/roles/saveRoleResources', '2', '3', '12');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'SUPERADMINISTATOR');
INSERT INTO `sys_role` VALUES ('2', 'ADMINISTATOR');
INSERT INTO `sys_role` VALUES ('3', 'USER');

-- ----------------------------
-- Table structure for sys_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `resources_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources` VALUES ('1', '1', '2');
INSERT INTO `sys_role_resources` VALUES ('2', '1', '3');
INSERT INTO `sys_role_resources` VALUES ('3', '1', '4');
INSERT INTO `sys_role_resources` VALUES ('4', '1', '5');
INSERT INTO `sys_role_resources` VALUES ('5', '1', '6');
INSERT INTO `sys_role_resources` VALUES ('6', '1', '7');
INSERT INTO `sys_role_resources` VALUES ('7', '1', '8');
INSERT INTO `sys_role_resources` VALUES ('8', '1', '9');
INSERT INTO `sys_role_resources` VALUES ('9', '1', '10');
INSERT INTO `sys_role_resources` VALUES ('10', '1', '11');
INSERT INTO `sys_role_resources` VALUES ('11', '1', '13');
INSERT INTO `sys_role_resources` VALUES ('12', '2', '2');
INSERT INTO `sys_role_resources` VALUES ('13', '2', '3');
INSERT INTO `sys_role_resources` VALUES ('14', '2', '4');
INSERT INTO `sys_role_resources` VALUES ('15', '2', '9');
INSERT INTO `sys_role_resources` VALUES ('16', '3', '2');
INSERT INTO `sys_role_resources` VALUES ('17', '3', '3');
INSERT INTO `sys_role_resources` VALUES ('18', '3', '4');
INSERT INTO `sys_role_resources` VALUES ('19', '3', '5');
INSERT INTO `sys_role_resources` VALUES ('20', '3', '7');
INSERT INTO `sys_role_resources` VALUES ('21', '3', '8');
INSERT INTO `sys_role_resources` VALUES ('22', '3', '9');
INSERT INTO `sys_role_resources` VALUES ('23', '3', '10');
INSERT INTO `sys_role_resources` VALUES ('24', '9', '9');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(33) DEFAULT NULL,
  `pass_word` varchar(33) DEFAULT NULL,
  `user_enable` int(10) DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin1', 'admin1', '1');
INSERT INTO `sys_user` VALUES ('2', 'user1', 'user1', '1');
INSERT INTO `sys_user` VALUES ('3', 'user2', '121', '0');
INSERT INTO `sys_user` VALUES ('4', 'user3', 'user3', '1');
INSERT INTO `sys_user` VALUES ('5', 'user4', 'user4', '1');
INSERT INTO `sys_user` VALUES ('6', 'user5', 'user5', '1');
INSERT INTO `sys_user` VALUES ('7', 'user6', 'user6', '1');
INSERT INTO `sys_user` VALUES ('8', 'user7', 'user7', '1');
INSERT INTO `sys_user` VALUES ('9', 'user8', 'user8', '1');
INSERT INTO `sys_user` VALUES ('10', 'user9', 'user9', '1');
INSERT INTO `sys_user` VALUES ('11', 'user10', 'user10', '1');
INSERT INTO `sys_user` VALUES ('12', 'user11', 'user11', '1');
INSERT INTO `sys_user` VALUES ('13', 'user12', 'user12', '1');
INSERT INTO `sys_user` VALUES ('14', 'user13', 'user13', '1');
INSERT INTO `sys_user` VALUES ('15', 'user14', 'user14', '1');
INSERT INTO `sys_user` VALUES ('16', 'user15', 'user15', '1');
INSERT INTO `sys_user` VALUES ('17', 'user16', 'user16', '1');
INSERT INTO `sys_user` VALUES ('18', 'user17', 'user17', '1');
INSERT INTO `sys_user` VALUES ('19', 'user18', 'user18', '1');
INSERT INTO `sys_user` VALUES ('21', 'user20', 'user20', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '23', '2');
INSERT INTO `sys_user_role` VALUES ('2', '1', '1');
INSERT INTO `sys_user_role` VALUES ('3', '2', '2');
INSERT INTO `sys_user_role` VALUES ('4', '3', '1');

-- ----------------------------
-- Procedure structure for init_shiro_demo
-- ----------------------------
DROP PROCEDURE IF EXISTS `init_shiro_demo`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `init_shiro_demo`()
BEGIN	
/*
SQLyog 企业版 - MySQL GUI v7.14 
MySQL - 5.6.16-log : Database - 
*********************************************************************
*/
/*表结构插入*/
DROP TABLE IF EXISTS `u_permission`;
CREATE TABLE `u_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*Table structure for table `u_role` */
DROP TABLE IF EXISTS `u_role`;
CREATE TABLE `u_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*Table structure for table `u_role_permission` */
DROP TABLE IF EXISTS `u_role_permission`;
CREATE TABLE `u_role_permission` (
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*Table structure for table `u_user` */
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` bigint(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*Table structure for table `u_user_role` */
DROP TABLE IF EXISTS `u_user_role`;
CREATE TABLE `u_user_role` (
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
SQLyog 企业版 - MySQL GUI v7.14 
MySQL - 5.6.16-log : Database - i_wenyiba_com
*********************************************************************
*/
/*所有的表数据插入*/
/*Data for the table `u_permission` */
insert  into `u_permission`(`id`,`url`,`name`) values (4,'/permission/index.shtml','权限列表'),(6,'/permission/addPermission.shtml','权限添加'),(7,'/permission/deletePermissionById.shtml','权限删除'),(8,'/member/list.shtml','用户列表'),(9,'/member/online.shtml','在线用户'),(10,'/member/changeSessionStatus.shtml','用户Session踢出'),(11,'/member/forbidUserById.shtml','用户激活&禁止'),(12,'/member/deleteUserById.shtml','用户删除'),(13,'/permission/addPermission2Role.shtml','权限分配'),(14,'/role/clearRoleByUserIds.shtml','用户角色分配清空'),(15,'/role/addRole2User.shtml','角色分配保存'),(16,'/role/deleteRoleById.shtml','角色列表删除'),(17,'/role/addRole.shtml','角色列表添加'),(18,'/role/index.shtml','角色列表'),(19,'/permission/allocation.shtml','权限分配'),(20,'/role/allocation.shtml','角色分配');
/*Data for the table `u_role` */
insert  into `u_role`(`id`,`name`,`type`) values (1,'系统管理员','888888'),(3,'权限角色','100003'),(4,'用户中心','100002');
/*Data for the table `u_role_permission` */
insert  into `u_role_permission`(`rid`,`pid`) values (4,8),(4,9),(4,10),(4,11),(4,12),(3,4),(3,6),(3,7),(3,13),(3,14),(3,15),(3,16),(3,17),(3,18),(3,19),(3,20),(1,4),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20);
/*Data for the table `u_user` */
insert  into `u_user`(`id`,`nickname`,`email`,`pswd`,`create_time`,`last_login_time`,`status`) values (1,'管理员','admin','9c3250081c7b1f5c6cbb8096e3e1cd04','2016-06-16 11:15:33','2016-06-16 11:24:10',1),(11,'soso','8446666@qq.com','d57ffbe486910dd5b26d0167d034f9ad','2016-05-26 20:50:54','2016-06-16 11:24:35',1),(12,'8446666','8446666','4afdc875a67a55528c224ce088be2ab8','2016-05-27 22:34:19','2016-06-15 17:03:16',1);
/*Data for the table `u_user_role` */
insert  into `u_user_role`(`uid`,`rid`) values (12,4),(11,3),(11,4),(1,1);
   
    END
;;
DELIMITER ;


11、为了方便开发和测试frame.shiro.support:false 默认是不支持的，需要改为true，系统就支持token了。