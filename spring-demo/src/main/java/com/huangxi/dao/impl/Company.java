package com.huangxi.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * 测试 构造方法 相互依赖
 * @see AbstractAutowireCapableBeanFactory#doCreateBean(String, org.springframework.beans.factory.support.RootBeanDefinition, Object[])
 *
 * @see AbstractAutowireCapableBeanFactory#createBeanInstance(String, org.springframework.beans.factory.support.RootBeanDefinition, Object[])
 *
 * if (ctors != null || mbd.getResolvedAutowireMode() == AUTOWIRE_CONSTRUCTOR ||
 * 				mbd.hasConstructorArgumentValues() || !ObjectUtils.isEmpty(args)) {
 * 			return autowireConstructor(beanName, mbd, ctors, args);
 *                }
 *
 */
@Repository
//@Scope("prototype") //Spring不支持prototype，无论是构造器还是setter方式注入
@Scope("singleton")  //构造器方式注入，即使为singleton，spring也会报循环依赖异常，不支持该方式注入
public class Company {
//	private Staff staff;
//	@Autowired
//	public Company(Staff staff){
//		this.staff = staff;
//	}
}
