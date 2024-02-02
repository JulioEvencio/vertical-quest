package verticalquest.scenarios;

import java.awt.Color;

import verticalquest.entities.Player;
import verticalquest.strings.StringLevel;
import verticalquest.utils.StringRender;

public class Victory extends Scenario {

	public Victory(Player player) {
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
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', ' ', ' ', ' ', ' ', ' ', 'J', ' ', ' ', ' ', ' ', 'P', ' ', 'W'},
			{'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F'}
		};
	}

	@Override
	protected void setStrings() {
		super.strings.add(new StringRender(StringLevel.LEVEL_VICTORY.getValue(), 80, 80, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.LEVEL_BEAT_GAME.getValue(), 80, 120, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.LEVEL_THANKS_FOR_PLAYING.getValue(), 80, 160, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.LEVEL_PRESS_P_ESC.getValue(), 80, 200, Color.WHITE));
	}

	@Override
	protected void nextLevel() {
		// Code
	}

	@Override
	protected Scenario getCurrentScenario() {
		return new Victory(super.player);
	}

}
