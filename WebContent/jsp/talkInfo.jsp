<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="../jquery/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery/themes/icon.css">
<script type="text/javascript" src="../jquery/jquery.min.js"></script>
<script type="text/javascript" src="../jquery/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../jquery/locale/easyui-lang-zh_CN.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("#PublishBtn").click(function(){
			
		$("#TalkPublish").form("submit", {
			url : "../talkInfoController/publishTalk.do",
			success : function(data) {
				if (data=="true") {
					$.messager.show({
						title:'消息提示',
						msg:'消息发布成功！',
						timeout:5000,
						showType:'slide'
					});
					$("#TalkPublish").form("clear");
				}else {
					$.messager.show({
						title:'消息提示',
						msg:'消息发布失败！',
						timeout:5000,
						showType:'slide'
					});
				}
			}

			});
		});
		
		$("#PublishBtn1").click(function(){
			
			
			$("#TalkPublish").form("submit", {
				url : "../talkInfoController/OutTalk.do",
				success : function(data) {
					if (data=="true") {
						$.messager.show({
							title:'消息提示',
							msg:'消息发布成功！',
							timeout:5000,
							showType:'slide'
						});
						$("#TalkPublish").form("clear");
					}else {
						$.messager.show({
							title:'消息提示',
							msg:'消息发布失败！',
							timeout:5000,
							showType:'slide'
						});
					}
				}

				});
			});
	})
</script>
</head>
<body>
<div align="center">
	<form  id="TalkPublish" method="post">
		<textArea cols="50" rows="10" name="talkInfo" class="easyui-validatebox"></textArea>
	</form>
	<button id="PublishBtn">发布内部消息</button>
	<shiro:hasRole name="管理员">
	<button id="PublishBtn1">发布外部消息</button>
	</shiro:hasRole>
</div>
</body>
</html>