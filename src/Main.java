import Controller.Controller;
import Model.Board;

import java.io.Console;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Classe mère de l'application
 */
public class Main {
    static Controller c;
    public static void main(String[] args){
        c=new Controller();
    }
}