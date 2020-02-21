package com.accp.jboa.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.accp.jboa.biz.CheckBiz;
import com.accp.jboa.biz.ReimburseBiz;
import com.accp.jboa.pojo.CheckInfo;
import com.accp.jboa.pojo.Employee;
import com.accp.jboa.pojo.Reimburse;
import com.accp.jboa.pojo.ReimburseDetail;
import com.accp.jboa.util.DateFormatUtil;
import com.accp.jboa.vo.EmployeeVo;
import com.accp.jboa.vo.ReimburseVo;
import com.github.pagehelper.PageInfo;


@Controller
@RequestMapping("/page")
public class ReimburseAction {
	@Autowired
	private ReimburseBiz biz;
	@Autowired
	private CheckBiz checkBiz;
	
	/**
	 * 
	 * @Title: findList
	 * @Description: 转到报销列表
	 * @param session
	 * @param model
	 * @param statusId
	 * @param sTime
	 * @param eTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * String
	 */
	@RequestMapping(value="findList",method=RequestMethod.GET)
	public String findList(HttpSession session,Model model,Integer statusId, String sTime, String eTime, Integer pageNum,
			Integer pageSize) {
		if(statusId != null && statusId == 0) {
			statusId = null;
		}
		if(pageNum == null) {
			pageNum = 1;
		}
		if(pageSize == null) {
			pageSize = 10;
		}
		EmployeeVo emp = (EmployeeVo)session.getAttribute("EMP");
		PageInfo<ReimburseVo> page = biz.findReimburseVoList(emp,statusId, DateFormatUtil.formatStringToDate(sTime,"yyyy-MM-dd"), DateFormatUtil.formatStringToDate(eTime,"yyyy-MM-dd"), pageNum, pageSize);
		model.addAttribute("statusId", statusId);
		model.addAttribute("sTime", sTime);
		model.addAttribute("eTime", eTime);
		model.addAttribute("STATUS", biz.findAllStatus());
		model.addAttribute("PAGE", page);
		return "reimburseList";
	}
	
	//跳转新增页面
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAddPage() {
		return "addReimburse";
	}
	
	/**
	 * @Title: addReim
	 * @Description: 新增报销单
	 * @param session
	 * @param statusId
	 * @param totalCount
	 * @param event
	 * @param shixiang
	 * @param subTotal
	 * @param imgFile
	 * @return
	 * @throws Exception
	 * String
	 */
	@RequestMapping(value="addReim",method=RequestMethod.POST)
	public String addReim(HttpSession session,Integer statusId,Float totalCount,String event,String[] shixiang,Float[] subTotal,MultipartFile[] imgFile) throws Exception {
		String dirRealPath = session.getServletContext().getRealPath("/images");//服务器真实地址
		String fName = "";
		EmployeeVo emp = (EmployeeVo)session.getAttribute("EMP");
		Integer nextDealMan = 0;
		if(emp.getpId() == 0) {  //总经理
			nextDealMan = 1001;
		}else {
			nextDealMan = emp.getpId();
		}
		if(statusId == 1) {
			nextDealMan = 10000;
		}
		Reimburse reim = new Reimburse(null, null, emp.getEmployeeId(), new Date(), emp.getDepartmentId(), nextDealMan, event, totalCount, statusId);
		int count = biz.addReimburse(reim);
		if(count <= 0) {
			return "addReimburse";
		}
		List<ReimburseDetail> details = new ArrayList<ReimburseDetail>();
		for (int i=0;i<shixiang.length;i++) {
			fName = imgFile[i].getOriginalFilename();
			fName = UUID.randomUUID().toString().replace("-", "") + fName.substring(fName.lastIndexOf("."));
			details.add(new ReimburseDetail(null, reim.getReimburseId(), subTotal[i], shixiang[i], fName, dirRealPath+File.separator+fName));
			imgFile[i].transferTo(new File(dirRealPath+File.separator+fName));// 保存
		}
		count = biz.addReimburseDetails(details);
		return "redirect:/page/findList";
	}
	
