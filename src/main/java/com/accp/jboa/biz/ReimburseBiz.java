package com.accp.jboa.biz;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.jboa.dao.IReimburseDao;
import com.accp.jboa.pojo.Employee;
import com.accp.jboa.pojo.Reimburse;
import com.accp.jboa.pojo.ReimburseDetail;
import com.accp.jboa.pojo.Status;
import com.accp.jboa.vo.EmployeeVo;
import com.accp.jboa.vo.ReimburseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ReimburseBiz {

	@Autowired
	private IReimburseDao dao;

	public PageInfo<ReimburseVo> findReimburseVoList(EmployeeVo emp,Integer statusId, Date sTime, Date eTime, Integer pageNum,
			Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<ReimburseVo>(dao.queryReimburseVoList(emp,statusId, sTime, eTime));
	};
	
	public List<Status> findAllStatus(){
		return dao.queryAllStatus();
	};
	
	public int addReimburse(Reimburse reim) {
		return dao.saveReimburse(reim);
	}
	
	public int addReimburseDetails(List<ReimburseDetail> details) {
		return dao.saveReimburseDetails(details);
	};
	
	public int removeReimburse(Integer id) {
		return dao.deleteReimburse(id);
	};
	
	public int modifyReimburse(Reimburse reim) {
		return dao.updateReimburse(reim);
	};
	
	public ReimburseVo getReimburseVoById(Integer id) {
		return dao.queryReimburseVoById(id);
	};
	
	public List<ReimburseDetail> findReimburseDetails(Integer id){
		return dao.queryReimburseDetails(id);
	};
	
	public int modifyReimburseDetails(List<ReimburseDetail> details) {
		return dao.updateReimburseDetails(details);
	};
	
	public int removeReimburseDetails(Integer id) {
		return dao.deleteReimburseDetails(id);
	};

}
