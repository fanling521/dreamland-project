use dreamland_attachment;

DROP TABLE IF EXISTS t_file_attachment;
create table t_file_attachment
(
    uid            nvarchar(50)  null comment '用户标识',
    file_extension nvarchar(50)  null comment '文件类型',
    file_size      nvarchar(50)  null comment '文件大小',
    file_name      nvarchar(100) null comment '文件名称',
    file_path      nvarchar(200) null comment '文件路径',
    id             nvarchar(50)  not null comment '主键',
    create_time    datetime      null comment '创建时间',
    update_time    datetime      null comment '更新时间',
    create_by      varchar(10)   null comment '创建人',
    update_by      varchar(10)   null comment '更新人',
    constraint t_file_attachment_id_uindex unique (id),
    primary key (id)
)
    comment '图片附件表';