/**
 * 后台首页controller
 */
package com.bootdo.core.image;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.controller.BaseController;
import com.bootdo.utils.CodeKit;
import com.bootdo.utils.VerifyCodeUtil;

@Controller
@RequestMapping("/")
public class ImageController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	* @Title: codeImage 
	* @Description: 生成验证码
	* @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author wyh<18749563260@139.com>
	 */
	@GetMapping("/codeImage")
	@ResponseBody
	String codeImage() {
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);

		String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
		// 生成图片
		int w = 200, h = 80;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			VerifyCodeUtil.outputImage(w, h, os, verifyCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// 放入session
		request.getSession().setAttribute(CodeKit.VERIFY_CODE, verifyCode);
		return null;
	}
}
