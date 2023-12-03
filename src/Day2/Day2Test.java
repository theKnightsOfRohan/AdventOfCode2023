package Day2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2Test {

	@Test
	public void testValidGame() {
		Game game1 = new Game("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
		Game game2 = new Game("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue");
		Game game3 = new Game("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");
		Game game4 = new Game("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red");
		Game game5 = new Game("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");

		assertTrue(game1.isValidGame(12, 13, 14));
		assertTrue(game2.isValidGame(12, 13, 14));
		assertFalse(game3.isValidGame(12, 13, 14));
		assertFalse(game4.isValidGame(12, 13, 14));
		assertTrue(game5.isValidGame(12, 13, 14));
	}

	@Test
	public void testSumIDs() {
		List<String> games = new ArrayList<String>(Arrays.asList(new String[] {
				"Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
				"Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
				"Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
				"Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
				"Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
		}));

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

		assertEquals(8, sumIDs);
	}
}