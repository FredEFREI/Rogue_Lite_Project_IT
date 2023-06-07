package Model.BoardObjects;

import java.awt.*;

public abstract class BoardObject {
    ObjType type;
    protected int boardX=-1;
    protected int boardY=-1;
    protected abstract Dimension getCoordinates();
    protected abstract boolean isOnBoard();
    public abstract ObjType getType();
}
