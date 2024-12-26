package xyz.linyh.dubbo.service;

import java.util.List;
import java.util.Map;

/**
 * @author lin
 */
public interface DubboUserService {

    Map<Long,String> getUserNameByIds(List<Long> userIds);
}
