package tbc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

import javax.sql.rowset.spi.SyncResolver;

import tbc.game.entities.Entity;
import tbc.game.entities.EntityOtherPlayer;
import tbc.game.entities.EntityPlayer;
import tbc.game.World;
import tbc.packets.PacketExit;
import tbc.packets.PacketNewEntity;
import tbc.packets.PacketSendWorldInfo;
import tbc.packets.PacketSpawnClientPlayer;
import tbc.util.Point;
import tbc.packets.Packet;

public class JewelServer {

	HashMap<Integer, ClientInstance> clients = new HashMap<Integer, ClientInstance>();
	HashSet<Integer> usedIDs = new HashSet<Integer>();
	ServerSocket server;
	boolean running = true;
	ServerGame game;
	public World gameWorld;

	public static void main(String[] args) {
		new JewelServer();
	}

	/**
	 * 
	 */
	public JewelServer() {
		gameWorld = new World();
		game = new ServerGame(this, gameWorld);
		(new Thread(game)).start();
		try {
			System.out.println("starting...");
			server = new ServerSocket(9999);
			while (running) {
				System.out.println("wating for client");
				Socket cliSoc = server.accept();
				ClientInstance ci = new ClientInstance(this, game, cliSoc,
						gameWorld);
				System.out.println("starting thread");
				(new Thread(ci)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	synchronized void addClient(ClientInstance ci) {
		int id = genID();
		Point spawn = gameWorld.findPlayerSpawn();
		EntityPlayer ep = EntityPlayer.makeServerPlayer(spawn, id);
		
		//Send packets to everyone about the new client
		PacketSpawnClientPlayer ps = new PacketSpawnClientPlayer(ep);
		PacketNewEntity pne = new PacketNewEntity(ep.toOtherPlayer());
		PacketSendWorldInfo worldSnap = new PacketSendWorldInfo(gameWorld.allEntities());
		
		broadcastPacket(pne);
		clients.put(id, ci);			
		ci.setID(id);
		sendPacket(id, worldSnap);
		sendPacket(id, ps);
		game.addEntityServerOnly(ep);
	}

	public synchronized int genID() {
		int id = 0;
		while (usedIDs.contains(id)) {
			id++;
		}
		usedIDs.add(id);
		return id;
		//O(1) lowest available technique should be implemented
	}

	public void sendPacket(int clientID, Packet p) {
		ClientInstance ci;
		synchronized (this) {
			ci = clients.get(clientID);
		}
		ci.sendPacket(p);
	}

	public synchronized void broadcastPacket(Packet p) {
		for (ClientInstance client : clients.values()) {
			client.sendPacket((p));
		}
	}
	
	public synchronized void broadcastPacketExceptTo(Packet p, int cid){
		for (ClientInstance client : clients.values()) {
			if(client.getID() != cid){
				client.sendPacket((p));
			}
		}
	}

	public synchronized void purge(int id) {
		sendPacket(id, new PacketExit());
		clients.remove(id).stop();
		usedIDs.remove(id);
		System.out.println("purging client #: " + id);
	}

	public synchronized void removeEntity(int id) {
		// send packet to clients
		clients.remove(id);
	}

	public synchronized void spawnEntity(Entity e) {
		game.spawnEntity(e);		
	}
	
}
