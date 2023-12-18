package bruno.aoc2015;

import bruno.util.AOCReader;
import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * --- Day 3: Perfectly Spherical Houses in a Vacuum ---
 *
 * <p>Santa is delivering presents to an infinite two-dimensional grid of houses.
 *
 * <p>He begins by delivering a present to the house at his starting location, and then an elf at
 * the North Pole calls him via radio and tells him where to move next. Moves are always exactly one
 * house to the north (^), south (v), east (>), or west (<). After each move, he delivers another
 * present to the house at his new location.
 *
 * <p>However, the elf back at the north pole has had a little too much eggnog, and so his
 * directions are a little off, and Santa ends up visiting some houses more than once. How many
 * houses receive at least one present?
 *
 * <p>For example:
 *
 * <p>> delivers presents to 2 houses: one at the starting location, and one to the east. ^>v<
 * delivers presents to 4 houses in a square, including twice to the house at his starting/ending
 * location. ^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.
 *
 * <p>Your puzzle answer was 2592. --- Part Two ---
 *
 * <p>The next year, to speed up the process, Santa creates a robot version of himself, Robo-Santa,
 * to deliver presents with him.
 *
 * <p>Santa and Robo-Santa start at the same location (delivering two presents to the same starting
 * house), then take turns moving based on instructions from the elf, who is eggnoggedly reading
 * from the same script as the previous year.
 *
 * <p>This year, how many houses receive at least one present?
 *
 * <p>For example:
 *
 * <p>^v delivers presents to 3 houses, because Santa goes north, and then Robo-Santa goes south.
 * ^>v< now delivers presents to 3 houses, and Santa and Robo-Santa end up back where they started.
 * ^v^v^v^v^v now delivers presents to 11 houses, with Santa going one direction and Robo-Santa
 * going the other.
 *
 * <p>Your puzzle answer was 2360.
 */
public class Day03 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2015, 3);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part1: " + part1(clipboard).size());
    System.out.println("Part2: " + part2(clipboard).size());
  }

  private static Set<Point> part1(String clipboard) {
    int x = 0;
    int y = 0;

    Set<Point> visited = new HashSet<>();
    visited.add(new Point(0, 0));

    deliver(clipboard, 0, 1, visited);
    return visited;
  }

  private static Set<Point> part2(String clipboard) {
    int x = 0;
    int y = 0;

    Set<Point> visited = new HashSet<>();
    visited.add(new Point(0, 0));

    deliver(clipboard, 0, 2, visited);
    deliver(clipboard, 1, 2, visited);

    return visited;
  }

  private static void deliver(String clipboard, int start, int increment, Set<Point> visited) {
    int x = 0;
    int y = 0;
    for (int i = start; i < clipboard.length(); i += increment) {
      char c = clipboard.charAt(i);
      if (c == '^') {
        y--;
      } else if (c == '>') {
        x++;
      } else if (c == '<') {
        x--;
      } else if (c == 'v') {
        y++;
      } else {
        throw new IllegalArgumentException("Invalid char: " + c);
      }
      visited.add(new Point(x, y));
    }
  }

  static class Point {
    int x;
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Point)) {
        return false;
      }

      Point other = (Point) obj;
      return other.x == x && other.y == y;
    }

    @Override
    public int hashCode() {
      return x * 31 + y;
    }
  }
}
