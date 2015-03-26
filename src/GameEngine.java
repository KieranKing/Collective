/**
 * This class processes matches and tournaments.
 * @author Kieran
 */
/**
 * The GameEngine class creates a game engine object which handles processing the
 * matches between players in either a tournament setup or a match set up.
 * Allows for the GUI to do its job without threads.
 * @author Ralph
 */
public class GameEngine {

	/**
	 * Constructer.
	 */
	GameEngine() {
		//world = new World(mapPath);
			//each player plays every other player, 3 plays against 2 and 1 (2 matches) and 2 plays against 1 (1 match) => (n-1)! matches.
			//1 world.
		// for each pair of players
		//{
		//	world.add(p1, p2, mapPath)
		//}
	}

	/** Kieran
	 * Process a single match.
	 * @param the red player
	 * @param the black player
	 * @return the file path of the processed match
	 */
	/** Ralph
	* Runs a match between two palyers, player1 and player2.
	* @param player1 Player 1, representing the first player.
	* @param player2 Player 2, representing the second player.
	*/	
	public String processMatch(Player playerA, Player playerB) {
		// Every round and ant instruction should be processed here.
		return "";
	}
	
	/** Kieran
	 * Process a tournament.
	 * @param the players that will be participating in this tournament
	 * @return an array of file paths of the processed matches
	 */
	/** Ralph
	* Runs a match between one player and every other player for each player.
	*/
	public String[] processTournament(Player[] players) {
		// This call execute processMatch for X matches.
		//(n-1)! matches, take 1 against 2, 3, take 2 against 3.
				//for (World w:world)
				//	w.step()
	}
}
