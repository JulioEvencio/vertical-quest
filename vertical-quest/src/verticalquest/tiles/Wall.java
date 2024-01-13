package verticalquest.tiles;

import java.awt.Color;

import verticalquest.utils.Rect;

public class Wall extends Tile {

	public Wall(int x, int y) {
		super(new Rect(x, y, 50, 50), new Color(58, 68, 102));
	}

}
