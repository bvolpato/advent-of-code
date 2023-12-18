package bruno.aoc2015;

import bruno.util.AOCReader;
import java.io.IOException;

/**
 * --- Day 11: Corporate Policy ---
 *
 * <p>Santa's previous password expired, and he needs help choosing a new one.
 *
 * <p>To help him remember his new password after the old one expires, Santa has devised a method of
 * coming up with a password based on the previous one. Corporate policy dictates that passwords
 * must be exactly eight lowercase letters (for security reasons), so he finds his new password by
 * incrementing his old password string repeatedly until it is valid.
 *
 * <p>Incrementing is just like counting with numbers: xx, xy, xz, ya, yb, and so on. Increase the
 * rightmost letter one step; if it was z, it wraps around to a, and repeat with the next letter to
 * the left until one doesn't wrap around.
 *
 * <p>Unfortunately for Santa, a new Security-Elf recently started, and he has imposed some
 * additional password requirements:
 *
 * <p>Passwords must include one increasing straight of at least three letters, like abc, bcd, cde,
 * and so on, up to xyz. They cannot skip letters; abd doesn't count. Passwords may not contain the
 * letters i, o, or l, as these letters can be mistaken for other characters and are therefore
 * confusing. Passwords must contain at least two different, non-overlapping pairs of letters, like
 * aa, bb, or zz.
 *
 * <p>For example:
 *
 * <p>hijklmmn meets the first requirement (because it contains the straight hij) but fails the
 * second requirement requirement (because it contains i and l). abbceffg meets the third
 * requirement (because it repeats bb and ff) but fails the first requirement. abbcegjk fails the
 * third requirement, because it only has one double letter (bb). The next password after abcdefgh
 * is abcdffaa. The next password after ghijklmn is ghjaabcc, because you eventually skip all the
 * passwords that start with ghi..., since i is not allowed.
 *
 * <p>Given Santa's current password (your puzzle input), what should his next password be?
 *
 * <p>Your puzzle answer was hxbxxyzz. --- Part Two ---
 *
 * <p>Santa's password expired again. What's the next one?
 *
 * <p>Your puzzle answer was hxcaabcc.
 */
public class Day11 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2015, 11);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part1: " + part1(clipboard));
    System.out.println("Part2: " + part1(part1(clipboard)));
  }

  private static String part1(String password) {

    do {
      password = next(password);
    } while (!valid(password));
    return password;
  }

  private static String next(String password) {
    char[] chars = password.toCharArray();
    int i = chars.length - 1;
    while (i >= 0) {
      chars[i]++;
      if (chars[i] > 'z') {
        chars[i] = 'a';
        i--;
      } else {
        break;
      }
    }
    return new String(chars);
  }

  private static boolean valid(String password) {
    return adjacentRule(password) && !containsInvalid(password) && pairsRule(password);
  }

  private static boolean adjacentRule(String password) {
    for (int i = 0; i < password.length() - 2; i++) {
      if (password.charAt(i + 1) == password.charAt(i) + 1
          && password.charAt(i + 2) == password.charAt(i) + 2) {
        return true;
      }
    }
    return false;
  }

  private static boolean containsInvalid(String password) {
    return password.contains("i") || password.contains("o") || password.contains("l");
  }

  private static boolean pairsRule(String password) {
    int pairCount = 0;
    for (int i = 0; i < password.length() - 1; i++) {
      if (password.charAt(i) == password.charAt(i + 1)) {
        pairCount++;
        i++;
      }
    }
    return pairCount >= 2;
  }
}
