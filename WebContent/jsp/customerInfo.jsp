<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>customerInfo.jsp</title>
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
				custNumber:$("#custNumber").val(),
				custName:$("#custName").val(),
				custSex:$("#custSex").combobox("getValue"),
				cusTypeId:$("#cusTypeId").combobox("getValue")
			});
			$("#cusTypeId").combobox("clear");
		});
		$("#edit-button").click(function(){
			if($("#dg").datagrid("getSelections").length==0){
					$.messager.alert('消息提示','请选择要修改的数据','info');
				}else if($("#dg").datagrid("getSelections").length>1){
					$.messager.alert('消息警告','只能选择一行数据进行修改！','error');
					$("#dg").datagrid("unselectAll");
				}else{
					var custId=$("#dg").datagrid("getSelected").custId;
					$("#edit-ff").form('load',"../customerInfoController/findCustomerById.do?id="+custId);
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
									url:"../customerInfoController/updateCustomerInfo.do",
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
													$("#edit-dialog").dialog({
														closed : true
													});
													$("#dg").datagrid("reload");
													$("#edit-ff").form("clear");
												}else {
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
		$("#remove-button").click(function(){
			if($("#dg").datagrid("getSelections").length==0){
				$.messager.alert('消息提示','请选择要删除的数据','info');
			}else{
				var arrayId=[];
				var ids=$("#dg").datagrid("getSelections");
				for(var i in ids){
					arrayId.push(parseInt(ids[i].custId));
				}
				$.messager.confirm('Confirm', '确定删除此条记录吗', function(res){
				if (res){
				$.post("../customerInfoController/delCustomerInfo.do",{"id[]":arrayId},function(data){
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
		});
		$("#dg").datagrid({
			url : '../customerInfoController/findCustomerWithPage.do',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			pagePosition : 'both',
			toolbar : '#tb',
			columns : [ [ {
				checkbox : true,
				title : "客户ID",
				field : "custId",
				width : 80,
				align : "left"
			}, {
				title : "客户编号",
				field : "custNumber",
				width : 80,
				align : "center"
			}, {
				title : "客户名称",
				field : "custName",
				width : 80,
				align : "center"
			}, {
				title : "年龄",
				field : "custAge",
				width : 80,
				align : "center"
			}, {
				title : "性别",
				field : "custSex",
				width : 80,
				align : "center"
			}, {
				title : "联系电话",
				field : "custTel",
				width : 80,
				align : "center"
			}, {
				title : "邮箱",
				field : "custEmail",
				width : 80,
				align : "center"
			}, {
				title : "爱好",
				field : "custHirbeat",
				width : 80,
				align : "center"
			},{
				title : "住址",
				field : "custAdress",
				width : 80,
				align : "center"
				
			},{
				title : "身份证号码",
				field : "custIdCard",
				width : 80,
				align : "center"
				
			},{
				title : "客户类型",
				field : "cusTypeName",
				width : 80,
				align : "center"
				
			},{
				title : "备注",
				field : "remark",
				width : 80,
				align : "center"
				
			},{
				title : "操作",
				field : "XXX",
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

				<td><label>客户编号</label></td>
				<td><input type="text" name="custNumber" id="custNumber"></td>
				<td><label>客户名称</label></td>
				<td><input type="text" name="custName" id="custName"></td>
				<td><label>性别</label></td>
				<td><select id="custSex" class="easyui-combobox" name="custSex"
					style="width: 150px;" data-options="editable:false">
						<option value="">--请选择--</option>
						<option value="0">女</option>
						<option value="1">男</option>
				</select></td>
				<td>客户类型：</td>
					<td><input  class="easyui-combobox"
						name="cusTypeId" id="cusTypeId"
						data-options="editable:false,valueField:'cusTypeId',textField:'cusTypeName',url:'../customerTypeController/findCusTypeAllInfo.do'" />
					</td>
				<td><a href="#" id="seach-button" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true">查询</a></td>
			</tr>


		</table>
		</shiro:hasPermission>
		<shiro:hasPermission name="system:update">
		<a href="#" id="edit-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a> 
		</shiro:hasPermission>
		<shiro:hasPermission name="system:delete">
		<a href="#" id="remove-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
		</shiro:hasPermission>

	</div>
	<div id="dg"></div>

	<div id="edit-dialog" class="easyui-dialog" data-options="closed:true"
		title="修改客户信息" style="width: auto; height: auto">

		<form id="edit-ff" method="post">
			<table>
				<tr>
					<td><input type="hidden" name="custId" class="easyui-validatebox"></input></td>
					<td></td>
				</tr>
				<tr>
					<td>客户名：</td>
					<td><input type="text" name="custName" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>客户编号</td>
					<td><input type="text" name="custNumber"
						class="easyui-validatebox" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><select id="sex" class="easyui-combobox" name="custSex"
						style="width: 150px;" data-options="editable:false">
							<option value="0">女</option>
							<option value="1">男</option>
					</select></td>
					<td>年龄：</td>
					<td><input type="text" name="custAge" class="easyui-numberspinner"
						data-options="required:true,editable:false,min:1,max:100"></input></td>
				</tr>
				<tr>
					<td>电话：</td>
					<td><input type="text" name="custTel" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>邮箱：</td>
					<td><input type="text" name="custEmail" class="easyui-validatebox"
						data-options="required:true,validType:'myEmail'"></input></td>
				</tr>
				<tr>
					<td>爱好：</td>
					<td><input type="text" name="custHirbeat" class="easyui-validatebox" data-options="required:true"></input></td>
					<td>住址：</td>
					<td><input type="text" name="custAdress"
						class="easyui-validatebox" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>备注：</td>
					<td><input type="text" name="remark" class="easyui-validatebox"></input></td>
					<td>客户类型：</td>
					<td><input  class="easyui-combobox"
						name="cusTypeId"
						data-options="editable:false,required:true,valueField:'cusTypeId',textField:'cusTypeName',url:'../customerTypeController/findCusTypeAllInfo.do'" />
					</td>
				</tr>
				<tr>
					<td>身份证号码：</td>
					<td><input type="text" name="custIdCard" class="easyui-validatebox" data-options="required:true"></input></td>
				</tr>
			</table>
		</form>


	</div>


</body>
</html>