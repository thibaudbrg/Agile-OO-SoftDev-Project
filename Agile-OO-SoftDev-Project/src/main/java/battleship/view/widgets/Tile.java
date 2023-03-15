package battleship.view.widgets;

import java.util.Objects;

import battleship.view.Coordinates;
import battleship.view.IdGenerator;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tile extends Canvas {

	public static final int SIZE = 50;
	
	private int id;
	private boolean mouseOver;
	private Coordinates position;
	
	public Tile(Coordinates position) {
		super(SIZE, SIZE);
		this.id = IdGenerator.get();
		this.position = position;
		this.mouseOver = false;
		
		redraw();
		
		setOnMouseEntered(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				setMouseOver(true);
			}
		});
		setOnMouseExited(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				setMouseOver(false);
			}
		});
	}
	
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
		redraw();
	}
	
	public Coordinates getCoordinates() {
		return position;
	}
	
	public void redraw() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, SIZE, SIZE);
		
		gc.setStroke(Color.WHITE);
		gc.setLineWidth(1);
		gc.strokeRect(0, 0, SIZE, SIZE);

		if (mouseOver) {
			gc.setStroke(Color.RED);
			gc.setLineWidth(3);
			gc.strokeRoundRect(3, 3, SIZE - 6, SIZE - 6, 10, 10);
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tile) {
			return this.id == ((Tile) obj).id;
		}
		return false;
	}
}
