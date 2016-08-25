package tbc.game.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import tbc.client.ImageLoader;
import tbc.util.InputConfig;

public class PlayerEntitity implements Entity{
	public int x, y, vx, vy; 
	private Input keyboard;
	Image sprite;
	
	public PlayerEntitity(Input keyboard, int x, int y){
		this.x = x;
		this.y = y;
		vx = vy = 0;
		this.keyboard = keyboard;
	}

	@Override
	public void setVelocity(int x, int y) {
		this.vy +=y;
		this.vx +=x;
	}

	@Override
	public int getx() {
		return x;
		
	}

	@Override
	public int gety() {
		return y;
	}

	@Override
	public Image getSprite() {
		return sprite;
	}

	@Override
	public void setSprite(Image sprite) {
		this.sprite  = sprite; 
	}
	
	public void onTick(){
		
		
		if (keyboard.isKeyDown(InputConfig.getKey("Forward"))){
			setVelocity(vx, vy);

		}
		if (keyboard.isKeyDown(Input.KEY_S)){
			play.sety(1);

		}
		if (keyboard.isKeyDown(Input.KEY_A)){
			play.setx(-1);

		}
		if (keybord.isKeyDown(Input.KEY_D)){
			play.setx(1);

		}
		
	}



}
