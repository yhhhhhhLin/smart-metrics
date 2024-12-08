package xyz.linyh.datasource.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据源表
 *
 * @author linzz
 * @TableName dsc_info
 */
@TableName(value = "dsc_info")
@Data
public class DscInfo implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 数据源名称
     */
    private String dscName;

    /**
     * 数据源类型(1:mysql 2:redis ....
     */
    private Integer dscType;

    /**
     * 数据源连接URL
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码（加密存储）
     */
    private String password;

    /**
     * 默认数据库名称
     */
    private String databaseName;

    /**
     * 其他连接参数（如超时设置）
     */
    private Object connectionParams;

    /**
     * 数据源状态（0不可用 1可用）
     */
    private Integer dscStatus;

    /**
     * 创建人ID
     */
    private Long createdUserId;

    /**
     * 最后更新人ID
     */
    private Long updatedBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 逻辑删除标志（0-未删除，1-已删除）
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}