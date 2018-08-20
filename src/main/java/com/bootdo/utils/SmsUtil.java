package com.bootdo.utils;

import java.util.Map;

import com.bootdo.common.utils.R;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

public class SmsUtil {

	public static R sendSm(String apikey,String tel,String code) {
		YunpianClient clnt = new YunpianClient(apikey).init();
		//发送短信API
		Map<String, String> param = clnt.newParam(2);
		param.put(YunpianClient.MOBILE, tel);
		param.put(YunpianClient.TEXT, "【袋鼠之家】您的验证码是"+code);
		@SuppressWarnings("unused")
		Result<SmsSingleSend> r = clnt.sms().single_send(param);
		//获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()

		//账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*

		//释放clnt
		clnt.close();
		if(r.getCode().intValue()==0) {
			return R.ok();
		}else {
			if(r.getCode().intValue()==8) {
				return R.error("同一个手机号不能再30秒内重复发送!");
			}else if(r.getCode().intValue()==9) {
				return R.error("同一个手机号5分钟内发送次数过多!");
			}else if(r.getCode().intValue()==17) {
				return R.error("该手机号今天允许发送的条数已达上限!");
			}else if(r.getCode().intValue()==22) {
				return R.error("该手机号一小时内允许发送的条数已达上限!");
			}else {
				return R.error("发送失败,请稍后再试!");
			}
		}
	} 
}
