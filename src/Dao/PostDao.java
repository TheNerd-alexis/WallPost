package Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import Model.Post;

public class PostDao{
//	private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryManager.getSqlSessionFactory();
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public int insertPost(Post post) {
		if (post.getTindex() == null)
			return -2;
		if (post.getEmail() == null)
			return -3;
		if (post.getContent() == null)
			return -4;

		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.getMapper(IPostDao.class).insertPost(post);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public int deletePost(Post post) {
		if (post.getTindex() == null)
			return -2;
		if (post.getEmail() == null)
			return -3;

		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.getMapper(IPostDao.class).deletePost(post);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public List<Post> selectPost(Post post) {
		if (post == null)
			post = new Post.Builder().build();
		
		if (post.getTindex() != null) {
			SqlSession session = sqlSessionFactory.openSession();
			try {
				return session.getMapper(IPostDao.class).selectPostByIndex(post);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				if (session != null)
					session.close();
			}
		}
		
		if(post.getEmail()==null)
			post.setEmail("%");
		if(post.getContent()==null)
			post.setContent("%");
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.getMapper(IPostDao.class).selectPostByContent(post);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null)
				session.close();
		}
	}
	// private static PostDao instance;
	// private static final String DBName = "post";
	// private static NamedParameterJdbcTemplate jdbcTemplate;
	// private static JdbcTemplate jdbcTemplate;
	//
	// private PostDao(JdbcTemplate jdbcTemplate) {
	// this.jdbcTemplate = jdbcTemplate;
	// }
	//
	// public static PostDao getInstance(JdbcTemplate jdbcTemplate) {
	// if (instance == null)
	// instance = new PostDao(jdbcTemplate);
	// return instance;
	// }
	//
	// public int insertPost(int index, int order, String content) {
	// String sql = "INSERT INTO " + DBName + "(index, order, content) VALUES
	// (?,?,?)";
	// return jdbcTemplate.update(sql, index, order, content);
	// }
	//
	// public int deletePost(int index, int order, String email) {
	// String sql = "Delete FROM " + DBName + " WHERE index = ? AND order =? AND
	// email = ?";
	// return jdbcTemplate.update(sql, index, order, email);
	// }
	//
	// public List<Post> selectPost(Post post) {
	// PreparedStatement statement = null;
	// String sql = "SELECT * FROM " + DBName + " WHERE content LIKE ? AND email
	// ?";
	// if (post.getContent() == null)
	// post.setContent("%");
	// if (post.getEmail() == null)
	// post.setEmail("%");
	//
	// return jdbcTemplate.query(sql, rowMapper, post.getContent(),
	// post.getEmail());
	// }
	//
	// public List<Post> selectPost(int index, int order) {
	// PreparedStatement statement = null;
	// String sql = "SELECT * FROM " + DBName + " WHERE index = ? AND order = ?
	// ORDER BY writedate DESC";
	//
	// return jdbcTemplate.query(sql, rowMapper, index, order);
	// }
	//
	// private PostDao(NamedParameterJdbcTemplate jdbcTemplate) {
	// this.jdbcTemplate = jdbcTemplate;
	// }
	//
	// public static PostDao getInstance(NamedParameterJdbcTemplate
	// jdbcTemplate) {
	// if (instance == null)
	// instance = new PostDao(jdbcTemplate);
	// return instance;
	// }
	//
	// public int insertPost(int index, int order, String content) {
	// String sql = "INSERT INTO " + DBName + "(tindex, torder, content) VALUES
	// (:index, :order, :content)";
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("index", index);
	// params.put("order", order);
	// params.put("content", content);
	// try {
	// return jdbcTemplate.update(sql, params);
	// } catch (Exception e) {
	// return -1;
	// }
	// }
	//
	// public int deletePost(int index, int order, String email) {
	// String sql = "Delete FROM " + DBName + " WHERE tindex = :index AND torder
	// = :order AND email = :email";
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("index", index);
	// params.put("order", order);
	// params.put("email", email);
	//
	// try {
	// return jdbcTemplate.update(sql, params);
	// } catch (Exception e) {
	// return -1;
	// }
	// }
	//
	// public List<Post> selectPost(Post post) {
	// if (post == null)
	// post = new Post.Builder().build();
	// String sql = "SELECT * FROM " + DBName + " WHERE content LIKE '%' AND
	// email LIKE '%'";
	// if (post.getContent() == null)
	// post.setContent("%");
	// if (post.getEmail() == null)
	// post.setEmail("%");
	//
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("content", post.getContent());
	// params.put("email", post.getEmail());
	//
	// try {
	// return jdbcTemplate.query(sql, params, rowMapper);
	// } catch (Exception e) {
	// return null;
	// }
	// }
	//
	// public List<Post> selectPost(int index, int order) {
	// String sql = "SELECT * FROM " + DBName + " WHERE tindex = :index AND
	// torder = :order ORDER BY writedate DESC";
	//
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("index", index);
	// params.put("order", order);
	//
	// try {
	// return jdbcTemplate.query(sql, params, rowMapper);
	// } catch (Exception e) {
	// return null;
	// }
	// }
	//
	// RowMapper<Post> rowMapper = new RowMapper<Post>() {
	// @Override
	// public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
	// // TODO Auto-generated method stub
	// Post post = new
	// Post.Builder().index(rs.getInt("tindex")).order(rs.getInt("torder"))
	// .writetime(rs.getTimestamp("writetime")).email(rs.getString("email"))
	// .content(rs.getString("content")).build();
	// return post;
	// }
	// };
}