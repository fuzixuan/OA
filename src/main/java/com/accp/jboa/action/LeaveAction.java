package com.accp.jboa.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accp.jboa.biz.CheckBiz;
import com.accp.jboa.biz.LeaveBiz;
import com.accp.jboa.pojo.CheckInfo;
import com.accp.jboa.pojo.LeaveInfo;
import com.accp.jboa.util.DateFormatUtil;
import com.accp.jboa.vo.EmployeeVo;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("leave")
public class LeaveAction {

	@Autowired
	private LeaveBiz biz;
	@Autowired
	private CheckBiz checkBiz;
	
	@RequestMapping(value="toAddPage",method=RequestMethod.GET)
	public String toAddPage() {
		return "addLeave";
	}
	
	/**
	 * 
	 * @Title: addLeave
	 * @Description: 新增请假记录
	 * @param session
	 * @param startDate
	 * @param endDate
	 * @param event
	 * @param totalCount
	 * @return
	 * String
	 */
	@RequestMapping(value="addLeave",method=RequestMethod.POST)
	public String addLeave(HttpSession session,String startDate,String endDate,String event,Integer totalCount) {
		EmployeeVo emp = (EmployeeVo)session.getAttribute("EMP");
		LeaveInfo leave = new LeaveInfo(null, 1, emp.getEmployeeId(), emp.getDepartmentId(), new Date(), 
				DateFormatUtil.formatStringToDate(startDate,"yyyy-MM-dd"), DateFormatUtil.formatStringToDate(endDate,"yyyy-MM-dd"), emp.getpId(), event, totalCount, null);
		biz.addLeaveInfo(leave);
		return "redirect:/leave/findList";
	}
	
	/**
	 * 
	 * @Title: findList
	 * @Description: 转到请假列表
	 * @param model
	 * @param sTime
	 * @param eTime
	 * @param pageNum
	 * @param session
	 * @param pageSize
	 * @return
	 * String
	 */
	@RequestMapping(value="findList",method=RequestMethod.GET)
	public String findList(Model model, String sTime, String eTime,Integer pageNum,HttpSession session,
			Integer pageSize) {
		if(pageNum == null) {
			pageNum = 1;
		}
		if(pageSize == null) {
			pageSize = 10;
		}
		EmployeeVo emp = (EmployeeVo)session.getAttribute("EMP");
		PageInfo<LeaveInfo> page = biz.findLeaveInfoList(emp, DateFormatUtil.formatStringToDate(sTime,"yyyy-MM-dd"), DateFormatUtil.formatStringToDate(eTime,"yyyy-MM-dd"), pageNum, pageSize);
		model.addAttribute("sTime", sTime);
		model.addAttribute("eTime", eTime);
		model.addAttribute("PAGE", page);	
		return "leaveList";
	}
	
	/**
	 * 
	 * @Title: getDetail
	 * @Description: 获取请假详情
	 * @param model
	 * @param leaveId
	 * @return
	 * String
	 */
	@RequestMapping(value="getDetail",method=RequestMethod.GET)
	public String getDetail(Model model,Integer leaveId) {
		LeaveInfo leave = biz.getLeaveInfo(leaveId);
		List<CheckInfo> check = checkBiz.findCheckList(leaveId, 1);
		model.addAttribute("LEAVE", leave);
		model.addAttribute("CHECK", check);
		return "leaveDetail";
	}
	
	@RequestMapping(value="toCheckPage",method=RequestMethod.GET)
	public String toCheckPage(Model model,Integer leaveId) {
		LeaveInfo leave = biz.getLeaveInfo(leaveId);
		List<CheckInfo> check = checkBiz.findCheckList(leaveId, 1);
		model.addAttribute("LEAVE", leave);
		model.addAttribute("CHECK", check);
		
		return "leaveCheck";
	}
	
	/**
	 * 
	 * @Title: checkLeave
	 * @Description: 审核请假
	 * @param session
	 * @param model
	 * @param leaveId
	 * @param statusId
	 * @param checkComment
	 * @return
	 * String
	 */
	@RequestMapping(value="checkLeave",method=RequestMethod.POST)
	public String checkLeave(HttpSession session,Model model,Integer leaveId,Integer statusId,String checkComment) {
		EmployeeVo emp = (EmployeeVo)session.getAttribute("EMP");
		//LeaveInfo oldLeave = biz.getLeaveInfo(leaveId);
		LeaveInfo leave = null;
		Integer nextDealMan = emp.getEmployeeId();
		if(emp.getPositionId() == 4) {  //人事
			leave = new LeaveInfo(leaveId, 10000, 7);
		}else {
			if(statusId == 1) {
				leave = new LeaveInfo(leaveId, 1017, 4);
			}else {
				leave = new LeaveInfo(leaveId, 10000, 4);
			}
		}
		CheckInfo check = new CheckInfo(null, leaveId, 1, new Date(), emp.getEmployeeId(), statusId, checkComment);
		checkBiz.addCheck(check);
		biz.modifyLeaveInfo(leave);
		return "redirect:/leave/findList";
	}
	
}
