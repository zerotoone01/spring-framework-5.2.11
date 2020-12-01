package org.demo.properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description TODO
 * @date 2020-08-05
 */
//public class CustomPropertyConfig extends PropertyPlaceholderConfigurer {
public class CustomPropertyConfig extends PropertySourcesPlaceholderConfigurer {
	private Resource[] locations;

	private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();


	@Override
	public void setLocations(Resource... locations) {
		this.locations = locations;
	}

	@Override
	public void setLocalOverride(boolean localOverride) {
		this.localOverride = localOverride;
	}


	/**
	 * 覆盖这个方法，根据启动参数，动态PropertyOverrideConfigurer 读取配置文件
	 * @param props
	 * @throws IOException
	 */
	@Override
	protected void loadProperties(Properties props) throws IOException {
		if (locations != null) {
			// locations 里面就已经包含了那三个定义的文件
			for (Resource location : this.locations) {
				InputStream is = null;
				try {
					String filename = location.getFilename();
					String env = "application-" + System.getProperty("spring.profiles.active", "dev") + ".properties";

					// 找到我们需要的文件，加载
					if (filename.contains(env)) {
						logger.info("Loading properties file from " + location);
						is = location.getInputStream();
						this.propertiesPersister.load(props, is);

					}
				} catch (IOException ex) {
					logger.info("读取配置文件失败.....");
					throw ex;
				} finally {
					if (is != null) {
						is.close();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		//在 idea 的 VM options 里面增加 -Dspring.profiles.active=dev，标志当前环境为 dev 环境,
		// 当将 -Dspring.profiles.active 调整为 test，则打印结果则是 chenssy-test，这样就完全实现了根据不同的环境加载不同的配置。
		String xmlPath="org/properties-beans.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);

		StudentService studentService = (StudentService) context.getBean("studentService");
		System.out.println("student name:" + studentService.getName());
	}
}
