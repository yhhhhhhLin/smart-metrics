create database smart_metrics_datasource;
use smart_metrics_datasource;

CREATE TABLE dsc_info
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    dsc_name          VARCHAR(100) NOT NULL COMMENT '数据源名称',
    dsc_type          TINYINT      NOT NULL COMMENT '数据源类型(1:mysql 2:redis ....',
    url               VARCHAR(500) NOT NULL COMMENT '数据源连接URL',
    username          VARCHAR(100) COMMENT '用户名',
    password          VARCHAR(256) COMMENT '密码（加密存储）',
    database_name     VARCHAR(100) COMMENT '默认数据库名称',
    dsc_desc          VARCHAR(255) COMMENT '数据源描述',
    connection_params JSON COMMENT '其他连接参数（如超时设置）',
    dsc_status        TINYINT COMMENT '数据源状态（0不可用 1可用）',
    created_user_id   BIGINT       NOT NULL COMMENT '创建人ID',
    updated_by        BIGINT COMMENT '最后更新人ID',
    created_time      DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time      DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_deleted        TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志（0-未删除，1-已删除）'
) COMMENT ='数据源表';

CREATE TABLE dsc_access_log
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    dsc_id           BIGINT  NOT NULL COMMENT '数据源ID',
    access_time      DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '访问时间',
    accessed_user_id BIGINT  NOT NULL COMMENT '访问人ID',
    query            TEXT COMMENT '访问查询内容（如SQL语句）',
    result_status    TINYINT NOT NULL COMMENT '访问结果状态 0失败 1成功',
    error_message    TEXT COMMENT '错误信息'
) COMMENT ='数据源访问日志表';

CREATE TABLE dsc_alert_rule
(
    id                     BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    dsc_id                 BIGINT       NOT NULL COMMENT '数据源ID',
    rule_name              VARCHAR(100) NOT NULL COMMENT '规则名称',
    rule_type              TINYINT      NOT NULL COMMENT '告警规则类型 1：每日首次发现连接失败 2：每次发现连接失败',
    is_enabled             TINYINT(1) DEFAULT 1 COMMENT '是否启用告警（0:禁用，1:启用）',
    notify_channel         TINYINT      NOT NULL COMMENT '通知方式 1: 短信 2：邮箱 ....',
    notify_recipients_type TINYINT      NOT NULL COMMENT '告警通知人方式（1：告警创建人，2：数据源创建人，3：其他）',
    alert_time             varchar(255) NULL COMMENT '最后告警时间，格式：年月日',
    notify_recipients      BIGINT       NOT NULL COMMENT '通知接收者id',
    created_user_id        BIGINT       NOT NULL COMMENT '创建人ID',
    updated_user_id        BIGINT COMMENT '最后更新人ID',
    created_time           DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time           DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_deleted             TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志（0-未删除，1-已删除）'
) COMMENT ='数据源告警规则表';

CREATE TABLE dsc_alert_log
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    alert_rule_id     BIGINT NOT NULL COMMENT '告警规则ID',
    dsc_id            BIGINT NOT NULL COMMENT '数据源ID',
    alert_time        DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '告警触发时间',
    alert_status      TINYINT COMMENT '告警状态 1：等待 2：通知发送 3：解决',
    error_message     TEXT COMMENT '告警错误信息描述',
    notify_channel    tinyint COMMENT '通知方式 1: 短信 2：邮箱 ....',
    notify_recipients BIGINT COMMENT '通知接收者id',
    resolved_at       DATETIME COMMENT '告警解决时间',
    resolved_user_id  BIGINT COMMENT '解决人ID',
    created_time      DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time      DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_deleted        TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标志（0-未删除，1-已删除）'
) COMMENT ='数据源告警日志表';
