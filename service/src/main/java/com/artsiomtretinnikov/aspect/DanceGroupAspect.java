package com.artsiomtretinnikov.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DanceGroupAspect {

    private final Logger DANCE_GROUP_LOGGER = Logger.getLogger(this.getClass());

    @Pointcut("execution(public * com.artsiomtretinnikov.service.DanceGroupService.*(..))*")
    public void danceGroupService() {}

    @After("danceGroupService()")
    public void afterMethodInvoke() {
        DANCE_GROUP_LOGGER.info("Dance group's service method was invoked");
    }
}
