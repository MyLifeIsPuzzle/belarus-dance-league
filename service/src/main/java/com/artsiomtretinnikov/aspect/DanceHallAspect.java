package com.artsiomtretinnikov.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DanceHallAspect {

    private final Logger DANCE_HALL_LOGGER = Logger.getLogger(this.getClass());

    @Pointcut("execution(public * com.artsiomtretinnikov.service.DancingHallService.*(..))*")
    public void dancingHallService() {}

    @After("dancingHallService()")
    public void afterMethodInvoke() {
        DANCE_HALL_LOGGER.info("Dance hall's service method was invoked");
    }
}
