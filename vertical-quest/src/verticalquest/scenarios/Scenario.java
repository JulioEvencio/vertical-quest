package verticalquest.scenarios;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import verticalquest.Game;
import verticalquest.GameStatus;
import verticalquest.entities.BlockRed;
import verticalquest.entities.Player;
import verticalquest.entities.Portal;
import verticalquest.entities.ZoneSpawn;
import verticalquest.tiles.Ceiling;
import verticalquest.tiles.Floor;
import verticalquest.tiles.PlayerClone;
import verticalquest.tiles.Tile;
import verticalquest.tiles.Wall;
import verticalquest.utils.Camera;
import verticalquest.utils.Rect;
import verticalquest.utils.StringRender;

public abstract class Scenario {

	public int width;

	protected char[][] map;

	private ZoneSpawn spawn;
	private Portal portal;

	private int playerX;
	private int playerY;

	protected final Player player;

	private final List<Tile> tiles;
	private final List<BlockRed> blockReds;
	protected final List<StringRender> strings;

	public final double gravity;

	private boolean keySpace;
	private boolean pressedSpace;

	public Scenario(Player player) {
		this.tiles = new ArrayList<>();
		this.blockReds = new ArrayList<>();
		this.strings = new ArrayList<>();

		this.player = player;
		this.player.setScenario(this);

		this.gravity = 0.5;

		this.keySpace = false;
		this.pressedSpace = false;

		this.player.stopUp();
		this.player.stopRight();
		this.player.stopLeft();

		this.setStrings();
		this.buildGame();
	}

	protected abstract void initializeLevel();

	private void buildGame() {
		this.initializeLevel();

		this.width = this.map[0].length * 50;

		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length; j++) {
				switch (map[i][j]) {
					case 'F':
						this.tiles.add(new Floor(50 * j, 50 * i));
						break;
					case 'C':
						this.tiles.add(new Ceiling(50 * j, 50 * i));
						break;
					case 'W':
						this.tiles.add(new Wall(50 * j, 50 * i));
						break;
					case 'R':
						this.blockReds.add(new BlockRed(50 * j, 50 * i));
						break;
					case 'P':
						this.portal = new Portal(50 * j, 50 * i - 20);
						break;
					case 'S':
						this.spawn = new ZoneSpawn(50 * j, 50 * i);
						break;
					case 'J':
						this.playerX = 50 * j;
						this.playerY = 50 * i;
						break;
				}
			}
		}

		this.playerSetPosition();
	}

	protected abstract void setStrings();

	private void playerSetPosition() {
		this.player.setPosition(this.playerX, this.playerY);
	}

	protected abstract void nextLevel();

	protected abstract Scenario getCurrentScenario();

	private void restart() {
		Game.restart(this.getCurrentScenario());
	}

	public boolean isFree(Rect rect) {
		for (Tile tile : this.tiles) {
			if (tile.getRect().isColliding(rect)) {
				return false;
			}
		}

		return true;
	}

	private boolean canRender(Rect object) {
		Rect areaCamera = new Rect(Camera.x, 0, Game.getGameWidth(), Game.getGameHeight());

		return areaCamera.isColliding(object);
	}

	public void tick() {
		this.player.tick();
		this.portal.tick();

		if (this.portal.getRect().isColliding(this.player.getRect())) {
			this.nextLevel();
		}

		this.blockReds.forEach(blockRed -> {
			if (blockRed.getRect().isColliding(this.player.getRect())) {
				this.restart();
			}

			blockRed.tick();
		});

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
		render.fillRect(0, 0, Game.getGameWidth(), Game.getGameHeight());

		this.strings.forEach(string -> {
			if (this.canRender(new Rect(string.getX(), string.getY(), string.getWidth(), string.getHeight()))) {
				string.render(render);
			}
		});

		if (this.canRender(this.spawn.getRect())) {
			this.spawn.render(render);
		}

		if (this.canRender(this.portal.getRect())) {
			this.portal.render(render);
		}

		if (this.canRender(this.player.getRect())) {
			this.player.render(render);
		}

		this.blockReds.forEach(blockRed -> {
			if (this.canRender(blockRed.getRect())) {
				blockRed.render(render);
			}
		});

		this.tiles.forEach(tile -> {
			if (this.canRender(tile.getRect())) {
				tile.render(render);
			}
		});
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
			this.restart();
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_P) {
			Game.updateGameStatus(GameStatus.PAUSE);
		}
	}

}
