package verticalquest.entities;

import java.awt.Color;

import verticalquest.utils.Rect;

public class Player extends Entity {

	private boolean up;
	private boolean right;
	private boolean left;

	public Player() {
		super(0, 0, 50, 50, 3.0, Color.BLUE);

		this.up = false;
		this.right = false;
		this.left = false;
	}

	public void moveUp() {
		this.up = true;
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
		if (super.scenario.isFree(new Rect((int) super.x, (int) (super.y + 5.0), super.width, super.height))) {
			this.y += 5.0;
		}

		if (this.up && super.scenario.isFree(new Rect((int) super.x, (int) super.y, super.width, super.height))) {
			// Code
		}

		if (this.right && super.scenario.isFree(new Rect((int) (super.x + this.speed), (int) super.y, super.width, super.height))) {
			this.x += this.speed;
		}

		if (this.left && super.scenario.isFree(new Rect((int) (super.x - this.speed), (int) super.y, super.width, super.height))) {
			this.x -= this.speed;
		}
	}

}
