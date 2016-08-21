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
	Image grass;	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		 load = new ImageLoader("assets/");
		//grass = load.getImage("grass");
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		//grass.draw(0,0);	
		arg2.setColor(Color.red);
		arg2.drawString("welcome to Jewel!!!", 0, 20);
		arg2.drawString("Start", 0, 60);
		arg2.drawString("Options", 0, 80);
		arg2.drawString("press E to enter game", 0, 120);
		arg2.drawString("press X to exit", 0, 140);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if (arg0.getInput().isKeyDown(Input.KEY_X)){
			arg0.exit();
			
		}
		if (arg0.getInput().isKeyDown(Input.KEY_E)){
			arg1.addState(new Game());
			arg1.enterState(new Game().getID());
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}


}
