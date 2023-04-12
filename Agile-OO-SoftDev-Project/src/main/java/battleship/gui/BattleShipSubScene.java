package battleship.gui;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BattleShipSubScene extends SubScene {

    private final static String BACKGROUND = "-fx-background-radius: 20; -fx-background-color: rgba(255, 255, 255, 0.5);";

    private boolean isHidden = true;

    public BattleShipSubScene() {
        super(new AnchorPane(), 600, 400);
        prefWidth(600);
        prefHeight(400);

        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setStyle(BACKGROUND);

        setLayoutX(1024);
        setLayoutY(250);
    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.5));
        transition.setNode(this);
        if (isHidden) {
            transition.setToX(-676);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }
        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }


}
