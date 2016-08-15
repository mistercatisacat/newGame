package tbc.util;

public class Point {

  private int x;
  private int y;

  public Point(int x, int y){
    this.x = x;
    this.y = y;
  }
  
  public int getX(){
    return x;
  }
  
  public int getY(){
    return y;
  }

  public boolean equals(Object obj) {
    if (!(obj instanceof Point)){
      return false;
    }
    Point p2 = (Point)obj;
    return p2.x == this.x && p2.y == this.y;
  }

  public int hashCode() {
    return (x * tbc.client.JewelClient.HEIGHT + y);
  }

}
