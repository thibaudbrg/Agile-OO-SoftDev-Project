package battleship.view;

import java.util.Random;

import javafx.scene.image.Image;

public enum ShipType {

	CARRIER(5, "assets/carrier.png"),
	BATTLESHIP(4, "assets/battleship.png"),
	CRUISER(3, "assets/cruiser.png"),
	SUBMARINE(3, "assets/submarine.png"),
	DESTROYER(2, "assets/destroyer.png");
	
	private ShipType(int size, String assetName) {
		this.size = size;
		this.image = new Image(ShipType.class.getClassLoader().getResourceAsStream(assetName));
	}
	
	private static final Random random = new Random();
	private int size;
	private Image image;
	
	public int size() {
		return size;
	}
	
	public Image image() {
		return image;
	}
	
	public static ShipType random() {
		return values()[random.nextInt(values().length)];
	}
}
