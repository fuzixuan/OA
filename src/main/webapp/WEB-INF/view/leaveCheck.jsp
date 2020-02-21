<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="/jboa/">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>北大青鸟办公自动化管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div class="action  divaction">
		<div class="t">审批请假</div>
		<div class="pages">
			<!--增加报销单 区域 开始-->
			<table width="90%" border="0" cellspacing="0" cellpadding="0"
				class="addform-base">
				<caption>基本信息</caption>
				<tbody>
					<tr>
						<td width="36%">姓名：${LEAVE.createManName }</td>
						<td width="64%">部门：${LEAVE.departmentName }</td>
					</tr>
					<tr>
						<td>开始时间：<fmt:formatDate value="${LEAVE.startTime }" pattern="yyyy-MM-dd"/></td>
						<td>结束时间：<fmt:formatDate value="${LEAVE.endTime }" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<td>请假天数：${LEAVE.totalCount }</td>
						<td>请假事由：${LEAVE.event }</td>
					</tr>
					<tr>
						<td>审批状态：${LEAVE.statusName }</td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<p>
				-------------------------------------------------------------------------------
			</p>
			<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="addform-base" style="margin: 20px 0;">
				<thead>
					<c:if test="${CHECK.size()>0 }">
						<tr>
							<td width="15%">审查人</td>
							<td width="40%">审查意见</td>
							<td width="30%">审查时间</td>
							<td width="15%">审查结果</td>
						</tr>
					</c:if>
				</thead>
				<tbody>
					<c:forEach items="${CHECK }" var="temp">
						<tr>
							<td>${temp.checkManName }</td>
							<td>${temp.checkComment }</td>
							<td>
								<fmt:formatDate value="${temp.checkTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td style="font-weight: bold;">${temp.resultName }</td>		
						</tr>		
					</c:forEach>
				</tbody>	
			</table>
			
			<form id="leave_checkLeave_action" name="leaveForm"
				action="../jboa/leave/checkLeave" method="post">
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="addform-base">
					<tbody>
						<tr>
							<td>审批意见：</td>
						</tr>
						<tr>
							<td><textarea name="checkComment" id="textarea"
									cols="45" rows="5"></textarea></td>
						</tr>
						<!--表单提交行-->
						<tr>
							<td colspan="4" class="submit">
								<c:choose>
									<c:when test="${EMP.positionId == 4 }">
										<input type="button" name="button" id="button" value="存档" class="submit_01"
										onclick="checkLeave('存档')"> 
									</c:when>
									<c:otherwise>
										<input type="button" name="button" id="button" value="审批通过" class="submit_01"
										onclick="checkLeave('同意')"> 
										<input type="button" name="button" id="button" value="审批拒绝" class="submit_01" onclick="checkLeave('拒绝')">
									</c:otherwise>
								</c:choose>
								<input type="hidden" name="leaveId" value="${LEAVE.leaveId }" id="leaveId"> 
								<input type="hidden" name="statusId" value="" id="status">
							</td>
						</tr>
						<tr>
							<td>
								<p>&nbsp;
								</p>
								<p>
									<input type="button" value="返回" onclick="window.history.go(-1)" class="submit_01">
								</p>	
							</td>
						</tr>

					</tbody>
				</table>
			</form>




			<!--增加报销单 区域 结束-->
		</div>
	</div>

</body>
<script type="text/javascript">
	function checkLeave(status){
   		if(!confirm('确定'+status+'请假吗')) return;
   		if(status != "拒绝"){
   			document.leaveForm.status.value = 1;
   		}else{
   			document.leaveForm.status.value = 2;
   		}
   		document.leaveForm.submit();
   		
   	}
</script>
</html>