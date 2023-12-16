package bruno.aoc2023;

import static bruno.util.AOCReader.readDay;

import bruno.util.AOCReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day11 {

  public static void main(String[] args) throws IOException {
    String clipboard = readDay(2023, 11);
    System.out.println("Clipboard content: " + clipboard);

    char[][] grid = AOCReader.grid(clipboard);

    Set<Integer> rowWithGalaxies = new HashSet<>();
    Set<Integer> colWithGalaxies = new HashSet<>();

    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == '#') {
          rowWithGalaxies.add(row);
          colWithGalaxies.add(col);
        }
      }
    }

    TreeSet<Integer> rowWithoutGalaxies =
        new TreeSet<>(
            IntStream.rangeClosed(0, grid.length).mapToObj(i -> i).collect(Collectors.toList()));
    rowWithoutGalaxies.removeAll(rowWithGalaxies);

    TreeSet<Integer> colWithoutGalaxies =
        new TreeSet<>(
            IntStream.rangeClosed(0, grid[0].length).mapToObj(i -> i).collect(Collectors.toList()));
    colWithoutGalaxies.removeAll(colWithGalaxies);

    List<int[]> galaxies = new ArrayList<>();
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        if (grid[row][col] == '#') {
          galaxies.add(new int[] {row, col});
        }
      }
    }

    long distance = 0;

    // expansion = 2 for pt 1
    long expansion = 1_000_000;

    long totalWarps = 0;

    for (int i = 0; i < galaxies.size(); i++) {
      for (int j = i + 1; j < galaxies.size(); j++) {

        int noGalaxyRowsInBetween;
        if (galaxies.get(i)[0] <= galaxies.get(j)[0]) {
          noGalaxyRowsInBetween =
              rowWithoutGalaxies
                  .subSet(galaxies.get(i)[0], false, galaxies.get(j)[0], false)
                  .size();
        } else {
          noGalaxyRowsInBetween =
              rowWithoutGalaxies
                  .subSet(galaxies.get(j)[0], false, galaxies.get(i)[0], false)
                  .size();
        }
        int noGalaxyColsInBetween;
        if (galaxies.get(i)[1] <= galaxies.get(j)[1]) {
          noGalaxyColsInBetween =
              colWithoutGalaxies
                  .subSet(galaxies.get(i)[1], false, galaxies.get(j)[1], false)
                  .size();
        } else {
          noGalaxyColsInBetween =
              colWithoutGalaxies
                  .subSet(galaxies.get(j)[1], false, galaxies.get(i)[1], false)
                  .size();
        }

        System.out.println(
            "Between ("
                + galaxies.get(i)[0]
                + ","
                + galaxies.get(i)[1]
                + ") and ("
                + galaxies.get(j)[0]
                + ","
                + galaxies.get(j)[1]
                + ") there are empty: "
                + noGalaxyRowsInBetween
                + " rows and "
                + noGalaxyColsInBetween
                + " cols.");

        totalWarps += noGalaxyRowsInBetween;
        totalWarps += noGalaxyColsInBetween;
        distance += (Math.abs(galaxies.get(i)[0] - galaxies.get(j)[0]));
        distance += (Math.abs(galaxies.get(i)[1] - galaxies.get(j)[1]));
      }
    }

    System.out.println(distance);
    System.out.println(totalWarps);
    System.out.println(distance + (totalWarps * (expansion - 1)));
  }
}
