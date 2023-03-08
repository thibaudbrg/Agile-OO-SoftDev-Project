package battleship.view.widgets;

import battleship.view.Coordinates;
import battleship.view.ShipType;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Sea extends Pane {

	public static final int SIZE = 10;
	private int offsetX = Tile.SIZE / 2;
	private int offsetY = Tile.SIZE / 2;
	
	public Sea() {
		setMinSize(SIZE * Tile.SIZE + offsetX, SIZE * Tile.SIZE + offsetY);
		setMaxSize(SIZE * Tile.SIZE + offsetX, SIZE * Tile.SIZE + offsetY);
		setPrefSize(SIZE * Tile.SIZE + offsetX, SIZE * Tile.SIZE + offsetY);
		setBackground(
				new Background(
						new BackgroundImage(
								new Image(Sea.class.getClassLoader().getResourceAsStream("assets/background.png")),
								BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT,
								new BackgroundPosition(Side.RIGHT, 0, true, Side.BOTTOM, 0, true),
								BackgroundSize.DEFAULT)));
		loadSea();
		
		setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if (event.getTarget() instanceof Tile) {
					Tile t = (Tile) event.getTarget();
					addShip(new Ship(ShipType.random()), t.getCoordinates());
				}
			};
		});
	}
	
	public void addShip(Ship ship, Coordinates coordinates) {
		ship.relocate(coordinates.x() * Tile.SIZE + offsetX, coordinates.y() * Tile.SIZE + offsetY);
		getChildren().add(ship);
	}
	
	public void removeShip(Ship ship) {
		getChildren().remove(ship);
	}
	
	private void loadSea() {
		
		for (int j = 0; j < SIZE; j++) {
			Text text = new Text(String.valueOf(j + 1));
			text.setFill(Color.BLUE);
			text.setFont(Font.font(16));
			text.relocate(
					(Tile.SIZE / 2) - text.getLayoutBounds().getWidth() - 3,
					((j + 1) * Tile.SIZE) - (text.getLayoutBounds().getHeight() / 2));
			getChildren().add(text);
			
			for (int i = 0; i < SIZE; i++) {
				if (j == 0) {
					Text text2 = new Text(Character.valueOf((char) (i + 65)).toString());
					text2.setFill(Color.BLUE);
					text2.setFont(Font.font(16));
					text2.relocate((i + 1) * Tile.SIZE, 0);
					getChildren().add(text2);
				}
				
				Tile t = new Tile(Coordinates.at(i, j));
				t.relocate(offsetX + (Tile.SIZE * i), offsetY + (Tile.SIZE * j));
				getChildren().add(t);
			}
		}
	}
}
