package xyz.linyh.datasource;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author linzz
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
@EnableScheduling
public class DatasourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatasourceApplication.class, args);
    }
}
