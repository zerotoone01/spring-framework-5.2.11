package org.demo.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @description TODO
 * @date 2020-08-04
 */
public class BeanPostProcessorTest implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Bean [" + beanName + "] 开始初始化");
		// 这里一定要返回 bean，不能返回 null
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Bean [" + beanName + "] 完成初始化");
		return bean;
	}

	public void display(){
		System.out.println("hello BeanPostProcessor!!!");
	}

	public static void main(String[] args) {
		String xmlPath="/org/my-beans.xml";
		ClassPathResource resource = new ClassPathResource(xmlPath);
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(resource);
		//要想 BeanFactory 容器 的 BeanPostProcessor 生效我们必须手动调用 #addBeanPostProcessor(BeanPostProcessor beanPostProcessor) 方法，将定义的 BeanPostProcessor 注册到相应的 BeanFactory 中。但是 ApplicationContext 不需要手动，因为 ApplicationContext 会自动检测并完成注册。
		//BeanPostProcessor 注册过程并不生效, 需要加入下面两行
		BeanPostProcessorTest beanPostProcessorTest = new BeanPostProcessorTest();
		factory.addBeanPostProcessor(beanPostProcessorTest);

		BeanPostProcessorTest test = (BeanPostProcessorTest) factory.getBean("beanPostProcessorTest");

		test.display();
	}
}
