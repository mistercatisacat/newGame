package tbc.game.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import tbc.client.ImageLoader;
import tbc.util.InputConfig;
import tbc.util.Point;

public class PlayerEntitity extends Entity{
	
	private Input keyboard;
	Image sprite;
	
	public PlayerEntitity(Input keyboard, Point p, int id){
		super(p, id, "rectangle");
		this.keyboard = keyboard;
	}
	
	
	public void onTick(){
		setVelocity(0, 0);
		if (keyboard.isKeyDown(InputConfig.getKey("UP"))){
			setVelocity(getVx(), getVy() - 1);
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
	
	public EntityOtherPlayer toOtherPlayer(int id){
		return new EntityOtherPlayer(getX(), getY(), getVx(), getVy(), id);
	}

}
