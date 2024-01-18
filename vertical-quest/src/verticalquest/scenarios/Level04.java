package verticalquest.scenarios;

import java.awt.Color;

import verticalquest.Game;
import verticalquest.entities.Player;
import verticalquest.entities.Portal;
import verticalquest.entities.ZoneSpawn;
import verticalquest.strings.StringLevel;
import verticalquest.tiles.Floor;
import verticalquest.utils.StringRender;

public class Level04 extends Scenario {

	public Level04(Player player) {
		super(Game.WIDTH * 2, Game.HEIGHT, new ZoneSpawn(100, Game.HEIGHT - 150), new Portal(Game.WIDTH - 150, Game.HEIGHT - 220), player);

		this.tiles.add(new Floor(super.width - 100, super.height - 100));
		this.tiles.add(new Floor(super.width - 150, super.height - 100));
		this.tiles.add(new Floor(super.width - 200, super.height - 100));
		
		this.tiles.add(new Floor(super.width - 100, super.height - 150));
		this.tiles.add(new Floor(super.width - 150, super.height - 150));
		this.tiles.add(new Floor(super.width - 200, super.height - 150));
	}

	@Override
	protected void setStrings() {
		super.strings.add(new StringRender(StringLevel.LEVEL_04.getValue(), 80, 80, Color.WHITE));
	}
	
	@Override
	protected void playerSetPosition() {
		super.player.setPosition(150, super.height - 100);
	}

	@Override
	protected void nextLevel() {
		Game.restart(new Level04(super.player));
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new Level04(super.player);
	}

}