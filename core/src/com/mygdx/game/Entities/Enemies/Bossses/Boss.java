package com.mygdx.game.Entities.Enemies.Bossses;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entities.Enemies.Enemy;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Red Mercy on 12/5/2016.
 */
// TODO Implement this
public abstract class Boss extends Enemy {
    public Boss(float x, float y, int behavior) {
        super(x, y, behavior);
    }

    public abstract void updateBehaviour();

    public void move() {
        // Moverlo simplemente.
        x += hSpeed * Gdx.graphics.getDeltaTime();
        y += vSpeed * Gdx.graphics.getDeltaTime();

        // Comprobar si hace falta quitarlo o no.
        if ((this.y > MyGdxGame.HEIGHT || this.y + this.height < 0) && !canLiveOutsideScreen()) {
            vSpeed = -vSpeed;
        }                                                    //bad trip
        if ((this.x + this.width > MyGdxGame.WIDTH || this.x/* + this.width*/ < 0 + Backgrounds.backgroundPowerUps.getWidth()) && !canLiveOutsideScreen()) {
            hSpeed = -hSpeed;
        }

        // Desactivar la habilidad de vivir fuera de la pantalla una vez que entre el area de renderizado
        // Este se usa para cuando generamos los enemigos.
        // Los enemigos se generan fuera de la pantalla con el booleano en true, y despues se desactiva para
        // ser destruidos al salir de la pantalla por debajo.
        if (this.y < MyGdxGame.HEIGHT && this.y + this.height > 0) {
            this.setLivesOutsideScreen(false);
        }
    }


}
