package Model.BoardObjects.Mobs;

import Model.BoardObjects.Collectible.Item;

import java.util.ArrayList;

/**
 * Interface qui determine ce que peut faire un Mob
 */
public interface Mob {

    /**
     * Méthode qui permet à un Mob d'attaquer
     */
    void attack(Mob m);

    /**
     * Méthode qui permet de faire mourrir un Mob
     * @return retourne un liste contenant le loot du mob
     */
    void die();

    int getHealth();
    void inflictDamage(int i);

    boolean isDead();
}
