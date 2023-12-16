package bruno.aoc2023;

import org.brunocvcunha.inutils4j.MyStringUtils;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

public class Day01 {

    public static void main(String[] args) throws IOException, UnsupportedFlavorException {
        String clipboard = MyStringUtils.getContent(Day01.class.getResourceAsStream("/2023/day01.txt"));
        System.out.println("Clipboard content: " + clipboard);

        // Part 2
        String[] spelledOut =
                new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        int sum = 0;

        List<String> listLines = MyStringUtils.asListLines(clipboard);

        for (String line : listLines) {

            System.out.println("Line: " + line);
            while (true) {

                String spell = "";
                String digit = "";
                int index = Integer.MAX_VALUE;

                for (int i = 0; i < spelledOut.length; i++) {
                    int indexOf = line.indexOf(spelledOut[i]);
                    if (indexOf != -1 && indexOf < index) {
                        spell = spelledOut[i];
                        digit = String.valueOf(i + 1);
                        index = indexOf;
                    }
                }

                if (index == Integer.MAX_VALUE) break;
                line = line.replaceFirst(spell, digit);
            }

            System.out.println("Modified line: " + line);

            int firstDigit = -1;
            int lastDigit = 0;

            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) {

                    lastDigit = Character.getNumericValue(line.charAt(i));

                    if (firstDigit == -1) {
                        firstDigit = lastDigit;
                    }
                }
            }

            int partialSum = (firstDigit * 10) + lastDigit;
            System.out.println(partialSum);
            sum += partialSum;
        }

        System.out.println(sum);
    }
}
