package tbc.util;

public class Rectangle {

  public static final int COLLISION_THRESH = 1;

  private Point[] corners;

  public Rectangle(Point p, int w, int h) {
    corners = new Point[] {p, new Point(p.getX() + w, p.getY()),
        new Point(p.getX() + w, p.getY() + h),
        new Point(p.getX(), p.getY() + h)};
  }

  public int getWidth() {
    return corners[1].getX() - corners[0].getX();
  }

  public int getHeight() {
    return corners[1].getX() - corners[0].getX();
  }


  public Point topLeft() {
    return corners[0];
  }

  public Point topRight() {
    return corners[1];
  }

  public Point BottomRight() {
    return corners[1];
  }

  public Point BottomLeft() {
    return corners[1];
  }

  public Direction intersecting(Rectangle other) {
    if (Math.abs(
        this.topRight().getX() - other.topLeft().getX()) <= COLLISION_THRESH) {
      return Direction.RIGHT;
    }
    if (Math.abs(this.BottomLeft().getY()
        - other.topLeft().getY()) <= COLLISION_THRESH) {
      return Direction.BOTTOM;
    }
    if (Math.abs(
        this.topLeft().getX() - other.topRight().getX()) <= COLLISION_THRESH) {
      return Direction.LEFT;
    }
    if (Math.abs(this.topLeft().getY()
        - other.BottomLeft().getY()) <= COLLISION_THRESH) {
      return Direction.TOP;
    }
    return Direction.EXCLUSIVE;
  }

  public boolean contains(Point p) {
    return p.getX() < corners[0].getX() || p.getY() > corners[0].getY()
        || p.getX() > corners[1].getX() || p.getY() > corners[2].getY();

  }

}
