package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Entities.PlainAnimations.Explosion;
import com.mygdx.game.Entities.Ship;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by xldan on 20/02/2017.
 */

public class SatelliteOrbitEnemy extends Enemy {

    private Ship parent;
    private float angleWithParent;
    private float rotationSpeed = 0.1f;
    private float distanceWithParent = 10f;

    private Behavior.SatelliteOrbitEnemy behavior;

    Sprite spr;

    public SatelliteOrbitEnemy(Ship parent, Behavior.SatelliteOrbitEnemy behavior) {

        super(0, 1000);

        this.parent = parent;

        //inutil (por evitar nullpointers):
        this.x = 0;
        this.y = 1000;

        this.behavior = behavior;

        width = 24;
        height = 24;
        lives = 1;

        this.setLivesOutsideScreen(true);

        damage = 2;

        // Valor que sumar para la puntuacion
        this.score = 0;

        // ascending = true;
        // descending = false;

        this.type = GameEngine.EnemyType.SATELLITE_ORBIT_ENEMY;
        powerUpProb = 10;

        spr = new Sprite(Sprites.getSpriteByName("enemy_satellite_orbit")[0]);
        spr.setSize(width, height);

        //initAnimation();
    }

    @Override
    public void draw() {
        spr.draw(GameEngine.batch);

    }

    //IMPORTANTÍSIMO SOBREESCRIBIR EL move() en este enemigo(al menos por ahora)
    @Override
    public void move() {

        if (parent.remove) {
            this.remove = true;
            GameEngine.addEntity(new Explosion(x, y, width, height, true),
                    GameEngine.EntityType.PLAIN_ANIMATION);
        }

        angleWithParent+=rotationSpeed;

        if (angleWithParent>=360) {
            angleWithParent = 0;
        }
                                                    //por algun motivo, donde esta 10f estaba la varaible rotation speed, aqui la velocidad no influye, influye arriba.
        this.x = (float) ((Math.cos(angleWithParent) * distanceWithParent) * (this.width / 2) + (parent.getX() + (parent.getWidth()/2)));
        this.y = (float) ((Math.sin(angleWithParent) * distanceWithParent) * (this.height / 2) + (parent.getY() + (parent.getHeight()/2)));

        spr.setPosition(x, y);

        //Sprites.getSpriteByName("enemy_satellite_orbit")[0].setPosition(x, y);

    }

    public void kill() {

    }

    //SOBRESCRITO PARA QUE SEA INMORTAL
    @Override
    public void decreaseLives(float lives) {

    }

    @Override
    public void initAnimation() {

    }

    @Override
    public void runBehavior() {

    }

    @Override
    public boolean canShoot() {
        return false;
    }

    @Override
    public void shoot() {

    }

}
