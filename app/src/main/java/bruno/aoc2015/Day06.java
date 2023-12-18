package bruno.aoc2015;

import bruno.util.AOCReader;
import java.io.IOException;
import java.util.List;

/**
 * --- Day 6: Probably a Fire Hazard ---
 *
 * <p>Because your neighbors keep defeating you in the holiday house decorating contest year after
 * year, you've decided to deploy one million lights in a 1000x1000 grid.
 *
 * <p>Furthermore, because you've been especially nice this year, Santa has mailed you instructions
 * on how to display the ideal lighting configuration.
 *
 * <p>Lights in your grid are numbered from 0 to 999 in each direction; the lights at each corner
 * are at 0,0, 0,999, 999,999, and 999,0. The instructions include whether to turn on, turn off, or
 * toggle various inclusive ranges given as coordinate pairs. Each coordinate pair represents
 * opposite corners of a rectangle, inclusive; a coordinate pair like 0,0 through 2,2 therefore
 * refers to 9 lights in a 3x3 square. The lights all start turned off.
 *
 * <p>To defeat your neighbors this year, all you have to do is set up your lights by doing the
 * instructions Santa sent you in order.
 *
 * <p>For example:
 *
 * <p>turn on 0,0 through 999,999 would turn on (or leave on) every light. toggle 0,0 through 999,0
 * would toggle the first line of 1000 lights, turning off the ones that were on, and turning on the
 * ones that were off. turn off 499,499 through 500,500 would turn off (or leave off) the middle
 * four lights.
 *
 * <p>After following the instructions, how many lights are lit?
 *
 * <p>Your puzzle answer was 569999. --- Part Two ---
 *
 * <p>You just finish implementing your winning light pattern when you realize you mistranslated
 * Santa's message from Ancient Nordic Elvish.
 *
 * <p>The light grid you bought actually has individual brightness controls; each light can have a
 * brightness of zero or more. The lights all start at zero.
 *
 * <p>The phrase turn on actually means that you should increase the brightness of those lights by
 * 1.
 *
 * <p>The phrase turn off actually means that you should decrease the brightness of those lights by
 * 1, to a minimum of zero.
 *
 * <p>The phrase toggle actually means that you should increase the brightness of those lights by 2.
 *
 * <p>What is the total brightness of all lights combined after following Santa's instructions?
 *
 * <p>For example:
 *
 * <p>turn on 0,0 through 0,0 would increase the total brightness by 1. toggle 0,0 through 999,999
 * would increase the total brightness by 2000000.
 *
 * <p>Your puzzle answer was 17836115.
 */
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
    return ans;
  }
}
