package bruno.aoc2023;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.*;

import org.brunocvcunha.inutils4j.MyStringUtils;

public class Day04 {

  public static void main(String[] args) throws IOException, UnsupportedFlavorException {
    String clipboard = MyStringUtils.getContent(Day04.class.getResourceAsStream("/2023/day04.txt"));
    System.out.println("Clipboard content: " + clipboard);

    List<String> cards = MyStringUtils.asListLines(clipboard);

    int sum = 0;

    int[] credits = new int[cards.size()];
    Arrays.fill(credits, 1);

    for (int i = 0; i < credits.length; i++) {
      String card = cards.get(i);
      int multiplier = credits[i];

      Set<String> winning =
          new HashSet<>(
              Arrays.asList(card.split(":")[1].trim().split("\\|")[0].trim().split("\\s+")));
      Set<String> my =
          new HashSet<>(
              Arrays.asList(card.split(":")[1].trim().split("\\|")[1].trim().split("\\s+")));

      System.out.println("Winning: " + winning);
      System.out.println("My: " + my);

      my.retainAll(winning);

      System.out.println("Retained: " + my);

      int pts = my.size();
      if (my.size() > 1) {
        pts = (int) Math.pow(2, my.size() - 1);
      }

      System.out.println("Points: " + pts);
      sum += pts;

      for (int j = 0; j < my.size(); j++) {
        credits[i + j + 1] += multiplier;
      }
    }

    System.out.println(sum);

    System.out.println(Arrays.toString(credits));
    System.out.println(Arrays.stream(credits).sum());
  }
}
