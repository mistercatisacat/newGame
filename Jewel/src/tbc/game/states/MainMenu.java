package tbc.game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import tbc.client.ImageLoader;

public class MainMenu extends BasicGameState{

	ImageLoader load = new ImageLoader("assets/");
	Image test;
	Input input;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		test = load.getImage("checker_small").getScaledCopy(640, 480);	
		test.draw(0, 0);
		arg2.setColor(Color.red);
		arg2.drawString("welcome to Jewel!!!", 0, 20);
		arg2.drawString("Start", 0, 60);
		arg2.drawString("Options", 0, 80);
		
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if (arg0.getInput().isKeyDown(Input.KEY_DOWN)){
			System.out.println("down key is down");
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
