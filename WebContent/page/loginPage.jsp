<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wall Post</title>
<script type="text/javascript" src="script/wallpost.js"></script>
<link rel="stylesheet" type="text/css" href="css/LoginPage.css">
</head>
<body>
	<c:if test="${not empty wrongaccess}">
		<script type="text/javascript">
			alert('${wrongaccess}');
		</script>
	</c:if>
	<c:if test="${not empty result}">
		<script type="text/javascript">
			var message = null;
			if('${result}'==-1)
				message = "등록되지 않은 회원입니다.";
			else if('${result}'==-2)
				message = "이름과 비밀번호가 일치하지 않습니다.";
			else if('${result}'==1)
				message = "로그인에 성공했습니다.";
			else if('${result}'==2)
				message = "회원가입에 성공했습니다.";
			alert(message);
		</script>
	</c:if>
	<form name="frm" action="WallPostServlet" method="post">
	<input type="hidden" name="command" value="login">
	<div id="vh" class="box">
		<table class="effects-table">
			<tr style="height:110px"><td align="center">
				<span class="font-effect-3d">Post U</span>
			<td></tr>
			<tr><td align="center"><input type="text" name="name" placeholder="이름"></td></tr>
			<tr><td align="center"><input type="password" name="password" style="height: 30px; width:250px" placeholder="비밀번호"></td></tr>
			<tr><td colspan=2 align="center">
				<input type="submit" value="login" onclick="return loginCheck()">
				<input type="button" value="join" onclick="location.href='WallPostServlet?command=joinPage'">
			</td></tr>
		</table>
	</div>
	</form>
	<footer2>
		site design / develope by <a href="mailto:develope.nerd@gmail.com">Alexis Choi</a>
	</footer2>
</div>
</body>
</html>