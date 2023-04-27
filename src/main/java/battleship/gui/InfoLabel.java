package battleship.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;


public class InfoLabel extends Label {
    //public final static String FONT_SPECIFIER = "-fx-font-family: 'KenVector'; -fx-font-size: 20px;";
    private final static String BACKGROUND = "-fx-background-radius: 20; -fx-background-color: rgba(60, 60, 60, 0.5);";


    public InfoLabel(String text) {
        setPrefWidth(200);
        setHeight(15);
        setPadding(new Insets(10, 40, 40, 50));
        setText(text);
        setWrapText(true);
        //setStyle(FONT_SPECIFIER);
        setStyle(BACKGROUND);

    }
}