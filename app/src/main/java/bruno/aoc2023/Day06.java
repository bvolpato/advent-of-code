package bruno.aoc2023;

import org.brunocvcunha.inutils4j.MyStringUtils;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day06 {

    public static void main(String[] args) throws IOException, UnsupportedFlavorException {
        String clipboard = MyStringUtils.getContent(Day06.class.getResourceAsStream("/day06.txt"));
        System.out.println("Clipboard content: " + clipboard);

        List<String> listLines = MyStringUtils.asListLines(clipboard);

        String num1 = listLines.get(0).split(":")[1].trim();
        String num2 = listLines.get(1).split(":")[1].trim();

        // part 2
        num1 = num1.replace(" ", "");
        num2 = num2.replace(" ", "");

        long[] time = Arrays.stream(num1.split("\\s+")).mapToLong(Long::valueOf).toArray();
        long[] distance = Arrays.stream(num2.split("\\s+")).mapToLong(Long::valueOf).toArray();

        System.out.println(Arrays.toString(time));
        System.out.println(Arrays.toString(distance));

        int ans = 1;
        for (int i = 0; i < time.length; i++) {

            int ways = 0;

            for (int speed = 0; speed < time[i]; speed++) {
                long timeLeft = time[i] - speed;
                long totalDist = speed * timeLeft;
                if (totalDist > distance[i]) {
                    ways++;
                }

                // System.out.println("Speed is " + speed + ", time left is " + timeLeft + " so total dist was " + totalDist);
            }

            System.out.println("time: " + time[i] + ", dist: " + distance[i] + " -> " + ways + " ways.");
            ans *= ways;
        }


        System.out.println(ans);
    }


}
