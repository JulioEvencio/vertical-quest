package verticalquest.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import verticalquest.GameUtil;
import verticalquest.resources.Spritesheet;

public class TextWithBackground {

	private final String text;

	private final int x;
	private final int y;

	private final int width;
	private final int height;

	private static final BufferedImage texture;

	static {
		texture = Spritesheet.getSpriteGUI(145, 65, 46, 14);
	}

	public TextWithBackground(String text, int x, int y, int width, int height) {
		this.text = text;

		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return this.width;
	}

	public void render(Graphics render) {
		render.drawImage(TextWithBackground.texture, this.x, this.y, this.width, this.height, null);

		render.setColor(Color.BLACK);
		render.setFont(GameUtil.getFontDefault());

		int textWidth = render.getFontMetrics().stringWidth(this.text);
		int textHeight = render.getFontMetrics().getHeight();

		int textX = this.x + (this.width - textWidth) / 2;
		int textY = this.y + (this.height - textHeight) / 2 + render.getFontMetrics().getAscent();

		render.drawString(this.text, textX, textY);
	}

}
