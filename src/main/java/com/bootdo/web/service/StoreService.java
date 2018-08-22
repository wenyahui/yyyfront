package com.bootdo.web.service;

import org.springframework.transaction.annotation.Transactional;

import com.bootdo.web.entity.Store;

@Transactional
public interface StoreService {
	
	Store getStoreByUserId(Integer userId);
	void addStore(Store store);
}
