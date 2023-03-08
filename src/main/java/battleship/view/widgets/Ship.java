package battleship.view.widgets;

import java.util.Objects;

import battleship.view.IdGenerator;
import battleship.view.ShipType;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;

public class Ship extends Canvas {

	private int id;
	private ShipType type;
	private boolean mouseOver;
	
	public Ship(ShipType type) {
		super(Tile.SIZE, type.size() * Tile.SIZE);
		this.id = IdGenerator.get();
		this.type = type;
		
		redraw();
		
		setOnMouseEntered(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				mouseOver = true;
				redraw();
			}
		});
		setOnMouseExited(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				mouseOver = false;
				redraw();
			}
		});
		setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				destroy();
			}
		});
	}
	
	private void destroy() {
		if (getParent() instanceof Sea) {
			((Sea) getParent()).removeShip(this);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ship) {
			return this.id == ((Ship) obj).id;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	private void redraw() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());
		
		
		if (mouseOver) {
			gc.save();
			ColorAdjust colorAdjust = new ColorAdjust();
		    colorAdjust.setBrightness(0.5);
		    gc.setEffect(colorAdjust);
		}
		gc.drawImage(type.image(), (Tile.SIZE - type.image().getWidth()) / 2, 0);
		if (mouseOver) {
			gc.restore();
		}
	}
}
