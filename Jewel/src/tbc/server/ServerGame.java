package tbc.server;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import tbc.client.JewelClient;

public class ServerGame extends BasicGame {

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int FPS = 60;
	public static final double VERSION = 1.0;

	private JewelServer server;

	public ServerGame(JewelServer server) {
		super("server game");
		this.server = server;
	}


	@Override
	public void init(GameContainer gc) throws SlickException {
		try {
			AppGameContainer app = new AppGameContainer(new JewelClient("Jewel Server v" + VERSION));
			app.setDisplayMode(WIDTH, HEIGHT, false);
			app.setTargetFrameRate(FPS);
			app.setShowFPS(true);
			app.setForceExit(false);
			System.out.println("app starting....");
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		server.gameWorld.tick();
	}
	
	@Override
	public void render(GameContainer gc, Graphics arg1) throws SlickException {
		server.gameWorld.renderAll();
	}
	

}
