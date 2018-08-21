package com.bootdo.web.dao;

import com.bootdo.web.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    int selectUserByTel(String tel);
    User selectUserEntityByTel(String tel);
}