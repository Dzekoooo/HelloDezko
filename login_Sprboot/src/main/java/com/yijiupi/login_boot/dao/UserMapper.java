package com.yijiupi.login_boot.dao;


import com.yijiupi.login_boot.pojo.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    UserPO login(@Param("loginName") String loginName,
                 @Param("password") String password);

    boolean register(UserPO userPO);

    UserPO checkUser(@Param("userName") String userName,
                     @Param("password") String password);

}
