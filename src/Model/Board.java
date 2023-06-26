package Model;

import Controller.Controller;
import Model.BoardObjects.*;
import Model.BoardObjects.Collectible.*;
import Model.BoardObjects.Mobs.BasicBoss;
import Model.BoardObjects.Mobs.BasicEnemy;
import Model.BoardObjects.Mobs.Mob;
import Model.BoardObjects.Mobs.Player;
import Vue.ConsoleWriter;

import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.util.*;

/**
 * Classe permettant la gestion du plateau (génération, déplacements...)
 */
public class Board {
    /**
     * Tableau qui contiens le plateau de jeu
     */
    private static BoardObject[][] board;
    /**
     * Raccourci vers l'objet joueur
     */
    private static Player player;
    /**
     * Raccourci vers la sortie
     */
    private static Exit exit;

    private static int minSize = 3;

    private static Dimension spawnCoordinate;

    private static Controller controller;

    private static int bossDefeated = 0;

    /**
     * Constructeur générant un plateau
     *
     * @param size     taille du plateau
     * @param goodsNum Nombre d'items
     */
    public Board(int size,  int goodsNum, int enemyNum, Controller controller) {
        this.controller = controller;
        if (size < minSize){ size = minSize; }
        flushBoard(size);
        generateMaze();
        int r=0;
        for (int i = 0; i < goodsNum; i++) {
            switch ((int) Math.round(Math.random()*3)){
                case 0:
                    new Armor();
                    break;
                case 1:
                    new HealthPotion();
                    break;
                case 2:
                    new Armor();
                    break;
            }
        }
        for (int i = 0; i < enemyNum; i++) {
            new BasicEnemy();
        }
        generateExit();
    }

    /**
     * Constructeur générant un plateau
     *
     * @param size     taille du plateau
     * @param goodsNum Nombre d'items
     */
    public Board(Player p, int size, int goodsNum, int enemyNumn, Controller controller) {
        this(size, goodsNum, enemyNumn, controller);
        player = p;
        player.setCoordinates(spawnCoordinate);
    }

    public Board(Player p, Controller controller){
        this.controller = controller;
        flushBoard(5);
        spawnCoordinate = new Dimension(4, 3);
        player = p;
        player.setCoordinates(spawnCoordinate);
        board[4][3] = player;
        BasicBoss boss = new BasicBoss( new Dimension(3, 2));
        board[2][3] = boss;
    }


    /**
     * Méthode pour le mise à zéro du plateau de jeu
     *
     * @param size taille du nouveau plateau
     */
    private void flushBoard(int size) {
        board = new BoardObject[size + 2][size + 2];
        for (int x = 0; x < size + 2; x++) {
            for (int y = 0; y < size + 2; y++) {
                if (x == 0 || x == size + 1 || y == 0 || y == size + 1)
                    board[x][y] = new Wall(new Dimension(x, y));
                else {
                    board[x][y] = new Empty(new Dimension(x, y));
                }
            }
        }
    }

