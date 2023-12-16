package bruno.aoc2015;

import bruno.util.AOCReader;
import java.io.IOException;
import org.brunocvcunha.inutils4j.MyStringUtils;

public class Day04 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2015, 4);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part1: " + part1(clipboard));
    System.out.println("Part2: " + part2(clipboard));
  }

  private static String part1(String clipboard) {

    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      String hash = MyStringUtils.md5hex(clipboard + i);

      if (hash.startsWith("00000")) {
        return String.valueOf(i);
      }
    }

    return "";
  }

  private static String part2(String clipboard) {
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      String hash = MyStringUtils.md5hex(clipboard + i);

      if (hash.startsWith("000000")) {
        return String.valueOf(i);
      }
    }

    return "";
  }
}
