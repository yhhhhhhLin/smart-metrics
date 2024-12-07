package xyz.linyh.user.model.dto;

import lombok.Data;

/**
 * @author linzz
 */
@Data
public class RegisterDTO {
    private String username;

    private String userAccount;

    private String password;

    private Integer registerType;

    private String email;

    /**
     * 邮箱注册验证码
     */
    private Integer code;
}
