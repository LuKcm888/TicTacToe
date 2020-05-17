package com.maxgames.game;

public class Set {
	
	public enum Marker {
		X_MARKER,
		O_MARKER,
		EMPTY
	}
	
	private boolean isOccupied = false;
	private Marker localMarker = Marker.EMPTY;
	private int number = 0;

	
	public Set() {

	}
	
	public Set(int number) {
		this.number = number;
	}


	public boolean isOccupied() {
		return isOccupied;
	}


	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}


	public Marker getLocalMarker() {
		return localMarker;
	}


	public void setLocalMarker(Marker localMarker) {
		this.localMarker = localMarker;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}
	
	
	

}
