package com.mygdx.game.Entities.Enemies.Bossses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Backgrounds;
import com.mygdx.game.Multimedia.Sprites;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Red Mercy on 12/28/2016.
 */

public class Boss1 extends Boss {
    Sprite spr = Sprites.boss_1;

    private int behaviorPhase;
    private int referenceY;
    private int actionTimer;

    public Boss1(float x, float y) {
        super(x, y, 0);
        vSpeed = -100; //-300 no se por que
        hSpeed = 0;
        //width = 48;
        //height = 48;
        width = 96;
        height = 96;
        lives = 2;

        // Implementado en otro lado. Usar cooldown
        // timerShoot = 50;
        //cooldown = 100;

        damage = 2;

        // Valor que sumar para la puntuacion
        score = 100;

        // ascending = true;
        // descending = false;

        type = GameEngine.EnemyType.BOSS;

        powerUpProb = 10;

        //incializamos el comportamiento en la primera fase
        behaviorPhase = 0;
        referenceY = 600;
        actionTimer = 0;
    }

    @Override
    public void updateBehaviour() {

    }

    @Override
    public void draw() {
        GameEngine.batch.draw(spr.getTexture(), x, y, width, height);
    }

    @Override
    protected void playAnimation() {

    }

    @Override
    public void action(Player player) {

    }

    @Override
    public void runBehavior() {

        //System.out.println("BOSS HSPEED: "+hSpeed+" VSPEED: "+vSpeed+" X: "+x+" Y: "+y);
        System.out.println("We need to build a BOSS");
        System.out.println("And some Universes");
        System.out.println("And it has to be build QUICLY!!");
        switch(behavior) {
            /*
            en funcion de si esta a la izquierda o a la derecha,
            le damos movimiento hacia un lado o hacia otro.
            */
            case 0:

                if (MathUtils.random(0, 1000) > 990) {

                    GameEngine.spawnEnemy(MathUtils.random(68, 432), 800, GameEngine.EnemyType.SPIKE_BALL, 0);
                }

                switch(behaviorPhase) {

                    case 0: vSpeed = -100;
                        if (y <= referenceY) {

                            hSpeed = 400;
                            vSpeed = 0;
                            behaviorPhase++;

                        }
                        break;

                    case 1:
                        if (actionTimer >= Math.random() * 500 + 250) {

                        hSpeed = 0;
                        vSpeed = -400;
                        referenceY-=144; //height * 1.5
                        if (referenceY <= 0) {
                            referenceY = 600;
                        }
                        actionTimer = 0;
                        behaviorPhase++;

                    }
                        break;

                    case 2: if (y<=0) {
                        vSpeed = 600;
                        behaviorPhase++;
                    }
                        break;

                    case 3: if (y >= referenceY) {
                        hSpeed = 400;
                        vSpeed = 0;
                        behaviorPhase = 1;

                    }
                        break;
                } //end switch

                break;
        } //end switch

    }

    @Override
    public boolean canShoot() {
        return false;
    }

    @Override
    public void shoot() {

    }

    @Override
    public void move() {

        //super.move();

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

        actionTimer++;
    }
}
