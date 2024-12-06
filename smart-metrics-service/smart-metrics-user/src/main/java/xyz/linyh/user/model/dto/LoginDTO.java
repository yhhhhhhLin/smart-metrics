package xyz.linyh.user.model.dto;

import lombok.Data;

/**
 * @author linzz
 */
@Data
public class LoginDTO {

    /**
     * 登陆类型 0：账号密码 1：邮箱
     */
    private Integer loginType;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String password;

    /**
     * 登陆邮箱
     */
    private String userEmail;

    /**
     * 邮箱登陆验证码
     */
    private Integer code;
}
