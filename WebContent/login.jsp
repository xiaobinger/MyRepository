<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login.jsp</title>
<link rel="stylesheet" type="text/css"
	href="jquery/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery/themes/icon.css">
<script type="text/javascript" src="jquery/jquery.min.js"></script>
<script type="text/javascript" src="jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
window.history.forward(-1);
	$(function(){
		$("#dd").dialog({
			closable:false,
			buttons:[{
				text:'登录',
				handler:function(){
					$("#login-form").form(
							'submit', {   
							    url:"userInfoController/login.do",   
							    onSubmit: function(){   
							       return $("#login-form").form("validate"); 
							    },   
							    success:function(data){ 
							       if(data=="true"){
							    	   window.location.href="index.jsp";
							       }else{
							    	   $.messager.alert('消息提示','你输入的用户名称或者密码不正确','error');
							       }   
							    } 

						});
				}
			},{
				text:'重置',
				handler:function(){
					$("#login-form").form("clear");
				}
			}]
		});
		
	});
</script>
</head>
<body>
<div id="dd" class="easyui-dialog" title="登录界面" style="width:auto;height:auto;"  
        data-options="modal:true">  
   <form action="" method="post" id="login-form">
   	<table>
   	<tr><td>用户名称</td><td><input class="easyui-validatebox" type="text" name="userName" data-options="required:true" /></td></tr>
   	<tr><td>用户密码</td><td><input class="easyui-validatebox" type="text" name="userPass" data-options="required:true" /></td></tr>
   	</table>
  </form> 
  <a href="regist.jsp">还没有注册,请点击</a>  
</div>  
</body>
</html>