package bruno.aoc2023;

import java.io.IOException;
import java.util.*;

import org.brunocvcunha.inutils4j.MyStringUtils;

public class Day15 {

  public static void main(String[] args) throws IOException {
    String clipboard = MyStringUtils.getContent(Day15.class.getResourceAsStream("/2023/day15.txt"));
    System.out.println("Clipboard content: " + clipboard);

    String[] tokens = clipboard.trim().split(",");

    int ans = 0;

    Map<Integer, Map<String, Integer>> boxes = new HashMap<>();

    for (int i = 0; i < tokens.length; i++) {
      String token = tokens[i].trim();
      int currValue = 0;
      int currValuePt2 = 0;

      String label = token.split("[-=]")[0];

      boolean seenSymbol = false;
      // part 1 is in token not label
      for (char c : token.toCharArray()) {

        if (c == '-' || c == '=') {
          seenSymbol = true;
        }
        System.out.println(c);
        currValue += c;
        System.out.println("\t" + currValue);
        currValue *= 17;
        System.out.println("\t" + currValue);
        currValue %= 256;
        System.out.println("\t" + currValue);

        if (!seenSymbol) {
          currValuePt2 = currValue;
        }
      }

      ans += currValue;

      Map<String, Integer> box = boxes.get(currValuePt2);
      if (box == null) {
        box = new LinkedHashMap<>();
        boxes.put(currValuePt2, box);
      }

      if (token.endsWith("-")) {
        System.out.println("Removing " + label);
        box.remove(label);
      } else {
        int focal = Integer.valueOf(token.split("[-=]")[1]);
        System.out.println("Adding " + label + ", " + focal);
        box.put(label, focal);
      }

      System.out.println(boxes);
    }

    int ans2 = 0;

    for (Map.Entry<Integer, Map<String, Integer>> entry : boxes.entrySet()) {
      System.out.println(entry.getKey() + " - " + entry.getValue());

      Map<String, Integer> boxAvail = entry.getValue();

      int counter = 1;
      for (Map.Entry<String, Integer> entry2 : boxAvail.entrySet()) {
        System.out.println("\t" + entry2.getKey() + " - " + entry2.getValue());
        ans2 += ((entry.getKey() + 1) * (counter++) * entry2.getValue());
      }
    }

    System.out.println("Pt1: " + ans);

    System.out.println("Pt2: " + ans2);
  }
}
