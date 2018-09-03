<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>ticket_BookList.jsp</title>
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
	function f1(airLineId){
		$("#airLine-info").dialog({
			closed:false,
			closable:false,
			buttons:[{
				text : '关闭',
				iconCls : "icon-cancel",
				handler : function() {
					$("#airLine-info").dialog({
						closed : true
					});
				}
			}]
		});
		$.post("../airLineInfoController/findAirLineInfoById.do","airLineId="+airLineId,function(data){
			var array=[];
			array=data.split(",");
			$("#airNumber").html(array[0]);
			$("#airNumber").css("color","red");
			$("#planeSince").html(array[1]);
			$("#planeSince").css("color","red");
			$("#startCity").html(array[2]);
			$("#startCity").css("color","red");
			$("#endCity").html(array[3]);
			$("#endCity").css("color","red");
			$("#airTime").html(array[4]);
			$("#airTime").css("color","red");
			$("#outTime").html(array[5]);
			$("#outTime").css("color","red");
			$("#inTime").html(array[6]);
			$("#inTime").css("color","red");
			$("#sendState").html(array[7]);
			$("#sendState").css("color","red");
		})
	}
	$(function() {
		$("#seach-button").click(function(){
			$("#dg").datagrid('load',{
				bookNumber:$("#bookNumber").val(),
				custId:$("#custId").combobox("getValue"),
				bookTime:$("#bookTime").datebox("getValue"),
				airLineNumber:$("#airLineNumber").combobox("getValue"),
				rankId:$("#rankId").combobox("getValue")
			});
			$("#custId").combobox("clear");
			$("#bookTime").datebox("clear");
			$("#airLineNumber").combobox("clear");
			$("#rankId").combobox("clear");
		});
		$("#remove-button").click(function(){
			if($("#dg").datagrid("getSelections").length==0){
				$.messager.alert('消息提示','请选择要删除的数据','info');
			}else{
				var arrayId=[];
				var ids=$("#dg").datagrid("getSelections");
				for(var i in ids){
					arrayId.push(parseInt(ids[i].bookId));
				}
				$.post("../ticketInfoController/delTicketBookInfo.do",{"id[]":arrayId},function(data){
					if(data=="true"){
						$.messager.confirm('Confirm', '确定删除此条记录吗', function(res){
							if (res){
								$.messager.show({
									title:'消息提示',
									msg:'删除成功！',
									timeout:5000,
									showType:'slide'
								});
							}
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
			}
		});
		 $("#print-button").click(function(){
			 $.post("../ticketInfoController/exportExcel.do",function(flag){
				 if(flag){
					 $.messager.alert('消息提示','生成订单报表成功，请在桌面查看','info');
				 }else{
					 $.messager.alert('消息提示','生成订单报表失败','error'); 
				 }
			 });
		 });
		$("#money-button").click(function(){
			if($("#dg").datagrid("getSelections").length==0){
				$.messager.alert('消息提示','请选择要计算的数据','info');
			}else{
				var arrayId=[];
				var ids=$("#dg").datagrid("getSelections");
				for(var i in ids){
					arrayId.push(parseInt(ids[i].bookId));
				}
				$.post("../ticketInfoController/getSumMoneyById.do",{"id[]":arrayId},function(data){
					$.messager.alert('计算结果','订单总金额为:'+data,'info');
				});
			}
		});
		$("#dg").datagrid({
			url : '../ticketInfoController/findTicketBookWithPage.do',
			fit : true,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			pagePosition : 'both',
			toolbar : '#tb',
			columns : [ [ {
				checkbox : true,
				title : "订单ID",
				field : "bookId",
				width : 80,
				align : "left"
			}, {
				title : "订单编号",
				field : "bookNumber",
				width : 80,
				align : "center"
			}, {
				title : "订单票价",
				field : "bookPrice",
				width : 80,
				align : "center"
			}, {
				title : "订单票数",
				field : "bookSum",
				width : 80,
				align : "center"
			}, {
				title : "下单时间",
				field : "bookTime",
				width : 80,
				align : "center"
			}, {
				title : "航线编号",
				field : "airLineNumber",
				width : 80,
				align : "center"
			}, {
				title : "仓位等级",
				field : "rankName",
				width : 80,
				align : "center"
			},{
				title : "客户名称",
				field : "custName",
				width : 80,
				align : "center"
				
			},{
				title : "备注",
				field : "remark",
				width : 80,
				align : "center"
				
			},{
				title : "操作",
				field : "xxx",
				width : 80,
				align : "center",
					formatter:function(val,rowData,index){
						if(rowData!=null){
							return "<input type='button' value='查看航线信息' onclick='f1("+rowData.airLineId+")'></input>"
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
	<a href="#" id="money-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">计算金额</a>
		<shiro:hasPermission name="system:find">
		<table>
			<tr>

				<td><label>订单编号</label></td>
				<td><input type="text" name="bookNumber" id="bookNumber"></td>
				<td>客户名称：</td>
					<td><input  class="easyui-combobox"
						name="custId" id="custId"
						data-options="editable:false,valueField:'custId',textField:'custName',url:'../customerInfoController/findCustomerAllInfo.do'" />
					</td>
				<td>订单日期：</td>
					<td><input  class="easyui-datebox"
						name="bookTime" id="bookTime"
						data-options="editable:false" />
					</td>
				<td>航线编码：</td>
					<td><input  class="easyui-combobox"
						name="airLineNumber" id="airLineNumber"
						data-options="editable:false,valueField:'airLineNumber',textField:'airLineNumber',url:'../airLineInfoController/findAirLineAllInfo.do'" />
					</td>
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
		<shiro:hasPermission name="system:delete">
		<a href="#" id="remove-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="system:print">
		<a href="#" id="print-button" class="easyui-linkbutton"
			data-options="iconCls:'icon-print',plain:true">导出订单报表</a>
		</shiro:hasPermission>
		
	</div>
	<div id="dg"></div>
	
	<div id="airLine-info" class="easy-dialog" data-options="closed:true" title="航线信息" style="width: 500px; height: auto">
		<table border="1" style="align:center">
			<tr>
				<td>航线编码</td>
				<td>客机型号</td>
				<td>出发城市</td>
				<td>到达城市</td>
				<td>航班日期</td>
				<td>出站时间</td>
				<td>到站时间</td>
				<td>当前状态</td>
			</tr>
			<tr>
				<td><span id="airNumber"></span></td>
				<td><span id="planeSince"></span></td>
				<td><span id="startCity"></span></td>
				<td><span id="endCity"></span></td>
				<td><span id="airTime"></span></td>
				<td><span id="outTime"></span></td>
				<td><span id="inTime"></span></td>
				<td><span id="sendState"></span></td>
			</tr>
		</table>
		
	</div>
</body>
</html>