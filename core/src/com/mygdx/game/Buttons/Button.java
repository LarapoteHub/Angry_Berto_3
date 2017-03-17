package com.mygdx.game.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by Red Mercy on 10/24/2016.
 * <p>
 * Molde comun para todos los botones, incluidos powerups
 */
public abstract class Button extends Rectangle {

    protected Sprite[] spr;

    // Para poder acceder facilmente a cada boton, y para que no se repitan.
    // ES NECESARIO DARLE UN VALOR, SINO SOBREESCRIBE UN BOTON EXISTENTE!! ************************************************************
    protected String name;

    // Pixeles de margen para que sea mas facil darle al boton...
    protected int margin = 3;

    // Interactuar de distinta manera si es un btn de power up
    public boolean isPowerUp = false;

    public boolean remove = false;

    // Inicializacion estandar con el extra de la textura del botón

    public Button(float x, float y, float width, float height, String name) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;

        // TODO Inspeccionar esto a ver si se puede eliminar/mejorar
        this.spr = Sprites.getSpriteByName(name);
        //this.spr = spr;
        for (Sprite t : spr) {
            //t.setPosition(x, y);
            // El tamaño ya se ajusta en la carga.
            t.setBounds(x, y, width, height);
        }
    }


    // el Texture index solo se va a poder usar cuando haya un array de texturas cargado en el boton.
    // En resto es ignorado por completo #ForeverAlone
    public void draw(Integer textureIndex) {

        // NOTA: ESTO ES COMO SE USA...
        // Nota2: spr.draw() No funca.... Ni idea como funca bien...
        //spr[textureIndex].draw(GameEngine.batch);
        if (textureIndex > spr.length - 1)
            textureIndex = spr.length - 1;

        spr[textureIndex].draw(GameEngine.batch);
        // DEBUG
        //System.out.println("X: " + spr[textureIndex].getX() + " Y: " + spr[textureIndex].getY() + " Width: " + spr[textureIndex].getWidth() + " Height: " + spr[textureIndex].getHeight());
    }

    // Comprobar si se ha tocado el boton y devolver el resultado
    public boolean isTouched(Vector3 touchCoords) {
        if (this.isPowerUp) {
            if (Gdx.input.justTouched()) {
                if (touchCoords.x >= x - margin && touchCoords.x <= x + width + margin
                        && touchCoords.y >= y - margin && touchCoords.y <= y + height + margin) {
                    // Esta funcion DEBE pasar fuera de este boton, porque sino se ejecuta cuando no le corresponde tambien, por ej cuando esta
                    // en pausa.
                    // onTouch();	DO NOT UNCOMMENT ME!
                    return true;
                }
            }
        } else if (Gdx.input.isTouched()) {
            if (touchCoords.x >= x - margin && touchCoords.x <= x + width + margin
                    && touchCoords.y >= y - margin && touchCoords.y <= y + height + margin) {
                // Esta funcion DEBE pasar fuera de este boton, porque sino se ejecuta cuando no le corresponde tambien, por ej cuando esta
                // en pausa.
                // onTouch();	DO NOT UNCOMMENT ME!
                return true;
            }
        }

        return false;
    }

    public abstract void onTouch();

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public String getName() {
        return name;
    }

}
