package com.accp.jboa.pojo;

import java.util.Date;

public class LeaveInfo {

	private Integer leaveId;
	private Integer typeId;
	private Integer createMan;
	private String createManName;
	private Integer departmentId;
	private String departmentName;
	private Date createTime;
	private Date startTime;
	private Date endTime;
	private Integer nextDealMan;
	private String nextDealManName;
	private String event;
	private Integer totalCount;
	private Integer statusId;
	private String resultId;
	private Date checkTime;
	private String statusName;
	
	
	
	
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultName) {
		this.resultId = resultName;
	}

	public String getCreateManName() {
		return createManName;
	}

	public void setCreateManName(String createManName) {
		this.createManName = createManName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getNextDealManName() {
		return nextDealManName;
	}

	public void setNextDealManName(String nextDealManName) {
		this.nextDealManName = nextDealManName;
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
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

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public LeaveInfo() {
		super();
	}

	public LeaveInfo(Integer leaveId, Integer typeId, Integer createMan, Integer departmentId, Date createTime,
			Date startTime, Date endTime, Integer nextDealMan, String event, Integer totalCount, Integer statusId) {
		super();
		this.leaveId = leaveId;
		this.typeId = typeId;
		this.createMan = createMan;
		this.departmentId = departmentId;
		this.createTime = createTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.nextDealMan = nextDealMan;
		this.event = event;
		this.totalCount = totalCount;
		this.statusId = statusId;
	}

	public LeaveInfo(Integer leaveId, Integer nextDealMan, Integer statusId) {
		super();
		this.leaveId = leaveId;
		this.nextDealMan = nextDealMan;
		this.statusId = statusId;
	}
	
	

}
