package com.example.projectliboo.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DdosInterceptor implements HandlerInterceptor {

    private static final int MAX_REQUESTS_PER_SECOND = 10;
    private final Map<String, Long> ipToRequestCountMap = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String ipAddress = request.getRemoteAddr();
        Long requestCount = ipToRequestCountMap.getOrDefault(ipAddress, 0L);
        if (requestCount >= MAX_REQUESTS_PER_SECOND) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            return false;
        } else {
            ipToRequestCountMap.put(ipAddress, requestCount + 1);
            return true;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        String ipAddress = request.getRemoteAddr();
        Long requestCount = ipToRequestCountMap.getOrDefault(ipAddress, 0L);
        if (requestCount > 0) {
            ipToRequestCountMap.put(ipAddress, requestCount - 1);
        }
    }

}
