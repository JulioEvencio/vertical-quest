package verticalquest.scenarios;

import java.awt.Color;

import verticalquest.Game;
import verticalquest.entities.Player;
import verticalquest.strings.StringLevel;
import verticalquest.utils.StringRender;

public class Level08 extends Scenario {

	public Level08(Player player) {
		super(player);
	}

	@Override
	protected void initializeLevel() {
		super.map = new char[][] {
			{'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C'},
			{'W', ' ', ' ', ' ', 'C', ' ', ' ', ' ', ' ', ' ', 'C', ' ', ' ', ' ', ' ', ' ', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', ' ', 'W'},
			{'W', ' ', ' ', ' ', 'C', ' ', ' ', ' ', ' ', ' ', 'C', ' ', ' ', ' ', ' ', ' ', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', ' ', 'W'},
			{'W', ' ', ' ', ' ', 'C', ' ', ' ', 'F', ' ', ' ', 'C', ' ', ' ', 'F', ' ', ' ', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', ' ', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', ' ', 'W'},
			{'W', 'S', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', 'J', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'P', ' ', 'W'},
			{'W', 'F', 'F', 'F', ' ', ' ', ' ', 'F', ' ', ' ', ' ', ' ', ' ', 'F', ' ', ' ', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'W'},
			{'W', 'F', 'F', 'F', 'R', 'R', 'R', 'F', 'R', 'R', 'R', 'R', 'R', 'F', 'R', 'R', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'W'},
			{'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F'}
		};
	}

	@Override
	protected void setStrings() {
		super.strings.add(new StringRender(StringLevel.LEVEL_08.getValue(), 80, 80, Color.WHITE));
	}

	@Override
	protected void nextLevel() {
		Game.restart(new Level09(super.player));
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new Level08(super.player);
	}

}
