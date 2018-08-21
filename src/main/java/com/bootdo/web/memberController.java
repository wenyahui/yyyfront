/**
 * 会员中心controller
 */
package com.bootdo.web;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bootdo.common.controller.BaseController;
import com.bootdo.web.entity.User;

@Controller
@RequestMapping("/member")
public class memberController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("")
	String memberIndex(RedirectAttributes ra) {
		User user = getLoginUser();
		if(user==null) {
			ra.addFlashAttribute("errorMsg", "请先登录!");
			return "redirect:/login";
		}
		System.out.println(user.getTel()+"---登录用户手机号");
		return "web/mem_index";
	}
}
