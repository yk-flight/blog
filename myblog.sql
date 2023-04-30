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

 Date: 01/05/2023 01:20:04
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
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1647951751875133440, '内容管理', 0, 'content', NULL, 0, 0, 0, 'content', 1, '2023-04-17 21:17:07', '2023-04-29 14:18:27');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1647951751875133441, '网站管理', 0, 'website', NULL, 0, 0, 0, 'message', 2, '2023-04-17 21:17:46', '2023-04-28 06:21:28');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1647951751875133442, '项目文档', 0, 'https://www.zrkizzy.com', NULL, 0, 1, 0, 'guide', 5, '2023-04-17 21:19:30', '2023-04-29 14:15:50');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1647952968122630144, '文章管理', 1647951751875133440, 'article', 'content/article/index', 0, 0, 0, 'article-create', 1, '2023-04-17 21:22:42', '2023-04-26 01:51:59');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1648539108017963008, '评论管理', 1647951751875133440, 'comment', 'content/comment/index', 0, 0, 0, 'comments', 2, '2023-04-19 12:09:46', '2023-04-26 01:52:00');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651040949146484736, '留言管理', 1647951751875133440, 'message', 'content/message/index', 0, 0, 0, 'message', 3, '2023-04-26 09:51:28', '2023-04-26 01:53:57');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651040949146484737, '分类管理', 1647951751875133440, 'category', 'content/category/index', 0, 0, 0, 'category', 4, '2023-04-26 09:52:57', '2023-04-26 01:53:59');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651042040491802625, '标签管理', 1647951751875133440, 'tags', 'content/tags/index', 0, 0, 0, 'tags', 5, '2023-04-26 09:53:51', '2023-04-26 01:54:48');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651217423560343552, '文件管理', 1647951751875133441, 'file', 'website/file/index', 0, 0, 0, 'article-ranking', 1, '2023-04-26 21:33:29', '2023-04-26 13:54:53');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651217423560343553, '相册管理', 1647951751875133441, 'photo', 'website/photo/index', 0, 0, 0, 'tool', 2, '2023-04-26 21:33:31', '2023-04-26 13:54:25');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651217423560343554, '页面管理', 1647951751875133441, 'page', 'website/page/index', 0, 0, 0, 'documentation', 3, '2023-04-26 21:33:29', '2023-04-26 13:55:21');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651217423560343555, '角色管理', 1652313423859417088, 'role', 'system/role/index', 0, 0, 0, 'role', 4, '2023-04-26 21:33:29', '2023-04-29 14:11:36');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651217423560343556, '友链管理', 1647951751875133441, 'link', 'website/link/index', 0, 0, 0, 'personnel', 5, '2023-04-26 21:33:29', '2023-04-26 13:53:02');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651217423560343557, '用户管理', 1652313423859417088, 'user', 'system/user/index', 0, 0, 0, 'personnel-manage', 6, '2023-04-26 21:33:29', '2023-04-29 14:11:16');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651217423560343558, '公告管理', 1647951751875133441, 'notice', 'website/notice/index', 0, 0, 0, 'email', 7, '2023-04-26 21:33:29', '2023-04-26 13:53:49');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651217423560343559, '字典管理', 1652313423859417088, 'dictionary', 'system/dictionary/index', 0, 0, 0, 'dict', 8, '2023-04-26 21:33:29', '2023-04-29 14:11:32');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651217423560343560, '菜单管理', 1652313423859417088, 'menu', 'system/menu/index', 0, 0, 0, 'menu', 9, '2023-04-26 21:33:29', '2023-04-29 14:11:25');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651217423560343561, '关于我', 1647951751875133441, 'about', 'website/about/index', 0, 0, 0, 'edit', 10, '2023-04-26 21:33:29', '2023-04-26 13:52:41');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651227500568641536, '系统监控', 0, 'monitor', NULL, 0, 0, 0, 'monitor', 4, '2023-04-26 22:15:11', '2023-04-29 14:07:25');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651227500568641537, '在线用户', 1651227500568641536, 'online', 'monitor/online/index', 0, 0, 0, 'online', 1, '2023-04-26 22:15:55', '2023-04-26 14:21:40');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651227500568641538, '服务监控', 1651227500568641536, 'server', 'monitor/server/index', 0, 0, 0, 'server', 2, '2023-04-26 22:16:24', '2023-04-26 14:21:47');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651227500568641539, '缓存监控', 1651227500568641536, 'cache', 'monitor/cache/index', 0, 0, 0, 'redis', 3, '2023-04-26 22:16:59', '2023-04-26 14:21:54');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651227500568641540, '操作日志', 1651227500568641536, 'operate-log', 'monitor/operate-log/index', 0, 0, 0, 'form', 4, '2023-04-26 22:18:09', '2023-04-26 14:22:03');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1651227500568641541, '登录日志', 1651227500568641536, 'login-log', 'monitor/login-log/index', 0, 0, 0, 'logininfor', 5, '2023-04-26 22:18:32', '2023-04-26 14:24:06');
INSERT INTO `tb_menu` (`id`, `name`, `parent_id`, `path`, `component`, `is_cache`, `is_link`, `visible`, `icon`, `order`, `create_time`, `update_time`) VALUES (1652313423859417088, '系统管理', 0, 'system', NULL, 0, 0, 0, 'system', 3, '2023-04-29 22:08:18', '2023-04-29 14:08:20');
COMMIT;

