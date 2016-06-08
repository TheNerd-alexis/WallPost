package Model;

import java.util.Date;

public class Post {
	private Integer tindex;
	private Date writetime;
	private String email;
	private String content;

	public Post() {
	}

	public Post(Builder builder) {
		this.tindex = builder.tindex;
		this.writetime = builder.writetime;
		this.email = builder.email;
		this.content = builder.content;
	}

	public static class Builder {
		private Integer tindex;
		private Date writetime;
		private String email;
		private String content;

		public Builder index(Integer tindex) {
			this.tindex = tindex;
			return this;
		}

		public Builder writetime(Date writetime) {
			this.writetime = writetime;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public Post build() {
			return new Post(this);
		}
	}

	public Integer getTindex() {
		return tindex;
	}

	public Date getWritetime() {
		return writetime;
	}

	public String getEmail() {
		return email;
	}

	public String getContent() {
		return content;
	}

	public void setTindex(Integer tindex) {
		this.tindex = tindex;
	}

	public void setWritetime(Date writetime) {
		this.writetime = writetime;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return tindex + " " + writetime + " " + email + " " + content;
	}
}
