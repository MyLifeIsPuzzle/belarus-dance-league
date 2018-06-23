package com.artsiomtretinnikov.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final String DEFAULT_ERROR_VIEW = "error";

    private final Logger CLUB_LOGGER = Logger.getLogger(this.getClass());

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);

        CLUB_LOGGER.info(LocalDateTime.now(ZoneId.systemDefault()));
        CLUB_LOGGER.info(request.getRequestURL());
        CLUB_LOGGER.info(e);

        return mav;
    }
}
