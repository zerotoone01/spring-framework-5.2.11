package com.huangxi.service.impl;

import com.huangxi.service.WelcomeService;
import org.springframework.stereotype.Service;

/**
 * @description TODO
 * @date 2020-07-20
 */
@Service
public class WelcomeServiceImpl implements WelcomeService {
	@Override
	public String sayHello(String name) {
		System.out.println("welcome: "+name);
		return null;
	}
}
