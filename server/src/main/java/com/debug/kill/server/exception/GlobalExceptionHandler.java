package com.debug.kill.server.exception;/**
 * Created by Administrator on 2021/3/28.
 */

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2021/3/28 10:12
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public String loginException(final Exception e){
        return "redirect:/to/login";
    }

}