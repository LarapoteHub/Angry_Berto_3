package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameEngine;

/**
 * Created by Red Mercy on 10/16/2016.
 */
public abstract class Entity {
    protected float x, y;
	protected float width;
	protected float height;
	protected float vSpeed = 0;
	protected float hSpeed = 0;
    protected Rectangle collisionBox;     // Need to change this
    protected Texture tex;
    public static GameEngine engine;
    
    protected int damage;

    // Quizas deberia usar metodos GET y SET...
    public boolean remove = false;
    
    // Un booleano especial para saber si puede vivir fuera de la pantalla
    // Único uso: el de los enemigos cuando aparecen en ola.
    private boolean livesOutsideScreen = false;
    
    public abstract void draw();
    public void kill() {
        remove = true;
    }
    public abstract void decreaseLives(int lives);
    public abstract void move();


    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float[] getPosition(){
        return new float[]{x, y};       // Forma bonita de devolverlo.
    }

    public Rectangle getCollisionBox() {
    	return collisionBox;
    }
    
    public boolean isColliding(Entity e) {
    	return x < e.x + e.width && x + width > e.x && y < e.y + e.height && y + height > e.y;
    }
    
    public float getVSpeed() {
		return vSpeed;
	}
	public void setVSpeed(float vSpeed) {
		this.vSpeed = vSpeed;
	}
	public float getHSpeed() {
		return hSpeed;
	}
	public void setHSpeed(float hSpeed) {
		this.hSpeed = hSpeed;
	}
	public void setX(float x) {
    	this.x = x;
    }
    
    public void setY(float y) {
    	this.y = y;
    }

    public float getX() {
    	return x;
    }
    
    public float getY() {
    	return y;
    }
    
    public boolean canLiveOutsideScreen() {
		return livesOutsideScreen;
	}
    
    public void setLivesOutsideScreen(boolean livesOutsideScreen) {
		this.livesOutsideScreen = livesOutsideScreen;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}

}
