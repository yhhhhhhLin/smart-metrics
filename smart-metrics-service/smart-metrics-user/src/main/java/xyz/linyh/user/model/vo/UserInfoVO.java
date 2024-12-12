package xyz.linyh.user.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 *
 * TODO：后续添加角色？
 * @author linzz
 */
@Data
public class UserInfoVO {

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
}
