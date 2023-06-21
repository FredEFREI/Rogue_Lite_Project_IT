package Controller;

import Model.Board;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Mobs.Player;
import Vue.ConsoleWriter;

import java.awt.*;

import java.util.ArrayList;
import java.util.Locale;



/**
 * Controlleur principal
 */
public class Controller {
    private Board b;

    private boolean running;

    public Controller(){
        b= new Board(17,1,1);
    }

    public Controller(BoardObject[][] board, Player player){
        Board.setPlayer(player);
        Board.setBoard(board);
    }
    public void run(){
        ConsoleWriter.printBoard(b.getBoard());
        String fout="";
        running = true;
        while (running){
            switch (ConsoleWriter.readConsole().trim().toLowerCase(Locale.ROOT)){
                case "z":
                    fout=Board.movePlayer(new Dimension(Board.getPlayer().getCoordinates().width-1,Board.getPlayer().getCoordinates().height));
                    ConsoleWriter.printBoard(Board.getBoard());
                    System.out.println(fout);
                    break;
                case "q":
                    fout=Board.movePlayer(new Dimension(Board.getPlayer().getCoordinates().width,Board.getPlayer().getCoordinates().height-1));
                    ConsoleWriter.printBoard(Board.getBoard());
                    System.out.println(fout);
                    break;
                case "s":
                    fout=Board.movePlayer(new Dimension(Board.getPlayer().getCoordinates().width+1,Board.getPlayer().getCoordinates().height));
                    ConsoleWriter.printBoard(Board.getBoard());
                    System.out.println(fout);
                    break;
                case "d":
                    fout=Board.movePlayer(new Dimension(Board.getPlayer().getCoordinates().width,Board.getPlayer().getCoordinates().height+1));
                    ConsoleWriter.printBoard(Board.getBoard());
                    System.out.println(fout);
                    break;
                case "i":
                    Board.getPlayer().useItem(null);
                    break;
                case "restart":
                    restart();
                    break;
                case "stats":
                    ConsoleWriter.printBar("HEALTH",Board.getPlayer().getHealth());
                    ConsoleWriter.printBar("ARMOR",Board.getPlayer().getArmor());
                    System.out.println("Damages:\n"+Board.getPlayer().getDamage()+"\nDamage multiplicator:\n"+Board.getPlayer().getAtkmult());
                    break;
                case "stop":
                    running=false;
                    break;
            }
        }
    }
    public void restart() {
        b=new Board(Board.getPlayer(), Board.getBoard().length - 2,5,3);
        ConsoleWriter.printBoard(Board.getBoard());
    }
    public void nextBoard(){
        b=new Board(Board.getPlayer(),Board.getBoard().length-4,5,1);
        ConsoleWriter.printBoard(Board.getBoard());
    }

}
