package verticalquest.screens;

import verticalquest.Game;
import verticalquest.GameStatus;
import verticalquest.gui.Button;
import verticalquest.strings.StringGame;
import verticalquest.strings.StringScreen;

public class MainMenu extends Screen {

	public MainMenu() {
		super(StringGame.TITLE);

		super.getButtons().add(new Button(StringScreen.NEW_GAME, (Game.WIDTH - Button.getWidthPressed()) / 2, 150, () -> Game.updateGameStatus(GameStatus.NEW_GAME)));
		super.getButtons().add(new Button(StringScreen.CREDITS, (Game.WIDTH - Button.getWidthPressed()) / 2, 250, () -> Game.updateGameStatus(GameStatus.CREDITS)));
		super.getButtons().add(new Button(StringScreen.EXIT, (Game.WIDTH - Button.getWidthPressed()) / 2, 350, () -> Game.updateGameStatus(GameStatus.EXIT)));
	}

}
