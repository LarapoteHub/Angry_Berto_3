package com.mygdx.game.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entities.Entity;
import com.mygdx.game.Entities.Player;

/**
 * Created by 100VOL on 06/09/2016.
 */
public abstract class Projectile extends Entity {
	
    protected float damage;
    protected int charge = 0;
    protected boolean gotIt = false;

    public Projectile() {
    	collisionBox = new Rectangle(x, y, width, height);
    }
    
    public abstract void draw();

    public float getDamage() {
    	return damage;
    }

    /**
     * Solo por si acaso
     * @param dmg nuevo daño a hacer este proyectil.
     */
    public void setDamage(float dmg) {
        this.damage = dmg;
    }
    
    public void destroy() {
    	this.remove = true;
    }
    
    public void move() {
    	this.x += hSpeed * Gdx.graphics.getDeltaTime();
    	this.y += vSpeed * Gdx.graphics.getDeltaTime();
    }

	public int getCharge() {
		gotIt = true;
		return charge;
	}

    // TODO Mirar como hacer que esto se muestre decentemente...
    /**
     * Metodo creado para centrar un proyectil en comparacion con algo.\n
     * Ejemplo: bala.centerTo("x", player.x, player.width);
     * @param coord "x" o "y", dependiendo de lo que se quiere centrar.
     * @param start la coordenada inicial del objeto al que se quiere centrar
     * @param size el tamaño correspondiente (ancho en caso de X o alto en caso de Y) del objeto
     *             con el que se quiere centrar.

     */
    public void centerTo(String coord, float start, float size) {
        if (coord.equals("x")) {
            this.x = start + (size - width) / 2;

        } else if (coord.equals("y")) {
            this.y = start + (size - height) / 2;
        }

    }
}
