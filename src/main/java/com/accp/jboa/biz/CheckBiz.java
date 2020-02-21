package com.accp.jboa.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.jboa.dao.ICheckDao;
import com.accp.jboa.pojo.CheckInfo;

/**
 * 
 * @ClassName: CheckBiz
 * @Description: 审核业务逻辑层
 * @author: wy
 * @date: 2018年6月24日 下午1:23:39
 * @version: V1.0
 */
@Service
public class CheckBiz {

	@Autowired
	private ICheckDao dao;
	
	/**
	 * 
	 * @Title: addCheck
	 * @Description: 新增审核记录
	 * @param check
	 * @return
	 * int
	 */
	public int addCheck(CheckInfo check) {
		return dao.saveCheck(check);
	};
	
	/**
	 * 
	 * @Title: findCheckList
	 * @Description: 查询审核列表
	 * @param bizId
	 * @param typeId
	 * @return
	 * List<CheckInfo>
	 */
	public List<CheckInfo> findCheckList(Integer bizId,Integer typeId){
		return dao.queryCheckList(bizId, typeId);
	};
	
	/**
	 * 
	 * @Title: getLastChecker
	 * @Description: 获取最后一条审核记录 
	 * @param bizId
	 * @param typeId
	 * @return
	 * CheckInfo
	 */
	public CheckInfo getLastChecker(Integer bizId,Integer typeId) {
		return dao.queryLastChecker(bizId, typeId);
	};

}
