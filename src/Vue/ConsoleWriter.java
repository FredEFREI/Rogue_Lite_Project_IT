package Vue;

import Model.BoardObjects.BoardObject;

public class ConsoleWriter {
    public static void printBoard(BoardObject[][] board){
        String res="";
        for (BoardObject[] row:board) {
            for (BoardObject elem:row) {
                switch (elem.getType()){
                    case armor:
                        res+="D\t";
                        break;
                    case wall:
                        res+="W\t";
                        break;
                    case player:
                        res+="O\t";
                        break;
                    case empty:
                        res+=" \t";
                        break;
                }
            }
            res+="\n";
        }
        System.out.println( res );
    }

    public static void printBar(String barname,int value){
        String hb=barname+" :\n["+value+"%] {";
        for(int i=0;i<100;i++){
            if(i<value)
                hb+="|";
            else
                hb+=" ";
        }
        System.out.println( hb+"} ");
    }
}
