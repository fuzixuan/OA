package com.accp.jboa.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.jboa.pojo.LeaveInfo;
import com.accp.jboa.vo.EmployeeVo;

/**
 * 
 * @ClassName: ILeaveDao
 * @Description: 请假类数据访问层
 * @author: wy
 * @date: 2018年6月24日 下午1:09:56
 * @version: V1.0
 */
public interface ILeaveDao {
	
	/**
	 * 
	 * @Title: saveLeaveInfo
	 * @Description: 新增请假记录
	 * @param leave
	 * @return
	 * int
	 */
	public int saveLeaveInfo(@Param("leave")LeaveInfo leave);
	
	/**
	 * 
	 * @Title: updateLeaveInfo
	 * @Description: 更新请假记录 
	 * @param leave
	 * @return
	 * int
	 */
	public int updateLeaveInfo(@Param("leave")LeaveInfo leave);
	
	/**
	 * 
	 * @Title: queryLeaveInfo
	 * @Description: 查询请假信息
	 * @param id
	 * @return
	 * LeaveInfo
	 */
	public LeaveInfo queryLeaveInfo(@Param("id")Integer id);
	
	/**
	 * 
	 * @Title: queryLeaveInfoList
	 * @Description: 查询请假信息列表 
	 * @param emp
	 * @param sTime
	 * @param eTime
	 * @return
	 * List<LeaveInfo>
	 */
	public List<LeaveInfo> queryLeaveInfoList(@Param("emp")EmployeeVo emp, @Param("sTime") Date sTime,
			@Param("eTime") Date eTime);

}
