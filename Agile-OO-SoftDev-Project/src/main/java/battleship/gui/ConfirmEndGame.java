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
        if (!Objects.equals(alert.showAndWait(), Optional.of(ButtonType.CANCEL))) {
            Optional<ButtonType> obj = alert.showAndWait();
            if (Objects.equals(obj, Optional.of(ButtonType.OK))) {
                return true;
            } else return !Objects.equals(obj, Optional.of(ButtonType.CANCEL));
        }
        return false;
    }
}