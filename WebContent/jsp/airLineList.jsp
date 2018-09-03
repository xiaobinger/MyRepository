<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>airLineList.jsp</title>
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
				airLineNumber:$("#airLineNumber").val(),
				planeId:$("#planeId").combobox("getValues")
			});
			$("#planeId").combobox("clear");
		});
		$("#edit-button").click(function(){
			if($("#dg").datagrid("getSelections").length==0){
					$.messager.alert('消息提示','请选择要修改的数据','info');
				}else if($("#dg").datagrid("getSelections").length>1){
					$.messager.alert('消息警告','只能选择一行数据进行修改！','error');
					$("#dg").datagrid("unselectAll");
				}else{
					var airLineId=$("#dg").datagrid("getSelected").airLineId;
					$("#edit-ff").form('load',"../airLineInfoController/findAirLineById.do?id="+airLineId);
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
									url:"../airLineInfoController/updateAirLineInfo.do",
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
					arrayId.push(parseInt(ids[i].airLineId));
				}
				$.messager.confirm('Confirm', '确定删除此条记录吗', function(res){
				if (res){
				$.post("../airLineInfoController/delAirLineInfo.do",{"id[]":arrayId},function(data){
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
							url : "../airLineInfoController/saveAirLine.do",
							onSubmit : function() {
								var isValid = $(this).form('validate');

								return isValid; // 返回false将停止form提交 

							},
							success : function(data) {
								if (data=="true") {
									$.messager.show({
										title:'消息提示',
										msg:'添加航线成功！',
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
										msg:'添加航线失败！',
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
			url : '../airLineInfoController/findAirLineWithPage.do',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			pagePosition : 'both',
			toolbar : '#tb',
			columns : [ [ {
				checkbox : true,
				title : "航线ID",
				field : "airLineId",
				width : 80,
				align : "left"
			},{
				title : "航线编码",
				field : "airLineNumber",
				width : 80,
				align : "center"
			}, {
				title : "客机型号",
				field : "planeSince",
				width : 80,
				align : "center"
			}, {
				title : "出发城市",
				field : "deparCity",
				width : 80,
				align : "center"
			}, {
				title : "到达城市",
				field : "arrivalCity",
				width : 80,
				align : "center"
			}, {
				title : "航班日期",
				field : "airLineTime",
				width : 80,
				align : "center"
			}, {
				title : "出发时间",
				field : "outTime",
				width : 80,
				align : "center"
			}, {
				title : "到达时间",
				field : "inTime",
				width : 80,
				align : "center"
			},{
				title : "状态",
				field : "sendState",
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

				<td>航线编码：</td>
					<td><input type="text" name="airLineNumber" id="airLineNumber"></input></td>
				<td>客机型号：</td>
				<td><input class="easyui-combobox" id="planeId"
						name="planeId"
						data-options="editable:false,valueField:'planeId',textField:'planeSince',url:'../planeInfoController/findPlaneAllInfo.do'" />
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
		title="添加航线信息" style="width: auto; height: auto">
		<form id="ff" method="post">
			<table>
				<tr>
					<td>航线编码：</td>
					<td><input type="text" name="airLineNumber" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>客机型号：</td>
					<td><input class="easyui-combobox" id="planeId"
						name="planeId"
						data-options="required:true,editable:false,valueField:'planeId',textField:'planeSince',url:'../planeInfoController/findPlaneAllInfo.do'" />
					</td>
				</tr>
				<tr>
					<td>出发城市：</td>
					<td><input class="easyui-combobox"
						name="deparCity"
						data-options="required:true,editable:false,valueField:'cityName',textField:'cityName',url:'../cityInfoController/findAllCityInfo.do'" />
					<td>到达城市：</td>
					<td><input class="easyui-combobox"
						name="arrivalCity"
						data-options="required:true,editable:false,valueField:'cityName',textField:'cityName',url:'../cityInfoController/findAllCityInfo.do'" />
				</tr>
				<tr>
					<td>航班日期：</td>
					<td><input  class="easyui-datebox"
						name="airLineTime"
						data-options="required:true,editable:false" />
					</td>
					<td>状态：</td>
					<td><select class="easyui-combobox" name="sendState"
					style="width: 150px;" data-options="required:true,editable:false">
						<option value="">--请选择--</option>
						<option value="待飞">待飞</option>
						<option value="正在飞行">正在飞行</option>
						<option value="已到站">已到站</option>
						</select></td>	
				</tr>
				<tr>
					<td>起飞时间：</td>
					<td><input name="outTime" class="easyui-timespinner"  style="width:80px;"  
        				required="required" data-options="min:'00:00',showSeconds:true" /></td>	
					<td>到达时间：</td>
					<td><input name="inTime" class="easyui-timespinner"  style="width:80px;"  
        				required="required" data-options="min:'03:30',showSeconds:true" /></td>
				</tr>
				<tr>
					<td>备注：</td>
					<td><input type="text" name="remark"
						class="easyui-validatebox" ></input></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="edit-dialog" class="easyui-dialog" data-options="closed:true"
		title="修改航线信息" style="width: auto; height: auto">
		<form id="edit-ff" method="post">
			<table>
				<tr>
					<td><input type="hidden" name="airLineId" class="easyui-validatebox"></input></td>
					<td></td>
				</tr>
				<tr>
					<td>航线编码：</td>
					<td><input type="text" name="airLineNumber" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>客机型号：</td>
					<td><input class="easyui-combobox" id="planeId"
						name="planeId"
						data-options="required:true,editable:false,valueField:'planeId',textField:'planeSince',url:'../planeInfoController/findPlaneAllInfo.do'" />
					</td>
				</tr>
				<tr>
					<td>出发城市：</td>
					<td><input class="easyui-combobox"
						name="deparCity"
						data-options="required:true,editable:false,valueField:'cityName',textField:'cityName',url:'../cityInfoController/findAllCityInfo.do'" />
					<td>到达城市：</td>
					<td><input class="easyui-combobox"
						name="arrivalCity"
						data-options="required:true,editable:false,valueField:'cityName',textField:'cityName',url:'../cityInfoController/findAllCityInfo.do'" />
				</tr>
				<tr>
					<td>航班日期：</td>
					<td><input  class="easyui-datebox"
						name="airLineTime"
						data-options="required:true,editable:false" />
					</td>
					<td>状态：</td>
					<td><select class="easyui-combobox" name="sendState"
					style="width: 150px;" data-options="required:true,editable:false">
						<option value="">--请选择--</option>
						<option value="待飞">待飞</option>
						<option value="正在飞行">正在飞行</option>
						<option value="已到站">已到站</option>
						</select></td>	
				</tr>
				<tr>
					<td>起飞时间：</td>
					<td><input name="outTime" class="easyui-timespinner"  style="width:80px;"  
        				required="required" data-options="min:'00:00',showSeconds:true" /></td>	
					<td>到达时间：</td>
					<td><input name="inTime" class="easyui-timespinner"  style="width:80px;"  
        				required="required" data-options="min:'03:30',showSeconds:true" /></td>
				</tr>
				<tr>
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