	/**
	 * @Title: updateStatus
	 * @Description: 审批操作
	 * @param checkComment
	 * @param reimburseId
	 * @param statusId
	 * @param session
	 * @return
	 * String
	 */
	@RequestMapping(value="updateStatus",method=RequestMethod.POST)
	public String updateStatus(String checkComment,Integer resultId,Integer reimburseId,HttpSession session) {
		ReimburseVo oldReim = biz.getReimburseVoById(reimburseId);  //获取报销记录
		EmployeeVo emp = (EmployeeVo)session.getAttribute("EMP");  //获取当前登录人信息
		Integer nextDealMan = 10000; //保存下一个处理人
		Integer statusId = 3;
		if(emp.getPositionId() == 1) {  //经理审批
			if(oldReim.getTotalCount()>5000) {
				nextDealMan = 1000;
			}else {
				nextDealMan = 1001;
			}
		}else if(emp.getPositionId() == 0) {  //总经理审批
			nextDealMan = 1001;
		}else if(emp.getPositionId() == 3) {   //财务审批
			nextDealMan = 1002;
			statusId = 4;
		}else {    //出纳付款
			nextDealMan = 10000;
			statusId = 5;
		}
		if(resultId == 3) {    //打回时待处理人变为报销申请人，状态改为已打回
			nextDealMan = oldReim.getCreateMan();
			statusId = 6;
		}else if(resultId == 2) {    //拒绝时待处理人改为空，状态改为已审批
			nextDealMan = 10000;
			statusId = 4;
		}
		//通过不做其他改变

		CheckInfo check = new CheckInfo(null, reimburseId, 2, new Date(), emp.getEmployeeId(), resultId, checkComment);
		checkBiz.addCheck(check);  //添加审批记录
		
		Reimburse newReim = new Reimburse();
		newReim.setStatusId(statusId);
		newReim.setNextDealMan(nextDealMan);
		newReim.setReimburseId(reimburseId);
		biz.modifyReimburse(newReim);  //改变报销申记录状态
		return "redirect:/page/findList";
		
	}
	
	
	/**
	 * @Title: submit
	 * @Description: 提交操作
	 * @param reimburseId
	 * @param session
	 * @return
	 * String
	 */
	@RequestMapping(value="submit",method=RequestMethod.GET)
	public String submit(Integer reimburseId,HttpSession session) {
		EmployeeVo emp = (EmployeeVo)session.getAttribute("EMP");//获取当前登录人信息
		ReimburseVo oldReim = biz.getReimburseVoById(reimburseId);//获取报销记录
		Integer nextDealMan = 10000; //保存下一个处理人
		if(emp.getPositionId() == 2) {  //普通员工
			nextDealMan = emp.getpId();
		}else if(emp.getPositionId() == 1) {  //部门经理
			if(oldReim.getTotalCount()>5000) {
				nextDealMan = 1000;
			}else {
				nextDealMan = 1001;
			}
		}else if(emp.getPositionId() == 0) {  //总经理
			nextDealMan = 1001;
		}else if(emp.getPositionId() == 3) {  //财务
			nextDealMan = 1000;
		}else {  //出纳
			nextDealMan = 1000;
		}
		Integer statusId = 2;
		Reimburse reim = new Reimburse(reimburseId, null, null, null, null, nextDealMan, null, null, statusId);
		biz.modifyReimburse(reim);
		return "redirect:/page/findList";
	}
	
