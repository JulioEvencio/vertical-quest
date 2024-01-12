package verticalquest.entities;

import java.awt.Color;

import verticalquest.utils.Rect;

public class Player extends Entity {

	private boolean right;
	private boolean left;

	private boolean isJump;
	private final int jumpHeight;
	private int jumpFrames;

	public Player() {
		super(0, 0, 50, 50, 3.0, Color.BLUE);

		this.right = false;
		this.left = false;

		this.isJump = false;
		this.jumpHeight = 130;
		this.jumpFrames = 0;
	}

	public void moveUp() {
		if (!this.isJump) {
			this.isJump = true;
		}
	}

	public void stopUp() {
		// this.isJump = false;
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

		for (int i = 0; i < super.speedY; i++) {
			if (super.scenario.isFree(new Rect((int) super.x, (int) (super.y + 1), super.width, super.height))) {
				super.y += 1;
			} else {
				super.speedY = 0;
			}
		}
	}

	@Override
	public void tick() {
		this.applyGravity();

		if (this.right && super.scenario
				.isFree(new Rect((int) (super.x + super.speed), (int) super.y, super.width, super.height))) {
			this.x += super.speed;
		}

		if (this.left && super.scenario
				.isFree(new Rect((int) (super.x - super.speed), (int) super.y, super.width, super.height))) {
			this.x -= super.speed;
		}
	}

}
