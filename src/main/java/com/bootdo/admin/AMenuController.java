/**   
* @Title: AMenuController.java 
* @Package com.bootdo.admin 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午6:11:04 
* @version V1.0   
*//*
package com.bootdo.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.admin.service.MenuService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.MenuDO;

*//** 
* @ClassName: AMenuController 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午6:11:04 
*  
*//*

@Controller
@RequestMapping("/MS/menu")
public class AMenuController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MenuService menuService;
	
	*//**
	 * 
	* @Title: getMenu 
	* @Description: 获取菜单列表
	* @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 *//*
	@GetMapping("/list")
	String getMenu(Model model){
		List<Tree<Map<String, Object>>> trees = new ArrayList<Tree<Map<String, Object>>>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sort", "order_num");
		paramMap.put("order", "asc");
		List<Map<String, Object>> list = menuService.menuList(paramMap);
		for (Map<String, Object> sysMenuDO : list) {
			Tree<Map<String, Object>> tree = new Tree<Map<String, Object>>();
			tree.setId(sysMenuDO.get("menuId")==null?"":sysMenuDO.get("menuId").toString());
			tree.setParentId(sysMenuDO.get("parentId")==null?"":sysMenuDO.get("parentId").toString());
			tree.setText(sysMenuDO.get("name")==null?"":sysMenuDO.get("name").toString());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.get("url")==null?"":sysMenuDO.get("url").toString());
			attributes.put("icon", sysMenuDO.get("icon")==null?"":sysMenuDO.get("icon").toString());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Map<String, Object>> t = BuildTree.build(trees);
		model.addAttribute("list", t);
		return "admin/menu/list";
	}
	
	*//**
	 * 
	* @Title: toEditMenu 
	* @Description: 去修改菜单
	* @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 *//*
	@GetMapping("/edit{id}")
	String toEditMenu(@PathVariable Long id,Model model){
		List<Tree<Map<String, Object>>> trees = new ArrayList<Tree<Map<String, Object>>>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sort", "order_num");
		paramMap.put("order", "asc");
		List<Map<String, Object>> list = menuService.menuList(paramMap);
		for (Map<String, Object> sysMenuDO : list) {
			Tree<Map<String, Object>> tree = new Tree<Map<String, Object>>();
			tree.setId(sysMenuDO.get("menuId")==null?"":sysMenuDO.get("menuId").toString());
			tree.setParentId(sysMenuDO.get("parentId")==null?"":sysMenuDO.get("parentId").toString());
			tree.setText(sysMenuDO.get("name")==null?"":sysMenuDO.get("name").toString());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.get("url")==null?"":sysMenuDO.get("url").toString());
			attributes.put("icon", sysMenuDO.get("icon")==null?"":sysMenuDO.get("icon").toString());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Map<String, Object>> t = BuildTree.build(trees);
		model.addAttribute("list", t);
		if(id!=null){
			Map<String, Object> menu = menuService.queryMenuById(id);
			model.addAttribute("menu", menu);
		}
		return "admin/menu/edit";
	}
	
	*//**
	 * 
	* @Title: saveMenu 
	* @Description: 保存菜单
	* @param menu
	* @return    设定文件 
	* @return R    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 *//*
	@PostMapping("/save")
	@ResponseBody
	R saveMenu(@ModelAttribute MenuDO menu){
		int count;
		if(menu.getMenuId()==null){
			count = menuService.insertMenu(menu);
		}else{
			count = menuService.updateMenuById(menu);
		}
		if(count>0){
			return R.ok("保存成功!");
		}
		return R.error("保存失败!");
	}
	
	*//**
	 * 
	* @Title: delMenu 
	* @Description: 删除菜单
	* @return    设定文件 
	* @return R    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 *//*
	@PostMapping("/del{menuId}")
	@ResponseBody
	R delMenu(@PathVariable Long menuId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("hasChildLv2", "1");
		paramMap.put("parentId", menuId);
		int childCount = menuService.menuCount(paramMap);
		if(childCount>0){
			return R.error("请先删除子菜单!");
		}
		int count = menuService.delMenuById(menuId);
		if(count>0){
			return R.ok("删除菜单成功!");
		}
		return R.error("删除菜单失败!");
	}
}
*/