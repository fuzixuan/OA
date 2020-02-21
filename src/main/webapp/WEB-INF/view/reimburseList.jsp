<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="/jboa/">
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<link href="js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="action  divaction">
		<div class="t">报销单列表</div>
		<div class="pages">
			<div class="forms">
				<form id="myForm" name="queryForm"
					action="../jboa/page/findList?pageNum=${PAGE.pageNum }&pageSize=${PAGE.pageSize }"
					method="get">
					<label>报销单状态</label> <select name="statusId"
						id="statusId">
						<option value="0">全部</option>
						<c:forEach items="${STATUS }" var="temp">
							<c:choose>
								<c:when test="${temp.statusId == statusId }">
									<option selected value="${temp.statusId }">${temp.statusName }</option>
								</c:when>
								<c:otherwise>
									<option value="${temp.statusId }">${temp.statusName }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> <label for="time">开始时间</label> <input type="text" name="sTime"
						value="${sTime }" id="startDate" class="nwinput"> <label
						for="end-time">结束时间</label> <input type="text" name="eTime"
						value="${eTime }" id="endDate" class="nwinput"> <!-- <input
						type="hidden" name="pageNum" value="1"> <input
						type="hidden" name="pageSize" value="2"> --> <input
						type="submit" id="claimVoucher_searchClaimVoucher_action_0"
						value="查询" class="submit_01">
				</form>
			</div>
			<!--增加报销单 区域 开始-->
			<form id="claimVoucher_searchClaimVoucher_action"
				name="claimVoucherForm"
				action="jsp/claim/claimVoucher_searchClaimVoucher.action"
				method="post">
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="list items">
					<thead>
						<tr class="even">
							<td>编号</td>
							<td>填报日期</td>
							<td>填报人</td>
							<td>总金额</td>
							<td>状态</td>
							<td>待处理人</td>
							<td width=20%>操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${PAGE.list }" var="temp">
							<tr>
							<td><a href="javascript:void(0)">${temp.reimburseId }</a></td>
							<td>
								<fmt:formatDate value="${temp.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>${temp.createManName }</td>
							<td>${temp.totalCount }</td>
							<td>${temp.statusName }</td>
							<td>${temp.nextDealManName }</td>
							<td>
								<c:if test="${temp.statusId == 1 }">
									<a href="/jboa/page/submit?reimburseId=${temp.reimburseId }" onclick="return confirm('确定要提交么？(提交后无法修改)')">
										<img src="images/save.gif" title="提交" width="16" height="16">
									</a>
									<a href="/jboa/page/toUpdatePage?reimburseId=${temp.reimburseId }">
										<img src="images/edit.gif" title="编辑" width="16" height="16">
									</a> 
									<a onclick="return confirm('确定要删除吗？')" href="/jboa/page/delete?reimburseId=${temp.reimburseId }"> 
										<img src="images/delete.gif" title="删除" width="16" height="16">
									</a>
								</c:if>
								<c:if test="${temp.statusId == 6 }">
									<c:if test="${temp.createMan == EMP.employeeId }">
										<a href="/jboa/page/toUpdatePage?reimburseId=${temp.reimburseId }">
											<img src="images/edit.gif" title="编辑" width="16" height="16">
										</a> 
									</c:if>
									
								</c:if>
								<a target="rightFrame" href="/jboa/page/getDetails?reimburseId=${temp.reimburseId }"> 
									<img src="images/search.gif" title="查看详情" width="16" height="15">
								</a> 
								<c:if test="${temp.nextDealMan == EMP.employeeId }">
									<c:if test="${temp.statusId != 6 }">
										<a target="rightFrame" href="/jboa/page/toCheckPage?reimburseId=${temp.reimburseId }"> 
											<img src="images/sub.gif" title="审批" width="16" height="16">
										</a>
									</c:if>
								</c:if>
							</td>
						</tr>
						</c:forEach>
					
						

						
					</tbody>
					<tr>
						<td colspan="7" align="center">
							<div class="page-bar">

								<a href="/jboa/page/findList?statusId=${statusId }&sTime=${sTime}&eTime=${eTime}&pageNum=1&pageSize=${PAGE.pageSize}">首页</a>
								&nbsp;&nbsp;
								<c:if test="${PAGE.isHasPreviousPage()}">
									<a href="/jboa/page/findList?statusId=${statusId }&sTime=${sTime}&eTime=${eTime}&pageNum=${PAGE.prePage}&pageSize=${PAGE.pageSize}">上一页</a>
								</c:if>
								&nbsp;&nbsp;
								<c:if test="${PAGE.isHasNextPage()}">
									<a href="/jboa/page/findList?statusId=${statusId }&sTime=${sTime}&eTime=${eTime}&pageNum=${PAGE.nextPage}&pageSize=${PAGE.pageSize}">下一页</a>
								</c:if>
								&nbsp;&nbsp; <a href="/jboa/page/findList?statusId=${statusId }&sTime=${sTime}&eTime=${eTime}&pageNum=${PAGE.lastPage}&pageSize=${PAGE.pageSize}">尾页</a>
								

								&nbsp;&nbsp; &nbsp;&nbsp;第 ${PAGE.pageNum}页/共${PAGE.lastPage}页&nbsp;&nbsp;共${PAGE.total}条记录
							
							</div>
						</td>
					</tr>
				</table>
			</form>
			<!--增加报销单 区域 结束-->
		</div>
	</div>
</body>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
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
</html>