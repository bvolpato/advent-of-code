package bruno.aoc2015;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.brunocvcunha.inutils4j.MyStringUtils;

public class Day01 {

    public static void main(String[] args) throws IOException, UnsupportedFlavorException {
        String clipboard = MyStringUtils.getContent(Day01.class.getResourceAsStream("/2015/day01.txt"));
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
