package bruno.aoc2023;

import org.brunocvcunha.inutils4j.MyStringUtils;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Day03 {
    static int[][] moves = new int[][]{
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1}
    };

    static class Coordinate {
        int r;
        int c;

        Coordinate(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return r == that.r && c == that.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    static class Num {
        int val;
        int row;
        int colStart;
        int colEnd;


    }

    public static void main(String[] args) throws IOException, UnsupportedFlavorException {
        String clipboard = MyStringUtils.getFromClipboard();
        System.out.println("Clipboard content: " + clipboard);

        List<String> listLines = MyStringUtils.asListLines(clipboard);

        char[][] grid = new char[listLines.size()][listLines.get(0).length()];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = listLines.get(i).toCharArray();
        }

        System.out.println(Arrays.deepToString(grid));

        Map<Coordinate, Num> discovered = new HashMap<>();

        List<Num> nums = new ArrayList<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {

                Num num = new Num();
                num.colStart = c;
                num.row = r;

                while (c < grid[r].length && Character.isDigit(grid[r][c])) {
                    discovered.put(new Coordinate(r, c), num);
                    num.val = (num.val * 10) + Character.getNumericValue(grid[r][c]);
                    c++;

                }

                num.colEnd = c;

                if (num.val > 0) {
                    nums.add(num);
                }
            }
        }


        int sum = 0;

        for (Num num : nums) {
            boolean good = false;

            for (int c = num.colStart; c < num.colEnd; c++) {
                if (findSymbolNeighbor(num.row, c, grid)) {
                    good = true;
                    break;
                }
            }


            System.out.println((good ? "**" : "--") + " " + num.val + " - " + num.row + " --> " + num.colStart + ", " + num.colEnd);
            if (good) {
                sum += num.val;
            }

        }


        int gears = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                Set<Num> adjacent = new HashSet<>();
                if (grid[r][c] == '*') {

                    for (int[] move : moves) {
                        int newrow = r + move[0];
                        int newcol = c + move[1];

                        Num adj = discovered.get(new Coordinate(newrow, newcol));
                        if (adj != null) {
                            adjacent.add(adj);
                        }
                    }

                    System.out.println("Search at (" + r + "," + c + ") returned " + adjacent.size() + " adjacent.");
                    if (adjacent.size() == 2) {
                        Num[] adjacentArray = adjacent.toArray(new Num[2]);
                        System.out.println("Gear: " + adjacentArray[0].val + ", " + adjacentArray[1].val);
                        gears += (adjacentArray[0].val * adjacentArray[1].val);
                    }
                }


            }
        }
        System.out.println(sum);
        System.out.println(gears);
    }

    private static boolean findSymbolNeighbor(int row, int col, char[][] grid) {

        for (int[] move : moves) {
            int newrow = row + move[0];
            int newcol = col + move[1];

            if (newrow < 0 || newcol < 0 || newrow >= grid.length || newcol >= grid[0].length) {
                continue;
            }

            if (grid[newrow][newcol] == '$'
                    || grid[newrow][newcol] == '*'
                    || grid[newrow][newcol] == '#'
                    || grid[newrow][newcol] == '+'
                    || grid[newrow][newcol] == '@'
                    || grid[newrow][newcol] == '%'
                    || grid[newrow][newcol] == '&'
                    || grid[newrow][newcol] == '/'
                    || grid[newrow][newcol] == '-'
                    || grid[newrow][newcol] == '=') {
                return true;
            }
        }

        return false;
    }


}
