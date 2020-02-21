package com.accp.jboa.pojo;

public class Statistics {

	private Integer countId;
	private Float money;
	private Integer year;
	private Integer month;
	private Integer departmentId;
	private String departmentName;
	private Integer employeeId;
	private String employeeName;

	public Integer getCountId() {
		return countId;
	}

	public void setCountId(Integer countId) {
		this.countId = countId;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

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

	public Statistics() {
		super();
	}

	public Statistics(Integer countId, Float money, Integer year, Integer month, Integer departmentId,
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
