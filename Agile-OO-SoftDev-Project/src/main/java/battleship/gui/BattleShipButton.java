package battleship.gui;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class BattleShipButton extends Button {

    private static final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent;-fx-background-image: url('ImagesFromSpaceRunner/rocketButton.png');-fx-background-size: 100%;";
    private static final String BUTTON_FREE_STYLE = "-fx-background-color: transparent;-fx-background-image: url('ImagesFromSpaceRunner/rocketButton.png');-fx-background-size: 100%;";

    public BattleShipButton(String text) {
        setStyle(BUTTON_FREE_STYLE);
        setPrefSize(1,1);
        setBackground(getBackground());
        setText(text);
        setPrefWidth(190);
        setPrefHeight(49);
        initializeButtonListeners();
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() + 4);

    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners() {
        setOnMousePressed(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
            }
        });
        setOnMouseReleased(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonReleasedStyle();
            }
        });
        setOnMouseEntered(event -> setEffect(new DropShadow(50, Color.YELLOW)));
        setOnMouseExited(event -> setEffect(null));
    }
}
