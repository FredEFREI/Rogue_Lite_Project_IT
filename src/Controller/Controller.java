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
        b= new Board(7,3,1, this);
    }

    public Controller(BoardObject[][] board, Player player){
        Board.setPlayer(player);
        Board.setBoard(board);
    }
    public void run(){
        ConsoleWriter.printBoard(b.getBoard());
        System.out.println("Type help to get commands");
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
                case "i","inventory":
                    Board.getPlayer().useItem(null);
                    ConsoleWriter.printBoard(Board.getBoard());
                    break;
                case "r","restart":
                    restart();
                    ConsoleWriter.printBoard(Board.getBoard());
                    break;
                case "st","stats":
                    ConsoleWriter.printBar(ConsoleWriter.RED+"HEALTH"+ConsoleWriter.RESET,Board.getPlayer().getHealth());
                    ConsoleWriter.printBar(ConsoleWriter.ANSI_CYAN+"ARMOR"+ConsoleWriter.RESET,Board.getPlayer().getArmor());
                    System.out.println("Damages:\n"+Board.getPlayer().getDamages()+"\nDamage multiplicator:\n"+Board.getPlayer().getAtkmult());
                    break;
                case "sq","quit":
                    running=false;
                    break;
                case "b","boss":
                    b = new Board(Board.getPlayer(), this);
                    ConsoleWriter.printBoard(Board.getBoard());
                    break;
                case "help":
                    System.out.println("Commands:\nz,q,s,d: move around\nst/stats: to print your stats\ni/inventory: open your inventory\nb/boss: go directly to the boss room\nsq/quit: save and quit\nr/restart: restart the game ");
                    break;
            }
        }
    }
    public void restart() {
        b=new Board(Board.getPlayer(), 7, 3, 1, this);
    }
    public void nextBoard(){
        if (Board.getBoard().length < 13) {
            b = new Board(Board.getPlayer(), Board.getBoard().length, 2 * (sizeMult() + 2), 1 + 2 * sizeMult(), this);
        }
        else {
            b = new Board(Board.getPlayer(), this);
        }
    }
    public Board getBoard() {
        return b;
    }

    public int sizeMult(){
        switch (Board.getBoard().length) {
            case 7, 9:
                return 0;
            case 11, 12:
                return 1;
            case 13:
                return 2;
            case 15:
                return 3;
        }
        return 4;
    }
}
