package tbc.game.entities;

import tbc.util.Point;

public class EntityOtherPlayer extends Entity {
	private static final long serialVersionUID = 19851298123L;
	
	public EntityOtherPlayer(int x, int y, int vx, int vy, int id){
		super(new Point(x, y), id, "rectangle");
	}
}
