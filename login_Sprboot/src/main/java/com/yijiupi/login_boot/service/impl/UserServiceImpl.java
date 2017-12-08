package com.yijiupi.login_boot.service.impl;


import com.yijiupi.login_boot.controller.UserController;
import com.yijiupi.login_boot.dao.UserMapper;
import com.yijiupi.login_boot.pojo.UserPO;
import com.yijiupi.login_boot.pojo.UserVO;
import com.yijiupi.login_boot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
*@Author: ZhangZhe
*@Date: 13:51 2017/12/7
*/
@Service("userService")
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO login(String name, String password) {
        //登录
        LOGGER.info("进入login方法");
        UserPO userPO = userMapper.login(name, password);
        return convertToVO(userPO);
    }

    @Override
    public boolean register(String name, String password) {
        LOGGER.info("进入register方法");
        UserPO userPO = new UserPO();
        userPO.setUserName(name);
        userPO.setPassword(password);
        return userMapper.register(userPO);
    }

    @Override
    public UserVO checkUser(String name, String password) {
        LOGGER.info("进入checkUser方法");
        UserPO userPO = userMapper.checkUser(name, password);
        return convertToVO(userPO);
    }


    /**
     *
     * @param userVO
     * @return userPO
     */

    private UserPO convertToPO(UserVO userVO){
        LOGGER.info("将PO转化成PO");
        if (null == userVO){
            return null;
        }
        UserPO userPO = new UserPO();
        userPO.setUserName(userVO.getUserName());
        userPO.setPassword(userVO.getPassword());
        return userPO;
    }

    /**
     *
     * @param userPO
     * @return userVO
     */
    private UserVO convertToVO(UserPO userPO){
        LOGGER.info("将VO转成PO");
        if (null == userPO){
            return null;
        }
        UserVO userVO = new UserVO();
        userVO.setId(userPO.getId());
        userVO.setUserName(userPO.getUserName());
        userVO.setPassword(userPO.getPassword());
        return userVO;
    }
}
