package tbc.packets;

import java.io.Serializable;

import tbc.client.NetworkStuff;
import tbc.game.states.Game;
import tbc.server.JewelServer;
import tbc.server.ServerGame;

public class PacketUpdateVelocity extends Packet implements Serializable {

	private static final long serialVersionUID = -1791827349640811235L;
	private int vx, vy, id, cid;

	public PacketUpdateVelocity(int vx, int vy, int id, int cid) {
		this.vx = vx;
		this.vy = vy;
		this.cid = cid;
	}

	@Override
	public void onServer(JewelServer server, ServerGame game, int senderID) {
		System.out.println("Server velocity update. Client:" + id);
		game.getWorld().setEntityVelocity(vx, vy, id);
	}

	@Override
	public void onClient(NetworkStuff client, Game game) {
		if (cid != game.getWorld().getPlayer().getID()) {
			game.getWorld().setEntityVelocity(vx, vy, id);
		}
	}

}
