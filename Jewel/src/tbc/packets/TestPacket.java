package tbc.packets;

import tbc.client.NetworkStuff;
import tbc.game.states.Game;
import tbc.server.JewelServer;
import tbc.server.ServerGame;

public class TestPacket extends Packet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7908557906383878527L;

	@Override
	public void onServer(JewelServer server, ServerGame game, int senderID) {
		server.sendPacket(0, this);
		
	}

	@Override
	public void onClient(NetworkStuff client, Game Game) {
		System.out.println("testPacket");
		
	}

}
