<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>spaceRankList.jsp</title>
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
				airLineNumber:$("#airLineNumber").combobox("getValue"),
				rankId:$("#rankId").combobox("getValue")
			});
			$("#airLineNumber").combobox("clear");
			$("#rankId").combobox("clear");
		});
		$("#edit-button").click(function(){
			if($("#dg").datagrid("getSelections").length==0){
					$.messager.alert('消息提示','请选择要修改的数据','info');
				}else if($("#dg").datagrid("getSelections").length>1){
					$.messager.alert('消息警告','只能选择一行数据进行修改！','error');
					$("#dg").datagrid("unselectAll");
				}else{
					var spaceId=$("#dg").datagrid("getSelected").spaceId;
					$("#edit-ff").form('load',"../spaceRankInfoController/findSpaceRankById.do?id="+spaceId);
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
									url:"../spaceRankInfoController/updateSpaceRankInfo.do",
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
					arrayId.push(parseInt(ids[i].spaceId));
				}
				$.messager.confirm('Confirm', '确定删除此条记录吗', function(res){
				if (res){
				$.post("../spaceRankInfoController/delSpaceRankInfo.do",{"id[]":arrayId},function(data){
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
							url : "../spaceRankInfoController/saveSpaceRank.do",
							onSubmit : function() {
								var isValid = $(this).form('validate');

								return isValid; // 返回false将停止form提交 

							},
							success : function(data) {
								if (data=="true") {
									$.messager.show({
										title:'消息提示',
										msg:'添加仓位等级成功！',
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
										msg:'添加仓位等级失败！',
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
			url : '../spaceRankInfoController/findSpaceRankWithPage.do',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			pagePosition : 'both',
			toolbar : '#tb',
			columns : [ [ {
				checkbox : true,
				title : "仓位等级ID",
				field : "spaceId",
				width : 80,
				align : "left"
			},{
				title : "仓位等级编号",
				field : "spaceNumber",
				width : 80,
				align : "center"
			}, {
				title : "航线编码",
				field : "airLineNumber",
				width : 80,
				align : "center"
			}, {
				title : "仓位等级名称",
				field : "rankName",
				width : 80,
				align : "center"
			}, {
				title : "是否有礼物",
				field : "isGift",
				width : 80,
				align : "center"
			}, {
				title : "是否有报纸",
				field : "isNewspaper",
				width : 80,
				align : "center"
			}, {
				title : "是否有饮料",
				field : "isDrink",
				width : 80,
				align : "center"
			}, {
				title : "是否有电影",
				field : "isMovie",
				width : 80,
				align : "center"
			}, {
				title : "是否可改签",
				field : "isChange",
				width : 80,
				align : "center"
			},{
				title : "是否可退票",
				field : "isReturnTicket",
				width : 80,
				align : "center",
			}, {
				title : "是否可打折",
				field : "isDiscount",
				width : 80,
				align : "center"
			},{
				title : "票价(￥)",
				field : "spacePrice",
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
					<td><input type="text" name="airLineNumber" id="airLineNumber" class="easyui-combobox"
						data-options="editable:false,valueField:'airLineNumber',textField:'airLineNumber',url:'../airLineInfoController/findAirLineAllInfo.do'"></input></td>
				<td>仓位等级：</td>
				<td><input  class="easyui-combobox"
						name="rankId" id="rankId"
						data-options="editable:false,valueField:'rankId',textField:'rankName',url:'../spaceRankInfoController/findSpaceRankAllInfo.do'" />
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
	<div id="dg"></div>

	<div id="add-dialog" class="easyui-dialog" data-options="closed:true"
		title="添加仓位等级信息" style="width: auto; height: auto">

		<form id="ff" method="post">
			<table>
				<tr>
					<td>仓位等级编号：</td>
					<td><input type="text" name="spaceNumber" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>航线编码：</td>
					<td><input type="text" name="airLineId" class="easyui-combobox"
						data-options="required:true,editable:false,valueField:'airLineId',textField:'airLineNumber',url:'../airLineInfoController/findAirLineAllInfo.do'"></input></td>
				</tr>
				<tr>
					<td>是否有礼物：</td>
					<td><select  class="easyui-combobox" name="isGift"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
					<td>是否有报纸：</td>
					<td><select  class="easyui-combobox" name="isNewspaper"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
				</tr>
				<tr>
					<td>是否有饮料：</td>
					<td><select  class="easyui-combobox" name="isDrink"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
					<td>是否有电影：</td>
					<td><select  class="easyui-combobox" name="isMovie"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
				</tr>
				<tr>
					<td>是否可改签：</td>
					<td><select  class="easyui-combobox" name="isChange"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
					<td>是否可退票：</td>
					<td><select  class="easyui-combobox" name="isReturnTicket"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
				</tr>
				<tr>
					<td>是否可打折：</td>
					<td><select  class="easyui-combobox" name="isDiscount"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
					<td>仓位等级：</td>
					<td><input  class="easyui-combobox"
						name="rankId"
						data-options="required:true,editable:false,valueField:'rankId',textField:'rankName',url:'../spaceRankInfoController/findSpaceRankAllInfo.do'" />
					</td>
				</tr>
				<tr>
					<td>票价(￥)：</td>
					<td><input type="text" name="spacePrice"
						class="required:true,easyui-validatebox" ></input></td>
					<td>备注：</td>
					<td><input type="text" name="remark"
						class="easyui-validatebox" ></input></td>
				</tr>
			</table>
		</form>


	</div>

	<div id="edit-dialog" class="easyui-dialog" data-options="closed:true"
		title="修改仓位等级" style="width: auto; height: auto">

		<form id="edit-ff" method="post">
			<table>
				<tr>
					<td><input type="hidden" name="spaceId" class="easyui-validatebox"></input></td>
					<td></td>
				</tr>
				<tr>
					<td>仓位等级编号：</td>
					<td><input type="text" name="spaceNumber" class="easyui-validatebox"
						data-options="required:true"></input></td>
					<td>航线编码：</td>
					<td><input type="text" name="airLineId" class="easyui-combobox"
						data-options="required:true,editable:false,valueField:'airLineId',textField:'airLineNumber',url:'../airLineInfoController/findAirLineAllInfo.do'"></input></td>
				</tr>
				<tr>
					<td>是否有礼物：</td>
					<td><select  class="easyui-combobox" name="isGift"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
					<td>是否有报纸：</td>
					<td><select  class="easyui-combobox" name="isNewspaper"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
				</tr>
				<tr>
					<td>是否有饮料：</td>
					<td><select  class="easyui-combobox" name="isDrink"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
					<td>是否有电影：</td>
					<td><select  class="easyui-combobox" name="isMovie"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
				</tr>
				<tr>
					<td>是否可改签：</td>
					<td><select  class="easyui-combobox" name="isChange"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
					<td>是否可退票：</td>
					<td><select  class="easyui-combobox" name="isReturnTicket"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
				</tr>
				<tr>
					<td>是否可打折：</td>
					<td><select  class="easyui-combobox" name="isDiscount"
						style="width: 150px;" data-options="editable:false">
							<option value="是">是</option>
							<option value="否">否</option>
					</select></td>
					<td>仓位等级：</td>
					<td><input  class="easyui-combobox"
						name="rankId"
						data-options="required:true,editable:false,valueField:'rankId',textField:'rankName',url:'../spaceRankInfoController/findSpaceRankAllInfo.do'" />
					</td>
				</tr>
				<tr>
				    <td>票价(￥)：</td>
					<td><input type="text" name="spacePrice"
						class="required:true,easyui-validatebox" ></input></td>
					<td>备注：</td>
					<td><input type="text" name="remark"
						class="easyui-validatebox" ></input></td>
				</tr>
			</table>
		</form>


	</div>


</body>
</html>