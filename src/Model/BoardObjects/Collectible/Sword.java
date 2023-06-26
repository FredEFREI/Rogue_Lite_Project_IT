package Model.BoardObjects.Collectible;

import Model.Board;
import Model.BoardObjects.Mobs.Mob;
import Model.BoardObjects.Mobs.Player;
import Model.BoardObjects.ObjType;
import Vue.ConsoleWriter;

import java.awt.*;

public class Sword extends Item {
    private int value = 25;

    public Sword() {
        super("LesFrais's course pdf", "adds 1 to your damages", ObjType.weapon);
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
        System.out.println(ConsoleWriter.GREEN+"Atk: " + p.getDamages() + " -> " + (p.getDamages() + 1)+ ConsoleWriter.RESET);
        p.setDamage(p.getDamages() + 1);
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
        return "LesFrais's course pdf";
    }
}