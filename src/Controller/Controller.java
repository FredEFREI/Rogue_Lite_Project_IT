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
        DataInputStream input = new DataInputStream(System.in);
        Scanner sc=new Scanner(System.in);
        while (running){
            switch (sc.next().trim().toLowerCase(Locale.ROOT)){
                case "z":
                    Board.movePlayer(new Dimension(Board.getPlayer().getCoordinates().width-1,Board.getPlayer().getCoordinates().height));
                    break;
                case "q":
                    Board.movePlayer(new Dimension(Board.getPlayer().getCoordinates().width,Board.getPlayer().getCoordinates().height-1));

                    break;
                case "s":
                    Board.movePlayer(new Dimension(Board.getPlayer().getCoordinates().width+1,Board.getPlayer().getCoordinates().height));
                    break;
                case "d":
                    Board.movePlayer(new Dimension(Board.getPlayer().getCoordinates().width,Board.getPlayer().getCoordinates().height+1));
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