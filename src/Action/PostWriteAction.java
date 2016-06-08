package Action;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.GenericXmlApplicationContext;

import Dao.PostDao;
import Model.Post;

public class PostWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		String email = (String) request.getSession().getAttribute("email");
		Integer index = Integer.valueOf(request.getParameter("index"));
		String content = request.getParameter("content");
		StringTokenizer token = new StringTokenizer(content, "\n");
		StringBuilder temp = new StringBuilder();
		while (token.hasMoreTokens()) {
			temp.append(token.nextToken());
			temp.append("<br>");
		}
	
		content = temp.toString().substring(0, temp.length()-4);
		PostDao postDao = new GenericXmlApplicationContext("applicationContext.xml")
				.getBean("postDao", PostDao.class);
		
		int result = postDao.insertPost(
									new Post.Builder()
									.index(index)
									.email(email)
									.content(content)
									.build());
		
		request.setAttribute("result", result);
		String url = "WallPostServlet?command=wallPosts";
		// request.getRequestDispatcher(url).forward(request, response);
		response.sendRedirect(url);
	}
}
