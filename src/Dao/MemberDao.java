package Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import Model.Member;

public class MemberDao {
	// private SqlSessionFactory sqlSessionFactory=
	// SqlSessionFactoryManager.getSqlSessionFactory()
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public int insertMember(Member member) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.getMapper(IMemberDao.class).insertMember(member);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public int deleteMember(Member member) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.getMapper(IMemberDao.class).deleteMember(member);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public int updateMember(Member member, Member orgMember) {
		if (member == null)
			return -6;
		if (orgMember == null)
			return -7;
		if (orgMember.getEmail() == null)
			return -2;

		orgMember = selectMember(new Member.Builder().email(orgMember.getEmail()).build()).get(0);

		if (member.getName() == null)
			member.setName(orgMember.getName());
		if (member.getPassword() == null)
			member.setPassword(orgMember.getPassword());
		if (member.getEmail() == null)
			member.setEmail(orgMember.getEmail());

		SqlSession session = sqlSessionFactory.openSession();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("member", member);
		params.put("orgMember", orgMember);

		try {
			return session.getMapper(IMemberDao.class).updateMember(params);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public List<Member> selectMember(Member member) {
		if (member == null)
			member = new Member.Builder().build();
		if (member.getName() == null)
			member.setName("%");
		if (member.getPassword() == null)
			member.setPassword("%");
		if (member.getEmail() == null)
			member.setEmail("%");

		SqlSession session = sqlSessionFactory.openSession();
		try {
			return session.getMapper(IMemberDao.class).selectMember(member);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null)
				session.close();
		}
	}

	// private static MemberDao instance;
	// private static final String DBName = "member";
	//
	// private JdbcTemplate jdbcTemplate;
	//
	// private MemberDao(JdbcTemplate jdbcTemplate) {
	// this.jdbcTemplate = jdbcTemplate;
	// }
	//
	// public static MemberDao getInstance(JdbcTemplate jdbcTemplate) {
	// if (instance == null)
	// instance = new MemberDao(jdbcTemplate);
	// return instance;
	// }
	//
	// public int insertMember(String name, String password, String email) {
	// String sql = "INSERT INTO " + DBName + "(name, password, email) VALUES
	// (,?,?)";
	// try {
	// return jdbcTemplate.update(sql, name, password, email);
	// } catch (Exception e) {
	// return -1;
	// }
	// }
	//
	// public int updateMember(String name, String password, String email,
	// String orgEmail) {
	// String sql = "UPDATE " + DBName + " SET name = ?, password = ?, email = ?
	// WHERE email = ?";
	// try {
	// return jdbcTemplate.update(sql, name, password, email, orgEmail);
	// } catch (Exception e) {
	// return -1;
	// }
	// }
	//
	// public int deleteMember(String email) {
	// String sql = "Delete FROM " + DBName + " WHERE email LIKE ?";
	// try {
	// return jdbcTemplate.update(sql, email);
	// } catch (Exception e) {
	// return -1;
	// }
	// }
	//
	// public List<Member> selectMember(Member member) {
	// String sql = "SELECT * FROM " + DBName + " WHERE name LIKE ? AND password
	// LIKE ? AND email LIKE ?";
	// List<Member> result = new ArrayList<Member>();
	// if (member == null)
	// member = new Member.Builder().build();
	// if (member.getName() == null)
	// member.setName("%");
	// if (member.getPassword() == null)
	// member.setPassword("%");
	// if (member.getEmail() == null)
	// member.setEmail("%");
	// try {
	// return jdbcTemplate.query(sql, rowMapper, member.getName(),
	// member.getPassword(), member.getEmail());
	// } catch (Exception e) {
	// return null;
	// }
	// }
	//
	// private NamedParameterJdbcTemplate jdbcTemplate;
	//
	// private MemberDao(NamedParameterJdbcTemplate jdbcTemplate) {
	// this.jdbcTemplate = jdbcTemplate;
	// }
	//
	// public static MemberDao getInstance(NamedParameterJdbcTemplate
	// jdbcTemplate) {
	// if (instance == null)
	// instance = new MemberDao(jdbcTemplate);
	// return instance;
	// }
	//
	// public int insertMember(String name, String password, String email) {
	// String sql = "INSERT INTO " + DBName + "(name, password, email) VALUES
	// (:name, :password, :email)";
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("name", name);
	// params.put("password", password);
	// params.put("email", email);
	//
	// try {
	// return jdbcTemplate.update(sql, params);
	// } catch (Exception e) {
	// return -1;
	// }
	// }
	// public int updateMember(String name, String password, String email,
	// String orgEmail) {
	// String sql = "UPDATE " + DBName + " SET name = :name, password =
	// :password, email = :email WHERE email = :orgEmail";
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("name", name);
	// params.put("passwod", password);
	// params.put("email", email);
	// params.put("orgEmail", orgEmail);
	// try {
	// return jdbcTemplate.update(sql, params);
	// } catch (Exception e) {
	// return -1;
	// }
	// }
	//
	// public int deleteMember(String email) {
	// String sql = "Delete FROM " + DBName + " WHERE email LIKE :email";
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("email", email);
	// try {
	// return jdbcTemplate.update(sql, params);
	// } catch (Exception e) {
	// return -1;
	// }
	// }
	//
	// public List<Member> selectMember(Member member) {
	// String sql = "SELECT * FROM " + DBName + " WHERE name LIKE :name AND
	// password LIKE :password AND email LIKE :email";
	// List<Member> result = new ArrayList<Member>();
	// if (member == null)
	// member = new Member.Builder().build();
	// if (member.getName() == null)
	// member.setName("%");
	// if (member.getPassword() == null)
	// member.setPassword("%");
	// if (member.getEmail() == null)
	// member.setEmail("%");
	//
	// Map<String, Object> params = new HashMap<String, Object>();
	// params.put("name", member.getName());
	// params.put("password", member.getPassword());
	// params.put("email", member.getEmail());
	//
	// try {
	// return jdbcTemplate.query(sql, params, rowMapper);
	// } catch (Exception e) {
	// return null;
	// }
	// }
	//
	// RowMapper<Member> rowMapper = new RowMapper<Member>() {
	// @Override
	// public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
	// // TODO Auto-generated method stub
	// Member member = new
	// Member.Builder().name(rs.getString("name")).password(rs.getString("password"))
	// .email(rs.getString("email")).build();
	// return member;
	// }
	// };
}