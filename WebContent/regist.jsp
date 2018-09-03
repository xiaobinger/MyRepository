<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>regist.jsp</title>
<link rel="stylesheet" type="text/css"
	href="jquery/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery/themes/icon.css">
<script type="text/javascript" src="jquery/jquery.min.js"></script>
<script type="text/javascript" src="jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="jquery/mydatavail.js"></script>
<script type="text/javascript">
window.history.forward(-1);
	$(function(){
		$("#dd").dialog({
			closable:false,
			buttons:[{
				text:'注册',
				handler:function(){
					$("#regist-form").form(
							'submit', {   
							    url:"userInfoController/regist.do",   
							    onSubmit: function(){   
							       return $("#regist-form").form("validate"); 
							    },   
							    success:function(data){ 
							       if(data=="true"){
							    	   window.location.href="success.jsp";
							       }else if(data=='exist'){
							    	   $.messager.alert('消息提示','用户名已存在','error');
							       }else{
							    	   $.messager.alert('消息提示','注册失败','error');
							       }  
							    } 

						});
				}
			},{
				text:'重置',
				handler:function(){
					$("#regist-form").form("clear");
				}
			}]
		});
		
	});
</script>
</head>
<body>
<div id="dd" class="easyui-dialog" title="注册界面" style="width:auto;height:auto;"  
        data-options="modal:true">  
   <form action="" method="post" id="regist-form">
   	<table>
   	<tr><td>用户名</td><td><input class="easyui-validatebox" type="text" name="userName" data-options="required:true,validType:'myUserName'" /></td></tr>
   	<tr><td>密码</td><td><input class="easyui-validatebox" type="text" name="userPass" id="userPass" data-options="required:true,validType:'myUserPass'" /></td></tr>
   	<tr><td>电话</td><td><input class="easyui-validatebox" type="text" name="userTel" data-options="required:true,validType:'myTel'" /></td></tr>
   	<tr><td>邮箱</td><td><input class="easyui-validatebox" type="text" name="userEmail" data-options="required:true,validType:'myEmail'" /></td></tr>
   	<tr><td><label>性别</label></td>
				<td><select id="userSex" class="easyui-combobox" name="userSex"
					style="width: 150px;" data-options="required:true,editable:false">
						<option value="">--请选择--</option>
						<option value="0">女</option>
						<option value="1">男</option>
				</select></td>
	</tr>
	<tr><td>年龄：</td>
		<td><input type="text" name="userAge" class="easyui-numberspinner"
				data-options="required:true,editable:false,min:1,max:100"></input></td>		
	</tr>
   	</table>
  </form> 
  <a href="login.jsp">暂不注册，返回登录</a>  
</div>  
</body>
</html>