package Model.BoardObjects;

import Model.Board;
import Model.BoardObjects.Collectible.Collectible;

import java.awt.*;

/**
 * Classe représentant un mur
 */
public class Wall extends BoardObject{
    public Wall(){
        type = ObjType.wall;
        Dimension d= Board.generateCoordinates(this);
        boardX=d.width;
        boardY=d.height;
    }
    public Wall(Dimension coordinates){
        type = ObjType.wall;
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

    public void deleteWall() {
        boardX = -1;
        boardY = -1;
    }
}
