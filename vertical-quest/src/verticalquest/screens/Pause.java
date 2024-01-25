package verticalquest.screens;

import java.awt.event.KeyEvent;

import verticalquest.Game;
import verticalquest.GameStatus;
import verticalquest.gui.Button;
import verticalquest.strings.StringScreen;

public class Pause extends Screen {

	public Pause() {
		super(StringScreen.PAUSE.getValue());

		super.getButtons().add(new Button(StringScreen.CONTINUE.getValue(), (Game.rendererWidth - Button.getWidthPressed()) / 2, 150, () -> Game.updateGameStatus(GameStatus.RUN)));
		super.getButtons().add(new Button(StringScreen.MENU.getValue(), (Game.rendererWidth - Button.getWidthPressed()) / 2, 250, () -> Game.updateGameStatus(GameStatus.MAIN_MENU)));
		super.getButtons().add(new Button(StringScreen.EXIT.getValue(), (Game.rendererWidth - Button.getWidthPressed()) / 2, 350, () -> Game.updateGameStatus(GameStatus.EXIT)));
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_P) {
			Game.updateGameStatus(GameStatus.RUN);
		}
	}

}
