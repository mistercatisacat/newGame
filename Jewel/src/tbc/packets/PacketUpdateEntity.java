package tbc.packets;

import java.io.Serializable;

import tbc.client.NetworkStuff;
import tbc.game.states.Game;
import tbc.server.JewelServer;
import tbc.server.ServerGame;

public class PacketUpdateEntity extends Packet implements Serializable {

	private static final long serialVersionUID = -1791827349640811235L;
	private int vx, vy, x, y, id;

	public PacketUpdateEntity(int vx, int vy, int x, int y, int id) {
		this.vx = vx;
		this.vy = vy;
		this.x = x;
		this.y = y;
		this.id = id;
	}

	@Override
	public void onServer(JewelServer server, ServerGame game, int senderID) {
		//System.out.println("Server velocity update. Client:" + id);
		game.getWorld().setEntityVelocity(vx, vy, id);
		server.broadcastPacketExceptTo(this, senderID);
	}

	@Override
	public void onClient(NetworkStuff client, Game game) {
		game.getWorld().setEntityPosition(x, y, id);
		game.getWorld().setEntityVelocity(vx, vy, id);
	}
 
}
