package com.accp.jboa.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accp.jboa.dao.IEmployeeDao;
import com.accp.jboa.vo.EmployeeVo;

/**
 * 
 * @ClassName: EmployeeBiz
 * @Description: 用户类业务层
 * @author: wy
 * @date: 2018年6月24日 下午1:26:14
 * @version: V1.0
 */
@Service
public class EmployeeBiz {
	
	@Autowired
	private IEmployeeDao dao;

	/**
	 * 
	 * @Title: getEmployeeByIdAndPwd
	 * @Description: 获取员工信息
	 * @param id
	 * @param pwd
	 * @return
	 * EmployeeVo
	 */
	public EmployeeVo getEmployeeByIdAndPwd(Integer id,String pwd) {
		return dao.queryEmployeeByIdAndPwd(id, pwd);
	};
}
