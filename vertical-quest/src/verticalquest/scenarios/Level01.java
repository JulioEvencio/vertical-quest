package verticalquest.scenarios;

import verticalquest.Game;
import verticalquest.entities.Entity;
import verticalquest.entities.EntityMove;
import verticalquest.entities.Player;
import verticalquest.resources.Spritesheet;

public class Level01 extends Scenario {

	public Level01(Player player) {
		super(Game.WIDTH, Game.HEIGHT, Spritesheet.getSpriteGUI(97, 37, 24, 22), player);

		super.player.setPosition(200, 200);
	}

	@Override
	public boolean isFree(Entity entity, EntityMove entityMove) {
		return true;
	}

}
