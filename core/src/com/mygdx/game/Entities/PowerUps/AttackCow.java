package com.mygdx.game.Entities.PowerUps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Projectiles.Projectile;

/**
 * Created by 100VOL on 20/08/2016.
 */
public class AttackCow extends Projectile {

    private Sprite spr;

    public AttackCow(float x, float y) {
        damage = 5;
        this.x = x;
        this.y = y;
        width = 64;
        height = 64;
        this.vSpeed = 750;

        // Crea copia local del sprite
        spr = new Sprite(Sprites.getSpriteByName("powerUp_attackCow")[0]);
        // Hace que coincida el sprite con el tamaño del objeto.
        spr.setBounds(x, y, width, height);
    }

    public void draw() {
        spr.draw(GameEngine.batch);
    }

	@Override
	public void decreaseLives(float lives) {
	}
	
	@Override
	public void destroy() {
		// NO USAR!!!
	}
	
	private void uberKill() {
		this.remove = true;
	}

	@Override
	public void move() {
        y += vSpeed * Gdx.graphics.getDeltaTime();
        if (this.y >= 864) {
            uberKill();
        }

        spr.setPosition(x, y);
	}



}
