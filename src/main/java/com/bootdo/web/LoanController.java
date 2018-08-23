/**
 * 会员中心controller
 */
package com.bootdo.web;
import java.util.Date;

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

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.R;
import com.bootdo.web.entity.Customer;
import com.bootdo.web.service.CustomerService;

@Controller
@RequestMapping("/")
public class LoanController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CustomerService customerService;
	@GetMapping("/loanMsg")
	String loanMsg(@RequestParam(required=false) Integer storeId,Model model) {
		model.addAttribute("storeId", storeId);
		return "web/message";
	}
	
	@PostMapping("/saveLoan")
	@ResponseBody
	R saveLoan(@RequestParam String name,@RequestParam String tel,@RequestParam Integer storeId) {
		//判断唯一的标准,手机号  店铺id  状态0 为唯一标准  不然不入库
		Customer customer = new Customer();
		if(storeId!=null) {
			customer.setStoreId(storeId);
		}
		customer.setName(name);
		customer.setTel(tel);
		customer.setAddTime(new Date());
		customer.setState(0);
		Boolean flag = customerService.checkExits(customer);
		if(flag) {
			customerService.addCustomer(customer);
		}
		return R.ok();	
	}
}
