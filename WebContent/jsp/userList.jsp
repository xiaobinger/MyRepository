<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>userList.jsp</title>
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
				userNumber:$("#userNumber").val(),
				userName:$("#userName").val(),
				userSex:$("#userSex").combobox('getValue'),
				rolerId:$("#rolerId_serach").combobox("getValues")
			});
		});
		$("#rolerId").combobox('clear');
		$("#edit-button").click(function(){
			if($("#dg").datagrid("getSelections").length==0){
					$.messager.alert('消息提示','请选择要修改的数据','info');
				}else if($("#dg").datagrid("getSelections").length>1){
					$.messager.alert('消息警告','只能选择一行数据进行修改！','error');
					$("#dg").datagrid("unselectAll");
				}else{
					var userId=$("#dg").datagrid("getSelected").userId;
					$("#edit-ff").form('load',"../userInfoController/findUserById.do?id="+userId);
					$.get("../userInfoController/findRolerOfUser.do?id="+userId,null,function(data){
						for(var i=1;i<data.length-1;i++){
							if(data[i]!=","){
								$("#rolerId").combobox('select',data[i]);
							}
							
						}
						
					});
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
									url:"../userInfoController/updateUserInfo.do",
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
					arrayId.push(parseInt(ids[i].userId));
				}
				$.messager.confirm('Confirm', '确定删除此条记录吗', function(res){
				if (res){
				$.post("../userInfoController/delUserInfo.do",{"id[]":arrayId},function(data){
					if(data=="true"){
						$("#dg").datagrid("reload");
								$.messager.show({
									title:'消息提示',
									msg:'删除成功！',
									timeout:5000,
									showType:'slide'
								});
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
		$("#add-button").click(function() {
			$("#add-dialog").dialog({
				closed : false,
				closable : false,
				buttons : [ {
					text : '保存',
					iconCls : "icon-save",
					handler : function() {
						$("#ff").form("submit", {
							url : "../userInfoController/saveUser.do",
							onSubmit : function() {
								var isValid = $(this).form('validate');

								return isValid; // 返回false将停止form提交 

							},
							success : function(data) {
								if (data=="true") {
									$.messager.show({
										title:'消息提示',
										msg:'角色分配成功！',
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
										msg:'角色分配失败！',
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
			url : '../userInfoController/findUserWithPage.do',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			pagePosition : 'both',
			toolbar : '#tb',
			columns : [ [ {
				checkbox : true,
				title : "用户ID",
				field : "userId",
				width : 80,
				align : "left"
			}, {
				title : "用户编号",
				field : "userNumber",
				width : 80,
				align : "center"
			}, {
				title : "用户名称",
				field : "userName",
				width : 80,
				align : "center"
			}, {
				title : "用户密码",
				field : "userPass",
				width : 80,
				align : "center"
			}, {
				title : "年龄",
				field : "userAge",
				width : 80,
				align : "center"
			}, {
				title : "性别",
				field : "userSex",
				width : 80,
				align : "center"
			}, {
				title : "联系电话",
				field : "userTel",
				width : 80,
				align : "center"
			}, {
				title : "邮箱",
				field : "userEmail",
				width : 80,
				align : "center"
			},{
				title : "用户角色",
				field : "setRolers",
				width : 80,
				align : "center",
				formatter:function(val,rowData,index){
					var array=[];
					for(var i=0;i<val.length;i++){
						array.push(val[i].rolerName);
					}
					return array.toString();
				}
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

				<td><label>用户编号</label></td>
				<td><input type="text" name="userNumber" id="userNumber"></td>
				<td><label>用户名称</label></td>
				<td><input type="text" name="userName" id="userName"></td>
				<td><label>性别</label></td>
				<td><select id="userSex" class="easyui-combobox" name="userSex"
					style="width: 150px;" data-options="editable:false">
						<option value="">--请选择--</option>
						<option value="0">女</option>
						<option value="1">男</option>
				</select></td>
				<td><label>用户角色</label></td>
				<td><input class="easyui-combobox" id="rolerId_serach"
						name="rolerId"
						data-options="multiple:true,editable:false,valueField:'rolerId',textField:'rolerName',url:'../rolerInfoController/findRolerAllInfo.do'" />
					</td>
				<td><a href="#" id="seach-button" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true">查询</a></td>
			</tr>


		</table>
		</shiro:hasPermission>
		<shiro:hasPermission name="system:create">
		<a href="#" id="add-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">角色分配/更改</a> 
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

	<div id="add-dialog" class="easyui-dialog" data-options="closed:true"
		title="用户的角色分配" style="width: auto; height: auto">

		<form id="ff" method="post">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="userId" class="easyui-combobox"
						data-options="required:true,editable:false,valueField:'userId',textField:'userName',url:'../userInfoController/findUserAllInfo.do'"></input></td>
					<td>角色：</td>
					<td><input  class="easyui-combobox"
						name="rolerId[]"
						data-options="multiple:true,editable:false,required:true,valueField:'rolerId',textField:'rolerName',url:'../rolerInfoController/findRolerAllInfo.do'" />
					</td>
				</tr>
			</table>
		</form>


	</div>

	<div id="edit-dialog" class="easyui-dialog" data-options="closed:true"
		title="修改用户" style="width: auto; height: auto">

		<form id="edit-ff" method="post">
			<table>
				<tr>
					<td><input type="hidden" name="userId" class="easyui-validatebox"></input></td>
					<td></td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="userName" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>密码：</td>
					<td><input type="text" name="userPass"
						class="easyui-validatebox" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><select id="sex" class="easyui-combobox" name="userSex"
						style="width: 150px;" data-options="editable:false">
							<option value="0">女</option>
							<option value="1">男</option>
					</select></td>
					<td>年龄：</td>
					<td><input type="text" name="userAge" class="easyui-numberspinner"
						data-options="required:true,editable:false,min:1,max:100"></input></td>
				</tr>
				<tr>
					<td>电话：</td>
					<td><input type="text" name="userTel" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>邮箱：</td>
					<td><input type="text" name="userEmail" class="easyui-validatebox"
						data-options="required:true,validType:'myEmail'"></input></td>
				</tr>
				<tr>
					<td>备注：</td>
					<td><input type="text" name="remark"></input></td>
					<td>用户编号：</td>
					<td><input type="text" name="userNumber"
						class="easyui-validatebox" data-options="required:true"></input></td>
				</tr>
			</table>
		</form>


	</div>


</body>
</html>