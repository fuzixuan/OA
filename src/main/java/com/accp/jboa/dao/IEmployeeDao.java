package com.accp.jboa.dao;

import org.apache.ibatis.annotations.Param;

import com.accp.jboa.vo.EmployeeVo;

/**
 * 
 * @ClassName: IEmployeeDao
 * @Description: 员工类数据访问层 
 * @author: wy
 * @date: 2018年6月24日 下午1:09:18
 * @version: V1.0
 */
public interface IEmployeeDao {
	
	/**
	 * 
	 * @Title: queryEmployeeByIdAndPwd
	 * @Description: 根据员工编号及密码查询员工对象
	 * @param id
	 * @param pwd
	 * @return
	 * EmployeeVo
	 */
	public EmployeeVo queryEmployeeByIdAndPwd(@Param("id")Integer id,@Param("pwd")String pwd);

}
