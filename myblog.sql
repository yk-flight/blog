/*
 Navicat Premium Data Transfer

 Source Server         : MySQL@localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 22/07/2023 22:45:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_config`;
CREATE TABLE `tb_config`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户默认头像',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统配置表格' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_config
-- ----------------------------
INSERT INTO `tb_config` VALUES (1682761996493127680, 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/avatar/20230529192353.jpg', '2023-07-22 22:38:28', '2023-07-22 22:38:31');

-- ----------------------------
-- Table structure for tb_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件存储位置',
  `src` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件访问路径',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件类型',
  `mode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '上传模式',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '上传用户ID',
  `file_type_id` bigint(20) NULL DEFAULT NULL COMMENT '文件分类ID',
  `md5` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件唯一标识（MD5哈希值）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_file
-- ----------------------------
INSERT INTO `tb_file` VALUES (1663144125785964544, '20230529192353.jpg', 'avatar/20230529192353.jpg', 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/avatar/20230529192353.jpg', 72708, '.jpg', 'oss', 1653794265890816000, 1656676089927303170, 'bee1e765d72ab1eb20e3cdfb3de68abb', '2023-05-29 19:23:54', '2023-05-30 09:36:07');
INSERT INTO `tb_file` VALUES (1663144536764841984, '20230529192531.jpg', 'avatar/20230529192531.jpg', 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/avatar/20230529192531.jpg', 152031, '.jpg', 'oss', 1653794265890816000, 1656676089927303170, 'fd43ed5490db1894cb5c46cb300e1cf2', '2023-05-29 19:25:32', '2023-05-30 09:36:07');
INSERT INTO `tb_file` VALUES (1663144640041189376, '20230529192556.jpg', 'avatar/20230529192556.jpg', 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/avatar/20230529192556.jpg', 149077, '.jpg', 'oss', 1653794265890816000, 1656676089927303170, '8a8aa84284c227bad891e840561cc2a4', '2023-05-29 19:25:57', '2023-05-30 09:36:07');
INSERT INTO `tb_file` VALUES (1663144694911074304, '20230529192609.jpeg', 'avatar/20230529192609.jpeg', 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/avatar/20230529192609.jpeg', 444745, '.jpeg', 'oss', 1653794265890816000, 1656676089927303170, '1a3376b08f3ed911caa689e479fb552d', '2023-05-29 19:26:10', '2023-05-30 09:36:07');
INSERT INTO `tb_file` VALUES (1663196287383633920, '20230529225103.jpg', 'avatar/20230529225103.jpg', 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/avatar/20230529225103.jpg', 97766, '.jpg', 'oss', 1653794265890816000, 1656676089927303170, 'e960ac942f4cd2c10af9bcbacba5f4e6', '2023-05-29 22:51:10', '2023-05-30 09:36:07');
INSERT INTO `tb_file` VALUES (1663554855068762112, '20230530223559.png', 'cover/20230530223559.png', 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/cover/20230530223559.png', 181272, '.png', 'oss', 1653794265890816000, 1656676089927303168, 'fa74451429d178e2682a3cf6e833c019', '2023-05-30 22:35:59', '2023-05-30 22:35:59');
INSERT INTO `tb_file` VALUES (1663556688776527872, '20230530224316.jpg', 'cover/20230530224316.jpg', 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/cover/20230530224316.jpg', 50746, '.jpg', 'oss', 1653794265890816000, 1656676089927303168, '8c2eb375053690cb1ae6e9cdc2253a23', '2023-05-30 22:43:17', '2023-05-30 22:43:17');
INSERT INTO `tb_file` VALUES (1665355748768481280, '20230604215201.jpeg', 'cover/20230604215201.jpeg', 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/cover/20230604215201.jpeg', 604332, '.jpeg', 'oss', 1653794265890816000, 1656676089927303168, '67bd2983a12ea08bffa358d68e1169f7', '2023-06-04 21:52:06', '2023-06-04 21:52:06');
INSERT INTO `tb_file` VALUES (1667556674283503616, '20230610233746.jpg', 'article/20230610233746.jpg', 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/article/20230610233746.jpg', 1284390, '.jpg', 'oss', 1653794265890816000, 1656676089927303169, 'e8afdebb8420ad7822f75df100c8cc99', '2023-06-10 23:37:48', '2023-06-10 23:37:48');

-- ----------------------------
-- Table structure for tb_file_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_file_type`;
CREATE TABLE `tb_file_type`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件分类名称',
  `mark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件分类标识',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件分类描述',
  `sort` int(11) NULL DEFAULT NULL COMMENT '文件分类排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_file_type
-- ----------------------------
INSERT INTO `tb_file_type` VALUES (1656676089927303168, '封面图片', 'cover', '文章封面图片', 1, '2023-05-11 23:02:37', '2023-05-23 16:02:28');
INSERT INTO `tb_file_type` VALUES (1656676089927303169, '文章图片', 'article', '文章中的图片', 2, '2023-05-11 23:03:05', '2023-05-23 16:02:50');
INSERT INTO `tb_file_type` VALUES (1656676089927303170, '头像文件', 'avatar', '分类图片', 3, '2023-05-11 23:03:24', '2023-05-23 16:06:41');
INSERT INTO `tb_file_type` VALUES (1656676089927303171, '其他文件', 'other', '其他类型文件', 4, '2023-05-11 23:03:45', '2023-05-23 16:03:01');

-- ----------------------------
-- Table structure for tb_link
-- ----------------------------
DROP TABLE IF EXISTS `tb_link`;
CREATE TABLE `tb_link`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网站名称',
  `website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网站域名',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网站Logo',
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网站介绍',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_name`(`name`) USING BTREE COMMENT '网站名称索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '友情链接表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_link
-- ----------------------------
INSERT INTO `tb_link` VALUES (1671411053646315520, '半晴Miko', 'https://banq.ink/', 'https://www.static.banq.ink/sunnyBlog/avatar/fcc72d35fc928185c3e70773a29a310f.jpg', 'Semisunny', '2023-06-21 14:53:43', '2023-06-21 15:17:31');

-- ----------------------------
-- Table structure for tb_login_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_login_info`;
CREATE TABLE `tb_login_info`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录用户名称',
  `login_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录IP',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录位置',
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '浏览器版本',
  `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作系统',
  `status` tinyint(4) NOT NULL COMMENT '登录状态：0 失败; 1 成功',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录消息提示',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户登录信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_login_info
-- ----------------------------
INSERT INTO `tb_login_info` VALUES (1681897322843209728, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-20 13:22:24');
INSERT INTO `tb_login_info` VALUES (1681931684896833536, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-20 15:38:56');
INSERT INTO `tb_login_info` VALUES (1682397470891442176, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-21 22:29:49');
INSERT INTO `tb_login_info` VALUES (1682748766060281856, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-22 21:45:44');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问路径',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件',
  `is_cache` tinyint(4) NULL DEFAULT NULL COMMENT '是否缓存 （0: 不缓存 1: 缓存）',
  `is_link` tinyint(4) NULL DEFAULT NULL COMMENT '是否外链 （0: 不是外链 1: 外链）',
  `visible` tinyint(4) NULL DEFAULT NULL COMMENT '是否隐藏 （0: 不隐藏 1: 隐藏）',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `order` int(11) NULL DEFAULT NULL COMMENT '菜单顺序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES (1647951751875133440, '内容管理', 0, 'content', NULL, 0, 0, 0, 'content', 1, '2023-04-17 21:17:07', '2023-04-29 14:18:27');
INSERT INTO `tb_menu` VALUES (1647951751875133441, '网站管理', 0, 'website', NULL, 0, 0, 0, 'message', 2, '2023-04-17 21:17:46', '2023-04-28 06:21:28');
INSERT INTO `tb_menu` VALUES (1647951751875133442, '项目文档', 0, 'https://www.zrkizzy.com', NULL, 0, 1, 0, 'guide', 5, '2023-04-17 21:19:30', '2023-04-29 14:15:50');
INSERT INTO `tb_menu` VALUES (1647952968122630144, '文章管理', 1647951751875133440, 'article', 'content/article/index', 0, 0, 0, 'article-create', 1, '2023-04-17 21:22:42', '2023-04-26 01:51:59');
INSERT INTO `tb_menu` VALUES (1648539108017963008, '评论管理', 1647951751875133440, 'comment', 'content/comment/index', 0, 0, 0, 'comments', 2, '2023-04-19 12:09:46', '2023-04-26 01:52:00');
INSERT INTO `tb_menu` VALUES (1651040949146484736, '留言管理', 1647951751875133440, 'message', 'content/message/index', 0, 0, 0, 'message', 3, '2023-04-26 09:51:28', '2023-04-26 01:53:57');
INSERT INTO `tb_menu` VALUES (1651040949146484737, '分类管理', 1647951751875133440, 'category', 'content/category/index', 0, 0, 0, 'category', 4, '2023-04-26 09:52:57', '2023-04-26 01:53:59');
INSERT INTO `tb_menu` VALUES (1651042040491802625, '标签管理', 1647951751875133440, 'tags', 'content/tags/index', 0, 0, 0, 'tags', 5, '2023-04-26 09:53:51', '2023-04-26 01:54:48');
INSERT INTO `tb_menu` VALUES (1651217423560343552, '文件管理', 1647951751875133441, 'file', 'website/file/index', 0, 0, 0, 'article-ranking', 1, '2023-04-26 21:33:29', '2023-04-26 13:54:53');
INSERT INTO `tb_menu` VALUES (1651217423560343553, '相册管理', 1647951751875133441, 'photo', 'website/photo/index', 0, 0, 0, 'tool', 2, '2023-04-26 21:33:31', '2023-04-26 13:54:25');
INSERT INTO `tb_menu` VALUES (1651217423560343554, '页面管理', 1647951751875133441, 'page', 'website/page/index', 0, 0, 0, 'documentation', 3, '2023-04-26 21:33:29', '2023-04-26 13:55:21');
INSERT INTO `tb_menu` VALUES (1651217423560343555, '角色管理', 1652313423859417088, 'role', 'system/role/index', 0, 0, 0, 'role', 4, '2023-04-26 21:33:29', '2023-04-29 14:11:36');
INSERT INTO `tb_menu` VALUES (1651217423560343556, '友链管理', 1647951751875133441, 'link', 'website/link/index', 0, 0, 0, 'personnel', 5, '2023-04-26 21:33:29', '2023-04-26 13:53:02');
INSERT INTO `tb_menu` VALUES (1651217423560343557, '用户管理', 1652313423859417088, 'user', 'system/user/index', 0, 0, 0, 'personnel-manage', 6, '2023-04-26 21:33:29', '2023-04-29 14:11:16');
INSERT INTO `tb_menu` VALUES (1651217423560343558, '公告管理', 1647951751875133441, 'notice', 'website/notice/index', 0, 0, 0, 'email', 7, '2023-04-26 21:33:29', '2023-04-26 13:53:49');
INSERT INTO `tb_menu` VALUES (1651217423560343559, '字典管理', 1652313423859417088, 'dictionary', 'system/dictionary/index', 0, 0, 0, 'dict', 8, '2023-04-26 21:33:29', '2023-04-29 14:11:32');
INSERT INTO `tb_menu` VALUES (1651217423560343560, '菜单管理', 1652313423859417088, 'menu', 'system/menu/index', 0, 0, 0, 'menu', 9, '2023-04-26 21:33:29', '2023-04-29 14:11:25');
INSERT INTO `tb_menu` VALUES (1651217423560343561, '网站配置', 1647951751875133441, 'config', 'website/config/index', 0, 0, 0, 'edit', 10, '2023-04-26 21:33:29', '2023-07-03 23:23:03');
INSERT INTO `tb_menu` VALUES (1651227500568641536, '系统监控', 0, 'monitor', NULL, 0, 0, 0, 'monitor', 4, '2023-04-26 22:15:11', '2023-04-29 14:07:25');
INSERT INTO `tb_menu` VALUES (1651227500568641537, '在线用户', 1651227500568641536, 'online', 'monitor/online/index', 0, 0, 0, 'online', 1, '2023-04-26 22:15:55', '2023-04-26 14:21:40');
INSERT INTO `tb_menu` VALUES (1651227500568641538, '服务监控', 1651227500568641536, 'server', 'monitor/server/index', 0, 0, 0, 'server', 2, '2023-04-26 22:16:24', '2023-04-26 14:21:47');
INSERT INTO `tb_menu` VALUES (1651227500568641539, '缓存管理', 1651227500568641536, 'cache', 'monitor/cache/index', 0, 0, 0, 'redis', 3, '2023-04-26 22:16:59', '2023-07-13 23:24:56');
INSERT INTO `tb_menu` VALUES (1651227500568641540, '操作日志', 1651227500568641536, 'operate-log', 'monitor/operate-log/index', 0, 0, 0, 'form', 4, '2023-04-26 22:18:09', '2023-04-26 14:22:03');
INSERT INTO `tb_menu` VALUES (1651227500568641541, '登录日志', 1651227500568641536, 'login-log', 'monitor/login-log/index', 0, 0, 0, 'logininfor', 5, '2023-04-26 22:18:32', '2023-04-26 14:24:06');
INSERT INTO `tb_menu` VALUES (1652313423859417088, '系统管理', 0, 'system', NULL, 0, 0, 0, 'system', 3, '2023-04-29 22:08:18', '2023-04-29 14:08:20');

-- ----------------------------
-- Table structure for tb_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_role`;
CREATE TABLE `tb_menu_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_menu_role
-- ----------------------------
INSERT INTO `tb_menu_role` VALUES (1647963394059272192, 1000000000000000000, 1647951751875133440);
INSERT INTO `tb_menu_role` VALUES (1647963394059272193, 1000000000000000000, 1647951751875133441);
INSERT INTO `tb_menu_role` VALUES (1647963394059272194, 1000000000000000000, 1647951751875133442);
INSERT INTO `tb_menu_role` VALUES (1647963893261139968, 1000000000000000000, 1647952968122630144);
INSERT INTO `tb_menu_role` VALUES (1648539108017963009, 1000000000000000000, 1648539108017963008);
INSERT INTO `tb_menu_role` VALUES (1651042040491802624, 1000000000000000000, 1648539108017963008);
INSERT INTO `tb_menu_role` VALUES (1651042293655797760, 1000000000000000000, 1651040949146484736);
INSERT INTO `tb_menu_role` VALUES (1651042293655797761, 1000000000000000000, 1651040949146484737);
INSERT INTO `tb_menu_role` VALUES (1651042293655797762, 1000000000000000000, 1651042040491802625);
INSERT INTO `tb_menu_role` VALUES (1651218840622727168, 1000000000000000000, 1651217423560343552);
INSERT INTO `tb_menu_role` VALUES (1651218840622727169, 1000000000000000000, 1651217423560343553);
INSERT INTO `tb_menu_role` VALUES (1651218840622727170, 1000000000000000000, 1651217423560343554);
INSERT INTO `tb_menu_role` VALUES (1651218840622727171, 1000000000000000000, 1651217423560343555);
INSERT INTO `tb_menu_role` VALUES (1651218840626921472, 1000000000000000000, 1651217423560343556);
INSERT INTO `tb_menu_role` VALUES (1651218840626921473, 1000000000000000000, 1651217423560343557);
INSERT INTO `tb_menu_role` VALUES (1651218840626921474, 1000000000000000000, 1651217423560343558);
INSERT INTO `tb_menu_role` VALUES (1651218840626921475, 1000000000000000000, 1651217423560343559);
INSERT INTO `tb_menu_role` VALUES (1651218840626921476, 1000000000000000000, 1651217423560343560);
INSERT INTO `tb_menu_role` VALUES (1651218840626921477, 1000000000000000000, 1651217423560343561);
INSERT INTO `tb_menu_role` VALUES (1651230337214185472, 1000000000000000000, 1651227500568641536);
INSERT INTO `tb_menu_role` VALUES (1651230337214185473, 1000000000000000000, 1651227500568641537);
INSERT INTO `tb_menu_role` VALUES (1651230337214185474, 1000000000000000000, 1651227500568641538);
INSERT INTO `tb_menu_role` VALUES (1651230337214185475, 1000000000000000000, 1651227500568641539);
INSERT INTO `tb_menu_role` VALUES (1651230337214185476, 1000000000000000000, 1651227500568641540);
INSERT INTO `tb_menu_role` VALUES (1651230337214185477, 1000000000000000000, 1651227500568641541);
INSERT INTO `tb_menu_role` VALUES (1652313423859417089, 1000000000000000000, 1652313423859417088);

-- ----------------------------
-- Table structure for tb_module
-- ----------------------------
DROP TABLE IF EXISTS `tb_module`;
CREATE TABLE `tb_module`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '模块名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模块描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '资源模块表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_module
-- ----------------------------
INSERT INTO `tb_module` VALUES (1636182933754609664, '基本模块', '个人博客系统基本模块，包括用户基本信息编辑、文件上传下载等功能', '2023-03-16 10:04:53', '2023-03-16 10:04:55');
INSERT INTO `tb_module` VALUES (1636182933754609665, '系统模块', '主要用于管理用户、角色等信息', '2023-03-16 10:05:44', '2023-03-16 10:05:47');

-- ----------------------------
-- Table structure for tb_module_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_module_role`;
CREATE TABLE `tb_module_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `module_id` bigint(20) NOT NULL COMMENT '模块ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '模块角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_module_role
-- ----------------------------
INSERT INTO `tb_module_role` VALUES (1636182933754609666, 1636182933754609665, 1000000000000000000, '2023-03-16 10:06:39', '2023-06-11 02:33:30');

-- ----------------------------
-- Table structure for tb_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_operate_log`;
CREATE TABLE `tb_operate_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `module_id` bigint(20) NOT NULL COMMENT '模块ID',
  `type` tinyint(4) NOT NULL COMMENT '操作类型 0 其他操作，1 新增，2 修改， 3 删除， 4 查询',
  `method_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作方法名称',
  `request_method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请求方式',
  `user_id` bigint(20) NOT NULL COMMENT '操作用户ID',
  `operate_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作IP',
  `operate_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作地址',
  `operate_param` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '操作参数',
  `operate_result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '操作结果描述',
  `status` tinyint(4) NOT NULL COMMENT '操作状态 0 失败 1 成功 ',
  `cost_time` bigint(20) NULL DEFAULT NULL COMMENT '操作消耗时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_module`(`module_id`) USING BTREE COMMENT '模块名称索引',
  INDEX `idx_type`(`type`) USING BTREE COMMENT '操作类型索引',
  INDEX `idx_request`(`request_method`) USING BTREE COMMENT '操作方法类型索引',
  INDEX `idx_user_id`(`user_id`) USING BTREE COMMENT '用户主键索引',
  INDEX `idx_status`(`status`) USING BTREE COMMENT '操作状态索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_operate_log
-- ----------------------------
INSERT INTO `tb_operate_log` VALUES (1676238846687182848, 1636182933754609665, 4, 'com.zrkizzy.web.controller.system.LinkController.listLinks()', 'POST', 1653794265890816000, '127.0.0.1', '本地登录', '[{\"currentPage\":1,\"dataRange\":[],\"pageSize\":10}]', 'Result(code=200, message=请求成功, data={list=[{website=https://banq.ink/, introduce=Semisunny, updateTime=1687331851000, createTime=1687330423000, name=半晴Miko, logo=https://www.static.banq.ink/sunnyBlog/avatar/fcc72d35fc928185c3e70773a29a310f.jpg, id=1671411053646315520}], total=1})', 1, 46, '2023-07-04 22:37:39', NULL);
INSERT INTO `tb_operate_log` VALUES (1676498669781647360, 1636182933754609665, 4, 'com.zrkizzy.web.controller.system.LinkController.listLinks()', 'POST', 1653794265890816000, '127.0.0.1', '本地登录', '[{\"currentPage\":1,\"dataRange\":[],\"pageSize\":10}]', 'Result(code=200, message=请求成功, data={list=[{website=https://banq.ink/, introduce=Semisunny, updateTime=1687331851000, createTime=1687330423000, name=半晴Miko, logo=https://www.static.banq.ink/sunnyBlog/avatar/fcc72d35fc928185c3e70773a29a310f.jpg, id=1671411053646315520}], total=1})', 1, 89, '2023-07-05 15:50:05', NULL);
INSERT INTO `tb_operate_log` VALUES (1679134410772840448, 1636182933754609665, 4, 'com.zrkizzy.web.controller.system.LinkController.listLinks()', 'POST', 1653794265890816000, '127.0.0.1', '本地登录', '[{\"currentPage\":1,\"dataRange\":[],\"pageSize\":10}]', 'Result(code=200, message=请求成功, data={list=[{website=https://banq.ink/, introduce=Semisunny, updateTime=1687331851000, createTime=1687330423000, name=半晴Miko, logo=https://www.static.banq.ink/sunnyBlog/avatar/fcc72d35fc928185c3e70773a29a310f.jpg, id=1671411053646315520}], total=1})', 1, 165, '2023-07-12 22:23:35', NULL);
INSERT INTO `tb_operate_log` VALUES (1679152859997798400, 1636182933754609665, 3, 'com.zrkizzy.web.controller.monitor.OnlineController.offlineUser()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[\"1679148698765885440\"]', 'Result(code=200, message=请求成功, data=null)', 1, 3, '2023-07-12 23:36:54', NULL);
INSERT INTO `tb_operate_log` VALUES (1679153124125704192, 1636182933754609665, 3, 'com.zrkizzy.web.controller.monitor.OnlineController.offlineUser()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[\"1679130827243388928\"]', 'Result(code=200, message=请求成功, data=null)', 1, 0, '2023-07-12 23:37:57', NULL);
INSERT INTO `tb_operate_log` VALUES (1679157982115397632, 1636182933754609665, 3, 'com.zrkizzy.web.controller.monitor.OnlineController.offlineUser()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[\"1679148814230880256\"]', 'Result(code=200, message=请求成功, data=null)', 1, 0, '2023-07-12 23:57:15', NULL);
INSERT INTO `tb_operate_log` VALUES (1679836654258356224, 1636182933754609665, 4, 'com.zrkizzy.web.controller.system.LinkController.listLinks()', 'POST', 1653794265890816000, '127.0.0.1', '本地登录', '[{\"currentPage\":1,\"dataRange\":[],\"pageSize\":10}]', 'Result(code=200, message=请求成功, data={list=[{website=https://banq.ink/, introduce=Semisunny, updateTime=1687331851000, createTime=1687330423000, name=半晴Miko, logo=https://www.static.banq.ink/sunnyBlog/avatar/fcc72d35fc928185c3e70773a29a310f.jpg, id=1671411053646315520}], total=1})', 1, 43, '2023-07-14 20:54:03', NULL);
INSERT INTO `tb_operate_log` VALUES (1680183441259233280, 1636182933754609665, 3, 'com.zrkizzy.web.controller.monitor.CacheController.clearCacheKeys()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[\"captcha:\"]', 'Result(code=200, message=请求成功, data=null)', 1, 0, '2023-07-15 19:52:03', NULL);
INSERT INTO `tb_operate_log` VALUES (1680231076905091072, 1636182933754609665, 3, 'com.zrkizzy.web.controller.monitor.CacheController.deleteCacheKey()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[\"captcha:1680230908587671552\"]', 'Result(code=200, message=请求成功, data=null)', 1, 2, '2023-07-15 23:01:21', NULL);
INSERT INTO `tb_operate_log` VALUES (1680235728878960640, 1636182933754609665, 3, 'com.zrkizzy.web.controller.monitor.CacheController.deleteCacheKey()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[\"captcha:1680235666417385472\"]', 'Result(code=200, message=请求成功, data=null)', 1, 2, '2023-07-15 23:19:50', NULL);
INSERT INTO `tb_operate_log` VALUES (1680236540690694144, 1636182933754609665, 3, 'com.zrkizzy.web.controller.monitor.CacheController.deleteCacheKey()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[\"captcha:1680235694112374784\"]', 'Result(code=200, message=请求成功, data=null)', 1, 2, '2023-07-15 23:23:03', NULL);

-- ----------------------------
-- Table structure for tb_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源描述',
  `method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源请求方式',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源请求路径',
  `module_id` bigint(20) NULL DEFAULT NULL COMMENT '模块ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '资源表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_resource
-- ----------------------------
INSERT INTO `tb_resource` VALUES (1636187548919267328, '获取所有用户', '获取当前系统中所有用户信息', 'POST', '/admin/user/list', 1636182933754609665, '2023-03-16 10:17:39', '2023-04-13 07:06:14');
INSERT INTO `tb_resource` VALUES (1636187548919267329, '获取所有角色', '获取当前系统中所有角色的信息', 'POST', '/admin/role/list', 1636182933754609665, '2023-03-16 10:24:57', '2023-04-13 07:06:16');
INSERT INTO `tb_resource` VALUES (1647860341062762496, '获取当前登录用户', '获取当前系统登录用户', 'GET', '/admin/user/getLoginUser', 1636182933754609665, '2023-04-17 15:12:14', '2023-04-17 21:24:19');
INSERT INTO `tb_resource` VALUES (1647952968122630145, '获取菜单列表', '获取菜单列表', 'GET', '/admin/menu/getRoutes', 1636182933754609665, '2023-04-17 21:24:19', '2023-04-19 08:28:04');
INSERT INTO `tb_resource` VALUES (1652715951180742656, '获取用户个人信息', '获取用户个人信息', 'GET', '/admin/user-info/getUserInfo', 1636182933754609665, '2023-05-01 00:46:55', '2023-07-18 09:23:14');
INSERT INTO `tb_resource` VALUES (1653929645323583488, '更新用户个人信息', '更新用户个人信息', 'POST', '/admin/user/updateUser', 1636182933754609665, '2023-05-01 00:46:55', '2023-07-18 09:28:04');
INSERT INTO `tb_resource` VALUES (1654391949517389824, '修改密码发送邮件验证码', '修改密码发送邮件验证码', 'GET', '/admin/email/password', 1636182933754609665, '2023-05-05 15:46:37', '2023-05-05 07:50:01');
INSERT INTO `tb_resource` VALUES (1654642840078123008, '用户更新密码', '用户个人信息更新密码', 'POST', '/admin/user/updatePassword', 1636182933754609665, '2023-05-06 08:22:27', '2023-05-06 00:22:58');
INSERT INTO `tb_resource` VALUES (1656679330433990656, '获取文件列表', '用户获取文件列表', 'GET', '/admin/file-type/list', 1636182933754609665, '2023-05-11 23:15:37', '2023-06-01 13:44:33');
INSERT INTO `tb_resource` VALUES (1661030954518446080, '获取文件上传模式', '用户获取文件上传模式', 'GET', '/admin/file/listModes', 1636182933754609665, '2023-05-23 23:26:23', '2023-05-23 15:29:01');
INSERT INTO `tb_resource` VALUES (1662978940790112256, '上传文件', '用户进行文件上传', 'POST', '/admin/file/upload', 1636182933754609665, '2023-05-29 08:28:17', '2023-05-29 00:28:26');
INSERT INTO `tb_resource` VALUES (1663469788107636736, '批量删除文件', '用户进行批量删除文件操作', 'DELETE', '/admin/file/delete', 1636182933754609665, '2023-05-30 16:58:49', '2023-05-30 08:59:01');
INSERT INTO `tb_resource` VALUES (1663579937522581504, '更新用户头像', '更新用户头像', 'POST', '/admin/user/updateLoginUserAvatar', 1636182933754609665, '2023-05-31 00:16:36', '2023-05-30 16:48:54');
INSERT INTO `tb_resource` VALUES (1664296403540639744, '获取指定文件分类对象', '根据文件分类ID获取指定文件分类', 'GET', '/admin/file-type/getFileTypeById/**', 1636182933754609665, '2023-06-01 23:46:32', '2023-06-01 15:47:52');
INSERT INTO `tb_resource` VALUES (1664914212519936000, '新增或编辑文件分类', '用户新增或编辑文件分类', 'POST', '/admin/file-type/save', 1636182933754609665, '2023-06-03 16:38:24', '2023-06-03 08:41:48');
INSERT INTO `tb_resource` VALUES (1664914212519936001, '删除指定文件分类', '用户删除指定文件分类', 'DELETE', '/admin/file-type/delete/**', 1636182933754609665, '2023-06-03 16:41:39', '2023-06-03 08:41:50');
INSERT INTO `tb_resource` VALUES (1667050222783561728, '新增或编辑更新角色信息', '新增或编辑角色信息', 'POST', '/admin/role/save', 1636182933754609665, '2023-06-09 14:05:59', '2023-06-09 06:06:11');
INSERT INTO `tb_resource` VALUES (1667578577488445440, '获取指定角色信息', '根据角色ID获取指定角色', 'GET', '/admin/role/getRoleById/**', 1636182933754609665, '2023-06-11 01:05:40', '2023-06-11 01:05:51');
INSERT INTO `tb_resource` VALUES (1667598029198196736, '批量删除角色信息', '批量删除角色信息', 'DELETE', '/admin/role/delete', 1636182933754609665, '2023-06-11 02:23:06', '2023-06-13 21:47:51');
INSERT INTO `tb_resource` VALUES (1671394657394753536, '获取友链信息', '分页查询友链', 'POST', '/admin/link/list', 1636182933754609665, '2023-06-21 13:49:53', '2023-06-21 05:52:34');
INSERT INTO `tb_resource` VALUES (1671394657394753537, '添加-更新友情链接', '编辑友链信息', 'POST', '/admin/link/save', 1636182933754609665, '2023-06-21 13:50:35', '2023-06-21 05:52:36');
INSERT INTO `tb_resource` VALUES (1671394657394753538, '获取指定友情链接信息', '根据友情链接ID获取到指定友请链接', 'GET', '/admin/link/getLinkById/**', 1636182933754609665, '2023-06-21 13:51:41', '2023-06-21 05:52:37');
INSERT INTO `tb_resource` VALUES (1671394657394753539, '批量删除友情链接数据', '根据友情链接集合批量删除友情链接数据', 'DELETE', '/admin/link/delete', 1636182933754609665, '2023-06-21 13:52:23', '2023-06-21 05:52:39');
INSERT INTO `tb_resource` VALUES (1675800348175892480, '获取操作日志信息', '分页查询操作日志信息', 'POST', '/admin/operate-log/list', 1636182933754609665, '2023-07-03 17:36:35', '2023-07-03 09:38:51');
INSERT INTO `tb_resource` VALUES (1675800348175892481, '批量删除操作日志信息', '批量删除操作日志信息', 'DELETE', '/admin/operate-log/delete', 1636182933754609665, '2023-06-21 13:50:35', '2023-06-21 05:52:36');
INSERT INTO `tb_resource` VALUES (1675800348175892482, '获取模块选项数据', '获取模块选项数据', 'GET', '/admin/module/listModuleOptions', 1636182933754609665, '2023-07-03 22:34:42', '2023-07-03 22:34:53');
INSERT INTO `tb_resource` VALUES (1675800348175892483, '获取用户选项数据', '获取用户选项数据集', 'GET', '/admin/user/listUserOptions', 1636182933754609665, '2023-07-03 22:55:11', '2023-07-03 22:55:27');
INSERT INTO `tb_resource` VALUES (1676236928191561728, '清空操作日志', '清空操作日志中的所有数据', 'GET', '/admin/operate-log/clear', 1636182933754609665, '2023-07-04 22:31:05', '2023-07-04 22:31:14');
INSERT INTO `tb_resource` VALUES (1676421084687106050, '获取登录日志信息', '获取所有登录日志信息', 'POST', '/admin/login-info/list', 1636182933754609665, '2023-07-05 10:43:50', '2023-07-05 02:44:01');
INSERT INTO `tb_resource` VALUES (1676421084687106051, '批量删除登录日志信息', '批量删除用户登录日志信息', 'DELETE', '/admin/login-info/delete', 1636182933754609665, '2023-07-05 10:45:47', '2023-07-05 10:45:49');
INSERT INTO `tb_resource` VALUES (1676421084687106052, '清空登录日志信息', '清空所有用户登录日志信息', 'GET', '/admin/login-info/clear', 1636182933754609665, '2023-07-05 10:46:30', '2023-07-05 10:46:32');
INSERT INTO `tb_resource` VALUES (1677234418973933568, '获取服务监控信息', '获取系统服务监控信息', 'GET', '/admin/service-monitor/getMonitorInfo', 1636182933754609665, '2023-07-07 16:34:54', '2023-07-07 08:35:04');
INSERT INTO `tb_resource` VALUES (1678704512069533696, '获取所有在线用户', '获取当前所有在线用户', 'POST', '/admin/online/list', 1636182933754609665, '2023-07-11 17:56:01', '2023-07-12 23:33:21');
INSERT INTO `tb_resource` VALUES (1679151716034936832, '下线指定用户', '将指定用户下线', 'DELETE', '/admin/online/offline/**', 1636182933754609665, '2023-07-12 23:33:03', '2023-07-12 23:33:14');
INSERT INTO `tb_resource` VALUES (1679512161577074688, '获取所有缓存键类型', '获取所有Redis缓存键类型', 'GET', '/admin/cache/listCacheType', 1636182933754609665, '2023-07-13 23:26:11', '2023-07-14 02:43:40');
INSERT INTO `tb_resource` VALUES (1679675931226013696, '获取所有缓存键', '获取所有缓存键', 'GET', '/admin/cache/listCacheKeys/**', 1636182933754609665, '2023-07-14 10:16:09', '2023-07-14 02:16:25');
INSERT INTO `tb_resource` VALUES (1679732738346713088, '获取指定缓存', '获取指定缓存', 'GET', '/admin/cache/getCacheInfoByKey/**', 1636182933754609665, '2023-07-14 14:01:49', '2023-07-14 06:01:59');
INSERT INTO `tb_resource` VALUES (1680179775546589184, '清除缓存列表', '清除缓存列表', 'DELETE', '/admin/cache/clearCacheKeys/**', 1636182933754609665, '2023-07-15 19:38:03', '2023-07-15 19:44:28');
INSERT INTO `tb_resource` VALUES (1680179775546589185, '删除指定缓存', '删除指定缓存', 'DELETE', '/admin/cache/deleteCacheKey/**', 1636182933754609665, '2023-07-15 21:39:56', '2023-07-15 21:40:07');
INSERT INTO `tb_resource` VALUES (1681231793782521856, '获取指定用户信息', '根据用户ID获取指定用户信息', 'GET', '/admin/user-info/getUserInfoById/**', 1636182933754609665, '2023-07-18 17:20:09', '2023-07-18 09:23:06');
INSERT INTO `tb_resource` VALUES (1682422693888000001, '获取系统配置', '获取系统基本配置', 'GET', '/admin/config/getConfig', 1636182933754609665, '2023-07-22 22:05:04', '2023-07-22 22:07:29');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` bigint(20) NOT NULL COMMENT '角色ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `mark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色标识',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_01`(`name`, `mark`, `create_time`) USING BTREE,
  INDEX `idx_mark_id`(`mark`, `id`) USING BTREE COMMENT '角色标识、ID联合索引',
  INDEX `idx_name_id`(`name`, `id`) USING BTREE COMMENT '角色名称、ID联合索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1000000000000000000, '超级管理员', 'ROLE_ADMIN', '超级管理员', '2023-03-09 09:50:27', '2023-06-11 02:31:37');
INSERT INTO `tb_role` VALUES (1633657944153260032, '默认用户', 'ROLE_LOGIN', '默认用户测试', '2023-03-09 10:36:20', '2023-06-11 01:55:33');
INSERT INTO `tb_role` VALUES (1667607066451116032, '测试用户', 'ROLE_TEST', '系统测试用户', '2023-06-11 02:58:02', '2023-06-11 02:58:01');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态，0：禁用，1：启用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1633027432405532672, '1072876976@qq.com', '$2a$10$Kbb.k6umlihAQBRtSxNGiuHaqT5YP4IVP9Cmytfn8xsecp7Z4e50G', '世纪末的架构师', 'https://s1.ax1x.com/2023/04/26/p9K181A.jpg', 1, NULL, '2023-03-07 16:52:16', '2023-04-28 07:04:14');
INSERT INTO `tb_user` VALUES (1653794265890816000, '2675525537@qq.com', '$2a$10$d4plA8fBNLdMFCQjo86vP.zrt714ei8llHZ.bvJk/OM1T.g77m5I2', '测试账号', 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/avatar/20230529192531.jpg', 1, '自古英雄出炼狱，破马长枪定乾坤！', '2023-05-03 16:15:23', '2023-06-06 11:09:55');

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info`  (
  `id` bigint(20) NOT NULL COMMENT '主键（同user主键）',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号码',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (1635171865322323968, 1633027432405532672, 1000000000000000000);
INSERT INTO `tb_user_role` VALUES (1653794265890816001, 1653794265890816000, 1000000000000000000);

SET FOREIGN_KEY_CHECKS = 1;
