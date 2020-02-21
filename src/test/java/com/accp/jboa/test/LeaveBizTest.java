package com.accp.jboa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accp.jboa.biz.LeaveBiz;
import com.accp.jboa.pojo.LeaveInfo;
import com.accp.jboa.vo.EmployeeVo;

@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-ctx.xml"})
public class LeaveBizTest {
	
	@Autowired
	private LeaveBiz biz;
	
	@Test
	public void testFind() {
		EmployeeVo emp = new EmployeeVo(1004, null, null, 3, null, 1, null, null, null);
		for (LeaveInfo leave : biz.findLeaveInfoList(emp, null, null, 2, 3).getList()) {
			System.out.println(leave.getEvent());
		}
		
	}

}
