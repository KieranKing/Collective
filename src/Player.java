import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;


/**
 * The Player class creates a Player object with a unique id, a name, a list of ant
 * brain instructions, and a colour by which the player identifies himself/herself in the match.
 *
 * @author Ralph
 */
public class Player {

	private int id;
	private String name;
	private Colour colour;
	private Instruction[] instruction;

	/**
	 * Represents the sensedir part of the antbrain CFG.
	 *
	 */
	private enum sensedir {

		Here, Ahead, LeftAhead, RightAhead
	};

	/**
	 * Represents the cond part of the ant brain CFG.
	 *
	 */
	private enum cond {

		Friend, Foe, FriendWithFood, FoeWithFood, Food, Rock, Marker, FoeMarker, Home, FoeHome
	};

	/**
	 * Represents the lr part of the ant brain CFG.
	 *
	 */
	private enum lr {

		Left, Right
	};


	/**
	 * Generates a Player object with a name, an id, a list of instructin generated from the specified brain file and a
	 * colour.
	 *
	 * @param id The id required to uniquely identify the player.
	 * @param name The name of the player.
	 * @param brainPath The path to the player's ant brain file.
	 */
	Player(int id, String name, String brainPath) throws LexicalException, SyntaxException, FileNotFoundException, IOException {
		this.id = id;
		this.name = name;
		colour = null;
		instruction = loadBrain(brainPath);
	}

	/**
	 * Gets the player's instruction array.
	 *
	 * @return Returns the players brain instructions array.
	 */
	public Instruction[] getInstructions(){
		return instruction;
	}
	/**
	 * Gets the player's unique id.
	 *
	 * @return Returns the players unique id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the player's name.
	 *
	 * @return Returns the player's name.
	 */
	public String getName() {
		return name;
	}


	/**
	 * Gets the colour that represents the player in a match.
	 *
	 * @return Returns the colour that represents the player in a match.
	 */
	public Colour getColour() {
		return colour;
	}


	/**
	 * Sets the player's colour to the given colour.
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
	 * @param state The current ant brain state as an integer.
	 * @return Retruns the next instruction to be given to the ant.
	 */
	public Instruction getNextInstruction(int state) {
		return null;
	}

