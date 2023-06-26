package Model.BoardObjects.Mobs;

import Model.Board;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Collectible.*;
import Model.BoardObjects.Empty;
import Model.BoardObjects.ObjType;
import Vue.ConsoleWriter;

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
        int damages = (int)  Math.round(Math.random() * 10 *(1+Board.getBossDefeated()*0.5));
        m.inflictDamage(damages);
        System.out.println(ConsoleWriter.RED +this.getClass().getSimpleName()+" inflicted "+damages+" of damage to "+m.getClass().getSimpleName()+ConsoleWriter.RESET);
    }

    public void die() {
        health=0;
        System.out.println("////////////////////\nYour enemy dropped:");
        int nbitems=1+(int) Math.round(Math.random()*(3+Board.getBossDefeated()));
        Item it=null;
        for (int i = 0; i < nbitems; i++) {
            switch ((int) Math.round(Math.random()*10)) {
                case 0,1,2:
                    it=new Armor();
                    System.out.println("Armor Item");
                    break;
                case 3,4:
                    it=new HealthPotion();
                    System.out.println("Health Item");
                    break;
                case 6,7,8:
                    it=new LesFrais();
                    System.out.println("LesFrais");
                    break;
                case 9,5:
                    it = new Sword();
                    System.out.println("LesFrais's course pdf:");
                    break;
            }
            if(it!=null && it.getBoardY()!=-1 && it.getBoardX()!=-1) {
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
        if(damages==-1)
            health=0;
        else
            health -= damages/(Board.getBossDefeated()*0.5+1);
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
