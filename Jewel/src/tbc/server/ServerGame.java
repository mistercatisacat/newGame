package tbc.server;


import java.util.Iterator;
import java.util.LinkedList;

import tbc.client.JewelClient;
import tbc.game.World;
import tbc.game.entities.Entity;
import tbc.packets.Packet;
import tbc.packets.PacketNewEntity;

public class ServerGame implements Runnable{

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int FPS = 60;
	public static final double VERSION = 1.0;

	private JewelServer server;
	private Thread gameThread;
	private World world;
	private LinkedList<Entity> entityToSpawn = new LinkedList<Entity>();

	public ServerGame(JewelServer server,World world) {		
		this.server = server;
		gameThread = new Thread(this);
		gameThread.start();
		this.world = world;
	}

	long startTime = 0;
	long endTime = 0;
	
	@Override
	public void run() {
		endTime = System.currentTimeMillis();
		delay((1000L/FPS), endTime - startTime);		
		startTime = System.currentTimeMillis();	
		tick();
	}
	
	private void delay(long targetMSPF, long deltaMS){
		long diff = targetMSPF - deltaMS;
		if(diff > 0){
			try {
				Thread.sleep(diff);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void tick(){
		addQueuedEntities();		
		world.onServerTick(this);
	}
	
	private void addQueuedEntities(){
		synchronized (entityToSpawn) {
			Iterator<Entity> it = entityToSpawn.iterator();
			while(it.hasNext()){
				addEntity(it.next());
				it.remove();
			}
		}
	}
	
	
	private void addEntity(Entity e){
		//add to world
		world.addEntity(e);
		// send packet to players
		PacketNewEntity pne = new PacketNewEntity(e);
		server.broadcastPacket(pne);
	}
	
	public void addEntityServerOnly(Entity e){
		//add to world
		world.addEntity(e);		
	}
	
	public void spawnEntity(Entity e){
		synchronized (entityToSpawn) {
			entityToSpawn.add(e);
		}	
	}
	
	public void removeEntity(Entity e){
		
	}
	
	public int genID(){	
		return server.genID();
	}
	
	public World getWorld(){
		return world;
	}
	
}
