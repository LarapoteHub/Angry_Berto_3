package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameEngine;

/**
 * Created by Red Mercy on 12/3/2016.
 */

public class Text {

    private float x, y, width, height;
    private String text;
    private Color color;

    public Text(String text, float x, float y) {
        // Sobrecarca de constructor
        this(text, x, y, Color.WHITE);

    }

    public Text(String text, float x, float y, Color color) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
    }


    public void draw() {
        // Guardar el color de texto viejo
        Color oldColor = GameEngine.printer.getColor();
        GameEngine.printer.setColor(color);
        GameEngine.printer
                .getRegion()
                .getTexture()
                .setFilter(Texture.TextureFilter.Linear,
                        Texture.TextureFilter.Linear);
        GameEngine.printer.draw(GameEngine.batch, text, x, y);
        // Restablecer el color de texto
        GameEngine.printer.setColor(oldColor);
    }

    public void setText(String newText) {
        this.text = newText;
    }
}
