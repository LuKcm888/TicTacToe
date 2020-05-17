package com.maxgames.ai;

import java.util.ArrayList;

import com.maxgames.game.Board;
import com.maxgames.game.Set;

public class MiniMaxAI {
	

	
	static class MoveObject {
		int move;
	}

	public static int move(Board gameBoard) {
		
		//System.out.println("Exceuting Smart AI move");
		
		//System.out.println("Creating local copy");
		ArrayList<Set> boardArray = gameBoard.getBoardArray();
		
		//printBoard(boardArray);
		
		int bestVal = -1000; 
		MoveObject bestMove = new MoveObject(); 
	    bestMove.move = -1;
	    
	    for (int i = 0; i < boardArray.size(); i ++) {
	    	
	    	//System.out.println("MiniMaxAI.move(): boardArray at position [" + i +"]"); 
	    	
	    	
	    	if(boardArray.get(i).getLocalMarker() == Set.Marker.EMPTY) {
	    		//System.out.println("MiniMaxAI.move(): boardArray.get("+i+").getLocalMarker(): [" + boardArray.get(i).getLocalMarker() +"]"); 
	    		
	    		boardArray.get(i).setLocalMarker(Set.Marker.X_MARKER);
	    		
	    		boardArray.get(i).setOccupied(true);
	    		
	    		//System.out.println("MiniMaxAI.move(): boardArray.get("+i+").setLocalMarker(): [" + boardArray.get(i).getLocalMarker() +"]"); 
	    		
	    		// compute evaluation function for this 
                // move. 
	    		//System.out.println("MiniMaxAI.move(): About to execute minimax function"); 
                int moveVal = minimax(boardArray, 0, false); 
                
                //System.out.println("MiniMaxAI.move(): moveVal from minimax function is: ["+moveVal+"]"); 
                
                //System.out.println("MiniMaxAI.move(): removing x maker"); 
                boardArray.get(i).setLocalMarker(Set.Marker.EMPTY);
                boardArray.get(i).setOccupied(false);
                
                
                // If the value of the current move is 
                // more than the best value, then update 
                // best/ 
                
                //System.out.println("MiniMaxAI.move(): bestVal is: ["+bestVal+"]"); 
                if (moveVal > bestVal) 
                { 
                	//System.out.println("MiniMaxAI.move(): moveVal is greater than bestVal"); 
                    bestMove.move = i; 
                    bestVal = moveVal; 
                } 
	    	}
	    }
	    
	    //System.out.printf("The value of the best Move " +  "is : %d\n\n", bestVal); 
	    //System.out.println("MiniMaxAI.move(): Exiting method"); 
		return bestMove.move;
	}
	
