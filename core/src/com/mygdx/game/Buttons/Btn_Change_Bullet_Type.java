package com.mygdx.game.Buttons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Entities.PowerUps.AttackCow;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by SlowFolk on 2/15/2017.
 */

public class Btn_Change_Bullet_Type extends Button {
    private int mode;

    public Btn_Change_Bullet_Type() {
        super(10, 50, 48, 48, Sprites.btn_switch_bullet);
        isPowerUp = false;
        name = "btn_Change_Bullet_Type";
        mode = 0;
    }

    public void onTouch() {
        // TODO Buscar sonido para cambio de tipo.
        //    Sounds.attackCowSound.play();
        // Mirar si hacen falta mas
        // Cambiar el mode dentro del limite.
        mode = ++mode % GameEngine.getPlayer().getNumModes();

        GameEngine.getPlayer().setMode(mode);

    }

    @Override
    public void draw(Integer a) {
        switch (mode) {
            case 0:
                textures[0].draw(GameEngine.batch);
                break;
            case 1:
                textures[1].draw(GameEngine.batch);
                break;
        }
    }

    public int getCost() {
        return 0;
    }
}
