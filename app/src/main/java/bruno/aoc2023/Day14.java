package bruno.aoc2023;

import org.brunocvcunha.inutils4j.MyStringUtils;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;


public class Day14 {

    public static void main(String[] args) throws IOException, UnsupportedFlavorException {
        String clipboard = MyStringUtils.getContent(Day14.class.getResourceAsStream("/2023/day14.txt"));
        System.out.println("Clipboard content: " + clipboard);

        List<String> listLines = MyStringUtils.asListLines(clipboard);

        char[][] grid =
                listLines
                        .stream()
                        .map(String::toCharArray)
                        .toList()
                        .toArray(new char[0][]);

        for (int i = 0; i < grid.length; i++) {
            System.out.println(new String(grid[i]));
        }


        for (int cycle = 0; cycle < 1000; cycle++) {
//            System.out.println("\nMutated North\n");
            north(grid);
//            for (int i = 0; i < grid.length; i++) {
//                System.out.println(new String(grid[i]));
//            }

//            System.out.println("\nMutated West\n");
            west(grid);
//            for (int i = 0; i < grid.length; i++) {
//                System.out.println(new String(grid[i]));
//            }

//            System.out.println("\nMutated South\n");
            south(grid);
//            for (int i = 0; i < grid.length; i++) {
//                System.out.println(new String(grid[i]));
//            }

//            System.out.println("\nMutated East\n");
            east(grid);
//            for (int i = 0; i < grid.length; i++) {
//                System.out.println(new String(grid[i]));
//            }



        }


        int ans1 = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 'O') {
                    ans1 += (grid.length - row);
                }
            }
        }

        System.out.println("Ans: " + ans1);
    }

    private static void north(char[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 'O') {
                    int gravityRow = row - 1;
                    while (gravityRow >= 0 && grid[gravityRow][col] == '.') {
                        gravityRow--;
                    }
                    if (row != gravityRow + 1) {
                        grid[gravityRow + 1][col] = 'O';
                        grid[row][col] = '.';
                    }
                }
            }
        }
    }

    private static void south(char[][] grid) {
        for (int row = grid.length - 1; row >= 0; row--) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 'O') {
                    int gravityRow = row + 1;
                    while (gravityRow < grid.length && grid[gravityRow][col] == '.') {
                        gravityRow++;
                    }
                    if (row != gravityRow - 1) {
                        grid[gravityRow - 1][col] = 'O';
                        grid[row][col] = '.';
                    }
                }
            }
        }
    }


    private static void west(char[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 'O') {
                    int gravityCol = col - 1;
                    while (gravityCol >= 0 && grid[row][gravityCol] == '.') {
                        gravityCol--;
                    }
                    if (col != gravityCol + 1) {
                        grid[row][gravityCol + 1] = 'O';
                        grid[row][col] = '.';
                    }
                }
            }
        }
    }

    private static void east(char[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = grid[0].length - 1; col >= 0; col--) {
                if (grid[row][col] == 'O') {
                    int gravityCol = col + 1;
                    while (gravityCol < grid[0].length && grid[row][gravityCol] == '.') {
                        gravityCol++;
                    }
                    if (col != gravityCol - 1) {
                        grid[row][gravityCol - 1] = 'O';
                        grid[row][col] = '.';
                    }
                }
            }
        }
    }

}