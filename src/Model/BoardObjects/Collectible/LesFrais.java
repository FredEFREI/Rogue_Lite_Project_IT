package Model.BoardObjects.Collectible;

import Model.Board;
import Model.BoardObjects.Mobs.Mob;
import Model.BoardObjects.Mobs.Player;
import Model.BoardObjects.ObjType;

import java.awt.*;

public class LesFrais extends Item {

    public LesFrais(){
        super("LesFrais", "Gain 25 armor in exchange of 10 health", ObjType.heal);
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
        if(parmor+25<=100) {
            System.out.println("Health: "+p.getHealth()+" -> "+(p.getHealth()-10));
            System.out.println("Armor: "+p.getArmor()+" -> "+(p.getArmor()+25));
            p.setArmor(parmor + 15);
            p.setHealth(p.getHealth()-10);
        }
        else {
            System.out.println("Health: " + p.getHealth() + " -> " + (p.getHealth() - 10));
            System.out.println("Armor: " + p.getArmor() + " -> 100");
            p.setHealth(p.getHealth() - 10);
            p.setArmor(100);
        }
        if(p.isDead()){
            p.die();
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
        return super.getType();
    }

    @Override
    public String toString() {
        return "LesFrais";
    }
}