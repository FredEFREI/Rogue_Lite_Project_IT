package Saves;

import Controller.Controller;
import Model.Board;
import Model.BoardObjects.BoardObject;

import java.io.*;
import java.util.ArrayList;

public class Save {
    private static String path;

    private static SaveData save;

    public static void saveGame(){
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path));){
            output.writeObject(save);
        } catch (IOException ioException){
            System.out.println("ERROR: " + ioException.getMessage());
        }
    }

    public static SaveData getSave() {
        return save;
    }

    public static String getPath() {
        return path;
    }

    public static SaveData loadGame(){
        SaveData data = null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(path))){
            data = (SaveData) input.readObject();
        } catch (IOException | ClassNotFoundException ioException){
            System.out.println("ERROR: " + ioException.getMessage());
        }
        return data;
    }

    public static void setPath(String path) {
        Save.path = path;
    }

    public static void setSave(SaveData save) {
        Save.save = save;
    }
}
