package com.accp.jboa.pojo;

public class Employee {

	private Integer employeeId;
	private String employeeName;
	private String password;
	private Integer departmentId;
	private Integer positionId;
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

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
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

	public Employee() {
		super();
	}

	public Employee(Integer employeeId, String employeeName, String password, Integer departmentId, Integer positionId,
			Integer pId, String status) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.password = password;
		this.departmentId = departmentId;
		this.positionId = positionId;
		this.pId = pId;
		this.status = status;
	}

}
