package Model.BoardObjects.Collectible;

import Model.BoardObjects.ObjType;
import Model.BoardObjects.Player;

import java.awt.*;

/**
 * interface pour les objets réccupérables
 */
public interface Collectible {
    /**
     * méthode permettant de réccupérer un objet
     * @return retourne l'état de le réccupération selon l'objet
     */
    int collect();

    /**
     * Méthode permettant d'utiliser un objet
     * @param p accesseur au joueur
     * @return retourne l'état final de l'utilisation selon l'objet
     */
    int use(Player p);
}
