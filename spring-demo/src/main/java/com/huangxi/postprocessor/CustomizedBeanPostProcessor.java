package com.huangxi.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义bean处理， 可以通过自定义来扩展和增强bean的能力
 *
 * 自定义bean的实现原理
 * @see org.springframework.context.support.AbstractApplicationContext#invokeBeanFactoryPostProcessors(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
 * @see org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanDefinitionRegistryPostProcessors(java.util.Collection, org.springframework.beans.factory.support.BeanDefinitionRegistry)
 */
@Configuration
public class CustomizedBeanPostProcessor implements BeanPostProcessor {

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println(beanName+" >>>> call postProcessBeforeInitialization");
		//TODO 可以在这里对bean进行处理
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println(beanName+" <<< call postProcessAfterInitialization");
		//TODO 可以在这里对bean进行处理, 类似AOP处理
		return bean;
	}
}
