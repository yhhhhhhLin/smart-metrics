package xyz.linyh.user.utils;

import jakarta.annotation.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author linzz
 */
@Service
public class EmailUtils {

    @Resource
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("a158342785@163.com");

        javaMailSender.send(message);
    }

    public void sendLoginEmail(String to, Integer code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("a158342785@163.com");
        message.setTo(to);
        message.setSubject("登陆验证码");
        message.setText("验证码为："+code);

        javaMailSender.send(message);

    }
}
