package tbc.game;

import java.util.HashMap;
import java.util.HashSet;

import tbc.game.entities.Entity;

public class World {
	private HashMap<Integer, Entity> entityList = new HashMap<Integer, Entity>();
	private HashSet<Integer> allBoxes = new HashSet<Integer>(); 
	
	public Entity getEntity(int x){
		return entityList.get(x);
		
	}
	public void addEntity(int key, Entity value){
		entityList.put(key, value);
	}
}
