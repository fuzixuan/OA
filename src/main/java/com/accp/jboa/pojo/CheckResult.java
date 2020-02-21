package com.accp.jboa.pojo;

/**
 * 
 * @ClassName: CheckResult
 * @Description: 审核结果类
 * @author: wy
 * @date: 2018年6月24日 下午1:29:54
 * @version: V1.0
 */
public class CheckResult {
	private Integer resultId;   //结果编号
	private String resultName;  //结果名称

	public Integer getResultId() {
		return resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

	public CheckResult() {
		super();
	}

	public CheckResult(Integer resultId, String resultName) {
		super();
		this.resultId = resultId;
		this.resultName = resultName;
	}

}
