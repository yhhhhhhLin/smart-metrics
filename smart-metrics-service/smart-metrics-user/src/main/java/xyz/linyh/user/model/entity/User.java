package xyz.linyh.user.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @author linzz
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 账号（用户名）
     */
    private String userAccount;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 性别 0男 1女 2未知
     */
    private Integer gender;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}