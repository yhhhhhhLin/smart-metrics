package xyz.linyh.user.dubbo;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.linyh.dubbo.service.DubboUserService;
import xyz.linyh.user.mapper.UserMapper;
import xyz.linyh.user.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author linzz
 */
@DubboService
@Slf4j
public class DubboUserServiceImpl implements DubboUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Map<Long, String> getUserNameByIds(List<Long> userIds) {
        List<User> users = userMapper.selectBatchIds(userIds);
        return users
                .stream()
                .collect(Collectors.toMap(User::getId, User::getUserName));
    }
}
