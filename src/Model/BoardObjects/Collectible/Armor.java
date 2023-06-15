package Model.BoardObjects.Collectible;

import Model.Board;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Collectible.Collectible;
import Model.BoardObjects.Mobs.*;
import Model.BoardObjects.ObjType;
import Model.BoardObjects.Player;

import java.awt.*;

public class Armor extends Item {
    private int value=25;

    public Armor(){
        super("Armor", "Gain armor", ObjType.armor);
        int size= (int) Math.round( Math.random()*3);
        switch (size){
            case 0:
                value=25;
                break;
            case 1:
                value=50;
                break;
            case 2:
                value=100;
                break;
        }
        Dimension d= Board.generateCoordinates(this);
        boardX=d.width;
        boardY=d.height;
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
        int parmor=p.getArmor();
        if(parmor+value<=100)
            p.setArmor(parmor+value);
        else
            p.setArmor(100);
        return 0;
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
        return super.getType();
    }

    @Override
    public String toString() {
        return "Armor ("+value+")";
    }
}
