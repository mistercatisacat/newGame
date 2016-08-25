package tbc.game.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import tbc.client.ImageLoader;
import tbc.util.InputConfig;

public class PlayerEntitity extends Entity{
	
	private Input keyboard;
	Image sprite;
	
	public PlayerEntitity(Input keyboard, int x, int y, int id){
		super(x,y,id);
		this.keyboard = keyboard;
	}
	
	
	public void onTick(){
		setVelocity(0, 0);
		if (keyboard.isKeyDown(InputConfig.getKey("UP"))){
			setVelocity(getVx() + 1, getVy());
		}
		if (keyboard.isKeyDown(InputConfig.getKey("DOWN"))){
			setVelocity(getVx(), getVx() + 1);
		}
		if (keyboard.isKeyDown(InputConfig.getKey("LEFT"))){
			setVelocity(getVx() - 1, getVy());
		}
		if (keyboard.isKeyDown(InputConfig.getKey("RIGHT"))){
			setVelocity(getVx() + 1, getVy());
		}
		updatePos();
	}	

}
