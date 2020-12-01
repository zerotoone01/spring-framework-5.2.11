package com.huangxi.controller;

import com.huangxi.service.WelcomeService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

@Controller
public class WelcomeController
	//自定义实现Aware的部分接口
		implements ApplicationContextAware, BeanNameAware {

	private String myName;
	private ApplicationContext myContainer;
	@Autowired
	private WelcomeService welcomeService;

	public void handleRequest(){
		welcomeService.sayHello("come from controller");
		System.out.println("who am i: "+myName);
		String[] beanDefinitionNames = myContainer.getBeanDefinitionNames();
		for(String item: beanDefinitionNames){
			System.out.println("get bean name from myContainer: "+item);
		}
	}

	@Override
	public void setBeanName(String name) {
		this.myName=name;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.myContainer=applicationContext;
	}
}
