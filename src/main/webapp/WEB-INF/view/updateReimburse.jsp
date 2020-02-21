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
<style type="text/css">
	.preview {
	    display: inline-block;
	    width: 40px;
	    height: 40px;
	    position: relative;
	    background-image: url("images/abcc.png");
	    background-repeat: no-repeat;
	    background-size: cover;
	}
	
	.file {
	    width: 40px;
	    height: 40px;
	    opacity: 0;
	    position: absolute;
	    left: 0;
	    top: 0;
	    cursor: pointer;
	    z-index: 5;
	}
</style>
<link href="css/style.css" rel="stylesheet" type="text/css">


</head>
<body>
	<div class="action  divaction">
		<div class="t">报销单更新</div>
		<div class="pages">
			<form id="claimVoucher_updateClaimVoucher_action" name="claimForm" enctype="multipart/form-data"
				action="../jboa/page/modifyReim"
				method="post">
				<input type="hidden" name="reimburseId" value="${REIM.reimburseId }">
				<input type="hidden" name="statusId" value="${REIM.statusId }" id="status">
				<input type="hidden" id="rowNumber" name="rowNumber" value="0">
				<input type="hidden" id="claimId" name="claimVoucher.id" value="133">
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="addform-base">
					<caption>基本信息</caption>
					<tbody>
						<tr>
							<td>编&nbsp;&nbsp;号：${REIM.reimburseId }</td>
							<td>填&nbsp;写&nbsp;人：${REIM.createManName }</td>
							<td>部&nbsp;&nbsp;门：${REIM.departmentName }</td>
							<td>职&nbsp;&nbsp;位：${REIM.positionName }</td>
						</tr>
						<tr>
							<td>总金额： <input type="text" name="totalCount"
								value="${REIM.totalCount }" readonly="readonly" id="totalAccount"></td>
							<td>填报时间：<fmt:formatDate value="${REIM.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>状态：${REIM.statusName }</td>
							<td>待处理人：${REIM.nextDealManName }</td>
						</tr>
						<tr>
							<td colspan="4"><p>-------------------------------------------------------------------------------</p></td>
						</tr>
					</tbody>
				</table>
				<table id="myTable" width="90%" border="0" cellspacing="0"
					cellpadding="0" class="addform-base">
					<thead>
						<tr>
							<td width="30%">项目说明</td>
							<td width="30%">项目金额</td>
							<td width="30%">票据截图</td>
							<td width="10%">操作</td>
						</tr>
					</thead>
					<tbody>
		<c:forEach items="${DETAILS}" var="temp">
			<tr>
				<td>
					<input type="hidden" name="detailsId" value="${temp.id }" id="id0"> 
					<input type="hidden" name="opr" value="no" > 
					<input type="hidden" name="shixiang" value="${temp.desc }"> ${temp.desc }</td>
				<td>
					<input type="hidden" name="subTotal" value="${temp.subTotal }" > ${temp.subTotal }</td>
				<td>
					 <div class="preview" style="background-image: url(images/${temp.pictureName})">
				        <input type="file" name="imgFile" accept="image/*" class="file" value="" />
				    </div>
				</td>
				<td><img src="images/delete.gif" width="16"
					height="16" onclick="delRow(${temp.id },this)"></td>
			</tr>
		</c:forEach>
					</tbody>
				</table>
				
				<table id="detailTable" width="90%" border="0" cellspacing="0"
					cellpadding="0" class="addform-base">
					<tbody>
						<tr>
							<td width="30%">
								<input type="text" id="item">
							</td>
							<td width="30%">
								<input type="text"
								name="claimVoucherDetail.account" id="account">
							</td>
							<td width="30%">
								<div class="preview">
							        <input type="file" name="imgFile" accept="image/*" class="file" value="" />
							    </div>
							</td>
							<td width="10%">
								<img src="images/add.gif" width="16" height="16" id="AddRow">
							</td>
						</tr>
					</tbody>
				</table>
				<table>
					<tbody>
						<tr>
							<td>*事由：</td>
							<td colspan="3">
							<textarea name="event" cols="66" rows="5" id="event">${REIM.event }</textarea>
							</td>
						</tr>
						<tr align="center" colspan="4">
							<td>&nbsp;</td>
							<td>
							<c:if test="${REIM.statusId != 6 }">
								<input type="button" id="1" name="1" value="保存"
								onclick="submitClaimVoucher('保存')" class="submit_01"> 
							</c:if>
							<input type="button" id="2" name="2" value="提交" class="submit_01"
								onclick="submitClaimVoucher('提交')"> 
							<input type="button"
								value="返回" onclick="window.history.go(-1)" class="submit_01">
							</td>
						</tr>
					</tbody>
				</table>
			</form>

		</div>
	</div>


</body>

