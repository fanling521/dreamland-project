use dreamland_service;

create table t_advertisement
(
width nvarchar(50)  null comment '宽度',
height nvarchar(50)  null comment '高度',
img_path nvarchar(50)  null comment '图片路径',
position nvarchar(50)  null comment '广告位置',
adv_status nvarchar(50)  null comment '广告状态',
id nvarchar(50) not null comment '主键',
create_time datetime null comment '创建时间',
update_time datetime null comment '更新时间',
create_by varchar(10) null comment '创建人',
update_by varchar(10) null comment '更新人',
constraint t_advertisement_id_uindex unique (id),
primary key (id)
)
comment '营销广告表';

create table t_article
(
author nvarchar(50)  null comment '作者',
source nvarchar(50)  null comment '来源',
img_1_path nvarchar(50)  null comment '图片1',
img_2_path nvarchar(50)  null comment '图片2',
ing_3_path nvarchar(50)  null comment '图片3',
content nvarchar(50)  null comment '文章正文（富文本）',
title nvarchar(100)  null comment '标题',
id nvarchar(50) not null comment '主键',
create_time datetime null comment '创建时间',
update_time datetime null comment '更新时间',
create_by varchar(10) null comment '创建人',
update_by varchar(10) null comment '更新人',
constraint t_article_id_uindex unique (id),
primary key (id)
)
comment '文章信息表';

create table t_after_sale
(
user_id nvarchar(50)  null comment '用户标识',
p_user_id nvarchar(50)  null comment '维修人员标识',
goods_id nvarchar(50)  null comment '商品标识',
order_id nvarchar(50)  null comment '订单标识',
complaint nvarchar(100)  null comment '投诉原因',
reply nvarchar(100)  null comment '问题解决',
id nvarchar(50) not null comment '主键',
create_time datetime null comment '创建时间',
update_time datetime null comment '更新时间',
create_by varchar(10) null comment '创建人',
update_by varchar(10) null comment '更新人',
constraint t_after_sale_id_uindex unique (id),
primary key (id)
)
comment '售后处理';

create table t_person_comment
(
order_id nvarchar(50)  null comment '订单标识',
p_user_id nvarchar(50)  null comment '维修人员标识',
star_lv nvarchar(50)  null comment '星级评分（5）',
remark nvarchar(50)  null comment '额外评价',
user_id nvarchar(50)  null comment '用户标识',
id nvarchar(50) not null comment '主键',
create_time datetime null comment '创建时间',
update_time datetime null comment '更新时间',
create_by varchar(10) null comment '创建人',
update_by varchar(10) null comment '更新人',
constraint t_person_comment_id_uindex unique (id),
primary key (id)
)
comment '维修人员评价表';

create table t_comment
(
user_id nvarchar(50)  null comment '用户标识',
anonymous nvarchar(50)  null comment '是否匿名',
order_id nvarchar(50)  null comment '订单标识',
remark nvarchar(100)  null comment '用户评论',
id nvarchar(50) not null comment '主键',
create_time datetime null comment '创建时间',
update_time datetime null comment '更新时间',
create_by varchar(10) null comment '创建人',
update_by varchar(10) null comment '更新人',
constraint t_comment_id_uindex unique (id),
primary key (id)
)
comment '商品评论表';

create table t_reservation
(
position nvarchar(50)  null comment '坐标位置',
room_info nvarchar(50)  null comment '小区室号',
area nvarchar(50)  null comment '省市区街道',
reservation_date nvarchar(50)  null comment '预约时间',
community_name nvarchar(50)  null comment '小区名称',
price nvarchar(50)  null comment '服务费用',
reservation_type nvarchar(50)  null comment '预约标记',
goods_id nvarchar(50)  null comment '(服务项目)商品id',
id nvarchar(50) not null comment '主键',
create_time datetime null comment '创建时间',
update_time datetime null comment '更新时间',
create_by varchar(10) null comment '创建人',
update_by varchar(10) null comment '更新人',
constraint t_reservation_id_uindex unique (id),
primary key (id)
)
comment '服务订购/预约表';

create table t_payment
(
pay_status nvarchar(50)  null comment '支付状态',
order_id nvarchar(50)  null comment '订单标识',
goods_id nvarchar(50)  null comment '商品标识',
user_id nvarchar(50)  null comment '用户标识',
p_user_id nvarchar(50)  null comment '维修人员标识',
money nvarchar(50)  null comment '付款金额',
payment_no nvarchar(50)  null comment '支付订单号',
pay_type nvarchar(50)  null comment '支付类型',
payment_seq_no nvarchar(50)  null comment '支付流水号',
id nvarchar(50) not null comment '主键',
create_time datetime null comment '创建时间',
update_time datetime null comment '更新时间',
create_by varchar(10) null comment '创建人',
update_by varchar(10) null comment '更新人',
constraint t_payment_id_uindex unique (id),
primary key (id)
)
comment '支付表';