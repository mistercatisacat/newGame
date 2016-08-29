package tbc.packets;

import java.io.Serializable;

import tbc.client.JewelClient;
import tbc.client.NetworkStuff;
import tbc.game.states.Game;
import tbc.server.JewelServer;
import tbc.server.ServerGame;

public abstract class Packet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1850556376455771336L;
	
	
	public abstract void onServer(JewelServer server, ServerGame game,int senderID);
	
	public abstract void onClient(NetworkStuff client, Game game);
	
	
}
