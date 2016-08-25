package tbc.game.entities;

import org.newdawn.slick.Image;

public interface Entity {
	
	public void setVelocity(int x, int y);
	public int getx();
	public int gety();
	public void onTick();
	public Image getSprite();
	public void setSprite(Image sprite);

	
}
