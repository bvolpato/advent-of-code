package bruno.util.grid;

import bruno.util.Point2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/** A grid that can be infinite in size. */
public class InfiniteGrid {

  private final Map<Point2D, Long> grid;
  public long minRow;
  public long minCol;
  public long maxRow;
  public long maxCol;

  public InfiniteGrid() {
    grid = new HashMap<>();

    minRow = Long.MAX_VALUE;
    minCol = Long.MAX_VALUE;
    maxRow = Long.MIN_VALUE;
    maxCol = Long.MIN_VALUE;
  }

  public void set(int row, int col, long value) {
    set(new Point2D(row, col), value);
  }

  public long get(int row, int col) {
    return grid.get(new Point2D(row, col));
  }

  public long get(Point2D point) {
    return grid.get(point);
  }

  public void put(Point2D point, long value) {
    set(point, value);
  }

  public void set(Point2D point, long value) {
    grid.put(point, value);

    minRow = Math.min(minRow, point.y);
    minCol = Math.min(minCol, point.x);
    maxRow = Math.max(maxRow, point.y);
    maxCol = Math.max(maxCol, point.x);
  }

  public void remove(Point2D point) {
    grid.remove(point);
  }

  public boolean contains(Point2D point) {
    return grid.containsKey(point);
  }

  public int size() {
    return grid.size();
  }

  // Materialize the grid into a 2D array.
  public long[][] materialize() {
    return materialize(0);
  }

  public long[][] materialize(long defaultValue) {
    int rows = (int) (maxRow - minRow + 1);
    int cols = (int) (maxCol - minCol + 1);
    long[][] materialized = new long[rows][cols];

    if (defaultValue != 0) {
      for (long[] row : materialized) {
        Arrays.fill(row, defaultValue);
      }
    }

    for (Map.Entry<Point2D, Long> entry : grid.entrySet()) {
      Point2D point = entry.getKey();
      long value = entry.getValue();
      materialized[(int) (point.y - minRow)][(int) (point.x - minCol)] = value;
    }

    return materialized;
  }

  public int[][] materializeInt(int defaultValue) {
    int rows = (int) (maxRow - minRow + 1);
    int cols = (int) (maxCol - minCol + 1);
    int[][] materialized = new int[rows][cols];

    if (defaultValue != 0) {
      for (int[] row : materialized) {
        Arrays.fill(row, defaultValue);
      }
    }

    for (Map.Entry<Point2D, Long> entry : grid.entrySet()) {
      Point2D point = entry.getKey();
      long value = entry.getValue();
      materialized[(int) (point.y - minRow)][(int) (point.x - minCol)] = (int) value;
    }

    return materialized;
  }

  public void print() {
    long[][] materialized = materialize();
    for (long[] row : materialized) {
      for (long value : row) {
        char valueChar = (char) value;
        if (valueChar == 0) {
          valueChar = ' ';
        }
        System.out.print(valueChar);
      }
      System.out.println();
    }
  }
}
