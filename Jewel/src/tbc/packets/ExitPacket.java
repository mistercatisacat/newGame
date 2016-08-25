package tbc.packets;

import tbc.client.JewelClient;
import tbc.client.NetworkStuff;
import tbc.game.Jewel;
import tbc.server.JewelServer;

public class ExitPacket extends Packet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5484694772231820092L;

	@Override
	public void onServer(JewelServer server, Jewel game, int senderID) {
		System.out.println("Dissconecting client: " + senderID);		
		server.purge(senderID);		
	}

	@Override
	public void onClient(NetworkStuff client, Jewel Game) {
		System.out.println("exiting!");
		client.exit();
	}
	
	
	
}