    /**
     * Méthode pour attribuer un objet à une case vide du plateau de facon aléatoire
     *
     * @param object Objet à placer
     * @return retourne les nouvelles coordonnées de l'objet
     */
    public static Dimension generateCoordinates(BoardObject object) {
        if (!isBoardFull()) {
            boolean found = false;
            Dimension res = new Dimension(-1, -1);
            while (!found) {
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
     *
     * @return rentvoie true si le plateau est plein
     */
    private static boolean isBoardFull() {
        for (BoardObject[] row : board) {
            for (BoardObject elem : row) {
                if (elem.getType() == ObjType.empty)
                    return false;
            }
        }
        return true;
    }

    /**
     * Méthode pour déplacer le joueur
     *
     * @param c Nouveles coordonnées du joueur
     */
    public static String movePlayer(Dimension c) {
        String out = "";
        boolean refreshCoordinate = true;
        if (!(board[c.width][c.height] instanceof Collectible)) {
            if (board[c.width][c.height].getType() != ObjType.wall && board[c.width][c.height].getType() != ObjType.exit) {
                if (board[c.width][c.height].getType() == ObjType.enemy) {
                    boolean fight_ended = false;
                    ArrayList<String> options = new ArrayList<>();
                    options.add("Attack");
                    options.add("See your stats");
                    options.add("Inventory");
                    options.add("Flee");
                    System.out.println("Fight started with " + board[c.width][c.height].getClass().getSimpleName());
                    Mob mob = (Mob) board[c.width][c.height];
                    while (!fight_ended) {
                        switch (ConsoleWriter.AskQuestion(options)) {
                            case 0:
                                player.attack((Mob) board[c.width][c.height]);
                                if (mob.isDead()) {
                                    System.out.println("Type anything to continue");
                                    ConsoleWriter.waitPlayer();
                                    fight_ended = true;
                                }
                                if(player.isDead()){
                                    player.die();
                                    controller.restart();
                                    return "";
                                }
                                break;
                            case 1:
                                ConsoleWriter.printBar(ConsoleWriter.RED+"HEALTH"+ConsoleWriter.RESET,Board.getPlayer().getHealth());
                                ConsoleWriter.printBar(ConsoleWriter.ANSI_CYAN+"ARMOR"+ConsoleWriter.RESET,Board.getPlayer().getArmor());
                                System.out.println("Damages:\n"+Board.getPlayer().getDamages()+"\nDamage multiplicator:\n"+Board.getPlayer().getAtkmult());
                                break;
                            case 2:
                                if (player.useItem(mob)) {
                                    if(player.isDead()){
                                        player.die();
                                        controller.restart();
                                        return "";
                                    }
                                    else {
                                        if (!mob.isDead())
                                            mob.attack(player);
                                        else {
                                            mob.die();
                                            System.out.println("Type anything to continue");
                                            ConsoleWriter.waitPlayer();
                                            fight_ended = true;
                                        }
                                    }
                                }
                                break;
                            case 3:
                                return ConsoleWriter.GREEN+"You fled..."+ConsoleWriter.RESET;
                        }
                    }
                }
                if (refreshCoordinate)
                    refreshCoordinates(c);
            }
            if (isEnded()) {
                exit.setstate(true);
            }
            if (board[c.width][c.height].getType() == ObjType.wall) {
                return ConsoleWriter.RED+"You can't walk thought walls"+ConsoleWriter.RESET;
            }
            if (board[c.width][c.height].getType() == ObjType.exit) {
                if (exit.getSate()) {
                    controller.nextBoard();
                    return ConsoleWriter.GREEN+"Board exited!"+ConsoleWriter.RESET;
                } else
                    return ConsoleWriter.RED+"The exit is locked!"+ConsoleWriter.RESET;
            }
        } else {
            out = ConsoleWriter.GREEN +board[c.width][c.height] + " picked up!"+ConsoleWriter.RESET;
            ((Collectible) board[c.width][c.height]).collect(player);
            refreshCoordinates(c);
            return out;
        }
        return out;
    }

    /**
     * Méthode pour update le Tableau du plateau de jeu lors d'une action du joueur
     *
     * @param c nouvelles coordonnées du joureur
     */
    private static void refreshCoordinates(Dimension c) {
        Dimension dplayer = player.getCoordinates();
        board[dplayer.width][dplayer.height] = new Empty(new Dimension(dplayer.width, dplayer.height));
        board[c.width][c.height] = player;
        player.setCoordinates(c);
    }

    /**
     * Getteur pour le Tableau du plateau de jeu
     *
     * @return retourne le plateau de jeu
     */
    public static BoardObject[][] getBoard() {
        return board;
    }

    /**
     * Getteur pour le joueur
     *
     * @return retourne l'objet joueur
     */
    public static Player getPlayer() {
        return player;
    }

    /**
     * Methode pour déterminer si tous les énemis sont morts
     * @return retourne true si tous les énemis sont morts
     */
    private static boolean isEnded() {
        for (BoardObject[] bo : board) {
            for (BoardObject elem : bo) {
                if (elem.getType() == ObjType.enemy) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Méthode pour remplir le Board de murs
     */
    public void fillBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Wall(new Dimension(i, j));
            }
        }
    }

    /**
     * Méthode pour trouver un mur à supprimer (sert à la génération)
     * @param d coordonées
     * @return retourne les coordonées du mur à supprimer
     */
    public Dimension findWallToBreak(Dimension d) {
        ArrayList<Dimension> list = new ArrayList<>();
        if (d.width + 2 < board.length - 1 && board[d.width + 2][d.height].isVisited()) {
            list.add(new Dimension(d.width + 1, d.height));
        }
        if (d.width - 2 > 0 && board[d.width - 2][d.height].isVisited()) {
            list.add(new Dimension(d.width - 1, d.height));
        }
        if (d.height + 2 < board.length - 1 && board[d.width][d.height + 2].isVisited()) {
            list.add(new Dimension(d.width, d.height + 1));
        }
        if (d.height - 2 > 0 && board[d.width][d.height - 2].isVisited()) {
            list.add(new Dimension(d.width, d.height - 1));
        }
        if (list.size() == 0) {
            return null;
        }
        int randIndex = (int) (Math.random() * list.size());
        return list.get(randIndex);
    }

    public boolean isValid(Dimension d, ArrayList<Dimension> list) {
        return !board[d.width][d.height].isVisited();
    }

    public void updateBoard(Dimension d, BoardObject object) {
        board[d.width][d.height] = object;
        board[d.width][d.height].setVisited(true);
    }

    public void createStartingRoom(Dimension d) {
        for (int i = -1; i <=1; i++){
            for (int j = -1; j <=1; j++){
                updateBoard(new Dimension(d.width+i, d.height+j), new Empty());
            }
        }
    }

    public void generateMaze() {
        fillBoard();
        int size = board.length / 2;
        ArrayList<Dimension> list = new ArrayList<>();
        System.out.println(board.length + " : " + size);
        Dimension dim = new Dimension(1, 1);
        updateBoard(dim, new Empty(dim));
        list.add(new Dimension(3, 1));
        list.add(new Dimension(1, 3));
        while (!list.isEmpty()) {
            int random = (int) (Math.random() * list.size());
            dim = list.get(random);
            if (isValid(dim, list)) {
                if (dim.width - 2 > 0 && !list.contains(new Dimension(dim.width - 2, dim.height))) {
                    list.add(new Dimension(dim.width - 2, dim.height));
                }
                if (dim.width + 2 < size * 2 && !list.contains(new Dimension(dim.width + 2, dim.height))) {
                    list.add(new Dimension(dim.width + 2, dim.height));
                }
                if (dim.height - 2 > 0 && !list.contains(new Dimension(dim.width, dim.height - 2))) {
                    list.add(new Dimension(dim.width, dim.height - 2));
                }
                if (dim.height + 2 < size * 2 && !list.contains(new Dimension(dim.width, dim.height + 2))) {
                    list.add(new Dimension(dim.width, dim.height + 2));
                }
                updateBoard(dim, new Empty());
                Dimension wall = findWallToBreak(dim);
                if (wall != null) {
                    updateBoard(wall, new Empty());
                }
            }
            list.remove(dim);
        }
        Dimension roomCenter = new Dimension((int) Math.floor(Math.random() * (board.length - 5)) +2,
                (int) Math.floor(Math.random() * (board.length - 5)) +2);
        createStartingRoom(roomCenter);
        player = new Player(roomCenter);
        board[player.getCoordinates().width][player.getCoordinates().height] = player;
        spawnCoordinate = roomCenter;
    }

    public void generateExit(){
        while(true){
            int x = (int) Math.round(Math.random() * (board.length - 1)) ;
            int y = (int) Math.round(Math.random() * (board.length - 1)) ;
            if (!(x == y || x == 0 && y == board.length - 1 ||y == 0 && x == board.length - 1)) {
                Dimension dim = new Dimension(x, y);
                if (x == 0 && !(board[x+1][y].getType().equals(ObjType.wall))) {
                    exit = new Exit(dim);
                    updateBoard(dim, exit);
                    break;
                }
                if (x == board.length - 1 && !(board[x-1][y].getType().equals(ObjType.wall))){
                    exit = new Exit(dim);
                    updateBoard(dim, exit);
                    break;
                }
                if (y == 0 && !(board[x][y+1].getType().equals(ObjType.wall))) {
                    exit = new Exit(dim);
                    updateBoard(dim, exit);
                    break;
                }
                if (y == board.length - 1 && !(board[x][y-1].getType().equals(ObjType.wall))){
                    exit = new Exit(dim);
                    updateBoard(dim, exit);
                    break;
                }
            }
        }
    }

    public static void setBoard(BoardObject[][] board) {
        Board.board = board;
    }

    public static void setPlayer(Player player){
        Board.player = player;
    }

    public static void setExit(Exit exit) {
        Board.exit = exit;
    }

    public static Exit getExit() {
        return exit;
    }

    public static int getBossDefeated() {
        return bossDefeated;
    }

    public static void setController(Controller controller) {
        Board.controller = controller;
    }

    public static void bossDefeatUp(){
        bossDefeated++;
        controller.nextBoard();
    }
    public static void bossDefeatDown(){
        if(bossDefeated>=1)
            bossDefeated--;
    }

    public static void setBossDefeated(int bossDefeated) {
        Board.bossDefeated = bossDefeated;
    }
}
