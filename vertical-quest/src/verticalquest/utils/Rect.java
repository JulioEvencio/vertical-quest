package verticalquest.utils;

public class Rect {

	public final int x;
	public final int y;

	public final int width;
	public final int height;

	public Rect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;
	}

	public boolean isColliding(Rect rect) {
		boolean collisionX = (this.x < rect.x + rect.width) && (this.x + this.width > rect.x);
		boolean collisionY = (this.y < rect.y + rect.height) && (this.y + this.height > rect.y);

		return collisionX && collisionY;
	}

}
