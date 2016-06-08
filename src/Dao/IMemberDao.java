package Dao;

import java.util.List;
import java.util.Map;

import Model.Member;

public interface IMemberDao {
	public int insertMember(Member member);

	public int deleteMember(Member member);

	public int updateMember(Map<String, Object> params);

	public List<Member> selectMember(Member member);
}
