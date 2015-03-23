import static org.junit.Assert.*;

import org.junit.Test;


public class PlayerTest {

	@Test
	public void testPlayer1() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		// Confirm successful construction.
		assertNotNull(player);
	}

	@Test (expected=Exception.class)
	public void testPlayer2() throws Exception {
		// Confirm that an out of bounds (-) id throws an exception.
		Player player = new Player(-1, "test", null);
	}
	
	@Test (expected=Exception.class)
	public void testPlayer3() throws Exception {
		// Confirm that an empty name throws an exception.
		Player player = new Player(0, "", null);
	}
	
	@Test
	public void testGetId() throws Exception {
		// Initialise testing objects.
		Player player = new Player(23, "test", null);
		// Confirm the correct id is returned.
		assertEquals(player.getId(), 23);
	}

	@Test
	public void testGetName() throws Exception {
		// Initialise testing objects.
		Player player = new Player(23, "test", null);
		// Confirm the correct name is returned.
		assertEquals(player.getName(), "test");
	}

	@Test
	public void testGetColour() {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		// Set the colour to red.
		player.setColour(Colour.RED);
		// Confirm the colour is returned.
		assertEquals(player.getColour(), Colour.RED);
	}

	@Test
	public void testSetColour() {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		// Set the colour to red.
		player.setColour(Colour.RED);
	}
	
	@Test
	public void testGetNextInstruction1() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		// Confirm an instruction is returned.
		Instruction nextInstruction = player.getNextInstruction(5);
		assertNotNull(nextInstruction);
		// TODO: This will need to be improved later
	}
	
	@Test (expected=Exception.class)
	public void testGetNextInstruction2() throws Exception {
		// Initialise testing objects.
		Player player = new Player(0, "test", null);
		// Confirm that an out of bounds (-) state throws an exception.
		Instruction nextInstruction = player.getNextInstruction(-1);
	}

}
