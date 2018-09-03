<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>operationLogList.jsp</title>
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
				operaUserName:$("#operaUserName").val(),
				operaTime:$("#operaTime").datebox("getValue")
			});
		});
		$("#remove-button").click(function(){
			if($("#dg").datagrid("getSelections").length==0){
				$.messager.alert('消息提示','请选择要删除的数据','info');
			}else{
				var arrayId=[];
				var ids=$("#dg").datagrid("getSelections");
				for(var i in ids){
					arrayId.push(parseInt(ids[i].operaId));
				}
				$.messager.confirm('Confirm', '确定删除此条记录吗', function(res){
				if (res){
				$.post("../operationLogInfoController/delOperationLogInfo.do",{"id[]":arrayId},function(data){
					if(data=="true"){
						
								$.messager.show({
									title:'消息提示',
									msg:'删除成功',
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
			url : '../operationLogInfoController/findOperationLogWithPage.do',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			pagePosition : 'both',
			toolbar : '#tb',
			columns : [ [ {
				checkbox : true,
				title : "操作日志ID",
				field : "operaId",
				width : 80,
				align : "left"
			}, {
				title : "操作人名称",
				field : "operaUserName",
				width : 80,
				align : "center"
			}, {
				title : "操作描述",
				field : "operaKind",
				width : 80,
				align : "center"
			}, {
				title : "操作时间",
				field : "operaTime",
				width : 80,
				align : "center"
			},{
				title : "备注",
				field : "remark",
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

				<td><label>操作人名称</label></td>
				<td><input type="text" name="operaUserName" id="operaUserName"></td>
				<td><label>操作时间</label></td>
				<td><input class="easyui-datebox" name="operaTime" id="operaTime" style="width:150px">  
				</td>
				<td><a href="#" id="seach-button" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true">查询</a></td>
			</tr>


		</table>
		 <a href="#"
			id="edit-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-print',plain:true">导出</a> <a href="#"
			id="remove-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>
	<div id="dg"></div>
</body>
</html>