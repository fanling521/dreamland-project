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

