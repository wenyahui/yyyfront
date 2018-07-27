/**   
* @Title: MenuServiceImpl.java 
* @Package com.bootdo.admin.service.impl 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午8:11:54 
* @version V1.0   
*/
package com.bootdo.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.admin.dao.MenuDao;
import com.bootdo.admin.service.MenuService;
import com.bootdo.system.domain.MenuDO;

/** 
* @ClassName: MenuServiceImpl 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午8:11:54 
*  
*/
@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuDao menuDao;
	
	@Override
	public int menuCount(Map<String, Object> paramMap) {
		return menuDao.menuCount(paramMap);
	}

	@Override
	public List<Map<String, Object>> menuList(Map<String, Object> paramMap) {
		return menuDao.menuList(paramMap);
	}

	@Override
	public Map<String, Object> queryMenuById(Long menuId) {
		return menuDao.queryMenuById(menuId);
	}

	@Override
	public int insertMenu(MenuDO menu) {
		return menuDao.insertMenu(menu);
	}

	@Override
	public int updateMenuById(MenuDO menu) {
		return menuDao.updateMenuById(menu);
	}
	
	@Override
	public int delMenuById(Long menuId) {
		return menuDao.delMenuById(menuId);
	}

}
