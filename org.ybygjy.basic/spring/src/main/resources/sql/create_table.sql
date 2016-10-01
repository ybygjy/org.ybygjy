create database db_order;
use db_order;
create table t_order (
	id bigint unsigned not null auto_increment primary key comment '主键',
	order_no varchar(20) not null unique  comment '订单编号',
	index using btree(order_no) ,
	order_amount decimal(10,2) not null default 0.0 comment '订单金额',
	order_flag tinyint not null default 10 comment '订单标识{10:正常}',
	buyer_id bigint not null default 0 comment '买家ID',
	index using btree(buyer_id),
	sales_id bigint not null default 0 comment '销售同学',
	index using btree (sales_id),
	send_time timestamp not null default '0000-00-00 00:00:00' comment '发货时间',
	order_mtime timestamp default current_timestamp on update current_timestamp,
	order_ctime timestamp not null default current_timestamp comment '创建时间',
	order_remark varchar(120) not null default '' comment '订单备注'
) engine=InnoDB default charset=utf8;
create table t_order_item (
	id bigint unsigned not null auto_increment primary key comment '主键',
	order_id bigint unsigned not null comment '订单ID',
	order_no varchar(20) not null comment '订单编号',
	quantity int not null default 0 comment '购买数量',
	unitprice decimal(10, 2) not null default 0 comment '标价',
	saleprice decimal(10, 2) not null default 0 comment '售价',
	goods_id bigint unsigned not null comment '商品ID',
	sku_id bigint unsigned not null comment 'skuID',
	goods_name varchar(120) not null default '' comment '商品名称',
	mtime timestamp default current_timestamp on update current_timestamp comment '修改时间',
	ctime timestamp default current_timestamp comment '创建时间',
	index using btree (order_no),
	index using btree (goods_id),
	index using btree (sku_id),
	index using btree (goods_name)
) engine=InnoDB default charset=utf8;
create table t_goods(
	goods_id bigint unsigned not null auto_increment primary key comment '商品编码',
	goods_name varchar(120) not null default '' comment '商品名称',
	unitprice decimal(10, 2) not null default 0 comment '标价',
	saleprice decimal(10, 2) not null default 0 comment '售价',
	state_flag int unsigned default 10 comment '状态{10:正常;20:下架}',
	mtime timestamp default current_timestamp on update current_timestamp comment '修改时间',
	ctime timestamp default current_timestamp comment '创建时间'
) engine=InnoDB default charset=utf8;
create table t_customer (
	id bigint unsigned not null auto_increment primary key comment '主键',
	name varchar(80) not null comment '客户名称',
	phone varchar(20) not null default '' comment '联系电话',
	qqno varchar(20) not null default '' comment '客户QQ号',
	wechart varchar(20) not null default '' comment '客户微信',
	state_flag int unsigned default 10 comment '状态{10:正常;20:其它}',
	address varchar(120) not null default '' comment '客户地址',
	remark varchar(120) not null default '' comment '用户备注',
	mtime timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	ctime timestamp not null default CURRENT_TIMESTAMP,
	index using btree (name),
	index using btree (phone),
	index using btree (qqno),
	index using btree (wechart),
	index using btree (address)
) engine=InnoDB default charset=utf8;
create table t_sys_user (
	id bigint unsigned not null auto_increment primary key comment '主键',
	user_no varchar(80) not null comment '雇员工号',
	user_name varchar(80) not null comment '雇员名称',
	user_role varchar(80) not null comment '权限标识',
	state_flag int unsigned default 10 comment '状态{10:正常;20:其它}',
	mtime timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	ctime timestamp not null default CURRENT_TIMESTAMP
) engine=InnoDB default charset=utf8;

insert into t_sys_user(user_no, user_name, user_role, state_flag) values('10001', '管理员', 'SYS_MGR',10);

create user tx_writer identified by password 'W#1^iPowSQPsd' password expire never account unlock;
grant ALL on db_order.* to 'tx_writer'@'localhost';