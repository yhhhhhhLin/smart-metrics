package xyz.linyh.datasource.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linzz
 */
@RestController
@Slf4j
public class PingController {

    @RequestMapping("/ping")
    public void ping() {
        log.info("dsc ping....");

    }
}
