package Model.BoardObjects;

import Model.Board;
import Model.BoardObjects.Collectible.Collectible;

import java.awt.*;

public class Empty extends BoardObject implements Collectible {
    ObjType type = ObjType.empty;

    public Empty(){
        Dimension d= Board.generateCoordinates(this);
        boardX=d.width;
        boardY=d.height;
    }
    public Empty(Dimension coordinates){
        boardX=coordinates.width;
        boardY=coordinates.height;
    }

    @Override
    public int collect() {
        return 1;
    }

    @Override
    public int use(Player p) {
        return 1;
    }

    public Dimension getCoordinates() {
        return new Dimension(boardX,boardY);
    }

    public boolean isOnBoard() {
        return true;
    }

    public ObjType getType() {
        return type;
    }
}
