package Model.BoardObjects.Collectible;

import Model.Board;
import Model.BoardObjects.Mobs.Mob;
import Model.BoardObjects.ObjType;
import Model.BoardObjects.Mobs.Player;
import Vue.ConsoleWriter;

import java.awt.*;

public class Steroids extends Item {
    private int value=25;

    public Steroids(){
        super("Steroids", "adds 0.1 to your damage multiplicator", ObjType.atkboost);
        int size= (int) Math.round( Math.random()*3);
        value=1;
        Dimension d= Board.generateCoordinates(this);
        boardX=d.width;
        boardY=d.height;
    }

    @Override
    public int collect(Player p) {
        boardX=-1;
        boardY=-1;
        use(p,null);
        return 0;
    }

    @Override
    public int use(Player p, Mob mob) {
        System.out.printf(ConsoleWriter.GREEN+"Atk Mult: %.1f -> %.1f\n"+ ConsoleWriter.RESET,p.getAtkmult(),p.getAtkmult()+value*0.1);
        p.setAtkmult(p.getAtkmult()+value*0.1);
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
        return "Steroids";
    }
}