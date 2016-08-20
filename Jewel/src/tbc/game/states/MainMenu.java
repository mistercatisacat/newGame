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

	ImageLoader load;
	Image test;
	Input input;
	static NetworkStuff net;
	static Thread t;
	String ip;
	int port;
	PlayerEntitity play;
	Image grass;
	int x = 0;
	int y = 0;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		play = new PlayerEntitity();
		 load = new ImageLoader("assets/");
		play.setSprite(load.getImage("rectangle"));
		grass = load.getImage("grass");
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.setColor(Color.red);
		arg2.drawString("welcome to Jewel!!!", 0, 20);
		arg2.drawString("Start", 0, 60);
		arg2.drawString("Options", 0, 80);
		arg2.drawString("press C to connect to server", 0, 120);
		arg2.drawString("press X to exit", 0, 140);
		grass.draw(x,y);
		play.getSprite().draw(640/2, 480/2);
		
		
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
			y -=1;

		}
		if (arg0.getInput().isKeyDown(Input.KEY_W)){
			y +=1;

		}
		if (arg0.getInput().isKeyDown(Input.KEY_A)){
			x += 1;
	

		}
		if (arg0.getInput().isKeyDown(Input.KEY_D)){
			x -= 1;

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
