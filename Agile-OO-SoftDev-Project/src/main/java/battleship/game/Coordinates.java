package battleship.game;

import battleship.gui.widgets.Sea;

public class Coordinates {
    private final int col;
    private final int row;

    public Coordinates(int col, int row) {
        this.col = col;
        this.row = row;
    }


    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }


    @Override
    public String toString() {
        return "Coordinates{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Coordinates)) {
            return false;
        }

        Coordinates other = (Coordinates) obj;
        return this.col == other.col && this.row == other.row;
    }

    public static Coordinates at(int x, int y) {
        return new Coordinates(clamp(x), clamp(y));
    } // TODO CHANGE THIS

    public static int clamp(int i) {
        return Math.max(0, Math.min(i, Sea.SIZE));
    } // TODO CHANGE THIS
}
