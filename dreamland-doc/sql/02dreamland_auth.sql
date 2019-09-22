use dreamland_auth;

create table t_app_user
(
    gender          nvarchar(50) null comment '性别',
    user_name       nvarchar(50) null comment '用户名',
    user_phone      nvarchar(50) null comment '手机号',
    password        nvarchar(50) null comment '密码',
    slat            nvarchar(50) null comment '盐',
    last_login_date nvarchar(50) null comment '上次登录时间',
    last_login_ip   nvarchar(50) null comment '上次登录ip',
    avatar          nvarchar(50) null comment '头像地址',
    id              nvarchar(50) not null comment '主键',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '更新时间',
    create_by       varchar(10)  null comment '创建人',
    update_by       varchar(10)  null comment '更新人',
    constraint t_app_user_id_uindex unique (id),
    primary key (id)
)
    comment 'APP用户表';

create table t_app_device_info
(
    phone_version nvarchar(50)  null comment '手机操作系统版本',
    phone_type    nvarchar(100) null comment '手机操作系统',
    user_phone    nvarchar(50)  null comment '手机号码',
    user_id       nvarchar(50)  null comment '用户标识',
    id            nvarchar(50)  not null comment '主键',
    create_time   datetime      null comment '创建时间',
    update_time   datetime      null comment '更新时间',
    create_by     varchar(10)   null comment '创建人',
    update_by     varchar(10)   null comment '更新人',
    constraint t_app_device_info_id_uindex unique (id),
    primary key (id)
)
    comment '用户移动设备信息表';

create table t_app_id_card
(
    user_id             nvarchar(50) null comment '用户标识',
    real_name           nvarchar(50) null comment '真实姓名',
    id_card_num         nvarchar(50) null comment '身份证号码',
    id_card_photo_path1 nvarchar(50) null comment '身份证正面图片路径',
    id_card_photo_path2 nvarchar(50) null comment '身份证反面图片路径',
    id_card_photo_path3 nvarchar(50) null comment '手持身份证',
    status              nvarchar(50) null comment '验证状态',
    id                  nvarchar(50) not null comment '主键',
    create_time         datetime     null comment '创建时间',
    update_time         datetime     null comment '更新时间',
    create_by           varchar(10)  null comment '创建人',
    update_by           varchar(10)  null comment '更新人',
    constraint t_app_id_card_user_id_uindex unique (user_id),
    constraint t_app_id_card_id_card_num_uindex unique (id_card_num),
    constraint t_app_id_card_id_uindex unique (id),
    primary key (id)
)
    comment '用户身份证表';

create table t_app_staff
(
    gender          nvarchar(50) null comment '性别',
    user_name       nvarchar(50) null comment '用户名',
    user_phone      nvarchar(50) null comment '手机号',
    password        nvarchar(50) null comment '密码',
    slat            nvarchar(50) null comment '盐',
    last_login_date nvarchar(50) null comment '上次登录时间',
    last_login_ip   nvarchar(50) null comment '上次登录ip',
    avatar          nvarchar(50) null comment '头像地址',
    id              nvarchar(50) not null comment '主键',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '更新时间',
    create_by       varchar(10)  null comment '创建人',
    update_by       varchar(10)  null comment '更新人',
    constraint t_app_user_id_uindex unique (id),
    primary key (id)
)
    comment '维修人员表';


create table t_app_manager
(
    gender          nvarchar(50) null comment '性别',
    user_name       nvarchar(50) null comment '用户名',
    user_phone      nvarchar(50) null comment '手机号',
    password        nvarchar(50) null comment '密码',
    slat            nvarchar(50) null comment '盐',
    last_login_date nvarchar(50) null comment '上次登录时间',
    last_login_ip   nvarchar(50) null comment '上次登录ip',
    avatar          nvarchar(50) null comment '头像地址',
    id              nvarchar(50) not null comment '主键',
    create_time     datetime     null comment '创建时间',
    update_time     datetime     null comment '更新时间',
    create_by       varchar(10)  null comment '创建人',
    update_by       varchar(10)  null comment '更新人',
    constraint t_app_user_id_uindex unique (id),
    primary key (id)
)
    comment '管理人员表';


