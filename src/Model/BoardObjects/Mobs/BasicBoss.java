package Model.BoardObjects.Mobs;

import Model.Board;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Collectible.*;
import Model.BoardObjects.Empty;
import Model.BoardObjects.ObjType;

import java.awt.*;

public class BasicBoss extends BoardObject implements Mob {
    int health = 100;

    public BasicBoss() {
        type = ObjType.enemy;
        Dimension d = Board.generateCoordinates(this);
        boardX = d.width;
        boardY = d.height;
    }

    public BasicBoss(Dimension coordinates) {
        type = ObjType.enemy;
        boardX = coordinates.width;
        boardY = coordinates.height;
    }

    @Override
    public boolean isOnBoard() {
        if (boardY >= 0 && boardX >= 0)
            return true;
        return false;
    }

    public void attack(Mob m) {
        if(!isDead()) {
            int damages = (int) Math.round(Math.random() * 35 * (1 + 0.5*Board.getBossDefeated()));
            m.inflictDamage(damages);
            System.out.println(this.getClass().getSimpleName() + " inflicted " + damages + " of damage to " + m.getClass().getSimpleName());
        }
    }

    public void die() {
        health = 0;

        System.out.println("////////////////////\nYour enemy dropped:");
        int nbitems = 1 + (int) Math.round(Math.random() * (5+Board.getBossDefeated()));
        Item it = null;
        for (int i = 0; i < nbitems; i++) {
            switch ((int) Math.round(Math.random() * 4)) {
                case 0:
                    it = new Fat();
                    System.out.println("Fat");
                    break;
                case 2:
                    it = new Sword();
                    System.out.println("LesFrais's course pdf");
                    break;
                case 1:
                    System.out.println("Steroids:");
                    it = new Steroids();
                    break;
                case 3:
                    it = new Sword();
                    System.out.println("LesFrais's course pdf");
                    break;
            }
            if (it != null) {
                if(it.getBoardX()!=-1 || it.getBoardY()!=-1) {
                    Board.getBoard()[it.getBoardX()][it.getBoardY()] = new Empty(new Dimension(it.getBoardX(), it.getBoardY()));
                    it.collect(Board.getPlayer());
                }
            }
        }
        if(Board.getBossDefeated()%5==0) {
            it = new EngineeringDiploma();
            Board.getBoard()[it.getBoardX()][it.getBoardY()] = new Empty(new Dimension(it.getBoardX(), it.getBoardY()));
            it.collect(Board.getPlayer());
            System.out.println("Engineering diploma");
        }
        Board.bossDefeatUp();
        System.out.println("Mob Level: "+Board.getBossDefeated());
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
        health = i;
    }

    @Override
    public boolean isDead() {
        if (health <= 0)
            return true;
        return false;
    }

    @Override
    public ObjType getType() {
        return type;
    }

    @Override
    public Dimension getCoordinates() {
        return new Dimension(boardX, boardY);
    }
}