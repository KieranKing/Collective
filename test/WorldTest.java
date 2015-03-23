import static org.junit.Assert.*;

import org.junit.Test;


public class WorldTest {

	@Test
	public void testWorld1() throws Exception {
		// Generate a world.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		// Confirm successful construction.
		assertNotNull(world);
	}

	@Test
	public void testWorld2() throws Exception {
		// Load a world from file.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players, "path to testing world");
		// Confirm successful construction.
		assertNotNull(world);
	}

	@Test
	public void testIncrementFoodCount() throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		// Increment the food counter for the specified player.
		world.incrementFoodCount(players[0]);
	}

	@Test
	public void testDecrementAntCount() throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		// Decrement the ant counter for the specified player.
		world.decrementAntCount(players[0]);
	}

	@Test
	public void testGetRocky() throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		// Confirm that getting the rocky state of a cell throws no exceptions.
		world.getRocky(new Position(0, 0));
	}

	@Test
	public void testSetRocky() throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		// Get the rocky state of cell (0, 0).
		boolean rocky = world.getRocky(new Position(0, 0));
		// Set the rocky state of cell (0, 0) to the opposite of what it was.
		world.setRocky(new Position(0, 0), !rocky);
		// Confirm the rocky state of cel (0, 0) has updated.
		assertNotEquals(world.getRocky(new Position(0, 0)), rocky);
	}

	@Test
	public void testIsAntAt() throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		Ant ant = new Ant(players[0]);
		// Set the specified ant to be located at cell (0, 0).
		world.setAntAt(ant, new Position(0, 0));
		// Confirm the specified ant is located at cell (0, 0).
		assertTrue(world.isAntAt(new Position(0, 0)));
	}

	@Test
	public void testSetAntAt() throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		Ant ant = new Ant(players[0]);
		// Set the specified ant to be located at cell (0, 0).
		world.setAntAt(ant, new Position(0, 0));
	}

	@Test
	public void testClearAntAt()  throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		Ant ant = new Ant(players[0]);
		// Set the specified ant to be located at cell (0, 0).
		world.setAntAt(ant, new Position(0, 0));
		// Confirm the specified ant is located at cell (0, 0).
		assertTrue(world.isAntAt(new Position(0, 0)));
		// Clear this ant from the cell (0, 0).
		world.clearAntAt(new Position(0, 0));
		// Confirm the ant has been cleared from the cell (0, 0).
		assertFalse(world.isAntAt(new Position(0, 0)));
	}

	@Test
	public void testKillAntAt() throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		Ant ant = new Ant(players[0]);
		// Set the specified ant to be located at cell (0, 0).
		world.setAntAt(ant, new Position(0, 0));
		// Confirm this ant is alive.
		assertNotNull(ant);
		// Kill the ant located at cell (0, 0).
		world.killAntAt(new Position(0, 0));
		// Confirm the ant has been killed.
		assertNull(ant);
	}

	@Test
	public void testGetFoodAt() throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		// Set the number of food particles at cell (0, 0) to 99.
		world.setFoodAt(new Position(0, 0), 99);
		// Confirm the number of food particles at cell (0, 0) is 99.
		assertEquals(world.getFoodAt(new Position(0, 0)), 99);
	}

	@Test
	public void testSetFoodAt() throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		// Set the number of food particles at cell (0, 0) to 99.
		world.setFoodAt(new Position(0, 0), 99);
	}

	@Test
	public void testGetAnthillAt() throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		// Set the anthill status of cell (0, 0) to belong to the specified player.
		world.setAnthillAt(new Position(0, 0), players[0]);
		// Confirm the anthill status of cell (0, 0) belongs to the specified player.
		assertEquals(world.getAnthillAt(new Position(0, 0)), players[0]);
	}
	
	@Test
	public void testSetAnthillAt() throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		// Set the anthill status of cell (0, 0) to belong to the specified player.
		world.setAnthillAt(new Position(0, 0), players[0]);
	}

	@Test
	public void testSetMarkerAt() throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		// Set chemical marker "3" for the specified player to be marked at cell (0, 0).
		world.setMarkerAt(players[0], 3, true, new Position(0, 0));
	}

	@Test
	public void testGetMarkerAt() throws Exception {
		// Initialise testing objects.
		Player[] players = {new Player(0, "red", null), new Player(1, "black", null)};
		World world = new World(players);
		// Confirm chemical marker "3" for the specified player is unmarked at cell (0, 0).
		assertFalse(world.getMarkerAt(players[0], 3, new Position(0, 0)));
		// Set chemical marker "3" for the specified player to be marked at cell (0, 0).
		world.setMarkerAt(players[0], 3, true, new Position(0, 0));
		// Confirm chemical marker "3" for the specified player has changed from unmarked to marked at cell (0, 0).
		assertTrue(world.getMarkerAt(players[0], 3, new Position(0, 0)));
	}

	@Test
	public void testCountAdjacentAnts() throws Exception {
		// TODO: This needs to be updated
		fail("Not yet implemented");
	}

	@Test
	public void testGetAdjacentCell() throws Exception {
		// TODO: This needs to be updated
		fail("Not yet implemented");
	}

	@Test
	public void testMoveAnt() throws Exception {
		// TODO: This needs to be updated
		fail("Not yet implemented");
	}

}
