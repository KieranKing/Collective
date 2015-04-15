import static org.junit.*;

import org.junit.Assert;
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

	@Test
	public void testLoadBrain()throws LexicalException, SyntaxException{
		Player player = new Player(1, "test", "lexsyntaxtest.txt");
		Instruction[] i= new Instruction[18];

		int[] int1 = new int[4];
		int1[0] =0 ; int1[1] =1 ; int1[2] =1 ; int1[3] =0 ;
		Instruction i1 = new Instruction(Action.SENSE,int1);
		int[] int2 = new int[4];
		int2[0] =1 ; int2[1] =1 ; int2[2] =1 ; int2[3] =1 ;
		Instruction i2 = new Instruction(Action.SENSE,int2);
		int[] int3 = new int[4];
		int3[0] =2 ; int3[1] =1 ; int3[2] =1 ; int3[3] =2 ;
		Instruction i3 = new Instruction(Action.SENSE,int3);
		int[] int4 = new int[4];
		int4[0] =3 ; int4[1] =1 ; int4[2] =1 ; int4[3] =3 ;
		Instruction i4 = new Instruction(Action.SENSE,int4);
		int[] int5 = new int[4];
		int5[0] =0 ; int5[1] =1 ; int5[2] =1 ; int5[3] =4 ;
		Instruction i5 = new Instruction(Action.SENSE,int5);
		int[] int6 = new int[4];
		int6[0] =1 ; int6[1] =1 ; int6[2] =1 ; int6[3] =5 ;
		Instruction i6 = new Instruction(Action.SENSE,int6);
		int[] int7 = new int[4];
		int7[0] =2 ; int7[1] =1 ; int7[2] =1 ; int7[3] =6 ;
		Instruction i7 = new Instruction(Action.SENSE,int7);
		int[] int8 = new int[4];
		int8[0] =3 ; int8[1] =1 ; int8[2] =1 ; int8[3] =7 ;
		Instruction i8 = new Instruction(Action.SENSE,int8);
		int[] int9 = new int[4];
		int9[0] =3 ; int9[1] =1 ; int9[2] =1 ; int9[3] =8 ;
		Instruction i9 = new Instruction(Action.SENSE,int9);
		int[] int10 = new int[4];
		int10[0] =3 ; int10[1] =1 ; int10[2] =1 ; int10[3] =9 ;
		Instruction i10 = new Instruction(Action.SENSE,int10);

		int[] int11 = new int[2];
		int11[0] =3 ; int11[1] =1 ;
		Instruction i11 = new Instruction(Action.SENSE,int11);
		int[] int12 = new int[2];
		int12[0] =3 ; int12[1] =1 ;
		Instruction i12 = new Instruction(Action.SENSE,int12);
		int[] int13 = new int[2];
		int13[0] =3 ; int13[1] =1 ;;
		Instruction i13 = new Instruction(Action.SENSE,int13);
		int[] int14 = new int[2];
		int14[0] =3 ; int14[1] =1 ;;
		Instruction i14 = new Instruction(Action.SENSE,int14);

		int[] int15 = new int[2];
		int15[0] =3 ; int15[1] =1 ;
		Instruction i15 = new Instruction(Action.SENSE,int11);
		int[] int16 = new int[2];
		int16[0] =3 ; int16[1] =1 ;
		Instruction i16 = new Instruction(Action.SENSE,int12);
		int[] int17 = new int[2];
		int17[0] =3 ; int17[1] =1 ;;
		Instruction i17 = new Instruction(Action.SENSE,int13);
		int[] int18 = new int[2];
		int18[0] =3 ; int18[1] =1 ;;
		Instruction i18 = new Instruction(Action.SENSE,int14);

		i[0] = i1;
		i[1] = i2;
		i[2] = i3;
		i[3] = i4;
		i[4] = i5;
		i[5] = i6;
		i[6] = i7;
		i[7] = i8;
		i[8] = i9;
		i[9] = i10;
		i[10] = i11;
		i[11] = i12;
		i[12] = i13;
		i[13] = i14;
		i[14] = i15;
		i[15] = i16;
		i[16] = i17;
		i[17] = i18;

		Assert.assertArrayEquals((object) i, (object) player.getInstructions());

	}
	@Test(expected=Exception.class)
	public void testLoadBrainLexError()throws LexicalException, SyntaxException{
		Player player = new Player(1, "test", "lexerror.txt");

	}
	@Test(expected=Exception.class)
	public void testLoadBrainParseError()throws LexicalException, SyntaxException{
		Player player = new Player(1, "test", "syntaxerror.txt");

	}

}
