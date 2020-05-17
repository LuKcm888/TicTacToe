package com.maxgames.ai;

import java.util.ArrayList;

import com.maxgames.game.Board;
import com.maxgames.game.Set;

/**
 * 
 * From https://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html
 * 
 * Abstract superclass for all AI players with different strategies.
 * To construct an AI player:
 * 1. Construct an instance (of its subclass) with the game Board
 * 2. Call setSeed() to set the computer's seed
 * 3. Call move() which returns the next move in an int[2] array of {row, col}.
 *
 * The implementation subclasses need to override abstract method move().
 * They shall not modify Cell[][], i.e., no side effect expected.
 * Assume that next move is available, i.e., not game-over yet.
 */
public abstract class AIPlayer {

	protected static ArrayList<Set> boardArray;
	
	/** Constructor with reference to game board */
	public AIPlayer(Board gameBoard) {

		boardArray = gameBoard.getBoardArray();
		
	}

	abstract int move(); 
	
	
}
