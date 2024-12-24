package xyz.linyh.datasource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linzz
 */
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "dscAlertRuleCheckTaskExecutor")
    public ThreadPoolExecutor dscAlertRuleCheckTaskExecutor() {
        ThreadPoolExecutor service = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadFactory() {
            private AtomicInteger num = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "DSC_ALERT_RULE_CHECK_TASK_EXECUTOR" + num.getAndIncrement());
            }
        });
        return service;
    }

}
