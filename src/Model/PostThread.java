package Model;

public class PostThread {
	private int tindex;
	private String color;
	private int x_pos;
	private int y_pos;

	public PostThread() {
	}

	public PostThread(Builder builder) {
		this.tindex = builder.tindex;
		this.color = builder.color;
		this.x_pos = builder.x_pos;
		this.y_pos = builder.y_pos;
	}

	public static class Builder {
		private int tindex;
		private String color;
		private int x_pos;
		private int y_pos;

		public Builder index(int tindex) {
			this.tindex = tindex;
			return this;
		}
		public Builder color(String color) {
			this.color = color;
			return this;
		}
		public Builder x_pos(int x_pos) {
			this.x_pos = x_pos;
			return this;
		}
		public Builder y_pos(int y_pos) {
			this.y_pos = y_pos;
			return this;
		}
		public PostThread build() {
			return new PostThread(this);
		}
	}
	public int getTindex() {
		return tindex;
	}
	public void setTindex(int tindex) {
		this.tindex = tindex;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getX_pos() {
		return x_pos;
	}
	public void setX_pos(int x_pos) {
		this.x_pos = x_pos;
	}
	public int getY_pos() {
		return y_pos;
	}
	public void setY_pos(int y_pos) {
		this.y_pos = y_pos;
	}
}
