package com.accp.jboa.action;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accp.jboa.biz.StatisticsBiz;
import com.accp.jboa.pojo.Statistics;
import com.accp.jboa.vo.EmployeeVo;
import com.accp.jboa.vo.StatisticsVo;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Controller
@RequestMapping("statistics")
public class StatisticsAction {
	@Autowired
	private StatisticsBiz biz;
	
	/**
	 * 
	 * @Title: yearList
	 * @Description: 查看年度报表 
	 * @param session
	 * @param pageNum
	 * @param pageSize
	 * @param startYear
	 * @param endYear
	 * @param model
	 * @return
	 * String
	 */
	@RequestMapping(value = "yearList", method = RequestMethod.GET)
	public String yearList(HttpSession session, Integer pageNum, Integer pageSize, Integer startYear,
			Integer endYear, Model model) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		if (startYear != null && startYear == 0) {
			startYear = null;
		}
		if (endYear != null && endYear == 0) {
			endYear = null;
		}
		EmployeeVo emp = (EmployeeVo) session.getAttribute("EMP");
		PageInfo<Statistics> page = biz.findStatisticsByYear(emp, startYear, endYear, pageNum, pageSize);
		model.addAttribute("PAGE", page);
		model.addAttribute("startYear", startYear);
		model.addAttribute("endYear", endYear);
		return "reimburseYear";
	}

	/**
	 * 
	 * @Title: monthList
	 * @Description: 查看月度报表
	 * @param session
	 * @param pageNum
	 * @param pageSize
	 * @param year
	 * @param startMonth
	 * @param endMonth
	 * @param model
	 * @return
	 * String
	 */
	@RequestMapping(value = "monthList", method = RequestMethod.GET)
	public String monthList(HttpSession session, Integer pageNum, Integer pageSize, Integer year, Integer startMonth,
			Integer endMonth, Model model) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		if (year != null && year == 0) {
			year = null;
		}
		if (startMonth != null && startMonth == 0) {
			startMonth = null;
		}
		if (endMonth != null && endMonth == 0) {
			endMonth = null;
		}
		EmployeeVo emp = (EmployeeVo) session.getAttribute("EMP");
		PageInfo<Statistics> page = biz.findStatisticsByMonth(emp, year, startMonth, endMonth, pageNum, pageSize);
		model.addAttribute("PAGE", page);
		model.addAttribute("year", year);
		model.addAttribute("startMonth", startMonth);
		model.addAttribute("endMonth", endMonth);
		return "reimburseMonth";
	}
	
	/**
	 * 
	 * @Title: monthDetails
	 * @Description: 月度报销详情 
	 * @param session
	 * @param departmentId
	 * @param month
	 * @param model
	 * @return
	 * String
	 */
	@RequestMapping(value = "monthDetails", method = RequestMethod.GET)
	public String monthDetails(HttpSession session,Integer departmentId,Integer month,Model model) {
		EmployeeVo emp = (EmployeeVo) session.getAttribute("EMP");
		List<Statistics> list = biz.findStatistcsMonthDetails(emp,month, departmentId);
		Float count = 0f;
		List<StatisticsVo> vo = new ArrayList<StatisticsVo>();
		for (Statistics s : list) {
			count+=s.getMoney();
			if(emp.getPositionId() == 0) {
				vo.add(new StatisticsVo(s.getDepartmentName(),s.getMoney()));
			}else {
				vo.add(new StatisticsVo(s.getEmployeeName(),s.getMoney()));
			}
		}
		model.addAttribute("LIST", list);
		model.addAttribute("DATA", JSON.toJSONString(vo));
		model.addAttribute("COUNT", count);
		return "monthCount";
	}
	
	/**
	 * 
	 * @Title: yearDetails
	 * @Description: 年度报销详情
	 * @param session
	 * @param year
	 * @param model
	 * @return
	 * String
	 */
	@RequestMapping(value = "yearDetails", method = RequestMethod.GET)
	public String yearDetails(HttpSession session,Integer year,Model model) {
		EmployeeVo emp = (EmployeeVo) session.getAttribute("EMP");
		List<Statistics> list = biz.findStatistcsYearDetails(emp,year);
		Float count = 0f;
		List<StatisticsVo> vo = new ArrayList<StatisticsVo>();
		for (Statistics s : list) {
			count += s.getMoney();
			if(emp.getPositionId() == 0) {
				vo.add(new StatisticsVo(s.getDepartmentName(),s.getMoney()));
			}else {
				vo.add(new StatisticsVo(s.getEmployeeName(),s.getMoney()));
			}
		}
		model.addAttribute("LIST", list);
		model.addAttribute("DATA", JSON.toJSONString(vo));
		model.addAttribute("COUNT", count);
		return "yearCount";
	}
	
	/**
	 * 
	 * @Title: monthExcel
	 * @Description: 导出月度报销Excel
	 * @param session
	 * @param year
	 * @param selectMonth
	 * @param model
	 * @return
	 * String
	 */
	@RequestMapping(value = "monthExcel", method = RequestMethod.GET)
	public String monthExcel(HttpSession session,Integer year,Integer selectMonth,Model model) {
		EmployeeVo emp = (EmployeeVo) session.getAttribute("EMP");
		List<Statistics> list = biz.findStatistcsMonthDetails(emp, selectMonth, emp.getDepartmentId());
		List<String[]> data = new ArrayList<String[]>();   //要导出数据的集合
		String fileName = "";    //导出Excel的文件名
		if(emp.getPositionId() == 1) {
			fileName = year+"年"+selectMonth+"月"+emp.getDepartmentName()+"月度报销统计";
			for(int i=0;i<list.size();i++) {
				data.add(new String[] { String.valueOf(i+1),list.get(i).getEmployeeName(),list.get(i).getDepartmentName(),String.valueOf(list.get(i).getYear()),String.valueOf(list.get(i).getMonth()),String.valueOf(list.get(i).getMoney())});
			}
		}else {
			fileName = year+"年"+selectMonth+"月公司月度";
			for(int i=0;i<list.size();i++) {
				data.add(new String[] { String.valueOf(i+1),list.get(i).getDepartmentName(),String.valueOf(list.get(i).getYear()),String.valueOf(list.get(i).getMonth()),String.valueOf(list.get(i).getMoney())});
			}
		}
		this.reprotExcel(data, session, fileName, "monthly");
		return "redirect:/statistics/monthList";
	}
	
	/**
	 * 
	 * @Title: yearExcel
	 * @Description: 导出年度报销Excel
	 * @param session
	 * @param currYear
	 * @param model
	 * @return
	 * String
	 */
	@RequestMapping(value = "yearExcel", method = RequestMethod.GET)
	public String yearExcel(HttpSession session,Integer currYear,Model model) {
		EmployeeVo emp = (EmployeeVo) session.getAttribute("EMP");
		List<Statistics> list = biz.findStatistcsYearDetails(emp, currYear);
		System.out.println("**********"+list.size());
		List<String[]> data = new ArrayList<String[]>();
		String fileName = "";
		if(emp.getPositionId() ==1) {
			fileName = currYear+"年"+emp.getDepartmentName()+"年度报销统计";
			for(int i=0;i<list.size();i++) {
				data.add(new String[] { String.valueOf(i+1),list.get(i).getEmployeeName(),list.get(i).getDepartmentName(),String.valueOf(list.get(i).getYear()),String.valueOf(list.get(i).getMoney())});
			}
		}else {
			fileName = currYear+"年公司年度";
			for(int i=0;i<list.size();i++) {
				data.add(new String[] { String.valueOf(i+1),list.get(i).getDepartmentName(),String.valueOf(list.get(i).getYear()),String.valueOf(list.get(i).getMoney())});
			}
		}
		this.reprotExcel(data, session, fileName, "year");
		return "redirect:/statistics/yearList";
	}
	// 编号、报销人、部门、年份、月份、金额
	
	//导出Excel
	public Integer reprotExcel(List<String[]> pageDataList,HttpSession session,String fileName,String groupBy) {
		String realPath = session.getServletContext().getRealPath("/uploads");
		EmployeeVo emp = (EmployeeVo) session.getAttribute("EMP");
		Integer columnCount = 0;   //列数量
		if(emp.getPositionId() == 1) {  //部门经理
			if("monthly".equals(groupBy)) {
				columnCount = 6;
			}else {
				columnCount = 5;
			}
		}else {   //总经理及财务
			if("monthly".equals(groupBy)) {
				columnCount = 5;
			}else {
				columnCount = 4;
			}
		}
		try {
			WritableWorkbook wbook = Workbook
					.createWorkbook(new FileOutputStream("C:/Users/wy/Desktop"+"/"+fileName + ".xls")); // 建立excel文件
			WritableSheet wsheet = wbook.createSheet("导出数据", 0); // sheet名称
			WritableCellFormat cellFormatNumber = new WritableCellFormat();
			cellFormatNumber.setAlignment(Alignment.RIGHT);

			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK); // 定义格式、字体、粗体、斜体、下划线、颜色
			WritableCellFormat wcf = new WritableCellFormat(wf); // title单元格定义
			WritableCellFormat wcfc = new WritableCellFormat(); // 一般单元格定义
			WritableCellFormat wcfe = new WritableCellFormat(); // 一般单元格定义
			wcf.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			wcfc.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式

			wcf.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			wcfc.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			wcfe.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			wsheet.setColumnView(0, 20);// 设置列宽
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 20);

			int rowIndex = 0;
			int columnIndex = 0;
			if (null != pageDataList) {
				// rowIndex++;
				columnIndex = 0;
				wsheet.setRowView(rowIndex, 500);// 设置标题行高
				wsheet.addCell(new Label(columnIndex++, rowIndex, fileName, wcf));
				wsheet.mergeCells(0, rowIndex,  columnCount-1, rowIndex);// 合并标题所占单元格
				rowIndex++;
				columnIndex = 0;
				wsheet.setRowView(rowIndex, 380);// 设置项目名行高
				wsheet.addCell(new Label(columnIndex++, rowIndex, "编号", wcf));
				if(emp.getPositionId() == 1) {
					wsheet.addCell(new Label(columnIndex++, rowIndex, "报销人", wcf));
				}
				wsheet.addCell(new Label(columnIndex++, rowIndex, "部门", wcf));
				wsheet.addCell(new Label(columnIndex++, rowIndex, "年份", wcf));
				if("monthly".equals(groupBy)) {
					wsheet.addCell(new Label(columnIndex++, rowIndex, "月份", wcf));
				}
				wsheet.addCell(new Label(columnIndex++, rowIndex, "报销金额", wcf));
				// 开始行循环
				for (String[] array : pageDataList) { // 循环列
					rowIndex++;
					columnIndex = 0;
					for(int j=0;j<columnCount;j++) {
						wsheet.addCell(new Label(columnIndex++, rowIndex, array[j],
								wcfe));
					}
				}
				rowIndex++;
				columnIndex = 0;
			}
			wbook.write();
			if (wbook != null) {
				wbook.close();
			}
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

}