	 private static int minimax(ArrayList<Set> boardArray, int depth, boolean isMax) { 
		 //System.out.println("MiniMaxAI.minimax(): Entering Method"); 
		 
		 //System.out.println("Current Depth is: ["+depth+"]");
		 
		 int score = evaluateScore(boardArray);

		 //System.out.println("MiniMaxAI.minimax(): score value is ["+score+"]"); 
		 
		 if(score == 10) {
			 //System.out.println("MiniMaxAI.minimax(): Exit for Max, score value is ["+score+"]"); 
			 return score;
		 }
		 
		 if(score == -10) {
			 //System.out.println("MiniMaxAI.minimax(): Exit for Min, score value is ["+score+"]"); 
			 return score;
		 }
		 
		 if (!areMovesLeft(boardArray)) {
			 //System.out.println("MiniMaxAI.minimax(): Exit for a draw, score value is ["+score+"]"); 
			 return 0;
		 }
		 
		 //System.out.println("MiniMaxAI.minimax(): isMax ["+isMax+"]"); 
		 if(isMax) {
			 
			 int bestMove = -1000;
			 //System.out.println("MiniMaxAI.minimax(): bestMove is: ["+bestMove+"]"); 
			 // loop through board
			 for (int i = 0; i < boardArray.size(); i ++) {
				 
				 //System.out.println("MiniMaxAI.minimax(): boardArray position is ["+i+"]"); 
				 // if a tile is empty place player's piece
				 if(!boardArray.get(i).isOccupied()) {
					 boardArray.get(i).setLocalMarker(Set.Marker.X_MARKER);
					 boardArray.get(i).setOccupied(true);
					 
					 
					 //System.out.println("MiniMaxAI.minimax(): boardArray position is not occupied set marker to x"); 
					// Call minimax recursively and choose 
	                    // the maximum value 
					 bestMove = Math.max(bestMove, minimax(boardArray,  depth + 1, !isMax));
					 
					 
					 //undo the move
					 //System.out.println("MiniMaxAI.minimax(): undo set x marker"); 
					 boardArray.get(i).setLocalMarker(Set.Marker.EMPTY);
					 boardArray.get(i).setOccupied(false);
				 }
			 }
			 //System.out.println("MiniMaxAI.minimax(): return the best move for x: ["+bestMove+"]"); 
			 //System.out.println("MiniMaxAI.minimax(): Exiting Method"); 
			 return bestMove;
		 }
		 // minimizer's move
		 else {
			 int bestMove = 1000;
			 //System.out.println("MiniMaxAI.minimax(): bestMove is: ["+bestMove+"]"); 
			 for (int i = 0; i < boardArray.size(); i ++) {
				 
				 //System.out.println("MiniMaxAI.minimax(): boardArray position is ["+i+"]"); 
				 if(!boardArray.get(i).isOccupied()) {
					 boardArray.get(i).setLocalMarker(Set.Marker.O_MARKER);
					 boardArray.get(i).setOccupied(true);
					 
					// System.out.println("MiniMaxAI.minimax(): boardArray position is not occupied set marker to o"); 
					 bestMove = Math.min(bestMove, minimax(boardArray,  depth + 1, isMax)); 
					 
					 //undo the move
					 //System.out.println("MiniMaxAI.minimax(): undo set o marker"); 
					 boardArray.get(i).setLocalMarker(Set.Marker.EMPTY);
					 boardArray.get(i).setOccupied(false);
					 
				 }
				 
			 }
			 
			 //System.out.println("MiniMaxAI.minimax(): return the best move for o: ["+bestMove+"]"); 
			 //System.out.println("MiniMaxAI.minimax(): Exiting Method"); 
			 return bestMove;
			 
			 
		 }
		 

	 }
	 
	 
	 
