package bruno.aoc2015;

import bruno.util.AOCReader;
import java.io.IOException;
import org.brunocvcunha.inutils4j.MyStringUtils;

/**
 * --- Day 4: The Ideal Stocking Stuffer ---
 *
 * <p>Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all
 * the economically forward-thinking little girls and boys.
 *
 * <p>To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five
 * zeroes. The input to the MD5 hash is some secret key (your puzzle input, given below) followed by
 * a number in decimal. To mine AdventCoins, you must find Santa the lowest positive number (no
 * leading zeroes: 1, 2, 3, ...) that produces such a hash.
 *
 * <p>For example:
 *
 * <p>If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043
 * starts with five zeroes (000001dbbfa...), and it is the lowest such number to do so. If your
 * secret key is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five
 * zeroes is 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....
 *
 * <p>Your puzzle answer was 254575. --- Part Two ---
 *
 * <p>Now find one that starts with six zeroes.
 *
 * <p>Your puzzle answer was 1038736.
 */
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
