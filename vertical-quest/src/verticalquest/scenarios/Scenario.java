package verticalquest.scenarios;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import verticalquest.Game;
import verticalquest.entities.Player;
import verticalquest.entities.Portal;
import verticalquest.entities.ZoneSpawn;
import verticalquest.tiles.Ceiling;
import verticalquest.tiles.Floor;
import verticalquest.tiles.PlayerClone;
import verticalquest.tiles.Tile;
import verticalquest.tiles.Wall;
import verticalquest.utils.Rect;

public abstract class Scenario {

	protected final int width;
	protected final int height;

	private final BufferedImage background;

	protected final ZoneSpawn spawn;
	protected final Portal portal;

	protected final List<Tile> tiles;

	protected final Player player;

	public final double gravity;

	private boolean keySpace;
	private boolean pressedSpace;

	public Scenario(int width, int height, BufferedImage background, ZoneSpawn spawn, Portal portal, Player player) {
		this.width = width;
		this.height = height;

		this.background = background;

		this.spawn = spawn;
		this.portal = portal;

		this.tiles = new ArrayList<>();

		this.player = player;

		this.player.setScenario(this);

		this.gravity = 0.5;

		this.keySpace = false;
		this.pressedSpace = false;

		for (int i = 0; i < (this.height / 50) + 1; i++) {
			this.tiles.add(new Wall(0, 50 * i));
			this.tiles.add(new Wall(this.width - 50, 50 * i));
		}

		for (int i = 0; i < (this.width / 50) + 1; i++) {
			this.tiles.add(new Ceiling(50 * i, 0));
			this.tiles.add(new Floor(50 * i, this.height - 50));
		}

		this.playerSetPosition();
	}

	protected abstract void playerSetPosition();

	protected abstract void nextLevel();

	protected abstract Scenario getCurrentScenario();

	public boolean isFree(Rect rect) {
		for (Tile tile : this.tiles) {
			if (tile.getRect().isColliding(rect)) {
				return false;
			}
		}

		return true;
	}

	public void tick() {
		this.player.tick();

		if (this.portal.nextLevel(this.player)) {
			this.nextLevel();
		}

		if (this.pressedSpace) {
			if (!this.spawn.getRect().isColliding(this.player.getRect())) {
				this.tiles.add(new PlayerClone((int) this.player.getX(), (int) this.player.getY()));

				this.playerSetPosition();
			}

			this.pressedSpace = false;
		}
	}

	public void render(Graphics render) {
		render.drawImage(this.background, 0, 0, this.width, this.height, null);

		this.tiles.forEach(tile -> tile.render(render));

		this.spawn.render(render);
		this.portal.render(render);

		this.player.render(render);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			this.player.moveUp();
		}

		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.player.moveRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.player.moveLeft();
		}

		if (!this.keySpace && e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.pressedSpace = true;
			this.keySpace = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			this.player.stopUp();
		}

		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.player.stopRight();
		}

		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.player.stopLeft();
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.keySpace = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_R) {
			Game.restart(this.getCurrentScenario());
		}
	}

}
