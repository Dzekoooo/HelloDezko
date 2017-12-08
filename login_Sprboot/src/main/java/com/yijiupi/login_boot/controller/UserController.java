package com.yijiupi.login_boot.controller;


import com.yijiupi.login_boot.pojo.UserPO;
import com.yijiupi.login_boot.pojo.UserVO;
import com.yijiupi.login_boot.service.UserService;
import com.yijiupi.login_boot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import static com.yijiupi.login_boot.util.CommonUtil.*;

@Controller
@RequestMapping("/user")


/**
*@Author: ZhangZhe
*@Description  Controller层
*@Date: 13:49 2017/12/7
*/

public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "name", required = false) String loginName,
                              @RequestParam(value = "pWord", required = false) String password,
                              HttpSession session, ModelAndView mv){
            UserVO userVO = userService.login(loginName, password);
            if (null != userVO){
                session.setAttribute(CommonUtil.USER_SESSION, userVO);
                mv.setViewName(CommonUtil.SUCCESS);
            } else {
                mv.setViewName(CommonUtil.LOGINFORM);
            }
        return mv;
    }


    @RequestMapping(value = "/register")
    public ModelAndView register(@RequestParam(value = "name", required = false)  String userName,
                                 @RequestParam(value = "userPass", required = false) String password,
                                @RequestParam(value = "userPass1", required = false) String password1,
                         ModelAndView mv) throws Exception {
        if (null == userName || null == password || null == password1){
            mv.setViewName(CommonUtil.REGISTER);
        } else {
            //判断用户名是否存在
            UserVO userVO = userService.checkUser(userName, password);
            if (null != userVO){
                mv.addObject("message", "用户名已存在，请直接登录");
                mv.setViewName(CommonUtil.LOGIN);
            }else {
                if (password != password1){
                    mv.addObject("message", "密码不一致,请重新注册");
                    mv.setViewName(CommonUtil.REGISTERFORM);
                }
                Boolean registerResult = userService.register(userName, password);
                if (true == registerResult){
                    mv.setViewName(CommonUtil.LOGIN);
                } else {
                    mv.addObject("message", "注册失败,请重新注册");
                    mv.setViewName(CommonUtil.REGISTERFORM);
                }
            }
        }
        return mv;
    }
}
