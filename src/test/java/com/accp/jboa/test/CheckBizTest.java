package com.accp.jboa.test;

import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.ContextLoaderListener;

import com.accp.jboa.biz.CheckBiz;

@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-ctx.xml"})
public class CheckBizTest {
	
	@Autowired
	private CheckBiz biz;
	
	@Test
	public void testQuery() {
		biz.findCheckList(1, 3);
	}

}
