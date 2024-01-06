package verticalquest.entities;

import java.awt.Color;
import java.awt.Graphics;

import verticalquest.scenarios.Scenario;

public abstract class Entity {

	protected double x;
	protected double y;

	private final int width;
	private final int height;

	protected final double speed;

	private final Color color;

	protected Scenario scenario;

	public Entity(double x, double y, int width, int height, double speed, Color color) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;

		this.speed = speed;

		this.color = color;
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
