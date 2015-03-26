/**
 * This class represents the world of a single match.
 * @author Kieran
 */
/**
 * The Map class creates a map object which is made up of cells. The map can either be generated
 * using a premade map pattern or it can be generated with a random pattern.
 * @author Ralph
 */
/**
 * The World class generates a world object, consisting of a map and two players. 
 * The match takes place between the two players.
 * @author Ralph
 */
public class World {
	
	private Cell[][] cells;
	private Ant[] ants;
	private int[] antCount; // Note: update class diagram to single array
	private int[] foodCount; // Note: update class diagram to single array
	private int round;
	
	/** Kieran
	 * Construct a randomly generated world.
	 */
	/** Ralph
	* Generates a World object consisting of a map and two players.
	* @param player1 First player to participae in the match.
	* @param player2 Second player to participate in the match.
	* @param path The path to the map file, if null, random map will be generated.
	*/
	World(Player[] players) {
		generateWorld();
	}
	
	/**
	 * Construct a world loaded from file.
	 * @param the world file to load
	 */
	World(Player[] players, String worldPath) {
		loadWorld(worldPath);
	}
		
	/**
	 * Randomly generate world.
	 */
	private void generateWorld() {
		//map = new Cell[y][x];
		//for(int i = 0; i<(y-1); i++){
		//	for(int j = 0; j<(x-1); j++){
		//			map[i][j] = new Cell();
		//	}
		//}
	}
	
	/** Kieran
	 * Load a world from file.
	 * @param the world file to load
	 */
	/** Ralph
	* Generates a map object using a predefined map layout.
	* @param path  Path to predefined map layout file.
	*/
	private void loadWorld(String worldPath) {
		//x = "";
		//y = "";
		//for(int i = 0; i<(y-1); i++){
		//	for(int j = 0; j<(x-1); j++){
		//		
		//	}
		//}
	}
	
	/**
	 * Increment the food count of a specified player.
	 * @param the player whose food count should be incremented
	 */
	public void incrementFoodCount(Player player) {
		foodCount[player.getId()]++;
	}
	
	/**
	 * Decrement the ant count of a specified player.
	 * @param player whose ant count should be decremented
	 */
	public void decrementAntCount(Player player) {
		antCount[player.getId()]--;
	}
	
	
	/** Ralph
	* Increments the amount of rounds completed by one.
	*/
	public void incrementRoundCount(){
		this.round++;
	}
	
	/** Ralph
	* Gets the amount of rounds that have been complete.
	* @return Returns the amount of rounds that have been completed.
	*/
	public int getRoundCount(){
		return this.round;
	}
	
	/**
	 * Get the rocky state of a cell at a specified location.
	 * @param the location of the cell to check
	 * @return true for rocky, false for clear
	 */
	public boolean getRocky(Position position) {
		// Note: add this method to the class diagram
	}
	
	/**
	 * Set the rocky state of a cell at a specified location.
	 * @param the location of the cell to update
	 * @param true for rocky, false for clear
	 */
	public void setRocky(Position position, boolean rocky) {
		// Note: update the paramters in the class diagram
	}
	
	/**
	 * Check if an ant exists in a cell at a specified location.
	 * @param the location of the cell to check
	 * @return true if an ant is at the specified location, false otherwise
	 */
	public boolean isAntAt(Position position) {
		
	}
	
	/**
	 * Set an ant to a cell at a specified location.
	 * @param the ant to relocate
	 * @param the location of the cell to move this ant to
	 */
	public void setAntAt(Ant ant, Position position) {
		
	}
	
	/**
	 * Remove an ant from a cell at a specified location.
	 * @param the location of the cell where the ant should be removed
	 * @return the ant that has been removed from the specified cell
	 */
	public Ant clearAntAt(Position position) {
		// Note: update class diagram from what this method returns
	}
	
	/**
	 * Check if an ant of a specified id is alive.
	 * @param the id of the ant to check
	 * @return true if the specified ant is alive, false otherwise
	 */
	public boolean antIsAlive(int id) {
		
	}
	
	/**
	 * Kill an ant in the cell at a specific location.
	 * @param the location of the cell that contains the ant to kill
	 */
	public void killAntAt(Position position) {
		
	}
	
	/**
	 * Get the number of food particles contained in a cell at a specified location.
	 * @param the location of the cell to check
	 * @return the number of food particles contained in the specified cell
	 */
	public int getFoodAt(Position position) {
		
	}
	
	/**
	 * Set the number of food particles contained in a cell at a specified location.
	 * @param the location of the cell to update
	 * @param the number of food particles to set in the specified cell
	 */
	public void setFoodAt(Position position, int foodCount) {
		
	}
	
	/**
	 * Get the player that owns an anthill at a specified location.
	 * @param the location of the cell to check
	 * @return the player that owns the anthill at the specified location, or null if this cell is not part of an anthill
	 */
	public Player getAnthillAt(Position position) {
		
	}
	
	/**
	 * Set the player that owns an anthill at a specified location.
	 * @param the location of the cell
	 * @param the player that owns the specified cell as an anthill
	 */
	public void setAnthillAt(Position position, Player player) {
		
	}
	
	/**
	 * Set the state of a chemical marker in a cell at a specified location.
	 * @param the player
	 * @param the chemical (from 0 to 5)
	 * @param the state of the specified chemical marker
	 * @param the location of the cell to update
	 */
	public void setMarkerAt(Player player, int chemical, boolean marked, Position position) {
		
	}
	
	/**
	 * Get the state of a chemical marker in a cell at a specified location.
	 * @param the player
	 * @param the specific chemical (from 0 to 5)
	 * @param the location of the cell to check
	 * @return the state of the specified chemical marker
	 */
	public boolean getMarkerAt(Player player, int chemical, Position position) {
		
	}
	
	/**
	 * Get the number of ants that are adjacent to a specified ant.
	 * @param the ant to check for adjacent ants
	 * @return the number of adjacent ants
	 */
	public int countAdjacentAnts(Ant ant) {
		
	}
	
	/**
	 * Get the adjacent cell in a specified cell.
	 * @param the cell to check for an adjacent cell
	 * @param the direction of the adjacent cell
	 * @return the adjacent cell, or null if there is not adjacent cell (outside of the world dimensions)
	 */
	public Cell getAdjacentCell(Cell cell, int direction) {
		
	}
	
	/**
	 * Move an ant in the direction that ant is facing.
	 * @param the ant to move
	 */
	public void moveAnt(Ant ant) {
		
	}
	
	/** Ralph
	* Gets the cell with the given x,y-coordinates.
	* @param x  X-Coordinate of the cell.
	* @param y  Y-Coordinate of the cell.
	* @return Returns the cell at position x,y in the map.
	*/
	public Cell getCell(Position position) {
		return cells[position.getX()][position.getY()];
	}
}
