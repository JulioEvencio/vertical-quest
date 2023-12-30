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

import verticalquest.strings.StringError;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;

	public static final String VERSION = "0.1";

	public static final int SCALE = 1;
	public static final int WIDTH = 720;
	public static final int HEIGHT = 480;

	private int fps;
	private boolean showFPS;

	private final BufferedImage renderer;

	private GameStatus gameStatus;

	public Game() {
		this.addKeyListener(this);
		this.addMouseListener(this);

		this.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));

		this.fps = 0;
		this.showFPS = false;
		this.renderer = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.gameStatus = GameStatus.MAIN_MENU;
	}

	public void updateGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	private void tick() {
		// Code
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

		// Code

		render.dispose();

		Graphics graphics = bs.getDrawGraphics();
		graphics.drawImage(this.renderer, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);

		if (this.gameStatus == GameStatus.MAIN_MENU) {
			// Code
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
			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				this.tick();
				this.render();

				delta--;
				frames++;
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
		// Code
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F3) {
			this.showFPS = !this.showFPS;
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
		// Code
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (this.gameStatus == GameStatus.MAIN_MENU) {
				System.out.println("Hi!");
			}
		}
	}

	public static void exitGame() {
		System.exit(0);
	}

	public static void exitWithError(String error) {
		JOptionPane.showMessageDialog(null, error, StringError.ERROR, JOptionPane.ERROR_MESSAGE);
		Game.exitGame();
	}

}
