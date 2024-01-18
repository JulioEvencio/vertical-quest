package verticalquest.utils;

import java.awt.Color;
import java.awt.Graphics;

import verticalquest.GameUtil;

public class StringRender {

	private final String text;

	private final int x;
	private final int y;

	private int width;
	private int height;

	private final Color color;

	public StringRender(String text, int x, int y, Color color) {
		this.text = text;

		this.x = x;
		this.y = y;

		this.color = color;
	}

	public String getText() {
		return this.text;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void render(Graphics render) {
		render.setColor(this.color);
		render.setFont(GameUtil.getFontDefault());

		this.width = render.getFontMetrics().stringWidth(this.text);
		this.height = render.getFontMetrics().getHeight();

		render.drawString(this.text, this.x - Camera.x, this.y);
	}

}
