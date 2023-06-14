package Model.BoardObjects.Collectible;

import Model.Board;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Collectible.Collectible;
import Model.BoardObjects.Mobs.*;
import Model.BoardObjects.ObjType;
import Model.BoardObjects.Player;

import java.awt.*;
import java.util.*;


public class Item extends BoardObject implements Collectible{
    private ObjType type;
    private String name;
    private String description;

    public Item(String name, String description, ObjType type){
        this.name = name;
        this.description = description;
        this.type = type;
    }

    @Override
    public int collect(Player p) {
        boardX=0;
        boardY=0;
        p.addItem(this);
        return 0;
    }

    @Override
    public int use(Player p, Mob mob) {
        p.removeItem(this);
        return 0;
    }


    @Override
    protected Dimension getCoordinates() {
        return new Dimension(boardX,boardY);
    }

    @Override
    protected boolean isOnBoard() {
        if(boardY>=0 && boardX>=0)
            return true;
        return false;
    }

    @Override
    public ObjType getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}