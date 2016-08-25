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
  private List<Entity> entityList = new ArrayList<Entity>();
  private LinkedList entityToSpawn = new LinkedList<Entity>();
  private HashSet<Point> allBoxes = new HashSet<Point>();

  public Entity getEntity(int x) {
    return entityList.get(x);

  }

  synchronized public void addEntity(Entity ent) {
    entityList.add(ent);
  }
  
  synchronized public void renderAll(){
	  for (Entity e: entityList){
		  e.getSprite().draw(e.getX(), e.getY());
	  }
  }
  
  synchronized public void tick(){
	  for (Entity e: entityList){
		  e.onTick();
	  }
  }

  public void addBoundPoint(Point p) {
    allBoxes.add(p);
  }
  
  public boolean containsBox(int x, int y){
    return allBoxes.contains(new Point(x, y));
  }




}
