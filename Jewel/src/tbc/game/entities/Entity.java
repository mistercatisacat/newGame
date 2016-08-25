package tbc.game.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import tbc.game.states.Game;
import tbc.util.InputConfig;
import tbc.util.Point;

public abstract class Entity {
	
	private int x, y, vx, vy, id; 	
	private Image sprite;
	
	public Entity(Point p, int id, String spriteName){
		this.sprite = Game.imLoad.getImage(spriteName).copy();
		this.x = p.getX();
		this.y = p.getY();
		vx = vy = 0;
		this.id = id;
	}
	
	public Entity(Point p, int vx, int vy, int id, String spriteName){
		this.sprite = Game.imLoad.getImage(spriteName);
		this.x = p.getX();
		this.y = p.getY();
		this.vx = vx;
		this.vy = vy;
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
		x += vx;
		y += vy;
	}

	public int getX() {
		return x;
		
	}

	public int getY() {
		return y;
	}

	public Image getSprite() {
		return sprite;
	}
	
	public void onTick(){
		updatePos();
	}
	



	
}
