/**
 * This class represnts a single player.
 * @author Kieran
 */
/**
 * The Player class creates a Player object whith a unique id, a name, 
 * an ant brain, and a colour by which the player identifies his/her ants.
 * The player also keeps track of the amount of food his/her ants gather, 
 * how many ants still are alive.
 * @author Ralph
 */
public class Player {
	
	private int id;
	private String name;
	private Colour colour;
	private Instruction[] instruction;
	
	/** Kieran
	 * Construct this player.
	 * @param a unique id of this player
	 * @param the name of this player
	 * @param the path of the brain file
	 */
	/** Ralph
	* Generates a Player object with a name, an id, an ant brain and a colour.
	* @param id The id required to uniquely identify the player.
	* @param name The name of the player.
	* @param brainFilePath  The path to the players ant brain.
	* @param colour The colour the player will use for his ants, red or black.
	*/
	Player(int id, String name, String brainPath) {
		// Note: brainPath "null" should load a default brain for testing purposes
		this.id = id;
		this.name = name;
		this.colour = null;
		loadBrain(brainPath);
	}
	
	/** Kieran
	 * Get the unique id of this player.
	 * @return unique id
	 */
	/** Ralph
	* Gets the players unique id.
	* @return Returns the players unique id.
	*/
	public int getId() {
		// Note: Add this to class diagram
		return id;
	}
	
	/** Kieran
	 * Get the name of this player.
	 * @return the name
	 */
	/** Ralph
	* Gets the name of the player.
	* @return Returns the player's name.
	*/
	public String getName() {
		return name;
	}
	
	/** Kieran
	 * Get the colour of this player.
	 * @return the colour
	 */
	/** Ralph
	* Gets the colour that represents a player in a match.
	* @return Returns the colour that represents the player in a match.
	*/
	public Colour getColour() {
		return colour;
	}
	
	/** Kieran
	 * Set the colour of this player.
	 * @return the colour
	 */
	/** Ralph
	* Sets the player's colour to a given colour.
	* @param colour The colour that the will be used to reperesent a player in a match.
	*/
	public void setColour(Colour colour) {
		this.colour = colour;
	}
	
	/**
	 * Get the next instruction of the ant brain.
	 * @param the current ant brain state
	 * @return the next instruction given the current ant brain state
	 */
	public Instruction getNextInstruction(int state) {
		return null;
	}
	
	
	/**
	 * Load an antbrain file..
	 * @param the location of the antbrain file
	 */
	private void loadBrain(String brainPath) {
		
	}
}
