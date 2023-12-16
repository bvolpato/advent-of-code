package bruno.aoc2015;

import bruno.util.AOCReader;
import java.io.IOException;
import java.util.List;

import org.brunocvcunha.inutils4j.MyStringUtils;

public class Day05 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2015, 5);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part1: " + part1(clipboard));
    System.out.println("Part2: " + part2(clipboard));
  }

  private static int part1(String clipboard) {

    List<String> lines = AOCReader.lines(clipboard);
    int ans = 0;
    for (String line : lines) {
      if (isNicePart1(line)) {
        ans++;
      }
    }

    return ans;
  }

  private static int part2(String clipboard) {

    List<String> lines = AOCReader.lines(clipboard);
    int ans = 0;
    for (String line : lines) {
      if (isNicePart2(line)) {
        ans++;
      }
    }

    return ans;
  }

  private static boolean isNicePart1(String line) {
    int vowels = 0;
    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);
      if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
        vowels++;
      }
    }

    boolean containsDouble = false;
    for (int i = 0; i < line.length() - 1; i++) {
      char c = line.charAt(i);
      char c2 = line.charAt(i + 1);
      if (c == c2) {
        containsDouble = true;
      }
    }

    return vowels >= 3
        && containsDouble
        && !line.contains("ab")
        && !line.contains("cd")
        && !line.contains("pq")
        && !line.contains("xy");
  }

  private static boolean isNicePart2(String line) {

    boolean rule1 = false;
    boolean rule2 = false;

    for (int i = 0; i < line.length() - 1; i++) {
      String pair = line.substring(i, i + 2);
      if (line.indexOf(pair, i + 2) != -1) {
        rule1 = true;
      }
    }

    for (int i = 0; i < line.length() - 2; i++) {
      char c = line.charAt(i);
      char c2 = line.charAt(i + 2);
      if (c == c2) {
        rule2 = true;
      }
    }

    return rule1 && rule2;
  }
}