<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
	$(function(){				
		//表单提交校验

		//$("#myTable tr").eq(0).hide();	
		$("form[name='claimForm']").submit(function(){
			//判断是否加入了问题 
			if($("#rowNumber").val()<1){
				//$.messager.defaults={ok:"确定"};$.messager.alert("提示信息",$("#rowNumber").val());
				$("#notice").text("* 添加报销单的明细，至少一条 ！");
				return false;
			}	
			$("#notice").text("*");
			for(var s=0;s<$("#rowNumber").val();s++){
				$("#account"+s).next(".notice").text("*");
				$("#desc"+s).next(".notice").text("*");
				if(isEmpty($("#account"+s).val())){
					$("#account"+s).next(".notice").text("* 金额不能为空  ！");
					return false;
				}		
				if(isEmpty($("#desc"+s).val())){
					$("#desc"+s).next(".notice").text("* 金额不能为空  ！");
					return false;
				}		
							
			}
			$(".buttons").hide();
			return true;
		});	
		$("#AddRow").click(function(){	
			var totalAccount = $("#totalAccount").val();
			var tr=$("#myTable tr").eq(0).clone();
			++i;
			var j=i-1;
			var item = $("#item").val();
			var account = $("#account").val();
			var imgEle = $(this).parent().prev().children()[0];
			var imgClone = $(imgEle).clone();
			totalAccount=parseFloat(totalAccount)+parseFloat(account);
			var desc = $("#item").val();
			
			var files = $(".file");//[0].files[0];
			var file = files.get(files.length-1).files[0];
			if(!file){
				alert("请选择文件");
				i--;
				return;
			}
			if(item == ""){
				alert("请输入项目说明");
				i--;
				return false;
			}
			if(account == ""){
				alert("请输入项目金额");
				i--;
				return false;
			}else{
				if(isNaN(account)){
					alert("请输入正确的数字");
					i--;
					return false;
				}else{
					if(account<=0){
						alert("请输入正确的数字");
						i--;
						return false;
					}
				}
			}
			
			tr.find("td").get(0).innerHTML="<input type='hidden' name='detailsId' value='0'> <input type='hidden' name='opr' value='add' > <input type='hidden' name='shixiang' value='"+desc+"'>"+desc;
			tr.find("td").get(1).innerHTML="<input  name='subTotal' id=account"+j+"  type=hidden value="+account+" />"+account;
			var td2 = tr.find("td").get(2);
			$(td2).html("").append(imgClone);
			tr.find("td").get(3).innerHTML="<img src=images/delete.gif width=16 height=16 id=DelRow"+j+" onclick=delRow("+j+") />";
			tr.show();
			tr.appendTo("#myTable");
			//传递一共添加多少问题 
			rowNumber=i;
			$("#item").attr("value","");
			$("#account").attr("value","");
			$(imgEle.children[0]).attr("value","");
			$(imgEle).css("background-image","url('images/abcc.png')");
			$("#totalAccount").attr("value",totalAccount);
	
		});	
		
	});
	var i=parseInt(1);
	
	var rowNumber=parseInt(1);
	
	function delRow(id,obj){	
		if(!confirm("确定要删除这条记录吗？")) return;
		var totalCount = $("#totalAccount").val();
		var count = $(obj).parent().prev().prev().children()[0].value;
		totalCount = parseInt(totalCount)-parseInt(count);
		$("#totalAccount").val(totalCount);
		$(obj).parent().parent().remove();
		
	}
	function submitClaimVoucher(action){
	  		if(!confirm("确定"+action+"报销单吗")) return;
	  		if (action == '保存'){
	  			//document.claimForm.status.value = "新创建";
	  		}else{
	  			document.claimForm.status.value = 2;
	  		}
	  		document.claimForm.submit();
	   		
	 }
	
	function fullGoodsPic(){
    	var goodsPic =$(".preview");
  		<%-- var pictrue = <%=pictrue%>;/* 获取图片路径 */
  		for(var i=0;i<goodsPic.length;i++){
  			$(goodsPic[i]).css("backgroundImage","url('"+pictrue[i].path+"')");
  		} --%>
    };
</script>

<script type="text/javascript">
    var preview = $(".preview");
    var eleFile = $(".file");
    var count = 0;
    eleFile.change(function(){
        var file = this.files[0];
        // 确认选择的文件是图片
        for(var i=0;i<eleFile.length;i++) {
            if (eleFile[i] == this) {
            	if($("[name='opr']")[i] != undefined){
            		$("[name='opr']")[i].value="upd";
            	}
                count = i;
                if (file.type.indexOf("image") == 0) {
                    var reader = new FileReader();
                    reader.readAsDataURL(file);
                    reader.onload = function (e) {
                        // 图片base64化
                        var newUrl = this.result;
                        preview[count].style.backgroundImage = "url("+newUrl+")";
                    };
                }
            }
        }
    });
    fullGoodsPic();
</script>
</html>