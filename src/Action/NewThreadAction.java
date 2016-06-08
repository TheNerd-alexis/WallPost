package Action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.GenericXmlApplicationContext;

import Dao.PostDao;
import Model.Post;

public class NewThreadAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = (String) request.getSession().getAttribute("email");
		
		PostDao postDao = new GenericXmlApplicationContext("applicationContext.xml")
				.getBean("postDao", PostDao.class);
		
		List<Post> list = postDao.selectPost(null);
		int newIndex = 0;
		if(!(list==null||list.isEmpty())){
			newIndex = list.get(list.size() - 1).getTindex() + 1;
		}
		
		int result = postDao.insertPost(
									new Post.Builder()
									.index(newIndex)
									.email(email)
									.content("")
									.build());
		request.setAttribute("result", result);
		String url = "WallPostServlet?command=wallPosts";
		response.sendRedirect(url);
	}

}
