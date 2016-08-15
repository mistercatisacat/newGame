package tbc.game.collision;

import java.util.ArrayList;
import java.util.List;

import tbc.game.World;
import tbc.util.Direction;
import tbc.util.Point;
import tbc.util.Rectangle;

public class BoundingBox {
  private List<StandardBox> boxes;
  private Rectangle perimiter;
  private World world;

  public BoundingBox(List<StandardBox> boxes, World world) {
    boxes = new ArrayList<StandardBox>();
    this.world = world;
  }

  public Direction collides() {
    List<StandardBox> candidates = new ArrayList<StandardBox>();
    int xi = perimiter.topLeft().getX(), yi = perimiter.topLeft().getY(),
        w = perimiter.getWidth(), h = perimiter.getHeight();
    for (int y = yi; y < yi + h; y++){
      for (int x = xi; x < xi + w; x++){
        if (world.containsBox(x, y)){
          Direction collides = inBounds(new StandardBox(new Point(x, y)));
          if (collides != Direction.EXCLUSIVE){
            return collides;
          }
        }
      }
    }
    return Direction.EXCLUSIVE;
  }

  public Direction inBounds(StandardBox other){
    Direction curr;
    for(StandardBox b : boxes){
      curr = b.collideAgainst(other);
      if (!(curr == Direction.EXCLUSIVE)){
        return curr;
      }
    }
    return Direction.EXCLUSIVE;
  }

}
