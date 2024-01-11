package verticalquest.scenarios;

import java.awt.event.KeyEvent;

import verticalquest.Game;
import verticalquest.entities.Player;
import verticalquest.resources.Spritesheet;
import verticalquest.tiles.Floor;
import verticalquest.tiles.Tile;
import verticalquest.utils.Rect;

public class Level01 extends Scenario {

	public Level01(Player player) {
		super(Game.WIDTH, Game.HEIGHT, Spritesheet.getSpriteGUI(97, 37, 24, 22), player);

		for (int i = 0; i < (Game.WIDTH / 50) + 1; i++) {
			super.tiles.add(new Floor(50 * i, Game.HEIGHT - 50));
		}

		super.tiles.add(new Floor(0, Game.HEIGHT - 100));
		super.tiles.add(new Floor(Game.WIDTH - 50, Game.HEIGHT - 100));

		super.tiles.add(new Floor(Game.WIDTH - 200, Game.HEIGHT - 200));

		super.player.setPosition(200, 200);
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
