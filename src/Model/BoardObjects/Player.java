package Model.BoardObjects;

import Model.Board;
import Model.BoardObjects.Collectible.*;

import java.awt.*;
import java.util.*;

/**
 * Classe représentant le joueur
 */
public class Player extends BoardObject{
    private int armor=0;
    private int health=100;
    private int damage=10;
    private ArrayList<Item> inventory;

    public Player(){
        type = ObjType.player;
        Dimension d = Board.generateCoordinates(this);
        boardX = d.width;
        boardY = d.height;
        inventory = new ArrayList<>();
    }

    public Player(Dimension coordinates){
        type = ObjType.player;
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

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}
