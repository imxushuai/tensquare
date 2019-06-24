package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if (request.getMethod().equals("OPTIONS")) {// 跳过预请求
            return null;
        }
        String url = request.getRequestURL().toString();
        if (url.indexOf("/admin/login") > 0) {// 放行登录
            log.info("登陆页面: [{}]", url);
            return null;
        }
        String authHeader = request.getHeader("Authorization");//获取头信息
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                if ("admin".equals(claims.get("roles"))) {
                    requestContext.addZuulRequestHeader("Authorization", authHeader);
                    log.info("Token验证通过，头信息 [{}]", authHeader);
                    return null;
                }
            }
        }
        requestContext.setSendZuulResponse(false);//终止运行
        requestContext.setResponseStatusCode(401);//http状态码
        requestContext.setResponseBody("无权访问");
        requestContext.getResponse().setContentType("text/html;charset=UTF‐8");
        return null;
    }
}
