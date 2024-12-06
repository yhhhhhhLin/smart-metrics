package xyz.linyh.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linzz
 */
@RestController
public class PingController {

    @GetMapping("/ping")
    public void ping2() {
        System.out.println("ping.....");
    }
}
