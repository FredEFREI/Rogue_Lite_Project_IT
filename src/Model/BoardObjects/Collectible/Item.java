package Model.BoardObjects.Collectible;

import Model.Board;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Collectible.Collectible;
import Model.BoardObjects.ObjType;
import Model.BoardObjects.Player;

import java.awt.*;
import java.util.*;


public abstract class Item extends BoardObject implements Collectible{
    private ArrayList<ObjType> types;
    private String name;
    private String description;

    public Item(String name, String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public int collect() {
        boardX=0;
        boardY=0;
        return 0;
    }

    @Override
    public int use(Player p) {
        return 0;
    }
}