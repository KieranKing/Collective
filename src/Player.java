/**
 * This class represnts a single player.
 * @author Kieran
 */
public class Player {
	
	private int id;
	private String name;
	private Colour colour;
	private Instruction[] instruction;
	
	/**
	 * Construct this player.
	 * @param a unique id of this player
	 * @param the name of this player
	 * @param the path of the brain file
	 */
	Player(int id, String name, String brainPath) {
		// Note: Update class diagrams to include name, and path.
		// Note: brainPath "null" should load a default brain for testing purposes
		this.id = id;
		this.name = name;
		loadBrain(brainPath);
	}
	
	/**
	 * Get the unique id of this player.
	 * @return unique id
	 */
	public int getId() {
		// Note: Add this to class diagram
		return id;
	}
	
	/**
	 * Get the name of this player.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the colour of this player.
	 * @return the colour
	 */
	public Colour getColour() {
		// Note: Update returned variable in class diagram here
		return colour;
	}
	
	/**
	 * Set the colour of this player.
	 * @return the colour
	 */
	public void setColour(Colour colour) {
		// Note: Update returned variable in class diagram here
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
