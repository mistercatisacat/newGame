package tbc.game.states;

import javax.swing.JOptionPane;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tbc.client.ImageLoader;
import tbc.client.NetworkStuff;
import tbc.game.entities.PlayerEntitity;

public class MainMenu extends BasicGameState{

	ImageLoader load = new ImageLoader("assets/");
	Image test;
	Input input;
	static NetworkStuff net;
	static Thread t;
	String ip;
	int port;
	PlayerEntitity play;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		play = new PlayerEntitity();
		play.setSprite(load.getImage("rectangle"));
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setColor(Color.red);
		arg2.drawString("welcome to Jewel!!!", 0, 20);
		arg2.drawString("Start", 0, 60);
		arg2.drawString("Options", 0, 80);
		arg2.drawString("press C to connect to server", 0, 120);
		arg2.drawString("press X to exit", 0, 140);
		play.getSprite().draw(play.getx(), play.gety());
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if (arg0.getInput().isKeyDown(Input.KEY_C)){
			JOptionPane connect = new JOptionPane();
			ip = connect.showInputDialog("please input ip");
			port = Integer.parseInt(connect.showInputDialog("please input port"));
			startNetThread(ip, port);
			
		}
		if (arg0.getInput().isKeyDown(Input.KEY_X)){
			net.stop();
			arg0.exit();
		}
		if (arg0.getInput().isKeyDown(Input.KEY_S)){
			play.sety(1);
			System.out.println("x: " + play.getx());
			System.out.println("y: " + play.gety());

		}
		if (arg0.getInput().isKeyDown(Input.KEY_W)){
			play.sety(-1);
			System.out.println("x: " + play.getx());
			System.out.println("y: " + play.gety());

		}
		if (arg0.getInput().isKeyDown(Input.KEY_A)){
			play.setx(-1);
			System.out.println("x: " + play.getx());
			System.out.println("y: " + play.gety());

		}
		if (arg0.getInput().isKeyDown(Input.KEY_D)){
			play.setx(1);
			System.out.println("x: " + play.getx());
			System.out.println("y: " + play.gety());

		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	  public static void startNetThread(String ip, int port) {
		    net = new NetworkStuff(ip, port);
		    t = new Thread(net);
		    t.start();


		  }

}
