package tbc.packets;

import tbc.client.NetworkStuff;
import tbc.game.entities.Entity;
import tbc.game.states.Game;
import tbc.server.JewelServer;
import tbc.server.ServerGame;

public class PacketNewEntity extends Packet{
	
	private static final long serialVersionUID = 1510927589123891282L;
	private Entity e;
	
	public PacketNewEntity(Entity e){
		this.e = e;		
	}

	@Override
	public void onServer(JewelServer server, ServerGame game, int senderID) {
		
	}

	@Override
	public void onClient(NetworkStuff client, Game game) {
		game.addEntity(e);
	}

}
