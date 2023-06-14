package Controller;

import Model.Board;
import Model.BoardObjects.Collectible.Collectible;
import Vue.ConsoleWriter;

import java.awt.*;
import java.io.Console;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


/**
 * Controlleur principal
 */
public class Controller {
    static Board b;
    public Controller(){
        boolean running=true;
        b=new Board(5,5,1,1);
        String fout="";
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
                    ConsoleWriter.printList("Inventory :",Board.getPlayer().getInventory());
                    ArrayList<String> o=new ArrayList<>();
                    o.add("Use an Item");
                    if(ConsoleWriter.AskQuestion(o)==0) {
                        ArrayList<String> opt = new ArrayList<>();
                        for (Collectible elem : Board.getPlayer().getInventory()) {
                            opt.add(elem.toString());
                        }
                        int itemid=ConsoleWriter.AskQuestion(opt);
                        if(itemid>=0) {
                            Board.getPlayer().getInventory().get(itemid).use(Board.getPlayer(), null);
                            Board.getPlayer().removeItem(Board.getPlayer().getInventory().get(itemid));
                            ConsoleWriter.printBoard(Board.getBoard());
                            System.out.println("Item used");
                            break;
                        }
                    }
                    break;
                case "restart":
                    restart();
                    break;
                case "stats":
                    ConsoleWriter.printBar("HEALTH",Board.getPlayer().getHealth());
                    ConsoleWriter.printBar("ARMOR",Board.getPlayer().getArmor());
                    break;
                case "test":
                    ArrayList t=new ArrayList<>();
                    t.add("test");
                    t.add("tes");
                    System.out.println( ConsoleWriter.AskQuestion(t));
                    break;
                case "stop":
                    running=false;
                    break;
            }
        }
    }
    public static void restart() {b=new Board(5,5,1);}
    public static void nextBoard(){b=new Board(Board.getPlayer(),Board.getBoard().length-2,5,5,1);};
}
