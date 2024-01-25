package verticalquest.screens;

import verticalquest.Game;
import verticalquest.GameStatus;
import verticalquest.gui.Button;
import verticalquest.resources.Translation;
import verticalquest.resources.Translation.Language;
import verticalquest.strings.StringGame;
import verticalquest.strings.StringScreen;

public class SelectLanguage extends Screen {

	public SelectLanguage() {
		super(StringGame.TITLE.getValue());

		super.getButtons().add(new Button(StringScreen.ENGLISH.getValue(), (Game.rendererWidth - Button.getWidthPressed()) / 2, 150, () -> this.selectLanguage(Language.ENGLISH)));
		super.getButtons().add(new Button(StringScreen.PORTUGUESE.getValue(), (Game.rendererWidth - Button.getWidthPressed()) / 2, 250, () -> this.selectLanguage(Language.PORTUGUESE)));
		super.getButtons().add(new Button(StringScreen.SPANISH.getValue(), (Game.rendererWidth - Button.getWidthPressed()) / 2, 350, () -> this.selectLanguage(Language.SPANISH)));
	}
	
	private void selectLanguage(Language language) {
		Translation.changeTheLanguage(language);

		Game.updateGameStatus(GameStatus.MAIN_MENU);
	}

}
