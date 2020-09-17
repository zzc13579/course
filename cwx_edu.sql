/*
 Navicat Premium Data Transfer

 Source Server         : con
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : cwx_edu

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 17/09/2020 12:44:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for edu_chapter
-- ----------------------------
DROP TABLE IF EXISTS `edu_chapter`;
CREATE TABLE `edu_chapter`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '章节ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '章节名称',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '显示排序',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_chapter
-- ----------------------------
INSERT INTO `edu_chapter` VALUES ('1301831450340970498', '1301831435472162818', 'aa', 0, '2020-09-04 18:36:19', '2020-09-04 18:36:19');
INSERT INTO `edu_chapter` VALUES ('1301835318646886401', '1301835301953556482', 'll', 0, '2020-09-04 18:51:42', '2020-09-04 18:51:42');
INSERT INTO `edu_chapter` VALUES ('1301836990639067138', '1301836975568932866', 'mm', 0, '2020-09-04 18:58:20', '2020-09-04 18:58:20');
INSERT INTO `edu_chapter` VALUES ('1301840254122151937', '1301840232445988866', 'nn', 0, '2020-09-04 19:11:18', '2020-09-04 19:11:18');
INSERT INTO `edu_chapter` VALUES ('1305170212206706689', '1305167481618333697', 'aaabbb', 0, '2020-09-13 23:43:22', '2020-09-13 23:49:47');

-- ----------------------------
-- Table structure for edu_course
-- ----------------------------
DROP TABLE IF EXISTS `edu_course`;
CREATE TABLE `edu_course`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
  `teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程讲师ID',
  `subject_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程专业ID',
  `subject_parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程专业父级ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程标题',
  `price` decimal(10, 4) UNSIGNED NOT NULL COMMENT '课程销售价格，设置为0则可免费观看',
  `lesson_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '总课时',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程封面图片路径',
  `buy_count` bigint(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '销售数量',
  `view_count` bigint(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '浏览数量',
  `version` bigint(20) UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Draft' COMMENT '课程状态 Draft未发布  Normal已发布',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_title`(`title`) USING BTREE,
  INDEX `idx_subject_id`(`subject_id`) USING BTREE,
  INDEX `idx_teacher_id`(`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_course
-- ----------------------------
INSERT INTO `edu_course` VALUES ('1301831435472162818', '10', '1101348945411493889', '1101348945352773634', 'aaa', 2.0000, 3, 'http://cwx-file.oss-cn-beijing.aliyuncs.com/cover/default-img.gif', 0, 0, 1, 'Draft', '2020-09-04 18:36:16', '2020-09-04 18:36:16');
INSERT INTO `edu_course` VALUES ('1301835301953556482', '2', '1101348945482797058', '1101348945352773634', 'll', 2.0000, 3, 'http://cwx-file.oss-cn-beijing.aliyuncs.com/cover/default-img.gif', 0, 2, 1, 'Draft', '2020-09-04 18:51:38', '2020-09-09 19:43:13');
INSERT INTO `edu_course` VALUES ('1301836975568932866', '3', '1101348945482797058', '1101348945352773634', 'mmm', 2.0000, 4, 'http://cwx-file.oss-cn-beijing.aliyuncs.com/cover/default-img.gif', 0, 0, 1, 'Draft', '2020-09-04 18:58:17', '2020-09-04 18:58:17');
INSERT INTO `edu_course` VALUES ('1301840232445988866', '2', '1101348945050783746', '1101348944920760321', 'nnn', 2.0000, 2, 'http://cwx-file.oss-cn-beijing.aliyuncs.com/cover/default-img.gif', 0, 4, 1, 'Draft', '2020-09-04 19:11:13', '2020-09-09 19:42:17');
INSERT INTO `edu_course` VALUES ('1305167481618333697', '3', '1305047558430695425', '1305047558044819458', 'bbb', 0.0000, 2, 'https://cwx-file.oss-cn-beijing.aliyuncs.com/cover/default-img.gif', 0, 0, 1, 'Normal', '2020-09-13 23:32:31', '2020-09-14 00:05:55');

-- ----------------------------
-- Table structure for edu_course_description
-- ----------------------------
DROP TABLE IF EXISTS `edu_course_description`;
CREATE TABLE `edu_course_description`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '课程简介',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程简介' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_course_description
-- ----------------------------
INSERT INTO `edu_course_description` VALUES ('11', 'string', '2020-09-11 21:33:30', '2020-09-11 21:33:30');
INSERT INTO `edu_course_description` VALUES ('1104870479077879809', '<p>11</p>', '2019-03-11 06:23:44', '2019-03-11 06:23:44');
INSERT INTO `edu_course_description` VALUES ('1301831435472162818', '<p>aa</p>', '2020-09-04 18:36:16', '2020-09-04 18:36:16');
INSERT INTO `edu_course_description` VALUES ('1301835301953556482', '<p>llll</p>', '2020-09-04 18:51:38', '2020-09-04 18:51:38');
INSERT INTO `edu_course_description` VALUES ('1301836975568932866', '<p>mmm</p>', '2020-09-04 18:58:17', '2020-09-04 18:58:17');
INSERT INTO `edu_course_description` VALUES ('1301840232445988866', '<p>nnn</p>', '2020-09-04 19:11:13', '2020-09-04 19:11:13');
INSERT INTO `edu_course_description` VALUES ('1305167481618333697', '<p>bb</p>', '2020-09-13 23:32:31', '2020-09-13 23:32:31');
INSERT INTO `edu_course_description` VALUES ('66', '22', '2020-09-11 21:56:42', '2020-09-11 21:58:55');

-- ----------------------------
-- Table structure for edu_subject
-- ----------------------------
DROP TABLE IF EXISTS `edu_subject`;
CREATE TABLE `edu_subject`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程类别ID',
  `title` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类别名称',
  `parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程科目' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_subject
-- ----------------------------
INSERT INTO `edu_subject` VALUES ('1305047556539064322', '后端开发', '0', 1, '2020-09-13 15:35:59', '2020-09-13 15:35:59');
INSERT INTO `edu_subject` VALUES ('1305047556853637122', 'Java', '1305047556539064322', 1, '2020-09-13 15:35:59', '2020-09-13 15:35:59');
INSERT INTO `edu_subject` VALUES ('1305047557256290305', 'Python', '1305047556539064322', 2, '2020-09-13 15:35:59', '2020-09-13 15:35:59');
INSERT INTO `edu_subject` VALUES ('1305047558044819458', '前端开发', '0', 3, '2020-09-13 15:35:59', '2020-09-13 15:35:59');
INSERT INTO `edu_subject` VALUES ('1305047558430695425', 'HTML/CSS', '1305047558044819458', 3, '2020-09-13 15:35:59', '2020-09-13 15:35:59');
INSERT INTO `edu_subject` VALUES ('1305047558795599873', 'JavaScript', '1305047558044819458', 4, '2020-09-13 15:35:59', '2020-09-13 15:35:59');
INSERT INTO `edu_subject` VALUES ('1305047559114366977', '云计算', '0', 5, '2020-09-13 15:35:59', '2020-09-13 15:35:59');
INSERT INTO `edu_subject` VALUES ('1305047559466688513', 'Docker', '1305047559114366977', 5, '2020-09-13 15:35:59', '2020-09-13 15:35:59');
INSERT INTO `edu_subject` VALUES ('1305047559877730305', 'Linux', '1305047559114366977', 6, '2020-09-13 15:36:00', '2020-09-13 15:36:00');
INSERT INTO `edu_subject` VALUES ('1305047560255217665', '系统/运维', '0', 7, '2020-09-13 15:36:00', '2020-09-13 15:36:00');
INSERT INTO `edu_subject` VALUES ('1305047560649482241', 'Linux', '1305047560255217665', 7, '2020-09-13 15:36:00', '2020-09-13 15:36:00');
INSERT INTO `edu_subject` VALUES ('1305047561047941122', 'Windows', '1305047560255217665', 8, '2020-09-13 15:36:00', '2020-09-13 15:36:00');
INSERT INTO `edu_subject` VALUES ('1305047561450594306', '数据库', '0', 9, '2020-09-13 15:36:00', '2020-09-13 15:36:00');
INSERT INTO `edu_subject` VALUES ('1305047562310426626', 'MySQL', '1305047561450594306', 9, '2020-09-13 15:36:00', '2020-09-13 15:36:00');
INSERT INTO `edu_subject` VALUES ('1305047562729857025', 'MongoDB', '1305047561450594306', 10, '2020-09-13 15:36:00', '2020-09-13 15:36:00');
INSERT INTO `edu_subject` VALUES ('1305047563057012737', '大数据', '0', 11, '2020-09-13 15:36:00', '2020-09-13 15:36:00');
INSERT INTO `edu_subject` VALUES ('1305047563442888705', 'Hadoop', '1305047563057012737', 11, '2020-09-13 15:36:00', '2020-09-13 15:36:00');
INSERT INTO `edu_subject` VALUES ('1305047563774238721', 'Spark', '1305047563057012737', 12, '2020-09-13 15:36:00', '2020-09-13 15:36:00');
INSERT INTO `edu_subject` VALUES ('1305047564134948866', '人工智能', '0', 13, '2020-09-13 15:36:01', '2020-09-13 15:36:01');
INSERT INTO `edu_subject` VALUES ('1305047564537602050', 'Python', '1305047564134948866', 13, '2020-09-13 15:36:01', '2020-09-13 15:36:01');

-- ----------------------------
-- Table structure for edu_teacher
-- ----------------------------
DROP TABLE IF EXISTS `edu_teacher`;
CREATE TABLE `edu_teacher`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '讲师ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '讲师姓名',
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '讲师资历,一句话说明讲师',
  `career` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '讲师简介',
  `level` int(10) UNSIGNED NOT NULL COMMENT '头衔 1高级讲师 2首席讲师',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '讲师头像',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '讲师' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_teacher
-- ----------------------------
INSERT INTO `edu_teacher` VALUES ('1', '刘德华9', '毕业于师范大学数学系，热爱教育事业，执教数学思维6年有余', '具备深厚的数学思维功底、丰富的小学教育经验，授课风格生动活泼，擅长用形象生动的比喻帮助理解、简单易懂的语言讲解难题，深受学生喜欢', 2, 'http://guli-file.oss-cn-beijing.aliyuncs.com/avatar/2019/02/27/073eb5d2-f5f4-488a-82ed-aec8a5289a5d.png', 10, 0, '2018-03-30 17:15:57', '2019-02-23 05:48:45');
INSERT INTO `edu_teacher` VALUES ('10', '唐嫣', '北京师范大学法学院副教授', '北京师范大学法学院副教授、清华大学法学博士。自2004年至今已有9年的司法考试培训经验。长期从事司法考试辅导，深知命题规律，了解解题技巧。内容把握准确，授课重点明确，层次分明，调理清晰，将法条法理与案例有机融合，强调综合，深入浅出。', 1, 'http://cwx-file.oss-cn-beijing.aliyuncs.com/avatar/2020/09/05/98755f2c-5619-4a7f-a0ab-062046f17161.png', 20, 0, '2018-04-03 14:32:19', '2020-09-05 22:25:38');
INSERT INTO `edu_teacher` VALUES ('2', '周润发', '中国人民大学附属中学数学一级教师', '中国科学院数学与系统科学研究院应用数学专业博士，研究方向为数字图像处理，中国工业与应用数学学会会员。参与全国教育科学“十五”规划重点课题“信息化进程中的教育技术发展研究”的子课题“基与课程改革的资源开发与应用”，以及全国“十五”科研规划全国重点项目“掌上型信息技术产品在教学中的运用和开发研究”的子课题“用技术学数学”。', 2, 'http://cwx-file.oss-cn-beijing.aliyuncs.com/avatar/2020/09/05/f7fbbc0c-7122-467e-a608-4398d222fbf4.png', 1, 0, '2018-03-30 18:28:26', '2020-09-05 22:19:32');
INSERT INTO `edu_teacher` VALUES ('3', '钟汉良', '钟汉良钟汉良钟汉良钟汉良', '中教一级职称。讲课极具亲和力。', 1, 'http://cwx-file.oss-cn-beijing.aliyuncs.com/avatar/2020/09/05/31603f3e-a702-4867-8ad3-f80b0b1849b0.png', 2, 0, '2018-03-31 09:20:46', '2020-09-05 22:20:30');
INSERT INTO `edu_teacher` VALUES ('4', '周杰伦', '长期从事考研政治课讲授和考研命题趋势与应试对策研究。考研辅导新锐派的代表。', '政治学博士、管理学博士后，北京师范大学马克思主义学院副教授。多年来总结出了一套行之有效的应试技巧与答题方法，针对性和实用性极强，能帮助考生在轻松中应考，在激励的竞争中取得高分，脱颖而出。', 1, 'http://cwx-file.oss-cn-beijing.aliyuncs.com/avatar/2020/09/05/fe23d463-2590-4de1-b038-8c5808c99f8e.png', 1, 0, '2018-04-03 14:13:51', '2020-09-05 22:19:53');
INSERT INTO `edu_teacher` VALUES ('5', '陈伟霆', '人大附中2009届毕业生', '北京大学数学科学学院2008级本科生，2012年第八届学生五四奖章获得者，在数学领域取得多项国际国内奖项，学术研究成绩突出。曾被两次评为北京大学三好学生、一次评为北京大学三好标兵，获得过北京大学国家奖学金、北京大学廖凯原奖学金、北京大学星光国际一等奖学金、北京大学明德新生奖学金等。', 1, 'http://cwx-file.oss-cn-beijing.aliyuncs.com/avatar/2020/09/05/ca8e5f86-e347-47d2-8ba1-82e521cd22f6.png', 1, 0, '2018-04-03 14:15:36', '2020-09-05 22:23:23');
INSERT INTO `edu_teacher` VALUES ('6', '姚晨', '华东师范大学数学系硕士生导师，中国数学奥林匹克高级教练', '曾参与北京市及全国多项数学活动的命题和组织工作，多次带领北京队参加高中、初中、小学的各项数学竞赛，均取得优异成绩。教学活而新，能够调动学生的学习兴趣并擅长对学生进行思维点拨，对学生学习习惯的养成和非智力因素培养有独到之处，是一位深受学生喜爱的老师。', 1, 'http://cwx-file.oss-cn-beijing.aliyuncs.com/avatar/2020/09/05/50e93c14-e85a-4625-867e-36d9eb087292.png', 1, 0, '2018-04-01 14:19:28', '2020-09-05 22:21:43');
INSERT INTO `edu_teacher` VALUES ('7', '胡歌', '考研政治辅导实战派专家，全国考研政治命题研究组核心成员。', '法学博士，北京师范大学马克思主义学院副教授，专攻毛泽东思想概论、邓小平理论，长期从事考研辅导。出版著作两部，发表学术论文30余篇，主持国家社会科学基金项目和教育部重大课题子课题各一项，参与中央实施马克思主义理论研究和建设工程项目。', 2, 'http://cwx-file.oss-cn-beijing.aliyuncs.com/avatar/2020/09/05/6133db02-c54a-473a-b84b-bccb30c425b3.png', 8, 0, '2018-04-03 14:21:03', '2020-09-05 22:20:14');
INSERT INTO `edu_teacher` VALUES ('8', '刘德华', '上海师范大学法学院副教授', '上海师范大学法学院副教授、清华大学法学博士。自2004年至今已有9年的司法考试培训经验。长期从事司法考试辅导，深知命题规律，了解解题技巧。内容把握准确，授课重点明确，层次分明，调理清晰，将法条法理与案例有机融合，强调综合，深入浅出。', 1, 'http://cwx-file.oss-cn-beijing.aliyuncs.com/avatar/2020/09/05/4326de42-e7ba-40a9-82e7-8b5615a6755e.png', 9, 0, '2018-04-03 14:23:06', '2020-09-05 22:22:05');
INSERT INTO `edu_teacher` VALUES ('9', '谢娜', '资深课程设计专家，专注10年AACTP美国培训协会认证导师', '十年课程研发和培训咨询经验，曾任国企人力资源经理、大型外企培训经理，负责企业大学和培训体系搭建；曾任专业培训机构高级顾问、研发部总监，为包括广东移动、东莞移动、深圳移动、南方电网、工商银行、农业银行、民生银行、邮储银行、TCL集团、清华大学继续教育学院、中天路桥、广西扬翔股份等超过200家企业提供过培训与咨询服务，并担任近50个大型项目的总负责人。', 1, 'http://cwx-file.oss-cn-beijing.aliyuncs.com/avatar/2020/09/05/45e28bc2-b0c6-4462-a1a2-55d30135e4d3.png', 10, 0, '2018-04-03 14:23:33', '2020-09-05 22:23:41');

-- ----------------------------
-- Table structure for edu_video
-- ----------------------------
DROP TABLE IF EXISTS `edu_video`;
CREATE TABLE `edu_video`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频ID',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
  `chapter_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '章节ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '节点名称',
  `video_source_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原始文件名称',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `play_count` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '播放次数',
  `is_free` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否可以试听：0收费 1免费',
  `duration` float NOT NULL DEFAULT 0 COMMENT '视频时长（秒）',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '视频状态',
  `size` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '视频源文件大小（字节）',
  `version` bigint(20) UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_chapter_id`(`chapter_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程视频' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edu_video
-- ----------------------------
INSERT INTO `edu_video` VALUES ('1301831715462926338', '1301831435472162818', '1301831450340970498', 'aaa', '', NULL, 0, 0, 0, 0, '', 0, 1, '2020-09-04 18:37:22', '2020-09-04 18:37:22');
INSERT INTO `edu_video` VALUES ('1301837059983495170', '1301836975568932866', '1301836990639067138', 'mmm', '', NULL, 0, 0, 0, 0, '', 0, 1, '2020-09-04 18:58:37', '2020-09-04 18:58:37');
INSERT INTO `edu_video` VALUES ('1301840275643125762', '1301840232445988866', '1301840254122151937', 'nn', '', NULL, 0, 0, 0, 0, '', 0, 1, '2020-09-04 19:11:23', '2020-09-04 19:11:23');
INSERT INTO `edu_video` VALUES ('1301840313211506690', '1301840232445988866', '1301840254122151937', 'nn', '', NULL, 0, 0, 0, 0, '', 0, 1, '2020-09-04 19:11:32', '2020-09-04 19:11:32');
INSERT INTO `edu_video` VALUES ('1305175825775099905', '1305167481618333697', '1305170212206706689', '666', '58d54b018d7f436e8891adb27fff3021', NULL, 0, 0, 0, 0, '', 0, 1, '2020-09-14 00:05:41', '2020-09-14 11:41:06');

SET FOREIGN_KEY_CHECKS = 1;
