package tbc.packets;

import tbc.client.NetworkStuff;
import tbc.game.entities.EntityPlayer;
import tbc.game.states.Game;
import tbc.server.JewelServer;
import tbc.server.ServerGame;

public class PacketSpawnClientPlayer extends Packet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3734217446396755605L;
	private EntityPlayer pl;
	public PacketSpawnClientPlayer(EntityPlayer pl) {
		this.pl = pl;
	}

	@Override
	public void onServer(JewelServer server, ServerGame game, int senderID) {
		
	}

	@Override
	public void onClient(NetworkStuff client, Game game) {
		game.initPlayer(pl);		
	}

}
