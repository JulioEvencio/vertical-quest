package verticalquest.entities;

import java.awt.Color;
import java.awt.Graphics;

import verticalquest.utils.Camera;
import verticalquest.utils.Rect;

public abstract class Block {

	protected final Rect rect;

	private Color color;

	private int colorFrames;
	private boolean colorFramesInvertUpdate;

	private final int colorFramesMax;
	private final int colorFramesMin;

	public Block(int x, int y, int width, int height, Color color) {
		this.rect = new Rect(x, y, width, height);

		this.color = color;

		this.colorFramesMax = 255;
		this.colorFramesMin = 150;

		this.colorFramesInvertUpdate = false;

		this.colorFrames = this.colorFramesMax;
	}

	public Rect getRect() {
		return this.rect;
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
		this.color = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), this.colorFrames);

		render.setColor(this.color);
		render.fillRect(this.rect.x - Camera.x, this.rect.y, this.rect.width, this.rect.height);
	}

}
