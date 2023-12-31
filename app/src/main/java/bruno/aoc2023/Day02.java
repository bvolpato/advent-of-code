package bruno.aoc2023;

import static bruno.util.AOCReader.readDay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.brunocvcunha.inutils4j.MyStringUtils;

public class Day02 {

  private static Map<String, Integer> available = new HashMap<>();

  static {
    available.put("red", 12);
    available.put("green", 13);
    available.put("blue", 14);
  }

  public static void main(String[] args) throws IOException {
    String clipboard = readDay(2023, 2);
    System.out.println("Clipboard content: " + clipboard);

    int ans1 = 0;
    int ans2 = 0;

    for (String line : MyStringUtils.asListLines(clipboard)) {
      int gameNum = Integer.valueOf(line.split(":")[0].split(" ")[1]);
      String lineContent = line.split(":")[1].trim();

      boolean good = true;

      Map<String, Integer> maxPerColorTurns = new HashMap<>();

      for (String turn : lineContent.split(";")) {
        for (String count : turn.split(",")) {
          int quantity = Integer.valueOf(count.trim().split(" ")[0]);
          String color = count.trim().split(" ")[1];

          maxPerColorTurns.put(
              color, Integer.max(maxPerColorTurns.getOrDefault(color, 0), quantity));

          if (quantity > available.get(color)) {
            good = false;
          }
        }
      }

      if (good) {
        ans1 += gameNum;
      }

      int asInt = maxPerColorTurns.values().stream().reduce(1, (a, b) -> a * b);
      System.out.println(maxPerColorTurns + " -> " + asInt);
      ans2 += asInt;
    }

    System.out.println(ans1);
    System.out.println(ans2);
  }
}
