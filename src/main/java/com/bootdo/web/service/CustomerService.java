package com.bootdo.web.service;

import org.springframework.transaction.annotation.Transactional;

import com.bootdo.web.entity.Customer;

@Transactional
public interface CustomerService {
	
	void addCustomer(Customer customer);
	
	Boolean checkExits(Customer customer);
}
