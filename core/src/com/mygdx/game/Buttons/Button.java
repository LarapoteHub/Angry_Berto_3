package com.mygdx.game.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by Red Mercy on 10/24/2016.
 *
 * Molde comun para todos los botones, incluidos powerups
 */
public abstract class Button extends Rectangle {

    protected Sprite tex;
    protected Sprite[] textures;
    public static GameEngine engine;

    // Para poder acceder facilmente a cada boton, y para que no se repitan.
    // ES NECESARIO DARLE UN VALOR, SINO SOBREESCRIBE UN BOTON EXISTENTE!! ************************************************************
    protected String name;

    // Pixeles de margen para que sea mas facil darle al boton...
    protected int margin = 3;

    // Interactuar de distinta manera si es un btn de power up
    public boolean isPowerUp = false;

    // Ni idea si se va a usar o no...
    protected boolean multipleTextures = false;
    // Inicializacion estandar con el extra de la textura del botón
    public Button(float x, float y, float width, float height, Sprite tex) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.tex = tex;
        tex.setBounds(x, y, width, height);
        multipleTextures = false;
    }

    public Button(float x, float y, float width, float height, Sprite[] textures) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.textures = textures;
        for (Sprite t : textures) {
        	t.setBounds(x, y, width, height);
        }
        multipleTextures = true;
    }

    // el Texture index solo se va a poder usar cuando haya un array de texturas cargado en el boton.
    // En resto es ignorado por completo #ForeverAlone
    public void draw(Integer textureIndex) {
        if (multipleTextures) {
        	// NOTA: ESTO ES COMO SE USA...
        	// Nota2: textures.draw() No funca.... Ni idea como funca bien...
        	//textures[textureIndex].draw(GameEngine.batch);
        	GameEngine.batch.draw(textures[textureIndex].getTexture(), x, y, width, height);
        	// DEBUG
        	//System.out.println("X: " + textures[textureIndex].getX() + " Y: " + textures[textureIndex].getY() + " Width: " + textures[textureIndex].getWidth() + " Height: " + textures[textureIndex].getHeight());
        	
        } else {
        	GameEngine.batch.draw(tex.getTexture(), x, y, width, height);
        }
    }

    // Aplicar la accion del boton. En caso de que sea uno de cambio de pantalla, el X e Y se ignoraran
    // public abstract void act(float x, float y, int currentCharge);

    // Comprobar si se ha tocado el boton y devolver el resultado
    public boolean isTouched(Vector3 touchCoords) {
    	if (this.isPowerUp) {
    		if (Gdx.input.justTouched()) {
                if (touchCoords.x >= x - margin && touchCoords.x <= x + width + margin
                        && touchCoords.y >= y - margin && touchCoords.y <= y + height + margin){
                    // Esta funcion DEBE pasar fuera de este boton, porque sino se ejecuta cuando no le corresponde tambien, por ej cuando esta
                    // en pausa.
                    // onTouch();	DO NOT UNCOMMENT ME!
                    return true;
                }
	            }
    	} else
        if (Gdx.input.isTouched()) {
        	if (touchCoords.x >= x - margin && touchCoords.x <= x + width + margin
                    && touchCoords.y >= y - margin && touchCoords.y <= y + height + margin){
            	// Esta funcion DEBE pasar fuera de este boton, porque sino se ejecuta cuando no le corresponde tambien, por ej cuando esta
            	// en pausa.
            	// onTouch();	DO NOT UNCOMMENT ME!
                return true;
            }
        }

        return false;
    }
    
    public abstract void onTouch();
    public String getName() {
        return name;
    }

}
