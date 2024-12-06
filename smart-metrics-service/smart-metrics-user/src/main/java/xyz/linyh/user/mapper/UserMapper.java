package xyz.linyh.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.linyh.user.model.entity.User;

/**
 * @author linzz
 * @description 针对表【user(用户表)】的数据库操作Mapper
 * @createDate 2024-12-06 20:57:39
 * @Entity generator.domain.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




