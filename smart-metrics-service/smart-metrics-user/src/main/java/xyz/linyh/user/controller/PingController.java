package xyz.linyh.user.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.linyh.user.utils.EmailUtils;

/**
 * @author linzz
 */
@RestController
@Slf4j
public class PingController {

    @Resource
    private EmailUtils emailService;

    @GetMapping("/ping")
    public void ping2() {
        log.info("ping2");
        System.out.println("wjhefioujhwoiejfiowejfoijewifwef");
        System.out.println("ping.....");
    }

    @GetMapping("/test")
    public void test(){
        emailService.sendEmail("1583427850@qq.com", "1","123");
    }
}
