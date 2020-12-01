package com.huangxi.spring.resource;

import org.springframework.core.io.*;

/**
 *  IoC 之 Spring 统一资源加载策略
 *  PathMatchingResourcePatternResolver路径资源匹配溶解器  https://www.cnblogs.com/question-sky/p/6959493.html
 *  Spring源码学习-含有通配符路径解析（上） https://www.coderli.com/spring-wildpath-parse/
 * @description TODO
 * @date 2020-07-21
 */
public class MyResourceLoader {
	public static void main(String[] args) {
		// /spring-framework/spring-demo/src/main/resources/file/spark.txt
		// 文件是否存在不影响测试结果
		String dir = System.getProperty("user.dir");
		System.out.println("current dir="+dir);
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource fileResource1 = resourceLoader.getResource("D:\\spring-framework\\spring-demo\\src\\main\\resources\\file\\spark.txt");
		System.out.println("fileResource1 is FileSystemResource:" + (fileResource1 instanceof FileSystemResource));

		Resource fileResource2 = resourceLoader.getResource("/spring-demo/src/main/resources/file/spark.txt");
		System.out.println("fileResource2 is ClassPathResource:" + (fileResource2 instanceof ClassPathResource));

		Resource urlResource1 = resourceLoader.getResource("file:/spring-demo/src/main/resources/file/spark.txt");
		System.out.println("urlResource1 is UrlResource:" + (urlResource1 instanceof UrlResource));

		Resource urlResource2 = resourceLoader.getResource("https://www.baidu.com");
		System.out.println("urlResource1 is urlResource:" + (urlResource2 instanceof UrlResource));
	}
}
/**
 result:
 fileResource1 is FileSystemResource:false
 fileResource2 is ClassPathResource:true
 urlResource1 is UrlResource:true
 urlResource1 is urlResource:true
 */