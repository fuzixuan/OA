package com.accp.jboa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accp.jboa.biz.EmployeeBiz;
import com.accp.jboa.pojo.Employee;
import com.accp.jboa.vo.EmployeeVo;

@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-ctx.xml"})
public class EmployeeBizTest {
	
	@Autowired
	private EmployeeBiz biz;
	
	@Test
	public void testLogin() {
		EmployeeVo count = biz.getEmployeeByIdAndPwd(1000, "aaa12345");
		System.out.println("*******"+count.getEmployeeName());
	}

}
