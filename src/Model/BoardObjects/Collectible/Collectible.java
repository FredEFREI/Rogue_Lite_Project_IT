package Model.BoardObjects.Collectible;

import Model.BoardObjects.Mobs.*;
import Model.BoardObjects.Mobs.Player;

/**
 * interface pour les objets réccupérables
 */
public interface Collectible {
    /**
     * méthode permettant de réccupérer un objet
     * @return retourne l'état de le réccupération selon l'objet
     */
    int collect(Player p);

    /**
     * Méthode permettant d'utiliser un objet
     * @param p accesseur au joueur
     * @return retourne l'état final de l'utilisation selon l'objet
     */
    int use(Player p, Mob mob);
}
