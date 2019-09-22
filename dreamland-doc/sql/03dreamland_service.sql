use dreamland_service;

create table t_advertisement
(
	title       nvarchar(50) null comment '标题',
    url       nvarchar(50) null comment '广告地址',
    img_path    nvarchar(50) null comment '图片路径',
    position    nvarchar(50) null comment '广告位置',
    adv_status  nvarchar(50) null comment '广告状态',
    id          nvarchar(50) not null comment '主键',
    create_time datetime     null comment '创建时间',
    update_time datetime     null comment '更新时间',
    create_by   varchar(10)  null comment '创建人',
    update_by   varchar(10)  null comment '更新人',
    constraint t_advertisement_id_uindex unique (id),
    primary key (id)
)
    comment '营销广告表';

create table t_article
(
    author      nvarchar(50)  null comment '作者',
    source      nvarchar(50)  null comment '来源',
    img_1_path  nvarchar(50)  null comment '图片1',
    img_2_path  nvarchar(50)  null comment '图片2',
    img_3_path  nvarchar(50)  null comment '图片3',
    content     nvarchar(50)  null comment '文章正文（富文本）',
    title       nvarchar(100) null comment '标题',
    id          nvarchar(50)  not null comment '主键',
    create_time datetime      null comment '创建时间',
    update_time datetime      null comment '更新时间',
    create_by   varchar(10)   null comment '创建人',
    update_by   varchar(10)   null comment '更新人',
    constraint t_article_id_uindex unique (id),
    primary key (id)
)
    comment '文章信息表';

create table t_after_sale
(
    user_id     nvarchar(50)  null comment '用户标识',
    p_user_id   nvarchar(50)  null comment '维修人员标识',
    goods_id    nvarchar(50)  null comment '商品标识',
    order_id    nvarchar(50)  null comment '订单标识',
    complaint   nvarchar(100) null comment '投诉原因',
    reply       nvarchar(100) null comment '问题解决',
    id          nvarchar(50)  not null comment '主键',
    create_time datetime      null comment '创建时间',
    update_time datetime      null comment '更新时间',
    create_by   varchar(10)   null comment '创建人',
    update_by   varchar(10)   null comment '更新人',
    constraint t_after_sale_id_uindex unique (id),
    primary key (id)
)
    comment '售后处理';

create table t_person_comment
(
    order_id    nvarchar(50) null comment '订单标识',
    p_user_id   nvarchar(50) null comment '维修人员标识',
    star_lv     nvarchar(50) null comment '星级评分（5）',
    remark      nvarchar(50) null comment '额外评价',
    user_id     nvarchar(50) null comment '用户标识',
    id          nvarchar(50) not null comment '主键',
    create_time datetime     null comment '创建时间',
    update_time datetime     null comment '更新时间',
    create_by   varchar(10)  null comment '创建人',
    update_by   varchar(10)  null comment '更新人',
    constraint t_person_comment_id_uindex unique (id),
    primary key (id)
)
    comment '维修人员评价表';

create table t_comment
(
    user_id     nvarchar(50)  null comment '用户标识',
    anonymous   nvarchar(50)  null comment '是否匿名',
    order_id    nvarchar(50)  null comment '订单标识',
    remark      nvarchar(100) null comment '用户评论',
    id          nvarchar(50)  not null comment '主键',
    create_time datetime      null comment '创建时间',
    update_time datetime      null comment '更新时间',
    create_by   varchar(10)   null comment '创建人',
    update_by   varchar(10)   null comment '更新人',
    constraint t_comment_id_uindex unique (id),
    primary key (id)
)
    comment '商品评论表';

create table t_payment
(
    pay_status     nvarchar(50) null comment '支付状态',
    order_id       nvarchar(50) null comment '订单标识',
    goods_id       nvarchar(50) null comment '商品标识',
    user_id        nvarchar(50) null comment '用户标识',
    p_user_id      nvarchar(50) null comment '维修人员标识',
    money          nvarchar(50) null comment '付款金额',
    payment_no     nvarchar(50) null comment '支付订单号',
    pay_type       nvarchar(50) null comment '支付类型',
    payment_seq_no nvarchar(50) null comment '支付流水号',
    id             nvarchar(50) not null comment '主键',
    create_time    datetime     null comment '创建时间',
    update_time    datetime     null comment '更新时间',
    create_by      varchar(10)  null comment '创建人',
    update_by      varchar(10)  null comment '更新人',
    constraint t_payment_id_uindex unique (id),
    primary key (id)
)
    comment '支付表';

