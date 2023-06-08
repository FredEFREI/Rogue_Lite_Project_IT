package Model.BoardObjects;

import Model.Board;
import Model.BoardObjects.Collectible.Collectible;

import java.awt.*;

/**
 * Classe représentant une case vide sur le plateau de jeu
 */
public class Empty extends BoardObject {

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
