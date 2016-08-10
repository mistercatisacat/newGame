package tbc.packets;

import tbc.game.Jewel;
import tbc.server.JewelServer;

public class TestPacket extends Packet{

	@Override
	public void onServer(JewelServer server, Jewel game) {
		System.out.println("testPacket");
		
		
	}

	@Override
	public void onClient(Jewel game) {
		System.out.println("testPacket");
		
	}

}
