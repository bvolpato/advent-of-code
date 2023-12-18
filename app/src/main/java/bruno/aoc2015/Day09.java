package bruno.aoc2015;

import bruno.util.AOCReader;
import bruno.util.Combinatorics;
import java.io.IOException;
import java.util.*;

public class Day09 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2015, 9);
    System.out.println("Clipboard content: " + clipboard);
    solve(clipboard);
  }

  private static void solve(String clipboard) {

    List<String> lines = AOCReader.lines(clipboard);
    Map<String, Integer> cities = new HashMap<>();

    int cityIndex = 0;

    for (String line : lines) {
      String from = line.split(" ")[0];
      if (!cities.containsKey(from)) {
        cities.put(from, cityIndex++);
      }
      String to = line.split(" ")[2];
      if (!cities.containsKey(to)) {
        cities.put(to, cityIndex++);
      }
    }

    int V = cities.size();

    int[][] graphMatrix = new int[V][V];
    for (int i = 0; i < V; i++) {
      Arrays.fill(graphMatrix[i], Integer.MAX_VALUE);
    }
    for (String line : lines) {
      int from = cities.get(line.split(" ")[0]);
      int to = cities.get(line.split(" ")[2]);
      int dist = Integer.parseInt(line.split(" ")[4]);
      graphMatrix[from][to] = dist;
      graphMatrix[to][from] = dist;
    }

    // Create a sequence from 0 to V-1
    List<Integer> sequence = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      sequence.add(i);
    }

    List<List<Integer>> permutations = Combinatorics.getPermutations(sequence);

    int ans1 = Integer.MAX_VALUE;
    int ans2 = Integer.MIN_VALUE;

    for (List<Integer> permutation : permutations) {
      int dist = 0;
      for (int i = 0; i < permutation.size() - 1; i++) {
        int from = permutation.get(i);
        int to = permutation.get(i + 1);

        if (graphMatrix[from][to] == Integer.MAX_VALUE) {
          dist = Integer.MAX_VALUE;
          break;
        }
        dist += graphMatrix[from][to];
      }
      ans1 = Integer.min(ans1, dist);
      ans2 = Integer.max(ans2, dist);
    }

    System.out.println("Part 1: " + ans1);
    System.out.println("Part 2: " + ans2);
  }
}
