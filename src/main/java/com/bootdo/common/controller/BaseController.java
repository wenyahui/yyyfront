package com.bootdo.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	
	@Autowired
	protected
	HttpServletRequest request;
	
	@Autowired
	protected
	HttpServletResponse response;
	
}