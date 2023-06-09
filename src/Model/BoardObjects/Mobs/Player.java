package Model.BoardObjects.Mobs;

import Model.Board;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Collectible.*;
import Model.BoardObjects.Empty;
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
    double defmult=1;
    private int armor=50;
    private int health=100;
    private int damage=20;
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
        System.out.println(ConsoleWriter.GREEN+this.getClass().getSimpleName()+" inflicted "+damages+" of damage to "+m.getClass().getSimpleName()+ConsoleWriter.RESET);
        if(m.isDead()){
            System.out.println(ConsoleWriter.GREEN +m.getClass().getSimpleName()+" died!"+ConsoleWriter.RESET);
            m.die();
        }else{
            ConsoleWriter.printBar("Enemy Health",m.getHealth());
            m.attack(this);
        }
    }

    public boolean useItem(Mob mob){
        if (inventory.size() < 1){
            System.out.println(ConsoleWriter.RED+"You have no item in your inventory"+ConsoleWriter.RESET);
            return false;
        }
        else {
            ConsoleWriter.printList("Inventory :", inventory);
            ArrayList<String> o=new ArrayList<>();
            o.add("Use an Item");
            o.add("See description of an item");
            o.add("drop an item");
            int usrres=ConsoleWriter.AskQuestion(o);
            if(usrres==0) {
                ArrayList<String> opt = new ArrayList<>();
                for (Collectible elem : inventory) {
                    opt.add(elem.toString());
                }
                int itemid=ConsoleWriter.AskQuestion(opt);
                if (itemid!=-1) {
                    if (inventory.get(itemid).use(this, mob) == 0) {
                        if (inventory.size() > 0)
                            removeItem(inventory.get(itemid));
                        System.out.println("Item used");
                        return true;
                    } else {
                        System.out.println(ConsoleWriter.RED+"This item can't be used in this situation"+ConsoleWriter.RESET);
                        return false;
                    }
                }
            }
            if(usrres==1) {
                ArrayList<String> opt = new ArrayList<>();
                for (Collectible elem : inventory) {
                    opt.add(elem.toString());
                }
                int itemid=ConsoleWriter.AskQuestion(opt);
                if(itemid!=-1)
                    System.out.println(inventory.get(itemid).getName()+": \n"+inventory.get(itemid).getDescription());
            }
            if(usrres==2){
                ArrayList<String> opt = new ArrayList<>();
                for (Collectible elem : inventory) {
                    opt.add(elem.toString());
                }
                int itemid=ConsoleWriter.AskQuestion(opt);
                if(itemid!=-1) {
                    inventory.remove(itemid);
                    System.out.println("Item dropped");
                }
            }
            return false;
        }
    }

    @Override
    public void die() {
        Board.bossDefeatDown();
        System.out.println(ConsoleWriter.RED+"You died!"+ConsoleWriter.RESET+"\nMob Level: "+Board.getBossDefeated());
        inventory.removeAll(inventory);
        health = 100;
        armor=50;
    }

    public int getHealth() {
        return health;
    }

    public void inflictDamage(int i) {
        if(armor>i/defmult){
            armor-=i/defmult;
        }else{
            i-=armor*defmult;
            armor=0;
            health-=i/defmult;
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

    public void setAtkmult(double mult){atkmult=mult;}

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

    public double getAtkmult() {
        return atkmult;
    }

    public double getDefmult() {
        return defmult;
    }

    public void setDefmult(double defmult) {
        this.defmult = defmult;
    }
}
