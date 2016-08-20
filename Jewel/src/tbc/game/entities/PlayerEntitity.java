package tbc.game.entities;

import org.newdawn.slick.Image;

import tbc.client.ImageLoader;

public class PlayerEntitity implements Entity{
	int x = 0;
	int y = 0;
	Image sprite;
	public void setx(int x) {
		this.x +=x;
	}

	@Override
	public void sety(int y) {
		this.y +=y;
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



}
