package com.bootdo.web.service;

import org.springframework.transaction.annotation.Transactional;

import com.bootdo.web.entity.User;

@Transactional
public interface UserService {
	
	void addUser(User user);
}
