package com.debug.kill.server.controller;/**
 * Created by Administrator on 2019/7/2.
 */

import com.debug.kill.model.entity.User;
import com.debug.kill.model.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户controller
 * @Author:debug (SteadyJack)
 * @Date: 2019/7/2 17:45
 **/
@Controller
public class UserController {

    private static final Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private Environment env;

    @Autowired
    private UserMapper userMapper;


    /**
     * 跳到登录页
     * @return
     */
    @RequestMapping(value = {"/to/login","/unauth"})
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = {"/to/register"})
    public String toRegister(){
        return "register";
    }

    /**
     * 登录认证
     * @param userName
     * @param password
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam String userName, @RequestParam String password, ModelMap modelMap){
        String errorMsg="";
        try {
            if (!SecurityUtils.getSubject().isAuthenticated()){
                String newPsd=new Md5Hash(password,env.getProperty("shiro.encrypt.password.salt")).toString();
                UsernamePasswordToken token=new UsernamePasswordToken(userName,newPsd);
                SecurityUtils.getSubject().login(token);
            }
        }catch (UnknownAccountException e){
            errorMsg=e.getMessage();
            modelMap.addAttribute("userName",userName);
        }catch (DisabledAccountException e){
            errorMsg=e.getMessage();
            modelMap.addAttribute("userName",userName);
        }catch (IncorrectCredentialsException e){
            errorMsg=e.getMessage();
            modelMap.addAttribute("userName",userName);
        }catch (Exception e){
            errorMsg="用户登录异常，请联系管理员!";
            e.printStackTrace();
        }
        if (StringUtils.isBlank(errorMsg)){
            return "redirect:/index";
        }else{
            modelMap.addAttribute("errorMsg",errorMsg);
            return "login";
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "login";
    }


    //用户注册
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestParam String userName, @RequestParam String email,@RequestParam String password, ModelMap modelMap){
        String errorMsg="";
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(email) || StringUtils.isBlank(password)){
            modelMap.addAttribute("errorMsg","用户名-邮箱-密码均不能为空！");
            return "register";
        }
        try {
            String newPsd=new Md5Hash(password,env.getProperty("shiro.encrypt.password.salt")).toString();
            User user=new User();
            user.setEmail(email);
            user.setPassword(newPsd);
            user.setUserName(userName);
            user.setCreateTime(DateTime.now().toDate());
            userMapper.insertSelective(user);
        }catch (Exception e){
            errorMsg="注册失败-当前用户名已存在 或 出现未知异常，请检查！";
            modelMap.addAttribute("userName",userName);
            e.printStackTrace();
        }
        if (StringUtils.isBlank(errorMsg)){
            return "redirect:/to/login";
        }else{
            modelMap.addAttribute("errorMsg",errorMsg);
            return "register";
        }
    }

}



































