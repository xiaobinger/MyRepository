<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>planeList.jsp</title>
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
				planeNumber:$("#planeNumber").val(),
				planeSince:$("#planeSince").val()
			});
			
		});
		$("#edit-button").click(function(){
			if($("#dg").datagrid("getSelections").length==0){
					$.messager.alert('消息提示','请选择要修改的数据','info');
				}else if($("#dg").datagrid("getSelections").length>1){
					$.messager.alert('消息警告','只能选择一行数据进行修改！','error');
					$("#dg").datagrid("unselectAll");
				}else{
					var planeId=$("#dg").datagrid("getSelected").planeId;
					$("#edit-ff").form('load',"../planeInfoController/findPlaneById.do?id="+planeId);
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
									url:"../planeInfoController/updatePlaneInfo.do",
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
					arrayId.push(parseInt(ids[i].planeId));
				}
				$.messager.confirm('Confirm', '确定删除此条记录吗', function(res){
				if (res){
				$.post("../planeInfoController/delPlaneInfo.do",{"id[]":arrayId},function(data){
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
							url : "../planeInfoController/savePlane.do",
							onSubmit : function() {
								var isValid = $(this).form('validate');

								return isValid; // 返回false将停止form提交 

							},
							success : function(data) {
								if (data=="true") {
									$.messager.show({
										title:'消息提示',
										msg:'添加客机成功！',
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
										msg:'添加客机失败！',
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
			url : '../planeInfoController/findPlaneWithPage.do',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			pagePosition : 'both',
			toolbar : '#tb',
			columns : [ [ {
				checkbox : true,
				title : "客机ID",
				field : "planeId",
				width : 80,
				align : "left"
			},{
				title : "客机编号",
				field : "planeNumber",
				width : 80,
				align : "center"
			}, {
				title : "客机型号",
				field : "planeSince",
				width : 80,
				align : "center"
			}, {
				title : "购买时间",
				field : "buyTime",
				width : 80,
				align : "center"
			}, {
				title : "服务年限",
				field : "serviceTime",
				width : 80,
				align : "center"
			}, {
				title : "经济舱座位数",
				field : "tSeats",
				width : 80,
				align : "center"
			}, {
				title : "商务舱座位数",
				field : "bSeats",
				width : 80,
				align : "center"
			}, {
				title : "头等舱座位数",
				field : "fSeats",
				width : 80,
				align : "center"
			}, {
				title : "备注",
				field : "remark",
				width : 80,
				align : "center"
			}  ] ]

		});

	});
</script>
</head>
<body>
	<div id="tb">
		<shiro:hasPermission name="system:find">
		<table>
			<tr>

				<td>客机编号：</td>
					<td><input type="text" name="planeNumber" id="planeNumber"></input></td>
				<td>客机型号：</td>
				<td><input type="text" name="planeSince" id="planeSince"></input></td>
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
		<shiro:hasPermission name="system:delete">
		<a href="#" id="remove-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
		</shiro:hasPermission>
	</div>
	<div id="dg">
	<div id="add-dialog" class="easyui-dialog" data-options="closed:true"
		title="添加客机信息" style="width: auto; height: auto">
		<form id="ff" method="post">
			<table>
				<tr>
					<td>客机编号：</td>
					<td><input type="text" name="planeNumber" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>客机型号：</td>
					<td><input type="text" name="planeSince" class="easyui-validatebox"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>购买时间：</td>
					<td><input type="text" name="buyTime" class="easyui-datebox"
						data-options="required:true,editable:false"></input></td>
					<td>服务年限：</td>
					<td><input type="text" name="serviceTime" class="easyui-numberspinner"
						data-options="required:true,editable:false,min:1,max:20"></input></td>	
				</tr>
				<tr>
					<td>经济舱座位数：</td>
					<td><input type="text" name="tSeats" class="easyui-numberspinner"
						data-options="required:true,editable:false,min:1,max:1000"></input></td>	
					<td>商务舱座位数：</td>
					<td><input type="text" name="bSeats" class="easyui-numberspinner"
						data-options="required:true,editable:false,min:1,max:1000"></input></td>	
				</tr>
				<tr>
					<td>头等舱座位数：</td>
					<td><input type="text" name="fSeats" class="easyui-numberspinner"
						data-options="required:true,editable:false,min:1,max:1000"></input></td>	
					<td>备注：</td>
					<td><input type="text" name="remark"
						class="easyui-validatebox" ></input></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="edit-dialog" class="easyui-dialog" data-options="closed:true"
		title="修改客機信息" style="width: auto; height: auto">
		<form id="edit-ff" method="post">
			<table>
				<tr>
					<td><input type="hidden" name="planeId" class="easyui-validatebox"></input></td>
					<td></td>
				</tr>
				<tr>
					<td>客机编号：</td>
					<td><input type="text" name="planeNumber" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>客机型号：</td>
					<td><input type="text" name="planeSince" class="easyui-validatebox"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>购买时间：</td>
					<td><input type="text" name="buyTime" class="easyui-datebox"
						data-options="required:true,editable:false"></input></td>
					<td>服务年限：</td>
					<td><input type="text" name="serviceTime" class="easyui-numberspinner"
						data-options="required:true,editable:false,min:1,max:20"></input></td>	
				</tr>
				<tr>
					<td>经济舱座位数：</td>
					<td><input type="text" name="tSeats" class="easyui-numberspinner"
						data-options="required:true,editable:false,min:1,max:1000"></input></td>	
					<td>商务舱座位数：</td>
					<td><input type="text" name="bSeats" class="easyui-numberspinner"
						data-options="required:true,editable:false,min:1,max:1000"></input></td>	
				</tr>
				<tr>
					<td>头等舱座位数：</td>
					<td><input type="text" name="fSeats" class="easyui-numberspinner"
						data-options="required:true,editable:false,min:1,max:1000"></input></td>	
					<td>备注：</td>
					<td><input type="text" name="remark"
						class="easyui-validatebox" ></input></td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>