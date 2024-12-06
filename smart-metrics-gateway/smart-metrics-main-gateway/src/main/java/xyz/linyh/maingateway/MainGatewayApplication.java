package xyz.linyh.maingateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author linzz
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MainGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainGatewayApplication.class, args);
    }

}
