/*
SQLyog Job Agent Version 9.62 Copyright(c) Webyog Inc. All Rights Reserved.


MySQL - 5.7.32-log : Database - pf
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pf` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `pf`;

/*Table structure for table `position` */

DROP TABLE IF EXISTS `position`;

CREATE TABLE `position` (
  `id` int(7) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `deptCode` varchar(64) DEFAULT NULL COMMENT '部门代码',
  `deptName` varchar(64) DEFAULT NULL COMMENT '部门名称',
  `employer` varchar(64) DEFAULT NULL COMMENT '用人司局',
  `organNature` varchar(64) DEFAULT NULL COMMENT '机构性质',
  `positionRecruitment` varchar(64) DEFAULT NULL COMMENT '招考职位',
  `positionAttribute` varchar(64) DEFAULT NULL COMMENT '职位属性',
  `positionDistribution` varchar(64) DEFAULT NULL COMMENT '职位分布',
  `positionSynopsis` varchar(256) DEFAULT NULL COMMENT '职位简介',
  `positionCode` varchar(64) DEFAULT NULL COMMENT '职位代码',
  `organLevel` varchar(64) DEFAULT NULL COMMENT '机构层级',
  `examType` varchar(64) DEFAULT NULL COMMENT '考试类别',
  `recruitmentNumber` varchar(64) DEFAULT NULL COMMENT '招考人数',
  `major` varchar(256) DEFAULT NULL COMMENT '专业',
  `education` varchar(64) DEFAULT NULL COMMENT '学历',
  `degree` varchar(64) DEFAULT NULL COMMENT '学位',
  `politicalOutlook` varchar(64) DEFAULT NULL COMMENT '政治面貌',
  `grassrootsWorkYears` varchar(64) DEFAULT NULL COMMENT '基层工作最低年限',
  `grassrootsWorkUndergo` varchar(128) DEFAULT NULL COMMENT '服务基层项目工作经历',
  `ifInterviewStage` varchar(64) DEFAULT NULL COMMENT '是否在面试阶段组织专业能力测试',
  `interviewPersonnelRatio` varchar(64) DEFAULT NULL COMMENT '面试人员比例',
  `workplace` varchar(64) DEFAULT NULL COMMENT '工作地点',
  `location` varchar(64) DEFAULT NULL COMMENT '落户地点',
  `remarks` varchar(4096) DEFAULT NULL COMMENT '备注',
  `deptWebsite` varchar(64) DEFAULT NULL COMMENT '部门网站',
  `consultTel1` varchar(64) DEFAULT NULL COMMENT '咨询电话1',
  `consultTel2` varchar(64) DEFAULT NULL COMMENT '咨询电话2',
  `consultTel3` varchar(64) DEFAULT NULL COMMENT '咨询电话3',
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15395 DEFAULT CHARSET=utf8;

/*Data for the table `position` */

insert  into `position`(`id`,`deptCode`,`deptName`,`employer`,`organNature`,`positionRecruitment`,`positionAttribute`,`positionDistribution`,`positionSynopsis`,`positionCode`,`organLevel`,`examType`,`recruitmentNumber`,`major`,`education`,`degree`,`politicalOutlook`,`grassrootsWorkYears`,`grassrootsWorkUndergo`,`ifInterviewStage`,`interviewPersonnelRatio`,`workplace`,`location`,`remarks`,`deptWebsite`,`consultTel1`,`consultTel2`,`consultTel3`) values (15391,'116109','生态环境部华南核与辐射安全监督站','生态环境部华南核与辐射安全监督站','中央国家行政机关参照公务员法管理事业单位','核安全监督岗位一级主任科员及以下','普通职位（不含非通用语、特殊专业职位）','其他职位','核与辐射安全监督管理','400110116001','省（副省）级','省级以上(含副省级)职位','2','核工程与核技术及相关专业','本科及以上','与最高学历相对应的学位','不限','无限制','无限制','否','5:1','广东省深圳市福田区','广东省深圳市福田区','1.有工作经历的人员其工作经历应与所学专业相关\n2.录用后需在核设施现场工作','http://scro.mee.gov.cn','075523964950','075583521247',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
