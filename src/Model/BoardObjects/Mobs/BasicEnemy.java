package Model.BoardObjects.Mobs;

import Model.Board;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Collectible.*;
import Model.BoardObjects.Empty;
import Model.BoardObjects.ObjType;

import java.awt.*;
import java.util.ArrayList;

public class BasicEnemy extends BoardObject implements Mob{
    int health = 100;
    public BasicEnemy(){
        type = ObjType.enemy;
        Dimension d = Board.generateCoordinates(this);
        boardX = d.width;
        boardY = d.height;
    }

    public BasicEnemy(Dimension coordinates){
        type = ObjType.enemy;
        boardX=coordinates.width;
        boardY=coordinates.height;
    }

    @Override
    public boolean isOnBoard() {
        if(boardY>=0 && boardX>=0)
            return true;
        return false;
    }

    public void attack(Mob m) {
        int damages = (int)  Math.round(Math.random() * 15);
        m.inflictDamage(damages);
        System.out.println(this.getClass().getSimpleName()+" inflicted "+damages+" of damage to "+m.getClass().getSimpleName());
    }

    public void die() {
        health=0;
        System.out.println("Your enemy dropped:");
        int nbitems=1+(int) Math.round(Math.random()*2);
        Item it=null;
        for (int i = 0; i < nbitems; i++) {
            switch ((int) Math.round(Math.random() * 2)) {
                case 0:
                    it=new Armor();
                    System.out.println("Armor");
                    break;
                case 2:
                    it=new Sword();
                    System.out.println("Sword");
                    break;
                case 1:
                    System.out.println("Steroids:");
                    it=new Steroids();
                    break;
                /*case 3:
                    it=new EngineeringDiploma();
                    System.out.println("Engineering diploma");
                    break;*/
            }
            if(it!=null) {
                Board.getBoard()[it.getBoardX()][it.getBoardY()] = new Empty(new Dimension(it.getBoardX(), it.getBoardY()));
                it.collect(Board.getPlayer());
            }
        }
    }

    public int getHealth() {
        return health;
    }

    @Override
    public void inflictDamage(int damages) {
        health-=damages;
    }

    public void setHealth(int i) {
        health=i;
    }

    @Override
    public boolean isDead() {
        if(health<=0)
            return true;
        return false;
    }

    @Override
    public ObjType getType() {
        return type;
    }

    @Override
    protected Dimension getCoordinates() {
        return new Dimension(boardX,boardY);
    }
}
