package com.accp.jboa.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accp.jboa.biz.EmployeeBiz;
import com.accp.jboa.pojo.Employee;
import com.accp.jboa.vo.EmployeeVo;

@Controller
public class EmployeeAction {

	@Autowired
	private EmployeeBiz biz;
	
	/**
	 * 
	 * @Title: loginIn
	 * @Description: 登录
	 * @param employee
	 * @param session
	 * @return
	 * String
	 */
	@RequestMapping(value="loginin",method=RequestMethod.GET)
	public String loginIn(Employee employee,HttpSession session) {
		EmployeeVo emp = biz.getEmployeeByIdAndPwd(employee.getEmployeeId(), employee.getPassword());
		if(emp != null) {
			session.setAttribute("EMP", emp);
			return "index";	
		}else {
			return "redirect:/login.jsp";
		}
	}
	
	/**
	 * 
	 * @Title: loginOut
	 * @Description: 登出
	 * @param session
	 * @return
	 * String
	 */
	@RequestMapping(value="loginout",method=RequestMethod.GET)
	public String loginOut(HttpSession session) {
		session.removeAttribute("EMP");
		return "redirect:/login.jsp";
	}
}
