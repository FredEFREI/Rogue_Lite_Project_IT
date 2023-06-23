package Model.BoardObjects.Collectible;

import Model.Board;
import Model.BoardObjects.Mobs.Mob;
import Model.BoardObjects.Mobs.Player;
import Model.BoardObjects.ObjType;

import java.awt.*;

public class Fat extends Item {
    private int value = 1;

    public Fat() {
        super("Fat", "adds 0.1 to your armor multiplicator", ObjType.atkboost);
        int size = (int) Math.round(Math.random() * 3);
        value = 1;
        Dimension d = Board.generateCoordinates(this);
        boardX = d.width;
        boardY = d.height;
    }

    @Override
    public int collect(Player p) {
        boardX = -1;
        boardY = -1;
        use(p, null);
        return 0;
    }

    @Override
    public int use(Player p, Mob mob) {
        System.out.println("Def Mult: " + p.getDefmult() + " -> " + (p.getDefmult() + value * 0.1));
        p.setDefmult(p.getDefmult() + value * 0.1);
        return 0;
    }

    @Override
    public Dimension getCoordinates() {
        return new Dimension(boardX, boardY);
    }

    @Override
    public boolean isOnBoard() {
        if (boardY >= 0 && boardX >= 0)
            return true;
        return false;
    }

    @Override
    public ObjType getType() {
        return super.getType();
    }

    @Override
    public String toString() {
        return "Fat";
    }
}