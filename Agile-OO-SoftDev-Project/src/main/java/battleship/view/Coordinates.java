package battleship.view;

import battleship.view.widgets.Sea;

public class Coordinates {

	private int x;
	private int y;
	
	private Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public static Coordinates at(int x, int y) {
		return new Coordinates(clamp(x), clamp(y)); 
	}
	
	public static int clamp(int i) {
		return Math.max(0, Math.min(i, Sea.SIZE));
	}
}
