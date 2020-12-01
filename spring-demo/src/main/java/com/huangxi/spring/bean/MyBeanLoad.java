package com.huangxi.spring.bean;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * IoC 之加载 BeanDefinition
 *
 * @description TODO
 * @date 2020-07-21
 */
public class MyBeanLoad {


	public static void main(String[] args) {
		/**
		 * 1.获取资源
		 * 2.获取 BeanFactory
		 * 3.根据新建的 BeanFactory 创建一个 BeanDefinitionReader 对象，该 Reader 对象为资源的解析器
		 * 4.装载资源
		 *
		 *  整个过程就分为三个步骤：资源定位、装载、注册
		 *
		 *  注册：在 IoC 容器内部其实是将第二个过程解析得到的 BeanDefinition 注入到一个 HashMap 容器中。
		 */
		ClassPathResource resource = new ClassPathResource("bean.xml"); // <1>
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory(); // <2>
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory); // <3>
		reader.loadBeanDefinitions(resource); // <4>
	}
}
