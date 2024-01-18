package verticalquest.utils;

import java.awt.Color;
import java.awt.Graphics;

import verticalquest.GameUtil;

public class StringRender {

	private final String text;

	private final int x;
	private final int y;

	private final Color color;

	public StringRender(String text, int x, int y, Color color) {
		this.text = text;

		this.x = x;
		this.y = y;

		this.color = color;
	}

	public void render(Graphics render) {
		render.setColor(this.color);
		render.setFont(GameUtil.getFontDefault());

		render.drawString(this.text, this.x - Camera.x, this.y);
	}

}
