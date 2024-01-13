package verticalquest.scenarios;

import java.awt.event.KeyEvent;

import verticalquest.Game;
import verticalquest.entities.Player;
import verticalquest.resources.Spritesheet;
import verticalquest.tiles.Ceiling;
import verticalquest.tiles.Floor;
import verticalquest.tiles.Tile;
import verticalquest.tiles.Wall;
import verticalquest.utils.Rect;

public class Level01 extends Scenario {

	public Level01(Player player) {
		super(Game.WIDTH, Game.HEIGHT, Spritesheet.getSpriteGUI(97, 37, 24, 22), player);

		for (int i = 0; i < (Game.HEIGHT / 50) + 1; i++) {
			super.tiles.add(new Wall(0, 50 * i));
			super.tiles.add(new Wall(Game.WIDTH - 50, 50 * i));
		}

		for (int i = 0; i < (Game.WIDTH / 50) + 1; i++) {
			super.tiles.add(new Ceiling(50 * i, 0));
			super.tiles.add(new Floor(50 * i, Game.HEIGHT - 50));
		}

		this.setPosition();
	}

	@Override
	protected void setPosition() {
		super.player.setPosition(200, 200);
	}

	@Override
	protected boolean canGenerateClone() {
		return true;
	}

	@Override
	public boolean isFree(Rect rect) {
		for (Tile tile : super.tiles) {
			if (tile.getRect().isColliding(rect)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);

		if (e.getKeyCode() == KeyEvent.VK_R) {
			Game.restart(new Level01(this.player));
		}
	}

}
