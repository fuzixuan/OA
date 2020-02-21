package com.accp.jboa.vo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ReimburseVo {

	private Integer reimburseId;
	private Integer typeId;
	private Integer createMan;
	private String createManName;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private Integer positiontId;
	private String positionName;
	private Integer departmentId;
	private String departmentName;
	private Integer nextDealMan;
	private String nextDealManName;
	private String event;
	private Float totalCount;
	private Integer statusId;
	private String statusName;
	
	

	public Integer getPositiontId() {
		return positiontId;
	}

	public void setPositiontId(Integer positiontId) {
		this.positiontId = positiontId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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

	public String getCreateManName() {
		return createManName;
	}

	public void setCreateManName(String createManName) {
		this.createManName = createManName;
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getNextDealMan() {
		return nextDealMan;
	}

	public void setNextDealMan(Integer nextDealMan) {
		this.nextDealMan = nextDealMan;
	}

	public String getNextDealManName() {
		return nextDealManName;
	}

	public void setNextDealManName(String nextDealManName) {
		this.nextDealManName = nextDealManName;
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

	public ReimburseVo() {
		super();
	}

	public ReimburseVo(Integer reimburseId, Integer createMan, Date createTime, Integer departmentId,
			Integer nextDealMan, String event, Float totalCount, Integer statusId) {
		super();
		this.reimburseId = reimburseId;
		this.createMan = createMan;
		this.createTime = createTime;
		this.departmentId = departmentId;
		this.nextDealMan = nextDealMan;
		this.event = event;
		this.totalCount = totalCount;
		this.statusId = statusId;
	}

	

}
