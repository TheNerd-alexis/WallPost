package Model;

public class Member {
	private String name;
	private String password;
	private String email;

	public Member() {
	}

	public Member(Builder builder) {
		this.name = builder.name;
		this.password = builder.password;
		this.email = builder.email;
	}

	public static class Builder {
		private String name;
		private String password;
		private String email;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Member build() {
			return new Member(this);
		}
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return name + " " + password + " " + email;
	}
}
