package org.demo.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @description TODO
 * @date 2020-08-03
 */
public class MyApplicationAware implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware {
	private String beanName;

	private BeanFactory beanFactory;

	private ClassLoader classLoader;

	private ApplicationContext applicationContext;
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("调用了 BeanClassLoaderAware 的 setBeanClassLoader 方法");
		this.classLoader = classLoader;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("调用了 BeanFactoryAware 的 setBeanFactory 方法");
		this.beanFactory = beanFactory;
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("调用了 BeanNameAware 的 setBeanName 方法");
		this.beanName = name;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("调用了 ApplicationContextAware 的 setApplicationContext 方法");
		this.applicationContext = applicationContext;
	}
	public void display(){
		System.out.println("beanName:" + beanName);
		System.out.println("是否为单例：" + beanFactory.isSingleton(beanName));
		if(applicationContext==null){
			System.out.println(" applicationContext is null");
		}else{
			System.out.println("系统环境为：" + applicationContext.getEnvironment());
		}
	}


	public static void main(String[] args) {
		//这里只执行了三个 Aware 接口的 set 方法，原因就是通过 #getBean(...) 方法调用时，在激活 Aware 接口时只检测了 BeanNameAware、BeanClassLoaderAware、BeanFactoryAware 三个 Aware 接口。
		System.out.println(System.getProperty("user.dir"));
		String xmlPath="/org/my-beans.xml";
		ClassPathResource resource = new ClassPathResource(xmlPath);
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(resource);
		MyApplicationAware applicationAware = (MyApplicationAware) factory.getBean("myApplicationAware");
		applicationAware.display();

		//这里执行了ApplicationContextAware
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
		MyApplicationAware applicationAware1 = (MyApplicationAware) applicationContext.getBean("myApplicationAware");
		applicationAware1.display();
	}
}
