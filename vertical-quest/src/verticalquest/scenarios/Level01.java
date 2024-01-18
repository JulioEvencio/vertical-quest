package verticalquest.scenarios;

import java.awt.Color;

import verticalquest.Game;
import verticalquest.entities.Player;
import verticalquest.entities.Portal;
import verticalquest.entities.ZoneSpawn;
import verticalquest.strings.StringLevel;
import verticalquest.utils.StringRender;

public class Level01 extends Scenario {

	public Level01(Player player) {
		super(Game.WIDTH, Game.HEIGHT, new ZoneSpawn(100, Game.HEIGHT - 150), new Portal(Game.WIDTH - 150, Game.HEIGHT - 120), player);
	}

	@Override
	protected void setStrings() {
		super.strings.add(new StringRender(StringLevel.LEVEL_01.getValue(), 80, 80, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.TUTORIAL_MOVE_RIGHT_LEFT.getValue(), 80, 120, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.GOAL.getValue(), 80, 160, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.TUTORIAL_PAUSE.getValue(), 80, 200, Color.WHITE));
	}
	
	@Override
	protected void playerSetPosition() {
		super.player.setPosition(150, super.height - 100);
	}

	@Override
	protected void nextLevel() {
		Game.restart(new Level02(super.player));
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new Level01(super.player);
	}

}
