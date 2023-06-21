package Model.BoardObjects.Mobs;

import Model.Board;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Collectible.Armor;
import Model.BoardObjects.Collectible.HealthPotion;
import Model.BoardObjects.Collectible.Item;
import Model.BoardObjects.Collectible.Steroids;
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
        System.out.println("Your enemy dropped: \n");
        int nbitems=(int) Math.round(Math.random()*3);
        for (int i = 0; i < nbitems; i++) {
            switch ((int) Math.round(Math.random() * 3)) {
                case 0:
                    new Armor().collect(Board.getPlayer());
                    System.out.println("Armor");
                    break;
                case 1:
                    new HealthPotion().collect(Board.getPlayer());
                    System.out.println("Health Potion");
                    break;
                case 2:
                    new Steroids().collect(Board.getPlayer());
                    System.out.println("Steroids");
                    break;
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
