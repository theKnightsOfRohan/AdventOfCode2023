package Day3;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class Day3Revised {
    public static void main(String[] args) {
        // List<String> input = readFileByLine("src/Day3/input.txt");
        List<String> input = readFileByLine("src/Day3/test.txt");

        String[][] engine = convertTo2dArray(input);

        List<int[]> partCoords = new ArrayList<int[]>();

        for (int r = 0; r < engine.length; r++) {
            for (int c = 0; c < engine[r].length; c++) {
                if (engine[r][c].equals(".")) {
                    continue;
                }

                try {
                    Integer.parseInt(engine[r][c]);
                } catch (NumberFormatException e) {
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int cc = c + j;
                            if (r + i < 0 || r + i >= engine.length || c + j < 0 || c + j >= engine[r].length) {
                                continue;
                            }

                            try {
                                Integer.parseInt(engine[r + i][c + j]);
                                while (cc > 0) {
                                    try {
                                        Integer.parseInt(engine[r + i][cc - 1]);
                                        cc--;
                                    } catch (NumberFormatException e2) {
                                        break;
                                    }
                                }

                                partCoords.add(new int[] { r + i, cc });
                            } catch (NumberFormatException e2) {
                                continue;
                            }
                        }
                    }
                }

            }
        }

        int sum = 0;
        for (int[] i : partCoords) {
            int r = i[0];
            int c = i[1];

            String num = "";
            while (c < engine[r].length) {
                try {
                    Integer.parseInt(engine[r][c]);
                    num += engine[r][c];
                    c++;
                } catch (NumberFormatException e) {
                    break;
                }
            }

            sum += Integer.parseInt(num);
        }

        System.out.println(sum);
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
