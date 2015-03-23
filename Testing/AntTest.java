import static org.junit.Assert.*;

import org.junit.Test;


public class AntTest {

	@Test
	public void testAnt() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Confirm successful construction.
		assertNotNull(ant);
	}

	@Test
	public void testGetPlayer() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Confirm the the correct player object is returned.
		assertEquals(ant.getPlayer(), player);
	}

	@Test
	public void testGetState() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Set the brain state to 23.
		ant.setState(23);
		// Confirm the correct brain state is returned.
		assertEquals(ant.getState(), 23);
	}
	
	@Test
	public void testSetState() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Set the brain state to 23.
		ant.setState(23);
	}
	
	@Test
	public void testGetRestingTurns() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Set the number of resting turns to 14.
		ant.setRestingTurns(14);
		// Confirm the correct number of resting turns is returned.
		assertEquals(ant.getRestingTurns(), 14);
	}
	
	@Test
	public void testSetRestingTurns() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Set the number of resting turns to 14.
		ant.setRestingTurns(14);
	}

	@Test
	public void testGetDirection() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Set the direction to 2.
		ant.setDirection(2);
		// Confirm the correct direction is returned.
		assertEquals(ant.getDirection(), 2);
	}
	
	@Test
	public void testSetDirection1() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Set the direction to 2.
		ant.setDirection(2);
	}
	
	@Test (expected=Exception.class)
	public void testSetDirection2() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Confirm that an out of bounds (-) direction parameter throws an exception.
		ant.setDirection(-1);
	}
	
	@Test (expected=Exception.class)
	public void testSetDirection3() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Confirm that an out of bounds (+) direction parameter throws an exception.
		ant.setDirection(6);
	}

	@Test
	public void testSetFood() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Set food holding state to true.
		ant.setFood(true);
	}

	@Test
	public void testGetFood() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Confirm current food holding state is false.
		assertFalse(ant.getFood());
		// Set food holding state to true.
		ant.setFood(true);
		// Confirm current food holding state has been updated to true.
		assertTrue(ant.getFood());
	}

	@Test
	public void testSetCell() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		Cell cell = new Cell(new Position(0, 0));
		// Set the location of this ant to a specified cell.
		ant.setCell(cell);
	}

	@Test
	public void testGetCell() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		Cell cell = new Cell(new Position(0, 0));
		// Set the location of this ant to a specified cell.
		ant.setCell(cell);
		// Confirm the correct location cell is returned.
		assertEquals(ant.getCell(), cell);
	}

}
