package com.artsiomtretinnikov.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ClubAspect {

    private final Logger CLUB_LOGGER = Logger.getLogger(this.getClass());

    @Pointcut("execution(public * com.artsiomtretinnikov.service.ClubService.*(..))*")
    public void clubService() {}

    @Before("clubService()")
    public void afterMethodInvoke() {
        CLUB_LOGGER.info("Club's service method was invoked");
    }
}
