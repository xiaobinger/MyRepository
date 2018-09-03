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
function f1(permiId){
	$("#roler-info").dialog({
		closed:false,
		closable:false,
		buttons:[{
			text : '关闭',
			iconCls : "icon-cancel",
			handler : function() {
				$("#roler-info").dialog({
					closed : true
				});
			}
		}]
	});
	
	$("#removeRolerInPermission").click(function(){
		if($("#roler-dg").datagrid("getSelections").length==0){
			$.messager.alert('消息提示','请选择要剔除的角色','info');
		}else{
			var arrayId=[];
			var ids=$("#roler-dg").datagrid("getSelections");
			for(var i in ids){
				arrayId.push(parseInt(ids[i].rolerId));
			}
				$.messager.confirm('Confirm', '确定移除该角色吗', function(res){
					if (res){
						$.post("../rolerInfoController/deletePermissionofRoler.do",{"id[]":arrayId,"permiId":permiId},function(data){
							if(data=="true"){
								$.messager.show({
									title:'消息提示',
									msg:'移除成功！',
									timeout:5000,
									showType:'slide'
								});
								$("#roler-dg").datagrid("reload");
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
						$("#roler-dg").datagrid("unselectAll");
					}
				});
		}
	});
	$("#roler-dg").datagrid({
		url : '../rolerInfoController/findRolerWithPageByPermiId.do?permiId='+permiId,
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		pagePosition : 'both',
		toolbar : '#tb_PermissionOfRoler',
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
				permiNumber:$("#permiNumber").val(),
				permiName:$("#permiName").val()
			});
		});
		$("#dg").datagrid({
			url : '../permissionInfoController/findPermissionWithPage.do',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			pagePosition : 'both',
			toolbar : '#tb',
			columns : [ [ {
				checkbox : true,
				title : "权限ID",
				field : "permiId",
				width : 80,
				align : "left"
			}, {
				title : "权限编号",
				field : "permiNumber",
				width : 80,
				align : "center"
			}, {
				title : "权限名称",
				field : "permiName",
				width : 80,
				align : "center"
			}, {
				title : "权限描述",
				field : "permiDesc",
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
							return "<input type='button' value='查看拥有该权限的角色信息' onclick='f1("+rowData.permiId+")'></input>"
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

				<td><label>权限编号</label></td>
				<td><input type="text" name="permiNumber" id="permiNumber"></td>
				<td><label>权限名称</label></td>
				<td><input type="text" name="permiName" id="permiName"></td>
				<td><a href="#" id="seach-button" class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true">查询</a></td>
			</tr>


		</table>
	</div>
	<div id="dg"></div>
	<div id="roler-info" class="easy-dialog" data-options="closed:true" title="用户信息" style="width: 800px; height: 400px">
		<div id="roler-dg">
			<div id="tb_PermissionOfRoler">
				<a href="#" id="removeRolerInPermission" class="easyui-linkbutton"
					data-options="iconCls:'icon-edit',plain:true">剔除角色</a> 
			</div>
		</div>
	</div>
</body>
</html>