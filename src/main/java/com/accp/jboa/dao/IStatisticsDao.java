package com.accp.jboa.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.jboa.pojo.Statistics;
import com.accp.jboa.vo.EmployeeVo;

public interface IStatisticsDao {

	/**
	 * @Title: queryStatisticsByYear
	 * @Description: 查看年统计报表
	 * @param emp
	 * @param startYear
	 * @param endYear
	 * @return
	 * List<Statistics>
	 */
	public List<Statistics> queryStatisticsByYear(@Param("emp") EmployeeVo emp, @Param("startYear") Integer startYear,
			@Param("endYear") Integer endYear);

	/**
	 * @Title: queryStatisticsByMonth
	 * @Description: 查看月统计报表
	 * @param year
	 * @param startMonth
	 * @param endMonth
	 * @return
	 * List<Statistics>
	 */
	public List<Statistics> queryStatisticsByMonth(@Param("emp") EmployeeVo emp, @Param("year") Integer year,
			@Param("startMonth") Integer startMonth, @Param("endMonth") Integer endMonth);

	/**
	 * @Title: queryStatistcsMonthDetails
	 * @Description: 查看月统计报表详情
	 * @param emp
	 * @param month
	 * @param departmentId
	 * @return
	 * List<Statistics>
	 */
	public List<Statistics> queryStatistcsMonthDetails(@Param("emp") EmployeeVo emp,@Param("month") Integer month,@Param("departmentId")Integer departmentId);
	
	/**
	 * @Title: queryStatistcsYearDetails
	 * @Description: 查看年统计报表详情
	 * @param month
	 * @param departmentId
	 * @return
	 * List<Statistics>
	 */
	public List<Statistics> queryStatistcsYearDetails(@Param("emp") EmployeeVo emp,@Param("year") Integer year);
	
	
	public int saveStatistics(@Param("currTime")String currTime);
}
