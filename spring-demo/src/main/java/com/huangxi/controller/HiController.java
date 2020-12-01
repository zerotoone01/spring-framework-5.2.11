package com.huangxi.controller;

import com.huangxi.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 测试AOP
@Controller
public class HiController {
	@Autowired
	private HiService hiService;
	public void handleRequest(){
		hiService.sayHi();
		hiService.justWantToSayHi();
	}
}
