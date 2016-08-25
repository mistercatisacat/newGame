package tbc.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import tbc.game.collision.StandardBox;
import tbc.game.entities.Entity;
import tbc.util.Point;
import tbc.util.Rectangle;
import tbc.game.collision.StandardBox;

public class World {
  private HashMap<Integer, Entity> entityList = new HashMap<Integer, Entity>();
  private LinkedList entityToSpawn = new LinkedList<>
  private HashSet<Point> allBoxes = new HashSet<Point>();

  public Entity getEntity(int x) {
    return entityList.get(x);

  }

  synchronized public void addEntity(int key, Entity value) {
    entityList.put(key, value);
  }
  
  synchronized public void tick(){
	  for (Entity e : entityList){
		  
	  }
  }

  public void addBoundPoint(Point p) {
    allBoxes.add(p);
  }
  
  public boolean containsBox(int x, int y){
    return allBoxes.contains(new Point(x, y));
  }




}
