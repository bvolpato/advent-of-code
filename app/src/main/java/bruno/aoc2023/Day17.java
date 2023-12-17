package bruno.aoc2023;

import static bruno.util.AOCReader.grid;
import static bruno.util.AOCReader.intGrid;

import bruno.util.AOCReader;
import bruno.util.Direction;
import bruno.util.Pair;
import bruno.util.Point2D;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Day17 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2023, 17);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part 1: " + part1(clipboard));
    System.out.println("Part 2: " + part2(clipboard));
  }

  public static int part1(String clipboard) {
    return solve(clipboard, 1, 3);
  }

  public static int part2(String clipboard) {
    return solve(clipboard, 4, 10);
  }

  public static int solve(String clipboard, int minSteps, int maxSteps) {

    int[][] grid = intGrid(clipboard);

    Node[][] nodes = new Node[grid.length][grid[0].length];

    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        Node node = new Node(grid[row][col]);
        nodes[row][col] = node;
      }
    }

    Point2D target = new Point2D(grid.length - 1, grid[0].length - 1);

    PriorityQueue<Explorer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.heat));
    queue.add(new Explorer(new Point2D(0, 0), Direction.RIGHT, 0, 0));
    queue.add(new Explorer(new Point2D(0, 0), Direction.DOWN, 0, 0));

    int minHeat = Integer.MAX_VALUE;

    while (!queue.isEmpty()) {
      Explorer explorer = queue.poll();
      Point2D currentPos = explorer.position;

      if (!currentPos.isValid(nodes)) {
        System.out.println("Invalid position: " + currentPos);
        break;
      }

      if (currentPos.equals(target) && explorer.directionSteps >= minSteps) {
        System.out.println("Found target: " + explorer + " - " + explorer.heat);
        minHeat = Math.min(minHeat, explorer.heat);
        continue;
      }

      Node node = nodes[currentPos.x][currentPos.y];
      if (node.visited.contains(new Pair<>(explorer.direction, explorer.directionSteps))) {
        continue;
      }

      node.visited.add(new Pair<>(explorer.direction, explorer.directionSteps));

      if (explorer.directionSteps < maxSteps) {
        Point2D next =
            new Point2D(
                currentPos.x + explorer.direction.col, currentPos.y + explorer.direction.row);

        if (next.isValid(nodes)) {
          Node nextNode = nodes[next.x][next.y];

          if (!nextNode.visited.contains(new Pair<>(explorer.direction, explorer.directionSteps))) {
            queue.add(
                new Explorer(
                    next,
                    explorer.direction,
                    explorer.directionSteps + 1,
                    explorer.heat + nextNode.heat));
          }
        }
      }

      if (explorer.directionSteps >= minSteps) {
        Direction rightDirection = explorer.direction.right();
        Point2D rightPoint =
            new Point2D(currentPos.x + rightDirection.col, currentPos.y + rightDirection.row);
        if (rightPoint.isValid(nodes)) {
          Node rightNode = nodes[rightPoint.x][rightPoint.y];
          if (!rightNode.visited.contains(new Pair<>(rightDirection, 1))) {
            queue.add(new Explorer(rightPoint, rightDirection, 1, explorer.heat + rightNode.heat));
          }
        }

        Direction leftDirection = explorer.direction.left();
        Point2D leftPoint =
            new Point2D(currentPos.x + leftDirection.col, currentPos.y + leftDirection.row);
        if (leftPoint.isValid(nodes)) {
          Node leftNode = nodes[leftPoint.x][leftPoint.y];
          if (!leftNode.visited.contains(new Pair<>(leftDirection, 1))) {
            queue.add(new Explorer(leftPoint, leftDirection, 1, explorer.heat + leftNode.heat));
          }
        }
      }
    }

    return minHeat;
  }

  public static record Explorer(
      Point2D position, Direction direction, int directionSteps, int heat) {}

  static class Node {
    int heat;
    Set<Pair<Direction, Integer>> visited;

    public Node(int heat) {
      this.heat = heat;
      this.visited = new HashSet<>();
    }
  }
}
