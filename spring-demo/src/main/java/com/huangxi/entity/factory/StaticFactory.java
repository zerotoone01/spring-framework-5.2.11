package com.huangxi.entity.factory;

import com.huangxi.entity.User;

//静态工厂调用
public class StaticFactory {

	public static User getUser(){
		return new User();
	}
}
