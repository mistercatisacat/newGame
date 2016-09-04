package tbc.packets;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.sun.org.apache.xml.internal.security.keys.content.KeyValue;

import tbc.client.NetworkStuff;
import tbc.game.World;
import tbc.game.entities.Entity;
import tbc.game.entities.EntityPlayer;
import tbc.game.states.Game;
import tbc.server.JewelServer;
import tbc.server.ServerGame;

public class PacketSendWorldInfo extends Packet {
	
	private Entity[] entities;
	public PacketSendWorldInfo(Entity[] entities) {
		this.entities = entities;
	}

	@Override
	public void onServer(JewelServer server, ServerGame game, int senderID) {
		
	}

	@Override
	public void onClient(NetworkStuff client, Game game) {
		//At the moment, works via side effect. Will also send tile and other info
		World w = game.getWorld();
		for (Entity e : entities){
			System.out.println("x: " + e.getX() + " y: " + e.getY());
			Entity toAdd = e;
			if (e instanceof EntityPlayer){
				toAdd = ((EntityPlayer) e).toOtherPlayer();
			}
			w.addEntity(toAdd);
		}
	}

}
