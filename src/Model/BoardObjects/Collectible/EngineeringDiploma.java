package Model.BoardObjects.Collectible;

import Model.Board;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Empty;
import Model.BoardObjects.Mobs.Mob;
import Model.BoardObjects.Mobs.Player;
import Model.BoardObjects.ObjType;

import java.awt.*;

public class EngineeringDiploma extends Item {
    private int value=25;

    public EngineeringDiploma(){
        super("Engineering diploma", "Instantly kills an enemy but flush your inventory", ObjType.atkboost);
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
        p.getInventory().add(this);
        return 0;
    }

    @Override
    public int use(Player p, Mob mob) {
        if(Board.getBoard().length>7) {
            if(mob!=null)
                mob.inflictDamage(-1);
            BoardObject[][] b = Board.getBoard();
            for (int i = 0; i < b.length; i++) {
                for (int j = 0; j < b.length; j++) {
                    if (b[i][j].getType() == ObjType.enemy)
                        b[i][j] = new Empty(new Dimension(i, j));
                }
            }
        }
        else{
            if(mob!=null)
                mob.inflictDamage(10000000*(Board.getBossDefeated()+1));
        }
        p.getInventory().removeAll(p.getInventory());
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
        return "Engineering diploma";
    }
}
