-- zt add start----
CREATE DATABASE /*!32312 IF NOT EXISTS*/`commodity` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `commodity`;
DROP TABLE IF EXISTS `t_brand`;

CREATE TABLE `t_brand` (
  `id` varchar(35) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `code` varchar(35) COLLATE utf8_bin NOT NULL COMMENT 'code',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT ' 品牌名称',
  `categoryId` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '商品类目ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='商品品牌';


DROP TABLE IF EXISTS `t_category`;

CREATE TABLE `t_category` (
  `id` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '主键',
  `code` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '类目编号',
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '类目名称',
  `level` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '类目级别',
  `parentId` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '父类目ID',
  `status` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '商品状态',
  `lastUser` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '最后修改人',
  `commTypeStr` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '类目字符串 :百货食品>>零食>>>>瓜子',
  `sortNo` int(11) DEFAULT NULL COMMENT '树形同一级的节点排序号 ',
  `description` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `deleteFlag` int(11) DEFAULT NULL COMMENT '删除标识 0未删除 1已删除',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `isChild` int(11) DEFAULT NULL COMMENT '是否有子级'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='商品类目表';

DROP TABLE IF EXISTS `t_commattr`;

CREATE TABLE `t_commattr` (
  `id` varchar(35) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `code` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT 'code',
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '商品属性名称',
  `categoryId` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '类目code',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='商品属性表';

DROP TABLE IF EXISTS `t_commattrvalue`;

CREATE TABLE `t_commattrvalue` (
  `id` varchar(35) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '属性名称',
  `value` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '属性值',
  `attrId` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '属性ID-对应商品属性表',
  `code` varchar(35) COLLATE utf8_bin NOT NULL COMMENT '属性值code',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='商品属性表值';

DROP TABLE IF EXISTS `t_district`;

CREATE TABLE `t_district` (
  `id` varchar(35) COLLATE utf8_bin NOT NULL COMMENT '区域ID',
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '区域名称',
  `level` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '级别',
  `parentId` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '父区域code',
  `desc` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `code` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '区域code',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='区域表';


DROP TABLE IF EXISTS `t_hiscategory`;

CREATE TABLE `t_hiscategory` (
  `id` varchar(35) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `categoryDesc` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '历史商品类目名称集',
  `shopId` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺ID',
  `categoryId` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '最后一级类目ID',
  `code` varchar(35) COLLATE utf8_bin NOT NULL COMMENT '历史类目ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='店铺历史类目表';


-- zt add end----