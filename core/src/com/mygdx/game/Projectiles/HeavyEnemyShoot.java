package com.mygdx.game.Projectiles;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 23/08/2016.
 */
public class HeavyEnemyShoot extends Projectile {

    Timer.Task animation;
    int index;


    public HeavyEnemyShoot(Enemy enemy) {

        super();
        //x = enemy.getX() +16;
        //y = enemy.getY();

        this.width = 32; //16
        this.height = 72; //16

        this.x = enemy.getX() + enemy.getWidth()/2 - this.width/2;
        this.y = enemy.getY();

        // TODO MALA IDEA! Si el boss esta yendo hacia arriba la velocidad de la bala es casi 0
        vSpeed = enemy.getVSpeed();
        // En caso de que suba, restarla.
        if (vSpeed > 0)
            vSpeed -= vSpeed;

        // Double speed?
        vSpeed -= 700; //* 2;

        remove = false;

        index = 0;
        
        damage = 2;
        // Animacion de la bala
        playAnimation();

    }

    @Override
    public void draw() {

        GameEngine.batch.draw(Sprites.getSpriteByName("bullet_heavy_enemy")[index].getTexture(), x, y, getWidth(), getHeight());

    }

    private void playAnimation() {
    	// Threads???
        animation = new Timer.Task(){
            @Override
            public void run() {

               if (index == 0) {
                   index = 1;
               } else {
                   index = 0;
               }

            }

        };

        //cada 0.1 segundos incrementamos el índice de la imagen.
        Timer.schedule(animation,0.09f, 0.09f);

    } //end playAnimation()

	@Override
	public void kill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decreaseLives(float lives) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void move() {

        super.move();



    }

//	@Override
//	public void move() {
//		y -= vSpeed * Gdx.graphics.getDeltaTime(); //antes 250
//		if (y + getHeight() < 0) {remove = true;}
//	}



}