	/**
	 * Generates a list of instructions from the specified brain file and returns the list.
	 *
	 * @param brainPath The file path leading to the ant brain file.
	 * @return Returns an array of Instruction objects.
	 */
	private Instruction[] loadBrain(String brainPath) throws LexicalException, SyntaxException, FileNotFoundException, IOException {
		File file = new File(brainPath);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<String> wordList = new ArrayList();
		ArrayList<Object> tokenList = new ArrayList();
		String str = bufferedReader.readLine();
		int i = 0;
		Character c;

		// Lexing of the ant brain.
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
		}catch(IOException e){
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
				throw new LexicalException("Invalid token: " + word.toString());
			}
		}

		//Parsing of the antbrain.
		int x = 0;
		Instruction[] instructions = new Instruction[tokenlist.size()];
		for (int z = 0; z < tokenList.size(); z++) {
			Object token = tokenList.get(z);
			if (tokenList.get(z) instanceof Enum<Action>) {
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
					instructions[x] = new Instruction(Action.SENSE, param);
				}
				else if(token.equals(Action.MARK)){
					int[] param1 = new int[2];
					param1[0] = recurseInt(tokenList, z);
					z++;
					param1[1] = recurseInt(tokenList, z);
					instructions[x] = new Instruction(Action.MARK, param1);
				}
				else if(token.equals(Action.UNMARK)){
					int[] param2 = new int[2];
					param2[0] = recurseInt(tokenList, z);
					z++;
					param2[1] = recurseInt(tokenList, z);
					instructions[x] = new Instruction(Action.UNMARK, param2);
				}
				else if(token.equals(Action.PICKUP)){
					int[] param3 = new int[2];
					param3[0] = recurseInt(tokenList, z);
					z++;
					param3[1] = recurseInt(tokenList, z);
					instructions[x] = new Instruction(Action.PICKUP, param3);
				}
				else if(token.equals(Action.DROP)){
					int[] param4 = new int[1];
					param4[0] = recurseInt(tokenList, z);
					instructions[x] = new Instruction(Action.DROP, param4);
				}
				else if(token.equals(Action.TURN)){
					int[] param5 = new int[2];
					param5[0] = recurseLr(tokenList, z);
					z++;
					param5[1] = recurseInt(tokenList, z);
					instructions[x] = new Instruction(Action.TURN, param5);
				}
				else if(token.equals(Action.MOVE)){
					int[] param6 = new int[2];
					param6[0] = recurseInt(tokenList, z);
					z++;
					param6[1] = recurseInt(tokenList, z);
					instructions[x] = new Instruction(Action.MOVE, param6);
				}
				else if(token.equals(Action.FLIP)){
					int[] param7 = new int[3];
					param7[0] = recurseInt(tokenList, z);
					z++;
					param7[1] = recurseInt(tokenList, z);
					z++;
					param7[2] = recurseInt(tokenList, z);
					instructions[x] = new Instruction(Action.FLIP, param7);
				}
				x++;

			} else {
				//throw error if not an Action/ syntax is faulty.
				throw new SyntaxException("Invalid token order: " + token.toString());
			}
		}

		return instructions;
	}

	/**
	 * Checks whether the specified token is an enum in the dir class, returns the required integer or throws a syntax error.
	 *
	 * @param tokens The token to be checked.
	 * @param index The index of the token in tokens to be checked.
	 * @return Returns an integer for the parameter of the instruction being processed or an error if invalid syntax.
	 */
	private int recurseSenseDir(ArrayList<Object> tokens, int index) throws SyntaxException {
		int param = 0;
		Object token = tokens.get(index);
		if (token instanceof Enum<sensedir>) {
			if(token.equals(sensedir.Here)) {
				param = 0;
			}
			else if(token.equals(sensedir.Ahead)){
				param = 1;
			}
			else if(token.equals(sensedir.LeftAhead)){
				param = 2;
			}
			else if(token.equals(sensedir.RightAhead)){
				param = 3;
			}
		} else {
			//throw error if syntax is faulty.
			throw new SyntaxException("Invalid token order: " + token.toString());

		}
		return param;
	}

	/**
	 * Checks whether the specified token is an enum in the cond class, returns the required integer or throws a syntax error.
	 *
	 * @param tokens The token to be checked.
	 * @param index The index of the token in tokens to be checked.
	 * @return Returns an integer for the parameter of the instruction being processed or an error if invalid syntax.
	 */
	private int recurseCond(ArrayList<Object> tokens, int index) throws SyntaxException {
		int param = 0;
		Object token = tokens.get(index);
		if (token instanceof Enum<cond>) {
			if(token.equals(cond.Friend)){
				param = 0;
			}
			else if(token.equals(cond.Foe)){
				param = 1;
			}
			else if(token.equals(cond.FriendWithFood)){
				param = 2;
			}
			else if(token.equals(cond.FoeWithFood)){
				param = 3;
			}
			else if(token.equals(cond.Food)){
				param = 4;
			}
			else if(token.equals(cond.Rock)){
				param = 5;
			}
			else if(token.equals(cond.Marker)){
				param = recurseInt(tokens, index+1);
			}
			else if(token.equals(cond.FoeMarker)){
				param = 7;
			}
			else if(token.equals(cond.Home)){
				param = 8;
			}
			else if(token.equals(cond.FoeHome)){
				param = 9;
			}
		}else{
			//throw error if syntax is faulty.
			throw new SyntaxException("Invalid token order: " + token.toString());
		}
		return param;
	}

	/**
	 * Checks whether the specified token is an enum in the lr class, returns the required integer or throws a syntax error.
	 *
	 * @param tokens The token to be checked.
	 * @param index The index of the token in tokens to be checked.
	 * @return Returns an integer for the parameter of the instruction being processed or an error if invalid syntax.
	 */
	private int recurseLr(ArrayList<Object> tokens, int index) throws SyntaxException {
		int param = 0;
		Object token = tokens.get(index);
		if (token instanceof Enum<lr>) {
			if(token.equals(lr.Left)){
				param = 0;
			}
			else if(token.equals(lr.Right)){
				param = 1;
			}
		} else {
			//throw error if syntax is faulty.
			throw new SyntaxException("Invalid token order: " + token.toString());
		}
		return param;
	}

	/**
	 * Checks whether the specified token is an integer, returns integer else throws syntax error.
	 *
	 * @param tokens The list of tokens from which a specific token is to be checked.
	 * @param index The index of the token in tokens representing an integer.
	 * @return Returns an integer for the parameter of the instruction being processed or an error if invalid syntax.
	 */
	private int recurseInt(ArrayList<Object> tokens, int index) throws SyntaxException {
		int param = 0;
		Object token = tokens.get(index);
		if (token instanceof Integer) {
			param = (Integer) token;
		}
		else{
			//throw error if syntax is faulty.
			throw new SyntaxException("Invalid token order: " + token.toString());
		}
		return param;
	}

	/**
	 * Checks whether the specified character is part of a keyword.
	 *
	 * @param c The character to be check whether it is part of a keyword or not.
	 * @return Returns true if the specified character is part of a keyword else false.
	 */
	private boolean isKeyWord(char c) {
		String s = "";
		s = c.toString();
		if (s.matches("[a-z]|[A-Z]")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks whether the specified character is an integer.
	 *
	 * @param c The character to be check whether it is an integer or not.
	 * @return Returns true if the specified character is an integer else false.
	 */
	private boolean isInt(char c) {
		String s = "";
		s += c.toString();
		if (s.matches("[0-9]")) {
			return true;
		} else {
			return false;
		}
	}
}
