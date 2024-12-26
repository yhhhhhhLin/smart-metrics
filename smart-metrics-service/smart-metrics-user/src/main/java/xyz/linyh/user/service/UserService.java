package xyz.linyh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.linyh.common.response.BaseResponse;
import xyz.linyh.user.model.dto.LoginDTO;
import xyz.linyh.user.model.dto.RegisterDTO;
import xyz.linyh.user.model.entity.User;
import xyz.linyh.user.model.vo.UserInfoVO;

import java.util.Map;

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
    Boolean getMsg(String email);

    /**
     * 用户注册
     * @param dto
     * @return
     */
    Boolean register(RegisterDTO dto);

    /**
     * 获取用户信息
     * @return
     */
    UserInfoVO getUserInfo(Long userId);

    /**
     * 获取所有用户id-用户名map
     * @return
     */
    Map<Long, String> listUserIdAndNameMap(String username);
}
