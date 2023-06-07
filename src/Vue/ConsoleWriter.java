package Vue;

import Model.BoardObjects.BoardObject;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleWriter {
    private static Scanner sc=new Scanner(System.in);
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

    public static String AskQuestion(ArrayList<String> options){
        boolean stopped=false;
        String in="";
        while (!stopped) {
            System.out.println("Choose an option: \n");
            for (String option : options) {
                System.out.println(options.indexOf(option) + ": " + option);
            }
            try {

                in=sc.next().trim().toLowerCase();
                Integer.parseInt(in);
                return in;

            } catch (NumberFormatException e) {
                if(in.equals("stop")){return null;}
                System.out.println("Please enter the number of the answer");
            }
        }
        return in;
    }
    public static String readConsole(){return sc.next();}
}
