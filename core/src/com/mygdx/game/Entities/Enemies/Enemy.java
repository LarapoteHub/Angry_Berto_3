package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Entities.PlainAnimations.Explosion;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.Entities.Ship;
import com.mygdx.game.GameEngine;
import com.mygdx.game.GameEngine.EnemyType;
import com.mygdx.game.MyGdxGame;

import java.util.Random;

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

    protected TextureRegion currentFrame;

    protected Animation currentAnimation;

    protected int FRAME_COLS;
    protected int FRAME_ROWS;

    protected float stateTime = 0f;

    //boolean para probar mecanica de hits (cuando un enemigo sea golpeado que se vuelva rojo)
    protected boolean hitted = false;
    //int para controlar el tiempo que se va a ver e nrojo el enemigo tras recibir daño.
    protected int hittedClock = 0;
    //variable para almacenar y restaurar el color actual de SpriteBatch.
    protected Color tmpColor;
    //variable para almacenar el numero de ciclos del render en los cuales el enemigo se verá de color rojo.
    protected int HITTED_TIME = 4;


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
        hitted = true;
    }

    // Mata. Se ha de llamar para matar un enemigo.
    public void kill() {
        lives = 0;
        remove = true;
        // Crear la explosion
        GameEngine.addEntity(new Explosion(x, y, width, height),
                GameEngine.EntityType.PLAIN_ANIMATION);
        // Incrementar la puntuacion del jugador
        GameEngine.getPlayer().addScore(score);
        // Spawnear carga de Power Up si hay suerte.
        Random rnd = new Random(System.nanoTime()
                * System.nanoTime() / 13);
        if (rnd.nextInt(100) < getPowerUpProbability())
            GameEngine.spawnPowerUpCharge(x, y);
    }

    public int getLives() {
        return this.lives;
    }
    
    public abstract void draw ();

    public abstract void initAnimation();

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

    //public abstract void initAnimation();

}
