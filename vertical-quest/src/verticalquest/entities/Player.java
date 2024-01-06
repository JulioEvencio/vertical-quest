package verticalquest.entities;

import java.awt.Color;

public class Player extends Entity {

	private boolean up;
	private boolean right;
	private boolean left;

	public Player() {
		super(0, 0, 50, 50, 1.0, Color.BLUE);

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
		if (this.up && super.scenario.isFree(this, EntityMove.UP)) {
			// Code
		}

		if (this.right && super.scenario.isFree(this, EntityMove.RIGHT)) {
			this.x += this.speed;
		}

		if (this.left && super.scenario.isFree(this, EntityMove.LEFT)) {
			this.x -= this.speed;
		}

		if (super.scenario.isFree(this, EntityMove.DOWN)) {
			// Code
		}
	}

}
