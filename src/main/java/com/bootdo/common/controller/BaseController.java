package com.bootdo.common.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bootdo.utils.CodeKit;
import com.bootdo.utils.RedisUtil;
import com.bootdo.web.entity.User;

@Controller
public class BaseController {
	
	@Autowired
	protected
	HttpServletRequest request;
	
	@Autowired
	protected
	HttpServletResponse response;
	
	@Autowired
	RedisUtil redisUtil;
	/**
	 * 设置cookie
	 * @param response
	 * @param name  cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期  以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
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
	/**
	 * 
	 * @Method: setSession
	 * @author wyh
	 * @Description: 添加session
	 * @param key
	 * @param object
	 * @param min 分钟数
	 * @return void
	 * @throws
	 */
	public void setSession(String key, Object value, int min) {
		String cookieValue = CodeKit.LOGIN_USER+UUID.randomUUID().toString();
		addCookie(response, key, cookieValue, 60 * min);
		redisUtil.set(cookieValue, value, min * 60);
	}

	/**
	 * 
	 * @Method: removeSession 
	 * @Description: 移除Session
	 * @param key
	 * @return void
	 * @throws
	 */
	public void removeSession(String key) {
		Cookie cookie = getCookieByName(request, key);
		if (cookie == null || StringUtils.isBlank(cookie.getValue())) {
			return;
		}
		redisUtil.del(cookie.getValue());
		addCookie(response, key, null, 0);
	}
	public User getLoginUser() {
		Object object = getSession(CodeKit.LOGIN_USER);
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
	public Object getSession(String key) {
		Cookie cookie = getCookieByName(request, key);
		if (cookie == null || StringUtils.isBlank(cookie.getValue())) {
			return null;
		} else {
			return redisUtil.get(cookie.getValue());
		}
	}
	public void setLoginUser(User user) {
		setSession(CodeKit.LOGIN_USER, user, 30*24*60);
	}
}