import Controller.Controller;
import Model.Board;
import Saves.Save;
import Saves.SaveData;
import Vue.ConsoleWriter;

import java.io.Console;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe mère de l'application
 */
public class Main {
    static Controller c;
    public static void main(String[] args){
        System.out.println("//////////////\nCONSOLE KEEPER\n//////////////");
        c = askSave();
        System.out.println("Mob Level: "+Board.getBossDefeated());
        c.run();
        Save.setSave(new SaveData(Board.getBoard(),Board.getBossDefeated(), Board.getPlayer(), Board.getExit()));
        Save.saveGame();
    }

    public static Controller askSave(){
        System.out.println("Enter Save file name");
        String path = ConsoleWriter.readConsole();
        ArrayList<String> questions = new ArrayList<>();
        questions.add(("New game"));
        questions.add("Load game");
        int result = ConsoleWriter.AskQuestion(questions);
        Save.setPath(path);
        if (result == 1 ){
            SaveData data = Save.loadGame();
            if(data!=null) {
                c = new Controller(data.getBoardObjects(), data.getPlayer());
                Board.setBossDefeated(data.getMoblevel());
                Board.setExit(data.getExit());
                Board.setController(c);
            }else
                c = new Controller();
        }
        else {
            c = new Controller();
        }
        return c;
    }
}