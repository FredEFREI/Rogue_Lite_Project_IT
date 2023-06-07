package Model;

import Model.BoardObjects.*;
import Model.BoardObjects.Collectible.Armor;
import Vue.ConsoleWriter;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    static BoardObject[][] board;
    static Player player;
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

    private static boolean isBoardFull(){
        for (BoardObject[] row:board) {
            for (BoardObject elem:row) {
                if(elem.getType()==ObjType.empty)
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Board of size "+board.length;
    }

    public static BoardObject[][] getBoard() {
        return board;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void  movePlayer(Dimension c){
        ConsoleWriter.printBoard(board);
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
    private static void refreshCoordinates(Dimension c){
        Dimension dplayer = player.getCoordinates();
        board[dplayer.width][dplayer.height] = new Empty(new Dimension(dplayer.width, dplayer.height));
        board[c.width][c.height] = player;
        player.setCoordinates(c);
    }
}
