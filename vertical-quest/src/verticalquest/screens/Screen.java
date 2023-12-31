package verticalquest.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import verticalquest.Game;
import verticalquest.GameUtil;
import verticalquest.gui.Button;
import verticalquest.gui.Text;
import verticalquest.resources.Spritesheet;
import verticalquest.strings.StringGame;

public abstract class Screen {

	private static final BufferedImage background;

	private final List<Text> texts;
	private final List<Button> buttons;

	private boolean mousePressed;
	private boolean mouseReleased;

	private int clickX;
	private int clickY;

	static {
		background = Spritesheet.getSpriteGUI(97, 37, 24, 22);
	}

	public Screen() {
		this.texts = new ArrayList<>();
		this.buttons = new ArrayList<>();

		this.mousePressed = false;
		this.mouseReleased = false;

		this.clickX = 0;
		this.clickY = 0;
	}

	protected List<Text> getTexts() {
		return this.texts;
	}

	protected List<Button> getButtons() {
		return this.buttons;
	}

	protected int getClickX() {
		return this.clickX;
	}

	protected int getClickY() {
		return this.clickY;
	}

	protected abstract void tick(Button button);

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
					this.tick(button);
				}

				button.setButtonReleased();
			});

			this.mouseReleased = false;
		}
	}

	public void render(Graphics render) {
		render.drawImage(Screen.background, 0, 0, Game.WIDTH, Game.HEIGHT, null);

		render.setColor(Color.WHITE);
		render.setFont(GameUtil.getFontTitle());

		int titleWidth = render.getFontMetrics().stringWidth(StringGame.TITLE);

		render.drawString(StringGame.TITLE, (Game.WIDTH - titleWidth) / 2, 100);

		this.texts.forEach(text -> text.render(render));
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
