package Saves;

import Controller.Controller;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Player;

import java.io.Serializable;

public class SaveData implements Serializable {
    private Player player;

    private BoardObject[][] boardObjects;

    public SaveData(BoardObject[][] boardObjects, Player player){
        this.player = player;
        this.boardObjects = boardObjects;
    }


    public Player getPlayer() {
        return player;
    }

    public BoardObject[][] getBoardObjects() {
        return boardObjects;
    }
}
