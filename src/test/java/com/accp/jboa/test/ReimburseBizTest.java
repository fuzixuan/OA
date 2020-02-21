package com.accp.jboa.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.event.TransactionalEventListener;

import com.accp.jboa.biz.ReimburseBiz;
import com.accp.jboa.pojo.Employee;
import com.accp.jboa.vo.EmployeeVo;
import com.accp.jboa.vo.ReimburseVo;
import com.github.pagehelper.PageInfo;

@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-ctx.xml"})
public class ReimburseBizTest {

	@Autowired
	private ReimburseBiz biz;
	
	@Test
	public void testQuery() {
		EmployeeVo emp = new EmployeeVo();
		emp.setEmployeeId(1004);
		emp.setDepartmentId(3);
		emp.setPositionId(1);
		
		PageInfo<ReimburseVo> page = biz.findReimburseVoList(emp,null, null, new Date(), 1, 3);
		for(ReimburseVo re:page.getList()) {
			System.out.println(re.getCreateManName());
		}
	}
}
