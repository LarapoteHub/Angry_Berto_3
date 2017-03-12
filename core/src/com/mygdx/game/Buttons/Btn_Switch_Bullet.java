package com.mygdx.game.Buttons;

import com.mygdx.game.Engine.SprNames;
import com.mygdx.game.GameEngine;

/**
 * Created by SlowFolk on 2/15/2017.
 */

public class Btn_Switch_Bullet extends Button {
    private int mode;

    public Btn_Switch_Bullet() {
        // X, Y, Ancho, Alto, Nombre de sprite
        super(10, 50, 48, 48, SprNames.btn_switch_bullet.name());
        isPowerUp = false;
        name = SprNames.btn_switch_bullet.name();
        mode = 0;
    }

    public void onTouch() {
        // TODO Buscar sonido para cambio de tipo.
        // Mirar si hacen falta mas
        // Cambiar el mode dentro del limite.
        mode = ++mode % GameEngine.getPlayer().getNumModes();

        GameEngine.getPlayer().setMode(mode);

    }

    @Override
    public void draw(Integer a) {
        switch (mode) {
            case 0:
                spr[0].draw(GameEngine.batch);
                break;
            case 1:
                spr[1].draw(GameEngine.batch);
                break;
        }
    }
}
