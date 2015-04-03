import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;// check what is really needed.
import java.lang.*;// check what is really needed.

/**
 * This class represnts a single player.
 *
 * @author Kieran
 */
/**
 * The Player class creates a Player object with a unique id, a name, an ant
 * brain, and a colour by which the player identifies his/her ants. The player
 * also keeps track of the amount of food his/her ants gather, how many ants
 * still are alive.
 *
 * @author Ralph
 */
public class Player {

	private int id;
	private String name;
	private Colour colour;
	private Instruction[] instruction;

	private enum sensedir {

		Here, Ahead, LeftAhead, RightAhead
	};

	private enum cond {

		Friend, Foe, FriendWithFood, FoeWithFood, Food, Rock, Marker, FoeMarker, Home, FoeHome
	};

	private enum lr {

		Left, Right
	};

	/**
	 * Kieran Construct this player.
	 *
	 * @param a unique id of this player
	 * @param the name of this player
	 * @param the path of the brain file
	 */
	/**
	 * Ralph Generates a Player object with a name, an id, an ant brain and a
	 * colour.
	 *
	 * @param id The id required to uniquely identify the player.
	 * @param name The name of the player.
	 * @param brainFilePath The path to the players ant brain.
	 * @param colour The colour the player will use for his ants, red or black.
	 */
	Player(int id, String name, String brainPath) throws LexicalException, SyntaxException, FileNotFoundException, IOException {
		// Note: brainPath "null" should load a default brain for testing purposes
		this.id = id;
		this.name = name;
		this.colour = null;
		loadBrain(brainPath);
	}

	/**
	 * Kieran Get the unique id of this player.
	 *
	 * @return unique id
	 */
	/**
	 * Ralph Gets the players unique id.
	 *
	 * @return Returns the players unique id.
	 */
	public int getId() {
		// Note: Add this to class diagram
		return id;
	}

	/**
	 * Kieran Get the name of this player.
	 *
	 * @return the name
	 */
	/**
	 * Ralph Gets the name of the player.
	 *
	 * @return Returns the player's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Kieran Get the colour of this player.
	 *
	 * @return the colour
	 */
	/**
	 * Ralph Gets the colour that represents a player in a match.
	 *
	 * @return Returns the colour that represents the player in a match.
	 */
	public Colour getColour() {
		return colour;
	}

	/**
	 * Kieran Set the colour of this player.
	 *
	 * @return the colour
	 */
	/**
	 * Ralph Sets the player's colour to a given colour.
	 *
	 * @param colour The colour that the will be used to reperesent a player in
	 * a match.
	 */
	public void setColour(Colour colour) {
		this.colour = colour;
	}

	/**
	 * Get the next instruction of the ant brain.
	 *
	 * @param the current ant brain state
	 * @return the next instruction given the current ant brain state
	 */
	public Instruction getNextInstruction(int state) {
		return null;
	}