	/**
	 * @Title: modifyReim
	 * @Description: 修改本人报销单操作
	 * @return
	 * String
	 * @throws Exception 
	 */
	@RequestMapping(value="modifyReim",method=RequestMethod.POST)
	public String modifyReim(Integer[] detailsId,String[] opr,Integer reimburseId,Integer statusId,HttpSession session,String event,
			Float totalCount,String[] shixiang,Float[] subTotal,MultipartFile[] imgFile) throws Exception {
		String dirRealPath = session.getServletContext().getRealPath("/images");//服务器真实地址
		String fName = "";  //保存文件名
		EmployeeVo emp = (EmployeeVo)session.getAttribute("EMP");  //操作人信息
		Integer nextDealMan = 0;
		if(statusId == 1) {  //新建状态
			nextDealMan = emp.getpId();
		}else {
			if(checkBiz.getLastChecker(reimburseId, 2) != null) {
				nextDealMan = checkBiz.getLastChecker(reimburseId, 2).getCheckMan();
			}else {
				nextDealMan = emp.getpId();
			}
		}
		if(emp.getpId() == 0) {  //总经理
			nextDealMan = 1001;
		}
		Reimburse reim = new Reimburse(reimburseId, null, null, null, null, nextDealMan, event, totalCount, statusId);
		List<ReimburseDetail> oldDetails = biz.findReimburseDetails(reimburseId);
		boolean bool = true;
		for(int i= 0 ;i<oldDetails.size();i++) {   //删除操作
			bool = true;
			for(int j= 0 ;j<detailsId.length;j++) {
				if(detailsId[j] == oldDetails.get(i).getId()) {
					 bool = false;
				}
			}
			if(bool) {
				new File(oldDetails.get(i).getPicturePath()).delete();
				biz.removeReimburseDetails(oldDetails.get(i).getId());
			}
		}
		List<ReimburseDetail> addList = new ArrayList<ReimburseDetail>();
		List<ReimburseDetail> updateList = new ArrayList<ReimburseDetail>();
		for (int i=0;i<opr.length;i++) {
			if("add".equals(opr[i])) {
				fName = imgFile[i].getOriginalFilename();
				fName = UUID.randomUUID().toString().replace("-", "") + fName.substring(fName.lastIndexOf("."));
				addList.add(new ReimburseDetail(null, reim.getReimburseId(), subTotal[i], shixiang[i], fName, dirRealPath+File.separator+fName));
				imgFile[i].transferTo(new File(dirRealPath+File.separator+fName));// 保存	
			}else if("upd".equals(opr[i])) {
				fName = imgFile[i].getOriginalFilename();
				fName = UUID.randomUUID().toString().replace("-", "") + fName.substring(fName.lastIndexOf("."));
				updateList.add(new ReimburseDetail(detailsId[i], null, null, null, fName, dirRealPath+File.separator+fName));
				imgFile[i].transferTo(new File(dirRealPath+File.separator+fName));// 保存
			}	
		}
		if(addList.size()>0) {
			biz.addReimburseDetails(addList);   //详表新增
		}
		if(updateList.size()>0) {
			biz.modifyReimburseDetails(updateList);   //详表修改
		}
		biz.modifyReimburse(reim);  //修改主表
		return "redirect:/page/findList";
	}
	
	//跳转更新页面
	@RequestMapping(value="toUpdatePage",method=RequestMethod.GET)
	public String toUpdatePage(Integer reimburseId,Model model) {
		ReimburseVo vo = biz.getReimburseVoById(reimburseId);
		List<ReimburseDetail> details = biz.findReimburseDetails(reimburseId);
		model.addAttribute("REIM", vo);
		model.addAttribute("DETAILS", details);
		return "updateReimburse";
	}
	
	//获取报销详情，跳转详情页面
	@RequestMapping(value="getDetails",method=RequestMethod.GET)
	public String getDetails(Integer reimburseId,Model model) {
		ReimburseVo vo = biz.getReimburseVoById(reimburseId);
		List<ReimburseDetail> details = biz.findReimburseDetails(reimburseId);
		List<CheckInfo> check = checkBiz.findCheckList(reimburseId, 2);
		model.addAttribute("REIM", vo);
		model.addAttribute("DETAILS", details);
		model.addAttribute("CHECK", check);
		return "reimburseDetail";
	}
	
	//删除报销详情记录
	@RequestMapping(value="delete",method=RequestMethod.GET)
	public String delete(Integer reimburseId) {
		biz.removeReimburse(reimburseId);
		return "redirect:/page/findList";
	}
	
	//跳转审核报销页面 
	@RequestMapping(value="toCheckPage",method=RequestMethod.GET)
	public String toCheckPage(Integer reimburseId,Model model) {
		ReimburseVo vo = biz.getReimburseVoById(reimburseId);
		List<ReimburseDetail> details = biz.findReimburseDetails(reimburseId);
		List<CheckInfo> check = checkBiz.findCheckList(reimburseId, 2);
		model.addAttribute("REIM", vo);
		model.addAttribute("DETAILS", details);
		model.addAttribute("CHECK", check);
		return "reimburseCheck";
	}

}
