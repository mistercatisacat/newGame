package tbc.packets;

import java.util.HashMap;

import tbc.client.NetworkStuff;
import tbc.game.entities.EntityPlayer;
import tbc.game.entities.Entity;
import tbc.game.states.Game;
import tbc.server.JewelServer;
import tbc.server.ServerGame;
import tbc.util.Point;

public class PacketConnect extends Packet{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4756880995093889172L;

	private HashMap<Integer, Entity> entities = new HashMap<Integer,Entity>();
	private Point spawn;
	private int id;
	
	public PacketConnect(int cID,Point spawn) {
		id = cID;		
		this.spawn = spawn;
	}
	
	public void addEntity(Entity e){
		entities.put(e.getID(), e);
	}
	
	@Override
	public void onServer(JewelServer server, ServerGame game, int senderID) {
		// nof'in
		
	}

	@Override
	public void onClient(NetworkStuff client, Game Game) {
				
	}

}
