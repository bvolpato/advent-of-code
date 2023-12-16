package bruno.aoc2015;

import bruno.util.AOCReader;
import java.io.IOException;

public class Day01 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2015, 1);
    System.out.println("Clipboard content: " + clipboard);

    int floor = 0;
    for (int i = 0; i < clipboard.length(); i++) {
      if (clipboard.charAt(i) == '(') {
        floor++;
      } else if (clipboard.charAt(i) == ')') {
        floor--;
      }

      if (floor == -1) {
        System.out.println("Basement at: " + (i + 1));
      }
    }

    System.out.println("Floor: " + floor);
  }
}
