package bruno.util;

public class Point2D {
  public int x;
  public int y;

  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Point2D(Point2D other) {
    this.x = other.x;
    this.y = other.y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Point2D) {
      Point2D other = (Point2D) obj;
      return x == other.x && y == other.y;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return 31 * x + y;
  }

  public boolean isValid(Object[][] grid) {
    return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
  }
}
