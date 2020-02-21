package com.accp.jboa.pojo;

import java.util.Date;

/**
 * 
 * @ClassName: CheckInfo
 * @Description: 审核信息类
 * @author: wy
 * @date: 2018年6月24日 下午1:12:17
 * @version: V1.0
 */
public class CheckInfo {

	private Integer checkId;  //审查编号
	private Integer bizId;   //业务编号
	private Integer typeId;  //业务类型
	private Date checkTime;  //审核时间
	private Integer checkMan;  //审核人
	private String checkManName;   //审核人名字
	private Integer checkResult;   //审核结果编号
	private String checkComment;   //审核意见
	private String resultName;   //审核结果名称
	
	
	

	public String getCheckManName() {
		return checkManName;
	}

	public void setCheckManName(String checkManName) {
		this.checkManName = checkManName;
	}

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(Integer checkMan) {
		this.checkMan = checkMan;
	}

	public Integer getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(Integer checkResult) {
		this.checkResult = checkResult;
	}

	public String getCheckComment() {
		return checkComment;
	}

	public void setCheckComment(String checkComment) {
		this.checkComment = checkComment;
	}

	public CheckInfo() {
		super();
	}

	public CheckInfo(Integer checkId, Integer bizId, Integer typeId, Date checkTime, Integer checkMan,
			Integer checkResult, String checkComment) {
		super();
		this.checkId = checkId;
		this.bizId = bizId;
		this.typeId = typeId;
		this.checkTime = checkTime;
		this.checkMan = checkMan;
		this.checkResult = checkResult;
		this.checkComment = checkComment;
	}

}
