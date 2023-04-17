package battleship.gui;

import java.util.Objects;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ConfirmEndGame {
    public static boolean confirm() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Ship commander !!");
        alert.setTitle("Not enjoying the game?");
        alert.setContentText("Are you sure you want to quit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

}