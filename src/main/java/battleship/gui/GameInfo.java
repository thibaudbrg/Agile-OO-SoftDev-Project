package battleship.gui;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.Observable;

public class GameInfo {

    private final LinkedList<String> infos = new LinkedList<>();
    private final ObjectProperty<LinkedList<String>> infoProperty = new SimpleObjectProperty<>();


    public GameInfo(String info) {
        addInfo(info);
    }

    public LinkedList<String> getInfos() {
        return infos;
    }


    public void addInfo(String addedInfo) {
        this.infos.add(addedInfo);
        infoProperty.set(new LinkedList<>(infos));
    }

    public ObjectProperty<LinkedList<String>> infoProperty() {
        return infoProperty;
    }
}

