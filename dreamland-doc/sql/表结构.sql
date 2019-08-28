create table sys_menu
(
    menu_id     varchar(50)              not null comment '菜单ID'
        primary key,
    menu_name   varchar(50)              not null comment '菜单名称',
    parent_id   varchar(50)  default '0' null comment '父菜单ID',
    parent_name varchar(50)  default '0' null comment '父菜单名称',
    order_num   int(4)       default 0   null comment '显示顺序',
    url         varchar(200) default '#' null comment '请求地址',
    menu_type   char         default ''  null comment '菜单类型（M目录 C菜单 F按钮）',
    visible     char         default '0' null comment '菜单状态（0显示 1隐藏）',
    perms       varchar(100)             null comment '权限标识',
    create_by   varchar(64)  default ''  null comment '创建者',
    create_time datetime                 null comment '创建时间',
    update_by   varchar(64)  default ''  null comment '更新者',
    update_time datetime                 null comment '更新时间'
)
    comment '菜单权限表';

create table sys_role
(
    role_id     varchar(50)            not null comment '角色ID'
        primary key,
    role_name   varchar(30)            not null comment '角色名称',
    role_key    varchar(100)           not null comment '角色权限字符串',
    role_sort   int(4)                 not null comment '显示顺序',
    status      char                   not null comment '角色状态（0正常 1停用）',
    create_by   varchar(64) default '' null comment '创建者',
    create_time datetime               null comment '创建时间',
    update_by   varchar(64) default '' null comment '更新者',
    update_time datetime               null comment '更新时间'
)
    comment '角色信息表';

create table sys_role_menu
(
    role_id varchar(50) not null comment '角色ID',
    menu_id varchar(50) not null comment '菜单ID',
    primary key (role_id, menu_id)
)
    comment '角色和菜单关联表';

create table sys_user
(
    user_id     varchar(50)             not null comment '用户ID'
        primary key,
    login_name  varchar(30)             not null comment '登录账号',
    user_name   varchar(30)             not null comment '用户昵称',
    phone       varchar(11) default ''  null comment '手机号码',
    password    varchar(50) default ''  null comment '密码',
    salt        varchar(50) default ''  null comment '盐加密',
    status      char        default '0' null comment '帐号状态（0正常 1停用）',
    login_ip    varchar(50) default ''  null comment '最后登陆IP',
    login_date  datetime                null comment '最后登陆时间',
    create_by   varchar(64) default ''  null comment '创建者',
    create_time datetime                null comment '创建时间',
    update_by   varchar(64) default ''  null comment '更新者',
    update_time datetime                null comment '更新时间'
)
    comment '用户信息表';

create table sys_user_role
(
    user_id varchar(50) not null comment '用户ID',
    role_id varchar(50) not null comment '角色ID',
    primary key (user_id, role_id)
)
    comment '用户和角色关联表';

create table t_app_device_info
(
    IMEI        varchar(100) null comment '国际移动设备识别码',
    phone_os    varchar(100) null comment '手机操作系统',
    user_phone  varchar(50)  null comment '手机号码',
    user_id     varchar(50)  null comment '用户标识',
    id          varchar(50)  not null comment '主键',
    create_time datetime     null comment '创建时间',
    update_time datetime     null comment '更新时间',
    create_by   varchar(10)  null comment '创建人',
    update_by   varchar(10)  null comment '更新人',
    constraint t_app_device_info_id_uindex
        unique (id)
)
    comment '用户移动设备信息表';

alter table t_app_device_info
    add primary key (id);

create table t_app_role
(
    role_name   varchar(50) null comment '角色名称',
    role_desc   varchar(50) null comment '角色描述',
    id          varchar(50) not null comment '主键',
    create_time datetime    null comment '创建时间',
    update_time datetime    null comment '更新时间',
    create_by   varchar(10) null comment '创建人',
    update_by   varchar(10) null comment '更新人',
    constraint t_app_role_id_uindex
        unique (id),
    constraint t_app_role_role_name_uindex
        unique (role_name)
)
    comment 'APP角色表';

alter table t_app_role
    add primary key (id);

create table t_app_user
(
    sex             varchar(50) null comment '性别',
    user_name       varchar(50) null comment '用户名',
    user_phone      varchar(50) null comment '手机号',
    password        varchar(50) null comment '密码',
    slat            varchar(50) null comment '盐',
    real_name       varchar(50) null comment '真实姓名',
    email           varchar(50) null comment '邮箱地址',
    role_key        varchar(50) null comment '角色标识',
    last_login_date datetime    null comment '上次登录时间',
    last_login_ip   varchar(50) null comment '上次登录ip',
    id              varchar(50) not null comment '主键',
    create_time     datetime    null comment '创建时间',
    update_time     datetime    null comment '更新时间',
    create_by       varchar(10) null comment '创建人',
    update_by       varchar(10) null comment '更新人',
    constraint t_app_user_id_uindex
        unique (id),
    constraint t_app_user_user_phone_role_key_uindex
        unique (user_phone, role_key)
)
    comment 'APP用户表';

alter table t_app_user
    add primary key (id);


create table t_top_category
(
top_goods_name nvarchar(50)  null comment '商品名称',
top_goods_desc nvarchar(50)  null comment '商品描述',
top_goods_status nvarchar(50)  null comment '商品分类状态',
top_goods_icon nvarchar(50)  null comment '商品图标',
id nvarchar(50) not null comment '主键',
create_time datetime null comment '创建时间',
update_time datetime null comment '更新时间',
create_by varchar(10) null comment '创建人',
update_by varchar(10) null comment '更新人',
constraint t_top_category_top_goods_name_uindex unique (top_goods_name),
constraint t_top_category_id_uindex unique (id),
primary key (id)
)
comment '商品一级分类';