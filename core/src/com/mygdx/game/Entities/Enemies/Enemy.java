package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.Entities.Ship;
import com.mygdx.game.GameEngine.EnemyType;
import com.mygdx.game.GameEngine;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 09/08/2016.
 */
public abstract class Enemy extends Ship {
	
    //protected int lives;
    protected int index;
    protected int behavior;
    //public Sound shootSound;
    protected Timer.Task animation;
    protected Vector2 center = new Vector2();
    
    protected EnemyType type;
    
    protected int score = 0;
    protected int powerUpProb;

    
    //determinará si dropea carga o no
    public boolean charge;

    //variable que almacenará tiradas aleatorias como posibilidad de disparar, etc...
    protected int random;

    //temporizador enrtre disparos
    protected int timerShoot;


    public Enemy(float x, float y, int behavior) {
    	cooldown = 50;
//        //hspeed = 0;
//
        this.x = x;
        this.y = y;
//
//        index = 0;
//
//        timerShoot = 0;
//
        this.behavior = behavior;
        
        // Necesario para que hagan Spawn por fuera de la pantalla.
        // Cosas de la organizacion.
        setLivesOutsideScreen(true);
        
//
//        playAnimation();
        collisionBox = new Rectangle(x, y, width, height);
        
    	// Esto se usará mas tarde para ahorrar comprobaciones de colisión.
    	center.x = x + getWidth() / 2;
    	center.y = y + getHeight() / 2;
    	
    	this.lives = 2;

    }

    // Quita vidas
    public void decreaseLives(int lives) {
        this.lives -= lives;
    }

    // Mata. Se ha de llamar para matar un enemigo.
    public void kill() {
        lives = 0;
        remove = true;
    }

    public int getLives() {
        return this.lives;
    }
    
    public abstract void draw ();
    
    // Usado por separado por cada enemigo, porque sus animaciones NO son estándar.
    // TODO Deberia plantearse estandarizarlas
    protected abstract void playAnimation();

    public abstract void action(Player player);

    public abstract void runBehavior();
    
    public Vector2 getCenter() {
    	return center;
    }
    
    public int getScore() {
    	return score;
    }
    
    
    // Usado por el Logic para saber que tipo de bala dispara.
    public EnemyType getType() {
    	return type;
    }
    
    @Override
    public void move() {
    	// Moverlo simplemente.
    	x += hSpeed * Gdx.graphics.getDeltaTime();
		y += vSpeed * Gdx.graphics.getDeltaTime();
		
		// Comprobar si hace falta quitarlo o no.
		if ((this.y > MyGdxGame.HEIGHT || this.y + this.height < 0) && !canLiveOutsideScreen()) {
			this.remove = true;
		}
		if ((this.x > MyGdxGame.WIDTH || this.x + this.width < 0) && !canLiveOutsideScreen()) {
			this.remove = true;
		}
		
		// Desactivar la habilidad de vivir fuera de la pantalla una vez que entre el area de renderizado
		// Este se usa para cuando generamos los enemigos.
		// Los enemigos se generan fuera de la pantalla con el booleano en true, y despues se desactiva para
		// ser destruidos al salir de la pantalla por debajo.
		if (this.y < MyGdxGame.HEIGHT && this.y + this.height > 0) {
			this.setLivesOutsideScreen(false);
		}
    }

    
	public int getPowerUpProbability() {
		return powerUpProb;
	}


}
