package verticalquest.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import verticalquest.Game;
import verticalquest.GameStatus;
import verticalquest.GameUtil;
import verticalquest.gui.Button;
import verticalquest.resources.Spritesheet;
import verticalquest.strings.StringGame;

public class MainMenu {

	private final BufferedImage background;

	private final List<Button> buttons;

	private boolean mousePressed;
	private boolean mouseReleased;

	private int clickX;
	private int clickY;

	public MainMenu() {
		this.background = Spritesheet.getSpriteGUI(97, 37, 24, 22);

		this.buttons = new ArrayList<>();

		this.buttons.add(new Button("New Game", (Game.WIDTH - Button.getWidthPressed()) / 2, 100));
		this.buttons.add(new Button("Credits", (Game.WIDTH - Button.getWidthPressed()) / 2, 200));
		this.buttons.add(new Button("Exit", (Game.WIDTH - Button.getWidthPressed()) / 2, 300));

		this.mousePressed = false;
		this.mouseReleased = false;

		this.clickX = 0;
		this.clickY = 0;
	}

	public void tick() {
		if (this.mousePressed) {
			this.buttons.forEach(button -> {
				if (button.wasClicked(this.clickX, this.clickY)) {
					button.setButtonPressed();
				}
			});

			this.mousePressed = false;
		}

		if (this.mouseReleased) {
			this.buttons.forEach(button -> {
				if (button.wasClicked(this.clickX, this.clickY)) {
					GameStatus gameStatus = null;

					if (button.getText().equals("New Game")) {
						gameStatus = GameStatus.NEW_GAME;
					} else if (button.getText().equals("Credits")) {
						gameStatus = GameStatus.CREDITS;
					} else if (button.getText().equals("Exit")) {
						gameStatus = GameStatus.EXIT;
					}

					Game.updateGameStatus(gameStatus);
				}

				button.setButtonReleased();
			});

			this.mouseReleased = false;
		}
	}

	public void render(Graphics render) {
		render.drawImage(this.background, 0, 0, Game.WIDTH, Game.HEIGHT, null);

		render.setColor(Color.WHITE);
		render.setFont(GameUtil.getFontTitle());

		int titleWidth = render.getFontMetrics().stringWidth(StringGame.TITLE);

		render.drawString(StringGame.TITLE, (Game.WIDTH - titleWidth) / 2, 70);

		this.buttons.forEach(button -> button.render(render));
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.mousePressed = true;

			this.clickX = e.getX();
			this.clickY = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.mouseReleased = true;

			this.clickX = e.getX();
			this.clickY = e.getY();
		}
	}

}
