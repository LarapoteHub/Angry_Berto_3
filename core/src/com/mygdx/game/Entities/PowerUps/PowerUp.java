package com.mygdx.game.Entities.PowerUps;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Projectiles.Projectile;

/**
 * Created by 100VOL on 19/08/2016.
 */
public abstract class PowerUp extends Projectile {
	public static GameEngine engine;
    public int vSpeed;
    public boolean remove;
    

    public PowerUp(float x, float y, String sprName) {
        remove = false;
        this.x = x;
        this.y = y;
        this.setWidth(48);
        this.setHeight(48);

        // TODO Ajustar esto!
        Sprites.getSpriteByName(sprName)[0].setBounds(x, y, width, height);

    } //end constructor

    public abstract void draw();
}
