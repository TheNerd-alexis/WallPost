/**
 * 
 */

function loginCheck() {
	if (document.frm.name.value.length == 0) {
		alert("이름을 입력하세요.");
		return false;
	}
	if (document.frm.password.value.length == 0) {
		alert("비밀번호를 입력하세요.");
		return false;
	}
	return true;
}
function joinCheck() {
	if (document.frm.name.value.length == 0) {
		alert("이름을 입력하세요.");
		return false;
	}
	if (document.frm.password.value.length == 0) {
		alert("비밀번호를 입력하세요.");
		return false;
	}
	if (document.frm.email.value.length == 0) {
		alert("이메일을 입력하세요.");
		return false;
	}
	return true;
}

function writeCheck(str) {
	if (document.getElementById(str).value.length != 0) {
		return true;
	}
	return false;
}

function password(){
	var password = prompt('비밀번호를 입력하세요.',"");
	if(password!=null)
		document.location.href = "WallPostServlet?command=passwordCheck&password="+password;	
}

function passCheck() {
	if (document.frm.pass.value.length == 0) {
		alert("비밀번호를 입력하세요.");
		return false;
	}
	return true;
}

function colorchange(e) {
	var colors = [ '#ffcc99', '#ffcccc', '#ffffcc', '#ccffcc', '#66ccff' ];
	$(document.getElementById(e)).css({
		'background-color' : colors[Math.floor(Math.random() * colors.length)],
	})
}