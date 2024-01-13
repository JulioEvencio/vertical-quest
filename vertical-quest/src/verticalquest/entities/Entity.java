package verticalquest.entities;

import java.awt.Color;
import java.awt.Graphics;

import verticalquest.scenarios.Scenario;
import verticalquest.utils.Rect;

public abstract class Entity {

	protected double x;
	protected double y;

	protected final int width;
	protected final int height;

	protected final double speed;
	protected double speedY;

	private final Color color;

	protected Scenario scenario;

	public Entity(double x, double y, int width, int height, double speed, Color color) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;

		this.speed = speed;
		this.speedY = 0;

		this.color = color;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public Rect getRect() {
		return new Rect((int) this.x, (int) this.y, this.width, this.height);
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public void setPosition(int x, int y) {
		this.x = (int) x;
		this.y = (int) y;
	}

	public abstract void tick();

	public void render(Graphics render) {
		render.setColor(this.color);
		render.fillRect((int) this.x, (int) this.y, this.width, this.height);
	}

}
