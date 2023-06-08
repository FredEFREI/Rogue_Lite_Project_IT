package Model.BoardObjects;

import Model.Board;

import java.awt.*;

public class Player extends BoardObject{
    ObjType type = ObjType.player;
    int armor=0;
    int health=100;
    int damage=10;
    ArrayList<Collectible> inventory;

    public Player(){
        this(Board.generateCoordinates(this));
    }
    
    public Player(Dimension coordinates){
        boardX=coordinates.width;
        boardY=coordinates.height;
        inventory = new ArrayList<>();
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

    public void setCoordinates(Dimension c){boardX=c.width;boardY=c.height;}

    public int getArmor() {
        return armor;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
