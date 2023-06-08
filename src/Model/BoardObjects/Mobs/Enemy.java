package Model.BoardObjects.Mobs;

import Model.BoardObjects.Collectible.Item;

import java.awt.*;
import java.util.ArrayList;

/**
 * Interface qui determine ce que peut faire un énemi
 */
public interface Enemy {
    /**
     * Méthode qui permet à un énemi d'attaquer
     */
    void attack();

    /**
     * Méthode qui permet de faire mourrir un énemi
     * @return retourne un liste contenant le loot du mob
     */
    ArrayList<Item> die();

    /**
     * Setteur pour les coordonées du mob
     * @param c
     */
    void setCoordiantes(Dimension c);
}
