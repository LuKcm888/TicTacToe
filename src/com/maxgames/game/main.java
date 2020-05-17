package com.maxgames.game;
import java.util.ArrayList;
import java.util.Scanner;
import com.maxgames.ai.DumbAI;
import com.maxgames.ai.MiniMaxAI;

public class main {
	
	
	private static boolean isVictory = false;
	
	private static boolean performedGameSetup = false;
	
	private static boolean running = true;
	
	
	private static Scanner scanner = new Scanner(System.in);
	
	private static Player player1 = new Player("player1", Set.Marker.EMPTY);
	private static Player player2 = new Player("player2", Set.Marker.EMPTY);
	
	private static boolean aiCompletedMove = true;
	private static boolean aiNeedsToCompleteMove = false;
	
	private static Board gameBoard = new Board();
	
	private static String dummy = "Dummy";
	private enum GameState {
		SINGLEPLAYER,
		MULTIPLAYER
	}
	
	private static int player1Move = 0;
	
	public static void main (String [] args) {
		
		GameState gameState = GameState.SINGLEPLAYER;

		DumbAI ai = null;
		MiniMaxAI smartAI = null;
		
		while (running) {
			
			boolean playerCompletedMove = false;

			
			if(!performedGameSetup) {
				
				int choice = 0;
				System.out.println("Welcome to Tic Tac Toe. Please choose SinglePlayer or Multiplayer");
				System.out.println("Type 1 for singleplayer, 2 for multiplayer, or 3 to exit");
				
				choice = scanner.nextInt();
				
				if(choice == 1) {
					performedGameSetup = true;

				}
				else if (choice == 2) {
					gameState = GameState.MULTIPLAYER;
					performedGameSetup = true;
				}
				else if (choice == 3) {
					performedGameSetup = true;
					System.out.println("GoodBye!");
					System.exit(0);;
				}
				else {
					System.out.println("Invalid Entry");
				}
				
				System.out.println("Please select 1 for X or 2 for O");
				
				choice = scanner.nextInt();
				
				if(choice == 1) {
					player1.setPlayerMarker(Set.Marker.X_MARKER);
					player2.setPlayerMarker(Set.Marker.O_MARKER);
				}
				else if (choice == 2) {
					player1.setPlayerMarker(Set.Marker.O_MARKER);
					player2.setPlayerMarker(Set.Marker.X_MARKER);
				}
				else {
					System.out.println("Invalid Choice");
				}
			}
			
			if (aiCompletedMove) {
				playerCompletedMove = executePlayerMove (gameBoard);
			}
			
			if(playerCompletedMove) {
				// execute game ai
				
				System.out.println("Player completed move, ai's turn");
				
				aiCompletedMove = excuteAIMove(gameBoard, ai);
				
				
			}
			else {
				if (aiNeedsToCompleteMove) {
					System.out.println("AI needs to complete move");
					aiCompletedMove = excuteAIMove(gameBoard, ai);
				} else {
					System.out.println("Player failed to complete move");
				}
			}
			
			
			running = checkVictory(gameBoard);
			
			if(!running) {
				gameBoard.printBoard();
				System.out.println("GAME OVER!!!");
			}
			
			
			
		}
	} // end main loop
	
	
	private static boolean executePlayerMove (Board gameBoard) {
		
		boolean moveCompleted = false;
		
		gameBoard.printBoard();
		
		System.out.println("Please enter your move");
		
		player1Move = scanner.nextInt();
		
		boolean isBoardUpdated = gameBoard.updateBoard(player1, player1Move);
		
		if(isBoardUpdated) {
		
			System.out.println("Board updated at position ["+player1Move + "]" );
			moveCompleted = true;
			gameBoard.printBoard();
		}
		else {
			System.out.println("Space ["+player1Move + "] is occupied, please choose another space" );

		}
		
		return moveCompleted;
		
	}
	
	private static boolean excuteAIMove(Board gameBoard, DumbAI ai) {
		
		boolean isUpdated = false;
		
		int aiNumber = 0;

		//ai = new DumbAI(gameBoard);
		aiNumber = MiniMaxAI.move(gameBoard);
		if (aiNumber == 256) {
			System.out.println("AI failed to calculate a valid move");
			aiNeedsToCompleteMove = true;
			
		}else {
			System.out.println("AI made good move");
			boolean isBoardUpdated = gameBoard.updateBoard(player2, aiNumber);
			if(isBoardUpdated) {
				System.out.println("Board updated at position ["+aiNumber + "] for AI Move" );
				isUpdated = true;
			}
			else {
				System.out.println("Space ["+aiNumber + "] is occupied, please choose another space" );

			}
			
		}
		return isUpdated;
	}
	
	
	
	
	private static boolean checkVictory(Board gameBoard) {
		
		//0 3 6  
		//1 4 7
		//2 5 8
		
		//0 1 2
		//3 4 5
		//6 7 8
		
		//0 4 8
		//2 4 6
		
		ArrayList<Set> boardArray = gameBoard.getBoardArray();

		
		
		boolean continueGame = true;
		
		if(
			
			(boardArray.get(0).getLocalMarker() == Set.Marker.X_MARKER && 
			boardArray.get(3).getLocalMarker() == Set.Marker.X_MARKER &&
			boardArray.get(6).getLocalMarker() == Set.Marker.X_MARKER) || 
				
			(boardArray.get(1).getLocalMarker() == Set.Marker.X_MARKER && 
			boardArray.get(4).getLocalMarker() == Set.Marker.X_MARKER &&
			boardArray.get(7).getLocalMarker() == Set.Marker.X_MARKER)  ||
			
			(boardArray.get(2).getLocalMarker() == Set.Marker.X_MARKER && 
			boardArray.get(5).getLocalMarker() == Set.Marker.X_MARKER &&
			boardArray.get(8).getLocalMarker() == Set.Marker.X_MARKER)	||
			
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
			boardArray.get(6).getLocalMarker() == Set.Marker.X_MARKER) ||
			
			
			
			(boardArray.get(0).getLocalMarker() == Set.Marker.O_MARKER && 
			boardArray.get(3).getLocalMarker() == Set.Marker.O_MARKER &&
			boardArray.get(6).getLocalMarker() == Set.Marker.O_MARKER) || 
				
			(boardArray.get(1).getLocalMarker() == Set.Marker.O_MARKER && 
			boardArray.get(4).getLocalMarker() == Set.Marker.O_MARKER &&
			boardArray.get(7).getLocalMarker() == Set.Marker.O_MARKER)  ||
			
			(boardArray.get(2).getLocalMarker() == Set.Marker.O_MARKER && 
			boardArray.get(5).getLocalMarker() == Set.Marker.O_MARKER &&
			boardArray.get(8).getLocalMarker() == Set.Marker.O_MARKER)	||
			
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
			boardArray.get(6).getLocalMarker() == Set.Marker.O_MARKER) 
				
		) {
			
			System.out.println("VICTORY!!!!");
			continueGame = false;
		} else if(
				boardArray.get(0).isOccupied() == true && 
				boardArray.get(1).isOccupied() == true &&
				boardArray.get(2).isOccupied() == true && 
					
				boardArray.get(3).isOccupied() == true && 
				boardArray.get(4).isOccupied() == true &&
				boardArray.get(5).isOccupied() == true &&
				
				boardArray.get(6).isOccupied() == true && 
				boardArray.get(7).isOccupied() == true &&
				boardArray.get(8).isOccupied() == true
				
		) {
			
			System.out.println("Board is full, its a draw!!!");
			continueGame = false;
		}
		
		return continueGame;
		
	}

}
