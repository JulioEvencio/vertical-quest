package verticalquest.scenarios;

import java.awt.Color;

import verticalquest.Game;
import verticalquest.entities.Player;
import verticalquest.strings.StringLevel;
import verticalquest.utils.StringRender;

public class Level04 extends Scenario {

	public Level04(Player player) {
		super(player);
	}

	@Override
	protected void initializeLevel() {
		super.map = new char[][] {
			{'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', ' ', 'J', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' ', 'W'},
			{'W', 'F', 'F', 'F', 'F', 'F', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'F', 'F', 'F', 'W'},
			{'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F'}
		};
	}

	@Override
	protected void setStrings() {
		super.strings.add(new StringRender(StringLevel.LEVEL_04.getValue(), 80, 80, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.TUTORIAL_BLOCK_RED.getValue(), 80, 120, Color.WHITE));
	}

	@Override
	protected void nextLevel() {
		Game.restart(new Level05(super.player));
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new Level04(super.player);
	}

}
