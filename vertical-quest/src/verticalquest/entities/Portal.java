package verticalquest.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import verticalquest.utils.Camera;

public class Portal extends Block {

	public Portal(int x, int y) {
		super(x, y, 70, 70, new Color(67, 131, 225));
	}

	@Override
	public void render(Graphics render) {
		super.render(render);

		Graphics2D g = (Graphics2D) render;
		g.setStroke(new BasicStroke(2.0f));

		render.setColor(Color.BLACK);
		render.drawRect(super.rect.x - Camera.x, super.rect.y, super.rect.width, super.rect.height);

		g.setStroke(new BasicStroke(1.0f));
	}

}
