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

import com.bootdo.web.dao.CustomerMapper;
import com.bootdo.web.dao.StoreMapper;
import com.bootdo.web.entity.Customer;
import com.bootdo.web.entity.Store;
import com.bootdo.web.service.CustomerService;
import com.bootdo.web.service.StoreService;

/** 
* @ClassName: CustomerServiceImpl 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午8:11:54 
*  
*/
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerMapper customerMapper;

	@Override
	public void addCustomer(Customer customer) {
		customerMapper.insertSelective(customer);
	}

	@Override
	public Boolean checkExits(Customer customer) {
		int count = customerMapper.selectCusIsExits(customer);
		if(count==0) {
			return true;
		}else {
			return false;
		}
	}
	
	

}
