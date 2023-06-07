package Model.BoardObjects.Collectible;

import Model.Board;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Collectible.Collectible;
import Model.BoardObjects.ObjType;
import Model.BoardObjects.Player;

import java.awt.*;

public class Armor extends BoardObject implements Collectible {
    ObjType type = ObjType.armor;
    private int size= (int) Math.round( Math.random()*3);

    public Armor(){
        Dimension d= Board.generateCoordinates(this);
        boardX=d.width;
        boardY=d.height;
    }

    @Override
    public int collect() {
        boardX=0;
        boardY=0;
        return 0;
    }

    @Override
    public int use(Player p) {
        int parmor=p.getArmor();
        switch (size) {
            case 0:
                if(parmor+25<=100)
                    p.setArmor(parmor+25);
                else
                    p.setArmor(100);
                break;
            case 1:
                if(parmor+50<=100)
                    p.setArmor(parmor+25);
                else
                    p.setArmor(100);
                break;
            case 2:
                p.setArmor(100);
                break;
        }
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
        return type;
    }
}