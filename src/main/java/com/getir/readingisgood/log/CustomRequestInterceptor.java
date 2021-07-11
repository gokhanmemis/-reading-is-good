package com.getir.readingisgood.log;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.Enumeration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class CustomRequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        long startTime = Instant.now().toEpochMilli();
        request.setAttribute("startTime", startTime);

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

        long startTime = (Long) request.getAttribute("startTime");

        LogDTO logDTO = new LogDTO();
        if(request.getUserPrincipal() != null)
            logDTO.userName = request.getUserPrincipal().getName();

        logDTO.path = request.getRequestURI();

        if(handler instanceof HandlerMethod) {
            logDTO.methodName = ((HandlerMethod) handler).getMethod().getName();
            logDTO.className = ((HandlerMethod) handler).getMethod().getDeclaringClass().getName();
        }
        else
        {
            logDTO.methodName ="Not Defined";
            logDTO.className ="Not Defined";
        }

        logDTO.message= request.getMethod() + " " + request.getRequestURI();
        logDTO.logType = LogTypeEnum.INFO;
        logDTO.clientIp = request.getHeader("client-ip");
        logDTO.clientName = request.getHeader("clientName");
        logDTO.clientType = request.getHeader("clientType");
        logDTO.clientDetail = request.getHeader("clientDetail");
        logDTO.lokasyon = request.getHeader("Location");

        try {
            logDTO.hostIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
        }

        logDTO.responseTime = Instant.now().toEpochMilli() - startTime;

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> LogUtil.log(logDTO));
    }

}
