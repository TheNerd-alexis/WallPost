package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Action.Action;
import Controller.ActionFactory;

@WebServlet("/WallPostServlet")
public class WallPostServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command =null;
		command = request.getParameter("command");
		if(command==null){
			if(request.getSession().getAttribute("name")==null)
				command="loginPage";
			else
				command="wallPosts";
		}
		
		System.out.println("받은 요청 : " + command);
		
		if(!command.equals("joinPage")&&
				!command.equals("join")&&
				!command.equals("loginPage")&&
				!command.equals("login")&&
				request.getSession().getAttribute("email")==null){
			
			request.setAttribute("wrongaccess", "잘못된 접근입니다.");
			
			String url = "WallPostServlet?command=loginPage";
			request.getRequestDispatcher(url).forward(request, response);
			return;
		}
		
		Action action = ActionFactory.getInstance().getAction(command);
		if (action != null)
			action.execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}
