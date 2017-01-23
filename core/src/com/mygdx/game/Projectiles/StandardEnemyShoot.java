package com.mygdx.game.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by 100VOL on 23/08/2016.
 */
public class StandardEnemyShoot extends Projectile {

    public StandardEnemyShoot(Enemy enemy) {

        super();
        x = enemy.getX() + enemy.getWidth() / 2 - 7; //??????
        y = enemy.getY();
        
        damage = 1;

        this.setWidth(16);
        this.setHeight(16);
        // Deberia basarse en la velocidad del enemigo.... pero 100 es muy poco
        // Y deberia tener una velocidad maxima de disparo tambien...
        vSpeed = 300 + 100;//enemy.getVSpeed()+100;

        remove = false;

    }

    @Override
    public void draw() {

        GameEngine.batch.draw(Sprites.bullet_enemy, x, y, getWidth(), getHeight());

    }

    
    public void act(Player player) {

        
        // en caso de que el enemigo se salga de los márgenes de la pantalla por debajo, la eliminamos.
        
/*
        //si un disparo colisiona con el jugador...
        if (this.overlaps(player)) {
            // reproducimos el sonido correspondiente...
            Sounds.explodeSound.play();
            //restamos una vida al jugador
            player.decreaseLives(1);
            // y lo eliminamos de nuestro mundo.
            remove = true;
        }*/

    }

	@Override
	public void kill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decreaseLives(int lives) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		y -= vSpeed * Gdx.graphics.getDeltaTime(); //antes 250
		if (y + getHeight() < 0) {remove = true;}
	}

}
