package xyz.linyh.user.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.linyh.common.response.BaseResponse;
import xyz.linyh.common.response.ResultUtils;
import xyz.linyh.user.model.dto.LoginDTO;
import xyz.linyh.user.model.dto.RegisterDTO;
import xyz.linyh.user.service.UserService;

/**
 * @author linzz
 */
@RestController
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody LoginDTO dto) {
        String result = userService.login(dto);
        return ResultUtils.success(result);
    }

    @GetMapping("/getMsg")
    public BaseResponse<Boolean> getMsg(String email) {
        Boolean result = userService.getMsg(email);
        return ResultUtils.success(result);
    }

    @PostMapping("/register")
    public BaseResponse<Boolean> register(@RequestBody RegisterDTO dto) {
        Boolean result = userService.register(dto);
        return ResultUtils.success(result);

    }

}
