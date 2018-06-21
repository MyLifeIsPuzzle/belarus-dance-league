package com.artsiomtretinnikov.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DancerAspect {

    private final Logger DANCER_LOGGER = Logger.getLogger(this.getClass());

    @Pointcut("execution(public * com.artsiomtretinnikov.service.DancerService.*(..))*")
    public void dancerService() {}

    @After("dancerService()")
    public void afterMethodInvoke() {
        DANCER_LOGGER.info("Dancer's service method was invoked");
    }
}
