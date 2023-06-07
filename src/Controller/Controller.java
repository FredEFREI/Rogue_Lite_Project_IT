package Controller;

import Model.Board;
import Vue.ConsoleWriter;

import java.awt.*;
import java.io.Console;
import java.io.DataInputStream;
import java.util.Locale;
import java.util.Scanner;

public class Controller {
    public Controller(){
        boolean running=true;
        Board b=new Board(5,5,1);
        while (running){
            switch (ConsoleWriter.readConsole().trim().toLowerCase(Locale.ROOT)){
                case "z":
                    Board.movePlayer(new Dimension(Board.getPlayer().getCoordinates().width-1,Board.getPlayer().getCoordinates().height));
                    ConsoleWriter.printBoard(Board.getBoard());
                    break;
                case "q":
                    Board.movePlayer(new Dimension(Board.getPlayer().getCoordinates().width,Board.getPlayer().getCoordinates().height-1));
                    ConsoleWriter.printBoard(Board.getBoard());
                    break;
                case "s":
                    Board.movePlayer(new Dimension(Board.getPlayer().getCoordinates().width+1,Board.getPlayer().getCoordinates().height));
                    ConsoleWriter.printBoard(Board.getBoard());
                    break;
                case "d":
                    Board.movePlayer(new Dimension(Board.getPlayer().getCoordinates().width,Board.getPlayer().getCoordinates().height+1));
                    ConsoleWriter.printBoard(Board.getBoard());
                    break;
                case "restart":
                    b=new Board(5,5,1);
                    break;
                case "stats":
                    ConsoleWriter.printBar("HEALTH",Board.getPlayer().getHealth());
                    ConsoleWriter.printBar("ARMOR",Board.getPlayer().getArmor());
                    break;
                case "stop":
                    running=false;
                    break;
            }
        }
    }
}
