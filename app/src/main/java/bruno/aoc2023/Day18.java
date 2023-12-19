package bruno.aoc2023;

import bruno.util.AOCReader;
import bruno.util.Direction;
import bruno.util.Point2D;
import bruno.util.grid.InfiniteGrid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 18: Lavaduct Lagoon ---
 *
 * <p>Thanks to your efforts, the machine parts factory is one of the first factories up and running
 * since the lavafall came back. However, to catch up with the large backlog of parts requests, the
 * factory will also need a large supply of lava for a while; the Elves have already started
 * creating a large lagoon nearby for this purpose.
 *
 * <p>However, they aren't sure the lagoon will be big enough; they've asked you to take a look at
 * the dig plan (your puzzle input). For example:
 *
 * <p>R 6 (#70c710) D 5 (#0dc571) L 2 (#5713f0) D 2 (#d2c081) R 2 (#59c680) D 2 (#411b91) L 5
 * (#8ceee2) U 2 (#caa173) L 1 (#1b58a2) U 2 (#caa171) R 2 (#7807d2) U 3 (#a77fa3) L 2 (#015232) U 2
 * (#7a21e3)
 *
 * <p>The digger starts in a 1 meter cube hole in the ground. They then dig the specified number of
 * meters up (U), down (D), left (L), or right (R), clearing full 1 meter cubes as they go. The
 * directions are given as seen from above, so if "up" were north, then "right" would be east, and
 * so on. Each trench is also listed with the color that the edge of the trench should be painted as
 * an RGB hexadecimal color code.
 *
 * <p>When viewed from above, the above example dig plan would result in the following loop of
 * trench (#) having been dug out from otherwise ground-level terrain (.):
 *
 * <p>####### #.....# ###...# ..#...# ..#...# ###.### #...#.. ##..### .#....# .######
 *
 * <p>At this point, the trench could contain 38 cubic meters of lava. However, this is just the
 * edge of the lagoon; the next step is to dig out the interior so that it is one meter deep as
 * well:
 *
 * <p>####### ####### ####### ..##### ..##### ####### #####.. ####### .###### .######
 *
 * <p>Now, the lagoon can contain a much more respectable 62 cubic meters of lava. While the
 * interior is dug out, the edges are also painted according to the color codes in the dig plan.
 *
 * <p>The Elves are concerned the lagoon won't be large enough; if they follow their dig plan, how
 * many cubic meters of lava could it hold?
 *
 * <p>Your puzzle answer was 108909. --- Part Two ---
 *
 * <p>The Elves were right to be concerned; the planned lagoon would be much too small.
 *
 * <p>After a few minutes, someone realizes what happened; someone swapped the color and instruction
 * parameters when producing the dig plan. They don't have time to fix the bug; one of them asks if
 * you can extract the correct instructions from the hexadecimal codes.
 *
 * <p>Each hexadecimal code is six hexadecimal digits long. The first five hexadecimal digits encode
 * the distance in meters as a five-digit hexadecimal number. The last hexadecimal digit encodes the
 * direction to dig: 0 means R, 1 means D, 2 means L, and 3 means U.
 *
 * <p>So, in the above example, the hexadecimal codes can be converted into the true instructions:
 *
 * <p>#70c710 = R 461937 #0dc571 = D 56407 #5713f0 = R 356671 #d2c081 = D 863240 #59c680 = R 367720
 * #411b91 = D 266681 #8ceee2 = L 577262 #caa173 = U 829975 #1b58a2 = L 112010 #caa171 = D 829975
 * #7807d2 = L 491645 #a77fa3 = U 686074 #015232 = L 5411 #7a21e3 = U 500254
 *
 * <p>Digging out this loop and its interior produces a lagoon that can hold an impressive
 * 952408144115 cubic meters of lava.
 *
 * <p>Convert the hexadecimal color codes into the correct instructions; if the Elves follow this
 * new dig plan, how many cubic meters of lava could the lagoon hold?
 *
 * <p>Your puzzle answer was 133125706867777.
 */
public class Day18 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readSample(2023, 18);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part 1: " + part1(clipboard));
    System.out.println("Part 2: " + part2(clipboard));
  }

  public static int part1(String clipboard) {

    List<String> lines = AOCReader.lines(clipboard);

    InfiniteGrid infiniteGrid = new InfiniteGrid();

    Point2D start = new Point2D(0, 0);
    Point2D curr = new Point2D(start.x, start.y);

    List<Point2D> points = new ArrayList<>();
    points.add(start);

    for (String line : lines) {
      String dir = line.substring(0, 1);

      Direction direction;
      switch (dir) {
        case "R":
          direction = Direction.RIGHT;
          break;
        case "L":
          direction = Direction.LEFT;
          break;
        case "U":
          direction = Direction.UP;
          break;
        case "D":
          direction = Direction.DOWN;
          break;
        default:
          throw new RuntimeException("Invalid direction: " + dir);
      }

      int distance = Integer.parseInt(line.split(" ")[1]);
      String color = line.substring(4).replace(")", "").replace("(", "");

      System.out.println(
          "Direction: " + direction + " - Distance: " + distance + " - Color: " + color);

      infiniteGrid.put(curr, '#');

      for (int i = 1; i <= distance; i++) {
        Point2D next = new Point2D(curr.x + direction.col, curr.y + direction.row);
        infiniteGrid.put(curr, '#');
        //        System.out.println("Paint: " + next + " - " + color);
        curr = next;
      }
      points.add(curr);
    }

    // infiniteGrid.print();

    int[][] grid = infiniteGrid.materializeInt('.');

    int internal =
        floodfill(grid, (int) (-infiniteGrid.minRow + 1), (int) (-infiniteGrid.minCol + 1));

    // Print grid
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        char valueChar = (char) grid[row][col];
        if (valueChar == 0) {
          valueChar = ' ';
        }
        System.out.print(valueChar);
      }
      System.out.println();
    }

    int ans = internal + infiniteGrid.size();
    System.out.println("Ans: " + ans);
    return internal + infiniteGrid.size();
  }

  public static int floodfill(int[][] grid, int row, int col) {
    if (row < 0
        || row >= grid.length
        || col < 0
        || col >= grid[0].length
        || grid[row][col] == '#') {
      return 0;
    }

    grid[row][col] = '#';
    return 1
        + floodfill(grid, row - 1, col)
        + floodfill(grid, row + 1, col)
        + floodfill(grid, row, col - 1)
        + floodfill(grid, row, col + 1);
  }

  public static long part2(String clipboard) {
    List<String> lines = AOCReader.lines(clipboard);

    Point2D start = new Point2D(0, 0);
    Point2D curr = new Point2D(start.x, start.y);

    List<Point2D> points = new ArrayList<>();
    int numPoints = 0;

    for (String line : lines) {
      String color = line.substring(4).replace(")", "").replace("(", "").trim();

      String hex = color.substring(1, 6);
      int distance = Integer.parseInt(hex, 16);

      Direction direction =
          switch (color.substring(6)) {
            case "0" -> Direction.RIGHT;
            case "1" -> Direction.UP;
            case "2" -> Direction.LEFT;
            case "3" -> Direction.DOWN;
            default -> throw new RuntimeException("Invalid direction: " + color.substring(6));
          };

      System.out.println(
          "Direction: " + direction + " - Distance: " + distance + " - Color: " + color);

      Point2D next =
          new Point2D(
              curr.x + ((long) direction.col * distance),
              curr.y + ((long) direction.row * distance));
      points.add(next);
      curr = next;

        numPoints += distance;

    }

    // Print points
    for (Point2D point : points) {
      System.out.println("(" + point.y + ", " + point.x + ")");
    }

    points.add(start);

    double I = shoelaceArea(points);
    int b = numPoints;

    System.out.println("I: " + (long) I);
    System.out.println("b: " + b);

    return (long) I + b;
  }

  // Use Shoelace algorithm to calculate area of polygon
  // https://en.wikipedia.org/wiki/Shoelace_formula
  private static double shoelaceArea(List<Point2D> v) {
    int n = v.size();
    double a = 0.0;
    for (int i = 0; i < n - 1; i++) {
      a += v.get(i).x * v.get(i + 1).y - v.get(i + 1).x * v.get(i).y;
    }
    return Math.abs(a + v.get(n - 1).x * v.get(0).y - v.get(0).x * v.get(n - 1).y) / 2.0;
  }
}
