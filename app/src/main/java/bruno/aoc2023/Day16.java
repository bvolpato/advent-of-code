package bruno.aoc2023;

import static bruno.util.AOCReader.grid;

import bruno.util.AOCReader;
import java.io.IOException;
import java.util.*;

public class Day16 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2023, 16);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part 1: " + part1(clipboard, 0, 0, RIGHT));
    System.out.println("Part 2: " + part2(clipboard));
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

    char[][] grid = grid(clipboard);

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

          dir =
              switch (dir) {
                case RIGHT -> DOWN;
                case DOWN -> RIGHT;
                case LEFT -> UP;
                case UP -> LEFT;
                default -> dir;
              };
          queue.offer(new int[] {row + moves[dir][0], col + moves[dir][1], dir});
        } else if (grid[row][col] == '/') {

          dir =
              switch (dir) {
                case RIGHT -> UP;
                case DOWN -> LEFT;
                case LEFT -> DOWN;
                case UP -> RIGHT;
                default -> dir;
              };

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
    }

    return energized;
  }

  public static int part2(String clipboard) {
    int ans = 0;
    char[][] grid = grid(clipboard);

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

    return ans;
  }
}
