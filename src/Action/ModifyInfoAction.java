package Action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.GenericXmlApplicationContext;

import Dao.MemberDao;
import Model.Member;

public class ModifyInfoAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String orgEmail = (String) request.getSession().getAttribute("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		MemberDao memberDao = new GenericXmlApplicationContext("applicationContext.xml")
				.getBean("memberDao", MemberDao.class);
		
		int result = memberDao.updateMember(
									new Member.Builder()
									.name(name)
									.password(password)
									.email(email)
									.build(),
									new Member.Builder()
									.email(orgEmail)
									.build());
		String url;
		if (result < 0) {
			url = "WallPostServlet?command=modifyInfo";
			request.setAttribute("message", "회원정보를 수정할 수 없습니다."+result);
		} else{
			url = "WallPostServlet?command=wallPosts";
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("email", email);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}