<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="/jboa/">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function submitSearch(){
		if(document.queryForm.endYear.value < document.queryForm.startYear.value){
			alert("请输入正确的年份区间");
			return false;
		}
		document.queryForm.submit();
		
	}
</script>
</head>
<body>
	<div class="action  divaction">
		<div class="t">年度统计列表</div>
		<div class="pages">
			<form id="compYearStatistics_getList_action" name="queryForm"
				action="../jboa/statistics/yearList?pageNum=${PAGE.pageNum }&pageSize=${PAGE.pageSize }"
				method="get">
				<label for="time">开始年份</label> 
					<select name="startYear"  id="startYear" class="nwinput">
						<option value="0">无</option>
						<c:forEach begin="2013" end="2018" var="i">
							<c:choose>
								<c:when test="${startYear == i }">
									<option selected value="${i }">${i }年</option>
								</c:when>
								<c:otherwise>
									<option value="${i }">${i }年</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				<label for="end-time">结束年份</label> 
				<select name="endYear"  id="endYear" class="nwinput">
					<option value="0">无</option>
					<c:forEach begin="2013" end="2018" var="i">
						<c:choose>
							<c:when test="${endYear == i }">
								<option selected value="${i }">${i }年</option>
							</c:when>
							<c:otherwise>
								<option value="${i }">${i }年</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
 				<input type="button" value="提交" class="submit_01" onclick="submitSearch()">
			</form>
			<table width="90%" border="0" cellspacing="0" cellpadding="0"
				class="list items">
				<thead>
					<tr class="even">
						<td>编号</td>
						<td>总计</td>
						<td>年份</td>
						<!--  <td>月份</td> -->
						<!-- <td>部门</td> -->
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${PAGE.list }" var="temp" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td>￥${temp.money }</td>
							<td>${temp.year }年</td>
							<td>
								<a href="/jboa/statistics/yearDetails?year=${temp.year }" target="rightFrame"> 
									<img src="images/search.gif" width="16" height="15">
								</a>
							</td>
						</tr>
						
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>