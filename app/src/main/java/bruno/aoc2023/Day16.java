package bruno.aoc2023;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.*;
import org.brunocvcunha.inutils4j.MyStringUtils;

public class Day16 {

  public static void main(String[] args) throws IOException {
    String clipboard = MyStringUtils.getContent(Day16.class.getResourceAsStream("/day16.txt"));
    System.out.println("Clipboard content: " + clipboard);

    part1(clipboard, 0, 0, RIGHT);
    part2(clipboard);
  }

  static final int RIGHT = 0;
  static final int DOWN = 1;
  static final int LEFT = 2;
  static final int UP = 3;

  static int[][] moves =
      new int[][] {
        {0, 1}, // right
        {1, 0}, // down
        {0, -1}, // left
        {-1, 0} // up
      };

  public static int part1(String clipboard, int iRow, int iCol, int iDir) {

    int rows = 0;

    char[][] grid = new char[clipboard.split("\n").length][clipboard.split("\n")[0].length()];
    for (String line : clipboard.split("\n")) {
      for (int i = 0; i < line.length(); i++) {
        grid[rows][i] = line.charAt(i);
      }
      rows++;
    }

    int energized = 0;

    boolean[][][] visited = new boolean[grid.length][grid[0].length][4];
    boolean[][] energizedVisited = new boolean[grid.length][grid[0].length];

    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] {iRow, iCol, iDir});

    while (!queue.isEmpty()) {

      int levelSize = queue.size();
      for (int i = 0; i < levelSize; i++) {

        int[] curr = queue.poll();
        int row = curr[0];
        int col = curr[1];
        int dir = curr[2];

        if (row < 0
            || row >= grid.length
            || col < 0
            || col >= grid[0].length
            || visited[row][col][dir]) {
          continue;
        }

        if (!energizedVisited[row][col]) {
          energizedVisited[row][col] = true;
          energized++;
        }

        visited[row][col][dir] = true;

        if (grid[row][col] == '.' || grid[row][col] == '#') {
          queue.offer(new int[] {row + moves[dir][0], col + moves[dir][1], dir});
        } else if (grid[row][col] == '\\') {

          switch (dir) {
            case RIGHT:
              dir = DOWN;
              break;
            case DOWN:
              dir = RIGHT;
              break;
            case LEFT:
              dir = UP;
              break;
            case UP:
              dir = LEFT;
              break;
          }
          queue.offer(new int[] {row + moves[dir][0], col + moves[dir][1], dir});
        } else if (grid[row][col] == '/') {

          switch (dir) {
            case RIGHT:
              dir = UP;
              break;
            case DOWN:
              dir = LEFT;
              break;
            case LEFT:
              dir = DOWN;
              break;
            case UP:
              dir = RIGHT;
              break;
          }

          queue.offer(new int[] {row + moves[dir][0], col + moves[dir][1], dir});
        } else if (grid[row][col] == '|') {

          if (dir == DOWN || dir == UP) {
            queue.offer(new int[] {row + moves[dir][0], col + moves[dir][1], dir});
          } else {
            queue.offer(new int[] {row - 1, col, UP});
            queue.offer(new int[] {row + 1, col, DOWN});
          }

        } else if (grid[row][col] == '-') {

          if (dir == LEFT || dir == RIGHT) {
            queue.offer(new int[] {row + moves[dir][0], col + moves[dir][1], dir});
          } else {
            queue.offer(new int[] {row, col - 1, LEFT});
            queue.offer(new int[] {row, col + 1, RIGHT});
          }
        }
      }
      // Print the whole grid
      //      System.out.println("Energized: " + energized);
      //      for (int i = 0; i < grid.length; i++) {
      //        System.out.println(Arrays.toString(grid[i]));
      //      }
    }

    return energized;
  }

  public static void part2(String clipboard) {
    int ans = 0;

    int rows = 0;

    char[][] grid = new char[clipboard.split("\n").length][clipboard.split("\n")[0].length()];
    for (String line : clipboard.split("\n")) {
      for (int i = 0; i < line.length(); i++) {
        grid[rows][i] = line.charAt(i);
      }
      rows++;
    }

    // Try all top row positions
    for (int i = 0; i < grid[0].length; i++) {
      ans = Math.max(ans, part1(clipboard, 0, i, DOWN));
    }
    // Try all left column positions
    for (int i = 0; i < grid.length; i++) {
      ans = Math.max(ans, part1(clipboard, i, 0, RIGHT));
    }
    // Try all right column positions
    for (int i = 0; i < grid.length; i++) {
      ans = Math.max(ans, part1(clipboard, i, grid[0].length - 1, LEFT));
    }
    // Try all bottom row positions
    for (int i = 0; i < grid[0].length; i++) {
      ans = Math.max(ans, part1(clipboard, grid.length - 1, i, UP));
    }

    System.out.println("Ans2: " + ans);
  }
}
