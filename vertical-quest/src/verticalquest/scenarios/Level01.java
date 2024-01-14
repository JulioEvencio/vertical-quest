package verticalquest.scenarios;

import verticalquest.Game;
import verticalquest.entities.Player;
import verticalquest.entities.Portal;
import verticalquest.entities.Spawn;
import verticalquest.resources.Spritesheet;

public class Level01 extends Scenario {

	public Level01(Player player) {
		super(Game.WIDTH, Game.HEIGHT, Spritesheet.getSpriteGUI(97, 37, 24, 22), new Spawn(100, Game.HEIGHT - 150), new Portal(Game.WIDTH - 150, Game.HEIGHT - 120), player);
	}

	@Override
	protected void setPosition() {
		super.player.setPosition(150, super.height - 100);
	}

	@Override
	protected boolean canGenerateClone() {
		return true;
	}

	@Override
	protected void nextLevel() {
		System.out.println("Next Level");
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new Level01(super.player);
	}

}
