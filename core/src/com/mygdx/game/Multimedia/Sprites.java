package com.mygdx.game.Multimedia;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;

/**
 * Created by 100VOL on 20/08/2016.
 */

public class Sprites {

    private static HashMap<String, Sprite[]> spriteMap = new HashMap<String, Sprite[]>();

    public static Sprite[] getSpriteByName(String spriteName) {
        // Pillar el valor del HashMap
        Sprite[] retValue = spriteMap.get(spriteName);
        // En caso de que ese valor no exista en le HashMap, crearlo y añadirlo.
        if (retValue == null) {
            // No se sabe el tamaño, asique 0
            retValue = new Sprite[0];
            // La parte importante es agregar la clave al HashMap

            putSpriteWithName(spriteName, retValue);
        }

        return retValue;
    }

    public static void putSpriteWithName(String spriteName, Sprite[] sprs) {
        spriteMap.put(spriteName, sprs);
    }
}
