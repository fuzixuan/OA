package com.accp.jboa.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("all")
public class SpringBeanUtil {

	private final static ApplicationContext ac = new ClassPathXmlApplicationContext("spring-ctx.xml");

	public static <T> T getBeanByName(String name) {
		return (T) ac.getBean(name);
	}
}
