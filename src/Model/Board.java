package Model;

import Controller.Controller;
import Model.BoardObjects.*;
import Model.BoardObjects.Collectible.Armor;
import Model.BoardObjects.Collectible.Collectible;
import Model.BoardObjects.Collectible.Item;
import Model.BoardObjects.Mobs.BasicEnemy;
import Model.BoardObjects.Mobs.Mob;
import Vue.ConsoleWriter;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe permettant la gestion du plateau (génération, déplacements...)
 */
public class Board {
    /**
     * Tableau qui contiens le plateau de jeu
     */
    static BoardObject[][] board;
    /**
     * Raccourci vers l'objet joueur
     */
    static Player player;
    /**
     * Raccourci vers la sortie
     */
    static Exit exit;

    /**
     * Constructeur générant un plateau
     * @param size taille du plateau
     * @param wallNum Nombre de murs
     * @param goodsNum Nombre d'items
     */
    public Board(int size,int wallNum,int goodsNum,int enemyNum){
        flushBoard(size);
        player=new Player();
        for(int i=0;i<wallNum;i++){
            new Wall();
        }
        for(int i=0;i<goodsNum;i++){
            new Armor();
        }
        for(int i=0;i<enemyNum;i++){
            new BasicEnemy();
        }
        ConsoleWriter.printBoard(board);
    }

    /**
     * Constructeur générant un plateau de jeu par défaut
     * @param wallNum Nombre de murs
     * @param goodsNum Nombre d'items
     */
    public Board(int wallNum,int goodsNum,int enemyNum){
        flushBoard(9);
        player=new Player();
        for(int i=0;i<wallNum;i++){
            new Wall();
        }
        for(int i=0;i<goodsNum;i++){
            new Armor();
        }
        for(int i=0;i<enemyNum;i++){
            new BasicEnemy();
        }
        ConsoleWriter.printBoard(board);
    }

    /**
     * Méthode pour le mise à zéro du plateau de jeu
     * @param size taille du nouveau plateau
     */
    private void flushBoard(int size){
        board= new BoardObject[size+2][size+2];
        for (int x=0;x<size+2;x++){
            for(int y=0;y<size+2;y++){
                if(x==0 || x==size+1 || y==0 || y==size+1)
                    board[x][y]=new Wall(new Dimension(x,y));
                else{
                    board[x][y]=new Empty(new Dimension(x,y));
                }
            }
        }
        switch ((int) Math.round(Math.random()*3)){
            case 0:
                exit=new Exit(new Dimension(size/2+1,0));
                board[size/2+1][0]=exit;
                break;
            case 1:
                exit=new Exit(new Dimension(0,size/2+1));
                board[0][size/2+1]=exit;
                break;
            case 2:
                exit=new Exit(new Dimension(size+1,size/2+1));
                board[size+1][size/2+1]=exit;
                break;
            case 3:
                exit=new Exit(new Dimension(size/2+1,size+1));
                board[size/2+1][size+1]=exit;
                break;
        }
    }

    /**
     * Méthode pour attribuer un objet à une case vide du plateau de facon aléatoire
     * @param object Objet à placer
     * @return retourne les nouvelles coordonnées de l'objet
     */
    public static Dimension generateCoordinates(BoardObject object){
        if(!isBoardFull()) {
            boolean found = false;
            Dimension res = new Dimension(-1, -1);
            while (found == false) {
                int gx = (int) Math.round(Math.random() * (board[0].length - 2));
                int gy = (int) Math.round(Math.random() * (board[0].length - 2));
                if (board[gx][gy].getType() == ObjType.empty) {
                    found = true;
                    res = new Dimension(gx, gy);
                }
            }
            board[res.width][res.height] = object;
            return res;
        }
        return new Dimension(-1, -1);
    }

    /**
     * Méthode permettant de savoir si le plateau est plein ou non
     * @return rentvoie true si le plateau est plein
     */
    private static boolean isBoardFull(){
        for (BoardObject[] row:board) {
            for (BoardObject elem:row) {
                if(elem.getType()==ObjType.empty)
                    return false;
            }
        }
        return true;
    }

    /**
     * Méthode pour déplacer le joueur
     * @param c Nouveles coordonnées du joueur
     */
    public static String  movePlayer(Dimension c){
        String out="";
        if (!(board[c.width][c.height] instanceof Collectible)) {
            if (board[c.width][c.height].getType() != ObjType.wall && board[c.width][c.height].getType() != ObjType.exit) {
                if(board[c.width][c.height].getType()==ObjType.enemy){
                    boolean fight_ended=false;
                    ArrayList<String> options=new ArrayList<>();
                    options.add("Attack");
                    options.add("See your stats");
                    options.add("Inventory");
                    options.add("Flee");
                    System.out.println("Fight started with "+board[c.width][c.height].getClass().getSimpleName());
                    Mob mob = (Mob) board[c.width][c.height];
                    while(!fight_ended) {
                        switch (ConsoleWriter.AskQuestion(options)) {
                            case 0:
                                player.attack((Mob)board[c.width][c.height]);
                                if(mob.isDead()) {
                                    System.out.println("Type anything to continue");
                                    ConsoleWriter.waitPlayer();
                                    fight_ended=true;
                                }
                                break;
                            case 1:
                                ConsoleWriter.printBar("HEALTH",Board.getPlayer().getHealth());
                                ConsoleWriter.printBar("ARMOR",Board.getPlayer().getArmor());
                                break;
                            case 2:
                                if(player.useItem(mob)) {
                                    mob.attack(player);
                                }
                                break;
                            case 3:
                                return "You fled...";
                        }
                    }
                }
                refreshCoordinates(c);
                if(isEnded()){
                    exit.setstate(true);
                }
            }
            if (board[c.width][c.height].getType() == ObjType.wall) {return "You can't walk thought walls";}
            if (board[c.width][c.height].getType() == ObjType.exit) {
                if(exit.getSate()){Controller.restart();
                    return "Board exited!";}
                else
                    return "The exit is locked!";
            }
        } else {
            out=board[c.width][c.height].getType()+" picked up!";
            ((Collectible) board[c.width][c.height]).collect(player);
            refreshCoordinates(c);
            return out;
        }
        return out;
    }

    /**
     * Méthode pour update le Tableau du plateau de jeu lors d'une action du joueur
     * @param c nouvelles coordonnées du joureur
     */
    private static void refreshCoordinates(Dimension c){
        Dimension dplayer = player.getCoordinates();
        board[dplayer.width][dplayer.height] = new Empty(new Dimension(dplayer.width, dplayer.height));
        board[c.width][c.height] = player;
        player.setCoordinates(c);
    }

    /**
     * Getteur pour le Tableau du plateau de jeu
     * @return retourne le plateau de jeu
     */
    public static BoardObject[][] getBoard() {
        return board;
    }

    /**
     * Getteur pour le joueur
     * @return retourne l'objet joueur
     */
    public static Player getPlayer() {
        return player;
    }

    private static boolean isEnded(){
        for (BoardObject[] bo:board) {
            for (BoardObject elem:bo) {
                if(elem.getType()==ObjType.enemy){
                    return false;
                }
            }
        }
        return true;
    }
}
