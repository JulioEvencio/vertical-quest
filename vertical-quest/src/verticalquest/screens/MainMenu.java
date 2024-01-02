package verticalquest.screens;

import verticalquest.Game;
import verticalquest.GameStatus;
import verticalquest.gui.Button;
import verticalquest.strings.StringGame;

public class MainMenu extends Screen {

	public MainMenu() {
		super(StringGame.TITLE);

		super.getButtons().add(new Button("New Game", (Game.WIDTH - Button.getWidthPressed()) / 2, 150));
		super.getButtons().add(new Button("Credits", (Game.WIDTH - Button.getWidthPressed()) / 2, 250));
		super.getButtons().add(new Button("Exit", (Game.WIDTH - Button.getWidthPressed()) / 2, 350));
	}

	@Override
	protected void tick(Button button) {
		if (button.getText().equals("New Game")) {
			Game.updateGameStatus(GameStatus.NEW_GAME);
		} else if (button.getText().equals("Credits")) {
			Game.updateGameStatus(GameStatus.CREDITS);
		} else if (button.getText().equals("Exit")) {
			Game.updateGameStatus(GameStatus.EXIT);
		}
	}

}
