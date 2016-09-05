package tbc.client;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.BufferedImageUtil;

import tbc.game.World;
import tbc.game.states.Game;
import tbc.game.states.MainMenu;

public class JewelClient extends StateBasedGame {

	public JewelClient(String title) {
		super(title);
	}

	static Thread t;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int FPS = 60;
	public static final double VERSION = 1.0;
	static boolean runnung = true;

	static ImageLoader load;
	Image back;

	private GameState mainMenu, game;

	public static void main(String args[]) {
		// start thread to receive packets at all time

		try {
			AppGameContainer app = new AppGameContainer(
					new JewelClient("Jewel v" + VERSION));
			app.setDisplayMode(WIDTH, HEIGHT, false);
			app.setTargetFrameRate(FPS);
			app.setShowFPS(true);
			app.setForceExit(false);
			System.out.println("app stargin....");
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean closeRequested() {
		if (game != null){
			((Game) game).close();
		}
	
		return super.closeRequested();
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		mainMenu = new MainMenu();
		game = new Game();

		this.addState(mainMenu);
		this.addState(game);

	}
}
