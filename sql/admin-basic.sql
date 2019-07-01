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
  role_id     varchar(50)             not null comment '角色ID'
    primary key,
  role_name   varchar(30)             not null comment '角色名称',
  role_key    varchar(100)            not null comment '角色权限字符串',
  role_sort   int(4)                  not null comment '显示顺序',
  status      char                    not null comment '角色状态（0正常 1停用）',
  del_flag    char        default '0' null comment '删除标志（0代表存在 2代表删除）',
  create_by   varchar(64) default ''  null comment '创建者',
  create_time datetime                null comment '创建时间',
  update_by   varchar(64) default ''  null comment '更新者',
  update_time datetime                null comment '更新时间'
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
  user_id     varchar(50)              not null comment '用户ID'
    primary key,
  login_name  varchar(30)              not null comment '登录账号',
  user_name   varchar(30)              not null comment '用户昵称',
  email       varchar(50)  default ''  null comment '用户邮箱',
  phone       varchar(11)  default ''  null comment '手机号码',
  sex         char         default '0' null comment '用户性别（0男 1女 2未知）',
  avatar      varchar(100) default ''  null comment '头像路径',
  password    varchar(50)  default ''  null comment '密码',
  salt        varchar(50)  default ''  null comment '盐加密',
  status      char         default '0' null comment '帐号状态（0正常 1停用）',
  del_flag    char         default '0' null comment '删除标志（0代表存在 2代表删除）',
  login_ip    varchar(50)  default ''  null comment '最后登陆IP',
  login_date  datetime                 null comment '最后登陆时间',
  create_by   varchar(64)  default ''  null comment '创建者',
  create_time datetime                 null comment '创建时间',
  update_by   varchar(64)  default ''  null comment '更新者',
  update_time datetime                 null comment '更新时间'
)
  comment '用户信息表';

create table sys_user_role
(
  user_id varchar(50) not null comment '用户ID',
  role_id varchar(50) not null comment '角色ID',
  primary key (user_id, role_id)
)
  comment '用户和角色关联表';


