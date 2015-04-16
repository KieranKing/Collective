/**
 * The Instruction class generates an instruction object
 * which contains an action and a set of parameters.
 */
public class Instruction {
	private Action action;
	private int[] parameters;
	
	/**
	 * Constructs an instruction.
	 * @param action The action the ant is to perform.
	 * @param parameters The parameters associated with the action the ant is to perform.
	 */
	Instruction(Action action, int[] parameters) throws Exception {		
		switch (action) {
			case SENSE:
				// Go to "state_1" if "condition" holds in "direction"; otherwise go to "state_2".
				// Expected parameters: [direction (0 to 3), state_1, state_2, condition (0 to 8)]
				if (parameters.length != 4) {
					throw new Exception("The SENSE instruction must have 4 parameters!");
				} else if (parameters[0] < 0 || parameters[0] > 3) {
					throw new Exception("The SENSE instruction must have a direction parameter in the range of 0 to 3!");
				} else if (parameters[3] < 0 || parameters[3] > 8) {
					throw new Exception("The SENSE instruction must have a condition parameter in the range of 0 to 8!");
				}
				break;
			case MARK:
				// Set mark "marker" in current cell and go to "state".
				// Expected parameters: [marker (0 to 5), state]
				if (parameters.length != 2) {
					throw new Exception("The MARK instruction must have 2 parameters!");
				} else if (parameters[0] < 0 || parameters[0] > 5) {
					throw new Exception("The MARK instruction must have a marker parameter in the range of 0 to 5!");
				}
				break;
			case UNMARK:
				// Clear mark "marker" in current cell and go to "state".
				// Expected parameters: [marker (0 to 5), state]
				if (parameters.length != 2) {
					throw new Exception("The UNMARK instruction must have 2 parameters!");
				} else if (parameters[0] < 0 || parameters[0] > 5) {
					throw new Exception("The UNMARK instruction must have a marker parameter in the range of 0 to 5!");
				}
				break;
			case PICKUP:
				// Pick up food from current cell and go to "state_1"; otherwise go to "state_2" if there is no food in the current cell.
				// Expected parameters: [state_1, state_2]
				if (parameters.length != 2) throw new Exception("The PICKUP instruction must have 2 parameters!");
				break;
			case DROP:
				// Drop food in current cell and go to state.
				// Expected parameters: [state]
				if (parameters.length != 1) throw new Exception("The DROP instruction must have 1 parameters!");
				break;
			case TURN:
				// Turn direction and go to state.
				// Expected parameters: [direction (0 to 1), state]
				if (parameters.length != 2) {
					throw new Exception("The TURN instruction must have 2 parameters!");
				} else if (parameters[0] < 0 || parameters[0] > 1) {
					throw new Exception("The TURN instruction must have a direction parameter in the range of 0 to 1!");
				}
				break;
			case MOVE:
				// Move forward and go to state_1; otherwise, go to state_2 if the cell ahead is blocked.
				// Expected parameters: [state_1, state_2]
				if (parameters.length != 2) throw new Exception("The MOVE instruction must have 2 parameters!");
				break;
			case FLIP:
				// Choose a random number x from 0 to seed-1; go to state_1 if x=0, otherwise go to state_2.
				// Expected parameters: [seed, state_1, state_2]
				if (parameters.length != 2) throw new Exception("The FLIP instruction must have 3 parameters!");
				break;
		}
		this.action = action;
		this.parameters = parameters;
	}
	
	/**
	 * Gets the action associated with the instruction.
	 * @return the action associated with this instruction
	 */
	public Action getAction() {
		return action;
	}
	
	/**
	 * Gets the parameters associated with the instruction.
	 * @return the parameters associated with this instruction
	 */
	public int[] getParameters() {
		return parameters;
	}
	
}