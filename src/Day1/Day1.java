package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day1 {
	/*
	 * Part 1.
	 * 
	 * The newly-improved calibration document consists of lines of text; each line
	 * originally contained a specific calibration value that the Elves now need to
	 * recover. On each line, the calibration value can be found by combining the
	 * first digit and the last digit (in that order) to form a single two-digit
	 * number.
	 */

	/*
	 * Part 2.
	 * 
	 * Your calculation isn't quite right. It looks like some of the digits are
	 * actually spelled out with letters: one, two, three, four, five, six, seven,
	 * eight, and nine also count as valid "digits".
	 * 
	 * Equipped with this new information, you now need to find the real first and
	 * last digit on each line. For example:
	 * 
	 * two1nine
	 * eightwothree
	 * abcone2threexyz
	 * xtwone3four
	 * 4nineeightseven2
	 * zoneight234
	 * 7pqrstsixteen
	 * In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76.
	 * Adding these together produces 281.
	 */
	public static void main(String[] args) {
		List<String> inputLines = Utils.Files.readFileByLine("src/Day1/input.txt");
		// List<String> inputLines = Utils.Files.readFileByLine("src/Day1/test.txt");

		List<Integer> numbersInLines = getNumbersInLines(inputLines);

		int sum = 0;
		for (int number : numbersInLines) {
			sum += number;
		}

		System.out.println("Sum: " + sum);
	}

	private static List<Integer> getNumbersInLines(List<String> inputLines) {
		List<Integer> numbersInLines = new ArrayList<>();

		for (String line : inputLines) {
			numbersInLines.add(getNumberInLine(line));
		}

		return numbersInLines;
	}

	private static int getNumberInLine(String line) {
		List<Integer> numbersInLine = new ArrayList<>();
		HashMap<String, Integer> numberMap = new HashMap<>() {
			{
				put("one", 1);
				put("two", 2);
				put("three", 3);
				put("four", 4);
				put("five", 5);
				put("six", 6);
				put("seven", 7);
				put("eight", 8);
				put("nine", 9);
			}
		};

		// for (char c : line.toCharArray()) {
		// if (Character.isDigit(c)) {
		// numbersInLine.add(Character.getNumericValue(c));
		// }
		// }

		for (int i = 0; i < line.length(); i++) {
			if (Character.isDigit(line.charAt(i))) {
				numbersInLine.add(Character.getNumericValue(line.charAt(i)));
			} else {
				for (String numAsWord : numberMap.keySet()) {
					if (line.substring(i).startsWith(numAsWord)) {
						numbersInLine.add(numberMap.get(numAsWord));
						// This line fucked me over in part two, because it disregards same
						// letters, like twone. So, subtract one extra.
						i += numAsWord.length() - 2;
					}
				}
			}
		}

		return numbersInLine.get(0) * 10 + numbersInLine.get(numbersInLine.size() - 1);
	}
}
