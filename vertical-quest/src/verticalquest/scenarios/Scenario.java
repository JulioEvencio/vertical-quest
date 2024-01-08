package verticalquest.scenarios;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import verticalquest.entities.Player;
import verticalquest.tiles.Tile;
import verticalquest.utils.Rect;

public abstract class Scenario {

	private final int width;
	private final int height;

	private final BufferedImage background;

	protected final List<Tile> tiles;

	protected final Player player;

	public Scenario(int width, int height, BufferedImage background, Player player) {
		this.width = width;
		this.height = height;

		this.background = background;

		this.tiles = new ArrayList<>();

		this.player = player;

		this.player.setScenario(this);
	}

	public abstract boolean isFree(Rect rect);

	public void tick() {
		this.player.tick();
	}

	public void render(Graphics render) {
		render.drawImage(this.background, 0, 0, this.width, this.height, null);

		this.tiles.forEach(tile -> tile.render(render));

		this.player.render(render);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			this.player.moveRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_A) {
			this.player.moveLeft();
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			this.player.stopRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_A) {
			this.player.stopLeft();
		}
	}

}
