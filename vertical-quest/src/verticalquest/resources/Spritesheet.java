package verticalquest.resources;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import verticalquest.Game;
import verticalquest.strings.StringError;

public class Spritesheet {

	private static final BufferedImage spritesheetGUI;

	static {
		BufferedImage auxSpritesheetGUI = null;

		try {
			auxSpritesheetGUI = ImageIO.read(Spritesheet.class.getResource("/gui/gui.png"));
		} catch (Exception e) {
			Game.exitWithError(StringError.ERROR_LOADING_SPRITES.getValue());
		}

		spritesheetGUI = auxSpritesheetGUI;
	}

	public static BufferedImage getSpriteGUI(int x, int y, int width, int height) {
		return Spritesheet.spritesheetGUI.getSubimage(x, y, width, height);
	}

}
