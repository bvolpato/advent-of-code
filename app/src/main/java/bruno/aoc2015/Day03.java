package bruno.aoc2015;

import bruno.util.AOCReader;
import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