create table t_repair_service_order
(
    user_name        nvarchar(50)  null comment '客户姓名',
    user_pay         nvarchar(50)  null comment '实际付款金额',
    position         nvarchar(50)  null comment '坐标位置',
    community_room   nvarchar(50)  null comment '小区室号',
    region           nvarchar(50)  null comment '省市区街道',
    reservation_date datetime      null comment '预约时间',
    community_name   nvarchar(50)  null comment '小区名称',
    total_price      nvarchar(50)  null comment '服务费用',
    repair_type      nvarchar(50)  null comment '维修类型',
    good_id          nvarchar(50)  null comment '商品id',
    title            nvarchar(50)  null comment '标题',
    order_no         nvarchar(50)  null comment '订单编号',
    status           nvarchar(50)  null comment '订单状态',
    user_id          nvarchar(50)  null comment '客户标识',
    remark           nvarchar(100) null comment '备注',
    id               nvarchar(50)  not null comment '主键',
    create_time      datetime      null comment '创建时间',
    update_time      datetime      null comment '更新时间',
    create_by        varchar(10)   null comment '创建人',
    update_by        varchar(10)   null comment '更新人',
    constraint t_repair_service_order_id_uindex unique (id),
    primary key (id)
)
    comment '维修服务表';
	
create table t_maintenance_service_order
(
    user_name        nvarchar(50)  null comment '客户姓名',
    user_pay         nvarchar(50)  null comment '实际付款金额',
    position         nvarchar(50)  null comment '坐标位置',
    community_room   nvarchar(50)  null comment '小区室号',
    region           nvarchar(50)  null comment '省市区街道',
    reservation_date datetime      null comment '预约时间',
    community_name   nvarchar(50)  null comment '小区名称',
    total_price      nvarchar(50)  null comment '服务费用',
    repair_type      nvarchar(50)  null comment '维修类型',
    good_id          nvarchar(50)  null comment '商品id',
    title            nvarchar(50)  null comment '标题',
    order_no         nvarchar(50)  null comment '订单编号',
    status           nvarchar(50)  null comment '订单状态',
    user_id          nvarchar(50)  null comment '客户标识',
    remark           nvarchar(100) null comment '备注',
    id               nvarchar(50)  not null comment '主键',
    create_time      datetime      null comment '创建时间',
    update_time      datetime      null comment '更新时间',
    create_by        varchar(10)   null comment '创建人',
    update_by        varchar(10)   null comment '更新人',
    constraint t_maintenance_service_order_id_uindex unique (id),
    primary key (id)
)
    comment '保养服务表';
	
create table t_design_service_order
(
    user_name        nvarchar(50)  null comment '客户姓名',
    user_pay         nvarchar(50)  null comment '实际付款金额',
    position         nvarchar(50)  null comment '坐标位置',
    community_room   nvarchar(50)  null comment '小区室号',
    region           nvarchar(50)  null comment '省市区街道',
    reservation_date datetime      null comment '预约时间',
    community_name   nvarchar(50)  null comment '小区名称',
    total_price      nvarchar(50)  null comment '服务费用',
    repair_type      nvarchar(50)  null comment '维修类型',
    good_id          nvarchar(50)  null comment '商品id',
    title            nvarchar(50)  null comment '标题',
    order_no         nvarchar(50)  null comment '订单编号',
    status           nvarchar(50)  null comment '订单状态',
    user_id          nvarchar(50)  null comment '客户标识',
    remark           nvarchar(100) null comment '备注',
    id               nvarchar(50)  not null comment '主键',
    create_time      datetime      null comment '创建时间',
    update_time      datetime      null comment '更新时间',
    create_by        varchar(10)   null comment '创建人',
    update_by        varchar(10)   null comment '更新人',
    constraint t_design_service_order_id_uindex unique (id),
    primary key (id)
)
    comment '设计服务表';
	
create table t_service_items
(
    top         nvarchar(50) null comment '主类编号',
    sub_no      nvarchar(50) null comment '分类编号',
    item_name   nvarchar(50) null comment '条目名称',
    top_name    nvarchar(50) null comment '主类名称',
    id          nvarchar(50) not null comment '主键',
    create_time datetime     null comment '创建时间',
    update_time datetime     null comment '更新时间',
    create_by   varchar(10)  null comment '创建人',
    update_by   varchar(10)  null comment '更新人',
    constraint t_service_items_id_uindex unique (id),
    primary key (id)
)
    comment '维修设计保养条目表';
	
alter table t_service_items
	add item_icon varchar(50) null comment '条目图标';
	