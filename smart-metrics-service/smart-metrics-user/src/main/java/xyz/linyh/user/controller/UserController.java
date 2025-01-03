package xyz.linyh.user.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.linyh.common.context.UserIdContext;
import xyz.linyh.common.response.BaseResponse;
import xyz.linyh.common.response.ResultUtils;
import xyz.linyh.user.model.dto.LoginDTO;
import xyz.linyh.user.model.dto.RegisterDTO;
import xyz.linyh.user.model.vo.UserInfoVO;
import xyz.linyh.user.service.UserService;

import java.util.Enumeration;
import java.util.Map;

/**
 * @author linzz
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody LoginDTO dto) {
        String token = userService.login(dto);
        return ResultUtils.success(token);
    }

    @GetMapping("/getMsg")
    public BaseResponse<Boolean> getMsg(String email, HttpServletRequest request) {
        System.out.println("受到发送短信消息");
        Enumeration<String> headerNames = request.getHeaderNames();
        Boolean result = userService.getMsg(email);
        return ResultUtils.success(result);
    }

    @PostMapping("/register")
    public BaseResponse<Boolean> register(@RequestBody RegisterDTO dto) {
        Boolean result = userService.register(dto);
        return ResultUtils.success(result);

    }

    @GetMapping("/info")
    public BaseResponse<UserInfoVO> getUserInfo(){
        UserInfoVO userinfo = userService.getUserInfo(UserIdContext.getUserId());
        return ResultUtils.success(userinfo);

    }

    @GetMapping("/usernames")
    public BaseResponse<Map<Long,String>> listUserIdAndNameMap(String username){
        return ResultUtils.success(userService.listUserIdAndNameMap(username));
    }

}
