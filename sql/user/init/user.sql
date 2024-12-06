create database smart_metrics_user;
use smart_metrics_user;

CREATE TABLE IF NOT EXISTS user
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    user_name     VARCHAR(256)                       NULL COMMENT '用户昵称',
    user_account  VARCHAR(256) unique                NOT NULL COMMENT '账号（用户名）',
    user_email    VARCHAR(256) unique                NULL COMMENT '邮箱',
    user_avatar   VARCHAR(1024)                      NULL COMMENT '用户头像',
    gender        TINYINT                            NULL COMMENT '性别 0男 1女 2未知',
    role_id       BIGINT                             NULL COMMENT '角色id',
    user_password VARCHAR(512)                       NOT NULL COMMENT '密码',
    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete     TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除'
) COMMENT '用户表';

CREATE TABLE IF NOT EXISTS user_role
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '角色ID',
    role_name   VARCHAR(256)                       NOT NULL UNIQUE COMMENT '角色名称',
    description VARCHAR(512)                       NULL COMMENT '角色描述',
    is_active   TINYINT  DEFAULT 1                 NOT NULL COMMENT '角色是否启用，1 为启用，0 为禁用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete   TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除'
) COMMENT '用户角色表';


CREATE TABLE IF NOT EXISTS permission
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '权限ID',
    permission_name VARCHAR(256)                       NOT NULL UNIQUE COMMENT '权限名称',
    description     VARCHAR(512)                       NULL COMMENT '权限描述',
    create_time     DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time     DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete       TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除'
) COMMENT '权限表';


CREATE TABLE IF NOT EXISTS role_permission
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '关联ID',
    role_id       BIGINT                             NOT NULL COMMENT '角色ID',
    permission_id BIGINT                             NOT NULL COMMENT '权限ID',
    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete     TINYINT  DEFAULT 0                 NOT NULL COMMENT '是否删除'
) COMMENT '角色权限关联表';
