package verticalquest.tiles;

import java.awt.Color;
import java.awt.Graphics;

import verticalquest.utils.Camera;
import verticalquest.utils.Rect;

public abstract class Tile {

	private final Rect rect;
	private final Color color;

	public Tile(Rect rect, Color color) {
		this.rect = rect;

		this.color = color;
	}

	public Rect getRect() {
		return this.rect;
	}

	public void render(Graphics render) {
		render.setColor(this.color);
		render.fillRect(this.rect.x - Camera.x, this.rect.y, this.rect.width, this.rect.height);
	}

}
