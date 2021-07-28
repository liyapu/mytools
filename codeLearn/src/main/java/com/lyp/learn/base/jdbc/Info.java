package com.lyp.learn.base.jdbc;

/**
 * @author liyapu
 * @date 2021-07-28 17:20
 * @desc

创建 人员表
CREATE TABLE `test`.`person` (
`id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
`name` VARCHAR(45) NULL COMMENT '姓名',
`address` VARCHAR(45) NULL COMMENT '地址',
`age` INT NULL,
`create_time` DATETIME NULL COMMENT '创建时间',
`update_time` DATETIME NULL COMMENT '更新时间',
PRIMARY KEY (`id`))
COMMENT = '人员表';

INSERT INTO `test`.`person` (`id`, `name`, `address`, `age`, `create_time`, `update_time`) VALUES ('1', '张三', '北京', '11', '2021-07-21 11:34:55', '2021-07-21 12:34:55');
INSERT INTO `test`.`person` (`id`, `name`, `address`, `age`, `create_time`, `update_time`) VALUES ('2', '李四', '河南', '18', '2021-07-22 11:34:55', '2021-07-22 12:34:55');
INSERT INTO `test`.`person` (`id`, `name`, `address`, `age`, `create_time`, `update_time`) VALUES ('3', '王五', '天津', '20', '2021-07-23 11:34:55', '2021-07-23 10:34:55');


 */
public interface Info {
}
