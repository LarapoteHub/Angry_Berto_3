package com.mygdx.game.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 23/08/2016.
 */
public class HeavyEnemyShoot extends Projectile {

    Timer.Task animation;
    int index;


    public HeavyEnemyShoot(Enemy enemy) {

        super();
        x = enemy.getX() +16;
        y = enemy.getY();

        this.setWidth(32); //16
        this.setHeight(72); //16

        vSpeed = enemy.getVSpeed() - 700;

        remove = false;

        index = 0;
        
        damage = 2;

        playAnimation();

    }

    @Override
    public void draw() {

        GameEngine.batch.draw(Sprites.bullet_heavy_enemy[index].getTexture(), x, y, getWidth(), getHeight());

    }

    // TODO: Quitar esto!
    
    public void action(Player player) {

        
        // en caso de que el enemigo se salga de los márgenes de la pantalla por debajo, la eliminamos.
        
/*
        //si un disparo colisiona con el jugador...
        if (this.overlaps(player)) {
            // reproducimos el sonido correspondiente...
            Sounds.explodeSound.play();
            //restamos una vida al jugador
            player.decreaseLives(2);
            // y lo eliminamos de nuestro mundo.
            remove = true;
        }*/

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
	public void decreaseLives(int lives) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void move() {
//		y -= vSpeed * Gdx.graphics.getDeltaTime(); //antes 250
//		if (y + getHeight() < 0) {remove = true;}
//	}



}
