package com.bootdo.web.dao;

import com.bootdo.web.entity.Store;

public interface StoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);
    Store getStoreByUserId(Integer userId);
    
}