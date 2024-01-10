package verticalquest.entities;

import java.awt.Color;

import verticalquest.utils.Rect;

public class Player extends Entity {

	private boolean up;
	private boolean right;
	private boolean left;

	private boolean isJump;
	private int jumpHeight;
	private int jumpFrames;
	private int jumpVel;

	public Player() {
		super(0, 0, 50, 50, 3.0, Color.BLUE);

		this.up = false;
		this.right = false;
		this.left = false;

		this.isJump = false;
		this.jumpHeight = 130;
		this.jumpFrames = 0;
		this.jumpVel = 5;
	}

	public void moveUp() {
		if (!this.isJump) {
			this.up = true;
		}
	}

	public void stopUp() {
		this.up = false;
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

	@Override
	public void tick() {
		if (!this.isJump) {
			for (int i = 0; i < 5; i++) {
				if (super.scenario.isFree(new Rect((int) super.x, (int) (super.y + 1.0), super.width, super.height))) {
					this.y += 1.0;
				} else {
					break;
				}
			}
		}

		if (this.up && !super.scenario.isFree(new Rect((int) super.x, (int) super.y + 1, super.width, super.height))) {
			this.isJump = true;
		}
		
		if (this.isJump && super.scenario.isFree(new Rect((int) super.x, (int) super.y - this.jumpVel, super.width, super.height))) {
			super.y -= this.jumpVel;
			this.jumpFrames += this.jumpVel;
			
			if (this.jumpFrames > this.jumpHeight - 10 && this.jumpFrames < this.jumpHeight) {
				this.jumpVel = 1;
			}
			
			if (this.jumpFrames == this.jumpHeight) {
				this.isJump = false;
				this.jumpFrames = 0;
				this.jumpVel = 5;
			}
		} else {
			this.isJump = false;
			this.jumpFrames = 0;
		}

		if (this.right && super.scenario.isFree(new Rect((int) (super.x + super.speed), (int) super.y, super.width, super.height))) {
			this.x += super.speed;
		}

		if (this.left && super.scenario.isFree(new Rect((int) (super.x - super.speed), (int) super.y, super.width, super.height))) {
			this.x -= super.speed;
		}
	}

}
