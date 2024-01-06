package verticalquest.scenarios;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Scenario {

	private final int width;
	private final int height;

	private final BufferedImage background;

	public Scenario(int width, int height, BufferedImage background) {
		this.width = width;
		this.height = height;

		this.background = background;
	}

	public abstract void tick();

	public void render(Graphics render) {
		render.drawImage(this.background, 0, 0, this.width, this.height, null);
	}

}
