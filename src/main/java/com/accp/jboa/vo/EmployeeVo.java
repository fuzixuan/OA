package com.accp.jboa.vo;

public class EmployeeVo {

	private Integer employeeId;
	private String employeeName;
	private String password;
	private Integer departmentId;
	private String departmentName;
	private Integer positionId;
	private String positionName;
	private Integer pId;
	private String status;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public String getpositionName() {
		return positionName;
	}

	public void setpositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public EmployeeVo() {
		super();
	}

	public EmployeeVo(Integer employeeId, String employeeName, String password, Integer departmentId,
			String departmentName, Integer positionId, String positionName, Integer pId, String status) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.password = password;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.positionId = positionId;
		this.positionName = positionName;
		this.pId = pId;
		this.status = status;
	}

}
