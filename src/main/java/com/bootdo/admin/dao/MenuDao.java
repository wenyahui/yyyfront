/**   
* @Title: MenuDao.java 
* @Package com.bootdo.admin.dao 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午6:29:45 
* @version V1.0   
*/
package com.bootdo.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.system.domain.MenuDO;

/** 
* @ClassName: MenuDao 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午6:29:45 
*  
*/
@Mapper
public interface MenuDao {
	
	/**
	 * 
	* @Title: menuList 
	* @Description: 获取菜单列表
	* @param paramMap
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 */
	List<Map<String, Object>> menuList(Map<String, Object> paramMap);
	
	/**
	 * 
	* @Title: menuCount 
	* @Description: 获取菜单总数
	* @param paramMap
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 */
	int menuCount(Map<String, Object> paramMap);
	
	/**
	 * 
	* @Title: queryMenuById 
	* @Description: 根据menuid查询菜单
	* @param menuId
	* @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 */
	Map<String, Object> queryMenuById(Long menuId);
	
	/**
	 * 
	* @Title: insertMenu 
	* @Description: 添加菜单
	* @param menu
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 */
	int insertMenu(MenuDO menu);
	
	/**
	 * 
	* @Title: updateMenuById 
	* @Description: 修改菜单
	* @param menu
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 */
	int updateMenuById(MenuDO menu);
	
	/**
	 * 
	* @Title: delMenuById 
	* @Description: 根据菜单id删除菜单
	* @param menuId
	* @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 */
	int delMenuById(Long menuId);
}
