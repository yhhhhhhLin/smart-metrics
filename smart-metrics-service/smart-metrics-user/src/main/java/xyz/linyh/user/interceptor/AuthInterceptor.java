package xyz.linyh.user.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.linyh.common.context.UserIdContext;
import xyz.linyh.common.utils.JwtUtils;

import static xyz.linyh.common.constant.UserConstant.*;

/**
 * @author linzz
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String stainHeader = request.getHeader(STAIN_HEADER);
        if (StringUtils.isBlank(stainHeader)) {
            return false;
        }


        String token = request.getHeader(USER_TOKEN);
        Long userId = Long.parseLong(JwtUtils.parseToken(token).getSubject());

        UserIdContext.setUserId(userId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
