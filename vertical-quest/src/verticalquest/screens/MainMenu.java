package verticalquest.screens;

import verticalquest.Game;
import verticalquest.GameStatus;
import verticalquest.gui.Button;
import verticalquest.strings.StringGame;
import verticalquest.strings.StringScreen;

public class MainMenu extends Screen {

	public MainMenu() {
		super(StringGame.TITLE.getValue());

		super.getButtons().add(new Button(StringScreen.NEW_GAME.getValue(), (Game.WIDTH - Button.getWidthPressed()) / 2, 150, () -> Game.updateGameStatus(GameStatus.NEW_GAME)));
		super.getButtons().add(new Button(StringScreen.CREDITS.getValue(), (Game.WIDTH - Button.getWidthPressed()) / 2, 250, () -> Game.updateGameStatus(GameStatus.CREDITS)));
		super.getButtons().add(new Button(StringScreen.EXIT.getValue(), (Game.WIDTH - Button.getWidthPressed()) / 2, 350, () -> Game.updateGameStatus(GameStatus.EXIT)));
	}

}
