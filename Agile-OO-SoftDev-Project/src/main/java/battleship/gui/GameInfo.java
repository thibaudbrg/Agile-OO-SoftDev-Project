package battleship.gui;
import java.util.LinkedList;
import java.util.Observable;

public class GameInfo extends Observable {

    private LinkedList<String> infos = new LinkedList<String>();

    public GameInfo(String info) {
        addInfo(info);
    }

    public LinkedList<String> getInfos() {
        return infos;
    }

    public void addInfo(String addedInfo) {
        this.infos.add(addedInfo);
        setChanged();
        notifyObservers();
    }
}