-- ----------------------------
-- Table structure for tb_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_role`;
CREATE TABLE `tb_menu_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单角色关联表';

-- ----------------------------
-- Records of tb_menu_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1647963394059272192, 1633646396710060032, 1647951751875133440);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1647963394059272193, 1633646396710060032, 1647951751875133441);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1647963394059272194, 1633646396710060032, 1647951751875133442);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1647963893261139968, 1633646396710060032, 1647952968122630144);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1648539108017963009, 1633646396710060032, 1648539108017963008);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651042040491802624, 1633646396710060032, 1648539108017963008);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651042293655797760, 1633646396710060032, 1651040949146484736);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651042293655797761, 1633646396710060032, 1651040949146484737);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651042293655797762, 1633646396710060032, 1651042040491802625);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651218840622727168, 1633646396710060032, 1651217423560343552);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651218840622727169, 1633646396710060032, 1651217423560343553);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651218840622727170, 1633646396710060032, 1651217423560343554);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651218840622727171, 1633646396710060032, 1651217423560343555);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651218840626921472, 1633646396710060032, 1651217423560343556);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651218840626921473, 1633646396710060032, 1651217423560343557);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651218840626921474, 1633646396710060032, 1651217423560343558);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651218840626921475, 1633646396710060032, 1651217423560343559);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651218840626921476, 1633646396710060032, 1651217423560343560);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651218840626921477, 1633646396710060032, 1651217423560343561);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651230337214185472, 1633646396710060032, 1651227500568641536);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651230337214185473, 1633646396710060032, 1651227500568641537);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651230337214185474, 1633646396710060032, 1651227500568641538);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651230337214185475, 1633646396710060032, 1651227500568641539);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651230337214185476, 1633646396710060032, 1651227500568641540);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1651230337214185477, 1633646396710060032, 1651227500568641541);
INSERT INTO `tb_menu_role` (`id`, `role_id`, `menu_id`) VALUES (1652313423859417089, 1633646396710060032, 1652313423859417088);
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
INSERT INTO `tb_resource` (`id`, `name`, `description`, `method`, `url`, `module_id`, `create_time`, `update_time`) VALUES (1652715951180742656, '获取用户个人信息', '获取用户个人信息', 'GET', '/admin/user/getUserInfo', 1636182933754609665, '2023-05-01 00:46:55', '2023-04-30 16:47:03');
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
INSERT INTO `tb_user` (`id`, `username`, `password`, `nickname`, `avatar`, `status`, `create_time`, `update_time`) VALUES (1633027432405532672, '1072876976@qq.com', '$2a$10$Kbb.k6umlihAQBRtSxNGiuHaqT5YP4IVP9Cmytfn8xsecp7Z4e50G', '世纪末的架构师', 'https://s1.ax1x.com/2023/04/26/p9K181A.jpg', 1, '2023-03-07 16:52:16', '2023-04-28 07:04:14');
COMMIT;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_role` (`id`, `user_id`, `role_id`) VALUES (1635171865322323968, 1633027432405532672, 1633646396710060032);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
