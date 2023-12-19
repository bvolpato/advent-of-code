package bruno.util;

public class Point2D implements Comparable<Point2D> {
  public long x;
  public long y;

  public Point2D(long x, long y) {
    this.x = x;
    this.y = y;
  }

  public Point2D(Point2D other) {
    this.x = other.x;
    this.y = other.y;
  }

  public long getX() {
    return x;
  }

  public long getY() {
    return y;
  }

  public void setX(long x) {
    this.x = x;
  }

  public void setY(long y) {
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
    return (int) (31L * x + y) % 1000000007;
  }

  public boolean isValid(Object[][] grid) {
    return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
  }

  @Override
  public int compareTo(Point2D o) {
    if (x == o.x) {
      return Long.compare(y, o.y);
    }
    return Long.compare(x, o.x);
  }

  public int intX() {
    return (int) x;
  }

    public int intY() {
        return (int) y;
    }

}
