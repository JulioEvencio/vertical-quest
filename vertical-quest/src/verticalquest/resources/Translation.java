package verticalquest.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import verticalquest.Game;
import verticalquest.strings.MessageString;
import verticalquest.strings.StringError;
import verticalquest.strings.StringScreen;

public class Translation {

	private static String language = "english";

	public enum Language {
		ENGLISH, PORTUGUESE, SPANISH;
	}

	public static void changeTheLanguage(Language language) {
		if (language == Translation.Language.PORTUGUESE) {
			Translation.language = "portuguese";
		} else if (language == Translation.Language.SPANISH) {
			Translation.language = "spanish";
		} else {
			Translation.language = "english";
		}

		try {
			Translation.toTranslation("error", new MessageString[] {
				StringError.ERROR,
				StringError.ERROR_LOADING_SPRITES,
				StringError.ERROR_LOADING_AUDIO
			});
			
			Translation.toTranslation("screen", new MessageString[] {
					StringScreen.NEW_GAME,
					StringScreen.CREDITS,
					StringScreen.EXIT,

					StringScreen.PROGRAMMER,
					StringScreen.PROGRAMMER_LINK,

					StringScreen.SPRITES_CREDITS,
					StringScreen.SPRITES_CREDITS_LINK,

					StringScreen.BACK
			});

			Game.initializeGame();
		} catch (Exception e) {
			Game.exitWithError("Error loading files");
		}
	}

	private static void toTranslation(String file, MessageString[] messageString) throws IOException {
		String fileName = String.format("/language/%s/%s.txt", Translation.language, file);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Translation.class.getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
			for (int i = 0; i < messageString.length; i++) {
				messageString[i].setValue(Translation.readLine(reader));
			}
		}
	}
	
	private static String readLine(BufferedReader reader) throws IOException {
		String content = reader.readLine();

		if (content == null) {
			throw new IOException();
		}

		return content;
	}

}
