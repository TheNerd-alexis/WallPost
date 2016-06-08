import Dao.MemberDao;
import Dao.PostDao;
import Model.Member;
import Model.Post;

public class test {
	public static void main(String[] args) {
		// ApplicationContext context
		// = new
		// GenericXmlApplicationContext("Controller/applicationContext.xml");
		// DBManager dbManager = context.getBean("dbManager", DBManager.class);

		MemberDao dao = new MemberDao();
		Member member = new Member.Builder().name("hi").email("1234@naver.com").password("123456").build();
		System.out.println(dao.insertMember(member));
		for (Member d : dao.selectMember(null))
			System.out.println(d);

		dao.updateMember(new Member.Builder().name("mayday").email("newemail").password("pw").build(), member);
		for (Member d : dao.selectMember(null))
			System.out.println(d);
		
		PostDao postDao = new PostDao();

		for (Post p : postDao.selectPost(null))
			System.out.println(p);
	}
}
