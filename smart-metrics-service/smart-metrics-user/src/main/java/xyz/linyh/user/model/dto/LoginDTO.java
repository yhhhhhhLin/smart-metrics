package xyz.linyh.user.model.dto;

import lombok.Data;

/**
 * @author linzz
 */
@Data
public class LoginDTO {

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱登陆验证码
     */
    private Integer code;
}
