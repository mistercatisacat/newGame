package tbc.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import tbc.client.JewelClient;
import tbc.game.collision.StandardBox;
import tbc.game.entities.EntityPlayer;
import tbc.game.entities.Entity;
import tbc.server.ServerGame;
import tbc.util.Point;
import tbc.util.Rectangle;
import tbc.game.collision.StandardBox;

public class World {
	private HashMap<Integer, Entity> entityList = new HashMap<Integer, Entity>();
	private HashSet<Point> allBoxes = new HashSet<Point>();
	private EntityPlayer ep = null;

	public Entity getEntity(int x) {
		return entityList.get(x);

	}
	
	synchronized public void addEntity(Entity ent) {
		entityList.put(ent.getID(), ent);
	}
	
	synchronized public void loadAll(){
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
		for (Entity e : entityList.values()) {
			e.onServerTick(serverGame);
		}
	}
	
	synchronized public void removeEntity(int id){
		entityList.remove(id);
	}
	
	public Point findPlayerSpawn(){
		return new Point((int)(Math.random()*JewelClient.WIDTH),(int) (Math.random()*JewelClient.HEIGHT));
	}

}
