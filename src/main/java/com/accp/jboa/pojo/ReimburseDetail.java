package com.accp.jboa.pojo;

/**
 * 
 * @ClassName: ReimburseDetail
 * @Description: 报销详情类
 * @author: wy
 * @date: 2018年6月24日 下午1:13:36
 * @version: V1.0
 */
public class ReimburseDetail {
	private Integer id;   //报销详情编号
	private Integer mainId;  //主业务编号
	private Float subTotal;  //数量
	private String desc;  //事由
	private String pictureName;  //图片名 
	private String picturePath;  //图片真实路径

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public Float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public ReimburseDetail() {
		super();
	}

	public ReimburseDetail(Integer id, Integer mainId, Float subTotal, String desc, String pictureName,
			String picturePath) {
		super();
		this.id = id;
		this.mainId = mainId;
		this.subTotal = subTotal;
		this.desc = desc;
		this.pictureName = pictureName;
		this.picturePath = picturePath;
	}

}
