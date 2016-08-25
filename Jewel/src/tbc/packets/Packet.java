package tbc.packets;

import java.io.Serializable;

import tbc.client.JewelClient;
import tbc.client.NetworkStuff;
import tbc.game.Jewel;
import tbc.server.JewelServer;

public abstract class Packet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1850556376455771336L;
	
	
	public abstract void onServer(JewelServer server, Jewel game,int senderID);
	
	public abstract void onClient(NetworkStuff client, Jewel Game);
	
	
}
