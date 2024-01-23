package verticalquest.scenarios;

import java.awt.Color;

import verticalquest.Game;
import verticalquest.entities.Player;
import verticalquest.strings.StringLevel;
import verticalquest.utils.StringRender;

public class Level03 extends Scenario {

	public Level03(Player player) {
		super(player);
	}

	@Override
	protected void initializeLevel() {
		super.map = new char[][] {
			{'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' ', 'W'},
			{'W', ' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'F', 'F', 'F', 'W'},
			{'W', ' ', ' ', 'J', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'F', 'F', 'F', 'W'},
			{'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F'}
		};
	}

	@Override
	protected void setStrings() {
		super.strings.add(new StringRender(StringLevel.LEVEL_03.getValue(), 80, 80, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.TUTORIAL_GENERATE_CLONE.getValue(), 80, 120, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.TUTORIAL_ZONE_GREEN.getValue(), 80, 160, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.TUTORIAL_RESTART.getValue(), 80, 200, Color.WHITE));
	}

	@Override
	protected void nextLevel() {
		Game.restart(new Level04(super.player));
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new Level03(super.player);
	}

}
