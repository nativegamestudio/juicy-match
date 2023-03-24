package com.nativegame.match3game.game.layer.grid;

public class GridInitializer {

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public static GridType getType(char c) {
        switch (c) {
            case 'n':
                return GridType.CENTER;
            case 'u':
                return GridType.TOP;
            case 'd':
                return GridType.DOWN;
            case 'l':
                return GridType.LEFT;
            case 'r':
                return GridType.RIGHT;
            case 'q':
                return GridType.TOP_LEFT;
            case 'w':
                return GridType.TOP_RIGHT;
            case 'a':
                return GridType.DOWN_LEFT;
            case 's':
                return GridType.DOWN_RIGHT;
            default:
                throw new IllegalArgumentException("GridType not found!");
        }
    }
    //========================================================

}
