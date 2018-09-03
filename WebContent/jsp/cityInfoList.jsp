<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>cityInfoList.jsp</title>
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
				privadeId:$("#privadeId_serach").combobox("getValues")
			});
		});
		$("#remove-button").click(function(){
			if($("#dg").datagrid("getSelections").length==0){
				$.messager.alert('消息提示','请选择要删除的数据','info');
			}else{
				var arrayId=[];
				var ids=$("#dg").datagrid("getSelections");
				for(var i in ids){
					arrayId.push(parseInt(ids[i].cityId));
				}
				$.messager.confirm('Confirm', '确定删除此条记录吗', function(res){
				if (res){
				$.post("../cityInfoController/delCityInfo.do",{"id[]":arrayId},function(data){
					if(data=="true"){
						
								$.messager.show({
									title:'消息提示',
									msg:'删除成功！',
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
		})
		$("#add-button").click(function() {
			$("#add-dialog").dialog({
				closed : false,
				closable : false,
				buttons : [ {
					text : '保存',
					iconCls : "icon-save",
					handler : function() {
						$("#ff").form("submit", {
							url : "../cityInfoController/saveCity.do",
							onSubmit : function() {
								var isValid = $(this).form('validate');

								return isValid; // 返回false将停止form提交 

							},
							success : function(data) {
								if (data=="true") {
									$.messager.show({
										title:'消息提示',
										msg:'添加成功！',
										timeout:5000,
										showType:'slide'
									});
									$("#add-dialog").dialog({
										closed : true
									});
									$("#ff").form("clear");
									$("#dg").datagrid("reload");
								} else if(data="exist"){
									$.messager.alert('消息警告','城市名已经存在','error');
								}else {
									$.messager.show({
										title:'消息提示',
										msg:'添加失败！',
										timeout:5000,
										showType:'slide'
									});
									$("#add-dialog").dialog({
										closed : true
									});
								}
							}

						});
					}
				}, {
					text : '重置',
					iconCls : "icon-redo",
					handler : function() {
						$("#ff").form("clear");
					}
				}, {
					text : '关闭',
					iconCls : "icon-cancel",
					handler : function() {
						$("#add-dialog").dialog({
							closed : true
						});
						$("#edit-ff").form("clear");
					}
				} ]
			});
		});

		$("#dg").datagrid({
			url : '../cityInfoController/findCityWithPage.do',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			pagePosition : 'both',
			toolbar : '#tb',
			columns : [ [ {
				checkbox : true,
				title : "城市ID",
				field : "cityId",
				width : 80,
				align : "left"
			}, {
				title : "城市编号",
				field : "cityNumber",
				width : 80,
				align : "center"
			}, {
				title : "城市名称",
				field : "cityName",
				width : 80,
				align : "center"
			}, {
				title : "所属省份",
				field : "privadeName",
				width : 80,
				align : "center"
			} ] ]

		});

	});
</script>
</head>
<body>
	<div id="tb">
		<shiro:hasPermission name="system:find">
		<table>
			<tr>
				<td><label>省份</label></td>
				<td><input class="easyui-combobox" id="privadeId_serach"
						name="privadeId"
						data-options="multiple:true,editable:false,valueField:'privadeId',textField:'privadeName',url:'../cityInfoController/findPrivadeAllInfo.do'" />
					</td>
				<td><a href="#" id="seach-button" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true">查询</a></td>
			</tr>
		</table>
	</shiro:hasPermission>
		<shiro:hasPermission name="system:create">
		<a href="#" id="add-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">添加</a> 
		</shiro:hasPermission>
		<shiro:hasPermission name="system:delete">
		<a href="#" id="remove-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
		</shiro:hasPermission>
	</div>
	<div id="dg"></div>

	<div id="add-dialog" class="easyui-dialog" data-options="closed:true"
		title="添加城市" style="width: auto; height: auto">

		<form id="ff" method="post">
			<table>
				<tr>
					<td>城市名称：</td>
					<td><input type="text" name="cityName" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>城市编号：</td>
					<td><input type="text" name="cityNumber"
						class="easyui-validatebox" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>所处省份：</td>
					<td><input  class="easyui-combobox"
						name="privadeId"
						data-options="multiple:true,editable:false,required:true,valueField:'privadeId',textField:'privadeName',url:'../cityInfoController/findPrivadeAllInfo.do'" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>