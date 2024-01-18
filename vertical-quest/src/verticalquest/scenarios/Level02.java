package verticalquest.scenarios;

import java.awt.Color;

import verticalquest.Game;
import verticalquest.entities.Player;
import verticalquest.entities.Portal;
import verticalquest.entities.ZoneSpawn;
import verticalquest.resources.Spritesheet;
import verticalquest.strings.StringLevel;
import verticalquest.tiles.Floor;
import verticalquest.utils.StringRender;

public class Level02 extends Scenario {

	public Level02(Player player) {
		super(Game.WIDTH, Game.HEIGHT, Spritesheet.getSpriteGUI(97, 37, 24, 22), new ZoneSpawn(100, Game.HEIGHT - 150), new Portal(Game.WIDTH - 150, Game.HEIGHT - 120), player);

		this.tiles.add(new Floor(350, super.height - 100));
	}

	@Override
	protected void setStrings() {
		super.strings.add(new StringRender(StringLevel.LEVEL_02.getValue(), 80, 80, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.TUTORIAL_MOVE_JUMP.getValue(), 80, 120, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.TUTORIAL_FPS.getValue(), 80, 160, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.TUTORIAL_MUSIC.getValue(), 80, 200, Color.WHITE));
	}

	@Override
	protected void playerSetPosition() {
		super.player.setPosition(150, super.height - 100);
	}

	@Override
	protected void nextLevel() {
		Game.restart(new Level03(super.player));
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new Level02(super.player);
	}

}
