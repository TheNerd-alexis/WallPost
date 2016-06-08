package Action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.GenericXmlApplicationContext;

import Dao.PostDao;
import Model.Post;

public class WallPostsAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PostDao postDao = new GenericXmlApplicationContext("applicationContext.xml")
				.getBean("postDao", PostDao.class);
		List<Post> postList = postDao.selectPost(null);
		Map<Integer, ArrayList<Post>> postMap = new HashMap<Integer, ArrayList<Post>>();
		for(Post p: postList){
			if(!postMap.containsKey(p.getTindex()))
				postMap.put(p.getTindex(), new ArrayList<Post>());
			postMap.get(p.getTindex()).add(p);
		}
		request.setAttribute("postMap", postMap);
		String url = "/page/wallPostsPage.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}
}
