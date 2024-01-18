package verticalquest.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import verticalquest.utils.Camera;
import verticalquest.utils.Rect;

public class Portal {

	private final Rect rect;

	private Color color;

	private int colorFrames;
	private boolean colorFramesInvertUpdate;

	private final int colorFramesMax;
	private final int colorFramesMin;

	public Portal(int x, int y) {
		this.rect = new Rect(x, y, 70, 70);

		this.color = new Color(67, 131, 225);

		this.colorFramesMax = 255;
		this.colorFramesMin = 150;

		this.colorFramesInvertUpdate = false;

		this.colorFrames = this.colorFramesMax;
	}

	public Rect getRect() {
		return this.rect;
	}

	public boolean nextLevel(Player player) {
		return this.rect.isColliding(player.getRect());
	}

	public void tick() {
		if (this.colorFramesInvertUpdate) {
			this.colorFrames -= 3;
		} else {
			this.colorFrames += 3;
		}

		if (this.colorFrames > this.colorFramesMax) {
			this.colorFrames = this.colorFramesMax;
			this.colorFramesInvertUpdate = true;
		}

		if (this.colorFrames < this.colorFramesMin) {
			this.colorFrames = this.colorFramesMin;
			this.colorFramesInvertUpdate = false;
		}
	}

	public void render(Graphics render) {
		this.color = new Color(67, 131, 225, this.colorFrames);

		render.setColor(this.color);
		render.fillRect(this.rect.x - Camera.x, this.rect.y, this.rect.width, this.rect.height);

		Graphics2D g = (Graphics2D) render;
		g.setStroke(new BasicStroke(2.0f));

		render.setColor(Color.BLACK);
		render.drawRect(this.rect.x - Camera.x, this.rect.y, this.rect.width, this.rect.height);

		g.setStroke(new BasicStroke(1.0f));
	}

}
