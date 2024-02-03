package verticalquest;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import verticalquest.entities.Player;
import verticalquest.resources.Audio;
import verticalquest.scenarios.Level01;
import verticalquest.scenarios.Scenario;
import verticalquest.screens.Credits;
import verticalquest.screens.MainMenu;
import verticalquest.screens.Pause;
import verticalquest.screens.Screen;
import verticalquest.screens.SelectLanguage;
import verticalquest.strings.StringError;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private static Game currentGame;

	public static final String VERSION = "0.3 - BETA";

	private static int WIDTH = 750;
	private static int HEIGHT = 500;

	private boolean isFullscreen;
	private boolean updateFullscreen;

	private int fps;
	private boolean showFPS;

	private final BufferedImage renderer;

	public static final int rendererWidth;
	public static final int rendererHeight;

	private static GameStatus gameStatus;

	private static Scenario scenarioRestart;

	private static Screen selectLanguage;
	private static Screen mainMenu;
	private static Screen pause;
	private static Screen credits;

	private static Scenario scenario;

	private static Player player;

	private boolean enableAudio;
	private static Audio audioNow;

	private static final Audio audioMenu;
	private static final Audio audioGame;

	static {
		audioMenu = new Audio("/audios/menu.wav");
		audioGame = new Audio("/audios/game.wav");

		rendererWidth = WIDTH;
		rendererHeight = HEIGHT;
	}

	public Game() {
		this.addKeyListener(this);
		this.addMouseListener(this);

		this.setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));

		this.isFullscreen = false;
		this.updateFullscreen = true;

		this.fps = 0;
		this.showFPS = false;

		this.renderer = new BufferedImage(Game.rendererWidth, Game.rendererHeight, BufferedImage.TYPE_INT_RGB);

		Game.gameStatus = GameStatus.SELECT_LANGUAGE;

		this.enableAudio = true;

		Game.updateAudio(Game.audioMenu);
		Game.initializeGame();

		Game.currentGame = this;
	}

	private static JFrame getJFrame() {
		return (JFrame) SwingUtilities.getWindowAncestor(Game.currentGame);
	}

	public static int getGameWidth() {
		return Game.WIDTH;
	}

	public static int getGameHeight() {
		return Game.HEIGHT;
	}

	public static boolean isFullscreen() {
		return Game.getGameWidth() != Game.rendererWidth && Game.getGameHeight() != Game.rendererHeight;
	}

	public static void initializeGame() {
		Game.player = new Player();

		Game.initializeScreen();
	}

	public static void initializeScreen() {
		Game.selectLanguage = new SelectLanguage();
		Game.mainMenu = new MainMenu();
		Game.pause = new Pause();
		Game.credits = new Credits();
	}

	public static void startGame() {
		Game.scenario = new Level01(Game.player);
	}

	public static void restart(Scenario scenario) {
		Game.scenarioRestart = scenario;
		Game.gameStatus = GameStatus.RESTART;
	}

	public static void updateGameStatus(GameStatus gameStatus) {
		Game.gameStatus = gameStatus;

		if (Game.gameStatus == GameStatus.RUN || Game.gameStatus == GameStatus.PAUSE) {
			Game.updateAudio(Game.audioGame);
			Game.hideCursor();

			if (Game.gameStatus == GameStatus.PAUSE) {
				Game.showCursor();
			}
		} else {
			Game.updateAudio(Game.audioMenu);
			Game.showCursor();
		}
	}

	public static void updateAudio(Audio audio) {
		if (Game.audioNow != audio) {
			if (Game.audioNow != null) {
				Game.audioNow.stop();
			}

			Game.audioNow = audio;
		}
	}

	private void toggleFullscreen() {
		if (this.isFullscreen != this.updateFullscreen) {
			this.isFullscreen = this.updateFullscreen;

			JFrame frame = Game.getJFrame();

			frame.dispose();

			if (this.isFullscreen) {
				Game.WIDTH = Game.rendererWidth;
				Game.HEIGHT = Game.rendererHeight;

				frame.setUndecorated(false);
			} else {
				Game.WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
				Game.HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

				frame.setUndecorated(true);
			}

			this.setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));

			Game.initializeScreen();

			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

			this.requestFocus();
		}
	}

	private static void showCursor() {
		Game.getJFrame().setCursor(Cursor.getDefaultCursor());
	}

	private static void hideCursor() {
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blankCursor");
		Game.getJFrame().setCursor(blankCursor);
	}

	private void tick() {
		if (this.enableAudio) {
			Game.audioNow.play();
		} else {
			Game.audioNow.stop();
		}

		this.toggleFullscreen();

		if (Game.gameStatus == GameStatus.RUN) {
			Game.scenario.tick();
		} else if (Game.gameStatus == GameStatus.MAIN_MENU) {
			Game.mainMenu.tick();
		} else if (Game.gameStatus == GameStatus.PAUSE) {
			Game.pause.tick();
		} else if (Game.gameStatus == GameStatus.CREDITS) {
			Game.credits.tick();
		} else if (Game.gameStatus == GameStatus.SELECT_LANGUAGE) {
			Game.selectLanguage.tick();
		} else if (Game.gameStatus == GameStatus.EXIT) {
			Game.exitGame();
		} else if (Game.gameStatus == GameStatus.RESTART) {
			Game.scenario = Game.scenarioRestart;
			Game.scenarioRestart = null;
			Game.gameStatus = GameStatus.RUN;
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics render = this.renderer.getGraphics();

		render.setColor(Color.BLACK);
		render.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		if (Game.gameStatus == GameStatus.RUN) {
			Game.scenario.render(render);
		}

		if (Game.gameStatus == GameStatus.MAIN_MENU) {
			Game.mainMenu.render(render);
		} else if (Game.gameStatus == GameStatus.PAUSE) {
			Game.pause.render(render);
		} else if (Game.gameStatus == GameStatus.CREDITS) {
			Game.credits.render(render);
		} else if (Game.gameStatus == GameStatus.SELECT_LANGUAGE) {
			Game.selectLanguage.render(render);
		}

		if (this.showFPS) {
			render.setColor(Color.BLACK);
			render.fillRect(Game.rendererWidth - 120, 10, 110, 30);

			render.setColor(Color.WHITE);
			render.setFont(GameUtil.getFontDefault());
			render.drawString(String.format("FPS: %d", this.fps), Game.rendererWidth - 115, 32);

			render.setColor(Color.WHITE);
			render.drawRect(Game.rendererWidth - 120, 10, 110, 30);
		}

		render.dispose();

		Graphics graphics = bs.getDrawGraphics();
		graphics.drawImage(this.renderer, 0, 0, Game.WIDTH, Game.HEIGHT, null);

		bs.show();
	}

	@Override
	public void run() {
		this.requestFocus();

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000.0 / amountOfTicks;
		double delta = 0.0;

		double timer = System.currentTimeMillis();

		int frames = 0;

		while (true) {
			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				this.tick();
				this.render();

				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				timer = System.currentTimeMillis();

				this.fps = frames;
				frames = 0;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (Game.gameStatus == GameStatus.RUN) {
			Game.scenario.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Game.gameStatus == GameStatus.RUN) {
			Game.scenario.keyReleased(e);
		} else if (Game.gameStatus == GameStatus.PAUSE) {
			((Pause) Game.pause).keyReleased(e);
		}

		if (e.getKeyCode() == KeyEvent.VK_F2) {
			this.updateFullscreen = !this.updateFullscreen;
		}

		if (e.getKeyCode() == KeyEvent.VK_F3) {
			this.showFPS = !this.showFPS;
		}

		if (e.getKeyCode() == KeyEvent.VK_F4) {
			this.enableAudio = !this.enableAudio;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Code
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Code
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Code
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Code
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Game.gameStatus == GameStatus.MAIN_MENU) {
			Game.mainMenu.mousePressed(e);
		} else if (Game.gameStatus == GameStatus.PAUSE) {
			Game.pause.mousePressed(e);
		} else if (Game.gameStatus == GameStatus.CREDITS) {
			Game.credits.mousePressed(e);
		} else if (Game.gameStatus == GameStatus.SELECT_LANGUAGE) {
			Game.selectLanguage.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Game.gameStatus == GameStatus.MAIN_MENU) {
			Game.mainMenu.mouseReleased(e);
		} else if (Game.gameStatus == GameStatus.PAUSE) {
			Game.pause.mouseReleased(e);
		} else if (Game.gameStatus == GameStatus.CREDITS) {
			Game.credits.mouseReleased(e);
		} else if (Game.gameStatus == GameStatus.SELECT_LANGUAGE) {
			Game.selectLanguage.mouseReleased(e);
		}
	}

	public static void exitGame() {
		System.exit(0);
	}

	public static void exitWithError(String error) {
		JOptionPane.showMessageDialog(null, error, StringError.ERROR.getValue(), JOptionPane.ERROR_MESSAGE);
		Game.exitGame();
	}

}