	/**
	 * Load an antbrain file..
	 *
	 * @param the location of the antbrain file
	 */
	private void loadBrain(String brainPath) throws LexicalException, SyntaxException, FileNotFoundException, IOException {
		File file = new File(brainPath);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<String> wordList = new ArrayList();
		ArrayList<Object> tokenList = new ArrayList();
		String str = bufferedReader.readLine();
		int i = 0;
		Character c;
		try {
			while (str != null) {
				c = str.charAt(i);
				if (c.toString().matches("[A-Z]|[a-z]")) {
					String word = "";
					//keep adding to the string
					while (i + 1 < str.length() && isKeyWord(str.charAt(i + 1))) {
						word += c.toString();
						i += 1;
						c = str.charAt(i);
					}
					i += 1;
					wordList.add(word + c.toString());
				} else if (c.toString().matches("[0-9]")) {
					String word = "";
					//keep adding to the integer
					while (i + 1 < str.length() && isInt(str.charAt(i + 1))) {
						word += c.toString();
						i += 1;
						c = str.charAt(i);
					}
					i += 1;
					wordList.add(word + c.toString());
				} else if (c.toString().matches(";")) {

					str = bufferedReader.readLine();
					i = 0;
				} else if (c.toString().matches("[\\s]")) {
					i += 1;
				} else {
					throw new LexicalException("Invalid token: " + c.toString());
				}
			}
		}catch(IOException e){ //double check if this is right.
			fileReader.close();
		}

		for (String word : wordList) {
			if (word.equals("Sense")) {
				tokenList.add(Action.SENSE);
			} else if (word.equals("Mark")) {
				tokenList.add(Action.MARK);
			} else if (word.equals("Unmark")) {
				tokenList.add(Action.UNMARK);
			} else if (word.equals("PickUp")) {
				tokenList.add(Action.PICKUP);
			} else if (word.equals("Drop")) {
				tokenList.add(Action.DROP);
			} else if (word.equals("Turn")) {
				tokenList.add(Action.TURN);
			} else if (word.equals("Move")) {
				tokenList.add(Action.MOVE);
			} else if (word.equals("Flip")) {
				tokenList.add(Action.FLIP);
			} else if (word.equals("Here")) {
				tokenList.add(sensedir.Here);
			} else if (word.equals("Ahead")) {
				tokenList.add(sensedir.Ahead);
			} else if (word.equals("LeftAhead")) {
				tokenList.add(sensedir.LeftAhead);
			} else if (word.equals("RightAhead")) {
				tokenList.add(sensedir.RightAhead);
			} else if (word.equals("Friend")) {
				tokenList.add(cond.Friend);
			} else if (word.equals("Foe")) {
				tokenList.add(cond.Foe);
			} else if (word.equals("FriendWithFood")) {
				tokenList.add(cond.FriendWithFood);
			} else if (word.equals("FoeWithFood")) {
				tokenList.add(cond.FoeWithFood);
			} else if (word.equals("Food")) {
				tokenList.add(cond.Food);
			} else if (word.equals("Rock")) {
				tokenList.add(cond.Rock);
			} else if (word.equals("Marker")) {
				tokenList.add(cond.Marker);
			} else if (word.equals("Home")) {
				tokenList.add(cond.Home);
			} else if (word.equals("FoeHome")) {
				tokenList.add(cond.FoeHome);
			} else if (word.equals("Left")) {
				tokenList.add(lr.Left);
			} else if (word.equals("Right")) {
				tokenList.add(lr.Right);
			} else if (word.matches("[0-9]+")) {
				tokenList.add(new Integer(word));
			} else {
				throw new LexicalException("Invalid token: " + word);
			}
		}

		//Parsing Stage.
		int x = 0;
		for (int z = 0; z < tokenList.size(); z++) {
			Object token = tokenList.get(z);
			if (tokenList.get(z) instanceof Action) {
				if(token.equals(Action.SENSE)){
					int[] param = new int[4];
					param[0] = recurseSenseDir(tokenList, z);
					z++;
					param[1] = recurseInt(tokenList, z);
					z++;
					param[2] = recurseInt(tokenList, z);
					z++;
					if (tokenList.get(z + 1).equals(cond.Marker)) {
						param[3] = recurseCond(tokenList, z);
						z++;
					} else {
						param[3] = recurseCond(tokenList, z);
					}
					instruction[x] = new Instruction(Action.SENSE, param);
				}
				else if(token.equals(Action.MARK)){
					int[] param1 = new int[2];
					param1[0] = recurseInt(tokenList, z);
					z++;
					param1[1] = recurseInt(tokenList, z);
					instruction[x] = new Instruction(Action.MARK, param1);
				}
				else if(token.equals(Action.UNMARK)){
					int[] param2 = new int[2];
					param2[0] = recurseInt(tokenList, z);
					z++;
					param2[1] = recurseInt(tokenList, z);
					instruction[x] = new Instruction(Action.UNMARK, param2);
				}
				else if(token.equals(Action.PICKUP)){
					int[] param3 = new int[2];
					param3[0] = recurseInt(tokenList, z);
					z++;
					param3[1] = recurseInt(tokenList, z);
					instruction[x] = new Instruction(Action.PICKUP, param3);
				}
				else if(token.equals(Action.DROP)){
					int[] param4 = new int[1];
					param4[0] = recurseInt(tokenList, z);
					instruction[x] = new Instruction(Action.DROP, param4);
				}
				else if(token.equals(Action.TURN)){
					int[] param5 = new int[2];
					param5[0] = recurseLr(tokenList, z);
					z++;
					param5[1] = recurseInt(tokenList, z);
					instruction[x] = new Instruction(Action.TURN, param5);
				}
				else if(token.equals(Action.MOVE)){
					int[] param6 = new int[2];
					param6[0] = recurseInt(tokenList, z);
					z++;
					param6[1] = recurseInt(tokenList, z);
					instruction[x] = new Instruction(Action.MOVE, param6);
				}
				else if(token.equals(Action.FLIP)){
					int[] param7 = new int[3];
					param7[0] = recurseInt(tokenList, z);
					z++;
					param7[1] = recurseInt(tokenList, z);
					z++;
					param7[2] = recurseInt(tokenList, z);
					instruction[x] = new Instruction(Action.FLIP, param7);
				}
				x++;

			} else {
				//throw error is not an Action.
				throw new SyntaxException("Invalid token order: " + token.toString());
			}
		}
	}

