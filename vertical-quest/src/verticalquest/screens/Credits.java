package verticalquest.screens;

import java.awt.Color;

import verticalquest.Game;
import verticalquest.GameStatus;
import verticalquest.gui.Button;
import verticalquest.gui.Text;
import verticalquest.strings.StringScreen;

public class Credits extends Screen {

	public Credits() {
		super(StringScreen.CREDITS.getValue());

		this.getTexts().add(new Text(StringScreen.PROGRAMMER.getValue(), 50, 130, Color.WHITE));
		this.getTexts().add(new Text(StringScreen.PROGRAMMER_LINK.getValue(), 50, 160, Color.WHITE));

		this.getTexts().add(new Text(StringScreen.SPRITES_CREDITS.getValue(), 50, 190, Color.WHITE));
		this.getTexts().add(new Text(StringScreen.SPRITES_CREDITS_LINK.getValue(), 50, 220, Color.WHITE));

		this.getTexts().add(new Text(StringScreen.AUDIO_MENU_CREDITS.getValue(), 50, 250, Color.WHITE));
		this.getTexts().add(new Text(StringScreen.AUDIO_MENU_CREDITS_LINK.getValue(), 50, 280, Color.WHITE));

		this.getTexts().add(new Text(StringScreen.AUDIO_GAME_CREDITS.getValue(), 50, 310, Color.WHITE));
		this.getTexts().add(new Text(StringScreen.AUDIO_GAME_CREDITS_LINK.getValue(), 50, 340, Color.WHITE));

		this.getButtons().add(new Button(StringScreen.BACK.getValue(), 50, Game.rendererHeight - Button.getHeightpressed() - 50, () -> Game.updateGameStatus(GameStatus.MAIN_MENU)));
	}

}
