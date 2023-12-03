package Day2;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class Day2 {
	/*
	 * Part 1.
	 * 
	 * You play several games and record the information from each game (your puzzle
	 * input). Each game is listed with its ID number (like the 11 in Game 11: ...)
	 * followed by a semicolon-separated list of subsets of cubes that were revealed
	 * from the bag (like 3 red, 5 green, 4 blue).
	 * 
	 * For example, the record of a few games might look like this:
	 * 
	 * Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
	 * Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
	 * Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
	 * Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
	 * Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
	 * In game 1, three sets of cubes are revealed from the bag (and then put back
	 * again). The first set is 3 blue cubes and 4 red cubes; the second set is 1
	 * red cube, 2 green cubes, and 6 blue cubes; the third set is only 2 green
	 * cubes.
	 * 
	 * The Elf would first like to know which games would have been possible if the
	 * bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?
	 * 
	 * In the example above, games 1, 2, and 5 would have been possible if the bag
	 * had been loaded with that configuration. However, game 3 would have been
	 * impossible because at one point the Elf showed you 20 red cubes at once;
	 * similarly, game 4 would also have been impossible because the Elf showed you
	 * 15 blue cubes at once. If you add up the IDs of the games that would have
	 * been possible, you get 8.
	 * 
	 * Determine which games would have been possible if the bag had been loaded
	 * with only 12 red cubes, 13 green cubes, and 14 blue cubes. What is the sum of
	 * the IDs of those games?
	 */

	/*
	 * Part 2.
	 * 
	 * As you continue your walk, the Elf poses a second question: in each game you
	 * played, what is the fewest number of cubes of each color that could have been
	 * in the bag to make the game possible?
	 * 
	 * Again consider the example games from earlier:
	 * 
	 * Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
	 * Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
	 * Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
	 * Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
	 * Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
	 * In game 1, the game could have been played with as few as 4 red, 2 green, and
	 * 6 blue cubes. If any color had even one fewer cube, the game would have been
	 * impossible.
	 * Game 2 could have been played with a minimum of 1 red, 3 green, and 4 blue
	 * cubes.
	 * Game 3 must have been played with at least 20 red, 13 green, and 6 blue
	 * cubes.
	 * Game 4 required at least 14 red, 3 green, and 15 blue cubes.
	 * Game 5 needed no fewer than 6 red, 3 green, and 2 blue cubes in the bag.
	 * The power of a set of cubes is equal to the numbers of red, green, and blue
	 * cubes multiplied together. The power of the minimum set of cubes in game 1 is
	 * 48. In games 2-5 it was 12, 1560, 630, and 36, respectively. Adding up these
	 * five powers produces the sum 2286.
	 * 
	 * For each game, find the minimum set of cubes that must have been present.
	 * What is the sum of the power of these sets?
	 */
	public static void main(String[] args) {
		List<String> games = Utils.Files.readFileByLine("src/Day2/input.txt");
		// List<String> games = Utils.Files.readFileByLine("src/Day2/test.txt");

		List<Game> gameList = new ArrayList<Game>();

		for (String game : games) {
			gameList.add(new Game(game));
		}

		int red = 12;
		int green = 13;
		int blue = 14;

		int sumIDs = 0;

		for (Game game : gameList) {
			if (game.isValidGame(red, green, blue)) {
				sumIDs += game.id;
			}
		}

		System.out.println("Sum of IDs: " + sumIDs);

		int sumPower = 0;

		for (Game game : gameList) {
			sumPower += game.getPower();
		}

		System.out.println("Sum of Power: " + sumPower);
	}

}

class Game {
	int id;
	String[] rounds;

	public Game(String game) {
		String[] gameSplit = game.split(": ");
		this.id = Integer.parseInt(gameSplit[0].split(" ")[1]);
		this.rounds = gameSplit[1].split("; ");
	}

	public boolean isValidGame(int red, int green, int blue) {
		HashMap<String, Integer> possibleAmounts = new HashMap<String, Integer>() {
			{
				put("red", red);
				put("green", green);
				put("blue", blue);
			}
		};

		for (String round : rounds) {
			String[] cubes = round.split(", ");
			for (String cube : cubes) {
				String[] cubeSplit = cube.split(" ");
				String color = cubeSplit[1];
				int amount = Integer.parseInt(cubeSplit[0]);

				if (amount > possibleAmounts.get(color)) {
					return false;
				}
			}
		}

		return true;
	}

	public int getPower() {
		HashMap<String, Integer> possibleAmounts = new HashMap<String, Integer>() {
			{
				put("red", 0);
				put("green", 0);
				put("blue", 0);
			}
		};

		for (String round : rounds) {
			String[] cubes = round.split(", ");
			for (String cube : cubes) {
				String[] cubeSplit = cube.split(" ");
				String color = cubeSplit[1];
				int amount = Integer.parseInt(cubeSplit[0]);

				if (amount > possibleAmounts.get(color)) {
					possibleAmounts.put(color, amount);
				}
			}
		}

		return possibleAmounts.get("red") * possibleAmounts.get("green") * possibleAmounts.get("blue");
	}
}