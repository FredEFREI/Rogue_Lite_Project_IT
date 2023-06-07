package Model.BoardObjects;

import Model.Board;
import Model.BoardObjects.Collectible.Collectible;

import java.awt.*;

public class Wall extends BoardObject{
    ObjType type = ObjType.wall;
    public Wall(){
        Dimension d= Board.generateCoordinates(this);
        boardX=d.width;
        boardY=d.height;
    }
    public Wall(Dimension coordinates){
        boardX=coordinates.width;
        boardY=coordinates.height;
    }

    @Override
    public Dimension getCoordinates() {
        return new Dimension(boardX,boardY);
    }

    @Override
    public boolean isOnBoard() {
        if(boardY>=0 && boardX>=0)
            return true;
        return false;
    }

    @Override
    public ObjType getType() {
        return type;
    }
}
