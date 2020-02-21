package com.accp.jboa.pojo;

public class CountInfo {

	private Integer countId;  //统计编号 
	private Integer money;  //金额
	private Integer year;  //年份 
	private Integer month;  //月份
	private Integer departmentId;   //部门编号
	private String departmentName;  //部门名
	private Integer employeeId;  //员工编号
	private String employeeName;   //员工名

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getCountId() {
		return countId;
	}

	public void setCountId(Integer countId) {
		this.countId = countId;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public CountInfo() {
		super();
	}

	public CountInfo(Integer countId, Integer money, Integer year, Integer month, Integer departmentId,
			String departmentName, Integer employeeId, String employeeName) {
		super();
		this.countId = countId;
		this.money = money;
		this.year = year;
		this.month = month;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
	}

}
