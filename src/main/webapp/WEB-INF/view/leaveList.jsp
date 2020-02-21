<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="/jboa/">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<link
	href="http://localhost:8080/jboa/js/My97DatePicker/skin/WdatePicker.css"
	rel="stylesheet" type="text/css">
<script>
	$(function(){
		 //日期选择控件
	 	$("#startDate").click(function(){
			WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}',isShowClear:true, readOnly:true });
		});
		$("#endDate").click(function(){
			WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}',isShowClear:true, readOnly:true });
		});
	});
</script>
</head>
<body>
	<div class="action  divaction">
		<div class="t">请假列表</div>
		<div class="pages">
			<div class="forms">
				<form id="leave_searchLeave_action" name="queryForm"
					action="../jboa/leave/findList"
					method="get">
					<label for="time">开始时间</label> <input type="text" name="sTime"
						value="${sTime }" id="startDate" class="nwinput"> <label
						for="end-time">结束时间</label> <input type="text" name="eTime"
						value="${eTime }" id="endDate" class="nwinput" readonly=""> <input
						type="hidden" name="pageNo" value="1"> <input
						type="hidden" name="pageSize" value="5"> <input
						type="submit" id="leave_searchLeave_action_0" value="查询"
						class="submit_01">

				</form>
			</div>
			<!--增加报销单 区域 开始-->
			<table width="90%" border="0" cellspacing="0" cellpadding="0"
				class="list items">
				<thead>
					<tr class="even">
						<td>编号</td>
						<td>名称</td>
						<td>发起时间</td>
						<td>审批时间</td>
						<td>审批意见</td>
						<td>待处理人</td>
						<td>审批状态</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${PAGE.list }" var="temp">
						<tr>
							<td><a href="javascript:void(0)">${temp.leaveId }</a></td>
							<td>${temp.createManName }请假${temp.totalCount }天</td>
							<td><fmt:formatDate value="${temp.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><fmt:formatDate value="${temp.checkTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>
								<c:if test="${temp.resultId == 1 }">
									通过
								</c:if>
								<c:if test="${temp.resultId == 2 }">
									拒绝
								</c:if>
							</td>
							<td>
								${temp.nextDealManName }
							</td>
							<td>
								${temp.statusName }
							</td>
							<td>
								<a href="/jboa/leave/getDetail?leaveId=${temp.leaveId }">
									<img src="images/search.gif" width="16" height="15">
								</a>
								<c:if test="${temp.nextDealMan == EMP.employeeId }">
									<%-- <c:if test="${temp.resultId == 1 }"> --%>
										<a target="rightFrame" href="/jboa/leave/toCheckPage?leaveId=${temp.leaveId }">
											<img src="images/sub.gif" width="16" height="16">
										</a>
									<%-- </c:if> --%>
								</c:if>
							</td>
						</tr>
					</c:forEach>

					<!-- <tr>
						<td><a href="leave_getLeaveById.action?leave.id=143">143</a></td>
						<td>张平请假1.0天</td>
						<td>2013-09-09 15:10</td>
						<td></td>
						<td></td>
						<td>待审批</td>
						<td>
							<a href="claimVoucher_toUpdate.action?claimVoucher.id=133">
									<img src="images/edit.gif" width="16" height="16">
							</a> 
							<a onclick="delVoucher(133)" href="#"> <img
								src="images/delete.gif" width="16" height="16">
							</a> 
							<a target="rightFrame" href="leaveDetail.jsp"><img
								src="images/search.gif" width="16" height="15"></a>
							<a target="rightFrame" href="leaveCheck.jsp"> <img
								src="images/sub.gif" width="16" height="16"></a></td>
					</tr> -->

					<tr>
						<td colspan="7" align="center">
							<div class="page-bar">

								<a href="/jboa/leave/findList?sTime=${sTime}&eTime=${eTime}&pageNum=1&pageSize=${PAGE.pageSize}">首页</a>
								&nbsp;&nbsp;
								<c:if test="${PAGE.isHasPreviousPage()}">
									<a href="/jboa/leave/findList?sTime=${sTime}&eTime=${eTime}&pageNum=${PAGE.prePage}&pageSize=${PAGE.pageSize}">上一页</a>
								</c:if>
								&nbsp;&nbsp;
								<c:if test="${PAGE.isHasNextPage()}">
									<a href="/jboa/leave/findList?sTime=${sTime}&eTime=${eTime}&pageNum=${PAGE.nextPage}&pageSize=${PAGE.pageSize}">下一页</a>
								</c:if>
								&nbsp;&nbsp; <a href="/jboa/leave/findList?sTime=${sTime}&eTime=${eTime}&pageNum=${PAGE.lastPage}&pageSize=${PAGE.pageSize}">尾页</a>							
								&nbsp;&nbsp; &nbsp;&nbsp;第 ${PAGE.pageNum}页/共${PAGE.lastPage}页&nbsp;&nbsp;共${PAGE.total}条记录
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<!--请假 区域 结束-->
		</div>
	</div>
</body>
</html>