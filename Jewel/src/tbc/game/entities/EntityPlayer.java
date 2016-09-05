package tbc.game.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import tbc.client.ImageLoader;
import tbc.game.states.Game;
import tbc.packets.PacketUpdateEntity;
import tbc.util.InputConfig;
import tbc.util.Point;
import tbc.util.Velocity;

public class EntityPlayer extends Entity {

	public static final float IM_SCALE = .5F;

	private Input keyboard;
	private Game game;

	private EntityPlayer(Input keyboard, Point p, int id, Game game) {
		super(p, id, SpriteNames.PLAYER_SPRITE);
		this.keyboard = keyboard;
		this.game = game;
		System.out.println("constructed!");
	}

	public static EntityPlayer makeServerPlayer(Point spawn, int id) {
		return new EntityPlayer(null, spawn, id, null);
	}

	public static EntityPlayer makeClientPlayer(Input keyboard, Point spawn,
			int id, Game g) {
		return new EntityPlayer(keyboard, spawn, id, g);
	}

	public void onTick() {
		Velocity prev = new Velocity(vx, vy);
		setVelocity(0, 0);
		if (keyboard.isKeyDown(InputConfig.getKey("UP"))) {
			setVelocity(getVx(), getVy() - 1);
		}
		if (keyboard.isKeyDown(InputConfig.getKey("DOWN"))) {
			setVelocity(getVx(), getVx() + 1);
		}
		if (keyboard.isKeyDown(InputConfig.getKey("LEFT"))) {
			setVelocity(getVx() - 1, getVy());
		}
		if (keyboard.isKeyDown(InputConfig.getKey("RIGHT"))) {
			setVelocity(getVx() + 1, getVy());
		}
		if (!new Velocity(vx, vy).equals(prev)) {
			System.out.println("New player velocity: Client  " + getID()
					+ " X: " + vx + "Y: " + vy);
			PacketUpdateEntity update = new PacketUpdateEntity(vx, vy, x, y, getID());
			game.getNetwork().sendPacket(update);
		}
		updatePos();
	}

	public void loadImage() {
		System.out.println("loaded image for player!");
		super.loadImage(IM_SCALE);
	}

	public EntityPlayer toClientPlayer(Game game) {
		return new EntityPlayer(game.getContainer().getInput(), new Point(x, y),
				this.id, game);
	}

	public EntityOtherPlayer toOtherPlayer() {
		return new EntityOtherPlayer(getX(), getY(), getVx(), getVy(), getID());
	}

}
