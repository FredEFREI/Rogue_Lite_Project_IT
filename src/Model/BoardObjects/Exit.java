package Model.BoardObjects;

import Model.Board;

import java.awt.*;

public class Exit extends BoardObject{
    private boolean state=false;
    public Exit(){
        type = ObjType.exit;
        Dimension d= Board.generateCoordinates(this);
        boardX=d.width;
        boardY=d.height;
    }
    public Exit(Dimension coordinates){
        type = ObjType.exit;
        boardX=coordinates.width;
        boardY=coordinates.height;
    }

    public void setstate(boolean state){
        this.state=state;
    }
    public boolean getSate(){return state;}

    @Override
    public Dimension getCoordinates() {
        return new Dimension(boardX,boardY);
    }

    @Override
    public boolean isOnBoard() {
        if(boardY>=0 && boardX>=0)
            return true;
        return false;
    }

    @Override
    public ObjType getType() {
        return type;
    }
}