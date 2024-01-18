package verticalquest.scenarios;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import verticalquest.Game;
import verticalquest.GameStatus;
import verticalquest.entities.Player;
import verticalquest.entities.Portal;
import verticalquest.entities.ZoneSpawn;
import verticalquest.tiles.Ceiling;
import verticalquest.tiles.Floor;
import verticalquest.tiles.PlayerClone;
import verticalquest.tiles.Tile;
import verticalquest.tiles.Wall;
import verticalquest.utils.Rect;
import verticalquest.utils.StringRender;

public abstract class Scenario {

	public final int width;
	public final int height;

	protected final ZoneSpawn spawn;
	protected final Portal portal;

	protected final List<Tile> tiles;
	protected final List<StringRender> strings;

	protected final Player player;

	public final double gravity;

	private boolean keySpace;
	private boolean pressedSpace;

	public Scenario(int width, int height, ZoneSpawn spawn, Portal portal, Player player) {
		this.width = width;
		this.height = height;

		this.spawn = spawn;
		this.portal = portal;

		this.tiles = new ArrayList<>();
		this.strings = new ArrayList<>();

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

		this.setStrings();
		this.playerSetPosition();

		this.player.stopUp();
		this.player.stopRight();
		this.player.stopLeft();
	}

	protected abstract void setStrings();

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
		this.portal.tick();

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
		render.setColor(Color.BLACK);
		render.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		this.strings.forEach(string -> string.render(render));

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

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_P) {
			Game.updateGameStatus(GameStatus.PAUSE);
		}
	}

}
