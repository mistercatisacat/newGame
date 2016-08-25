package tbc.packets;

import tbc.client.NetworkStuff;
import tbc.game.Jewel;
import tbc.server.JewelServer;

public class TestPacket extends Packet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7908557906383878527L;

	@Override
	public void onServer(JewelServer server, Jewel game, int senderID) {
		server.sendPacket(0, this);
		
	}

	@Override
	public void onClient(NetworkStuff client, Jewel game) {
		System.out.println("testPacket");
		
	}

}