	private int recurseSenseDir(ArrayList<Object> tokens, int index) throws SyntaxException {
		int param = 0;
		Object token = tokens.get(index);
		if (token instanceof sensedir) {
			if(token.equals(sensedir.Here)) {
				param =;
			}
			else if(token.equals(sensedir.Ahead)){
				param =;
			}
			else if(token.equals(sensedir.LeftAhead)){
				param =;
			}
			else if(token.equals(sensedir.RightAhead)){
				param =;
			}
		} else {
			//throw error if parameters are faulty.
			throw new SyntaxException("Invalid token order: " + token.toString());

		}
		return param;
	}

	private int recurseCond(ArrayList<Object> tokens, int index) throws SyntaxException {
		int param = 0;
		Object token = tokens.get(index);
		if (token instanceof cond) {
			if(token.equals(cond.Friend)){
				param =; //fill these in.
			}
			else if(token.equals(cond.Foe)){
				param =;
			}
			else if(token.equals(cond.FriendWithFood)){
				param =;
			}
			else if(token.equals(cond.FoeWithFood)){
				param =;
			}
			else if(token.equals(cond.Food)){
				param =;
			}
			else if(token.equals(cond.Rock)){
				param =;
			}
			else if(token.equals(cond.Marker)){
				param = recurseInt(tokens, index+1);
			}
			else if(token.equals(cond.FoeMarker)){
				param =;
			}
			else if(token.equals(cond.Home)){
				param =;
			}
			else if(token.equals(cond.FoeHome)){
				param =;
			}
		}else{
			//throw error
			throw new SyntaxException("Invalid token order: " + token.toString());
		}
		return param;
	}

	private int recurseLr(ArrayList<Object> tokens, int index) throws SyntaxException {
		int param = 0;
		Object token = tokens.get(index);
		if (token instanceof lr) {
			if(token.equals(lr.Left)){
				param = ;
			}
			else if(token.equals(lr.Right)){
				param =;
			}
		} else {
			//throw error.
			throw new SyntaxException("Invalid token order: " + token.toString());
		}
		return param;
	}

	private int recurseInt(ArrayList<Object> tokens, int index) throws SyntaxException {
		int param = 0;
		Object token = tokens.get(index);
		if (token instanceof Integer) {
			param = (int) token;
		}
		else{
			//throw error
			throw new SyntaxException("Invalid token order: " + token.toString());
		}
		return param;
	}

	private boolean isKeyWord(char c) {
		String s = "";
		s += c;
		if (s.matches("[a-z]|[A-Z]")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isInt(char c) {
		String s = "";
		s += c;
		if (s.matches("[0-9]")) {
			return true;
		} else {
			return false;
		}
	}
}

class LexicalException extends Exception {

	LexicalException(String string) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}

class SyntaxException extends Exception {

	SyntaxException(String string) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
