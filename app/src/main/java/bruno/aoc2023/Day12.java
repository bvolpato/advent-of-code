package bruno.aoc2023;

import org.brunocvcunha.inutils4j.MyStringUtils;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day12 {

    public static void main(String[] args) throws IOException, UnsupportedFlavorException {
        String clipboard = MyStringUtils.getFromClipboard();
        System.out.println("Clipboard content: " + clipboard);

        List<String> contentLines = MyStringUtils.asListLines(clipboard);

        char[][] gridPt1 =
                contentLines.stream()
                        .map(s -> s.split(" ")[0])
                        .map(String::toCharArray)
                        .toList()
                        .toArray(new char[contentLines.size()][]);

        char[][] grid =
                contentLines.stream()
                        .map(s -> {
                            String content = s.split(" ")[0];
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < 5; i++) {
                                sb.append(content).append("?");
                            }
                            return sb.deleteCharAt(sb.length() - 1).toString().toCharArray();
                        })
                        .toList()
                        .toArray(new char[contentLines.size()][]);

        List<int[]> groupSizes =
                contentLines.stream()
                        .map(s -> {
                            String content = s.split(" ")[1];
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < 5; i++) {
                                sb.append(content).append(",");
                            }
                            return sb.deleteCharAt(sb.length() - 1).toString().split(",");
                        })
                        .map(s -> Arrays.stream(s).mapToInt(Integer::valueOf).toArray())
                        .toList();

        long sumAll = 0;

        for (int i = 0; i < grid.length; i++) {
            long ans = dp(grid[i], 0, 0, groupSizes.get(i), 0, new HashMap<>());
            System.out.println(new String(grid[i]) + " " + Arrays.toString(groupSizes.get(i)) + " == " + ans);
            sumAll += ans;
        }

        System.out.println(sumAll);


    }

    public static long dp(char[] line, int index, int currGroup, int[] groups, int groupIndex, Map<Long, Long> memo) {
        // Lazy way of doing hash code, using ~large primes
        long key = (3761L * index) + (-5417L * currGroup) + (7459L * groupIndex);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (index >= line.length) {
            if (currGroup == 0 && groupIndex >= groups.length) {
                return 1;
            }
            if (groupIndex == groups.length - 1 && currGroup == groups[groupIndex]) {
                return 1;
            }

            return 0;
        }

        int possibleMore = 0;
        for (int k = index; k < line.length; k++) {
            if (line[k] == '#' || line[k] == '?') {
                possibleMore++;
            }
        }
        int remainGroupsSum = 0;
        for (int k = groupIndex; k < groups.length; k++) {
            remainGroupsSum += groups[k];
        }

        if ((currGroup > 0 && (possibleMore + currGroup) < remainGroupsSum) ||
                (currGroup == 0 && possibleMore < remainGroupsSum) ||
                (currGroup > 0 && remainGroupsSum == 0)) {
            return 0;
        }

        long ans = 0;

        char c = line[index];

        if (currGroup > 0) {
            if (c == '.' && currGroup != groups[groupIndex]) {
                return 0;
            }
            if (c == '.') {
                ans += dp(line, index + 1, 0, groups, groupIndex + 1, memo);
            }
            if (c == '?' && currGroup == groups[groupIndex]) {
                ans += dp(line, index + 1, 0, groups, groupIndex + 1, memo);
            }
            if (c == '#' || c == '?') {
                ans += dp(line, index + 1, currGroup + 1, groups, groupIndex, memo);
            }
        } else {
            if (c == '?' || c == '#') {
                ans += dp(line, index + 1, 1, groups, groupIndex, memo);
            }
            if (c == '?' || c == '.') {
                ans += dp(line, index + 1, 0, groups, groupIndex, memo);
            }
        }

        memo.put(key, ans);
        return ans;
    }
}
