package Action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.GenericXmlApplicationContext;

import Dao.MemberDao;
import Model.Member;

public class PasswordCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String password = request.getParameter("password");
		String email = (String) request.getSession().getAttribute("email");
		
		MemberDao memberDao = new GenericXmlApplicationContext("applicationContext.xml")
				.getBean("memberDao", MemberDao.class);
		List<Member> list = memberDao.selectMember(
												new Member.Builder()
												.email(email)
												.build());
		String message = new String();
		String url = "WallPostServlet?command=wallPosts";
		if (list == null && list.size() != 1) {
			message = "회원정보를 찾을 수 없습니다.";
		} else {
			if (!list.get(0).getPassword().equals(password)) {
				message = "비밀번호가 일치하지 않습니다.";
			} else {
				message = "success";
			}
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	}
}