package Model.BoardObjects.Collectible;

import Model.BoardObjects.ObjType;
import Model.BoardObjects.Player;

import java.awt.*;

public interface Collectible {
    int collect();
    int use(Player p);
}
