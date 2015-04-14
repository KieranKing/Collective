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
		World[] simulationResult;
		World matchWorld = new World(playerA, playerB);
		for(int round = 0; round < 300000; round++;){
			for(/* getAnt method from the world class which doesn't exist yet*/){ // cause there's no getAnt, you can't for through them. of if you can think of workaround - do it :}
				Ant ant = new Ant();
				Instruction instruction = ant.getPlayer().getNextInstruction(ant.getState());

				if(instruction.getAction() == Action.MARK){
					ant.getCell().setMarker(ant.getPlayer(), instruction.getParameters()[0], true);

				}

				else if(instruction.getAction() == Action.UNMARK){
					ant.getCell().setMarker(ant.getPlayer(), instruction.getParameters()[0], false);
				}

				else if(instruction.getAction() == Action.PICKUP){
					ant.pickFood(ant.getCell()); // is there a method pick food? :/ hope it works right -_- and that they just did not add it
					if (ant.getFood()){
						ant.setState(1);
					}
					else{
						ant.setState(2);
					}
				}

				else if(instruction.getAction() == Action.DROP){
					ant.dropFood(getCell());   // also could not find the drop food function :/
				}

				else if(instruction.getAction() == Action.TURN){
					ant.setDirection(instruction.getParameters()[0]);
				}

				else if(instruction.getAction() == Action.MOVE){
					if (ant.getCell().isRocky()){ // if method get direction worked, it should've returned a cell in front, and it could check if it's blocked, BUT it returns direction :/ maybe it could try go formard, see if it's block and go beckwards, but that's so ishy, that i don't like it.
						ant.moveForward();
						ant.setState(1);
					}
					else{
						ant.setState(2);
					}
				}

				else if(instruction.getAction() == Action.FLIP){

				}


			}



			simulationResult[round]=matchWorld;
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
		public String[] processTournament(Player[] players) {// can do array of players as match input, new array assign then take array as input for match

			for(int i = 0; i<players.length(); i++){
				for(int j = i+1; j<(players.length()-1); j++){//r:check i and j and where they need to end.
					processMatch(players[i], players[j]);
				}
			}
			// This call execute processMatch for X matches.
			//(n-1)! matches, take 1 against 2, 3, take 2 against 3.
			//for (World w:world)
			//	w.step()
		}
	}