package com.artsiomtretinnikov.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CoachAspect {

    private final Logger COACH_LOGGER = Logger.getLogger(this.getClass());

    @Pointcut("execution(public * com.artsiomtretinnikov.service.CoachService.*(..))*")
    public void coachService() {}

    @After("coachService()")
    public void afterMethodInvoke() {
        COACH_LOGGER.info("Coach's service method was invoked");
    }
}
