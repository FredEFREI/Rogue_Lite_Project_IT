package Model.BoardObjects.Mobs;

import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Collectible.*;
import Model.BoardObjects.Mobs.Mob;
import Model.BoardObjects.ObjType;
import Vue.ConsoleWriter;

import java.awt.*;
import java.util.*;

/**
 * Classe représentant le joueur
 */
public class Player extends BoardObject implements Mob {
    double atkmult=1;
    private int armor=0;
    private int health=100;
    private int damage=10;
    private ArrayList<Item> inventory;

    public Player(int boardSize){
        type = ObjType.player;
        setCoordinates(new Dimension(boardSize/2+1,boardSize/2+1));
        inventory = new ArrayList<>();
    }

    public Player(Dimension coordinates){
        type = ObjType.player;
        setCoordinates(coordinates);
        inventory = new ArrayList<>();
    }




    @Override
    public boolean isOnBoard() {
        if(boardY>=0 && boardX>=0)
            return true;
        return false;
    }


    public void attack(Mob m) {
        int damages = (int)  Math.round((Math.random() * damage)*atkmult);
        m.inflictDamage(damages);
        System.out.println(this.getClass().getSimpleName()+" inflicted "+damages+" of damage to "+m.getClass().getSimpleName());
        if(m.isDead()){
            System.out.println(m.getClass().getSimpleName()+" died!");
            m.die();
        }else{
            ConsoleWriter.printBar("Enemy Health",m.getHealth());
            m.attack(this);
        }
    }

    public boolean useItem(Mob mob){
        if (inventory.size() < 1){
            System.out.println("You have no item in your inventory");
            return false;
        }
        else {
            ConsoleWriter.printList("Inventory :", inventory);
            ArrayList<String> o=new ArrayList<>();
            o.add("Use an Item");
            o.add("See description of an item");
            if(ConsoleWriter.AskQuestion(o)==0) {
                ArrayList<String> opt = new ArrayList<>();
                for (Collectible elem : inventory) {
                    opt.add(elem.toString());
                }
                int itemid=ConsoleWriter.AskQuestion(opt);
                if(inventory.get(itemid).use(this, mob)==0) {
                    removeItem(inventory.get(itemid));
                    System.out.println("Item used");
                    return true;
                }
                else{System.out.println("This item can't be used in this situation");return false;}
            }
            else {
                ArrayList<String> opt = new ArrayList<>();
                for (Collectible elem : inventory) {
                    opt.add(elem.toString());
                }
                int itemid=ConsoleWriter.AskQuestion(opt);
                if(itemid!=-1)
                    System.out.println(inventory.get(itemid).getName()+": \n"+inventory.get(itemid).getDescription());
            }
            return false;
        }
    }

    @Override
    public void die() {
        System.out.println("You died!");
        inventory.removeAll(inventory);
    }

    public int getHealth() {
        return health;
    }

    public void inflictDamage(int i) {
        if(armor>i){
            armor-=i;
        }else{
            i-=armor;
            armor=0;
            health-=i;
        }
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

    @Override
    public boolean isDead() {
        if(health<=0)
            return true;
        return false;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void addItem(Item item){
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public double getatkmult(){return atkmult;}

    public void setatkmult(double mult){atkmult=mult;}

    public int getDamages(){return damage;}

    @Override
    public Dimension getCoordinates() {
        return new Dimension(boardX,boardY);
    }
    @Override
    public ObjType getType() {
        return type;
    }

    public void setCoordinates(Dimension c){
        boardX=c.width;
        boardY=c.height;
    }

    public int getArmor() {
        return armor;
    }

    public int getDamage() {
        return damage;
    }

    public double getAtkmult() {
        return atkmult;
    }
}
