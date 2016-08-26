package tbc.game.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import tbc.client.ImageLoader;
import tbc.util.InputConfig;
import tbc.util.Point;

public class EntityPlayer extends Entity{
	
	private Input keyboard;
	Image sprite;
	
	public EntityPlayer(Input keyboard, Point p, int id){
		super(p, id, "rectangle");
		this.keyboard = keyboard;
		System.out.println("constructed!");
	}
	
	
	public void onTick(){
		System.out.println("player tick +  " + this);
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
	@Override
	public void loadImage() {
		System.out.println("loaded image for player!");
		super.loadImage();
	}
		
	public EntityOtherPlayer toOtherPlayer(){
		return new EntityOtherPlayer(getX(), getY(), getVx(), getVy(), getID());
	}

}
