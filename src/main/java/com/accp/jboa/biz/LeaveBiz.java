package com.accp.jboa.biz;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.jboa.dao.ILeaveDao;
import com.accp.jboa.pojo.LeaveInfo;
import com.accp.jboa.vo.EmployeeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: LeaveBiz
 * @Description: 请假业务层
 * @author: wy
 * @date: 2018年6月24日 下午1:27:57
 * @version: V1.0
 */
@Service
public class LeaveBiz {

	@Autowired
	private ILeaveDao dao;
	
	/**
	 * 
	 * @Title: addLeaveInfo
	 * @Description: 新增请假记录
	 * @param leave
	 * @return
	 * int
	 */
	public int addLeaveInfo(LeaveInfo leave) {
		return dao.saveLeaveInfo(leave);
	};
	
	/**
	 * 
	 * @Title: modifyLeaveInfo
	 * @Description: 修改请假 
	 * @param leave
	 * @return
	 * int
	 */
	public int modifyLeaveInfo(LeaveInfo leave) {
		return dao.updateLeaveInfo(leave);
	};
	
	/**
	 * 
	 * @Title: getLeaveInfo
	 * @Description: 获得请假信息
	 * @param id
	 * @return
	 * LeaveInfo
	 */
	public LeaveInfo getLeaveInfo(Integer id) {
		return dao.queryLeaveInfo(id);
	};
	
	public PageInfo<LeaveInfo> findLeaveInfoList(EmployeeVo emp, Date sTime, Date eTime,Integer pageNum,Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<LeaveInfo>(dao.queryLeaveInfoList(emp, sTime, eTime));
	};
}
