package xyz.linyh.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import xyz.linyh.common.enums.ErrorCodeEnum;
import xyz.linyh.common.enums.LoginTypeEnum;
import xyz.linyh.common.exception.BusinessException;
import xyz.linyh.common.response.BaseResponse;
import xyz.linyh.user.mapper.UserMapper;
import xyz.linyh.user.model.dto.LoginDTO;
import xyz.linyh.user.model.entity.User;
import xyz.linyh.user.service.UserService;

/**
 * @author linzz
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-12-06 20:57:39
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public String login(LoginDTO dto) {
        Integer loginType = dto.getLoginType();
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.fromCode(loginType);

        switch (loginTypeEnum) {
            case ACCOUNT -> accountLogin(dto);
            case EMAIL -> emailLogin(dto);
        }

//        生成token返回
        return "token";

    }

    @Override
    public BaseResponse<String> getMsg(String email) {
        return null;
    }

    private void emailLogin(LoginDTO dto) {
        String userEmail = dto.getUserEmail();
        if (StringUtils.isBlank(userEmail)) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "邮箱不能为空");
        }
    }

    private void accountLogin(LoginDTO dto) {
        String userAccount = dto.getUserAccount();
        String password = dto.getPassword();
        if (StringUtils.isAllBlank(userAccount, password)) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "账号或密码不能为空");
        }

        User user = lambdaQuery().eq(User::getUserAccount, userAccount)
                .eq(User::getUserPassword, password)
                .one();
        if (user == null) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "账号或密码错误!");
        }

    }
}




