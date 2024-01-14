package verticalquest.scenarios;

import java.awt.Color;

import verticalquest.Game;
import verticalquest.entities.Player;
import verticalquest.entities.Portal;
import verticalquest.entities.ZoneSpawn;
import verticalquest.resources.Spritesheet;
import verticalquest.utils.StringRender;

public class Level01 extends Scenario {

	public Level01(Player player) {
		super(Game.WIDTH, Game.HEIGHT, Spritesheet.getSpriteGUI(97, 37, 24, 22), new ZoneSpawn(100, Game.HEIGHT - 150), new Portal(Game.WIDTH - 150, Game.HEIGHT - 120), player);
	}

	@Override
	protected void setStrings() {
		super.strings.add(new StringRender("Hello, world", 80, 80, Color.WHITE));
		super.strings.add(new StringRender("Hello, world", 80, 100, Color.WHITE));
		super.strings.add(new StringRender("Hello, world", 80, 120, Color.WHITE));
	}
	
	@Override
	protected void playerSetPosition() {
		super.player.setPosition(150, super.height - 100);
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
