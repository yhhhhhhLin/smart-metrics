package xyz.linyh.datasource.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author linzz
 */

@Data
public class DscInfoVO {

    /**
     * 主键ID
     */
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
     * 数据源描述
     */
    private String dscDesc;

    /**
     * 创建人ID
     */
    private Long createdUserId;

    /**
     * 创建人name
     */
    private String createdUserName;

    /**
     * 最后更新人ID
     */
    private Long updatedBy;

    /**
     * 最后更新人name
     */
    private Long updatedUserName;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;
}
