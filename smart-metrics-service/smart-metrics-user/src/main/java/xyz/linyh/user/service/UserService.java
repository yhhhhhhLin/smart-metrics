package xyz.linyh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.linyh.common.response.BaseResponse;
import xyz.linyh.user.model.dto.LoginDTO;
import xyz.linyh.user.model.entity.User;

/**
* @author linzz
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-12-06 20:57:39
*/
public interface UserService extends IService<User> {

    /**
     * 登陆
     * @param dto
     * @return
     */
    String login(LoginDTO dto);

    /**
     * 根据邮箱发送验证码
     * @param email
     * @return
     */
    BaseResponse<String> getMsg(String email);
}
