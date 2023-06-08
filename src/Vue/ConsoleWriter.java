package Vue;

import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Collectible.Collectible;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe permettant la lecture des entrées et l'affichage dans la console
 */
public class ConsoleWriter {
    /**
     * Scanner de l'application
     */
    private static Scanner sc=new Scanner(System.in);

    /**
     * Méthode affichant un plateau de jeu
     * @param board Tableau à double entrée représentant le plateau de jeu
     */
    public static void printBoard(BoardObject[][] board){
        String res="";
        for (BoardObject[] row:board) {
            for (BoardObject elem:row) {
                switch (elem.getType()){
                    case wall:
                        res+="W\t";
                        break;
                    case player:
                        res+="@\t";
                        break;
                    case enemy:
                        res+="!\t";
                        break;
                    case empty:
                        res+=" \t";
                        break;
                }
                if(elem instanceof Collectible) {
                    res += "+\t";
                }
            }
            res+="\n";
        }
        System.out.println( res );
    }

    /**
     * Méthode qui affiche une barre de progression avec un titre
     * @param barname Titre de la barre de progression
     * @param value Valeur en % de la barre
     */
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

    /**
     * Demande à l'utilisateur de choisir une option
     * @param options liste de String représentant les options de l'utilisateur
     * @return retourne 0 si stoppé, -1 en cas d'erreur, et l'index du choix dans la liste
     */
    public static int AskQuestion(ArrayList<String> options){
        boolean stopped=false;
        String in="";
        while (!stopped) {
            System.out.println("Choose an option: \n");
            for (String option : options) {
                System.out.println((options.indexOf(option))+1 + ": " + option);
            }
            try {

                in=sc.next().trim().toLowerCase();
                int c=Integer.parseInt(in);
                if(c<=options.size() && c>0)
                    return c-1;
                else
                    System.out.println("Wrong number, please choose between 1 and "+options.size());

            } catch (NumberFormatException e) {
                if(in.equals("stop")){return 0;}
                System.out.println("Please enter the number of the answer");
            }
        }
        return -1;
    }

    /**
     * Méthode qui permet de lire une ligne de la console
     * @return retourne la ligne que l'utilisateur à entré
     */
    public static String readConsole(){return sc.next();}
}
