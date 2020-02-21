package com.accp.jboa.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: Reimburse
 * @Description: 报销实体类
 * @author: wy
 * @date: 2018年6月24日 下午1:14:29
 * @version: V1.0
 */
public class Reimburse {

	private Integer reimburseId;   //报销编号
	private Integer typeId;   //业务类型固定=2
	private Integer createMan;   //创建人
	private Date createTime;  //创建时间
	private Integer departmentId;  //部门编号
	private Integer nextDealMan;   //下个处理人
	private String event;  //事由
	private Float totalCount;  //总金额
	private Integer statusId;  //状态编号
	private List<ReimburseDetail> details = new ArrayList<ReimburseDetail>();
	
	

	public List<ReimburseDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ReimburseDetail> details) {
		this.details = details;
	}

	public Integer getReimburseId() {
		return reimburseId;
	}

	public void setReimburseId(Integer reimburseId) {
		this.reimburseId = reimburseId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getCreateMan() {
		return createMan;
	}

	public void setCreateMan(Integer createMan) {
		this.createMan = createMan;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getNextDealMan() {
		return nextDealMan;
	}

	public void setNextDealMan(Integer nextDealMan) {
		this.nextDealMan = nextDealMan;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Float getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Float totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Reimburse() {
		super();
	}

	public Reimburse(Integer reimburseId, Integer typeId, Integer createMan, Date createTime, Integer departmentId,
			Integer nextDealMan, String event, Float totalCount, Integer statusId) {
		super();
		this.reimburseId = reimburseId;
		this.typeId = typeId;
		this.createMan = createMan;
		this.createTime = createTime;
		this.departmentId = departmentId;
		this.nextDealMan = nextDealMan;
		this.event = event;
		this.totalCount = totalCount;
		this.statusId = statusId;
	}

}
