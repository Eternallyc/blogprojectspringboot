package com.eternallyc.blogproject.interceptors;

import com.alibaba.fastjson.JSONObject;
import com.eternallyc.blogproject.bean.ResultMessage;
import com.eternallyc.blogproject.util.Const;
import com.eternallyc.blogproject.util.TokenHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
        // System.out.println("postHandle---");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        response.setCharacterEncoding("utf-8");
        // Get authorization from Http request
        final String authHeader = request.getHeader(Const.JWT_HEADER);

        //print the authorization to console for debug
        /*  System.out.println("auth = "+authHeader);*/

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            responseMessage(response, response.getWriter(), new ResultMessage(false, "您没有登录"));
          /*  System.out.println("new Result(false, \"空值\")");*/
            return false;
        }

        // Then get the JWT token from authorization
        final String token = authHeader.substring(7);
        try {
            final Claims claims = Jwts.parser().setSigningKey(Const.JWT_SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            responseMessage(response, response.getWriter(), new ResultMessage(false, "您没有登录"));
            /*System.out.println("new Result(false, \"JWT错误2\")");*/
            return false;

        }

        TokenHelper tokenHelper = new TokenHelper(authHeader);
        if(tokenHelper.tokenExpIsFailed()) {
            responseMessage(response, response.getWriter(), new ResultMessage(false, "您没有登录"));
            /*System.out.println("new Result(false, \"JWT过期\")");*/
            return false;
        }
        return true;
    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResultMessage res) {
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSONString(res);
        out.print(json);
        out.flush();
        out.close();
    }
}
