package Action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.GenericXmlApplicationContext;

import Dao.MemberDao;
import Model.Member;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("name") == null) {
			request.setAttribute("wrongaccess", "잘못된 접근입니다.");

			String url = "WallPostServlet?command=loginPage";
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		MemberDao memberDao = new GenericXmlApplicationContext("applicationContext.xml")
				.getBean("memberDao", MemberDao.class);
		
		List<Member> list = memberDao.selectMember(
													new Member
													.Builder()
													.name(request.getParameter("name"))
													.build());
		if (list == null || list.size() != 1) {
			request.setAttribute("result", -1);
		} else {
			if (list.get(0).getPassword().equals(request.getParameter("password"))) {
				request.getSession().setAttribute("name", request.getParameter("name"));
				request.getSession().setAttribute("email", list.get(0).getEmail());
			} else
				request.setAttribute("result", -2);
		}
		String url = "WallPostServlet?command=wallPosts";
		request.getRequestDispatcher(url).forward(request, response);
	}
}
