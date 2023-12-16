package bruno.aoc2015;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import org.brunocvcunha.inutils4j.MyStringUtils;

public class Day02 {

    public static void main(String[] args) throws IOException, UnsupportedFlavorException {
        String clipboard = MyStringUtils.getContent(Day02.class.getResourceAsStream("/2015/day02.txt"));
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
