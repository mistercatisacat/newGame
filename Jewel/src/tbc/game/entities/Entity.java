package tbc.game.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import tbc.util.InputConfig;

public abstract class Entity {
	
	private int x, y, vx, vy, id; 	
	private Image sprite;
	
	public Entity( int x, int y, int id){
		this.x = x;
		this.y = y;
		vx = vy = 0;
		this.id = id;
	}

	
	public void setVelocity(int vx, int vy) {
		this.vx = vx;
		this.vy = vy;
		
	}

	public int getVx(){
		return vx;
	}
	
	public int getVy(){
		return vy;
	}
	
	public void updatePos(){
		
	}

	public int getx() {
		return x;
		
	}

	public int gety() {
		return y;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite  = sprite; 
	}
	
	public void onTick(){
		
		updatePos();
	}
	



	
}
