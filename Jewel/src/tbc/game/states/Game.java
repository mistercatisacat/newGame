package tbc.game.states;

import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tbc.client.ImageLoader;
import tbc.client.NetworkStuff;
import tbc.game.World;
import tbc.game.entities.PlayerEntitity;
import tbc.util.Point;



public class Game extends BasicGameState{
	
	public static NetworkStuff net;
	public static ImageLoader imLoad = new ImageLoader("assets/");
	
	String ip;
	int port;
	static Thread t;
	PlayerEntitity play;
	
	World world;
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		world = new World();
		Point center = new Point(container.getWidth() / 2, container.getHeight() / 2);
		ip = JOptionPane.showInputDialog("please input ip");
		port = Integer.parseInt(JOptionPane.showInputDialog("please input port"));
		startNetThread(ip, port);
		play = new PlayerEntitity(container.getInput(), center, 5);
		world.addEntity(play);
		super.enter(container, game);
	}
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		world.renderAll();
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if (arg0.getInput().isKeyDown(Input.KEY_X)){
			net.stop();
			arg0.exit();
		}
		world.tick();

	}
	
	@Override
	public int getID() {
		return 1;
	}
	public static void startNetThread(String ip, int port) {
		net = new NetworkStuff(ip, port);
		t = new Thread(net);
		t.start();


	}

}
