package bruno.util;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.brunocvcunha.inutils4j.MyStringUtils;

/** Reading input files for Advent of Code, in different formats. */
public class AOCReader {

  public static String readDay(int year, int day) {
    try {
      return MyStringUtils.getContent(
              Objects.requireNonNull(
                  AOCReader.class.getResourceAsStream(
                      "/" + year + "/day" + (day < 10 ? "0" : "") + day + ".txt")))
          .trim();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String readSample(int year, int day) {
    try {
      return MyStringUtils.getContent(
              Objects.requireNonNull(
                  AOCReader.class.getResourceAsStream(
                      "/" + year + "/day" + (day < 10 ? "0" : "") + day + "-sample.txt")))
          .trim();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<String> lines(String clipboard) {
    return MyStringUtils.asListLines(clipboard);
  }

  public static char[][] grid(String clipboard) {
    List<String> lines = MyStringUtils.asListLines(clipboard);
    char[][] grid = new char[lines.size()][];
    for (int i = 0; i < lines.size(); i++) {
      grid[i] = lines.get(i).toCharArray();
    }
    return grid;
  }

  public static int[][] intGrid(String clipboard) {
    List<String> lines = MyStringUtils.asListLines(clipboard);
    int[][] grid = new int[lines.size()][];
    for (int i = 0; i < lines.size(); i++) {

      int[] nums = new int[lines.get(i).length()];
      for (int j = 0; j < lines.get(i).length(); j++) {
        nums[j] = Character.getNumericValue(lines.get(i).charAt(j));
      }
      grid[i] = nums;
    }
    return grid;
  }
}
