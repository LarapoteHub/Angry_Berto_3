package com.mygdx.game.Entities.Enemies;

import com.badlogic.gdx.Game;
import com.mygdx.game.Entities.PlainAnimations.Explosion;
import com.mygdx.game.GameEngine;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by xldan on 20/02/2017.
 */

public class SatelliteOrbitEnemy extends Enemy {

    /*
    // this is degrees per second
   float speed = 10f;
   float rate = 5f;
   float circleX = (float) (Math.cos(drone.getAngle()) *
          (ship.getWidth() / 1.25) + centerX);
   float circleY = (float) (Math.sin(drone.getAngle()) *
          (ship.getHeight() / 1.25) + centerY);
   float angle = drone.getAngle() + (speed * (rate/1000)) % 360;
   if (angle >= 360) {
      angle = 0;
   }
   drone.setAngle(angle);
   drone.setX(circleX);
   drone.setY(circleY);
     */

    Enemy parent;
    private float angleWithParent;
    private float rotationSpeed = 10f;

    public SatelliteOrbitEnemy(Enemy parent) {

        super(0, 1000, -1);

        this.parent = parent;

        //inutil (por evitar nullpointers):
        this.x = 0;
        this.y = 1000;

        //this.angleWithParent = (float) ((Math.atan2(this.y - parent.getY(), -(this.x - parent.getY())) * 180.0d / Math.PI));

        //this.x = (float) (Math.cos(angleWithParent) * (this.width / 1.25) + (MyGdxGame.WIDTH / 2));
        //this.y = (float) (Math.sin(angleWithParent) * (this.height / 1.25) + (MyGdxGame.HEIGHT / 2));


        //vSpeed = -300;
        width = 24;
        height = 24;
        lives = 1;

        this.setLivesOutsideScreen(true);

        // Implementado en otro lado. Usar cooldown
        // timerShoot = 50;
        //cooldown = 100;

        damage = 2;

        // Valor que sumar para la puntuacion
        this.score = 0;

        // ascending = true;
        // descending = false;

        this.type = GameEngine.EnemyType.SATELLITE_ORBIT_ENEMY;
        powerUpProb = 10;

        Sprites.getSpriteByName("enemy_satellite_orbit")[0].setSize(width, height);

        //TODO, muy peligroso
        //this.cooldown = GameEngine.uni.getEnemyAttackSpeed();
        //this.lives = GameEngine.uni.getEnemyHPBuff() * lives;

        //this.FRAME_COLS = 4;
        //this.FRAME_ROWS = 1;

        //initAnimation();
    }

    @Override
    public void draw() {
        Sprites.getSpriteByName("enemy_satellite_orbit")[0].draw(GameEngine.batch);
        //GameEngine.batch.draw(Sprites.enemy_satellite_orbit.getTexture(), x, y, width, height);

    }

    //IMPORTANTÍSIMO SOBREESCRIBIR EL move() en este enemigo(al menos por ahora)
    @Override
    public void move() {

        if (parent.remove) {
            this.remove = true;
            GameEngine.addEntity(new Explosion(x, y, width, height, true),
                    GameEngine.EntityType.PLAIN_ANIMATION);
        }

        angleWithParent+=0.1f;
        if (angleWithParent>=360) {
            angleWithParent = 0;
        }



        //parent.get

        /*
        this.x = (float) (Math.cos(angleWithParent) * (this.width / 1.25) + (MyGdxGame.WIDTH / 2)) + parent.getX();
        this.y = (float) (Math.sin(angleWithParent) * (this.height / 1.25) + (MyGdxGame.HEIGHT / 2) + parent.getY());
        */

        this.x = (float) ((Math.cos(angleWithParent) * rotationSpeed) * (this.width / 2) + (parent.getX() + (parent.getWidth()/2)));
        this.y = (float) ((Math.sin(angleWithParent) * rotationSpeed) * (this.height / 2) + (parent.getY() + (parent.getHeight()/2)));

        Sprites.getSpriteByName("enemy_satellite_orbit")[0].setPosition(x, y);

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
