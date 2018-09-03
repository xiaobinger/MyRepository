<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>customerTypeList.jsp</title>
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
				cusTypeName:$("#cusTypeName").val()
			});
		});
		
		$("#add-button").click(function() {
			$("#add-dialog").dialog({
				closed : false,
				closable : false,
				buttons : [ {
					text : '保存',
					iconCls : "icon-save",
					handler : function() {
						$("#ff").form("submit", {
							url : "../customerTypeController/saveCustomType.do",
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
		
		$("#edit-button").click(function(){
			if($("#dg").datagrid("getSelections").length==0){
					$.messager.alert('消息提示','请选择要修改的数据','info');
				}else if($("#dg").datagrid("getSelections").length>1){
					$.messager.alert('消息警告','只能选择一行数据进行修改！','error');
					$("#dg").datagrid("unselectAll");
				}else{
					var cusTypeId=$("#dg").datagrid("getSelected").cusTypeId;
					$("#edit-ff").form('load',"../customerTypeController/findCusTypeById.do?id="+cusTypeId);
					$("#edit-dialog").dialog({
						closed : false,
						closable : false,
						buttons : [ {
							text : '保存',
							iconCls : "icon-save",
							handler : function() {
								$.messager.confirm('Confirm', '确定提交修改内容吗', function(res){
									if (res){
								$("#edit-ff").form("submit",{
									url:"../customerTypeController/updateCusTypeInfo.do",
									onSubmit : function() {
										var isValid = $(this).form('validate');
										return isValid; // 返回false将停止form提交 
									},
									success : function(data) {
										if (data) {
											
													$.messager.show({
														title:'消息提示',
														msg:'修改成功！',
														timeout:5000,
														showType:'slide'
													});
													$("#edit-ff").form("clear");
													$("#edit-dialog").dialog({
														closed : true
													});
													$("#dg").datagrid("reload");
												} else {
													$.messager.show({
														title:'消息提示',
														msg:'修改失败！',
														timeout:5000,
														showType:'slide'
													});
												}
											}
										});
											
										
									}

								});
							}
						}, {
							text : '关闭',
							iconCls : "icon-cancel",
							handler : function() {
								$("#edit-dialog").dialog({
									closed : true
								});
							}
						} ]
					});
				}
		});

		$("#dg").datagrid({
			url : '../customerTypeController/findCustomTypeWithPage.do',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			pagePosition : 'both',
			toolbar : '#tb',
			columns : [ [ {
				checkbox : true,
				title : "客户类型ID",
				field : "cusTypeId",
				width : 80,
				align : "left"
			}, {
				title : "客户类型编号",
				field : "cusTypeNumber",
				width : 80,
				align : "center"
			}, {
				title : "客户类型名称",
				field : "cusTypeName",
				width : 80,
				align : "center"
			}, {
				title : "折扣百分比",
				field : "discountName",
				width : 80,
				align : "center"
			}, {
				title : "备注",
				field : "remark",
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
				<td><label>客户类型名称</label></td>
				<td><input type="text" name="cusTypeName" id="cusTypeName" class="easyui-validatebox"></input></td>
				<td><a href="#" id="seach-button" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true">查询</a></td>
			</tr>
		</table>
		</shiro:hasPermission>
		<shiro:hasPermission name="system:create">
		<a href="#" id="add-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">添加</a> 
		</shiro:hasPermission>
		<shiro:hasPermission name="system:update">
		<a href="#" id="edit-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a> 
		</shiro:hasPermission>
	</div>
	<div id="dg"></div>

	<div id="add-dialog" class="easyui-dialog" data-options="closed:true"
		title="添加客户类型" style="width: auto; height: auto">

		<form id="ff" method="post">
			<table>
				<tr>
					<td>客户类型名称：</td>
					<td><input type="text" name="cusTypeName" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>客户类型编号：</td>
					<td><input type="text" name="cusTypeNumber"
						class="easyui-validatebox" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>折扣百分比：</td>
					<td><input  class="easyui-combobox"
						name="discountId"
						data-options="multiple:true,editable:false,required:true,valueField:'discountId',textField:'discountName',url:'../customerTypeController/findDiscountAllInfo.do'" />
					</td>
					<td>备注：</td>
					<td><input type="text" name="remark"
						class="easyui-validatebox"></input></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="edit-dialog" class="easyui-dialog" data-options="closed:true"
		title="修改客户类型" style="width: auto; height: auto">

		<form id="edit-ff" method="post">
			<table>
				<tr>
					<td><input type="hidden" name="cusTypeId" class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>客户类型名称：</td>
					<td><input type="text" name="cusTypeName" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>客户类型编号：</td>
					<td><input type="text" name="cusTypeNumber"
						class="easyui-validatebox" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>折扣百分比：</td>
					<td><input  class="easyui-combobox"
						name="discountId"
						data-options="editable:false,required:true,valueField:'discountId',textField:'discountName',url:'../customerTypeController/findDiscountAllInfo.do'" />
					</td>
					<td>备注：</td>
					<td><input type="text" name="remark"
						class="easyui-validatebox"></input></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>