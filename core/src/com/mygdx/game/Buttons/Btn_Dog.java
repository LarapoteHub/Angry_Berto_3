package com.mygdx.game.Buttons;

import com.mygdx.game.Entities.PowerUps.AttackCow;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sounds;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by 100VOL on 20/08/2016.
 */
public class Btn_Dog extends PowerUp_Button {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3171969675451534756L;
	private int cost = 100;

    // Pasarle las coordenadas predeterminadas por Dani para la localizacion del boton
    // Tambien pasarle el array de sprites.
    public Btn_Dog() {
        super(10, 276, 48, 48, Sprites.btn_dog);
        isPowerUp = true;
        name = "btn_Dog";
    }

    // Sobreescribir y dibujar el que corresponda si hay la energia necesaria o no
    /*@Override
    public void draw(Integer chargeBar) {
        if (chargeBar >= cost) {
        	GameEngine.batch.draw(textures[1].getTexture(), x, y, width, height);
        } else {
        	GameEngine.batch.draw(textures[0].getTexture(), x, y, width, height);
        }
    }*/

    // X e Y son ignorados en este caso, ya que el PowerUp no depende de dónde esté el jugador.
    public void onTouch() {
    	if ((GameEngine.getPlayer().getCharge() >= cost)) {
    		Sounds.attackCowSound.play();
        	GameEngine.addEntity(new AttackCow(99.2f, -64), GameEngine.EntityType.BULLET_PLAYER);
        	GameEngine.addEntity(new AttackCow(194.4f, -64), GameEngine.EntityType.BULLET_PLAYER);
        	GameEngine.addEntity(new AttackCow(289.6f, -64), GameEngine.EntityType.BULLET_PLAYER);
        	GameEngine.addEntity(new AttackCow(384.8f, -64), GameEngine.EntityType.BULLET_PLAYER);
    		GameEngine.getPlayer().decreaseCharge(cost);
    	}
    	
        //31.2
        /*
        engine.entities.add(new AttackCow(engine, 99.2f, -64));
        engine.entities.add(new AttackCow(engine, 194.4f, -64));
        engine.entities.add(new AttackCow(engine, 289.6f, -64));
        engine.entities.add(new AttackCow(engine, 384.8f, -64));
        */
    }

    public int getCost() {
        return cost;
    }
}
