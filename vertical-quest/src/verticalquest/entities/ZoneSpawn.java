package verticalquest.entities;

import java.awt.Color;
import java.awt.Graphics;

import verticalquest.utils.Camera;
import verticalquest.utils.Rect;

public class ZoneSpawn {

	private final Rect rect;
	private final Color color;

	public ZoneSpawn(int x, int y) {
		this.rect = new Rect(x, y, 150, 100);
		this.color = new Color(84, 245, 113, 50);
	}

	public Rect getRect() {
		return this.rect;
	}

	public void render(Graphics render) {
		render.setColor(this.color);
		render.fillRect(this.rect.x - Camera.x, this.rect.y, this.rect.width, this.rect.height);
	}

}
