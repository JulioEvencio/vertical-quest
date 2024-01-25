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

public abstract class Screen {

	private static final BufferedImage background;

	private final String title;

	private final List<Text> texts;
	private final List<Button> buttons;

	private boolean mousePressed;
	private boolean mouseReleased;

	private int clickX;
	private int clickY;

	static {
		background = Spritesheet.getSpriteGUI(97, 37, 24, 22);
	}

	public Screen(String title) {
		this.title = title;

		this.texts = new ArrayList<>();
		this.buttons = new ArrayList<>();

		this.mousePressed = false;
		this.mouseReleased = false;

		this.clickX = 0;
		this.clickY = 0;

		this.texts.add(new Text(String.format("Version: %s", Game.VERSION), 50, 50, Color.WHITE));
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
					button.onClick();
				}

				button.setButtonReleased();
			});

			this.mouseReleased = false;
		}
	}

	public void render(Graphics render) {
		render.drawImage(Screen.background, 0, 0, Game.getGameWidth(), Game.getGameHeight(), null);

		render.setColor(Color.WHITE);
		render.setFont(GameUtil.getFontTitle());

		int titleWidth = render.getFontMetrics().stringWidth(this.title);

		render.drawString(this.title, (Game.getGameWidth() - titleWidth) / 2, 100);

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
