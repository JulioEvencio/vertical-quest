package verticalquest.resources;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import verticalquest.Game;
import verticalquest.strings.StringError;

public class Spritesheet {

	private static BufferedImage spritesheet;
	private static BufferedImage spritesheetGUI;

	public static BufferedImage getSprite(int x, int y, int width, int height) {
		if (Spritesheet.spritesheet == null) {
			try {
				String path = null;

				Spritesheet.spritesheet = ImageIO.read(Spritesheet.class.getResource(path));
			} catch (Exception e) {
				Game.exitWithError(StringError.ERROR_LOADING_SPRITES);
			}
		}

		return Spritesheet.spritesheet.getSubimage(x, y, width, height);
	}

	public static BufferedImage getSpriteGUI(int x, int y, int width, int height) {
		if (Spritesheet.spritesheetGUI == null) {
			try {
				String path = "/gui/gui.png";

				Spritesheet.spritesheetGUI = ImageIO.read(Spritesheet.class.getResource(path));
			} catch (Exception e) {
				Game.exitWithError(StringError.ERROR_LOADING_SPRITES);
			}
		}

		return Spritesheet.spritesheetGUI.getSubimage(x, y, width, height);
	}

}
