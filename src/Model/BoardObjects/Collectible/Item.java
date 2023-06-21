package Model.BoardObjects.Collectible;

import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Mobs.*;
import Model.BoardObjects.ObjType;
import Model.BoardObjects.Mobs.Player;

import java.awt.*;


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
        boardX=-1;
        boardY=-1;
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

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getBoardX(){return boardX;}
    public int getBoardY(){return boardY;}
}