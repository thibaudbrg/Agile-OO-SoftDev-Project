package battleship.gui;

import java.util.Observable;

public class GameInfo extends Observable {
    private String lastInfo = "" ;
    private String newInfo= "";

    public GameInfo(String newInfo ,String lastInfo) {
        addInfo(lastInfo);
        addInfo(newInfo);
    }

    public String getLastInfo() {
        return lastInfo;
    }

    public String getNewInfo() {
        return newInfo;
    }

    public void addInfo(String addedInfo) {
        this.lastInfo = newInfo;
        this.newInfo= addedInfo;
        setChanged();
        notifyObservers();
    }
}

