package tbc.packets;

import tbc.client.JewelClient;
import tbc.client.NetworkStuff;
import tbc.game.states.Game;
import tbc.server.JewelServer;
import tbc.server.ServerGame;

public class PacketExit extends Packet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5484694772231820092L;

	@Override
	public void onServer(JewelServer server, ServerGame game, int senderID) {
		System.out.println("Dissconecting client: " + senderID);		
		server.purge(senderID);		
	}

	@Override
	public void onClient(NetworkStuff client, Game Game) {
		System.out.println("exiting!");
		client.exit();
	}
	
	
	
}
