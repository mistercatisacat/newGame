package tbc.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import tbc.client.JewelClient;
import tbc.game.collision.StandardBox;
import tbc.game.entities.EntityPlayer;
import tbc.packets.Packet;
import tbc.packets.PacketRemoveEntity;
import tbc.game.entities.Entity;
import tbc.server.ServerGame;
import tbc.util.Point;
import tbc.util.Rectangle;
import tbc.game.collision.StandardBox;

public class World {
	private HashMap<Integer, Entity> entityList = new HashMap<Integer, Entity>();
	private HashSet<Point> allBoxes = new HashSet<Point>();
	private EntityPlayer ep = null;

	synchronized public Entity getEntity(int id) {
		return entityList.get(id);

	}

	synchronized public void setEntityVelocity(int vx, int vy, int id) {
		entityList.get(id).setVelocity(vx, vy);
	}
	
	synchronized public void setEntityPosition(int x, int y, int id) {
		entityList.get(id).setLocation(x, y);
	}
	

	synchronized public void addEntity(Entity ent) {
		entityList.put(ent.getID(), ent);
	}

	synchronized public void loadAll() {
		for (Entity e : entityList.values()) {
			e.loadImage();
		}
	}

	synchronized public void renderAll() {
		for (Entity e : entityList.values()) {
			e.getSprite().draw(e.getX(), e.getY());
		}
	}

	synchronized public void tick() {
		for (Entity e : entityList.values()) {
			e.onTick();
		}
	}

	public void addBoundPoint(Point p) {
		allBoxes.add(p);
	}

	public boolean containsBox(int x, int y) {
		return allBoxes.contains(new Point(x, y));
	}

	public void setPlayer(EntityPlayer player) {
		ep = player;
	}

	public EntityPlayer getPlayer() {
		return ep;
	}

	public synchronized void onServerTick(ServerGame serverGame) {
		Iterator<Entity> it = entityList.values().iterator();
		while(it.hasNext()){
			Entity e = it.next();
			if(e.toBeRemoved){
				it.remove();
				PacketRemoveEntity pre = new PacketRemoveEntity(e.getID());
				serverGame.broadcastPacket(pre);
			}else{
				e.onServerTick(serverGame);
			}
		}
	}

	synchronized public void removeEntity(int id) {
		entityList.remove(id);
	}
	
	public synchronized void markForRemoval(int id){ 
		entityList.get(id).toBeRemoved = true;
	}

	public Point findPlayerSpawn() {
		return new Point((int) (Math.random() * JewelClient.WIDTH),
				(int) (Math.random() * JewelClient.HEIGHT));
	}

	synchronized public Entity[] allEntities() {
		Entity[] ents = new Entity[entityList.values().size()];
		entityList.values().toArray(ents);
		for (int i = 0; i < ents.length; i++) {
			System.out.println(
					"server x: " + ents[i].getX() + " y: " + ents[i].getY());
		}
		return ents;
	}

}
