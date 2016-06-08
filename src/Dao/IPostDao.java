package Dao;

import java.util.List;

import Model.Post;

public interface IPostDao {
	public int insertPost(Post post);
	public int deletePost(Post post);
	public List<Post> selectPostByContent(Post post);
	public List<Post> selectPostByIndex(Post post);
}
