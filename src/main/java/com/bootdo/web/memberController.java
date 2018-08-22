/**
 * 会员中心controller
 */
package com.bootdo.web;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bootdo.common.config.WebConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.R;
import com.bootdo.utils.QrcodeUtil;
import com.bootdo.web.entity.Store;
import com.bootdo.web.entity.User;
import com.bootdo.web.service.StoreService;

@Controller
@RequestMapping("/member")
public class memberController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WebConfig webConfig;
	@Autowired
	StoreService storeService;
	@GetMapping("")
	String memberIndex(RedirectAttributes ra) {
		User user = getLoginUser();
		System.out.println(user.getTel()+"---登录用户手机号");
		return "web/mem_index";
	}
	
	/**
	 * 店铺二维码
	 * @return
	 */
	@GetMapping("/storeCode")
	@ResponseBody
	R storeCode() {
		//查询是否有店铺
		User user = getLoginUser();
		Store store = storeService.getStoreByUserId(user.getId());
		if(store==null) {
			return R.error(1, "请先申请店铺二维码!");
		}
		if(store.getStatus().intValue()==0) {
			return R.error(2, "您的店铺二维码正在审核中!");
		}
		if(store.getStatus().intValue()==2) {
			return R.error(3, "您的店铺二维码审核失败!");
		}
		return R.ok(webConfig.getSite()+"loanMsg?storeId="+store.getId());
	}
	
	/**
	 * 申请店铺页面
	 * @return
	 */
	@GetMapping("/applyStore")
	String applyStore() {
		
		return "web/applyStore";
	}
	
	/**
	 * 保存店铺
	 * @param store
	 * @return
	 */
	@PostMapping("/doSaveStore")
	@ResponseBody
	R doSaveStore(@ModelAttribute Store store) {
		User user = getLoginUser();
		store.setUserId(user.getId());
		store.setAddTime(new Date());
		store.setStatus(0);
		storeService.addStore(store);
		return R.ok();
	}
	
	@GetMapping("/createCode")
	void createCode(@RequestParam String text) throws IOException {
		QrcodeUtil.createQrcode(text, response);
	}
}
