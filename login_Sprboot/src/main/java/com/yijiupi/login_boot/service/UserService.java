package com.yijiupi.login_boot.service;

import com.yijiupi.login_boot.pojo.UserVO;

public interface UserService {
    /**
     * 登录功能
     */
    UserVO login(String loginName, String password);

    /**
     * 注册功能
     */
    boolean register(String userName, String password);

    /**
     * 查重
     */
    UserVO checkUser(String userName, String password);

}
