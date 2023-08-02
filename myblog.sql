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

 Date: 03/08/2023 00:39:48
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
  `notice` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '系统公告',
  `upload` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件上传策略：local 本地策略，oss OSS策略',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_config
-- ----------------------------
INSERT INTO `tb_config` VALUES (1682761996493127680, 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/avatar/20230529192531.jpg', '<p><strong style=\"color: rgb(96, 98, 102);\">尊敬的用户：</strong></p><p>欢迎来到我的个人博客! 我是Dream_飞翔，非常感谢您的来访。这里是我分享自己生活、学习和工作中的一些体验与见解的地方。您可以随意浏览我的文章以及后台的所有页面，也可以在评论区分享您的想法和反馈。如果您有任何问题或建议，请与我联系，祝您生活愉快！</p><p><strong style=\"color: rgb(96, 98, 102);\"><span class=\"ql-cursor\">﻿﻿﻿﻿﻿</span></strong><img src=\"https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/article/20230724214014.jpeg\" width=\"263\" style=\"display: block; margin: auto; cursor: nwse-resize;\"></p><p><strong style=\"color: rgb(96, 98, 102);\">其他：</strong></p><p>目前项目的技术栈为SpringBoot2，后续我会向SpringBoot3进行迁移</p><p><br></p>', 'oss', '2023-07-22 22:38:28', '2023-07-27 13:56:12');

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
INSERT INTO `tb_file` VALUES (1683472159025922048, '20230724214014.jpeg', 'article/20230724214014.jpeg', 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/article/20230724214014.jpeg', 604332, '.jpeg', 'oss', 1653794265890816000, 1656676089927303169, '67bd2983a12ea08bffa358d68e1169f7', '2023-07-24 21:40:15', '2023-07-24 21:40:15');

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
INSERT INTO `tb_login_info` VALUES (1683012474535673856, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-23 15:13:37');
INSERT INTO `tb_login_info` VALUES (1683091885125533696, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-23 20:29:10');
INSERT INTO `tb_login_info` VALUES (1683126491543502848, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-23 22:46:41');
INSERT INTO `tb_login_info` VALUES (1683361700956340224, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-24 14:21:19');
INSERT INTO `tb_login_info` VALUES (1683461001057402880, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-24 20:55:54');
INSERT INTO `tb_login_info` VALUES (1683461454834958336, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-24 20:57:43');
INSERT INTO `tb_login_info` VALUES (1683491805116497920, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-24 22:58:19');
INSERT INTO `tb_login_info` VALUES (1683493188335042560, '123@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-24 23:03:48');
INSERT INTO `tb_login_info` VALUES (1683493260846170112, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-24 23:04:06');
INSERT INTO `tb_login_info` VALUES (1683495082721804288, '1@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-24 23:11:20');
INSERT INTO `tb_login_info` VALUES (1683495142473859072, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-24 23:11:34');
INSERT INTO `tb_login_info` VALUES (1683495226779369472, '17@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 0, '服务器内部错误', '2023-07-24 23:11:55');
INSERT INTO `tb_login_info` VALUES (1683495248019324928, '1@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 0, '服务器内部错误', '2023-07-24 23:11:59');
INSERT INTO `tb_login_info` VALUES (1683495262355456000, '1@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-24 23:12:03');
INSERT INTO `tb_login_info` VALUES (1683495299261136896, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-24 23:12:12');
INSERT INTO `tb_login_info` VALUES (1683495811566010368, '1@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 0, '验证码错误', '2023-07-24 23:14:14');
INSERT INTO `tb_login_info` VALUES (1683495829056258048, '1@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 0, '密码错误', '2023-07-24 23:14:18');
INSERT INTO `tb_login_info` VALUES (1683495849553821696, '11@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 0, '用户不存在', '2023-07-24 23:14:23');
INSERT INTO `tb_login_info` VALUES (1683495861897658368, '1@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-24 23:14:26');
INSERT INTO `tb_login_info` VALUES (1683495904398540800, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-24 23:14:36');
INSERT INTO `tb_login_info` VALUES (1683838807415193600, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-25 21:57:10');
INSERT INTO `tb_login_info` VALUES (1683838967784407040, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 0, '用户不存在', '2023-07-25 21:57:49');
INSERT INTO `tb_login_info` VALUES (1683838999594008576, '26755255371@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-25 21:57:56');
INSERT INTO `tb_login_info` VALUES (1683839290091503616, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-25 21:59:06');
INSERT INTO `tb_login_info` VALUES (1683839780061708288, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-25 22:01:02');
INSERT INTO `tb_login_info` VALUES (1683856599656955904, '123@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-25 23:07:52');
INSERT INTO `tb_login_info` VALUES (1683856728036212736, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-25 23:08:23');
INSERT INTO `tb_login_info` VALUES (1683856867131916288, '123@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 0, '当前用户已禁用', '2023-07-25 23:08:56');
INSERT INTO `tb_login_info` VALUES (1683856896798228480, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-25 23:09:03');
INSERT INTO `tb_login_info` VALUES (1683862868178501632, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 0, '密码错误', '2023-07-25 23:32:47');
INSERT INTO `tb_login_info` VALUES (1683862884087496704, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-25 23:32:51');
INSERT INTO `tb_login_info` VALUES (1683863040220463104, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-25 23:33:28');
INSERT INTO `tb_login_info` VALUES (1684007310982119424, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-26 09:06:44');
INSERT INTO `tb_login_info` VALUES (1684045536325271552, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-26 11:38:38');
INSERT INTO `tb_login_info` VALUES (1684076064227196928, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-26 13:39:56');
INSERT INTO `tb_login_info` VALUES (1684115834097631232, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-26 16:17:59');
INSERT INTO `tb_login_info` VALUES (1684207327927861248, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-26 22:21:32');
INSERT INTO `tb_login_info` VALUES (1684208831892029440, '123@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-26 22:27:31');
INSERT INTO `tb_login_info` VALUES (1684209009000710144, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-26 22:28:13');
INSERT INTO `tb_login_info` VALUES (1684369189361418240, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-27 09:04:42');
INSERT INTO `tb_login_info` VALUES (1684405187155853312, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-27 11:27:46');
INSERT INTO `tb_login_info` VALUES (1684436695002382336, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-27 13:32:58');
INSERT INTO `tb_login_info` VALUES (1684539020152930304, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-27 20:19:34');
INSERT INTO `tb_login_info` VALUES (1684576785045389312, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-27 22:49:38');
INSERT INTO `tb_login_info` VALUES (1684743829787246592, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-28 09:53:24');
INSERT INTO `tb_login_info` VALUES (1684799218218172416, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-28 13:33:30');
INSERT INTO `tb_login_info` VALUES (1684832579586359296, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-07-28 15:46:04');
INSERT INTO `tb_login_info` VALUES (1685314474354933760, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-29 23:40:57');
INSERT INTO `tb_login_info` VALUES (1685590070666461184, '2675525537@qq.com', '0:0:0:0:0:0:0:1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-30 17:56:04');
INSERT INTO `tb_login_info` VALUES (1685606379118657536, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-30 19:00:52');
INSERT INTO `tb_login_info` VALUES (1685642070636625920, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-30 21:22:42');
INSERT INTO `tb_login_info` VALUES (1685673287306182656, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-30 23:26:44');
INSERT INTO `tb_login_info` VALUES (1686011104968310784, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 0, '验证码错误', '2023-07-31 21:49:07');
INSERT INTO `tb_login_info` VALUES (1686011153454465024, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-31 21:49:18');
INSERT INTO `tb_login_info` VALUES (1686025919895961600, '2675525537@qq.com', '0:0:0:0:0:0:0:1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-07-31 22:47:59');
INSERT INTO `tb_login_info` VALUES (1686274351525003264, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-08-01 15:15:09');
INSERT INTO `tb_login_info` VALUES (1686362139901558784, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-08-01 21:03:59');
INSERT INTO `tb_login_info` VALUES (1686552064252968960, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-08-02 09:38:41');
INSERT INTO `tb_login_info` VALUES (1686584852884750336, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-08-02 11:48:58');
INSERT INTO `tb_login_info` VALUES (1686615025369743360, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Mac OS X', 1, '登录成功', '2023-08-02 13:48:52');
INSERT INTO `tb_login_info` VALUES (1686715449984679936, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-08-02 20:27:55');
INSERT INTO `tb_login_info` VALUES (1686716481636663296, '1@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-08-02 20:32:01');
INSERT INTO `tb_login_info` VALUES (1686716619499241472, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-08-02 20:32:34');
INSERT INTO `tb_login_info` VALUES (1686747583441010688, '2675525537@qq.com', '127.0.0.1', '本地登录', 'Chrome 11', 'Windows 10', 1, '登录成功', '2023-08-02 22:35:37');

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
  `status` tinyint(255) NULL DEFAULT NULL COMMENT '状态，0：禁用，1：正常',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `order` int(11) NULL DEFAULT NULL COMMENT '菜单顺序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES (1647951751875133440, '内容管理', 0, 'content', NULL, 0, 0, 0, 0, 'content', 1, '2023-04-17 21:17:07', '2023-08-02 21:49:29');
INSERT INTO `tb_menu` VALUES (1647951751875133441, '网站管理', 0, 'website', NULL, 0, 0, 0, 1, 'message', 2, '2023-04-17 21:17:46', '2023-08-02 21:39:32');
INSERT INTO `tb_menu` VALUES (1647951751875133442, '项目文档', 0, 'https://www.zrkizzy.com', NULL, 0, 1, 0, 1, 'guide', 5, '2023-04-17 21:19:30', '2023-08-02 21:39:33');
INSERT INTO `tb_menu` VALUES (1647952968122630144, '文章管理', 1647951751875133440, 'article', 'content/article/index', 0, 0, 0, 1, 'article-create', 1, '2023-04-17 21:22:42', '2023-08-02 21:39:34');
INSERT INTO `tb_menu` VALUES (1648539108017963008, '评论管理', 1647951751875133440, 'comment', 'content/comment/index', 0, 0, 0, 1, 'comments', 2, '2023-04-19 12:09:46', '2023-08-02 21:39:35');
INSERT INTO `tb_menu` VALUES (1651040949146484736, '留言管理', 1647951751875133440, 'message', 'content/message/index', 0, 0, 0, 1, 'message', 3, '2023-04-26 09:51:28', '2023-08-02 21:39:53');
INSERT INTO `tb_menu` VALUES (1651040949146484737, '分类管理', 1647951751875133440, 'category', 'content/category/index', 0, 0, 0, 1, 'category', 4, '2023-04-26 09:52:57', '2023-08-02 21:39:36');
INSERT INTO `tb_menu` VALUES (1651042040491802625, '标签管理', 1647951751875133440, 'tags', 'content/tags/index', 0, 0, 0, 1, 'tags', 5, '2023-04-26 09:53:51', '2023-08-02 21:39:50');
INSERT INTO `tb_menu` VALUES (1651217423560343552, '文件管理', 1647951751875133441, 'file', 'website/file/index', 0, 0, 0, 1, 'article-ranking', 1, '2023-04-26 21:33:29', '2023-08-02 21:39:37');
INSERT INTO `tb_menu` VALUES (1651217423560343553, '相册管理', 1647951751875133441, 'photo', 'website/photo/index', 0, 0, 0, 1, 'tool', 2, '2023-04-26 21:33:31', '2023-08-02 21:39:49');
INSERT INTO `tb_menu` VALUES (1651217423560343554, '页面管理', 1647951751875133441, 'page', 'website/page/index', 0, 0, 0, 1, 'documentation', 3, '2023-04-26 21:33:29', '2023-08-02 21:39:37');
INSERT INTO `tb_menu` VALUES (1651217423560343555, '角色管理', 1652313423859417088, 'role', 'system/role/index', 0, 0, 0, 1, 'role', 4, '2023-04-26 21:33:29', '2023-08-02 21:39:48');
INSERT INTO `tb_menu` VALUES (1651217423560343556, '友链管理', 1647951751875133441, 'link', 'website/link/index', 0, 0, 0, 1, 'personnel', 5, '2023-04-26 21:33:29', '2023-08-02 21:39:38');
INSERT INTO `tb_menu` VALUES (1651217423560343557, '用户管理', 1652313423859417088, 'user', 'system/user/index', 0, 0, 0, 1, 'personnel-manage', 6, '2023-04-26 21:33:29', '2023-08-02 21:39:47');
INSERT INTO `tb_menu` VALUES (1651217423560343558, '公告管理', 1647951751875133441, 'notice', 'website/notice/index', 0, 0, 0, 1, 'email', 7, '2023-04-26 21:33:29', '2023-08-02 21:39:38');
INSERT INTO `tb_menu` VALUES (1651217423560343559, '接口管理', 1652313423859417088, 'interface', 'system/interface/index', 0, 0, 0, 1, 'dict', 8, '2023-04-26 21:33:29', '2023-08-02 21:39:46');
INSERT INTO `tb_menu` VALUES (1651217423560343560, '菜单管理', 1652313423859417088, 'menu', 'system/menu/index', 0, 0, 0, 1, 'menu', 9, '2023-04-26 21:33:29', '2023-08-02 21:39:39');
INSERT INTO `tb_menu` VALUES (1651217423560343561, '网站配置', 1647951751875133441, 'config', 'website/config/index', 0, 0, 0, 1, 'edit', 10, '2023-04-26 21:33:29', '2023-08-02 21:39:40');
INSERT INTO `tb_menu` VALUES (1651227500568641536, '系统监控', 0, 'monitor', NULL, 0, 0, 0, 1, 'monitor', 4, '2023-04-26 22:15:11', '2023-08-02 21:39:40');
INSERT INTO `tb_menu` VALUES (1651227500568641537, '在线用户', 1651227500568641536, 'online', 'monitor/online/index', 0, 0, 0, 1, 'online', 1, '2023-04-26 22:15:55', '2023-08-02 21:39:41');
INSERT INTO `tb_menu` VALUES (1651227500568641538, '服务监控', 1651227500568641536, 'server', 'monitor/server/index', 0, 0, 0, 1, 'server', 2, '2023-04-26 22:16:24', '2023-08-02 21:39:41');
INSERT INTO `tb_menu` VALUES (1651227500568641539, '缓存管理', 1651227500568641536, 'cache', 'monitor/cache/index', 0, 0, 0, 1, 'redis', 3, '2023-04-26 22:16:59', '2023-08-02 21:39:42');
INSERT INTO `tb_menu` VALUES (1651227500568641540, '操作日志', 1651227500568641536, 'operate-log', 'monitor/operate-log/index', 0, 0, 0, 1, 'form', 4, '2023-04-26 22:18:09', '2023-08-02 21:39:42');
INSERT INTO `tb_menu` VALUES (1651227500568641541, '登录日志', 1651227500568641536, 'login-log', 'monitor/login-log/index', 0, 0, 0, 1, 'logininfor', 5, '2023-04-26 22:18:32', '2023-08-02 21:39:43');
INSERT INTO `tb_menu` VALUES (1652313423859417088, '系统管理', 0, 'system', NULL, 0, 0, 0, 1, 'system', 3, '2023-04-29 22:08:18', '2023-08-02 21:39:44');

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
INSERT INTO `tb_module` VALUES (1636182933754609665, '系统模块', '主要用于管理用户、角色等信息', '2023-03-16 10:05:44', '2023-07-27 20:59:17');
INSERT INTO `tb_module` VALUES (1686716084163444736, '测试模块', '测试模块', '2023-08-02 20:30:27', '2023-08-02 20:30:27');

-- ----------------------------
-- Table structure for tb_module_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_module_resource`;
CREATE TABLE `tb_module_resource`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `module_id` bigint(20) NULL DEFAULT NULL COMMENT '模块主键',
  `resource_id` bigint(20) NULL DEFAULT NULL COMMENT '资源主键',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_module_id`(`module_id`) USING BTREE COMMENT '模块ID索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '资源模块关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_module_resource
-- ----------------------------
INSERT INTO `tb_module_resource` VALUES (1686716253214867456, 1686716084163444736, 1686025120788774912, '2023-08-02 20:31:07');
INSERT INTO `tb_module_resource` VALUES (1686716253214867457, 1686716084163444736, 1675800348175892482, '2023-08-02 20:31:07');
INSERT INTO `tb_module_resource` VALUES (1686716253214867458, 1686716084163444736, 1684541750435119104, '2023-08-02 20:31:07');
INSERT INTO `tb_module_resource` VALUES (1686716253214867459, 1686716084163444736, 1684541750435119105, '2023-08-02 20:31:07');
INSERT INTO `tb_module_resource` VALUES (1686716253214867460, 1686716084163444736, 1636187548919267328, '2023-08-02 20:31:07');
INSERT INTO `tb_module_resource` VALUES (1686716253214867461, 1686716084163444736, 1647860341062762496, '2023-08-02 20:31:07');
INSERT INTO `tb_module_resource` VALUES (1686716253214867462, 1686716084163444736, 1675800348175892480, '2023-08-02 20:31:07');
INSERT INTO `tb_module_resource` VALUES (1686716253214867463, 1686716084163444736, 1652715951180742656, '2023-08-02 20:31:07');
INSERT INTO `tb_module_resource` VALUES (1686723013480808448, 1636182933754609665, 1686025120788774912, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808449, 1636182933754609665, 1686370540320718848, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808450, 1636182933754609665, 1675800348175892482, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808451, 1636182933754609665, 1684541750435119104, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808452, 1636182933754609665, 1684541750435119105, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808453, 1636182933754609665, 1684548449283866624, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808454, 1636182933754609665, 1684548449283866625, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808455, 1636182933754609665, 1654391949517389824, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808456, 1636182933754609665, 1682422693888000001, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808457, 1636182933754609665, 1683094177929232384, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808458, 1636182933754609665, 1647952968122630145, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808459, 1636182933754609665, 1686722622882054144, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808460, 1636182933754609665, 1671394657394753536, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808461, 1636182933754609665, 1671394657394753537, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808462, 1636182933754609665, 1671394657394753538, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808463, 1636182933754609665, 1671394657394753539, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808464, 1636182933754609665, 1677234418973933568, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808465, 1636182933754609665, 1684568413176856576, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808466, 1636182933754609665, 1684568413176856577, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808467, 1636182933754609665, 1684568413176856578, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808468, 1636182933754609665, 1684128046551924736, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808469, 1636182933754609665, 1678704512069533696, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808470, 1636182933754609665, 1679151716034936832, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808471, 1636182933754609665, 1679512161577074688, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808472, 1636182933754609665, 1679675931226013696, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808473, 1636182933754609665, 1679732738346713088, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808474, 1636182933754609665, 1680179775546589184, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808475, 1636182933754609665, 1680179775546589185, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808476, 1636182933754609665, 1656679330433990656, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808477, 1636182933754609665, 1664296403540639744, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808478, 1636182933754609665, 1664914212519936000, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808479, 1636182933754609665, 1664914212519936001, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808480, 1636182933754609665, 1661030954518446080, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808481, 1636182933754609665, 1662978940790112256, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808482, 1636182933754609665, 1663469788107636736, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808483, 1636182933754609665, 1683471580354576384, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808484, 1636182933754609665, 1684832097144930304, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808485, 1636182933754609665, 1685590302728912896, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808486, 1636182933754609665, 1685641090620719104, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808487, 1636182933754609665, 1685664653591445512, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808488, 1636182933754609665, 1676421084687106050, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808489, 1636182933754609665, 1676421084687106051, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808490, 1636182933754609665, 1676421084687106052, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808491, 1636182933754609665, 1683131008930545664, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013480808492, 1636182933754609665, 1636187548919267329, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002752, 1636182933754609665, 1667050222783561728, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002753, 1636182933754609665, 1667578577488445440, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002754, 1636182933754609665, 1667598029198196736, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002755, 1636182933754609665, 1684082256563404800, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002756, 1636182933754609665, 1636187548919267328, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002757, 1636182933754609665, 1647860341062762496, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002758, 1636182933754609665, 1653929645323583488, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002759, 1636182933754609665, 1654642840078123008, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002760, 1636182933754609665, 1663579937522581504, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002761, 1636182933754609665, 1675800348175892483, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002762, 1636182933754609665, 1683471580354576385, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002763, 1636182933754609665, 1683841299993591808, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002764, 1636182933754609665, 1683841299993591809, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002765, 1636182933754609665, 1683841299993591810, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002766, 1636182933754609665, 1684216181440905216, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002767, 1636182933754609665, 1675800348175892480, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002768, 1636182933754609665, 1675800348175892481, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002769, 1636182933754609665, 1676236928191561728, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002770, 1636182933754609665, 1652715951180742656, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723013485002771, 1636182933754609665, 1681231793782521856, '2023-08-02 20:57:59');
INSERT INTO `tb_module_resource` VALUES (1686723099728281600, 1636182933754609664, 1686025120788774912, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281601, 1636182933754609664, 1675800348175892482, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281602, 1636182933754609664, 1684541750435119104, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281603, 1636182933754609664, 1684541750435119105, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281604, 1636182933754609664, 1682422693888000001, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281605, 1636182933754609664, 1647952968122630145, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281606, 1636182933754609664, 1686722622882054144, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281607, 1636182933754609664, 1671394657394753536, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281608, 1636182933754609664, 1671394657394753538, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281609, 1636182933754609664, 1677234418973933568, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281610, 1636182933754609664, 1684568413176856576, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281611, 1636182933754609664, 1684568413176856578, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281612, 1636182933754609664, 1678704512069533696, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281613, 1636182933754609664, 1679512161577074688, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281614, 1636182933754609664, 1679675931226013696, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281615, 1636182933754609664, 1679732738346713088, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281616, 1636182933754609664, 1656679330433990656, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281617, 1636182933754609664, 1664296403540639744, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281618, 1636182933754609664, 1661030954518446080, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281619, 1636182933754609664, 1684832097144930304, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281620, 1636182933754609664, 1685590302728912896, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281621, 1636182933754609664, 1676421084687106050, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281622, 1636182933754609664, 1683131008930545664, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281623, 1636182933754609664, 1636187548919267329, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281624, 1636182933754609664, 1667578577488445440, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281625, 1636182933754609664, 1684082256563404800, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281626, 1636182933754609664, 1636187548919267328, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281627, 1636182933754609664, 1647860341062762496, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281628, 1636182933754609664, 1675800348175892483, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281629, 1636182933754609664, 1675800348175892480, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281630, 1636182933754609664, 1652715951180742656, '2023-08-02 20:58:19');
INSERT INTO `tb_module_resource` VALUES (1686723099728281631, 1636182933754609664, 1681231793782521856, '2023-08-02 20:58:19');

-- ----------------------------
-- Table structure for tb_module_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_module_role`;
CREATE TABLE `tb_module_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `module_id` bigint(20) NOT NULL COMMENT '模块ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '模块角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_module_role
-- ----------------------------
INSERT INTO `tb_module_role` VALUES (1636182933754609666, 1636182933754609665, 1000000000000000000, '2023-03-16 10:06:39');
INSERT INTO `tb_module_role` VALUES (1686375098224738304, 1636182933754609664, 1633657944153260032, '2023-08-01 21:55:29');
INSERT INTO `tb_module_role` VALUES (1686716440331157504, 1686716084163444736, 1667607066451116032, '2023-08-02 20:31:52');

-- ----------------------------
-- Table structure for tb_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_operate_log`;
CREATE TABLE `tb_operate_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `operate_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作内容',
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
  INDEX `idx_type`(`type`) USING BTREE COMMENT '操作类型索引',
  INDEX `idx_request`(`request_method`) USING BTREE COMMENT '操作方法类型索引',
  INDEX `idx_user_id`(`user_id`) USING BTREE COMMENT '用户主键索引',
  INDEX `idx_status`(`status`) USING BTREE COMMENT '操作状态索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_operate_log
-- ----------------------------
INSERT INTO `tb_operate_log` VALUES (1686557298492702720, '批量删除资源模块数据', 3, 'com.zrkizzy.web.controller.core.ModuleController.delete()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[[1686554551525572608]]', 'org.springframework.jdbc.BadSqlGrammarException: \n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'1686554551525572608\' at line 7\n### The error may exist in file [/Users/zhangrongkang/workspace/IdeaProject/blog/blog-springboot/blog-data/target/classes/mapper/ModuleRoleMapper.xml]\n### The error may involve defaultParameterMap\n### The error occurred while setting parameters\n### SQL: SELECT             COUNT(1)         FROM             tb_module_role          WHERE module_id IN                              ?\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'1686554551525572608\' at line 7\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'1686554551525572608\' at line 7', 0, 454, '2023-08-02 09:59:29', NULL);
INSERT INTO `tb_operate_log` VALUES (1686557758595268608, '批量删除资源模块数据', 3, 'com.zrkizzy.web.controller.core.ModuleController.delete()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[[1686554551525572608]]', '部分模块有角色关联模块，不允许删除', 0, 405, '2023-08-02 10:01:19', NULL);
INSERT INTO `tb_operate_log` VALUES (1686557851033534464, '分配角色模块权限', 1, 'com.zrkizzy.web.controller.core.ModuleRoleController.save()', 'POST', 1653794265890816000, '127.0.0.1', '本地登录', '[{\"moduleIds\":[1636182933754609665],\"roleId\":1667607066451116032}]', 'Result(code=200, message=请求成功, data=true)', 1, 462, '2023-08-02 10:01:41', NULL);
INSERT INTO `tb_operate_log` VALUES (1686557892297097216, '批量删除资源模块数据', 3, 'com.zrkizzy.web.controller.core.ModuleController.delete()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[[1686554551525572608]]', '模块资源删除失败', 0, 588, '2023-08-02 10:01:51', NULL);
INSERT INTO `tb_operate_log` VALUES (1686558130139299840, '批量删除资源模块数据', 3, 'com.zrkizzy.web.controller.core.ModuleController.delete()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[[1686554551525572608]]', '部分模块对应资源删除失败', 0, 234, '2023-08-02 10:02:48', NULL);
INSERT INTO `tb_operate_log` VALUES (1686573455727656960, '批量删除资源模块数据', 3, 'com.zrkizzy.web.controller.core.ModuleController.delete()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[[1686554551525572608]]', '部分模块对应资源删除失败', 0, 372, '2023-08-02 11:06:31', NULL);
INSERT INTO `tb_operate_log` VALUES (1686573549831061504, '批量删除资源模块数据', 3, 'com.zrkizzy.web.controller.core.ModuleController.delete()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[[1686554551525572608]]', '部分模块对应资源删除失败', 0, 244, '2023-08-02 11:06:31', NULL);
INSERT INTO `tb_operate_log` VALUES (1686573644655886336, '批量删除资源模块数据', 3, 'com.zrkizzy.web.controller.core.ModuleController.delete()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[[1686554551525572608]]', '部分模块对应资源删除失败', 0, 290, '2023-08-02 11:06:31', NULL);
INSERT INTO `tb_operate_log` VALUES (1686574189420478464, '批量删除资源模块数据', 3, 'com.zrkizzy.web.controller.core.ModuleController.delete()', 'DELETE', 1653794265890816000, '127.0.0.1', '本地登录', '[[1686554551525572608]]', 'Result(code=200, message=请求成功, data=null)', 1, 540, '2023-08-02 11:06:37', NULL);
INSERT INTO `tb_operate_log` VALUES (1686578292133462016, '分配角色模块权限', 1, 'com.zrkizzy.web.controller.core.ModuleRoleController.save()', 'POST', 1653794265890816000, '127.0.0.1', '本地登录', '[{\"moduleIds\":[1636182933754609664],\"roleId\":1667607066451116032}]', 'Result(code=200, message=请求成功, data=true)', 1, 334, '2023-08-02 11:23:29', NULL);
INSERT INTO `tb_operate_log` VALUES (1686615481118621696, '分页查询友链', 4, 'com.zrkizzy.web.controller.system.LinkController.listLinks()', 'POST', 1653794265890816000, '127.0.0.1', '本地登录', '[{\"currentPage\":1,\"dataRange\":[],\"pageSize\":10}]', 'Result(code=200, message=请求成功, data={list=[{website=https://banq.ink/, introduce=Semisunny, updateTime=1687331851000, createTime=1687330423000, name=半晴Miko, logo=https://www.static.banq.ink/sunnyBlog/avatar/fcc72d35fc928185c3e70773a29a310f.jpg, id=1671411053646315520}], total=1})', 1, 97, '2023-08-02 13:50:41', NULL);
INSERT INTO `tb_operate_log` VALUES (1686716253328113664, '为指定模块分配资源请求', 1, 'com.zrkizzy.web.controller.core.ModuleResourceController.save()', 'POST', 1653794265890816000, '127.0.0.1', '本地登录', '[{\"moduleId\":1686716084163444736,\"resourceIds\":[1686025120788774912,1675800348175892482,1684541750435119104,1684541750435119105,1636187548919267328,1647860341062762496,1675800348175892480,1652715951180742656]}]', 'Result(code=200, message=请求成功, data=true)', 1, 17, '2023-08-02 20:31:07', NULL);
INSERT INTO `tb_operate_log` VALUES (1686716440675090432, '分配角色模块权限', 1, 'com.zrkizzy.web.controller.core.ModuleRoleController.save()', 'POST', 1653794265890816000, '127.0.0.1', '本地登录', '[{\"moduleIds\":[1686716084163444736],\"roleId\":1667607066451116032}]', 'Result(code=200, message=请求成功, data=true)', 1, 70, '2023-08-02 20:31:52', NULL);
INSERT INTO `tb_operate_log` VALUES (1686723013799575552, '为指定模块分配资源请求', 1, 'com.zrkizzy.web.controller.core.ModuleResourceController.save()', 'POST', 1653794265890816000, '127.0.0.1', '本地登录', '[{\"moduleId\":1636182933754609665,\"resourceIds\":[1686025120788774912,1686370540320718848,1675800348175892482,1684541750435119104,1684541750435119105,1684548449283866624,1684548449283866625,1654391949517389824,1682422693888000001,1683094177929232384,1647952968122630145,1686722622882054144,1671394657394753536,1671394657394753537,1671394657394753538,1671394657394753539,1677234418973933568,1684568413176856576,1684568413176856577,1684568413176856578,1684128046551924736,1678704512069533696,1679151716034936832,1679512161577074688,1679675931226013696,1679732738346713088,1680179775546589184,1680179775546589185,1656679330433990656,1664296403540639744,1664914212519936000,1664914212519936001,1661030954518446080,1662978940790112256,1663469788107636736,1683471580354576384,1684832097144930304,1685590302728912896,1685641090620719104,1685664653591445512,1676421084687106050,1676421084687106051,1676421084687106052,1683131008930545664,1636187548919267329,1667050222783561728,1667578577488445440,1667598029198196736,1684082256563404800,1636187548919267328,1647860341062762496,1653929645323583488,1654642840078123008,1663579937522581504,1675800348175892483,1683471580354576385,1683841299993591808,1683841299993591809,1683841299993591810,1684216181440905216,1675800348175892480,1675800348175892481,1676236928191561728,1652715951180742656,1681231793782521856]}]', 'Result(code=200, message=请求成功, data=true)', 1, 80, '2023-08-02 20:57:59', NULL);
INSERT INTO `tb_operate_log` VALUES (1686723099845722112, '为指定模块分配资源请求', 1, 'com.zrkizzy.web.controller.core.ModuleResourceController.save()', 'POST', 1653794265890816000, '127.0.0.1', '本地登录', '[{\"moduleId\":1636182933754609664,\"resourceIds\":[1686025120788774912,1675800348175892482,1684541750435119104,1684541750435119105,1682422693888000001,1647952968122630145,1686722622882054144,1671394657394753536,1671394657394753538,1677234418973933568,1684568413176856576,1684568413176856578,1678704512069533696,1679512161577074688,1679675931226013696,1679732738346713088,1656679330433990656,1664296403540639744,1661030954518446080,1684832097144930304,1685590302728912896,1676421084687106050,1683131008930545664,1636187548919267329,1667578577488445440,1684082256563404800,1636187548919267328,1647860341062762496,1675800348175892483,1675800348175892480,1652715951180742656,1681231793782521856]}]', 'Result(code=200, message=请求成功, data=true)', 1, 32, '2023-08-02 20:58:19', NULL);

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
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '资源表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_resource
-- ----------------------------
INSERT INTO `tb_resource` VALUES (1636187548919267328, '获取所有用户', '获取当前系统中所有用户信息', 'POST', '/admin/user/list', '2023-03-16 10:17:39', '2023-04-13 07:06:14');
INSERT INTO `tb_resource` VALUES (1636187548919267329, '获取所有角色', '获取当前系统中所有角色的信息', 'POST', '/admin/role/list', '2023-03-16 10:24:57', '2023-04-13 07:06:16');
INSERT INTO `tb_resource` VALUES (1647860341062762496, '获取当前登录用户', '获取当前系统登录用户', 'GET', '/admin/user/getLoginUser', '2023-04-17 15:12:14', '2023-04-17 21:24:19');
INSERT INTO `tb_resource` VALUES (1647952968122630145, '获取菜单列表', '获取菜单列表', 'GET', '/admin/menu/getRoutes', '2023-04-17 21:24:19', '2023-04-19 08:28:04');
INSERT INTO `tb_resource` VALUES (1652715951180742656, '获取用户个人信息', '获取用户个人信息', 'GET', '/admin/user-info/getUserInfo', '2023-05-01 00:46:55', '2023-07-18 09:23:14');
INSERT INTO `tb_resource` VALUES (1653929645323583488, '更新用户个人信息', '更新用户个人信息', 'PUT', '/admin/user/updateLoginUser', '2023-05-01 00:46:55', '2023-07-25 22:42:51');
INSERT INTO `tb_resource` VALUES (1654391949517389824, '修改密码发送邮件验证码', '修改密码发送邮件验证码', 'GET', '/admin/email/password', '2023-05-05 15:46:37', '2023-05-05 07:50:01');
INSERT INTO `tb_resource` VALUES (1654642840078123008, '用户更新密码', '用户个人信息更新密码', 'PUT', '/admin/user/updatePassword', '2023-05-06 08:22:27', '2023-07-25 22:42:49');
INSERT INTO `tb_resource` VALUES (1656679330433990656, '获取文件列表', '用户获取文件列表', 'GET', '/admin/file-type/list', '2023-05-11 23:15:37', '2023-06-01 13:44:33');
INSERT INTO `tb_resource` VALUES (1661030954518446080, '获取文件上传模式', '用户获取文件上传模式', 'GET', '/admin/file/listModes', '2023-05-23 23:26:23', '2023-05-23 15:29:01');
INSERT INTO `tb_resource` VALUES (1662978940790112256, '上传文件', '用户进行文件上传', 'POST', '/admin/file/upload', '2023-05-29 08:28:17', '2023-05-29 00:28:26');
INSERT INTO `tb_resource` VALUES (1663469788107636736, '批量删除文件', '用户进行批量删除文件操作', 'DELETE', '/admin/file/delete', '2023-05-30 16:58:49', '2023-05-30 08:59:01');
INSERT INTO `tb_resource` VALUES (1663579937522581504, '更新用户头像', '更新用户头像', 'PUT', '/admin/user/updateLoginUserAvatar', '2023-05-31 00:16:36', '2023-07-25 22:42:57');
INSERT INTO `tb_resource` VALUES (1664296403540639744, '获取指定文件分类对象', '根据文件分类ID获取指定文件分类', 'GET', '/admin/file-type/getFileTypeById/**', '2023-06-01 23:46:32', '2023-06-01 15:47:52');
INSERT INTO `tb_resource` VALUES (1664914212519936000, '新增或编辑文件分类', '用户新增或编辑文件分类', 'POST', '/admin/file-type/save', '2023-06-03 16:38:24', '2023-06-03 08:41:48');
INSERT INTO `tb_resource` VALUES (1664914212519936001, '删除指定文件分类', '用户删除指定文件分类', 'DELETE', '/admin/file-type/delete/**', '2023-06-03 16:41:39', '2023-06-03 08:41:50');
INSERT INTO `tb_resource` VALUES (1667050222783561728, '新增或编辑更新角色信息', '新增或编辑角色信息', 'POST', '/admin/role/save', '2023-06-09 14:05:59', '2023-06-09 06:06:11');
INSERT INTO `tb_resource` VALUES (1667578577488445440, '获取指定角色信息', '根据角色ID获取指定角色', 'GET', '/admin/role/getRoleById/**', '2023-06-11 01:05:40', '2023-06-11 01:05:51');
INSERT INTO `tb_resource` VALUES (1667598029198196736, '批量删除角色信息', '批量删除角色信息', 'DELETE', '/admin/role/delete', '2023-06-11 02:23:06', '2023-06-13 21:47:51');
INSERT INTO `tb_resource` VALUES (1671394657394753536, '获取友链信息', '分页查询友链', 'POST', '/admin/link/list', '2023-06-21 13:49:53', '2023-06-21 05:52:34');
INSERT INTO `tb_resource` VALUES (1671394657394753537, '添加-更新友情链接', '编辑友链信息', 'POST', '/admin/link/save', '2023-06-21 13:50:35', '2023-06-21 05:52:36');
INSERT INTO `tb_resource` VALUES (1671394657394753538, '获取指定友情链接信息', '根据友情链接ID获取到指定友请链接', 'GET', '/admin/link/getLinkById/**', '2023-06-21 13:51:41', '2023-06-21 05:52:37');
INSERT INTO `tb_resource` VALUES (1671394657394753539, '批量删除友情链接数据', '根据友情链接集合批量删除友情链接数据', 'DELETE', '/admin/link/delete', '2023-06-21 13:52:23', '2023-06-21 05:52:39');
INSERT INTO `tb_resource` VALUES (1675800348175892480, '获取操作日志信息', '分页查询操作日志信息', 'POST', '/admin/operate-log/list', '2023-07-03 17:36:35', '2023-07-03 09:38:51');
INSERT INTO `tb_resource` VALUES (1675800348175892481, '批量删除操作日志信息', '批量删除操作日志信息', 'DELETE', '/admin/operate-log/delete', '2023-06-21 13:50:35', '2023-06-21 05:52:36');
INSERT INTO `tb_resource` VALUES (1675800348175892482, '获取模块选项数据', '获取模块选项数据', 'GET', '/admin/module/listModuleOptions', '2023-07-03 22:34:42', '2023-07-03 22:34:53');
INSERT INTO `tb_resource` VALUES (1675800348175892483, '获取用户选项数据', '获取用户选项数据集', 'GET', '/admin/user/listUserOptions', '2023-07-03 22:55:11', '2023-07-03 22:55:27');
INSERT INTO `tb_resource` VALUES (1676236928191561728, '清空操作日志', '清空操作日志中的所有数据', 'GET', '/admin/operate-log/clear', '2023-07-04 22:31:05', '2023-07-04 22:31:14');
INSERT INTO `tb_resource` VALUES (1676421084687106050, '获取登录日志信息', '获取所有登录日志信息', 'POST', '/admin/login-info/list', '2023-07-05 10:43:50', '2023-07-05 02:44:01');
INSERT INTO `tb_resource` VALUES (1676421084687106051, '批量删除登录日志信息', '批量删除用户登录日志信息', 'DELETE', '/admin/login-info/delete', '2023-07-05 10:45:47', '2023-07-05 10:45:49');
INSERT INTO `tb_resource` VALUES (1676421084687106052, '清空登录日志信息', '清空所有用户登录日志信息', 'GET', '/admin/login-info/clear', '2023-07-05 10:46:30', '2023-07-05 10:46:32');
INSERT INTO `tb_resource` VALUES (1677234418973933568, '获取服务监控信息', '获取系统服务监控信息', 'GET', '/admin/service-monitor/getMonitorInfo', '2023-07-07 16:34:54', '2023-07-07 08:35:04');
INSERT INTO `tb_resource` VALUES (1678704512069533696, '获取所有在线用户', '获取当前所有在线用户', 'POST', '/admin/online/list', '2023-07-11 17:56:01', '2023-07-12 23:33:21');
INSERT INTO `tb_resource` VALUES (1679151716034936832, '下线指定用户', '将指定用户下线', 'DELETE', '/admin/online/offline/**', '2023-07-12 23:33:03', '2023-07-12 23:33:14');
INSERT INTO `tb_resource` VALUES (1679512161577074688, '获取所有缓存键类型', '获取所有Redis缓存键类型', 'GET', '/admin/cache/listCacheType', '2023-07-13 23:26:11', '2023-07-14 02:43:40');
INSERT INTO `tb_resource` VALUES (1679675931226013696, '获取所有缓存键', '获取所有缓存键', 'GET', '/admin/cache/listCacheKeys/**', '2023-07-14 10:16:09', '2023-07-14 02:16:25');
INSERT INTO `tb_resource` VALUES (1679732738346713088, '获取指定缓存', '获取指定缓存', 'GET', '/admin/cache/getCacheInfoByKey/**', '2023-07-14 14:01:49', '2023-07-14 06:01:59');
INSERT INTO `tb_resource` VALUES (1680179775546589184, '清除缓存列表', '清除缓存列表', 'DELETE', '/admin/cache/clearCacheKeys/**', '2023-07-15 19:38:03', '2023-07-15 19:44:28');
INSERT INTO `tb_resource` VALUES (1680179775546589185, '删除指定缓存', '删除指定缓存', 'DELETE', '/admin/cache/deleteCacheKey/**', '2023-07-15 21:39:56', '2023-07-15 21:40:07');
INSERT INTO `tb_resource` VALUES (1681231793782521856, '获取指定用户信息', '根据用户ID获取指定用户信息', 'GET', '/admin/user-info/getUserInfoById/**', '2023-07-18 17:20:09', '2023-07-18 09:23:06');
INSERT INTO `tb_resource` VALUES (1682422693888000001, '获取系统配置', '获取系统基本配置', 'GET', '/admin/config/getConfig', '2023-07-22 22:05:04', '2023-07-22 22:07:29');
INSERT INTO `tb_resource` VALUES (1683094177929232384, '保存系统基本配置', '更新系统基本配置', 'POST', '/admin/config/save', '2023-07-23 20:38:02', '2023-07-23 20:38:05');
INSERT INTO `tb_resource` VALUES (1683131008930545664, '获取首页内容', '获取系统后台首页信息', 'GET', '/admin/index/getHomeInfo', '2023-07-23 23:04:49', '2023-07-23 23:04:52');
INSERT INTO `tb_resource` VALUES (1683471580354576384, '富文本上传图片', '富文本上传图片，默认保存到文章图片中', 'POST', '/admin/file/addImage', '2023-07-24 21:38:56', '2023-07-24 21:39:05');
INSERT INTO `tb_resource` VALUES (1683471580354576385, '新增用户', '新增用户', 'POST', '/admin/user/insert', '2023-07-24 22:47:55', '2023-07-24 22:48:04');
INSERT INTO `tb_resource` VALUES (1683841299993591808, '更新指定用户信息', '更新指定用户信息', 'PUT', '/admin/user/updateUser', '2023-07-25 22:08:08', '2023-07-26 23:20:41');
INSERT INTO `tb_resource` VALUES (1683841299993591809, '重置指定用户密码', '重置指定用户密码', 'GET', '/admin/user/resetPassword/**', '2023-07-25 22:28:23', '2023-07-25 23:20:56');
INSERT INTO `tb_resource` VALUES (1683841299993591810, '更新指定用户状态', '更新指定用户状态', 'GET', '/admin/user/updateUserStatus/**', '2023-07-25 22:58:29', '2023-07-25 22:58:31');
INSERT INTO `tb_resource` VALUES (1684082256563404800, '获取角色选项', '获取角色选项', 'GET', '/admin/role/listRoleOptions', '2023-07-26 14:03:37', '2023-07-26 14:03:40');
INSERT INTO `tb_resource` VALUES (1684128046551924736, '更新用户角色', '更新用户角色', 'PUT', '/admin/user-role/update', '2023-07-26 17:06:18', '2023-07-26 17:06:21');
INSERT INTO `tb_resource` VALUES (1684216181440905216, '批量删除用户', '批量删除指定用户', 'DELETE', '/admin/user/delete', '2023-07-26 22:57:17', '2023-07-26 22:57:26');
INSERT INTO `tb_resource` VALUES (1684541750435119104, '分页查询资源模块', '分页查询资源模块', 'POST', '/admin/module/list', '2023-07-27 20:34:10', '2023-07-27 20:34:12');
INSERT INTO `tb_resource` VALUES (1684541750435119105, '根据ID查询模块内容', '获取指定模块信息', 'GET', '/admin/module/getModuleById/**', '2023-07-27 20:51:12', '2023-07-27 20:51:14');
INSERT INTO `tb_resource` VALUES (1684548449283866624, '添加-更新资源模块', '添加-更新资源模块', 'POST', '/admin/module/save', '2023-07-27 20:57:44', '2023-07-27 20:57:47');
INSERT INTO `tb_resource` VALUES (1684548449283866625, '批量删除资源模块数据', '批量删除资源模块数据', 'DELETE', '/admin/module/delete', '2023-07-27 20:58:18', '2023-07-27 20:58:21');
INSERT INTO `tb_resource` VALUES (1684568413176856576, '分页获取所有资源', '分页获取所有资源', 'POST', '/admin/resource/list', '2023-07-27 22:17:43', '2023-07-27 22:17:45');
INSERT INTO `tb_resource` VALUES (1684568413176856577, '更新指定请求资源', '更新指定请求资源', 'PUT', '/admin/resource/save', '2023-07-27 22:38:18', '2023-07-27 22:43:58');
INSERT INTO `tb_resource` VALUES (1684568413176856578, '获取指定资源信息', '根据ID获取指定资源信息', 'GET', '/admin/resource/getResourceById/**', '2023-07-27 22:39:15', '2023-07-27 22:39:17');
INSERT INTO `tb_resource` VALUES (1684832097144930304, '分页获取指定模块请求资源', '分页获取指定模块请求资源', 'POST', '/admin/module-resource/list', '2023-07-28 15:44:41', '2023-07-28 15:44:44');
INSERT INTO `tb_resource` VALUES (1685590302728912896, '获取指定模块可以添加的接口', '获取指定模块可以添加的接口', 'GET', '/admin/module-resource/listResourceById/**', '2023-07-30 17:57:37', '2023-07-30 17:57:39');
INSERT INTO `tb_resource` VALUES (1685641090620719104, '批量删除模块对应请求资源', '批量删除模块对应请求资源', 'DELETE', '/admin/module-resource/delete', '2023-07-30 21:56:44', '2023-07-30 21:56:48');
INSERT INTO `tb_resource` VALUES (1685664653591445512, '为指定模块分配资源请求', '为指定模块分配资源请求', 'POST', '/admin/module-resource/save', '2023-07-30 23:11:07', '2023-07-30 23:11:10');
INSERT INTO `tb_resource` VALUES (1686025120788774912, '获取角色对应模块权限', '获取角色对应模块权限', 'GET', '/admin/module-role/listByRoleId/**', '2023-07-31 22:45:26', '2023-07-31 22:45:28');
INSERT INTO `tb_resource` VALUES (1686370540320718848, '分配角色模块权限', '分配角色模块权限', 'POST', '/admin/module-role/save', '2023-08-01 21:37:38', '2023-08-01 21:38:41');
INSERT INTO `tb_resource` VALUES (1686722622882054144, '获取菜单数据', '获取菜单数据', 'POST', '/admin/menu/list', '2023-08-02 20:57:14', '2023-08-02 21:33:42');

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
INSERT INTO `tb_role` VALUES (1633657944153260032, '默认用户', 'ROLE_DEFAULT', '默认用户测试', '2023-03-09 10:36:20', '2023-07-25 22:11:18');
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
INSERT INTO `tb_user` VALUES (1633027432405532672, '1072876976@qq.com', '$2a$10$Kbb.k6umlihAQBRtSxNGiuHaqT5YP4IVP9Cmytfn8xsecp7Z4e50G', '世纪末的架构师', 'https://s1.ax1x.com/2023/04/26/p9K181A.jpg', 1, NULL, '2023-03-07 16:52:16', '2023-07-26 23:23:29');
INSERT INTO `tb_user` VALUES (1653794265890816000, '2675525537@qq.com', '$2a$10$INorakGPR9WOUBOSQGh3k.QhZLcbjr..hgALnlgdQ1t3D.hgZKqey', '测试账号', 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/avatar/20230529192556.jpg', 1, '自古英雄出炼狱，破马长枪定乾坤！', '2023-05-03 16:15:23', '2023-07-25 23:33:14');
INSERT INTO `tb_user` VALUES (1684221352883519488, '1@qq.com', '$2a$10$JcnvFXeXlcU7YFjHje2n5eaiiVxO/byA0hSBMT.RubSbhjXlcKUve', '123456', 'https://blog-yk0504.oss-cn-hangzhou.aliyuncs.com/avatar/20230529192531.jpg', 1, NULL, '2023-07-26 23:17:17', '2023-07-26 23:23:24');

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
INSERT INTO `tb_user_role` VALUES (1683843226475167744, 1683843225900548096, 1633657944153260032);
INSERT INTO `tb_user_role` VALUES (1684217316075634688, 1684217315379380224, 1633657944153260032);
INSERT INTO `tb_user_role` VALUES (1684217357158842368, 1684217356554862592, 1633657944153260032);
INSERT INTO `tb_user_role` VALUES (1684221353441361920, 1684221352883519488, 1667607066451116032);

SET FOREIGN_KEY_CHECKS = 1;
