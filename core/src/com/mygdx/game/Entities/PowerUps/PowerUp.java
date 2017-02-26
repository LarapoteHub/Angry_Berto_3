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
    private int dmgPower = 0;
    private Sprite tex;
    private Sprite[] textures;
    

    public PowerUp(float x, float y, String sprName) {
        remove = false;
        this.x = x;
        this.y = y;
        this.setWidth(48);
        this.setHeight(48);
        Sprites.getSpriteByName(sprName)[0].setBounds(x, y, width, height);

    } //end constructor
    
    public PowerUp(float x, float y, float width, float height, Sprite tex) {
    	remove = false;
    	this.x = x;
    	this.y = y;
    	this.setWidth(width);
    	this.setHeight(height);
    	this.tex = tex;
    }

    public abstract void draw();
}
