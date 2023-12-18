package bruno.aoc2015;

import bruno.util.AOCReader;
import java.io.IOException;
import java.util.List;
import org.brunocvcunha.inutils4j.MyStringUtils;

/**
 * --- Day 2: I Was Told There Would Be No Math ---
 *
 * <p>The elves are running low on wrapping paper, and so they need to submit an order for more.
 * They have a list of the dimensions (length l, width w, and height h) of each present, and only
 * want to order exactly as much as they need.
 *
 * <p>Fortunately, every present is a box (a perfect right rectangular prism), which makes
 * calculating the required wrapping paper for each gift a little easier: find the surface area of
 * the box, which is 2*l*w + 2*w*h + 2*h*l. The elves also need a little extra paper for each
 * present: the area of the smallest side.
 *
 * <p>For example:
 *
 * <p>A present with dimensions 2x3x4 requires 2*6 + 2*12 + 2*8 = 52 square feet of wrapping paper
 * plus 6 square feet of slack, for a total of 58 square feet. A present with dimensions 1x1x10
 * requires 2*1 + 2*10 + 2*10 = 42 square feet of wrapping paper plus 1 square foot of slack, for a
 * total of 43 square feet.
 *
 * <p>All numbers in the elves' list are in feet. How many total square feet of wrapping paper
 * should they order?
 *
 * <p>Your puzzle answer was 1588178. --- Part Two ---
 *
 * <p>The elves are also running low on ribbon. Ribbon is all the same width, so they only have to
 * worry about the length they need to order, which they would again like to be exact.
 *
 * <p>The ribbon required to wrap a present is the shortest distance around its sides, or the
 * smallest perimeter of any one face. Each present also requires a bow made out of ribbon as well;
 * the feet of ribbon required for the perfect bow is equal to the cubic feet of volume of the
 * present. Don't ask how they tie the bow, though; they'll never tell.
 *
 * <p>For example:
 *
 * <p>A present with dimensions 2x3x4 requires 2+2+3+3 = 10 feet of ribbon to wrap the present plus
 * 2*3*4 = 24 feet of ribbon for the bow, for a total of 34 feet. A present with dimensions 1x1x10
 * requires 1+1+1+1 = 4 feet of ribbon to wrap the present plus 1*1*10 = 10 feet of ribbon for the
 * bow, for a total of 14 feet.
 *
 * <p>How many total feet of ribbon should they order?
 *
 * <p>Your puzzle answer was 3783758.
 */
public class Day02 {

  public static void main(String[] args) throws IOException {
    String clipboard = AOCReader.readDay(2015, 2);
    System.out.println("Clipboard content: " + clipboard);

    List<String> lines = MyStringUtils.asListLines(clipboard);

    int total = 0;
    int totalRibbon = 0;

    for (String gift : lines) {
      String[] dimentions = gift.split("x");
      int l = Integer.parseInt(dimentions[0]);
      int w = Integer.parseInt(dimentions[1]);
      int h = Integer.parseInt(dimentions[2]);

      total += 2 * l * w + 2 * w * h + 2 * h * l;

      int min = Math.min(l * w, Math.min(w * h, h * l));
      total += min;

      int ribbon = 2 * Math.min(l + w, Math.min(w + h, h + l));
      totalRibbon += ribbon + l * w * h;
    }

    System.out.println("Total: " + total);
    System.out.println("Total Ribbon: " + totalRibbon);
  }
}
