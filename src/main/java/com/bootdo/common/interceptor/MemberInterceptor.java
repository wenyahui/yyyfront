package com.bootdo.common.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bootdo.utils.CodeKit;
import com.bootdo.utils.RedisUtil;
import com.bootdo.web.entity.User;
@Component
public class MemberInterceptor implements HandlerInterceptor{

	@Autowired
	RedisUtil redisUtil;
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入拦截器");
		User user = getLoginUser(request);
		if(user==null) {
			response.sendRedirect("/login?errorMsg=请先登录!");
			return false;
		}
		return true;
	}

	public User getLoginUser(HttpServletRequest request) {
		Object object = getSession(request,CodeKit.LOGIN_USER);
		if (object != null) {
			User user = (User) object;
			if (user != null) {
				// Cookie cookie = getCookieByName(request,
				// CodeKit.SYSTEM_MANAGER);
				// CacheCommon.set(cookie.getValue(), account,
				// CacheCommon.COOKIE_MAXMINUTE * 60 * 1000);
				return user;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @Method: getSession 
	 * @Description: 查询Session值
	 * @param key
	 * @return Object
	 * @throws
	 */
	public Object getSession(HttpServletRequest request,String key) {
		Cookie cookie = getCookieByName(request, key);
		if (cookie == null || StringUtils.isBlank(cookie.getValue())) {
			return null;
		} else {
			return redisUtil.get(cookie.getValue());
		}
	}
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = readCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
}
