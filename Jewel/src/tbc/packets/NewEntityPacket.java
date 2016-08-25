package tbc.packets;

import tbc.client.NetworkStuff;
import tbc.game.Jewel;
import tbc.game.entities.Entity;
import tbc.server.JewelServer;

public class NewEntityPacket extends Packet{
	
	private static final long serialVersionUID = 1510927589123891282L;
	private Entity e;
	private int id;
	public NewEntityPacket(Entity e, int id){
		this.e = e;
		this.id = id;
	}

	@Override
	public void onServer(JewelServer server, Jewel game, int senderID) {
		
		
	}

	@Override
	public void onClient(NetworkStuff client, Jewel Game) {
		// TODO Auto-generated method stub
		
	}

}
