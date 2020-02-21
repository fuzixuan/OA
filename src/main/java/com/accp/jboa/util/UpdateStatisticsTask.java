package com.accp.jboa.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.accp.jboa.biz.StatisticsBiz;

import jxl.read.biff.SheetImpl;

public class UpdateStatisticsTask extends TimerTask{
	ApplicationContext ac=new ClassPathXmlApplicationContext("spring-ctx.xml");
	StatisticsBiz biz = (StatisticsBiz)ac.getBean("statisticsBiz");
	
	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("开始统计");
		synchronized (biz) {
			try {
				Calendar c = Calendar.getInstance(); 
				c.setTime(new Date());
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH) + 1; 
				int day = c.get(Calendar.DAY_OF_MONTH);
				if(year % 4 == 0) {//判断当前日期是否为本月最后一天
					if(month == 1||month == 3||month == 5||month == 7||month == 8||month == 10||month == 12) {
						if(day == 31) {
							this.startStatistics(c, sdf);
						}
					}else if(month == 4||month == 6||month == 9||month == 11) {
						if(day == 30) {
							this.startStatistics(c, sdf);
						}
					}else {
						if(day == 29) {
							this.startStatistics(c, sdf);
						}
					}
				}else {
					if(month == 1||month == 3||month == 5||month == 7||month == 8||month == 10||month == 12) {
						if(day == 31) {
							this.startStatistics(c, sdf);
						}
					}else if(month == 4||month == 6||month == 9||month == 11) {
						if(day == 30) {
							this.startStatistics(c, sdf);
						}
					}else {
						if(day == 28) {
							this.startStatistics(c, sdf);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("更新统计数据完毕");
	}
	
	public void startStatistics(Calendar c,SimpleDateFormat sdf) {
		c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String first = sdf.format(c.getTime());
		int count = biz.addStatistics(first);
		System.out.println("时间："+first+"更新条数："+count);
	}
	
}
