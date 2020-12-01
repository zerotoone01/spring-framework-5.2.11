package com.huangxi.controller;

import com.huangxi.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//测试 AOP
@Controller
public class HelloController {
	@Autowired
	private HelloService helloService;

	public void handleRequest(){
		helloService.sayHello();
		helloService.justWantToThrowException();

	}

}
