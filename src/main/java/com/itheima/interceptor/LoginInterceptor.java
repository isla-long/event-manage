package com.itheima.interceptor;

import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.itheima.utils.JwtUtil;
import com.itheima.utils.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor  implements  HandlerInterceptor{


    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if(token == null){
            response.setStatus(401);
            return false;
        }
        try{
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            String res = valueOperations.get(token);
            if(res == null){
                throw new RuntimeException("token已过期");
            }
            Map<String,Object> claim = JwtUtil.parseToken(token);
            ThreadLocalUtil.set(claim);
        }catch(Exception e){
            e.printStackTrace();
            response.setStatus(401);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
