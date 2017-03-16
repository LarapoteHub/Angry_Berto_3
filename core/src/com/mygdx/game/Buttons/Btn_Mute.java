package com.mygdx.game.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Engine.MusicManager;
import com.mygdx.game.Engine.SprNames;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Red Mercy on 10/25/2016.
 */
public class Btn_Mute extends Button {

    public Btn_Mute() {
        super(MyGdxGame.WIDTH - 128 - 5, MyGdxGame.HEIGHT - 64 - 5, 64, 64, SprNames.btn_mute.name());
        name = SprNames.btn_mute.name();
    }

    @Override
    public void onTouch() {

        //esto hace que si el player se ha pasado el nivel, no se pueda pasar hasta que termine.

        GameEngine.setMuted(!GameEngine.isMuted());

        if (GameEngine.isMuted())
            MusicManager.stopMusic();
        else
            MusicManager.playMusic();

    }

    @Override
    public boolean isTouched(Vector3 touchCoords) {
        if (Gdx.input.justTouched()) {
            if (touchCoords.x >= x - margin && touchCoords.x <= x + width + margin
                    && touchCoords.y >= y - margin && touchCoords.y <= y + height + margin) {
                onTouch();
                return true;
            }
        }

        return false;
    }

    @Override
    public void draw(Integer texIndex) {
        // TODO Corregir esto cuando haya el segundo Sprite.
        if (GameEngine.isMuted())
            GameEngine.batch.draw(spr[0].getTexture(), x, y, width, height);
        else
            GameEngine.batch.draw(spr[0].getTexture(), x, y, width, height);
    }

}
