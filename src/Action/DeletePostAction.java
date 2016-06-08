package Action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.GenericXmlApplicationContext;

import Dao.PostDao;
import Model.Post;

public class DeletePostAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = (String) request.getSession().getAttribute("email");
		int index = Integer.valueOf(request.getParameter("index"));
		String time = request.getParameter("writetime");
		Date writetime = null;
		if (time != null) {
			try {
				DateFormat df = new SimpleDateFormat("EEE MMM dd hh:mm:ss Z yyyy", Locale.US);
				writetime = df.parse(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
		PostDao postDao = new GenericXmlApplicationContext("applicationContext.xml").getBean("postDao", PostDao.class);

		int result = postDao.deletePost(new Post.Builder().email(email).index(index).writetime(writetime).build());

		request.setAttribute("result", result);
		String url = "WallPostServlet?command=wallPosts";
		response.sendRedirect(url);
	}
}