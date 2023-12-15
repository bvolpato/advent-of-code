package bruno.aoc2023;

import org.brunocvcunha.inutils4j.MyStringUtils;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day08 {

    public static void main(String[] args) throws IOException, UnsupportedFlavorException {
        String clipboard = MyStringUtils.getContent(Day08.class.getResourceAsStream("/day08.txt"));
        System.out.println("Clipboard content: " + clipboard);


        List<String> contentLines = MyStringUtils.asListLines(clipboard);

        String instructions = contentLines.get(0);

        Map<String, Direction> map = new HashMap<>();

        for (int i = 1; i < contentLines.size(); i++) {
            if (contentLines.get(i).trim().isEmpty()) continue;

            String mapLine = contentLines.get(i).replace("(", "").replace(")", "");

            String from = mapLine.split("=")[0].trim();

            String to = mapLine.split("=")[1].trim();
            String toLeft = to.split(",")[0].trim();
            String toRight = to.split(",")[1].trim();

            map.put(from, new Direction(toLeft, toRight));
        }

        System.out.println("Map: " + map);


        List<String> current = new ArrayList<>();
        for (String pos : map.keySet()) {
            if (pos.endsWith("A")) {
                current.add(pos);
            }
        }

        int steps = 0;

        int[] cycles = new int[current.size()];

        while (Arrays.stream(cycles).anyMatch(i -> i == 0)) {
            //System.out.println(current);

            char now = instructions.charAt(steps % instructions.length());

            List<String> next = new ArrayList<>();
            for (int i = 0; i < current.size(); i++) {
                String curr = current.get(i);
                String n;
                if (now == 'L') {
                    n = map.get(curr).left;
                } else {
                    n = map.get(curr).right;
                }

                if (n.endsWith("Z")) {
                    cycles[i] = steps + 1;
                }
                next.add(n);
            }

            steps++;

            current = next;

        }
        System.out.println(current);

        System.out.println("Steps: " + steps);
        System.out.println("Cycles: " + Arrays.toString(cycles));
        System.out.println("LCM: " + lcmPartition(cycles, 0, cycles.length));

    }


    static class Direction {
        String left;
        String right;

        public Direction(String left, String right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Direction{" +
                    "left='" + left + '\'' +
                    ", right='" + right + '\'' +
                    '}';
        }
    }

    public static long lcmPartition(int[] arr, int start, int end) {
        if (end - start == 1) {
            return lcm(arr[start], arr[end - 1]);
        }
        return lcm(arr[start], lcmPartition(arr, start + 1, end));
    }

    public static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    public static long gcd(long a, long b) {
        if (a < b) {
            return gcd(b, a);
        }
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }
}
