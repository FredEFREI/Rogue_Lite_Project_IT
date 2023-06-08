package Model.BoardObjects.Collectible;

import Model.Board;
import Model.BoardObjects.BoardObject;
import Model.BoardObjects.Collectible.Collectible;
import Model.BoardObjects.ObjType;
import Model.BoardObjects.Player;

import java.awt.*;


public class Item extends BoardObject implements Collectible{
    private ArrayList<ObjType> types;
    private String name;
    private String description;
    private int damage;
    private int heal;
    private int armor;
    private int boostAtk;
    private int boostDef;

    public Item(String name, int damage, int heal, int armor, int boostAtk, int boostDef){
        this.name = name;
        this.damage = damage;
        this.heal = heal;
        this.armor = armor;
        this.boostAtk = boostAtk;
        this.boostDef = boostDef;

        types = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        
        if(damage != 0){
            types.add(ObjType.weapon);
            str.append("Inflict " + damage + " damages\n");
        }
        if (heal != 0){
            types.add(ObjType.heal);
            str.append("Heal yourself " + heal + " health\n");
        }
        if (armor != 0){
            types.add(ObjType.armor);
            str.append("Gain " + armor + " armor\n")
        }
        if (boostAtk != 0){
            types.add(Objtype.atkbonus);
            str.append("Augment your damage by " + boostAtk + "\n");
        }
        if (boostDef != 0){
            types.add(ObjType.defbonus);
            str.append("Reduce damage taken by " + boostDef + "\n");
        }

        description = str.String();
    }

    
}