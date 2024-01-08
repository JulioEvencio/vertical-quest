package verticalquest.tiles;

import java.awt.Color;

import verticalquest.utils.Rect;

public class Floor extends Tile {

	public Floor(int x, int y) {
		super(new Rect(x, y, 50, 50), Color.BLACK);
	}

}
