package bruno.aoc2023;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.brunocvcunha.inutils4j.MyStringUtils;

public class Day10 {

  public static void main(String[] args) throws IOException, UnsupportedFlavorException {
    String clipboard = MyStringUtils.getContent(Day10.class.getResourceAsStream("/2023/day10.txt"));
    System.out.println("Clipboard content: " + clipboard);

    List<String> listLines = MyStringUtils.asListLines(clipboard);

    char[][] grid =
        listLines.stream()
            .map(String::toCharArray)
            .collect(Collectors.toList())
            .toArray(new char[listLines.size()][]);

    Point initialPoint = new Point(0, 0);

    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {

        if (grid[row][col] == 'S') {
          initialPoint = new Point(row, col);
        }
      }
    }

    boolean[][] visited = new boolean[grid.length][grid[0].length];
    Queue<Point> queue = new LinkedList<>();
    queue.offer(initialPoint);

    int max = 0;
    while (!queue.isEmpty()) {

      Point curr = queue.poll();

      char c = grid[curr.row][curr.col];

      //        System.out.println("============================");

      System.out.println(curr + " - " + c);

      //        for (int k = 0; k < grid.length; k++) {
      //          for (int j = 0; j < grid[0].length; j++) {
      //            if (k == curr.row && j == curr.col) {
      //              System.out.print("X");
      //            } else {
      //              System.out.print(grid[k][j]);
      //            }
      //          }
      //          System.out.println();
      //        }
      //        System.out.println();

      if (curr.equals(initialPoint) && curr.max > 0) {
        System.out.println("Back at initial!! " + curr + ", steps: " + (curr.max / 2));
        max = curr.max;
        break;
      }

      int[][] moves = getMoves(c);

      for (int[] move : moves) {
        int newrow = curr.row + move[0];
        int newcol = curr.col + move[1];

        if (newrow < 0
            || newcol < 0
            || newrow >= grid.length
            || newcol >= grid[0].length
            || (newrow == curr.prevRow && newcol == curr.prevCol)
            || grid[newrow][newcol] == '.') {
          continue;
        }

        System.out.println("Going next to " + newrow + " and " + newcol);
        queue.offer(new Point(curr.row, curr.col, newrow, newcol, curr.max + 1));
        visited[newrow][newcol] = true;
      }
    }

    int outer = dfs(0, 0, grid, visited);
    System.out.println("Outer: " + outer);

    System.out.println("Ans2: " + ((grid.length * grid[0].length) - outer - max) / 2);
  }

  private static int dfs(int row, int col, char[][] grid, boolean[][] visited) {
    if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || visited[row][col]) {
      return 0;
    }

    visited[row][col] = true;

    return 1
        + dfs(row + 1, col, grid, visited)
        + dfs(row - 1, col, grid, visited)
        + dfs(row, col + 1, grid, visited)
        + dfs(row, col - 1, grid, visited);
  }

  private static int[][] getMoves(char c) {
    int[][] moves = new int[0][0];

    if (c == 'S') {
      moves =
          new int[][] {
            // {-1, 0},
            // {1, 0},
            {0, -1},
            // {0, 1}
          };
    } else if (c == '-') {
      moves =
          new int[][] {
            {0, -1},
            {0, 1}
          };
    } else if (c == '|') {
      moves =
          new int[][] {
            {-1, 0},
            {1, 0}
          };
    } else if (c == 'L') {
      moves =
          new int[][] {
            {-1, 0},
            {0, 1}
          };
    } else if (c == 'J') {
      moves =
          new int[][] {
            {-1, 0},
            {0, -1}
          };
    } else if (c == '7') {
      moves =
          new int[][] {
            {1, 0},
            {0, -1}
          };
    } else if (c == 'F') {
      moves =
          new int[][] {
            {1, 0},
            {0, 1}
          };
    }
    return moves;
  }
  static class Point {
    int prevRow;
    int prevCol;

    int row;
    int col;
    int max;

    public Point(int row, int col) {
      this.row = row;
      this.col = col;
    }

    public Point(int row, int col, int max) {
      this.row = row;
      this.col = col;
      this.max = max;
    }

    public Point(int prevRow, int prevCol, int row, int col, int max) {
      this.prevRow = prevRow;
      this.prevCol = prevCol;
      this.row = row;
      this.col = col;
      this.max = max;
    }

    @Override
    public String toString() {
      return String.format("(row:%d,col:%d,dist=%d)", row, col, max);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Point point = (Point) o;
      return row == point.row && col == point.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }

}
