package bruno.aoc2015;

import bruno.util.AOCReader;
import java.io.IOException;
import java.util.List;

public class Day06 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2015, 6);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part1: " + part1(clipboard));
    System.out.println("Part2: " + part2(clipboard));
  }

  private static int part1(String clipboard) {

    List<String> lines = AOCReader.lines(clipboard);

    boolean[][] grid = new boolean[1000][1000];

    for (String line : lines) {
      line = line.replace("turn ", "");

      String from = line.split(" ")[1];
      String to = line.split(" ")[3];

      int x1 = Integer.parseInt(from.substring(0, from.indexOf(',')));
      int y1 = Integer.parseInt(from.substring(from.indexOf(',') + 1));
      int x2 = Integer.parseInt(to.substring(0, to.indexOf(',')));
      int y2 = Integer.parseInt(to.substring(to.indexOf(',') + 1));

      for (int x = x1; x <= x2; x++) {
        for (int y = y1; y <= y2; y++) {
          if (line.startsWith("on")) {
            grid[x][y] = true;
          } else if (line.startsWith("off")) {
            grid[x][y] = false;
          } else if (line.startsWith("toggle")) {
            grid[x][y] = !grid[x][y];
          }
        }
      }
    }

    int ans = 0;
    for (int x = 0; x < grid.length; x++) {
      for (int y = 0; y < grid[0].length; y++) {
        if (grid[x][y]) {
          ans++;
        }
      }
    }
    return ans;
  }

  private static int part2(String clipboard) {

    List<String> lines = AOCReader.lines(clipboard);

    int[][] grid = new int[1000][1000];

    for (String line : lines) {
      line = line.replace("turn ", "");

      String from = line.split(" ")[1];
      String to = line.split(" ")[3];

      int x1 = Integer.parseInt(from.substring(0, from.indexOf(',')));
      int y1 = Integer.parseInt(from.substring(from.indexOf(',') + 1));
      int x2 = Integer.parseInt(to.substring(0, to.indexOf(',')));
      int y2 = Integer.parseInt(to.substring(to.indexOf(',') + 1));

      for (int x = x1; x <= x2; x++) {
        for (int y = y1; y <= y2; y++) {
          if (line.startsWith("on")) {
            grid[x][y]++;
          } else if (line.startsWith("off")) {
            grid[x][y]--;
          } else if (line.startsWith("toggle")) {
            grid[x][y] += 2;
          }

          if (grid[x][y] < 0) {
            grid[x][y] = 0;
          }
        }
      }
    }

    int ans = 0;
    for (int x = 0; x < grid.length; x++) {
      for (int y = 0; y < grid[0].length; y++) {
        ans += grid[x][y];
      }
    }
    return ans;  }

}
