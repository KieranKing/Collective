import static org.junit.Assert.*;

import org.junit.Test;


public class InstructionTest {

	@Test
	public void testInstruction() throws Exception {
		// Initialise testing objects.
		Instruction instruction = new Instruction(Action.MARK, new int[]{0, 0});
		// Confirm successful construction.
		assertNotNull(instruction);
	}
	
	@Test (expected=Exception.class)
	public void testInstructionSense1() throws Exception {
		// Confirm that an incorrect number of parameters for the SENSE throws an exception.
		Instruction instruction = new Instruction(Action.SENSE, new int[]{0, 0, 0, 0, 0});
	}

	@Test (expected=Exception.class)
	public void testInstructionSense2() throws Exception {
		// Confirm that an out of bounds (-) direction parameter throws an exception.
		Instruction instruction = new Instruction(Action.SENSE, new int[]{-1, 0, 0, 0});
	}	
	
	@Test (expected=Exception.class)
	public void testInstructionSense3() throws Exception {
		// Confirm that an out of bounds (+) direction parameter throws an exception.
		Instruction instruction = new Instruction(Action.SENSE, new int[]{10, 0, 0, 0});
	}
	
	@Test (expected=Exception.class)
	public void testInstructionSense4() throws Exception {
		// Confirm that an out of bounds (-) condition parameter throws an exception.
		Instruction instruction = new Instruction(Action.SENSE, new int[]{0, 0, 0, -1});
	}
	
	@Test (expected=Exception.class)
	public void testInstructionSense5() throws Exception {
		// Confirm that an out of bounds (+) condition parameter throws an exception.
		Instruction instruction = new Instruction(Action.SENSE, new int[]{0, 0, 0, 10});
	}
	
	@Test (expected=Exception.class)
	public void testInstructionMark() throws Exception {
		// Confirm that an incorrect number of parameters for the MARK instruction throws an exception.
		Instruction instruction = new Instruction(Action.MARK, new int[]{0, 0, 0, 0, 0});
	}
	
	@Test (expected=Exception.class)
	public void testInstructionUnmark() throws Exception {
		// Confirm that an incorrect number of parameters for the UNMARK instruction throws an exception.
		Instruction instruction = new Instruction(Action.UNMARK, new int[]{0, 0, 0, 0, 0});
	}
	
	@Test (expected=Exception.class)
	public void testInstructionPickup() throws Exception {
		// Confirm that an incorrect number of parameters for the PICKUP instruction throws an exception.
		Instruction instruction = new Instruction(Action.PICKUP, new int[]{0, 0, 0, 0, 0});
	}
	
	@Test (expected=Exception.class)
	public void testInstructionDrop() throws Exception {
		// Confirm that an incorrect number of parameters for the DROP instruction throws an exception.
		Instruction instruction = new Instruction(Action.DROP, new int[]{0, 0, 0, 0, 0});
	}
	
	@Test (expected=Exception.class)
	public void testInstructionTurn1() throws Exception {
		// Confirm that an incorrect number of parameters for the TURN instruction throws an exception.
		Instruction instruction = new Instruction(Action.TURN, new int[]{0, 0, 0, 0, 0});
	}

	@Test (expected=Exception.class)
	public void testInstructionTurn2() throws Exception {
		// Confirm that an out of bounds (-) direction parameter throws an exception.
		Instruction instruction = new Instruction(Action.TURN, new int[]{-1, 0});
	}
	
	@Test (expected=Exception.class)
	public void testInstructionTurn3() throws Exception {
		// Confirm that an out of bounds (+) direction parameter throws an exception.
		Instruction instruction = new Instruction(Action.TURN, new int[]{2, 0});
	}

	@Test (expected=Exception.class)
	public void testInstructionMove() throws Exception {
		// Confirm that an incorrect number of parameters for the MOVE instruction throws an exception.
		Instruction instruction = new Instruction(Action.MOVE, new int[]{0, 0, 0, 0, 0});
	}

	@Test (expected=Exception.class)
	public void testInstructionFlip() throws Exception {
		// Confirm that an incorrect number of parameters for the FLIP instruction throws an exception.
		Instruction instruction = new Instruction(Action.FLIP, new int[]{0, 0, 0, 0, 0});
	}
	
	@Test
	public void testGetAction() throws Exception {
		// Initialise testing objects.
		Instruction instruction = new Instruction(Action.MARK, new int[]{0, 0});
		// Confirm the correct action is returned.
		assertEquals(instruction.getAction(), Action.MARK);
	}

	@Test
	public void testGetParameters() throws Exception {
		// Initialise testing objects.
		Instruction instruction = new Instruction(Action.MARK, new int[]{1, 2});
		// Confirm the correct parameters are returned.
		assertEquals(instruction.getParameters()[0], 1);
		assertEquals(instruction.getParameters()[1], 2);
	}

}