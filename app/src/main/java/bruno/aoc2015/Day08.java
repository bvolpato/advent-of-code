package bruno.aoc2015;

import bruno.util.AOCReader;
import java.io.IOException;
import java.util.List;

public class Day08 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2015, 8);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part1: " + part1(clipboard));
    System.out.println("Part2: " + part2(clipboard));
  }

  private static int part1(String clipboard) {

    List<String> lines = AOCReader.lines(clipboard);

    int numStrings = 0;
    int totalMemory = 0;
    for (String line : lines) {
      numStrings += line.length();
      totalMemory +=
          line.substring(1, line.length() - 1)
              .replaceAll("\\\\x[a-fA-F0-9]{2}", "X")
              .replace("\\\"", "\"")
              .replace("\\\\", "\\")
              .length();
    }

    System.out.println("Strings: " + numStrings);
    System.out.println("Memory: " + totalMemory);

    return numStrings - totalMemory;
  }

  private static int part2(String clipboard) {

    List<String> lines = AOCReader.lines(clipboard);

    int numStrings = 0;
    int totalMemory = 0;
    for (String line : lines) {
      numStrings += line.length();
      totalMemory += line.replace("\\", "\\\\").replaceAll("\"", "\\\\\"").length() + 2;
    }

    System.out.println("Strings: " + numStrings);
    System.out.println("Memory: " + totalMemory);

    return totalMemory - numStrings;
  }
}
