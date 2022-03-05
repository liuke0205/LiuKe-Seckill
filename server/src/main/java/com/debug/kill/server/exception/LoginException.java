package com.debug.kill.server.exception;/**
 * Created by Administrator on 2021/3/28.
 */

/**
 * @Author:debug (SteadyJack)
 * @Link: weixin-> debug0868 qq-> 1948831260
 * @Date: 2021/3/28 10:12
 **/
public class LoginException extends RuntimeException{

    private String msg;

    public LoginException(String msg) {
        this.msg = msg;
    }

    public LoginException(String message, String msg) {
        super(message);
        this.msg = msg;
    }
}