/**   
* @Title: MenuServiceImpl.java 
* @Package com.bootdo.admin.service.impl 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午8:11:54 
* @version V1.0   
*/
package com.bootdo.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.web.dao.UserMapper;
import com.bootdo.web.entity.User;
import com.bootdo.web.service.UserService;

/** 
* @ClassName: ColumnServiceImpl 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午8:11:54 
*  
*/
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void addUser(User user) {
		userMapper.insertSelective(user);
	}

	@Override
	public Boolean checkHasUserByTel(String tel) {
		int count = userMapper.selectUserByTel(tel);
		if(count==0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public User selectUserByTel(String tel) {
		return userMapper.selectUserEntityByTel(tel);
	}

}
