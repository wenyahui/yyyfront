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

import com.bootdo.web.dao.StoreMapper;
import com.bootdo.web.entity.Store;
import com.bootdo.web.service.StoreService;

/** 
* @ClassName: ColumnServiceImpl 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午8:11:54 
*  
*/
@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	StoreMapper storeMapper;
	
	@Override
	public Store getStoreByUserId(Integer userId) {
		return storeMapper.getStoreByUserId(userId);
	}

	@Override
	public void addStore(Store store) {
		storeMapper.insertSelective(store);
	}
	

}
