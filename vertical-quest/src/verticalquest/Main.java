package verticalquest;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import verticalquest.strings.StringGame;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame();
			Game game = new Game();

			frame.setTitle(StringGame.TITLE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(game);
			frame.setResizable(false);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

			new Thread(game).start();
		});
	}

}
