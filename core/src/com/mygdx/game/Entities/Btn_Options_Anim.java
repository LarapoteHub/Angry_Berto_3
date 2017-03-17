package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Engine.SprNames;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Red Mercy on 3/16/2017.
 */

public class Btn_Options_Anim extends Entity {

    // TODO IMPORTANT: Crear un CreateEntityFromBtn o algo por el estilo.
    private Sprite spr = new Sprite(Sprites.getSpriteByName(SprNames.btn_options.name())[0]);

    private float rotationSpeed = MathUtils.random(5, 10);

    public Btn_Options_Anim(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        vSpeed = 700;
        spr.setBounds(x, y, width, height);
    }

    @Override
    public void draw() {

        // TODO OPTIMIZAR ESTO, POR DIOS!
        // Cortocircuito para que no haga nada si esta mu lejos de la pantalla.
        // Solo ocupará memoria, pero no ciclos (casi)
        if (!remove) {
            this.y -= vSpeed * Gdx.graphics.getDeltaTime();
            spr.rotate(rotationSpeed);

            spr.setPosition(x, y);

            spr.draw(GameEngine.batch);

            System.out.println((y + height) + "  " + (MyGdxGame.HEIGHT - 100));
            if (y + height < 0 - 100)
                remove = true;
        }
    }

    @Override
    public void decreaseLives(float lives) {

    }

    @Override
    public void move() {

    }
}
