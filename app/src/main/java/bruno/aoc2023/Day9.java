package bruno.aoc2023;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.brunocvcunha.inutils4j.MyStringUtils;

public class Day9 {

  public static void main(String[] args) throws IOException, UnsupportedFlavorException {
    String clipboard = MyStringUtils.getFromClipboard();
    System.out.println("Clipboard content: " + clipboard);

    List<String> contentLines = MyStringUtils.asListLines(clipboard);

    long ans = 0;

    for (String line : contentLines) {

      LinkedList<Integer> list =
          new LinkedList<>(
              Arrays.stream(line.split(" ")).map(Integer::valueOf).collect(Collectors.toList()));

      System.out.println(list);
      Stack<LinkedList<Integer>> levels = new Stack<>();
      levels.add(list);

      while (list.stream().anyMatch(i -> i != 0)) {

        LinkedList<Integer> next = new LinkedList<>();
        for (int i = 1; i < list.size(); i++) {
          next.add(list.get(i) - list.get(i - 1));
        }

        list = next;
        levels.add(list);
        System.out.println(list);
      }

      int need = 0;
      levels.pop();
      while (!levels.isEmpty()) {
        LinkedList<Integer> level = levels.pop();

        // int nextNum = level.get(level.size() - 1) + need;
        // part 2
        int nextNum = level.get(0) - need;

        // level.addLast(nextNum);
        // part 2
        level.addFirst(nextNum);

        System.out.println(level);
        need = nextNum;
      }

      System.out.println(need);
      ans += need;
      System.out.println();
    }

    System.out.println(ans);
  }
}