package verticalquest.screens;

import verticalquest.Game;
import verticalquest.GameStatus;
import verticalquest.gui.Button;
import verticalquest.strings.StringGame;

public class MainMenu extends Screen {

	public MainMenu() {
		super(StringGame.TITLE);

		super.getButtons().add(new Button("New Game", (Game.WIDTH - Button.getWidthPressed()) / 2, 150, () -> Game.updateGameStatus(GameStatus.NEW_GAME)));
		super.getButtons().add(new Button("Credits", (Game.WIDTH - Button.getWidthPressed()) / 2, 250, () -> Game.updateGameStatus(GameStatus.CREDITS)));
		super.getButtons().add(new Button("Exit", (Game.WIDTH - Button.getWidthPressed()) / 2, 350, () -> Game.updateGameStatus(GameStatus.EXIT)));
	}

}
