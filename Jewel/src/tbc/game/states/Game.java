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
import tbc.game.entities.PlayerEntitity;



public class Game extends BasicGameState{
	String ip;
	int port;
	public static NetworkStuff net;
	static Thread t;
	PlayerEntitity play;
	ImageLoader load;
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		 
		ip = JOptionPane.showInputDialog("please input ip");
		port = Integer.parseInt(JOptionPane.showInputDialog("please input port"));
		startNetThread(ip, port);
		play = new PlayerEntitity();
		load = new ImageLoader("assets/");
		play.setSprite(load.getImage("rectangle"));
		super.enter(container, game);
	}
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
	
		

		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
	play.getSprite().draw(play.getx(), play.gety());
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if (arg0.getInput().isKeyDown(Input.KEY_X)){
			net.stop();
			arg0.exit();

		}
		if (arg0.getInput().isKeyDown(Input.KEY_W)){
			play.sety(-1);

		}
		if (arg0.getInput().isKeyDown(Input.KEY_S)){
			play.sety(1);

		}
		if (arg0.getInput().isKeyDown(Input.KEY_A)){
			play.setx(-1);

		}
		if (arg0.getInput().isKeyDown(Input.KEY_D)){
			play.setx(1);

		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
	public static void startNetThread(String ip, int port) {
		net = new NetworkStuff(ip, port);
		t = new Thread(net);
		t.start();


	}

}
