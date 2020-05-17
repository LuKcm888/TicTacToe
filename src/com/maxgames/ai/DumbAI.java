package com.maxgames.ai;

import java.util.Random;

import com.maxgames.constants.Constants;
import com.maxgames.game.Board;
import com.maxgames.game.Set;

/**
 * 
 * @author maxwellmackoul
 *
 * This AI randomly chooses spots on the board
 */

public class DumbAI extends AIPlayer {
	

	public DumbAI(Board gameBoard) {
		super(gameBoard);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int move() {
		
		Random rand = new Random();
		
		int randomNumber = rand.nextInt(Constants.BOARD_SIZE);
		
		System.out.println("AI chose number:  ["+randomNumber+"]");
		
		int numberToReturn = 256;
		
		for (Set tile : boardArray) {
			if(tile.getNumber() == randomNumber ) {
				System.out.println("Found number");
				if(!tile.isOccupied()) {
	
					System.out.println("Tile is not occupied at ["+tile.getNumber()+"]");
					numberToReturn = randomNumber;
					break;
					
				}
				else {
					System.out.println("The space the ai chose was occupied at[" +tile.getNumber()+"]");
				}
				
			}
			
		}
		return numberToReturn;
	}

}
