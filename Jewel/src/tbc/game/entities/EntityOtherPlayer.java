package tbc.game.entities;

import tbc.util.Point;

public class EntityOtherPlayer extends Entity {
	private static final long serialVersionUID = 19851298123L;
	public static final float IM_SCALE = .5F;
	
	public EntityOtherPlayer(int x, int y, int vx, int vy, int id){
		super(new Point(x, y), id, SpriteNames.OTHER_PLAYER_SPRITE);
		
	}
	
	@Override
	public void loadImage() {
		System.out.println("Loaded image for other player");
		super.loadImage(IM_SCALE);
	}
}
