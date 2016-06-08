package Action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.GenericXmlApplicationContext;

import Dao.MemberDao;
import Model.Member;

public class JoinAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		MemberDao memberDao = new GenericXmlApplicationContext("applicationContext.xml")
				.getBean("memberDao", MemberDao.class);
		
		int result = memberDao.insertMember(
										new Member.Builder()
										.name(name)
										.password(password)
										.email(email)
										.build());
		String url;
		if(result<0){
			url = "WallPostServlet?command=joinPage";
			request.setAttribute("message", "회원가입이 불가합니다.");
		}else{
			url = "WallPostServlet?command=loginPage";
			request.setAttribute("result", 2);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}