	 private static int evaluateScore(ArrayList<Set> boardArray) {
		 
		 //printBoard(boardArray);

		 
		 if ((boardArray.get(0).getLocalMarker() == Set.Marker.X_MARKER && 
					boardArray.get(3).getLocalMarker() == Set.Marker.X_MARKER &&
					boardArray.get(6).getLocalMarker() == Set.Marker.X_MARKER) || 
						
					(boardArray.get(1).getLocalMarker() == Set.Marker.X_MARKER && 
					boardArray.get(4).getLocalMarker() == Set.Marker.X_MARKER &&
					boardArray.get(7).getLocalMarker() == Set.Marker.X_MARKER)  ||
					
					(boardArray.get(2).getLocalMarker() == Set.Marker.X_MARKER && 
					boardArray.get(5).getLocalMarker() == Set.Marker.X_MARKER &&
					boardArray.get(8).getLocalMarker() == Set.Marker.X_MARKER) ||
					
					(boardArray.get(0).getLocalMarker() == Set.Marker.X_MARKER && 
					boardArray.get(1).getLocalMarker() == Set.Marker.X_MARKER &&
					boardArray.get(2).getLocalMarker() == Set.Marker.X_MARKER) || 
						
					(boardArray.get(3).getLocalMarker() == Set.Marker.X_MARKER && 
					boardArray.get(4).getLocalMarker() == Set.Marker.X_MARKER &&
					boardArray.get(5).getLocalMarker() == Set.Marker.X_MARKER)  ||
					
					(boardArray.get(6).getLocalMarker() == Set.Marker.X_MARKER && 
					boardArray.get(7).getLocalMarker() == Set.Marker.X_MARKER &&
					boardArray.get(8).getLocalMarker() == Set.Marker.X_MARKER) ||
					
					(boardArray.get(0).getLocalMarker() == Set.Marker.X_MARKER && 
					boardArray.get(4).getLocalMarker() == Set.Marker.X_MARKER &&
					boardArray.get(8).getLocalMarker() == Set.Marker.X_MARKER) || 
						
					(boardArray.get(2).getLocalMarker() == Set.Marker.X_MARKER && 
					boardArray.get(4).getLocalMarker() == Set.Marker.X_MARKER &&
					boardArray.get(6).getLocalMarker() == Set.Marker.X_MARKER)){
						
			 
			 		return 10;
			}
		 else if ((boardArray.get(0).getLocalMarker() == Set.Marker.O_MARKER && 
					boardArray.get(3).getLocalMarker() == Set.Marker.O_MARKER &&
					boardArray.get(6).getLocalMarker() == Set.Marker.O_MARKER) || 
						
					(boardArray.get(1).getLocalMarker() == Set.Marker.O_MARKER && 
					boardArray.get(4).getLocalMarker() == Set.Marker.O_MARKER &&
					boardArray.get(7).getLocalMarker() == Set.Marker.O_MARKER)  ||
					
					(boardArray.get(2).getLocalMarker() == Set.Marker.O_MARKER && 
					boardArray.get(5).getLocalMarker() == Set.Marker.O_MARKER &&
					boardArray.get(8).getLocalMarker() == Set.Marker.O_MARKER) ||
					
					(boardArray.get(0).getLocalMarker() == Set.Marker.O_MARKER && 
					boardArray.get(1).getLocalMarker() == Set.Marker.O_MARKER &&
					boardArray.get(2).getLocalMarker() == Set.Marker.O_MARKER) || 
						
					(boardArray.get(3).getLocalMarker() == Set.Marker.O_MARKER && 
					boardArray.get(4).getLocalMarker() == Set.Marker.O_MARKER &&
					boardArray.get(5).getLocalMarker() == Set.Marker.O_MARKER)  ||
					
					(boardArray.get(6).getLocalMarker() == Set.Marker.O_MARKER && 
					boardArray.get(7).getLocalMarker() == Set.Marker.O_MARKER &&
					boardArray.get(8).getLocalMarker() == Set.Marker.O_MARKER) ||
					
					(boardArray.get(0).getLocalMarker() == Set.Marker.O_MARKER && 
					boardArray.get(4).getLocalMarker() == Set.Marker.O_MARKER &&
					boardArray.get(8).getLocalMarker() == Set.Marker.O_MARKER) || 
						
					(boardArray.get(2).getLocalMarker() == Set.Marker.O_MARKER && 
					boardArray.get(4).getLocalMarker() == Set.Marker.O_MARKER &&
					boardArray.get(6).getLocalMarker() == Set.Marker.O_MARKER) ) {
			 
			 
			 		return -10;
			 
		 }else {
			 return 0;
		 }
					
	 }
	 
	 private static boolean areMovesLeft(ArrayList<Set> boardArray) {
		 
		 //System.out.println("MiniMaxAI.areMovesLeft(): Entering Method"); 
		 
		 boolean areMovesLeft = false;
		 
		 
		 for (int i = 0; i < boardArray.size(); i ++) {
			 if (boardArray.get(0).isOccupied()) {
				 areMovesLeft = true;
				 break;
				 
			 }
		 }

		 //System.out.println("MiniMaxAI.areMovesLeft(): areMovesLeft: ["+areMovesLeft+"]"); 
		 
		 //System.out.println("MiniMaxAI.areMovesLeft(): Exiting Method"); 
		 return areMovesLeft;
	 }
	 
	 
	 
	 private static void printBoard(ArrayList<Set> boardArray) {
		 for (int i = 0; i < boardArray.size(); i ++) {
				Set localSet = boardArray.get(i);
				Set.Marker localMarker = localSet.getLocalMarker();
				
				if (localMarker.equals(Set.Marker.X_MARKER)) {
					
					System.out.print("|-----X-----|");
				}
				else if(localMarker.equals(Set.Marker.O_MARKER)) {
					System.out.print("|-----O-----|");
				}
				else {
					System.out.print("|-----------|");
				}
				
				if(i == 2 || i == 5 || i == 8) {
					System.out.println();
				}
			}
	 }
	

}
