1、activti6.0
2、新建数据库activti启动项目的时候，系统会自动新增activiti默认28个表到数据数据库中
3、可以通过test下junit去测试不同的业务场景
4、新增两个表，
CREATE TABLE `user` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`type` INT(2) NULL DEFAULT NULL,
	`delete_flag` INT(2) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=3
;
CREATE TABLE `vacation_form` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(50) NULL DEFAULT NULL,
	`content` VARCHAR(50) NULL DEFAULT NULL,
	`applicant` VARCHAR(50) NULL DEFAULT NULL,
	`approver` VARCHAR(50) NULL DEFAULT NULL,
	`state` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=6
;

INSERT INTO `user` (`id`, `name`, `type`, `delete_flag`) VALUES
	(1, 'wang', 1, 1),
	(2, 'xu', 2, 1);
启动项目就可以看到一个简单的审批流程demo
wang 为申请人
xu 为审批人


学习一下npmn流程图
中国特色复杂的流程如会签等的说明在百度云盘

