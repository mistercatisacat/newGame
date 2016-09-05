package tbc.game.states;

import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tbc.game.entities.Entity;

import tbc.client.ImageLoader;
import tbc.client.NetworkStuff;
import tbc.game.World;
import tbc.game.entities.EntityPlayer;
import tbc.util.Point;

public class Game extends BasicGameState {

	public final static int STATE_ID = 1;

	private NetworkStuff net;
	public static ImageLoader imLoad = new ImageLoader("assets/");

	String ip;
	int port;
	static Thread t;
	private World world;
	private GameContainer gc;

	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		world = new World();
		Point center = new Point(container.getWidth() / 2,
				container.getHeight() / 2);
		ip = JOptionPane.showInputDialog("please input ip", "localhost");
		port = Integer.parseInt(
				JOptionPane.showInputDialog("please input port", 9999));
		gc = container;
		startNetThread(ip, port);
		while (!net.serverLoaded()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
		world.loadAll();
		super.enter(container, game);
	}

	@Override
	public void init(GameContainer container, StateBasedGame arg1)
			throws SlickException {

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		world.renderAll();
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		if (arg0.getInput().isKeyDown(Input.KEY_X)) {
			close();
		}
		world.tick();

	}

	@Override
	public int getID() {
		return STATE_ID;
	}
	
	public void close(){
		net.stop();		
	}
	
	public void exitGC(){
		gc.exit();
		//System.exit(0);
	}

	private void startNetThread(String ip, int port) {
		net = new NetworkStuff(ip, port, this);
		t = new Thread(net);
		t.start();
	}

	public NetworkStuff getNetwork() {
		return net;
	}

	public void initPlayer(EntityPlayer pl) {
		EntityPlayer player = pl.toClientPlayer(this);
		player.loadImage();
		world.addEntity(player);
		world.setPlayer(player);
	}

	public void addEntity(Entity e) {
		e.loadImage();
		world.addEntity(e);
	}

	public void removeEntity(int id) {
		world.removeEntity(id);
	}

	public GameContainer getContainer() {
		return gc;
	}

	public World getWorld() {
		return world;
	}

}
