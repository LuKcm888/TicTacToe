package com.maxgames.game;

public class Player {
	
	private String playerNumber= "";
	Set.Marker playerMarker = null;
	
	
	public Player() {
		
	}
	
	public Player(String playerNumber, Set.Marker playerMarker) {
		this.playerNumber = playerNumber;
		this.playerMarker = playerMarker;
		
	}

	public String getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(String playerNumber) {
		this.playerNumber = playerNumber;
	}

	public Set.Marker getPlayerMarker() {
		return playerMarker;
	}

	public void setPlayerMarker(Set.Marker playerMarker) {
		this.playerMarker = playerMarker;
	}

}
