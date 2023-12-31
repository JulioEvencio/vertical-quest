package verticalquest.screens;

import java.awt.Color;

import verticalquest.Game;
import verticalquest.GameStatus;
import verticalquest.gui.Button;
import verticalquest.gui.Text;

public class Credits extends Screen {

	public Credits() {
		super();

		this.getButtons().add(new Button("Back", 50, Game.HEIGHT - Button.getHeightpressed() - 50));

		this.getTexts().add(new Text("Programmer: Júlio Igreja", 50, 150, Color.WHITE));
		this.getTexts().add(new Text("Github: https://github.com/JulioEvencio", 50, 180, Color.WHITE));

		this.getTexts().add(new Text("Sprites: Mounir Tohami", 50, 250, Color.WHITE));
		this.getTexts().add(new Text("itch.io: https://mounirtohami.itch.io/", 50, 280, Color.WHITE));
	}

	@Override
	protected void tick(Button button) {
		if (button.getText().equals("Back")) {
			Game.updateGameStatus(GameStatus.MAIN_MENU);
		}
	}

}
