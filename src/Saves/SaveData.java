package Saves;

import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Exit;
import Model.BoardObjects.Mobs.Player;

import java.io.Serializable;

public class SaveData implements Serializable {
    private Player player;

    private BoardObject[][] boardObjects;
    private int moblevel;

    private Exit exit;

    public SaveData(BoardObject[][] boardObjects,int moblevel, Player player, Exit exit){
        this.player = player;
        this.boardObjects = boardObjects;
        this.exit = exit;
        this.moblevel=moblevel;
    }


    public Player getPlayer() {
        return player;
    }

    public BoardObject[][] getBoardObjects() {
        return boardObjects;
    }

    public Exit getExit() {
        return exit;
    }

    public int getMoblevel() {
        return moblevel;
    }
}
