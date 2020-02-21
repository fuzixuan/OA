package com.accp.jboa.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.jboa.pojo.Reimburse;
import com.accp.jboa.pojo.ReimburseDetail;
import com.accp.jboa.pojo.Status;
import com.accp.jboa.vo.EmployeeVo;
import com.accp.jboa.vo.ReimburseVo;

/**
 * 
 * @ClassName: IReimburseDao
 * @Description: 报销类数据访问层
 * @author: wy
 * @date: 2018年6月24日 下午1:10:51
 * @version: V1.0
 */
public interface IReimburseDao {

	/**
	 * 
	 * @Title: queryReimburseVoList
	 * @Description: 查询报销列表
	 * @param emp
	 * @param statusId
	 * @param sTime
	 * @param eTime
	 * @return
	 * List<ReimburseVo>
	 */
	public List<ReimburseVo> queryReimburseVoList(@Param("emp")EmployeeVo emp,@Param("statusId") Integer statusId, @Param("sTime") Date sTime,
			@Param("eTime") Date eTime);
	
	/**
	 * 
	 * @Title: queryAllStatus
	 * @Description: 查询所有状态
	 * @return
	 * List<Status>
	 */
	public List<Status> queryAllStatus();
	
	/**
	 * 
	 * @Title: saveReimburse
	 * @Description: 新增报销记录
	 * @param reim
	 * @return
	 * int
	 */
	public int saveReimburse(@Param("reim")Reimburse reim);
	
	/**
	 * 
	 * @Title: saveReimburseDetails
	 * @Description: 新增报销详情
	 * @param details
	 * @return
	 * int
	 */
	public int saveReimburseDetails(@Param("details")List<ReimburseDetail> details);
	
	/**
	 * 
	 * @Title: deleteReimburse
	 * @Description: 删除报销记录
	 * @param id
	 * @return
	 * int
	 */
	public int deleteReimburse(@Param("id")Integer id);
	
	public int updateReimburse(@Param("reim")Reimburse reim);
	
	public ReimburseVo queryReimburseVoById(@Param("id")Integer id);
	
	public List<ReimburseDetail> queryReimburseDetails(@Param("id")Integer id);
	
	public int updateReimburseDetails(@Param("details")List<ReimburseDetail> details);
	
	public int deleteReimburseDetails(@Param("id")Integer id);

}
