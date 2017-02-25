package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.GameEngine;

/**
 * Created by Red Mercy on 12/3/2016.
 */

public class Text {

    private float x, y, width, height;
    private String text;
    private Color color;
    private boolean centerToPoint = false;
    private boolean scale;
    private float scaleX;
    private float scaleY;

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
        if (scale) {
            GameEngine.printer.getData().setScale(scaleX, scaleY);
        }
        GameEngine.printer
                .getRegion()
                .getTexture()
                .setFilter(Texture.TextureFilter.Linear,
                        Texture.TextureFilter.Linear);
        if (centerToPoint) {
            GameEngine.printer.draw(GameEngine.batch, text, x, y, Align.center, Align.center, true);
        } else {
            GameEngine.printer.draw(GameEngine.batch, text, x, y);
        }

        if (scale) {
            //le devolvemos su escala original.
            GameEngine.printer.getData().setScale(1, 1);
        }

        // Restablecer el color de texto
        GameEngine.printer.setColor(oldColor);
    }

    public void setText(String newText) {
        this.text = newText;
    }

    //poniendo centerToPoint a true, el texto se centrará en el origen.
    public Text setCenterToPoint(boolean centerToPoint) {
        this.centerToPoint = centerToPoint;
        return this;
    }

    public Text setScale(float scaleX, float scaleY) {
        this.scale = true;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        return this;
    }
}
