package verticalquest.scenarios;

import java.awt.Color;

import verticalquest.Game;
import verticalquest.entities.Player;
import verticalquest.strings.StringLevel;
import verticalquest.utils.StringRender;

public class Level02 extends Scenario {

	public Level02(Player player) {
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
			{'W', ' ', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W'},
			{'W', ' ', ' ', 'J', ' ', ' ', ' ', ' ', 'F', ' ', ' ', ' ', 'P', ' ', 'W'},
			{'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F', 'F'}
		};
	}

	@Override
	protected void setStrings() {
		super.strings.add(new StringRender(StringLevel.LEVEL_02.getValue(), 80, 80, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.TUTORIAL_MOVE_JUMP.getValue(), 80, 120, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.TUTORIAL_FPS.getValue(), 80, 160, Color.WHITE));
		super.strings.add(new StringRender(StringLevel.TUTORIAL_MUSIC.getValue(), 80, 200, Color.WHITE));
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
