package verticalquest.entities;

import java.awt.Color;

import verticalquest.Game;
import verticalquest.utils.Camera;
import verticalquest.utils.Rect;

public class Player extends Entity {

	private boolean right;
	private boolean left;

	private boolean keyJump;
	
	private boolean isJump;
	private final int jumpHeight;
	private int jumpFrames;

	public Player() {
		super(0, 0, 50, 50, 3.0, new Color(192, 203, 220));

		this.right = false;
		this.left = false;

		this.keyJump = false;
		
		this.isJump = false;
		this.jumpHeight = 75;
		this.jumpFrames = 0;
	}

	public void moveUp() {
		if (!this.keyJump && !this.isJump && !super.scenario.isFree(new Rect((int) super.x, (int) (super.y + 1), super.width, super.height))) {
			this.keyJump = true;
			this.isJump = true;
		}
	}
	
	public void stopUp() {
		this.keyJump = false;
	}

	public void moveRight() {
		this.right = true;
	}

	public void stopRight() {
		this.right = false;
	}

	public void moveLeft() {
		this.left = true;
	}

	public void stopLeft() {
		this.left = false;
	}

	private void applyGravity() {
		super.speedY += this.scenario.gravity;

		if (super.speedY > 8) {
			super.speedY = 8;
		}

		for (int i = 0; i < super.speedY; i++) {
			if (super.scenario.isFree(new Rect((int) super.x, (int) (super.y + 1.0), super.width, super.height))) {
				super.y += 1.0;
			} else {
				super.speedY = 0;
			}
		}
	}

	private void toJump() {
		if (this.jumpFrames < 9) {
			super.speedY = 8;
		} else if (this.jumpFrames < 18) {
			super.speedY = 7;
		} else if (this.jumpFrames < 27) {
			super.speedY = 6;
		} else if (this.jumpFrames < 36) {
			super.speedY = 5;
		} else if (this.jumpFrames < 45) {
			super.speedY = 4;
		} else {
			super.speedY = 3;
		}

		for (int i = 0; i < super.speedY; i++) {
			if (this.jumpFrames < this.jumpHeight && super.scenario.isFree(new Rect((int) super.x, (int) (super.y - 1.0), super.width, super.height))) {
				super.y -= 1.0;
				this.jumpFrames++;
			} else {
				super.speedY = 0;
				this.jumpFrames = 0;
				this.isJump = false;
			}
		}
	}

	private void toRight() {
		for (int i = 0; i < super.speed; i++) {
			if (this.right && super.scenario.isFree(new Rect((int) (super.x + 1.0), (int) super.y, super.width, super.height))) {
				this.x += 1.0;
			}
		}
	}

	private void toLeft() {
		for (int i = 0; i < super.speed; i++) {
			if (this.left && super.scenario.isFree(new Rect((int) (super.x - 1.0), (int) super.y, super.width, super.height))) {
				this.x -= 1.0;
			}
		}
	}

	@Override
	public void tick() {
		if (this.isJump) {
			this.toJump();
		} else {
			this.applyGravity();
		}

		this.toRight();
		this.toLeft();

		Camera.x = Camera.clamp((int) (x - (Game.getGameWidth() / 2)), 0, super.scenario.width - Game.getGameWidth());
	}

}
