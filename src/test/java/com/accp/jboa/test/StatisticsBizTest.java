package com.accp.jboa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accp.jboa.biz.StatisticsBiz;
import com.accp.jboa.pojo.Statistics;
import com.accp.jboa.vo.EmployeeVo;
import com.github.pagehelper.PageInfo;

@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-ctx.xml"})
public class StatisticsBizTest {
	
	@Autowired
	private StatisticsBiz biz;
	
	@Test
	public void testQuery() {
		EmployeeVo emp = new EmployeeVo();
		emp.setDepartmentId(3);
		emp.setPositionId(0);
		PageInfo<Statistics> page = biz.findStatisticsByMonth(emp, null, null, null, 1, 3);
		for (Statistics s : page.getList()) {
			System.out.println(s.getDepartmentName());
		}
	}
	
	@Test
	public void testBizAOP() throws Exception {
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring-ctx.xml");
		StatisticsBiz sBiz = (StatisticsBiz)ac.getBean("statisticsBiz");
		sBiz.addStatistics("2018-05-01");
	}

}
