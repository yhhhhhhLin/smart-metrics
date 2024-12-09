package xyz.linyh.maingateway.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import xyz.linyh.common.utils.JwtUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static xyz.linyh.common.constant.UserConstant.*;

/**
 * 用来判断用户是否登录的校验
 *
 * @author linzz
 */
@Component
@Slf4j
public class AuthorizeFilter implements Ordered, GlobalFilter {

    // 白名单路径
    private static final List<Pattern> WHITELIST = Arrays.asList(
            Pattern.compile("^/user/register$"),
            Pattern.compile("^/user/login$"),
            Pattern.compile("^/user/getMsg$")
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();

        String path = exchange.getRequest().getURI().getPath();
        log.info("有新请求，请求地址为：{}-----------------------------------------", path);

        // 如果是白名单路径，直接放行
        if (WHITELIST.stream().anyMatch(pattern -> pattern.matcher(path).matches())) {
            return chain.filter(exchange);
        }

        if (path != null && path.contains("gpt/connect")) {
            return chain.filter(exchange);
        }

        // 获取请求头中的 Token
        String token = exchange.getRequest().getHeaders().getFirst(USER_TOKEN);

        if (token == null || !JwtUtils.validateToken(token)) {
            log.warn("Token 校验失败");
            return unauthorizedResponse(response);
        }

        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.error("Token 解码失败", e);
            return unauthorizedResponse(response);
        }

        String userId = decodedJWT.getSubject();
        if (userId == null) {
            return unauthorizedResponse(response);
        }

        return chain.filter(exchange);
    }

    public Mono<Void> unauthorizedResponse(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    /**
     * 用来指定这个filter优先级，越小优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
