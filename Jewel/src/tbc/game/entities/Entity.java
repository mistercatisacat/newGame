package tbc.game.entities;

import org.newdawn.slick.Image;

public interface Entity {
	int x = 0;
	int y = 0;
	
	public void setx(int x);
	public void sety(int y);
	public int getx();
	public int gety();
	public Image getSprite();
	public void setSprite(Image sprite);

	
}
