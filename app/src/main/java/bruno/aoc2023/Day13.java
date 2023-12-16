package bruno.aoc2023;

import static bruno.util.AOCReader.readDay;

import java.io.IOException;
import java.util.Arrays;

public class Day13 {

  public static void main(String[] args) throws IOException {
    String clipboard = readDay(2023, 13);
    System.out.println("Clipboard content: " + clipboard);

    String[] blocks = clipboard.split("\n\n");

    int rows1 = 0;
    int columns1 = 0;

    int rows2 = 0;
    int columns2 = 0;

    for (String block : blocks) {

      System.out.println("====================");
      char[][] grid =
          Arrays.stream(block.split("\n")).map(String::toCharArray).toList().toArray(new char[0][]);

      System.out.println("\nNormal:");
      for (int i = 0; i < grid.length; i++) {
        System.out.println(new String(grid[i]));
      }
      System.out.println("\nTransposed:");
      char[][] transposed = transpose(grid);
      for (int i = 0; i < transposed.length; i++) {
        System.out.println(new String(transposed[i]));
      }

      System.out.println("Rows pt1");
      rows1 += getMirrors(grid, false);
      System.out.println("Rows pt2");
      rows2 += getMirrors(grid, true);

      System.out.println("Columns pt1");
      columns1 += getMirrors(transposed, false);
      System.out.println("Columns pt2");
      columns2 += getMirrors(transposed, true);
    }

    System.out.println("Pt1: Total rows " + rows1 + ", Total columns: " + columns1);
    System.out.println("Pt2: Total rows " + rows2 + ", Total columns: " + columns2);

    System.out.println("Ans1: " + (((rows1) * 100) + (columns1)));
    System.out.println("Ans2: " + (((rows2) * 100) + (columns2)));
  }

  private static int getMirrors(char[][] grid, boolean smudge) {
    for (int left = 0; left < grid.length - 1; left++) {

      int matches = 0;
      int right = left + 1;

      int l = left;
      int r = right;

      boolean canSmudge = smudge;

      while (l < r && l >= 0 && r < grid.length && lineEquals(grid[l], grid[r], canSmudge)) {
        System.out.println("(" + left + "," + right + ") Match " + l + " and " + r);

        if (diffCount(grid[l], grid[r]) == 1) {
          canSmudge = false;
        }

        l--;
        r++;
        matches++;
      }

      System.out.println(
          "("
              + left
              + ","
              + right
              + ") Matches is "
              + (matches)
              + " at ("
              + left
              + ","
              + right
              + ") but left is "
              + left
              + " or "
              + grid.length);
      if (matches > 0 && (l < 0 || r >= grid.length)) {
        if (smudge && canSmudge) continue;

        System.out.println("Return " + (left + 1));
        return left + 1;
      }
    }
    return 0;
  }

  private static boolean lineEquals(char[] line1, char[] line2, boolean smudge) {
    if (!smudge) {
      return Arrays.equals(line1, line2);
    }

    return diffCount(line1, line2) <= 1;
  }

  private static int diffCount(char[] line1, char[] line2) {
    int diffs = 0;
    for (int i = 0; i < line1.length; i++) {
      if (line1[i] != line2[i]) {
        diffs++;
      }
    }

    return diffs;
  }

  public static char[][] transpose(char[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    char[][] transposed = new char[cols][rows];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        transposed[j][i] = matrix[i][j];
      }
    }

    return transposed;
  }
}
