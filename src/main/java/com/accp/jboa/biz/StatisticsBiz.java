package com.accp.jboa.biz;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.accp.jboa.dao.BaseDao;
import com.accp.jboa.dao.IStatisticsDao;
import com.accp.jboa.pojo.Statistics;
import com.accp.jboa.vo.EmployeeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("statisticsBiz")
public class StatisticsBiz {

	@Autowired
	private IStatisticsDao dao;

	public PageInfo<Statistics> findStatisticsByMonth(EmployeeVo emp, Integer year, Integer startMonth,
			Integer endMonth, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Statistics>(dao.queryStatisticsByMonth(emp, year, startMonth, endMonth));
	};

	public PageInfo<Statistics> findStatisticsByYear(EmployeeVo emp, Integer startYear, Integer endYear,
			Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Statistics>(dao.queryStatisticsByYear(emp, startYear, endYear));
	};

	public List<Statistics> findStatistcsMonthDetails(EmployeeVo emp,Integer month, Integer departmentId) {
		return dao.queryStatistcsMonthDetails(emp,month, departmentId);
	};

	public List<Statistics> findStatistcsYearDetails(EmployeeVo emp,Integer year) {
		return dao.queryStatistcsYearDetails(emp,year);
	};
	
	public int addStatistics(String currTime) {
		return dao.saveStatistics(currTime);
	};

}
