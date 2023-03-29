package battleship;

import battleship.view.widgets.Sea;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Battleship extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Sea sea = new Sea();
		
		BorderPane container = new BorderPane();
		container.setCenter(sea);
		
		Scene s = new Scene(container);
		
		primaryStage.setScene(s);
		primaryStage.setTitle("Battleship - v. 0.1");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
