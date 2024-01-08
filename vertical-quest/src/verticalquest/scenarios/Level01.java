package verticalquest.scenarios;

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

}
