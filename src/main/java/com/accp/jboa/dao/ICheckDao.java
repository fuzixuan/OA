package com.accp.jboa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.jboa.pojo.CheckInfo;

/**
 * 
 * @ClassName: ICheckDao
 * @Description: 检查记录
 * @author: wy
 * @date: 2018年6月24日 下午1:08:30
 * @version: V1.0
 */
public interface ICheckDao {
	
	/**
	 * 
	 * @Title: saveCheck
	 * @Description: 新增审核记录
	 * @param check
	 * @return
	 * int
	 */
	public int saveCheck(@Param("check")CheckInfo check);
	
	/**
	 * 
	 * @Title: queryCheckList
	 * @Description: 查询审核记录 
	 * @param bizId
	 * @param typeId
	 * @return
	 * List<CheckInfo>
	 */
	public List<CheckInfo> queryCheckList(@Param("bizId")Integer bizId,@Param("typeId")Integer typeId);

	/**
	 * 
	 * @Title: queryLastChecker
	 * @Description: 查询最后一条审核记录
	 * @param bizId
	 * @param typeId
	 * @return
	 * CheckInfo
	 */
	public CheckInfo queryLastChecker(@Param("bizId")Integer bizId,@Param("typeId")Integer typeId);
}
