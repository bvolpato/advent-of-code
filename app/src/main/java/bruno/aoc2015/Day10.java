package bruno.aoc2015;

import bruno.util.AOCReader;
import java.io.IOException;

/**
 * --- Day 10: Elves Look, Elves Say ---
 *
 * <p>Today, the Elves are playing a game called look-and-say. They take turns making sequences by
 * reading aloud the previous sequence and using that reading as the next sequence. For example, 211
 * is read as "one two, two ones", which becomes 1221 (1 2, 2 1s).
 *
 * <p>Look-and-say sequences are generated iteratively, using the previous value as input for the
 * next step. For each step, take the previous value, and replace each run of digits (like 111) with
 * the number of digits (3) followed by the digit itself (1).
 *
 * <p>For example:
 *
 * <p>1 becomes 11 (1 copy of digit 1). 11 becomes 21 (2 copies of digit 1). 21 becomes 1211 (one 2
 * followed by one 1). 1211 becomes 111221 (one 1, one 2, and two 1s). 111221 becomes 312211 (three
 * 1s, two 2s, and one 1).
 *
 * <p>Starting with the digits in your puzzle input, apply this process 40 times. What is the length
 * of the result?
 *
 * <p>Your puzzle answer was 329356. --- Part Two ---
 *
 * <p>Neat, right? You might also enjoy hearing John Conway talking about this sequence (that's
 * Conway of Conway's Game of Life fame).
 *
 * <p>Now, starting again with the digits in your puzzle input, apply this process 50 times. What is
 * the length of the new result?
 *
 * <p>Your puzzle answer was 4666278.
 */
public class Day10 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2015, 10);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part1: " + part1(clipboard));
    System.out.println("Part2: " + part2(clipboard));
  }

  private static int part1(String clipboard) {
    return solve(clipboard, 40);
  }

  private static int part2(String clipboard) {
    return solve(clipboard, 50);
  }

  private static int solve(String clipboard, int repeats) {

    for (int i = 0; i < repeats; i++) {
      StringBuilder sb = new StringBuilder();
      int count = 1;
      char prev = clipboard.charAt(0);
      for (int j = 1; j < clipboard.length(); j++) {
        char curr = clipboard.charAt(j);
        if (curr == prev) {
          count++;
        } else {
          sb.append(count).append(prev);
          count = 1;
          prev = curr;
        }
      }
      sb.append(count).append(prev);
      clipboard = sb.toString();
    }

    return clipboard.length();
  }
}
