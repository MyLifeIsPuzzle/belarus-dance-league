package com.artsiomtretinnikov.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestAspect {

    private final Logger REQUEST_LOGGER = Logger.getLogger(this.getClass());

    @Pointcut("execution(public * com.artsiomtretinnikov.service.RequestService.*(..))*")
    public void requestService() {}

    @Around("requestService()")
    public void afterMethodInvoke() {
        REQUEST_LOGGER.info("Request's service method was invoked");
    }
}
