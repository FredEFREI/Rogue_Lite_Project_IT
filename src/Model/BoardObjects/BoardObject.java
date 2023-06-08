package Model.BoardObjects;

import java.awt.*;

/**
 * Classe abstraite mère de tous les objets affichables sur la board
 */
public abstract class BoardObject {
    /**
     * Type d'objet (armure, vie ...)
     */
    protected ObjType type;
    /**
     * coordonée x de l'objet sur le plateau
     */
    protected int boardX=-1;
    /**
     * coordonée y de l'objet sur le plateau
     */
    protected int boardY=-1;

    /**
     * Méthode qui permet d'obtenir les coordonées d'un objet
     * @return retourne les coordonées de l'objet
     */
    protected abstract Dimension getCoordinates();

    /**
     * Méthode Permettant de savoir si l'objet est sur le plateau ou dans l'inventaire
     * @return retourne true si l'objet est sur le plateau et false si il est dans l'inventaire
     */
    protected abstract boolean isOnBoard();

    /**
     * Getteur pour le type de l'objet
     * @return retourne le type de l'objet
     */
    public abstract ObjType getType();
}
