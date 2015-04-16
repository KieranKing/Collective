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
	}

	/** Kieran
	 * Process a single match.
	 * @param the red player
	 * @param the black player
	 * @return the file path of the processed match
	 */
	public String processMatch(Player playerA, Player playerB) {
		World[] simulationResult;
		World matchWorld = new World(new Player[] {playerA, playerB});
		Ant[] ants = matchWorld.getAnts();
		// Loop through rounds.
		for (int round = 0; round < 300000; round++) {
			// Loop through ants.
			for (int a = 0; a<ants.length; a++){
				Instruction instruction = ants[a].getPlayer().getNextInstruction(ant.getState());
				
				switch (instruction.getAction()) {
					case SENSE:
						break;
						
					case MARK:
						// Mark the cell where this ant is located.
						ants[a].getCell().setMarker(ants[a].getPlayer(), instruction.getParameters()[0], true);
						// Update the state for this ant.
						ants[a].setState(instruction.getParameters()[1]);
						break;
						
					case UNMARK:
						// Unmark the cell where this ant is located.
						ants[a].getCell().setMarker(ants[a].getPlayer(), instruction.getParameters()[0], false);
						// Update the state for this ant.
						ants[a].setState(instruction.getParameters()[1]);
						break;
						
					case PICKUP:
						if (ants[a].getCell().getFoodCount() > 0 && ants[a].getFood() == false) {
							// This cell contains food and the ant is not holding food, pick up the food on this cell.
							ants[a].getCell().setFoodCount(ants[a].getCell().getFoodCount()-1);
							ants[a].setFood(true);
							// Update the state for this ant.
							ants[a].setState(instruction.getParameters()[0]);
						} else {
							// This cell does not contain food, update state.
							ants[a].setState(instruction.getParameters()[1]);
						}
						break;
						
					case DROP:
						if (ants[a].getFood()) {
							// Drop food on the cell where the ant is located.
							ants[a].getCell().setFoodCount(ants[a].getCell().getFoodCount()+1);
							ants[a].setFood(false);
						}
						// Update the state for this ant.
						ants[a].setState(instruction.getParameters()[0]);
						break;
						
					case TURN:
						if (instruction.getParameters()[0] == 0) {
							// Turn left.
							ants[a].setDirection((ants[a].getDirection()-1) % 6);
						} else {
							// Turn right.
							ants[a].setDirection((ants[a].getDirection()+1) % 6);
						}
						// Update the state for this ant.
						ants[a].setState(instruction.getParameters()[1]);
						break;
						
					case MOVE:
						// Move instruction
						ants[a].setState(instruction.getParameters()[1]);
						break;
						
					case FLIP:
						// Flip instruction
						ants[a].setState(instruction.getParameters()[1]);
						break;
						
					default:
						break;
				}

			}

			simulationResult[round] = matchWorld;
			// Every round and ant instruction should be processed here.
			return "";
			}
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
