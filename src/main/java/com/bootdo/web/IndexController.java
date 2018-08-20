/**
 * 首页controller
 */
package com.bootdo.web;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.config.YunpianConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.utils.CodeKit;
import com.bootdo.utils.SmsUtil;
import com.bootdo.utils.VerifyCodeUtil;
import com.bootdo.web.entity.User;
import com.bootdo.web.service.ArticleService;
import com.bootdo.web.service.ColumnService;
import com.bootdo.web.service.UserService;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ColumnService columnService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	YunpianConfig ypConfig;
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
	String index(Model model) {
		//文章栏目
		List<Map<String, Object>> columnList = columnService.getColumnList();
		model.addAttribute("columnList", columnList);
		return "web/index";
	}
	
	@RequestMapping("/index/articleData")
	@ResponseBody
	public PageUtils listData(Model model,@RequestParam Map<String, Object> paramMap) {
		Query query = new Query(paramMap);
		List<Map<String, Object>> list = articleService.getArticleList(query);
		int count = articleService.queryArticleCount(paramMap);
		PageUtils page = new PageUtils(list, count);
		return page;
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
		return "web/login";
	}
	
	@PostMapping("/doLogin")
	@ResponseBody
	R doLogin(@RequestParam String tel, @RequestParam String password) {
		
		return R.ok();
	}
	/**
	 * 
	 * @Title: register 
	 * @Description: 去登录页面
	 * @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author wyh<18749563260@139.com>
	 */
	@GetMapping("/reg")
	String register(){
		return "web/register";
	}
	
	@PostMapping("/doReg")
	@ResponseBody
	R doReg(@RequestParam String tel , @RequestParam String code, @RequestParam String password) {
		Object sessionCode = request.getSession().getAttribute(CodeKit.REG_TEL_CODE);
		if(sessionCode!=null && sessionCode.toString().equals(code)) {
			User user = new User();
			user.setAddTime(new Date());
			user.setNickname("用户"+tel.substring(6));
			user.setPassword(MD5Utils.encrypt(tel,password));
			userService.addUser(user);
			return R.ok();
		}else {
			return R.error("验证码错误!");
		}
	}
	/**
	 * 获取短信验证码
	 * @return
	 */
	@PostMapping("/getSMCode")
	@ResponseBody
	R getSMCode(@RequestParam String tel) {
		String code = VerifyCodeUtil.generateVerifyCode(4, VerifyCodeUtil.NUM_CODES);
		request.getSession().setAttribute(CodeKit.REG_TEL_CODE, code);
		return SmsUtil.sendSm(ypConfig.getApiKey(), tel, code);
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
