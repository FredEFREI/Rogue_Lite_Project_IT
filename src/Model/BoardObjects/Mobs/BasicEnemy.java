package Model.BoardObjects.Mobs;

import Model.Board;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Collectible.Item;
import Model.BoardObjects.ObjType;
import Model.BoardObjects.Player;

import java.awt.*;
import java.util.ArrayList;

public class BasicEnemy extends BoardObject implements Mob{

    public BasicEnemy(){
        type = ObjType.enemy;
        Dimension d = Board.generateCoordinates(this);
        boardX = d.width;
        boardY = d.height;
    }

    public BasicEnemy(Dimension coordinates){
        type = ObjType.enemy;
        boardX=coordinates.width;
        boardY=coordinates.height;
    }

    @Override
    public boolean isOnBoard() {
        if(boardY>=0 && boardX>=0)
            return true;
        return false;
    }

    public void attack(Mob m) {
        int damages = (int) Math.round(Math.random() * 50);
    }

    public ArrayList<Item> die() {
        return null;
    }

    public void setCoordiantes(Dimension c) {

    }

    @Override
    public ObjType getType() {
        return type;
    }

    @Override
    protected Dimension getCoordinates() {
        return new Dimension(boardX,boardY);
    }
}
