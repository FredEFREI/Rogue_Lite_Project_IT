package Model;

import Model.BoardObjects.*;
import Model.BoardObjects.Collectible.Armor;
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
     * Constructeur générant un plateau
     * @param size taille du plateau
     * @param wallNum Nombre de murs
     * @param goodsNum Nombre d'items
     */
    public Board(int size,int wallNum,int goodsNum){
        flushBoard(size);
        player=new Player();
        for(int i=0;i<wallNum;i++){
            new Wall();
        }
        for(int i=0;i<goodsNum;i++){
            new Armor();
        }
        ConsoleWriter.printBoard(board);
    }

    /**
     * Constructeur générant un plateau de jeu par défaut
     * @param wallNum Nombre de murs
     * @param goodsNum Nombre d'items
     */
    public Board(int wallNum,int goodsNum){
        flushBoard(10);
        player=new Player();
        for(int i=0;i<wallNum;i++){
            new Wall();
        }
        for(int i=0;i<goodsNum;i++){
            new Armor();
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
    public static void  movePlayer(Dimension c){
        switch (board[c.width][c.height].getType()) {
            case wall:
                break;
            case armor:
                Armor a= (Armor) board[c.width][c.height];
                a.use(player);
                refreshCoordinates(c);
                break;
            case empty:
                refreshCoordinates(c);
                break;
        }
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
}
