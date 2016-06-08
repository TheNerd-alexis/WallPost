<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wall Post</title>
<link rel="stylesheet" type="text/css" href="css/WallPost.css">
<link rel="stylesheet" type="text/css" href="css/Note.css">
<script type="text/javascript" src="script/wallpost.js"></script>

<!-- <link rel="stylesheet" -->
<!-- 	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> -->
<!-- <script src="//code.jquery.com/jquery-1.10.2.js"></script> -->
<!-- <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<!-- <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script> -->
<!-- <script -->
<!-- 	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->

<!--   <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> -->
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<!--   <link rel="stylesheet" href="/resources/demos/style.css"> -->
<script type="text/javascript">
	$(function() {
		$('textarea').each(function() {
			h(this);
		}).on('input', function() {
			h(this);
		});
	})
	function h(e) {
		$(e).css({
			'height' : 'auto',
			'overflow-y' : 'hidden'
		}).height(e.scrollHeight);
	}
</script>
</head>
<body>
	<c:if test="${not empty result }">
		<script type="text/javascript">
			if ('${result}' < 0)
				alert('글을 작성할 수 없습니다.');
		</script>
	</c:if>
	<c:if test="${not empty message }">
		<c:choose>
			<c:when test="${message=='success' }">
				<script type="text/javascript">
					document.location.href = "WallPostServlet?command=modifyInfoForm";
				</script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript">
					alert('${message}');
					document.location.href = "WallPostServlet?command=wallPosts";
				</script>
			</c:otherwise>
		</c:choose>
	</c:if>
	<ul>
		<c:forEach var="postthread" items="${postMap }">
			<li>
				<div class='colour<%=(int)(Math.random()*5)%>'
					id="postIt${postthread.key }">
					<c:forEach var="post" items="${postthread.value}"
						varStatus="status">
						<c:if test="${not empty post.content }">
							<p1>${post.content }</p1>
							<fmt:formatDate value="${post.writetime}" var="formattedDate"
								type="date" pattern="YY.MM.dd hh:mm" />
							<p2> ${formattedDate }</p2>
							<c:if test="${post.email.equals(sessionScope.email)}">
								<a
									href="WallPostServlet?command=deletePost&index=${postthread.key}&writetime=${post.writetime }"
									style="text-decoration: none">X</a>
							</c:if>
							<br>
						</c:if>
					</c:forEach>
					<form name="frm${postthread.key}" action="WallPostServlet"
						method="post">
						<input type="hidden" name="command" value="postWrite" /> <input
							type="hidden" name="index" value="${postthread.key }" />
						<textarea name="content" id="content${postthread.key}"
							style="font-family: 'Nanum Pen Script', sans-serif; font-size: 20px;"></textarea>
						<input type="submit" value="작성"
							onclick="return writeCheck('content${postthread.key}');"
							style="float: right;" /> <input type="button" value="삭제"
							onclick="location.href='WallPostServlet?command=deletePost&index=${postthread.key}'"
							style="float: right;">
					</form>
				</div>
			</li>
		</c:forEach>
	</ul>
	<footer1> <input type="button" value="My Info"
		onclick="password()" style="float: right; margin: 10px;" /> <input
		type="button" value="Log Out"
		onclick="location.href='WallPostServlet?command=logOut'"
		style="float: right; margin: 10px;" /> <input type="button"
		value="New Post"
		onclick="location.href='WallPostServlet?command=newThread'"
		style="float: right; margin: 10px;" /> </footer1>
	<footer2> site design / develope by <a
		href="mailto:develope.nerd@gmail.com">Alexis Choi</a> </footer2>
</body>
</html>
