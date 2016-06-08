<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wall Post</title>
</head>
<script type="text/javascript" src="script/wallpost.js"></script>
<link rel="stylesheet" type="text/css" href="css/LoginPage.css">
<body>
	<c:if test="${not empty message }">
		<script type="text/javascript">
			alert('${message}');
		</script>
	</c:if>
	<form name="frm" action="WallPostServlet" method="post">
		<input type="hidden" name="command" value="join">
		<div id="vh" class="box">
		<table class="info-table">
			<tr>
				<td>name </td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>password </td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>email </td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<input type="submit" value="join" onclick="return loginCheck()"> 
					<input type="reset" value="reset">
					<input type="button" value="to Front" onclick="location.href='WallPostServlet?command=loginPage'">
				</td>
			</tr>
		</table>
		</div>
	</form>
	<footer2>
		site design / develope by <a href="mailto:develope.nerd@gmail.com">Alexis Choi</a>
	</footer2>
</body>
</html>