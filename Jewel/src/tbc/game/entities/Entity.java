package tbc.game.entities;

import java.io.Serializable;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import tbc.game.states.Game;
import tbc.server.ServerGame;
import tbc.util.InputConfig;
import tbc.util.Point;

public abstract class Entity implements Serializable{
	

	private static final long serialVersionUID = -4559998615674348779L;
	protected int x, y, vx, vy, id;
	protected Image sprite;
	protected String spriteName;
	
	
	public Entity(Point p, int id, String spriteName){
		this.spriteName = spriteName;
		this.x = p.getX();
		this.y = p.getY();
		vx = vy = 0;
		this.id = id;
	}
	
	public Entity(Point p, int vx, int vy, int id, String spriteName){
		this.spriteName = spriteName;
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
		int px = x;
		int py = y;
		x += vx;
		y += vy;
		//check collision
		
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
	
	public void onServerTick(ServerGame game){
		updatePos();
	}
	
	public int getID(){
		return id;
	}
	
	public void loadImage(float scale){
		Image src = Game.imLoad.getImage(spriteName);
		sprite = src.getScaledCopy(scale);
	}
	
	public void loadImage(){
		sprite = Game.imLoad.getImage(spriteName).copy();
	}
	
}
