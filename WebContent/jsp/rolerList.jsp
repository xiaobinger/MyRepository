<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
function f1(rolerId){
	$("#user-info").dialog({
		closed:false,
		closable:false,
		buttons:[{
			text : '关闭',
			iconCls : "icon-cancel",
			handler : function() {
				$("#user-info").dialog({
					closed : true
				});
			}
		}]
	});
	$("#removeUserInRoler").click(function(){
		if($("#user-dg").datagrid("getSelections").length==0){
			$.messager.alert('消息提示','请选择要剔除的用户','info');
		}else{
			var arrayId=[];
			var ids=$("#user-dg").datagrid("getSelections");
			for(var i in ids){
				arrayId.push(parseInt(ids[i].userId));
				}
				$.messager.confirm('Confirm', '确定移除该用户吗', function(res){
					if (res){
						$.post("../userInfoController/deleteRolerofUser.do",{"id[]":arrayId,"rolerId":rolerId},function(data){
							if(data=="true"){
								$.messager.show({
									title:'消息提示',
									msg:'移除成功！',
									timeout:5000,
									showType:'slide'
								});
								$("#user-dg").datagrid("reload");
								$("#dg").datagrid("unselectAll");
								}else{
									alert("~~~~");
									$.messager.show({
										title:'消息提示',
										msg:'移除失败！',
										timeout:5000,
										showType:'slide'
									});
								}
						});
						
					}else{
						$("#user-dg").datagrid("unselectAll");
					}
				});
		}
	});
	$("#user-dg").datagrid({
		url : '../userInfoController/findUserWithPageByRolerId.do?rolerId='+rolerId,
//			fit : true,
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		pagePosition : 'both',
		toolbar : '#tb_rolerOfUser',
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
			title : "性别",
			field : "userSex",
			width : 80,
			align : "center"
		}, {
			title : "年龄",
			field : "userAge",
			width : 80,
			align : "center"
		}, {
			title : "电话",
			field : "userTel",
			width : 80,
			align : "center"
		}, {
			title : "邮箱",
			field : "userEmail",
			width : 80,
			align : "center"
		}, {
			title : "备注",
			field : "remark",
			width : 80,
			align : "center"
		} ] ]

	});
	
}
	$(function() {
		$("#seach-button").click(function(){
			$("#dg").datagrid('load',{
				rolerNumber:$("#rolerNumber").val(),
				rolerName:$("#rolerName").val(),
				permiId:$("#permiId_serach").combobox("getValues")
			});
		});
		$("#edit-button").click(function(){
			if($("#dg").datagrid("getSelections").length==0){
					$.messager.alert('消息提示','请选择要分配权限的角色','info');
				}else if($("#dg").datagrid("getSelections").length>1){
					$.messager.alert('消息警告','只能选择一个角色进行权限分配！','error');
					$("#dg").datagrid("unselectAll");
				}else{
					var rolerId=$("#dg").datagrid("getSelected").rolerId;
					$("#edit-ff").form('load',"../rolerInfoController/findRolerById.do?id="+rolerId);
					$.get("../rolerInfoController/findPermissionOfRoler.do?id="+rolerId,null,function(data){
							for(var i=1;i<data.length-1;i++){
								if(data[i]!=","){
									$("#permiId").combobox('select',data[i]);
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
								$("#edit-ff").form("submit",{
									url:"../rolerInfoController/updateRolerInfo.do",
									onSubmit : function() {
										var isValid = $(this).form('validate');
										return isValid; // 返回false将停止form提交 
									},
									success : function(data) {
										if (data) {
											$.messager.confirm('Confirm', '确定提交内容吗', function(res){
												if (res){
													$.messager.show({
														title:'消息提示',
														msg:'权限分配成功！',
														timeout:5000,
														showType:'slide'
													});
													$("#edit-ff").form("clear");
												}
											});
											$("#edit-dialog").dialog({
												closed : true
											});
											$("#dg").datagrid("reload");
										} else {
											$.messager.show({
												title:'消息提示',
												msg:'权限分配失败！',
												timeout:5000,
												showType:'slide'
											});
										}
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
			url : '../rolerInfoController/findRolerWithPage.do',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			pagePosition : 'both',
			toolbar : '#tb',
			columns : [ [ {
				checkbox : true,
				title : "角色ID",
				field : "rolerId",
				width : 80,
				align : "left"
			}, {
				title : "角色编号",
				field : "rolerNumber",
				width : 80,
				align : "center"
			}, {
				title : "角色名称",
				field : "rolerName",
				width : 80,
				align : "center"
			}, {
				title : "角色描述",
				field : "rolerDesc",
				width : 80,
				align : "center"
			},{
				title : "备注",
				field : "remark",
				width : 80,
				align : "center"
			},{
				title : "操作",
				field : "xxxx",
				width : 80,
				align : "center",
				formatter:function(val,rowData,index){
					if(rowData!=null){
						return "<input type='button' value='查看拥有该角色的用户信息' onclick='f1("+rowData.rolerId+")'></input>"
					}else{
						return '';
					}
				}
			} ] ]

		});

	});
</script>
</head>
<body>
	
	<div id="tb">

		<table>
			<tr>

				<td><label>角色编号</label></td>
				<td><input type="text" name="rolerNumber" id="rolerNumber"></td>
				<td><label>角色名称</label></td>
				<td><input type="text" name="rolerName" id="rolerName"></td>
				<td><label>权限名称</label></td>
				<td><input  class="easyui-combobox" id="permiId_serach"
						name="permiId"
						data-options="multiple:true,editable:false,valueField:'permiId',textField:'permiName',url:'../permissionInfoController/findPermissionAllInfo.do'" />
					</td>
				<td><a href="#" id="seach-button" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true">查询</a></td>
			</tr>
		</table>
		<a href="#"
			id="edit-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">权限分配</a> 
	</div>
	<div id="dg"></div>
	
	<div id="user-info" class="easy-dialog" data-options="closed:true" title="用户信息" style="width: 800px; height: 400px">
		<div id="user-dg">
			<div id="tb_rolerOfUser">
				<a href="#" id="removeUserInRoler" class="easyui-linkbutton"
					data-options="iconCls:'icon-edit',plain:true">剔除用户</a> 
			</div>
		</div>
	</div>
	
	<div id="edit-dialog" class="easyui-dialog" data-options="closed:true"
		title="角色的权限分配" style="width: auto; height: auto">

		<form id="edit-ff" method="post">
			<table>
				<tr>
					<td><input type="hidden" name="rolerId" class="easyui-validatebox"></input></td>
					<td></td>
				</tr>
				<tr>
					<td>角色名：</td>
					<td><input type="text" name="rolerName" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>权限：</td>
					<td><input id="permiId" class="easyui-combobox"
						name="permiId[]"
						data-options="multiple:true,editable:false,required:true,valueField:'permiId',textField:'permiName',url:'../permissionInfoController/findPermissionAllInfo.do'" />
					</td>
				</tr>
			</table>
		</form>


	</div>
</body>
</html>