package com.mygdx.game.Entities.PowerUps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.Projectiles.Projectile;

import java.util.Iterator;

/**
 * Created by 100VOL on 20/08/2016.
 */
public class AttackCow extends Projectile {


    Timer.Task animation;

    public AttackCow(float x, float y) {
        damage = 5;
        this.x = x;
        this.y = y;
        width = 64;
        height = 64;
        this.vSpeed = 750;
        //setLivesOutsideScreen(true);
    }

    public void draw() {
        GameEngine.batch.draw(Sprites.powerUp_attackCow, x, y, getWidth(), getHeight());
    }

	@Override
	public void decreaseLives(int lives) {
		// TODO Auto-generated method stub
		
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
	}



}
