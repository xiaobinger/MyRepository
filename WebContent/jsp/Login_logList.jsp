<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>loginLogList.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="../jquery/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery/themes/icon.css">
<script type="text/javascript" src="../jquery/jquery.min.js"></script>
<script type="text/javascript" src="../jquery/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../jquery/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="../jquery/mydatavail.js"></script>
<script type="text/javascript">
	$(function() {
		$("#seach-button").click(function(){
			$("#dg").datagrid('load',{
				loginName:$("#loginName").val(),
				loginTime:$("#loginTime").datebox("getValue")
			});
		});
		 $("#print-button").click(function(){
			 $.post("../loginLogInfoController/exportExcel.do",function(flag){
				 if(flag){
					 $.messager.alert('消息提示','生成登录日志报表成功，请在桌面查看','info');
				 }else{
					 $.messager.alert('消息提示','生成订单报表失败','error'); 
				 }
			 });
		 });
		$("#remove-button").click(function(){
			if($("#dg").datagrid("getSelections").length==0){
				$.messager.alert('消息提示','请选择要删除的数据','info');
			}else{
				var arrayId=[];
				var ids=$("#dg").datagrid("getSelections");
				for(var i in ids){
					arrayId.push(parseInt(ids[i].loginId));
				}
				$.messager.confirm('Confirm', '确定删除此条记录吗', function(res){
				if (res){
				$.post("../loginLogInfoController/delLoginLogInfo.do",{"id[]":arrayId},function(data){
					if(data=="true"){
						
								$.messager.show({
									title:'消息提示',
									msg:'该权限已删除成功！请重新给拥有该权限的角色分配权限！',
									timeout:5000,
									showType:'slide'
								});
								$("#dg").datagrid("reload");
							}else{
								$.messager.show({
									title:'消息提示',
									msg:'删除失败！',
									timeout:5000,
									showType:'slide'
								});
							}
						});
						
					}else{
						$("#dg").datagrid("unselectAll");
					}
				});
			}
		});

		$("#dg").datagrid({
			url : '../loginLogInfoController/findLoginLogWithPage.do',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			pagePosition : 'both',
			toolbar : '#tb',
			columns : [ [ {
				checkbox : true,
				title : "登录日志ID",
				field : "loginId",
				width : 80,
				align : "left"
			}, {
				title : "登录名称",
				field : "loginName",
				width : 80,
				align : "center"
			}, {
				title : "登录Ip",
				field : "loginIp",
				width : 80,
				align : "center"
			}, {
				title : "登陆时间",
				field : "loginTime",
				width : 80,
				align : "center"
			},{
				title : "登出时间",
				field : "logoutTime",
				width : 80,
				align : "center"
			}] ]
		});

	});
</script>
</head>
<body>
	<div id="tb">

		<table>
			<tr>

				<td><label>登录名称</label></td>
				<td><input type="text" name="loginName" id="loginName"></td>
				<td><label>登陆时间</label></td>
				<td><input class="easyui-datebox" name="loginTime" id="loginTime" style="width:150px">  
				</td>
				<td><a href="#" id="seach-button" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true">查询</a></td>
			</tr>


		</table>
		 <a href="#"
			id="print-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-print',plain:true">导出</a> <a href="#"
			id="remove-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>
	<div id="dg"></div>
</body>
</html>