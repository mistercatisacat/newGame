package tbc.packets;

import tbc.client.NetworkStuff;
import tbc.game.entities.Entity;
import tbc.game.states.Game;
import tbc.server.JewelServer;
import tbc.server.ServerGame;

public class PacketRemoveEntity extends Packet {
	
	private static final long serialVersionUID = 12312398764L;
	
	private int eid;
	public PacketRemoveEntity(int eid) {
		this.eid = eid;
	}

	@Override
	public void onServer(JewelServer server, ServerGame game, int senderID) {
		
	}

	@Override
	public void onClient(NetworkStuff client, Game game) {
		game.removeEntity(eid);
	}

}
