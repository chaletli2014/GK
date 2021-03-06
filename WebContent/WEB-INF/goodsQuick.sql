--分类
CREATE TABLE tbl_category (
id  					int(11) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
category_name  		varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
category_code  		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
category_desc  		varchar(200) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
parentId  			int(11) 		NULL DEFAULT NULL ,
has_child  			varchar(2) 		CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT;

--数据字典
CREATE TABLE tbl_goods_dictionary (
id  			int(11) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
type_code  	varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
dic_name  	varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
dic_desc  	varchar(200) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT;

--数据字典分类
CREATE TABLE tbl_goods_dictionary_type (
id  			int(11) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
type_name  	varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
type_code  	varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
type_desc  	varchar(200) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--权限表
CREATE TABLE tbl_goods_privilege (
id  					bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
privilege_name  		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
privilege_code  		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
privilege_desc  		varchar(128) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
create_time  			datetime NULL 	DEFAULT NULL ,
create_user  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
update_time  			datetime NULL 	DEFAULT NULL ,
update_user  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--角色表
CREATE TABLE tbl_goods_role (
id  			int(11) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
role_name  	varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
role_code  	varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
role_desc  	varchar(128) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--角色权限
CREATE TABLE tbl_goods_role_privilege (
id  				bigint(20) 	UNSIGNED NOT NULL AUTO_INCREMENT ,
role_code  		varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
privilege_code  	varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--用户角色
CREATE TABLE tbl_goods_user_role (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
role_code  		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
user_id  			bigint(20) 		NULL DEFAULT NULL ,
create_time  		datetime 		NULL DEFAULT NULL ,
create_user  		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--普通住宅表
CREATE TABLE tbl_goods_ordinary_house (
id  					bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
house_code				varchar(20),
building_name  			varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
building_status  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
company  					varchar(128) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
contacter_name  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
contacter_position		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
contacter_telephone  		varchar(20) 	NULL DEFAULT NULL ,
property_name  			varchar(128) 	NULL DEFAULT NULL comment '物业名称',
location  				varchar(128) 	NULL DEFAULT NULL ,
project_position_e  		varchar(128) 	NULL DEFAULT NULL ,
project_position_w  		varchar(128) 	NULL DEFAULT NULL ,
project_position_s  		varchar(128) 	NULL DEFAULT NULL ,
project_position_n  		varchar(128) 	NULL DEFAULT NULL ,
property_type  			varchar(20) 	NULL DEFAULT NULL comment '物业类型',
property_type_o  			varchar(20) 	NULL DEFAULT NULL comment '物业类型其他',
start_year  				int 	NULL DEFAULT 0 ,
start_month  				int 	NULL DEFAULT 0 ,
start_date  				int 	NULL DEFAULT 0 ,
checkin_year  			int 	NULL DEFAULT 0 ,
checkin_month  			int 	NULL DEFAULT 0 ,
checkin_date  			int 	NULL DEFAULT 0 ,
floor_space  				int 	NULL DEFAULT 0 comment '占地面积',
building_number  			int 	NULL DEFAULT 0 comment '建筑总栋数',
has_lift_number  			int 	NULL DEFAULT 0 comment '有电梯栋数',
non_lift_number  			int 	NULL DEFAULT 0 comment '无电梯栋数',
lobby_number  			int 	NULL DEFAULT 0 comment '大堂数量',
lift_lobby_number  		int 	NULL DEFAULT 0 comment '电梯大堂个数',
non_lift_lobby_number 	int 	NULL DEFAULT 0 comment '无电梯大堂个数',
owner_households 			int 	NULL DEFAULT 0 comment '业主总户数',
tenant_households 		int 	NULL DEFAULT 0 comment '租户总户数',
delivery_households 		int 	NULL DEFAULT 0 comment '已经交房户数',
non_delivery_households 	int 	NULL DEFAULT 0 comment '未交房户数',
covered_area 				int 	NULL DEFAULT 0 comment '总建筑面积',
period 					int 	NULL DEFAULT 0 comment '开发期数',
west_east_length 			int 	NULL DEFAULT 0 comment '周界长度-东西',
south_north_length 		int 	NULL DEFAULT 0 comment '周界长度-南北',
plan_sideway_num 			int 	NULL DEFAULT 0 comment '规划出入口数量 - 人行',
plan_carway_num 			int 	NULL DEFAULT 0 comment '规划出入口数量 - 车行',
actual_sideway_num 		int 	NULL DEFAULT 0 comment '实际出入口数量 - 人行',
actual_carway_num 		int 	NULL DEFAULT 0 comment '实际出入口数量 - 车行',
create_date  				datetime 		NULL DEFAULT NULL ,
create_user  				varchar(20) 	NULL DEFAULT NULL ,
update_date  				datetime 		NULL DEFAULT NULL ,
update_user  				varchar(20) 	NULL DEFAULT NULL ,
last_login_time  			datetime 		NULL DEFAULT NULL ,
status  					varchar(2) 		CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--居住物业设施设备表
CREATE TABLE tbl_owner_device (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
owner_code  		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
device_code  		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
owner_device_code	varchar(20)		not null,
installation_site	varchar(200) 	comment '安装地点',
use_date			datetime comment '投用日期',
login_date			datetime comment '登记日期',
createdate  		datetime,
create_user  		varchar(20),
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--不动产设备-电梯
CREATE TABLE tbl_goods_devices_lift (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
lift_type			varchar(20)		not null default '*' comment '电梯分类',	
lift_code  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL default '*',
lift_name  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
lift_desc			varchar(200)	comment '设备描述',
subject_id			int 			not null comment '隶属主体ID',
module_id			int 			not null comment '隶属构件ID',
delivery_date  		datetime		comment '交付日期',
purchase_price		decimal(11,6)	comment '采购价格',
user_name			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL comment '使用人',
brand_code			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL comment '品牌',
lift_purpose  		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '电梯用途',
lift_style  		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '电梯款型',
lift_CT  			double 			comment '载重量',
lift_NS  			double 			comment '额定速度',
lift_QA				varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL comment '保修期限',
car_size  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL comment '轿厢尺寸（宽*深）(mm)',
car_height  		int				comment '轿厢净高(mm)',
door_size  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL comment '开门尺寸（宽*高）(mm)',
main_power  		int 			comment '主机功率(kw)',
made_date			datetime		comment '制造日期',
life_time			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL comment '使用期限',
repository_code		varchar(20) 	comment '物库编码',
create_user  		varchar(20),
create_date			datetime,
update_user			varchar(20),
update_date			datetime,
status				varchar(2),
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--服务商库
CREATE TABLE tbl_goods_service (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
code  				varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
name  				varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
address				varchar(2000)	not null comment '服务商地址',	
telephone			varchar(20)		not null comment '服务商手机号码',
contract_number  	varchar(20)		not null comment '服务商联系电话',
service_content		varchar(2000) 	comment '服务商详细介绍',
create_user  		varchar(20),
createdate			datetime,
update_user			varchar(20),
updatedate			datetime,
status				varchar(2),
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--服务明细库
CREATE TABLE tbl_goods_service_detail (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
provider_code  		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL comment '服务商编码',
product_category  	varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL comment '物品类别',
service_code		varchar(2000)	not null comment '服务范围',	
price				decimal(11,6)	not null comment '服务价格',
service_content		varchar(2000) 	comment '服务详细介绍',
create_user  		varchar(20),
createdate			datetime,
update_user			varchar(20),
updatedate			datetime,
status				varchar(2),
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--商品物联属性表
CREATE TABLE tbl_goods_relationship_property (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
source_table  		varchar(100) 	CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL comment '数据来源表',
source_id  			bigint(20) 		UNSIGNED NOT NULL comment '数据来源主键',
location			varchar(2000)	comment '商品位置',/*存放最新的位置*/
brand_name			varchar(200)	comment '商品品牌商名称',
brand_code			varchar(200)	comment '商品品牌商编码',
design_name			varchar(200) 	comment '商品设计商名称',
design_code			varchar(200) 	comment '商品设计商编码',
manufacturer_name	varchar(200) 	comment '生产制造商名称',
manufacturer_code	varchar(200) 	comment '生产制造商编码',
certification_name	varchar(200) 	comment '检测认证商名称',
certification_code	varchar(200) 	comment '检测认证商编码',
channel_name		varchar(200) 	comment '商品渠道商名称',
channel_code		varchar(200) 	comment '商品渠道商编码',
logistics_name		varchar(200) 	comment '商品物流商名称',
logistics_code		varchar(200) 	comment '商品物流商编码',
owner_name			varchar(200) 	comment '商品所有人名称',
owner_code			varchar(200) 	comment '商品所有人编码',
trusteeship_name	varchar(200) 	comment '商品托管人名称',
trusteeship_code	varchar(200) 	comment '商品托管人编码',
supervision_name	varchar(200) 	comment '市场监管人名称',
supervision_code	varchar(200) 	comment '市场监管人编码',
recycling_name		varchar(200) 	comment '回收处理商名称',
recycling_code		varchar(200) 	comment '回收处理商编码',
create_user  		varchar(20),
createdate			datetime,
update_user			varchar(20),
updatedate			datetime,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--不动产设备表
CREATE TABLE tbl_goods_house_device (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
eq_type				varchar(20)		comment '设施设备分类',
name				varchar(200)	comment '设备名称',
brand				varchar(200)	comment '设备品牌',
style				varchar(200) 	comment '设备款型',
eq_desc				varchar(200) 	comment '描述',
subjectId			int 			comment '隶属主体ID',
moduleId			int 			comment '隶属构件ID',
create_user  		varchar(20),
create_date			datetime,
update_user			varchar(20),
update_date			datetime,
status  			varchar(2) 		DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

alter table tbl_web_userinfo add column has_house varchar(5) not null default '0' comment '是否拥有不动产';
alter table tbl_web_userinfo add column has_service varchar(5) not null default '0' comment '是否提供服务';

--服务商客户表
CREATE TABLE tbl_goods_sp_customer (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
sp_code				varchar(200)	comment '服务商编码',
category_code		varchar(200)	comment '服务分类',
service_type		varchar(200)	comment '服务范围',
customer_name		varchar(200)	comment '客户名称',
customer_code		varchar(200)	comment '客户编码',
status				varchar(10) 	comment '当前状态',
create_user  		varchar(20),
createdate			datetime,
update_user			varchar(20),
updatedate			datetime,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--服务商关联请求表
CREATE TABLE tbl_goods_sp_request (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
sp_customer_id		bigint(20)      UNSIGNED NOT NULL,
sp_code				varchar(200)	comment '服务商编码',
service_type		varchar(200)	comment '服务范围',
customer_name		varchar(200)	comment '客户名称',
status				varchar(10) 	comment '当前请求状态',
create_user  		varchar(20),
createdate			datetime,
update_user			varchar(20),
updatedate			datetime,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--配置表
CREATE TABLE tbl_goods_config (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
config_code			varchar(200)	comment '配置项编码',
config_value		varchar(200)	comment '配置项内容',
create_user  		varchar(20),
createdate			datetime,
update_user			varchar(20),
updatedate			datetime,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

alter table tbl_goods_ordinary_house add index IDX_HOUSE_CODE(house_code);
alter table tbl_goods_house_device add index IDX_HOUSE_CODE_DEVICE_TYPE(house_code,device_type);

--用户信息表
CREATE TABLE tbl_web_userinfo (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
name  				varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '真实姓名',
login_name  		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL comment '登录名',
password  			varchar(64) 	CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL comment '登录密码',
telephone  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '联系电话',
level  				varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
createdate  		datetime 		NULL DEFAULT NULL ,
updatedate  		datetime 		NULL DEFAULT NULL ,
last_login_time  	datetime 		NULL DEFAULT NULL ,
status  			varchar(2) 		CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--公司信息表
CREATE TABLE tbl_goods_company (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
industry			varchar(200)	comment '公司行业',
company_name		varchar(200)	comment '公司名称',
company_province	varchar(200)   comment '公司所在省份',
company_city		varchar(200)   comment '公司所在城市',
company_email		varchar(200)   comment '公司邮箱',
create_user  		varchar(20),
createdate			datetime,
update_user			varchar(20),
updatedate			datetime,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--公司用户信息表
CREATE TABLE tbl_goods_company_user (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
company_id			varchar(200)	comment '公司ID',
user_id				varchar(200)	comment '用户ID',
create_user  		varchar(20),
createdate			datetime,
update_user			varchar(20),
updatedate			datetime,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

/**
 * 不动产服务商和组件商关系表
 */
CREATE TABLE tbl_goods_house_sp (
id  					bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
house_code				varchar(20),
module_sp_type  		varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
module_sp_value  		varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
create_date  			datetime 		NULL DEFAULT NULL ,
create_user  			varchar(20) 	NULL DEFAULT NULL ,
update_date  			datetime 		NULL DEFAULT NULL ,
update_user  			varchar(20) 	NULL DEFAULT NULL ,
status  				varchar(2) 		CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
remark  				varchar(200) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

/**
 * 资品库表
 */
CREATE TABLE tbl_goods_repository (
id  					bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
repository_name			varchar(20)		CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
repository_code  		varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
repository_desc  		varchar(255) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
repository_type  		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
create_date  			datetime 		NULL DEFAULT NULL ,
create_user  			varchar(20) 	NULL DEFAULT NULL ,
update_date  			datetime 		NULL DEFAULT NULL ,
update_user  			varchar(20) 	NULL DEFAULT NULL ,
status  				varchar(2) 		CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

/**
 * 资品库用户表
 */
CREATE TABLE tbl_goods_repository_user (
id  					bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
repository_code			varchar(20)		CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
user_code  				varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
priv  					varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
create_date  			datetime 		NULL DEFAULT NULL ,
create_user  			varchar(20) 	NULL DEFAULT NULL ,
update_date  			datetime 		NULL DEFAULT NULL ,
update_user  			varchar(20) 	NULL DEFAULT NULL ,
status  				varchar(2) 		CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

alter table tbl_goods_ordinary_house add column repository_code varchar(20) not null default '' comment '所属资品库';

--普通住宅表
CREATE TABLE tbl_goods_ordinary_house_owned (
id  					bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
house_code				varchar(20),
building_name  			varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
building_status  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
company  					varchar(128) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
contacter_name  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
contacter_position		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
contacter_telephone  		varchar(20) 	NULL DEFAULT NULL ,
property_name  			varchar(128) 	NULL DEFAULT NULL comment '物业名称',
location  				varchar(128) 	NULL DEFAULT NULL ,
project_position_e  		varchar(128) 	NULL DEFAULT NULL ,
project_position_w  		varchar(128) 	NULL DEFAULT NULL ,
project_position_s  		varchar(128) 	NULL DEFAULT NULL ,
project_position_n  		varchar(128) 	NULL DEFAULT NULL ,
property_type  			varchar(20) 	NULL DEFAULT NULL comment '物业类型',
property_type_o  			varchar(20) 	NULL DEFAULT NULL comment '物业类型其他',
start_year  				int 	NULL DEFAULT 0 ,
start_month  				int 	NULL DEFAULT 0 ,
start_date  				int 	NULL DEFAULT 0 ,
`province`  			varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`city`  				varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`county`  				varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
checkin_year  			int 	NULL DEFAULT 0 ,
checkin_month  			int 	NULL DEFAULT 0 ,
checkin_date  			int 	NULL DEFAULT 0 ,
floor_space  				int 	NULL DEFAULT 0 comment '占地面积',
building_number  			int 	NULL DEFAULT 0 comment '建筑总栋数',
has_lift_number  			int 	NULL DEFAULT 0 comment '有电梯栋数',
non_lift_number  			int 	NULL DEFAULT 0 comment '无电梯栋数',
lobby_number  			int 	NULL DEFAULT 0 comment '大堂数量',
lift_lobby_number  		int 	NULL DEFAULT 0 comment '电梯大堂个数',
non_lift_lobby_number 	int 	NULL DEFAULT 0 comment '无电梯大堂个数',
owner_households 			int 	NULL DEFAULT 0 comment '业主总户数',
tenant_households 		int 	NULL DEFAULT 0 comment '租户总户数',
delivery_households 		int 	NULL DEFAULT 0 comment '已经交房户数',
non_delivery_households 	int 	NULL DEFAULT 0 comment '未交房户数',
covered_area 				int 	NULL DEFAULT 0 comment '总建筑面积',
period 					int 	NULL DEFAULT 0 comment '开发期数',
west_east_length 			int 	NULL DEFAULT 0 comment '周界长度-东西',
south_north_length 		int 	NULL DEFAULT 0 comment '周界长度-南北',
plan_sideway_num 			int 	NULL DEFAULT 0 comment '规划出入口数量 - 人行',
plan_carway_num 			int 	NULL DEFAULT 0 comment '规划出入口数量 - 车行',
actual_sideway_num 		int 	NULL DEFAULT 0 comment '实际出入口数量 - 人行',
actual_carway_num 		int 	NULL DEFAULT 0 comment '实际出入口数量 - 车行',
create_date  				datetime 		NULL DEFAULT NULL ,
create_user  				varchar(20) 	NULL DEFAULT NULL ,
update_date  				datetime 		NULL DEFAULT NULL ,
update_user  				varchar(20) 	NULL DEFAULT NULL ,
last_login_time  			datetime 		NULL DEFAULT NULL ,
status  					varchar(2) 		CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
repository_code				varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;


alter table tbl_goods_ordinary_house add column finish_year int NULL DEFAULT 0;
alter table tbl_goods_ordinary_house add column finish_month int NULL DEFAULT 0;
alter table tbl_goods_ordinary_house add column finish_date int NULL DEFAULT 0;

alter table tbl_goods_ordinary_house_owned add column finish_year int NULL DEFAULT 0;
alter table tbl_goods_ordinary_house_owned add column finish_month int NULL DEFAULT 0;
alter table tbl_goods_ordinary_house_owned add column finish_date int NULL DEFAULT 0;

/**
 * 不动产二级服务商和组件商关系表
 */
CREATE TABLE tbl_goods_house_sp_2nd (
id  					bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
house_code				varchar(20),
sp_type  				varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
module_type  			varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
sp_name  				varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
create_date  			datetime 		NULL DEFAULT NULL ,
create_user  			varchar(20) 	NULL DEFAULT NULL ,
update_date  			datetime 		NULL DEFAULT NULL ,
update_user  			varchar(20) 	NULL DEFAULT NULL ,
status  				varchar(2) 		CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
remark  				varchar(200) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

/**
 * 物链管理-小区主体/设施设备/材料装饰
 */
CREATE TABLE tbl_goods_house_module_sp (
id  					bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
repository_code			varchar(20),
from_source  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '来源',
sp_type_code  			varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '供应商类型',
module_type1  			varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '产品类别1',
module_type2  			varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '产品类别2',
sp_name  				varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '供应商名称',
sp_id	  				varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '供应商ID',
sp_tel  				varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '供应商电话',
sp_phone  				varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '供应商手机号码',
relation_status  		varchar(2) 		CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '状态',
create_date  			datetime 		NULL DEFAULT NULL ,
create_user  			varchar(20) 	NULL DEFAULT NULL ,
update_date  			datetime 		NULL DEFAULT NULL ,
update_user  			varchar(20) 	NULL DEFAULT NULL ,
status  				varchar(2) 		CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
remark  				varchar(200) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--产品库
CREATE TABLE tbl_goods_product (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
product_type		varchar(200)	not null comment '产品类目',
product_name		varchar(200)	not null comment '产品名称',
product_price		decimal(11,6)	not null comment '产品价格',
repository_code		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci not NULL,
create_user  		varchar(20),
create_date			datetime,
update_user			varchar(20),
update_date			datetime,
status				varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
remark				varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--不动产主体
CREATE TABLE tbl_goods_house_subject (
id  					int(11) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
subject_name  			varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
subject_code  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
subject_desc  			varchar(200) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
parentId  				int(11) 		NULL DEFAULT NULL ,
subject_level			varchar(2)		CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
repository_code  		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT;

--主体构件
CREATE TABLE tbl_goods_house_subject_module (
id  					int(11) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
subject_id  			int(11) 		NOT NULL,
module_type_code		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
module_name  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
module_code  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
module_desc  			varchar(200) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
create_user  			varchar(20),
create_date				datetime,
update_user				varchar(20),
update_date				datetime,
status					varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
remark					varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT;

--产品-电梯
CREATE TABLE tbl_goods_product_lift (
id  					int(11) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
code  					varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '电梯编码',
liftBrand				varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '电梯品牌',
liftPurpose  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '电梯用途',
liftStyle  				varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '电梯款型',
liftCT  				double 			comment '载重量',
liftNS  				double 			comment '额定速度',
liftQA  				int 			comment '质保',
price  					double 			comment '价格',
holeSize  				varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '井道尺寸（宽*深）(mm)',
pitDepth 				int 			comment '底坑深度(mm)',
overheadHeight  		int				comment '顶层高度(mm)',
reservation  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '门洞预留（宽*高）(mm)',
roomSize  				varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '机房尺寸（宽*深）(mm)',
roomHeight  			int				comment '机房净高(mm)',
carSize  				varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '轿厢尺寸（宽*深）(mm)',
carHeight  				int				comment '轿厢净高(mm)',
doorSize  				varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '开门尺寸（宽*高）(mm)',
mainPower  				int 			comment '主机功率(kw)',
manufacturer  			varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL comment '生产厂家',
madeDate				datetime		comment '制造日期',
repository_code  		varchar(50) 	CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
create_user  			varchar(20),
create_date				datetime,
update_user				varchar(20),
update_date				datetime,
status					varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
remark					varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT;

--不动产材料装饰表--涂料
CREATE TABLE tbl_goods_house_paint (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
type1_code			varchar(20)		comment '材料类别1',
type2_code			varchar(20)		comment '材料类别2',
paint_name			varchar(200)	comment '名称',
paint_desc			varchar(200) 	comment '描述',
subject_id			int 			comment '隶属主体ID',
module_id			int 			comment '隶属构件ID',
paint_brand			varchar(20)		comment '材料品牌',
paint_model			varchar(20)		comment '材料型号',
paint_style			varchar(20)		comment '材料类型',
liquid_type			varchar(20)		comment '液态类型',
film_former			varchar(20)		comment '成膜物性质',
paint_storage		varchar(20)		comment '储存器',
solid_proportion  	double 			comment '固体含量',
brush_area  		double 			comment '涂刷面积',
tensile_strength  	double 			comment '拉伸强度',
break_elongation  	double 			comment '断裂伸长率',
surface_dryTime		varchar(20)		comment '表干时间',
drying_time			varchar(20)		comment '干燥时间',
coating_filmColor	varchar(20)		comment '漆膜颜色',
origin_place		varchar(20)		comment '产地',
repository_code		varchar(20) 	comment '物库编码',
create_user  		varchar(20),
create_date			datetime,
update_user			varchar(20),
update_date			datetime,
status  			varchar(2) 		DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;
--不动产资源文件表
CREATE TABLE tbl_goods_house_file (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
repository_code		varchar(20)		comment '物库编码',
file_type			varchar(20)		comment '文件类型',
file_name			varchar(200)	comment '文件名称',
file_path			varchar(200) 	comment '文件路径',
is_main				int 			comment '是否是封面图片，1是0否',
create_user  		varchar(20),
create_date			datetime,
update_user			varchar(20),
update_date			datetime,
status  			varchar(2) 		DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

alter table tbl_goods_house_subject add column status varchar(2) default '1';
alter table tbl_goods_house_subject add column create_date datetime;
alter table tbl_goods_house_subject add column create_user varchar(20);
alter table tbl_goods_house_subject add column update_date datetime;
alter table tbl_goods_house_subject add column update_user varchar(20);


--实物类产品库
CREATE TABLE tbl_goods_product_obj (
id  				bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
product_type		varchar(200)	not null comment '产品类目',
product_name		varchar(200)	not null comment '产品名称',
product_brand		varchar(200)	not null comment '产品品牌',
product_model		varchar(200)	not null comment '产品型号',
item_code			varchar(200)	not null comment '产品编码',
product_dom			date			not null comment '生产日期',
product_QA			int				not null comment '质保',
product_price		decimal(19,2)	not null comment '产品价格',
repository_code		varchar(20) 	CHARACTER SET utf8 COLLATE utf8_general_ci not NULL,
create_user  		varchar(20),
create_date			datetime,
update_user			varchar(20),
update_date			datetime,
status				varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
remark				varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

alter table tbl_goods_dictionary_type modify column type_name varchar(50);
alter table tbl_goods_dictionary_type modify column type_code varchar(50);
alter table tbl_goods_dictionary modify column type_code varchar(50);
alter table tbl_goods_dictionary modify column dic_name varchar(50);
alter table tbl_goods_dictionary modify column dic_code varchar(50);

--产品资源文件表
CREATE TABLE tbl_goods_product_source (
id  							bigint(20) 		UNSIGNED NOT NULL AUTO_INCREMENT ,
repository_code					varchar(20)		comment '物库编码',
tbl_goods_product_obj_id		bigint(20)		comment '产品主键',
file_type						varchar(20)		comment '文件类型',
file_name						varchar(200)	comment '文件名称',
file_path						varchar(200) 	comment '文件路径',
is_main							int 			comment '是否是封面图片，1是0否',
create_user  					varchar(20),
create_date						datetime,
update_user						varchar(20),
update_date						datetime,
status  						varchar(2) 		DEFAULT NULL ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--物讯表
CREATE TABLE tbl_goods_message (
id  				bigint(20) 			UNSIGNED NOT NULL AUTO_INCREMENT ,
message_type		varchar(20)			comment '消息类型：common为普通文本讯息',
message_title		varchar(200)		comment '消息标题',
message_content		varchar(20000)   	comment '消息内容',
create_user  		varchar(20),
create_date			datetime,
update_user			varchar(20),
update_date			datetime,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

--物讯关系人表
CREATE TABLE tbl_goods_message_detail (
id  				bigint(20) 			UNSIGNED NOT NULL AUTO_INCREMENT ,
message_id			bigint(20)			comment '物讯主键ID',
sender_id			bigint(20)			comment '发件人',
receiver_id			bigint(20)   		comment '收件人',
status_se			varchar(2)			comment '发件人物讯状态 0删除、1有效',
status_rec			varchar(2)			comment '收件人物讯状态 0删除、1未读、2已读',
create_user  		varchar(20),
create_date			datetime,
update_user			varchar(20),
update_date			datetime,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=0
ROW_FORMAT=COMPACT
;

alter table tbl_web_userinfo add column user_type varchar(20) not null default 'common' comment '用户类型';

alter table tbl_goods_ordinary_house add column lift_num int NULL DEFAULT 0 comment '电梯数';
alter table tbl_goods_ordinary_house add column parking_vg_num int NULL DEFAULT 0 comment '地上停车位';
alter table tbl_goods_ordinary_house add column parking_ug_num int NULL DEFAULT 0 comment '地下停车位';
alter table tbl_goods_ordinary_house add column fire_pump_num int NULL DEFAULT 0 comment '消防水泵数';

alter table tbl_goods_ordinary_house_owned add column lift_num int NULL DEFAULT 0 comment '电梯数';
alter table tbl_goods_ordinary_house_owned add column parking_vg_num int NULL DEFAULT 0 comment '地上停车位';
alter table tbl_goods_ordinary_house_owned add column parking_ug_num int NULL DEFAULT 0 comment '地下停车位';
alter table tbl_goods_ordinary_house_owned add column fire_pump_num int NULL DEFAULT 0 comment '消防水泵数';

alter table tbl_goods_house_device add column manufacturer varchar(100) NULL DEFAULT '' comment '生产厂家';
alter table tbl_goods_house_device add column velocity double NULL DEFAULT 0.00 comment '速度/功率';
alter table tbl_goods_house_device add column num int NULL DEFAULT 0 comment '数量';
alter table tbl_goods_house_device add column enable_time datetime NULL DEFAULT NULL comment '启用时间';
alter table tbl_goods_house_device add column maintenance_unit varchar(100) NULL DEFAULT '' comment '维保单位';
alter table tbl_goods_house_device add column telephone varchar(100) NULL DEFAULT '' comment '联系电话';
alter table tbl_goods_house_device add column remark varchar(1000) NULL DEFAULT '' comment '备注';

alter table tbl_goods_repository add column repos_category varchar(20) NULL default 'estate' comment '物库种类：动产、不动产';
