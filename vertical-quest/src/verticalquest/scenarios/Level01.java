package verticalquest.scenarios;

import verticalquest.Game;
import verticalquest.entities.Entity;
import verticalquest.entities.EntityMove;
import verticalquest.entities.Player;
import verticalquest.resources.Spritesheet;
import verticalquest.tiles.Floor;

public class Level01 extends Scenario {

	public Level01(Player player) {
		super(Game.WIDTH, Game.HEIGHT, Spritesheet.getSpriteGUI(97, 37, 24, 22), player);

		for (int i = 0; i < (Game.WIDTH / 50) + 1; i++) {
			super.tiles.add(new Floor(50 * i, Game.HEIGHT - 50));
		}

		super.player.setPosition(200, 200);
	}

	@Override
	public boolean isFree(Entity entity, EntityMove entityMove) {
		return true;
	}

}
