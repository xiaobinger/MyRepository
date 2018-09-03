<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.html</title>
<link rel="stylesheet" type="text/css"
	href="jquery/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery/themes/icon.css">
<script type="text/javascript" src="jquery/jquery.min.js"></script>
<script type="text/javascript" src="jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function openTabs(text, url) {
		if($("#tabs").tabs('exists',text)){
			$("#tabs").tabs('select',text);
		}else{
			var myContent = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+ url + "></iframe>";
			$("#tabs").tabs('add',{
				title:text,
				content:myContent,
				closable:true
				
			});
			
		}

	}
	function f1(){
		$.ajax({
 			url:"userInfoController/getNewTime.do",
 			data:null,
			type:"post",
			async:false,
			success:function(data){
				var array=data.split("\"");
				$("#NowTime").html(array[0]);
			}
 		});
	}
	function time(){
		setInterval(f1,1000);
	}
	window.onload=function(){
		time();
	};
</script>
</head>

<body class="easyui-layout">
<%-- <c:catch var="Expection"> --%>
	<div title="航空订票后台管理系统" data-options="region:'north',split:true"
		style="height: 150px;" >
				<h1 style="float: right;">欢迎您:<shiro:principal/><br>
				<a href="userInfoController/logout.do">注销</a>
				&nbsp;&nbsp;&nbsp;<span id="NowTime"></span>
				</h1>
				<div style="float: left;"><img alt="航空订票后台管理系统" src="image/logo.jpg" style="width:100%;height:100%"></div>
				
	</div>
	<div title="导航菜单" data-options="region:'west',split:true"
		style="width: 150px;">
		<div class="easyui-accordion" style="width: 100%"
			data-options="fit:true">
			<shiro:hasRole name="管理员">
		
				<div title="城市信息管理">
					<a href="#" onclick="openTabs('城市管理','jsp/cityInfoList.jsp')"
					class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">城市管理</a><br> 
				</div>
				<div title="航班信息管理">
				<a href="#" onclick="openTabs('航班管理','jsp/airLineList.jsp')"
					class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">航班管理</a><br> 
				</div>
				<div title="客机管理">
					<a href="#" onclick="openTabs('客机信息管理','jsp/planeList.jsp')"
						class="easyui-linkbutton" data-options="plain:true"
						style="width: 100%">客机信息管理</a><br>
				</div>
				<div title="仓位信息管理">
					<a href="#" onclick="openTabs('仓位等级管理','jsp/spaceRankList.jsp')"
						class="easyui-linkbutton" data-options="plain:true"
						style="width: 100%">仓位等级管理</a><br> 
				</div>
				<div title="客户管理">
				<a href="#" onclick="openTabs('客户信息管理','jsp/customerInfo.jsp')"
					class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">客户信息管理</a><br> 
				<a href="#" onclick="openTabs('客户类型管理','jsp/customerTypeList.jsp')"
					class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">客户类型管理</a><br> 
				</div>
				<div title="订单管理">
				<a href="#" onclick="openTabs('订单信息管理','jsp/ticket_BookList.jsp')"
					class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">订单信息管理</a><br>
				</div>
				<div title="系统管理">
				<a href="#" onclick="openTabs('用户管理','jsp/userList.jsp')"  class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">用户管理</a><br>
				<a href="#" onclick="openTabs('角色信息','jsp/rolerList.jsp')"  class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">查看系统角色</a>
				<a href="#" onclick="openTabs('权限信息','jsp/permissionList.jsp')"  class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">查看系统权限</a>
				<a href="#" onclick="openTabs('登录日志管理','jsp/Login_logList.jsp')"  class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">登录日志管理</a>
				<a href="#" onclick="openTabs('操作日志管理','jsp/operationLogList.jsp')"  class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">操作日志管理</a>
				</div>
				</shiro:hasRole>
				<shiro:lacksRole name="管理员">
				<shiro:hasRole name="普通员工">
				<div title="城市信息管理">
					<a href="#" onclick="openTabs('城市管理','jsp/cityInfoList.jsp')"
					class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">城市管理</a><br> 
				</div>
				<div title="航班信息管理">
				<a href="#" onclick="openTabs('航班管理','jsp/airLineList.jsp')"
					class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">航班管理</a><br> 
				</div>
				<div title="客机管理">
					<a href="#" onclick="openTabs('客机信息管理','jsp/planeList.jsp')"
						class="easyui-linkbutton" data-options="plain:true"
						style="width: 100%">客机信息管理</a><br>
				</div>
				<div title="仓位信息管理">
					<a href="#" onclick="openTabs('仓位等级管理','jsp/spaceRankList.jsp')"
						class="easyui-linkbutton" data-options="plain:true"
						style="width: 100%">仓位等级管理</a><br> 
				</div>
				</shiro:hasRole>
				<shiro:hasRole name="客服">
				<div title="客户管理">
				<a href="#" onclick="openTabs('客户信息管理','jsp/customerInfo.jsp')"
					class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">客户信息管理</a><br> 
				<a href="#" onclick="openTabs('客户类型管理','jsp/customerTypeList.jsp')"
					class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">客户类型管理</a><br> 
				</div>
				<div title="订单管理">
				<a href="#" onclick="openTabs('订单信息管理','jsp/ticket_BookList.jsp')"
					class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">订单信息管理</a><br>
				</div>
				
				<div title="系统管理">
				<a href="#" onclick="openTabs('用户管理','jsp/userList.jsp')"  class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">用户管理</a><br>
				</div>
				</shiro:hasRole>
			</shiro:lacksRole>
				<div title="消息会话管理">
				<a href="#" onclick="openTabs('会话管理','jsp/talkInfo.jsp')"
					class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">消息会话管理</a><br>
				</div>
				<div title="公司文件管理">
				<a href="#" onclick="openTabs('文件管理','jsp/fileLoader.jsp')"
					class="easyui-linkbutton" data-options="plain:true"
					style="width: 100%">文件管理</a><br>
				</div>
		</div>
	</div>
	<div title="显示数据" data-options="region:'center',split:true">
		<div id="tabs" class="easyui-tabs" data-options="fit:true">
			<div title="首页">
				<div align="center">
					<h1 style="color:red">消息通知：</h1>
					<c:choose>
					<c:when test="${not empty talkList}">
					<ol>
						<c:forEach items="${talkList}" var="talk">
							
							<li>${talk.talkInfo}(发布人：${talk.userName})
							<shiro:hasRole name="管理员">
								<a href="talkInfoController/delTalkInfo.do?talkId=${talk.talkId}">删除</a>
							</shiro:hasRole>
							</li>
							
						</c:forEach>
					</ol>
					</c:when>
					<c:otherwise>
						<h1>暂未消息发布！</h1>
					</c:otherwise>
					</c:choose>
				</div>
			</div>


		</div>

	</div>
<%-- </c:catch> --%>
<%-- <c:out value="Exception"></c:out> --%>
</body>

</html>