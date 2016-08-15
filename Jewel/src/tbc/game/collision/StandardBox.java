package tbc.game.collision;

import tbc.util.Direction;
import tbc.util.Point;
import tbc.util.Rectangle;

public class StandardBox {
  
  public static final int UNIT_BOX = 5;
  
  private Rectangle rect;
  private Point origin;
  
  public StandardBox(Point origin){
    this.origin = origin;
    this.rect = new Rectangle(origin, UNIT_BOX, UNIT_BOX);
  }
  
  public Direction collideAgainst(StandardBox other){
    return this.getBox().intersecting(other.getBox());
  }
  
  public Rectangle getBox(){
    return rect;
  }
  
  public Point getOrigin(){
    return origin;
  }
  
  
  public boolean equals(Object obj) {
    if(!(obj instanceof StandardBox)){
      return false;
    }
    return this.origin.equals(rect.topLeft());
  }
  
  public int hashCode() {
    return origin.hashCode();
  }
  
  

}
