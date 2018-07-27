/**
 * 首页controller
 */
package com.bootdo.web;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.utils.CodeKit;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	* @Title: welcome 
	* @Description: 去首页
	* @param model
	* @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 */
	@GetMapping("/")
	String index() {
		//TODO 首页
		return "web/index";
	}
	
	/**
	 * 
	* @Title: login 
	* @Description: 去登录页面
	* @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 */
	@GetMapping("/login")
	String login(){
		return "admin/login";
	}

	/**
	 * 
	* @Title: ajaxLogin 
	* @Description: 异步登录方法
	* @param username
	* @param password
	* @return    设定文件 
	* @return R    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 */
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String account,String password,String code,HttpSession session){
		//先判断验证码
		Object sessionCode = session.getAttribute(CodeKit.VERIFY_CODE);
		if(StringUtils.isBlank(code)||!code.toUpperCase().equals(sessionCode)){
			return R.error("验证码错误");
		}
		password = MD5Utils.encrypt(account, password);
		UsernamePasswordToken token = new UsernamePasswordToken(account, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}
	
	/**
	 * 
	* @Title: main 
	* @Description: 主页面
	* @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 */
	@GetMapping("/main")
	String main(){
		
		return "admin/center";
	}
}
