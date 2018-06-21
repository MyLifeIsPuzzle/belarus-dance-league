package com.artsiomtretinnikov.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RatingAspect {

    private final Logger RATING_LOGGER = Logger.getLogger(this.getClass());

    @Pointcut("execution(public * com.artsiomtretinnikov.service.RatingService.*(..))*")
    public void ratingService() {}

    @After("ratingService()")
    public void afterMethodInvoke() {
        RATING_LOGGER.info("Rating's service method was invoked");
    }

}
