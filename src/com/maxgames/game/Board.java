package com.maxgames.game;
import java.util.ArrayList;


public class Board {
	
	private ArrayList<Set> boardArray;
	
	private final String EMPTY = "EMPTY";
	private final String X_MARKER = "X_MARKER";
	private final String O_MARKER = "O_MARKER";
	
	
	public Board() {
		
		boardArray = new ArrayList<Set>();
		
		Set set1 = new Set(0);
		Set set2 = new Set(1);
		Set set3 = new Set(2);
		Set set4 = new Set(3);
		Set set5 = new Set(4);
		Set set6 = new Set(5);
		Set set7 = new Set(6);
		Set set8 = new Set(7);
		Set set9 = new Set(8);
		
		boardArray.add(set1);
		boardArray.add(set2);
		boardArray.add(set3);
		boardArray.add(set4);
		boardArray.add(set5);
		boardArray.add(set6);
		boardArray.add(set7);
		boardArray.add(set8);
		boardArray.add(set9);
		
	}
	
	public void printBoard() {	
		for (int i = 0; i < this.boardArray.size(); i ++) {
			Set localSet = this.boardArray.get(i);
			Set.Marker localMarker = localSet.getLocalMarker();
			
			if (localMarker.toString().equals(X_MARKER)) {
				
				System.out.print("|-----X-----|");
			}
			else if(localMarker.toString().equals(O_MARKER)) {
				System.out.print("|-----O-----|");
			}
			else {
				System.out.print("|-----------|");
			}
			
			if(i == 2 || i == 5 || i == 8) {
				System.out.println();
			}
		}
	}// end printBoard
	
	/**
		updates the board with the player position 
	*/
	public boolean updateBoard(Player player, int position) {
		
		boolean updatedSuccessfully = false;
		
		for (int i = 0; i < this.boardArray.size(); i ++) {
			if(position == i) {
				if (!this.boardArray.get(i).isOccupied()) {
					this.boardArray.get(i).setLocalMarker(player.getPlayerMarker());
					this.boardArray.get(i).setOccupied(true);
					updatedSuccessfully = true;
				}
			}
		}
		
		return updatedSuccessfully;
	}// end updateBoard
	

	public ArrayList<Set> getBoardArray() {
		return boardArray;
	}

	public void setBoardArray(ArrayList<Set> boardArray) {
		this.boardArray = boardArray;
	}
	
	
	
	

}
