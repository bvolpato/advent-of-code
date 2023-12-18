package bruno.aoc2015;

import bruno.util.AOCReader;
import java.io.IOException;
import java.util.List;

/**
 * --- Day 5: Doesn't He Have Intern-Elves For This? ---
 *
 * <p>Santa needs help figuring out which strings in his text file are naughty or nice.
 *
 * <p>A nice string is one with all of the following properties:
 *
 * <p>It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou. It
 * contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa,
 * bb, cc, or dd). It does not contain the strings ab, cd, pq, or xy, even if they are part of one
 * of the other requirements.
 *
 * <p>For example:
 *
 * <p>ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter
 * (...dd...), and none of the disallowed substrings. aaa is nice because it has at least three
 * vowels and a double letter, even though the letters used by different rules overlap.
 * jchzalrnumimnmhp is naughty because it has no double letter. haegwjzuvuyypxyu is naughty because
 * it contains the string xy. dvszwmarrgswjxmb is naughty because it contains only one vowel.
 *
 * <p>How many strings are nice?
 *
 * <p>Your puzzle answer was 236. --- Part Two ---
 *
 * <p>Realizing the error of his ways, Santa has switched to a better model of determining whether a
 * string is naughty or nice. None of the old rules apply, as they are all clearly ridiculous.
 *
 * <p>Now, a nice string is one with all of the following properties:
 *
 * <p>It contains a pair of any two letters that appears at least twice in the string without
 * overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps). It
 * contains at least one letter which repeats with exactly one letter between them, like xyx,
 * abcdefeghi (efe), or even aaa.
 *
 * <p>For example:
 *
 * <p>qjhvhtzxzqqjkmpb is nice because is has a pair that appears twice (qj) and a letter that
 * repeats with exactly one letter between them (zxz). xxyxx is nice because it has a pair that
 * appears twice and a letter that repeats with one between, even though the letters used by each
 * rule overlap. uurcxstgmygtbstg is naughty because it has a pair (tg) but no repeat with a single
 * letter between them. ieodomkazucvgmuy is naughty because it has a repeating letter with one
 * between (odo), but no pair that appears twice.
 *
 * <p>How many strings are nice under these new rules?
 *
 * <p>Your puzzle answer was 51.
 */
public class Day05 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2015, 5);
    System.out.println("Clipboard content: " + clipboard);

    System.out.println("Part1: " + part1(clipboard));
    System.out.println("Part2: " + part2(clipboard));
  }

  private static int part1(String clipboard) {

    List<String> lines = AOCReader.lines(clipboard);
    int ans = 0;
    for (String line : lines) {
      if (isNicePart1(line)) {
        ans++;
      }
    }

    return ans;
  }

  private static int part2(String clipboard) {

    List<String> lines = AOCReader.lines(clipboard);
    int ans = 0;
    for (String line : lines) {
      if (isNicePart2(line)) {
        ans++;
      }
    }

    return ans;
  }

  private static boolean isNicePart1(String line) {
    int vowels = 0;
    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);
      if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
        vowels++;
      }
    }

    boolean containsDouble = false;
    for (int i = 0; i < line.length() - 1; i++) {
      char c = line.charAt(i);
      char c2 = line.charAt(i + 1);
      if (c == c2) {
        containsDouble = true;
      }
    }

    return vowels >= 3
        && containsDouble
        && !line.contains("ab")
        && !line.contains("cd")
        && !line.contains("pq")
        && !line.contains("xy");
  }

  private static boolean isNicePart2(String line) {

    boolean rule1 = false;
    boolean rule2 = false;

    for (int i = 0; i < line.length() - 1; i++) {
      String pair = line.substring(i, i + 2);
      if (line.indexOf(pair, i + 2) != -1) {
        rule1 = true;
      }
    }

    for (int i = 0; i < line.length() - 2; i++) {
      char c = line.charAt(i);
      char c2 = line.charAt(i + 2);
      if (c == c2) {
        rule2 = true;
      }
    }

    return rule1 && rule2;
  }
}
