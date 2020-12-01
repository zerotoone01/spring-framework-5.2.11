package com.huangxi.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * 测试 构造方法 相互依赖
 */
@Repository
//@Scope("prototype") //Spring不支持prototype，无论是构造器还是setter方式注入
@Scope("singleton") //构造器方式注入，即使为singleton，spring也会报循环依赖异常，不支持该方式注入
public class Staff {
//	private Company company;
//	@Autowired
//	public Staff(Company company){
//		this.company = company;
//	}

}
