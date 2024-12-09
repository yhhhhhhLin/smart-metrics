package xyz.linyh.user.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import xyz.linyh.common.enums.ErrorCodeEnum;
import xyz.linyh.common.enums.RegisterTypeEnum;
import xyz.linyh.common.exception.BusinessException;
import xyz.linyh.common.utils.JwtUtils;
import xyz.linyh.user.mapper.UserMapper;
import xyz.linyh.user.model.dto.LoginDTO;
import xyz.linyh.user.model.dto.RegisterDTO;
import xyz.linyh.user.model.entity.User;
import xyz.linyh.user.service.UserService;
import xyz.linyh.user.utils.EmailUtils;
import xyz.linyh.user.utils.RedisUtil;

import java.util.Objects;

/**
 * @description 用户Service实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private EmailUtils emailUtils;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String LOGIN_EMAIL_MSG_ = "email_msg_phone:";
    private static final Integer LOGIN_MSG_EXPIRE_TIME = 60;
    private static final String PASSWORD_SALT = "yh@!%#G";

    @Override
    public String login(LoginDTO dto) {
        String userAccount = dto.getUserAccount();
        String password = dto.getPassword();
        String encryptPassword = DigestUtils.md5DigestAsHex((PASSWORD_SALT + password).getBytes());
        validateAccountAndPassword(userAccount, password);
        User user = lambdaQuery().eq(User::getUserAccount, userAccount)
                .eq(User::getUserPassword, encryptPassword).one();
        if(user == null) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "账号或密码错误");
        }

         return JwtUtils.generateToken(user.getId().toString());
    }

    @Override
    public Boolean getMsg(String email) {
        Integer randomCode = RandomUtil.randomInt(10000, 99999);
        emailUtils.sendLoginEmail(email, randomCode);
        redisUtil.set(LOGIN_EMAIL_MSG_ + email, randomCode, LOGIN_MSG_EXPIRE_TIME);
        return true;
    }

    @Override
    public Boolean register(RegisterDTO dto) {
        RegisterTypeEnum registerTypeEnum = RegisterTypeEnum.fromCode(dto.getRegisterType());

        switch (registerTypeEnum) {
            case ACCOUNT:
                accountRegister(dto);
                break;
            case EMAIL:
                emailRegister(dto);
                break;
            default:
                throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "不支持的注册方式");
        }
        return true;
    }

    private void accountRegister(RegisterDTO dto) {
        String userAccount = dto.getUserAccount();
        String password = dto.getPassword();
        String checkPassword = dto.getCheckPassword();

        validateAccountAndPassword(userAccount, password);
        if(!password.equals(checkPassword)) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR,"密码不一致");
        }

        if (isAccountExists(userAccount)) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "账号已经存在");
        }

        createDefaultUser(dto.getUsername(), userAccount, password, RegisterTypeEnum.ACCOUNT);
    }

    private void emailRegister(RegisterDTO dto) {
        String userEmail = dto.getEmail();
        String password = dto.getPassword();
        String checkPassword = dto.getCheckPassword();

        validateAccountAndPassword(userEmail, password);
        if(!password.equals(checkPassword)) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR,"密码不一致");
        }

        if (isEmailExists(userEmail)) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "该邮箱已注册");
        }

        validateEmailCode(userEmail, dto.getCode());

        createDefaultUser(dto.getUsername(), userEmail, password, RegisterTypeEnum.EMAIL);
    }

    private void validateAccountAndPassword(String account, String password) {
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "账号或密码不能为空");
        }
    }

    private boolean isAccountExists(String account) {
        User user = lambdaQuery().eq(User::getUserAccount, account).one();
        return user != null;
    }

    private boolean isEmailExists(String email) {
        User user = lambdaQuery().eq(User::getUserEmail, email).one();
        return user != null;
    }

    private void validateEmailCode(String email, Integer code) {
        Object storedCode = redisUtil.get(LOGIN_EMAIL_MSG_ + email);
        if (storedCode == null) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "未发送验证码或验证码已过期");
        }
        if (!Objects.equals(storedCode.toString(), code.toString())) {
            throw new BusinessException(ErrorCodeEnum.PARAMS_ERROR, "邮箱验证码错误");
        }
    }

    private void createDefaultUser(String username, String accountOrEmail, String password, RegisterTypeEnum registerTypeEnum) {
        User user = new User();
        user.setUserName(username);
        String encryptPassword = DigestUtils.md5DigestAsHex((PASSWORD_SALT + password).getBytes());
        user.setUserPassword(encryptPassword);

        if (registerTypeEnum == RegisterTypeEnum.ACCOUNT) {
            user.setUserAccount(accountOrEmail);
        } else if (registerTypeEnum == RegisterTypeEnum.EMAIL) {
            user.setUserAccount(accountOrEmail);
            user.setUserEmail(accountOrEmail);
        }

        this.save(user);
    }
}
