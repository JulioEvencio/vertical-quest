package verticalquest;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

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

	public static final String VERSION = "0.1";

	public static final int WIDTH = 750;
	public static final int HEIGHT = 500;

	private int fps;
	private boolean showFPS;

	private final BufferedImage renderer;

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
	}

	public Game() {
		this.addKeyListener(this);
		this.addMouseListener(this);

		this.setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));

		this.fps = 0;
		this.showFPS = false;
		this.renderer = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);

		Game.gameStatus = GameStatus.SELECT_LANGUAGE;

		this.enableAudio = true;

		Game.updateAudio(Game.audioMenu);
		Game.initializeGame();
	}

	public static void initializeGame() {
		Game.player = new Player();

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
		} else {
			Game.updateAudio(Game.audioMenu);
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

	private void tick() {
		if (this.enableAudio) {
			Game.audioNow.play();
		} else {
			Game.audioNow.stop();
		}

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

		render.dispose();

		Graphics graphics = bs.getDrawGraphics();
		graphics.drawImage(this.renderer, 0, 0, Game.WIDTH, Game.HEIGHT, null);

		if (Game.gameStatus == GameStatus.MAIN_MENU) {
			Game.mainMenu.render(graphics);
		} else if (Game.gameStatus == GameStatus.PAUSE) {
			Game.pause.render(graphics);
		} else if (Game.gameStatus == GameStatus.CREDITS) {
			Game.credits.render(graphics);
		} else if (Game.gameStatus == GameStatus.SELECT_LANGUAGE) {
			Game.selectLanguage.render(graphics);
		}

		if (this.showFPS) {
			graphics.setColor(Color.BLACK);
			graphics.fillRect(Game.WIDTH - 120, 10, 110, 30);

			graphics.setColor(Color.WHITE);
			graphics.setFont(GameUtil.getFontDefault());
			graphics.drawString(String.format("FPS: %d", this.fps), Game.WIDTH - 115, 32);

			graphics.setColor(Color.WHITE);
			graphics.drawRect(Game.WIDTH - 120, 10, 110, 30);
		}

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
			this.render();
			frames++;

			if (frames > 999999) {
				frames = 999999;
			}

			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				this.tick();

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
