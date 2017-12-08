package com.yijiupi.login_boot;

import com.yijiupi.login_boot.dao.UserMapper;
import com.yijiupi.login_boot.pojo.UserPO;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	private UserMapper userMapper;


	//测试注册操作
//	@Test
//	public void testRegister() {
//		UserPO user = new UserPO();
//		user.setUserName("eeee");
//		user.setPassword("123456");
//		userMapper.register(user);
//	}


	//测试登录
//	@Test
//	public void testLogin(){
//		Boolean result = false;
//		UserPO userPO = userMapper.login("eeee", "123456");
//		if (null == userPO){
//			System.out.println("1111");
//		} else {
//			System.out.println("2222");
//		}
//	}

	//测试匹配
	@Test
	public void testCheck(){
		UserPO userPO = userMapper.checkUser("eeee", "123456");
		if (null == userPO){
			System.out.println("1111");
		} else {
			System.out.println("2222");
		}
	}

}
