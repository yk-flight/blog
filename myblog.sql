/*
 Navicat Premium Data Transfer

 Source Server         : blog@49.234.13.224
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 49.234.13.224:3306
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 19/04/2023 17:28:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单',
  `path` varchar(100) DEFAULT NULL COMMENT '访问路径',
  `component` varchar(100) DEFAULT NULL COMMENT '组件',
  `is_cache` tinyint(4) DEFAULT NULL COMMENT '是否缓存 （0: 不缓存 1: 缓存）',
  `is_link` tinyint(4) DEFAULT NULL COMMENT '是否外链 （0: 不是外链 1: 外链）',
  `visible` tinyint(4) DEFAULT NULL COMMENT '是否隐藏 （0: 不隐藏 1: 隐藏）',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `order` int(11) DEFAULT NULL COMMENT '菜单顺序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单表';

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
BEGIN;
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1647951751875133440, '内容管理', 0, 'article', NULL, 0, 0, 0, 'article', 1, '2023-04-17 21:17:07', '2023-04-19 09:27:53');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1647951751875133441, '消息管理', 0, 'message', NULL, 0, 0, 0, 'message', 2, '2023-04-17 21:17:46', '2023-04-19 08:49:53');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1647951751875133442, '博客首页', 0, 'https://www.zrkizzy.com', NULL, 0, 1, 0, 'guide', 3, '2023-04-17 21:19:30', '2023-04-19 09:27:54');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1647952968122630144, '发布文章', 1647951751875133440, 'publish', 'article/publish/index', 0, 0, 0, 'article-create', 2, '2023-04-17 21:22:42', '2023-04-19 09:27:56');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1648539108017963008, '查看文章', 1647951751875133440, 'view', NULL, 0, 0, 0, 'article-create', 1, '2023-04-19 12:09:46', '2023-04-19 09:27:57');
COMMIT;

-- ----------------------------
-- Table structure for tb_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_role`;
CREATE TABLE `tb_menu_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单角色关联表';

-- ----------------------------
-- Records of tb_menu_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1647963394059272192, 1633646396710060032, 1647951751875133440, '2023-04-17 22:01:59', '2023-04-17 14:02:03');
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1647963394059272193, 1633646396710060032, 1647951751875133441, '2023-04-17 22:02:31', '2023-04-17 14:02:32');
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1647963394059272194, 1633646396710060032, 1647951751875133442, '2023-04-17 22:02:57', '2023-04-17 14:02:59');
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1647963893261139968, 1633646396710060032, 1647952968122630144, '2023-04-17 22:03:20', '2023-04-17 14:03:22');
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1648539108017963009, 1633646396710060032, 1648539108017963008, '2023-04-19 12:10:58', '2023-04-19 04:11:00');
COMMIT;

-- ----------------------------
-- Table structure for tb_module
-- ----------------------------
DROP TABLE IF EXISTS `tb_module`;
CREATE TABLE `tb_module` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '模块名称',
  `description` varchar(255) DEFAULT NULL COMMENT '模块描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源模块表';

-- ----------------------------
-- Records of tb_module
-- ----------------------------
BEGIN;
INSERT INTO `tb_module` (`id`, `name`, `description`, `create_time`, `update_time`) VALUES (1636182933754609664, '基本模块', '个人博客系统基本模块，包括用户基本信息编辑、文件上传下载等功能', '2023-03-16 10:04:53', '2023-03-16 10:04:55');
INSERT INTO `tb_module` (`id`, `name`, `description`, `create_time`, `update_time`) VALUES (1636182933754609665, '系统模块', '主要用于管理用户、角色等信息', '2023-03-16 10:05:44', '2023-03-16 10:05:47');
COMMIT;

-- ----------------------------
-- Table structure for tb_module_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_module_role`;
CREATE TABLE `tb_module_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `module_id` bigint(20) NOT NULL COMMENT '模块ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='模块角色表';

-- ----------------------------
-- Records of tb_module_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_module_role` (`id`, `module_id`, `role_id`, `create_time`, `update_time`) VALUES (1636182933754609666, 1636182933754609665, 1633646396710060032, '2023-03-16 10:06:39', '2023-03-16 10:06:45');
COMMIT;

-- ----------------------------
-- Table structure for tb_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源名称',
  `description` varchar(255) DEFAULT NULL COMMENT '资源描述',
  `method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源请求方式',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源请求路径',
  `module_id` bigint(20) DEFAULT NULL COMMENT '模块ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源表';

-- ----------------------------
-- Records of tb_resource
-- ----------------------------
BEGIN;
INSERT INTO `tb_resource` (`id`, `name`, `description`, `method`, `url`, `module_id`, `create_time`, `update_time`) VALUES (1636187548919267328, '获取所有用户', '获取当前系统中所有用户信息', 'POST', '/admin/user/list', 1636182933754609665, '2023-03-16 10:17:39', '2023-04-13 07:06:14');
INSERT INTO `tb_resource` (`id`, `name`, `description`, `method`, `url`, `module_id`, `create_time`, `update_time`) VALUES (1636187548919267329, '获取所有角色', '获取当前系统中所有角色的信息', 'POST', '/admin/role/list', 1636182933754609665, '2023-03-16 10:24:57', '2023-04-13 07:06:16');
INSERT INTO `tb_resource` (`id`, `name`, `description`, `method`, `url`, `module_id`, `create_time`, `update_time`) VALUES (1647860341062762496, '获取当前登录用户', '获取当前系统登录用户', 'GET', '/admin/user/getLoginUser', 1636182933754609665, '2023-04-17 15:12:14', '2023-04-17 21:24:19');
INSERT INTO `tb_resource` (`id`, `name`, `description`, `method`, `url`, `module_id`, `create_time`, `update_time`) VALUES (1647952968122630145, '获取菜单列表', '获取菜单列表', 'GET', '/admin/menu/getRoutes', 1636182933754609665, '2023-04-17 21:24:19', '2023-04-19 08:28:04');
COMMIT;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(20) NOT NULL COMMENT '角色ID',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_role` (`id`, `name`, `description`, `create_time`, `update_time`) VALUES (1633646396710060032, 'ROLE_ADMIN', '超级管理员', '2023-03-09 09:50:27', '2023-03-09 09:50:27');
INSERT INTO `tb_role` (`id`, `name`, `description`, `create_time`, `update_time`) VALUES (1633657944153260032, 'ROLE_LOGIN', '默认用户', '2023-03-09 10:36:20', '2023-03-15 09:17:02');
INSERT INTO `tb_role` (`id`, `name`, `description`, `create_time`, `update_time`) VALUES (1636631667483344896, 'ROLE_TEST', '测试用户', '2023-03-17 15:32:51', '2023-03-17 15:38:23');
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态，0：禁用，1：启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` (`id`, `username`, `password`, `nickname`, `avatar`, `status`, `create_time`, `update_time`) VALUES (1633027432405532672, '1072876976@qq.com', '$2a$10$Kbb.k6umlihAQBRtSxNGiuHaqT5YP4IVP9Cmytfn8xsecp7Z4e50G', '世纪末的架构师', NULL, 1, '2023-03-07 16:52:16', '2023-04-14 07:16:39');
COMMIT;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_role` (`id`, `user_id`, `role_id`, `update_time`, `create_time`) VALUES (1635171865322323968, 1633027432405532672, 1633646396710060032, '2023-03-13 15:00:31', '2023-03-13 15:00:34');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
