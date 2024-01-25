package verticalquest.screens;

import verticalquest.Game;
import verticalquest.GameStatus;
import verticalquest.gui.Button;
import verticalquest.strings.StringGame;
import verticalquest.strings.StringScreen;

public class MainMenu extends Screen {

	public MainMenu() {
		super(StringGame.TITLE.getValue());

		super.getButtons().add(new Button(StringScreen.NEW_GAME.getValue(), (Game.getGameWidth() - Button.getWidthPressed()) / 2, 150, () -> {
			Game.startGame();
			Game.updateGameStatus(GameStatus.RUN);
		}));
		super.getButtons().add(new Button(StringScreen.CREDITS.getValue(), (Game.getGameWidth() - Button.getWidthPressed()) / 2, 250, () -> Game.updateGameStatus(GameStatus.CREDITS)));
		super.getButtons().add(new Button(StringScreen.EXIT.getValue(), (Game.getGameWidth() - Button.getWidthPressed()) / 2, 350, () -> Game.updateGameStatus(GameStatus.EXIT)));
	}

}
