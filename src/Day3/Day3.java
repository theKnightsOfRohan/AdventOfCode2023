package Day3;

import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class Day3 {
    /*
     * Part 1.
     * 
     * The engine schematic (your puzzle input) consists of a visual representation
     * of the engine. There are lots of numbers and symbols you don't really
     * understand, but apparently any number adjacent to a symbol, even diagonally,
     * is a "part number" and should be included in your sum. (Periods (.) do not
     * count as a symbol.)
     * 
     * Here is an example engine schematic:
     * 
     * 467..114..
     * ...*......
     * ..35..633.
     * ......#...
     * 617*......
     * .....+.58.
     * ..592.....
     * ......755.
     * ...$.*....
     * .664.598..
     *
     * In this schematic, two numbers are not part numbers because they are not
     * adjacent to a symbol: 114 (top right) and 58 (middle right). Every other
     * number is adjacent to a symbol and so is a part number; their sum is 4361.
     * 
     * Of course, the actual engine schematic is much larger. What is the sum of all
     * of the part numbers in the engine schematic?
     */
    public static void main(String[] args) {
        List<String> games = readFileByLine("src/Day3/input.txt");
        // List<String> games = readFileByLine("src/Day3/test.txt");
        String[][] engine = convertTo2dArray(games);

        // printAsGrid(engine);

        List<Integer> partNumbers = getPartNumbers(engine);
        System.out.println(partNumbers);

        int sum = 0;
        for (int i : partNumbers) {
            sum += i;
        }

        System.out.println("Part 1: " + sum);
    }

    private static List<Integer> getPartNumbers(String[][] engine) {
        List<Integer> partNumbers = new ArrayList<Integer>();
        for (int i = 0; i < engine.length; i++) {
            for (int j = 0; j < engine[i].length; j++) {
                try {
                    Integer.parseInt(engine[i][j]);
                } catch (NumberFormatException e) {
                    if (!engine[i][j].equals(".")) {
                        addAdjacentNumbers(engine, i, j, partNumbers);
                    }
                }
            }
        }

        return partNumbers;
    }

    private static void addAdjacentNumbers(String[][] engine, int x, int y, List<Integer> partNumbers) {
        boolean isAdjacent = false;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0)
                    continue;

                try {
                    Integer.parseInt(engine[x + i][y + j]);
                    if (!isAdjacent) {
                        int num = getFullNumber(engine, x + i, y + j);
                        partNumbers.add(num);
                        isAdjacent = true;
                    }
                } catch (NumberFormatException e) {
                    isAdjacent = false;
                    continue;
                }
            }

            isAdjacent = false;
        }
    }

    private static int getFullNumber(String[][] engine, int y, int x) {
        String[] line = engine[y];
        String num = engine[y][x];
        int minBound = 0;
        int maxBound = line.length - 1;

        for (int i = 1; i < line.length; i++) {
            if (!(x - i < minBound)) {
                try {
                    Integer.parseInt(engine[y][x - i]);
                    num = engine[y][x - i] + num;
                } catch (NumberFormatException e) {
                    minBound = x - i;
                }
            }

            if (!(x + i > maxBound)) {
                try {
                    Integer.parseInt(engine[y][x + i]);
                    num = num + engine[y][x + i];
                } catch (NumberFormatException e) {
                    maxBound = x + i;
                }
            }
        }

        return Integer.parseInt(num);
    }

    private static HashSet<String> getPossibleSymbols(String[][] engine) {
        HashSet<String> possibleSymbols = new HashSet<String>();

        for (int i = 0; i < engine.length; i++) {
            for (int j = 0; j < engine[i].length; j++) {
                try {
                    Integer.parseInt(engine[i][j]);
                } catch (NumberFormatException e) {
                    if (!engine[i][j].equals("."))
                        possibleSymbols.add(engine[i][j]);
                }
            }
        }

        return possibleSymbols;
    }

    private static void printAsGrid(String[][] engine) {
        for (int i = 0; i < engine.length; i++) {
            for (int j = 0; j < engine[i].length; j++) {
                System.out.print(engine[i][j] + " ");
            }

            System.out.println();
        }
    }

    private static String[][] convertTo2dArray(List<String> games) {
        String[][] engine = new String[games.size()][games.get(0).length()];

        for (int i = 0; i < games.size(); i++) {
            engine[i] = games.get(i).split("");
        }

        return engine;
    }

    public static List<String> readFileByLine(String string) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(string))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
