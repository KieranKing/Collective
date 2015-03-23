import static org.junit.Assert.*;

import org.junit.Test;


public class GameEngineTest {

	@Test
	public void testProcessMatch() {
		// Initialise testing objects.
		GameEngine engine = new GameEngine();
		Player playerRed = new Player(0, "red", null);
		Player playerBlack = new Player(1, "black", null);
		// Process a match.
		String match = engine.processMatch(playerRed, playerBlack);
		// Confirm that a string has been returned.
		assertNotNull(match);
	}

	@Test
	public void testProcessTournament() {
		// Initialise testing objects.
		GameEngine engine = new GameEngine();
		Player[] players = null;
		for (int p = 0; p<10; p++) {
			players[p] = new Player(p, "player: " + p, null);
		}
		// Process a tournament.
		String[] match = engine.processTournament(players);
		// Confirm that a string has been returned.
		assertNotNull(match);
	}

}
